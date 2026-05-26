
package barbearia.dao;

import barbearia.conexao.ConexaoMySQL;
import barbearia.model.Barbeiro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarbeirDAO {
    
    //Inserir barbeiro
    public void inserir(Barbeiro barbeiro) throws SQLException {
    String sql = "INSERT INTO barbeiro (nome, telefone, especialidade, ativo) "
               + "VALUES (?, ?, ?, ?)";
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, barbeiro.getNome());
        ps.setString(2, barbeiro.getTelefone());
        ps.setString(3, barbeiro.getEspecialidade());
        ps.setBoolean(4, barbeiro.isAtivo());
        ps.executeUpdate();
    }
}
    
    //Buscar todos os barbeiros
public List<Barbeiro> buscarTodos() throws SQLException {
    String sql = "SELECT * FROM barbeiro ORDER BY nome";
    List<Barbeiro> lista = new ArrayList<>();
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            lista.add(new Barbeiro(
                rs.getInt("id_barbeiro"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("especialidade"),
                rs.getBoolean("ativo")
            ));
        }
    }
    return lista;
}

//Buscar barbeiro por nome
public List<Barbeiro> buscarPorNome(String nome) throws SQLException {
    String sql = "SELECT * FROM barbeiro WHERE nome LIKE ? ORDER BY nome";
    List<Barbeiro> lista = new ArrayList<>();
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Barbeiro(
                rs.getInt("id_barbeiro"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("especialidade"),
                rs.getBoolean("ativo")
            ));
        }
    }
    return lista;
}

//Editar barbeiro
public void atualizar(Barbeiro barbeiro) throws SQLException {
    String sql = "UPDATE barbeiro SET nome=?, telefone=?, especialidade=?, ativo=? "
               + "WHERE id_barbeiro=?";
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, barbeiro.getNome());
        ps.setString(2, barbeiro.getTelefone());
        ps.setString(3, barbeiro.getEspecialidade());
        ps.setBoolean(4, barbeiro.isAtivo());
        ps.setInt(5, barbeiro.getId_barbeiro());
        ps.executeUpdate();
    }
}

//Deletar barbeiro
public void deletar(int idBarbeiro) throws SQLException {
    String sql = "DELETE FROM barbeiro WHERE id_barbeiro = ?";
    try (Connection con = ConexaoMySQL.obterConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idBarbeiro);
        ps.executeUpdate();
    }
    }
}