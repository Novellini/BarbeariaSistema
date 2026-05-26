package barbearia.view;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Sistema de Barbearia");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //Cabeçalho
        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(30, 30, 30));
        JLabel lblTitulo = new JLabel("Barbearia Sistema");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        JLabel lblSubtitulo = new JLabel("Bem-vindo ao sistema de gerenciamento");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSubtitulo.setForeground(new Color(180, 180, 180));
        painelTopo.setLayout(new GridLayout(2, 1));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelTopo.add(lblTitulo);
        painelTopo.add(lblSubtitulo);

        // Botões do Menu
        JPanel painelMenu = new JPanel(new GridLayout(3, 2, 15, 15));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        JButton btnClientes    = criarBotao("Clientes",     new Color(60, 60, 60));
        JButton btnBarbeiros   = criarBotao("Barbeiros",    new Color(60, 60, 60));
        JButton btnServicos    = criarBotao("Serviços",     new Color(60, 60, 60));
        JButton btnAgendar     = criarBotao("Agendar",      new Color(60, 60, 60));
        JButton btnAgendaDia   = criarBotao("Agenda do Dia",new Color(60, 60, 60));
        JButton btnSair        = criarBotao("Sair",         new Color(150, 40, 40));

        painelMenu.add(btnClientes);
        painelMenu.add(btnBarbeiros);
        painelMenu.add(btnServicos);
        painelMenu.add(btnAgendar);
        painelMenu.add(btnAgendaDia);
        painelMenu.add(btnSair);

        // Rodapé
        JPanel painelRodape = new JPanel();
        painelRodape.setBackground(new Color(30, 30, 30));
        JLabel lblRodape = new JLabel("Projeto Acadêmico — " + java.time.Year.now().getValue());
        lblRodape.setForeground(new Color(120, 120, 120));
        lblRodape.setFont(new Font("Arial", Font.PLAIN, 11));
        painelRodape.add(lblRodape);

        add(painelTopo,   BorderLayout.NORTH);
        add(painelMenu,   BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);

        // Eventos
        btnClientes.addActionListener(e  -> new TelaCliente().setVisible(true));
        btnBarbeiros.addActionListener(e -> new TelaBarbeiro().setVisible(true));
        btnServicos.addActionListener(e -> new TelaServico().setVisible(true));
        btnAgendar.addActionListener(e   -> JOptionPane.showMessageDialog(this, "Em breve!"));
        btnAgendaDia.addActionListener(e -> JOptionPane.showMessageDialog(this, "Em breve!"));
        btnSair.addActionListener(e      -> System.exit(0));
    }

    //Método auxiliar para criar botões padronizados
    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}