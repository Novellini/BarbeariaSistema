package barbearia.dao;

import barbearia.conexao.ConexaoMySQL;
import barbearia.model.Agendamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    //Inserir novo agendamento 
    //Retorna o id gerado pelo banco 
    public int inserir(Agendamento agendamento) throws SQLException {
        String sql = "INSERT INTO agendamento (id_cliente, id_barbeiro, data_hora, status, observacao) "
                   + "VALUES (?, ?, ?, ?, ?)";

        //pede para o banco devolver o id gerado
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, agendamento.getIdCliente());
            ps.setInt(2, agendamento.getIdBarbeiro());
            ps.setTimestamp(3, Timestamp.valueOf(agendamento.getDataHora()));
            ps.setString(4, agendamento.getStatus());
            ps.setString(5, agendamento.getObservacao());
            ps.executeUpdate();

            //Pega o id gerado e retorna
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    //Inserir serviços do agendamento (tabela intermediária)
    public void inserirServicos(int idAgendamento, int idServico, java.math.BigDecimal precocobrado) throws SQLException {
        String sql = "INSERT INTO agendamento_servico (id_agendamento, id_servico, preco_cobrado) "
                   + "VALUES (?, ?, ?)";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAgendamento);
            ps.setInt(2, idServico);
            ps.setBigDecimal(3, precocobrado);
            ps.executeUpdate();
        }
    }

    //Buscar agendamentos por data
    public List<Agendamento> buscarPorData(String data) throws SQLException {
        //yyyy-MM-dd
        String sql = "SELECT * FROM agendamento WHERE DATE(data_hora) = ? ORDER BY data_hora";
        List<Agendamento> lista = new ArrayList<>();
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Agendamento(
                    rs.getInt("id_agendamento"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_barbeiro"),
                    rs.getTimestamp("data_hora").toLocalDateTime(),
                    rs.getString("status"),
                    rs.getString("observacao")
                ));
            }
        }
        return lista;
    }

    //Atualizar status do agendamento
    public void atualizarStatus(int idAgendamento, String status) throws SQLException {
        String sql = "UPDATE agendamento SET status = ? WHERE id_agendamento = ?";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, idAgendamento);
            ps.executeUpdate();
        }
    }
    //Registrar pagamento
public void registrarPagamento(int idAgendamento, java.math.BigDecimal valor, String formaPagamento) throws SQLException {
    String sql = "INSERT INTO pagamento (id_agendamento, valor_total, forma_pagamento) "
               + "VALUES (?, ?, ?)";
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idAgendamento);
        ps.setBigDecimal(2, valor);
        ps.setString(3, formaPagamento);
        ps.executeUpdate();
    }
}

    //Deletar agendamento
    public void deletar(int idAgendamento) throws SQLException {
        // Primeiro apaga os serviços vinculados (FK)
        String sqlServicos = "DELETE FROM agendamento_servico WHERE id_agendamento = ?";
        String sqlAgendamento = "DELETE FROM agendamento WHERE id_agendamento = ?";

        try (Connection con = ConexaoMySQL.obterConexao()) {
            // Desativa autocommit para fazer as duas operações juntas
            con.setAutoCommit(false);
            try {
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
               + "SUM(ag_s.preco_cobrado) AS total "
               + "FROM agendamento a "
               + "JOIN cliente c ON c.id_cliente = a.id_cliente "
               + "JOIN barbeiro b ON b.id_barbeiro = a.id_barbeiro "
               + "JOIN agendamento_servico ag_s ON ag_s.id_agendamento = a.id_agendamento "
               + "JOIN servico s ON s.id_servico = ag_s.id_servico "
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
                rs.getString("observacao") != null ? rs.getString("observacao") : ""
            });
        }
    }
    return lista;
}    
}