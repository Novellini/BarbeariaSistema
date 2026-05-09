
package barbearia.dao;

import barbearia.conexao.ConexaoMySQL;
import barbearia.model.Barbeiro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarbeirDAO {
    
    //inserir barbeiro
    public void Inserir(Barbeiro barbeiro) throws SQLException{
        String sql = "inserir into barbeiro (nome,telefone,especialidade,ativo)"
                + "values (?,?,?,?)";
        try (Connection con = ConexaoMySQL.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, barbeiro.getNome());
            ps.setString(2, barbeiro.getTelefone());
            ps.setString(3, barbeiro.getEspecialidade());
            ps.setDate(4, barbeiro.getAtivo() != null
                    ? Date.valueOf(barbeiro.getDataNascimento()) : null);
            ps.executeUpdate();
        }
    }
    }
}
