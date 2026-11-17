import barbearia.view.Estilo;
import barbearia.view.TelaLogin;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Estilo.aplicarTema();
            new TelaLogin().setVisible(true);
        });
    }
}
