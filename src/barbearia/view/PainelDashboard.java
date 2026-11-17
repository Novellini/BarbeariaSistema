package barbearia.view;

import barbearia.dao.AgendamentoDAO;
import barbearia.dao.ClienteDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PainelDashboard extends javax.swing.JPanel {

    private final TelaPrincipal principal;
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public PainelDashboard(TelaPrincipal principal) {
        this.principal = principal;
        initComponents();
        Estilo.estilizarTabela(tblProximos);
        Estilo.estilizarRolagem(rolProximos);
        Estilo.colunaStatus(tblProximos, 3);
        tblProximos.getColumnModel().getColumn(0).setMinWidth(80);
        tblProximos.getColumnModel().getColumn(0).setMaxWidth(90);
    }

    public void atualizar() {
        String hoje = LocalDate.now().format(Estilo.DATA_SQL);
        DefaultTableModel modelo = (DefaultTableModel) tblProximos.getModel();
        modelo.setRowCount(0);
        try {
            lblAgendamentos.setText(String.valueOf(agendamentoDAO.contarPorData(hoje, null)));
            lblAtendimentos.setText(String.valueOf(agendamentoDAO.contarPorData(hoje, "concluido")));
            lblClientes.setText(String.valueOf(clienteDAO.buscarTodos().size()));

            // Mostra os agendamentos de hoje que ainda não passaram e não foram cancelados/concluídos
            String agora = LocalTime.now().format(Estilo.HORA);
            List<String[]> lista = agendamentoDAO.buscarAgendamentosDodia(hoje);
            for (String[] ag : lista) {
                String status = ag[6].toLowerCase();
                if (status.equals("cancelado") || status.equals("concluido")) continue;
                if (ag[3].compareTo(agora) < 0) continue;
                modelo.addRow(new Object[]{ag[3], ag[1], ag[4], status});
                if (modelo.getRowCount() == 5) break;
            }
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao carregar o resumo: " + ex.getMessage());
        }
        lblSemProximos.setVisible(modelo.getRowCount() == 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;
        pnlConteudo = new javax.swing.JPanel();
        pnlCabecalho = new javax.swing.JPanel();
        pnlTitulos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlCorpo = new javax.swing.JPanel();
        pnlIndicadores = new javax.swing.JPanel();
        pnlCardAgendamentos = new javax.swing.JPanel();
        lblCardAgendamentos = new javax.swing.JLabel();
        lblAgendamentos = new javax.swing.JLabel();
        pnlCardClientes = new javax.swing.JPanel();
        lblCardClientes = new javax.swing.JLabel();
        lblClientes = new javax.swing.JLabel();
        pnlCardAtendimentos = new javax.swing.JPanel();
        lblCardAtendimentos = new javax.swing.JLabel();
        lblAtendimentos = new javax.swing.JLabel();
        pnlProximos = new javax.swing.JPanel();
        lblProximos = new javax.swing.JLabel();
        rolProximos = new javax.swing.JScrollPane();
        tblProximos = new javax.swing.JTable();
        lblSemProximos = new javax.swing.JLabel();
        pnlRodape = new javax.swing.JPanel();
        btnNovoAgendamento = new barbearia.view.Botao();
        btnVerAgenda = new barbearia.view.Botao();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlConteudo.setBackground(new java.awt.Color(255, 255, 255));
        pnlConteudo.setBorder(Estilo.bordaConteudo());
        pnlConteudo.setLayout(new java.awt.BorderLayout());

        pnlCabecalho.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 18, 0));
        pnlCabecalho.setOpaque(false);
        pnlCabecalho.setLayout(new java.awt.BorderLayout());

        pnlTitulos.setOpaque(false);
        pnlTitulos.setLayout(new javax.swing.BoxLayout(pnlTitulos, javax.swing.BoxLayout.PAGE_AXIS));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblTitulo.setText("Dashboard");
        pnlTitulos.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSubtitulo.setForeground(new java.awt.Color(107, 114, 128));
        lblSubtitulo.setText("Resumo do dia");
        lblSubtitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pnlTitulos.add(lblSubtitulo);

        pnlCabecalho.add(pnlTitulos, java.awt.BorderLayout.WEST);

        pnlConteudo.add(pnlCabecalho, java.awt.BorderLayout.NORTH);

        pnlCorpo.setOpaque(false);
        pnlCorpo.setLayout(new java.awt.BorderLayout(0, 22));

        pnlIndicadores.setOpaque(false);
        pnlIndicadores.setLayout(new java.awt.GridLayout(1, 3, 18, 0));

        pnlCardAgendamentos.setBackground(new java.awt.Color(243, 244, 246));
        pnlCardAgendamentos.setBorder(Estilo.bordaCartao(14, 18, 14, 18));
        pnlCardAgendamentos.setLayout(new java.awt.BorderLayout(0, 6));

        lblCardAgendamentos.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblCardAgendamentos.setForeground(new java.awt.Color(107, 114, 128));
        lblCardAgendamentos.setText("Agendamentos hoje");
        pnlCardAgendamentos.add(lblCardAgendamentos, java.awt.BorderLayout.NORTH);

        lblAgendamentos.setFont(new java.awt.Font("SansSerif", 1, 28));
        lblAgendamentos.setForeground(new java.awt.Color(17, 24, 39));
        lblAgendamentos.setText("0");
        pnlCardAgendamentos.add(lblAgendamentos, java.awt.BorderLayout.CENTER);

        pnlIndicadores.add(pnlCardAgendamentos);

        pnlCardClientes.setBackground(new java.awt.Color(243, 244, 246));
        pnlCardClientes.setBorder(Estilo.bordaCartao(14, 18, 14, 18));
        pnlCardClientes.setLayout(new java.awt.BorderLayout(0, 6));

        lblCardClientes.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblCardClientes.setForeground(new java.awt.Color(107, 114, 128));
        lblCardClientes.setText("Clientes cadastrados");
        pnlCardClientes.add(lblCardClientes, java.awt.BorderLayout.NORTH);

        lblClientes.setFont(new java.awt.Font("SansSerif", 1, 28));
        lblClientes.setForeground(new java.awt.Color(17, 24, 39));
        lblClientes.setText("0");
        pnlCardClientes.add(lblClientes, java.awt.BorderLayout.CENTER);

        pnlIndicadores.add(pnlCardClientes);

        pnlCardAtendimentos.setBackground(new java.awt.Color(243, 244, 246));
        pnlCardAtendimentos.setBorder(Estilo.bordaCartao(14, 18, 14, 18));
        pnlCardAtendimentos.setLayout(new java.awt.BorderLayout(0, 6));

        lblCardAtendimentos.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblCardAtendimentos.setForeground(new java.awt.Color(107, 114, 128));
        lblCardAtendimentos.setText("Atendimentos concluídos");
        pnlCardAtendimentos.add(lblCardAtendimentos, java.awt.BorderLayout.NORTH);

        lblAtendimentos.setFont(new java.awt.Font("SansSerif", 1, 28));
        lblAtendimentos.setForeground(new java.awt.Color(17, 24, 39));
        lblAtendimentos.setText("0");
        pnlCardAtendimentos.add(lblAtendimentos, java.awt.BorderLayout.CENTER);

        pnlIndicadores.add(pnlCardAtendimentos);

        pnlCorpo.add(pnlIndicadores, java.awt.BorderLayout.NORTH);

        pnlProximos.setOpaque(false);
        pnlProximos.setLayout(new java.awt.BorderLayout(0, 10));

        lblProximos.setFont(new java.awt.Font("SansSerif", 1, 15));
        lblProximos.setForeground(new java.awt.Color(17, 24, 39));
        lblProximos.setText("Próximos agendamentos");
        pnlProximos.add(lblProximos, java.awt.BorderLayout.NORTH);

        rolProximos.setBorder(Estilo.bordaLinha());

        tblProximos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Horário", "Cliente", "Serviço", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rolProximos.setViewportView(tblProximos);

        pnlProximos.add(rolProximos, java.awt.BorderLayout.CENTER);

        lblSemProximos.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSemProximos.setForeground(new java.awt.Color(107, 114, 128));
        lblSemProximos.setText("Nenhum agendamento pendente para o restante do dia.");
        pnlProximos.add(lblSemProximos, java.awt.BorderLayout.SOUTH);

        pnlCorpo.add(pnlProximos, java.awt.BorderLayout.CENTER);

        pnlRodape.setOpaque(false);
        pnlRodape.setLayout(new java.awt.GridBagLayout());

        btnNovoAgendamento.setText("NOVO AGENDAMENTO");
        btnNovoAgendamento.addActionListener(this::btnNovoAgendamentoActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlRodape.add(btnNovoAgendamento, gridBagConstraints);

        btnVerAgenda.setText("VER AGENDA COMPLETA");
        btnVerAgenda.setContorno(true);
        btnVerAgenda.setForeground(new java.awt.Color(17, 24, 39));
        btnVerAgenda.addActionListener(this::btnVerAgendaActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlRodape.add(btnVerAgenda, gridBagConstraints);

        pnlCorpo.add(pnlRodape, java.awt.BorderLayout.SOUTH);

        pnlConteudo.add(pnlCorpo, java.awt.BorderLayout.CENTER);

        add(pnlConteudo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoAgendamentoActionPerformed
        principal.novoAgendamento(LocalDate.now());
    }//GEN-LAST:event_btnNovoAgendamentoActionPerformed

    private void btnVerAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAgendaActionPerformed
        principal.mostrar(TelaPrincipal.AGENDA);
    }//GEN-LAST:event_btnVerAgendaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnNovoAgendamento;
    private barbearia.view.Botao btnVerAgenda;
    private javax.swing.JLabel lblAgendamentos;
    private javax.swing.JLabel lblAtendimentos;
    private javax.swing.JLabel lblCardAgendamentos;
    private javax.swing.JLabel lblCardAtendimentos;
    private javax.swing.JLabel lblCardClientes;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblProximos;
    private javax.swing.JLabel lblSemProximos;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecalho;
    private javax.swing.JPanel pnlCardAgendamentos;
    private javax.swing.JPanel pnlCardAtendimentos;
    private javax.swing.JPanel pnlCardClientes;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlCorpo;
    private javax.swing.JPanel pnlIndicadores;
    private javax.swing.JPanel pnlProximos;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlTitulos;
    private javax.swing.JScrollPane rolProximos;
    private javax.swing.JTable tblProximos;
    // End of variables declaration//GEN-END:variables
}
