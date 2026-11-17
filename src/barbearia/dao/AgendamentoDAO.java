package barbearia.dao;

import barbearia.conexao.ConexaoMySQL;
import barbearia.model.Agendamento;
import barbearia.service.GradeHorarios;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    // O bloqueio por barbeiro também é usado pela versão web.
    public void inserir(Agendamento agendamento, List<Integer> idsServicos) throws SQLException {
        if (idsServicos.isEmpty() || new HashSet<>(idsServicos).size() != idsServicos.size()) {
            throw new IllegalArgumentException("Selecione serviços válidos.");
        }
        try (Connection con = ConexaoMySQL.obterConexao()) {
            con.setAutoCommit(false);
            try {
                try (var ps = con.prepareStatement("SELECT ativo FROM barbeiro WHERE id_barbeiro = ? FOR UPDATE")) {
                    ps.setInt(1, agendamento.getIdBarbeiro());
                    try (var rs = ps.executeQuery()) {
                        if (!rs.next() || !rs.getBoolean("ativo")) {
                            throw new IllegalArgumentException("Selecione um barbeiro ativo.");
                        }
                    }
                }
                List<BigDecimal> precos = new ArrayList<>();
                int minutos = 0;
                try (var ps = con.prepareStatement("SELECT preco, duracao_min FROM servico WHERE id_servico = ? AND ativo")) {
                    for (int id : idsServicos) {
                        ps.setInt(1, id);
                        try (var rs = ps.executeQuery()) {
                            if (!rs.next() || rs.getInt("duracao_min") <= 0) {
                                throw new IllegalArgumentException("Um dos serviços selecionados não está disponível.");
                            }
                            minutos += rs.getInt("duracao_min");
                            precos.add(rs.getBigDecimal("preco"));
                        }
                    }
                }
                LocalDateTime inicio = agendamento.getDataHora();
                var reservas = buscarReservas(con, agendamento.getIdBarbeiro(), inicio.toLocalDate());
                if (!GradeHorarios.cabe(inicio, minutos, LocalDateTime.now(), reservas)) {
                    throw new IllegalArgumentException("O atendimento não cabe nesse horário. Escolha outro.");
                }
                int idAgendamento;
                String sql = """
                        INSERT INTO agendamento (id_cliente, id_barbeiro, data_hora, status, observacao, duracao_reservada)
                        VALUES (?, ?, ?, ?, ?, ?)
                        """;
                try (var ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, agendamento.getIdCliente());
                    ps.setInt(2, agendamento.getIdBarbeiro());
                    ps.setObject(3, inicio);
                    ps.setString(4, agendamento.getStatus());
                    ps.setString(5, agendamento.getObservacao());
                    ps.setInt(6, minutos);
                    ps.executeUpdate();
                    try (var rs = ps.getGeneratedKeys()) {
                        if (!rs.next()) throw new SQLException("O banco não devolveu o código do agendamento.");
                        idAgendamento = rs.getInt(1);
                    }
                }
                try (var ps = con.prepareStatement(
                        "INSERT INTO agendamento_servico (id_agendamento, id_servico, preco_cobrado) VALUES (?, ?, ?)")) {
                    for (int i = 0; i < idsServicos.size(); i++) {
                        ps.setInt(1, idAgendamento);
                        ps.setInt(2, idsServicos.get(i));
                        ps.setBigDecimal(3, precos.get(i));
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                con.commit();
            } catch (SQLException | RuntimeException ex) {
                con.rollback();
                throw ex;
            }
        }
    }

    public List<GradeHorarios.Reserva> buscarReservas(int idBarbeiro, LocalDate dia) throws SQLException {
        try (Connection con = ConexaoMySQL.obterConexao()) {
            return buscarReservas(con, idBarbeiro, dia);
        }
    }

    private List<GradeHorarios.Reserva> buscarReservas(Connection con, int idBarbeiro, LocalDate dia) throws SQLException {
        String sql = """
                SELECT data_hora, COALESCE(duracao_reservada, 30) AS minutos
                FROM agendamento
                WHERE id_barbeiro = ? AND status <> 'cancelado' AND data_hora < ?
                    AND TIMESTAMPADD(MINUTE, COALESCE(duracao_reservada, 30), data_hora) > ?
                """;
        List<GradeHorarios.Reserva> reservas = new ArrayList<>();
        try (var ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBarbeiro);
            ps.setObject(2, dia.plusDays(1).atStartOfDay());
            ps.setObject(3, dia.atStartOfDay());
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservas.add(new GradeHorarios.Reserva(rs.getObject("data_hora", LocalDateTime.class), rs.getInt("minutos")));
                }
            }
        }
        return reservas;
    }

    //Atualizar status do agendamento
    public void atualizarStatus(int idAgendamento, String status) throws SQLException {
        String sql = "UPDATE agendamento SET status = ? WHERE id_agendamento = ? AND status <> 'cancelado'";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, idAgendamento);
            ps.executeUpdate();
        }
    }
    //Registrar pagamento (se já existir um pagamento pendente para o agendamento, ele é atualizado)
    public void registrarPagamento(int idAgendamento, java.math.BigDecimal valor, String formaPagamento) throws SQLException {
        registrarPagamento(idAgendamento, valor, formaPagamento, "pago");
    }

    public void registrarPagamento(int idAgendamento, java.math.BigDecimal valor, String formaPagamento, String status) throws SQLException {
        String sql = "INSERT INTO pagamento (id_agendamento, valor_total, forma_pagamento, status) "
                   + "VALUES (?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE valor_total = VALUES(valor_total), "
                   + "forma_pagamento = VALUES(forma_pagamento), status = VALUES(status), pago_em = NOW()";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAgendamento);
            ps.setBigDecimal(2, valor);
            ps.setString(3, formaPagamento);
            ps.setString(4, status);
            ps.executeUpdate();
        }
    }

    //Conta agendamentos de uma data (ignora cancelados); se status != null, conta só esse status
    public int contarPorData(String data, String status) throws SQLException {
        String sql = "SELECT COUNT(*) FROM agendamento WHERE DATE(data_hora) = ? "
                   + (status == null ? "AND status <> 'cancelado'" : "AND status = ?");
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data);
            if (status != null) ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    //Deletar agendamento
    public void deletar(int idAgendamento) throws SQLException {
        // Primeiro apaga os serviços vinculados (FK)
        String sqlPagamento = "DELETE FROM pagamento WHERE id_agendamento = ?";
        String sqlServicos = "DELETE FROM agendamento_servico WHERE id_agendamento = ?";
        String sqlAgendamento = "DELETE FROM agendamento WHERE id_agendamento = ?";

        try (Connection con = ConexaoMySQL.obterConexao()) {
            // Desativa autocommit para fazer as duas operações juntas
            con.setAutoCommit(false);
            try {
                PreparedStatement ps0 = con.prepareStatement(sqlPagamento);
                ps0.setInt(1, idAgendamento);
                ps0.executeUpdate();

                PreparedStatement ps1 = con.prepareStatement(sqlServicos);
                ps1.setInt(1, idAgendamento);
                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement(sqlAgendamento);
                ps2.setInt(1, idAgendamento);
                ps2.executeUpdate();

                con.commit(); // confirma as duas operações
            } catch (SQLException ex) {
                con.rollback(); // se der erro, desfaz tudo
                throw ex;
            }
        }
    }
//Busca agendamentos do dia com detalhes completos
public List<String[]> buscarAgendamentosDodia(String data) throws SQLException {
    String sql = "SELECT a.id_agendamento, c.nome AS cliente, b.nome AS barbeiro, "
               + "a.data_hora, a.status, a.observacao, "
               + "GROUP_CONCAT(s.nome SEPARATOR ', ') AS servicos, "
               + "SUM(ag_s.preco_cobrado) AS total, "
               + "MAX(p.status) AS pagamento "
               + "FROM agendamento a "
               + "JOIN cliente c ON c.id_cliente = a.id_cliente "
               + "JOIN barbeiro b ON b.id_barbeiro = a.id_barbeiro "
               + "JOIN agendamento_servico ag_s ON ag_s.id_agendamento = a.id_agendamento "
               + "JOIN servico s ON s.id_servico = ag_s.id_servico "
               + "LEFT JOIN pagamento p ON p.id_agendamento = a.id_agendamento "
               + "WHERE DATE(a.data_hora) = ? "
               + "GROUP BY a.id_agendamento "
               + "ORDER BY a.data_hora";

    List<String[]> lista = new ArrayList<>();
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, data);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // Cada agendamento vira um array de Strings para exibir na tabela
            lista.add(new String[]{
                rs.getString("id_agendamento"),
                rs.getString("cliente"),
                rs.getString("barbeiro"),
                rs.getTimestamp("data_hora").toLocalDateTime()
                    .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")),
                rs.getString("servicos"),
                "R$ " + rs.getString("total"),
                rs.getString("status"),
                rs.getString("observacao") != null ? rs.getString("observacao") : "",
                rs.getString("pagamento") != null ? rs.getString("pagamento") : ""
            });
        }
    }
    return lista;
}    
}