package barbearia.conexao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoMySQL {

    public static Connection obterConexao() throws SQLException {
        Properties config = new Properties();
        Path arquivo = Path.of("config", "database.properties");
        if (Files.exists(arquivo)) {
            try (var leitor = Files.newBufferedReader(arquivo)) {
                config.load(leitor);
            } catch (IOException | IllegalArgumentException ex) {
                throw new SQLException("Não foi possível ler " + arquivo, ex);
            }
        }

        String url = valor(config, "DB_URL", "db.url", "jdbc:mysql://localhost:3306/barbearia");
        String usuario = valor(config, "DB_USER", "db.user", "root");
        String senha = valor(config, "DB_PASSWORD", "db.password", "");
        return DriverManager.getConnection(url, usuario, senha);
    }

    private static String valor(Properties config, String variavel, String chave, String padrao) {
        String ambiente = System.getenv(variavel);
        return ambiente != null ? ambiente : config.getProperty(chave, padrao);
    }
}
