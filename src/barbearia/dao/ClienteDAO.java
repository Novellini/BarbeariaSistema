package barbearia.dao;

import barbearia.conexao.ConexaoMySQL;
import barbearia.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //Inserir cliente
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, telefone, email, data_nascimento) "
                   + "VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
            ps.setDate(4, cliente.getDataNascimento() != null
                    ? Date.valueOf(cliente.getDataNascimento()) : null);
            ps.executeUpdate();
        }
    }
    //Buscar todos clientes
    public List<Cliente> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM cliente ORDER BY nome";
        List<Cliente> lista = new ArrayList<>();
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getDate("data_nascimento") != null
                        ? rs.getDate("data_nascimento").toLocalDate() : null,
                    rs.getTimestamp("criado_em").toLocalDateTime()
                ));
            }
        }
        return lista;
    }
    //Buscar por nome
    public List<Cliente> buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? ORDER BY nome";
        List<Cliente> lista = new ArrayList<>();
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getDate("data_nascimento") != null
                        ? rs.getDate("data_nascimento").toLocalDate() : null,
                    rs.getTimestamp("criado_em").toLocalDateTime()
                ));
            }
        }
        return lista;
    }
    //Editar cliente
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome=?, telefone=?, email=?, data_nascimento=? "
                   + "WHERE id_cliente=?";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
            ps.setDate(4, cliente.getDataNascimento() != null
                    ? Date.valueOf(cliente.getDataNascimento()) : null);
            ps.setInt(5, cliente.getIdCliente());
            ps.executeUpdate();
        }
    }
    //Deletar cliente
    public void deletar(int idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
        }
    }
}