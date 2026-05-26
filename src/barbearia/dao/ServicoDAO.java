
package barbearia.dao;

import barbearia.conexao.ConexaoMySQL;
import barbearia.model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    //inserir serviço novo
    public void inserir(Servico servico) throws SQLException {
        String sql = "INSERT INTO servico (nome, descricao, preco,duracao_min,ativo )"
                + "VALUES (?,?,?,?,?)";
        try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, servico.getNome());
            ps.setString(2, servico.getDescricao());
            ps.setBigDecimal(3, servico.getPreco());
            ps.setInt(4, servico.getDuracaoMin());
            ps.setBoolean(5,servico.isAtivo());
            ps.executeUpdate();
    }
}        
        
        //Buscar todos os serviços
public List<Servico> buscarTodos() throws SQLException {
    String sql = "SELECT * FROM servico ORDER BY nome";
    List<Servico> lista = new ArrayList<>();
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            lista.add(new Servico(
                rs.getInt("id_servico"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getBigDecimal("preco"),
                rs.getInt("duracao_min"),
                rs.getBoolean("ativo")
            ));
        }
    }
    return lista;  
}
    //Buscar serviço por nome
public List<Servico> buscarPorNome(String nome) throws SQLException {
    String sql = "SELECT * FROM servico WHERE nome LIKE ? ORDER BY nome";
    List<Servico> lista = new ArrayList<>();
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Servico(
                rs.getInt("id_servico"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getBigDecimal("preco"),
                rs.getInt("duracao_min"),
                rs.getBoolean("ativo")
            ));
        }
    }
    return lista;
}

// Editar serviço
public void atualizar(Servico servico) throws SQLException {
    String sql = "UPDATE servico SET nome=?, descricao=?, preco=?, duracao_min=?, ativo=? "
               + "WHERE id_servico=?";
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, servico.getNome());
        ps.setString(2, servico.getDescricao());
        ps.setBigDecimal(3, servico.getPreco());
        ps.setInt(4, servico.getDuracaoMin());
        ps.setBoolean(5, servico.isAtivo());
        ps.setInt(6, servico.getIdServico());
        ps.executeUpdate();
    }
}

//Deletar serviço
public void deletar(int idServico) throws SQLException {
    String sql = "DELETE FROM servico WHERE id_servico = ?";
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idServico);
        ps.executeUpdate();
    }
}
}