package barbearia.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;

// O menu troca o painel exibido no centro da janela.
public class TelaPrincipal extends javax.swing.JFrame {

    public static final String DASHBOARD = "Dashboard";
    public static final String AGENDA    = "Agenda";
    public static final String CLIENTES  = "Clientes";
    public static final String SERVICOS  = "Serviços";
    public static final String BARBEIROS = "Barbeiros";
    public static final String PAGAMENTOS= "Pagamentos";
    public static final String RELATORIOS = "Relatórios";
    public static final String NOVO_AGENDAMENTO = "NovoAgendamento";

    private static final Color MENU_TEXTO = new Color(0xD1D5DB);

    private final Map<String, JButton> botoesMenu = new LinkedHashMap<>();

    private final PainelDashboard dashboard = new PainelDashboard(this);
    private final PainelAgenda agenda = new PainelAgenda(this);
    private final PainelNovoAgendamento novoAgendamento = new PainelNovoAgendamento(this);
    private final PainelClientes clientes = new PainelClientes();
    private final PainelServicos servicos = new PainelServicos();
    private final PainelBarbeiros barbeiros = new PainelBarbeiros();
    private final PainelPagamento pagamento = new PainelPagamento(this);

    private final PainelRelatorios relatorios = new PainelRelatorios();

    public TelaPrincipal() {
        initComponents();

        pnlArea.add(dashboard, DASHBOARD);
        pnlArea.add(agenda, AGENDA);
        pnlArea.add(novoAgendamento, NOVO_AGENDAMENTO);
        pnlArea.add(clientes, CLIENTES);
        pnlArea.add(servicos, SERVICOS);
        pnlArea.add(barbeiros, BARBEIROS);
        pnlArea.add(pagamento, PAGAMENTOS);
        pnlArea.add(relatorios, RELATORIOS);

        botoesMenu.put(DASHBOARD, btnDashboard);
        botoesMenu.put(AGENDA, btnAgenda);
        botoesMenu.put(CLIENTES, btnClientes);
        botoesMenu.put(SERVICOS, btnServicos);
        botoesMenu.put(BARBEIROS, btnBarbeiros);
        botoesMenu.put(PAGAMENTOS, btnPagamentos);
        botoesMenu.put(RELATORIOS, btnRelatorios);
        for (JButton b : botoesMenu.values()) {
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                    if (b.getBackground() != Estilo.PRIMARIA) b.setBackground(Estilo.LATERAL_HOVER);
                }
                @Override public void mouseExited(java.awt.event.MouseEvent e) {
                    if (b.getBackground() != Estilo.PRIMARIA) b.setBackground(Estilo.LATERAL);
                }
            });
        }
        btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setSize(1100, 700);
        setMinimumSize(new java.awt.Dimension(960, 620));
        setLocationRelativeTo(null);

        mostrar(DASHBOARD);
    }

    public void mostrar(String tela) {
        switch (tela) {
            case DASHBOARD -> dashboard.atualizar();
            case AGENDA -> agenda.atualizar();
            case NOVO_AGENDAMENTO -> novoAgendamento.preparar(null);
            case CLIENTES -> clientes.atualizar();
            case SERVICOS -> servicos.atualizar();
            case BARBEIROS -> barbeiros.atualizar();
            case RELATORIOS -> relatorios.atualizar();
            case PAGAMENTOS -> pagamento.preparar(LocalDate.now(), -1);
        }
        exibir(tela);
    }

    private void exibir(String tela) {
        ((CardLayout) pnlArea.getLayout()).show(pnlArea, tela);
        String destaque = tela.equals(NOVO_AGENDAMENTO) ? AGENDA : tela;
        botoesMenu.forEach((nome, b) -> {
            boolean ativo = nome.equals(destaque);
            b.setBackground(ativo ? Estilo.PRIMARIA : Estilo.LATERAL);
            b.setForeground(ativo ? Color.WHITE : MENU_TEXTO);
        });
    }

    // Abre "Novo agendamento" já com a data escolhida na agenda
    public void novoAgendamento(LocalDate data) {
        novoAgendamento.preparar(data);
        exibir(NOVO_AGENDAMENTO);
    }

    // Agenda -> Pagamento com o agendamento já selecionado
    public void registrarPagamento(LocalDate data, int idAgendamento) {
        pagamento.preparar(data, idAgendamento);
        exibir(PAGAMENTOS);
    }

    public void mostrarAgenda(LocalDate data) {
        agenda.irPara(data);
        exibir(AGENDA);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;
        pnlMenu = new javax.swing.JPanel();
        lblMarca = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnAgenda = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnServicos = new javax.swing.JButton();
        btnBarbeiros = new javax.swing.JButton();
        btnPagamentos = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        pnlArea = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Barbearia - Sistema de Gerenciamento");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlMenu.setBackground(new java.awt.Color(31, 41, 51));
        pnlMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 14, 20, 14));
        pnlMenu.setPreferredSize(new java.awt.Dimension(200, 600));
        pnlMenu.setLayout(new java.awt.GridBagLayout());

        lblMarca.setFont(new java.awt.Font("SansSerif", 1, 20));
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setText("BARBEARIA");
        lblMarca.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 24, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlMenu.add(lblMarca, gridBagConstraints);

        btnDashboard.setBackground(new java.awt.Color(31, 41, 51));
        btnDashboard.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnDashboard.setForeground(new java.awt.Color(209, 213, 219));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnDashboard.setBorderPainted(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnDashboard, gridBagConstraints);

        btnAgenda.setBackground(new java.awt.Color(31, 41, 51));
        btnAgenda.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnAgenda.setForeground(new java.awt.Color(209, 213, 219));
        btnAgenda.setText("Agenda");
        btnAgenda.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnAgenda.setBorderPainted(false);
        btnAgenda.setFocusPainted(false);
        btnAgenda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgenda.addActionListener(this::btnAgendaActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnAgenda, gridBagConstraints);

        btnClientes.setBackground(new java.awt.Color(31, 41, 51));
        btnClientes.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnClientes.setForeground(new java.awt.Color(209, 213, 219));
        btnClientes.setText("Clientes");
        btnClientes.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnClientes.setBorderPainted(false);
        btnClientes.setFocusPainted(false);
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.addActionListener(this::btnClientesActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnClientes, gridBagConstraints);

        btnServicos.setBackground(new java.awt.Color(31, 41, 51));
        btnServicos.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnServicos.setForeground(new java.awt.Color(209, 213, 219));
        btnServicos.setText("Serviços");
        btnServicos.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnServicos.setBorderPainted(false);
        btnServicos.setFocusPainted(false);
        btnServicos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnServicos.addActionListener(this::btnServicosActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnServicos, gridBagConstraints);

        btnBarbeiros.setBackground(new java.awt.Color(31, 41, 51));
        btnBarbeiros.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnBarbeiros.setForeground(new java.awt.Color(209, 213, 219));
        btnBarbeiros.setText("Barbeiros");
        btnBarbeiros.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnBarbeiros.setBorderPainted(false);
        btnBarbeiros.setFocusPainted(false);
        btnBarbeiros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBarbeiros.addActionListener(this::btnBarbeirosActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnBarbeiros, gridBagConstraints);

        btnPagamentos.setBackground(new java.awt.Color(31, 41, 51));
        btnPagamentos.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnPagamentos.setForeground(new java.awt.Color(209, 213, 219));
        btnPagamentos.setText("Pagamentos");
        btnPagamentos.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnPagamentos.setBorderPainted(false);
        btnPagamentos.setFocusPainted(false);
        btnPagamentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPagamentos.addActionListener(this::btnPagamentosActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnPagamentos, gridBagConstraints);

        btnRelatorios.setBackground(new java.awt.Color(31, 41, 51));
        btnRelatorios.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnRelatorios.setForeground(new java.awt.Color(209, 213, 219));
        btnRelatorios.setText("Relatórios");
        btnRelatorios.setBorder(javax.swing.BorderFactory.createEmptyBorder(11, 14, 11, 14));
        btnRelatorios.setBorderPainted(false);
        btnRelatorios.setFocusPainted(false);
        btnRelatorios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRelatorios.addActionListener(this::btnRelatoriosActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        pnlMenu.add(btnRelatorios, gridBagConstraints);

        btnSair.setFont(new java.awt.Font("SansSerif", 0, 13));
        btnSair.setForeground(new java.awt.Color(156, 163, 175));
        btnSair.setText("Sair");
        btnSair.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btnSair.setBorderPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setFocusPainted(false);
        btnSair.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSair.addActionListener(this::btnSairActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        pnlMenu.add(btnSair, gridBagConstraints);

        getContentPane().add(pnlMenu, java.awt.BorderLayout.WEST);

        pnlArea.setBackground(new java.awt.Color(245, 246, 248));
        pnlArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlArea.setLayout(new java.awt.CardLayout());

        getContentPane().add(pnlArea, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        mostrar(DASHBOARD);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed
        mostrar(AGENDA);
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        mostrar(CLIENTES);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicosActionPerformed
        mostrar(SERVICOS);
    }//GEN-LAST:event_btnServicosActionPerformed

    private void btnBarbeirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarbeirosActionPerformed
        mostrar(BARBEIROS);
    }//GEN-LAST:event_btnBarbeirosActionPerformed

    private void btnPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamentosActionPerformed
        mostrar(PAGAMENTOS);
    }//GEN-LAST:event_btnPagamentosActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        mostrar(RELATORIOS);
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        if (Estilo.confirmar(this, "Deseja sair do sistema?")) {
            dispose();
            new TelaLogin().setVisible(true);
        }
    }//GEN-LAST:event_btnSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnBarbeiros;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnPagamentos;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnServicos;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JPanel pnlArea;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}
