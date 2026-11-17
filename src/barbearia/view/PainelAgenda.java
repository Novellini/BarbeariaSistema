package barbearia.view;

import barbearia.dao.AgendamentoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PainelAgenda extends javax.swing.JPanel {

    private final TelaPrincipal principal;
    private final AgendamentoDAO dao = new AgendamentoDAO();

    private LocalDate dataAtual = LocalDate.now();
    private List<String[]> agendamentos = new ArrayList<>();

    public PainelAgenda(TelaPrincipal principal) {
        this.principal = principal;
        initComponents();
        Estilo.estilizarTabela(tblAgenda);
        Estilo.estilizarRolagem(rolTabela);
        Estilo.colunaStatus(tblAgenda, 4);
        Estilo.colunaStatus(tblAgenda, 5);
        Estilo.colunaAcao(tblAgenda, 6, "DETALHES");
        tblAgenda.getColumnModel().getColumn(0).setMinWidth(80);
        tblAgenda.getColumnModel().getColumn(0).setMaxWidth(90);
    }

    public void atualizar() {
        irPara(dataAtual);
    }

    public void irPara(LocalDate data) {
        dataAtual = data;
        txtData.setText(data.format(Estilo.DATA));
        carregar();
    }

    private void buscarDataDigitada() {
        try {
            irPara(LocalDate.parse(txtData.getText(), Estilo.DATA));
        } catch (DateTimeParseException ex) {
            Estilo.erro(this, "Data inválida! Use o formato dd/mm/aaaa.");
            txtData.requestFocusInWindow();
        }
    }

    private void carregar() {
        DefaultTableModel modelo = (DefaultTableModel) tblAgenda.getModel();
        modelo.setRowCount(0);
        try {
            agendamentos = dao.buscarAgendamentosDodia(dataAtual.format(Estilo.DATA_SQL));
            for (String[] ag : agendamentos) {
                modelo.addRow(new Object[]{ag[3], ag[1], ag[2], ag[4], ag[6],
                        ag[8].isEmpty() ? "-" : ag[8], ""});
            }
            String dia = dataAtual.equals(LocalDate.now()) ? "hoje" : dataAtual.format(Estilo.DATA);
            lblResumo.setText(agendamentos.isEmpty()
                    ? "Nenhum agendamento para " + dia + "."
                    : agendamentos.size() + " agendamento(s) para " + dia + ".");
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao carregar a agenda: " + ex.getMessage());
        }
    }

    private void abrirDetalhes(String[] ag) {
        DialogDetalhesAgendamento d = new DialogDetalhesAgendamento(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this), ag, dataAtual);
        d.setVisible(true);
        if (d.isPagamentoSolicitado()) {
            principal.registrarPagamento(dataAtual, Integer.parseInt(ag[0]));
        } else if (d.isAlterado()) {
            carregar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        pnlCabecalho = new javax.swing.JPanel();
        pnlTitulos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlAcaoCabecalho = new javax.swing.JPanel();
        btnNovoAgendamento = new barbearia.view.Botao();
        pnlCorpo = new javax.swing.JPanel();
        pnlBarraData = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        btnAnterior = new barbearia.view.Botao();
        txtData = Estilo.campoData();
        btnProximo = new barbearia.view.Botao();
        btnHoje = new barbearia.view.Botao();
        btnVer = new barbearia.view.Botao();
        lblResumo = new javax.swing.JLabel();
        rolTabela = new javax.swing.JScrollPane();
        tblAgenda = new javax.swing.JTable();

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
        lblTitulo.setText("Agenda do dia");
        pnlTitulos.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSubtitulo.setForeground(new java.awt.Color(107, 114, 128));
        lblSubtitulo.setText("Clique em DETALHES (ou dê dois cliques na linha) para gerenciar um agendamento");
        lblSubtitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pnlTitulos.add(lblSubtitulo);

        pnlCabecalho.add(pnlTitulos, java.awt.BorderLayout.WEST);

        pnlAcaoCabecalho.setOpaque(false);
        pnlAcaoCabecalho.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        btnNovoAgendamento.setText("+ AGENDAMENTO");
        btnNovoAgendamento.addActionListener(this::btnNovoAgendamentoActionPerformed);
        pnlAcaoCabecalho.add(btnNovoAgendamento);

        pnlCabecalho.add(pnlAcaoCabecalho, java.awt.BorderLayout.EAST);

        pnlConteudo.add(pnlCabecalho, java.awt.BorderLayout.NORTH);

        pnlCorpo.setOpaque(false);
        pnlCorpo.setLayout(new java.awt.BorderLayout(0, 14));

        pnlBarraData.setOpaque(false);
        pnlBarraData.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));

        lblData.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblData.setForeground(new java.awt.Color(17, 24, 39));
        lblData.setText("Data:");
        pnlBarraData.add(lblData);

        btnAnterior.setText("<");
        btnAnterior.setContorno(true);
        btnAnterior.setForeground(new java.awt.Color(17, 24, 39));
        btnAnterior.setToolTipText("Dia anterior");
        btnAnterior.addActionListener(this::btnAnteriorActionPerformed);
        pnlBarraData.add(btnAnterior);

        txtData.setPreferredSize(new java.awt.Dimension(130, 36));
        txtData.addActionListener(this::txtDataActionPerformed);
        pnlBarraData.add(txtData);

        btnProximo.setText(">");
        btnProximo.setContorno(true);
        btnProximo.setForeground(new java.awt.Color(17, 24, 39));
        btnProximo.setToolTipText("Próximo dia");
        btnProximo.addActionListener(this::btnProximoActionPerformed);
        pnlBarraData.add(btnProximo);

        btnHoje.setText("Hoje");
        btnHoje.setContorno(true);
        btnHoje.setForeground(new java.awt.Color(17, 24, 39));
        btnHoje.addActionListener(this::btnHojeActionPerformed);
        pnlBarraData.add(btnHoje);

        btnVer.setText("Ver agenda");
        btnVer.setContorno(true);
        btnVer.setForeground(new java.awt.Color(17, 24, 39));
        btnVer.addActionListener(this::btnVerActionPerformed);
        pnlBarraData.add(btnVer);

        lblResumo.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblResumo.setForeground(new java.awt.Color(107, 114, 128));
        lblResumo.setText(" ");
        lblResumo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 0));
        pnlBarraData.add(lblResumo);

        pnlCorpo.add(pnlBarraData, java.awt.BorderLayout.NORTH);

        rolTabela.setBorder(Estilo.bordaLinha());

        tblAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Horário", "Cliente", "Barbeiro", "Serviço", "Status", "Pagamento", "Ação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAgenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAgendaMouseClicked(evt);
            }
        });
        rolTabela.setViewportView(tblAgenda);

        pnlCorpo.add(rolTabela, java.awt.BorderLayout.CENTER);

        pnlConteudo.add(pnlCorpo, java.awt.BorderLayout.CENTER);

        add(pnlConteudo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoAgendamentoActionPerformed
        principal.novoAgendamento(dataAtual);
    }//GEN-LAST:event_btnNovoAgendamentoActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        irPara(dataAtual.minusDays(1));
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        buscarDataDigitada();
    }//GEN-LAST:event_txtDataActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        irPara(dataAtual.plusDays(1));
    }//GEN-LAST:event_btnProximoActionPerformed

    private void btnHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHojeActionPerformed
        irPara(LocalDate.now());
    }//GEN-LAST:event_btnHojeActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        buscarDataDigitada();
    }//GEN-LAST:event_btnVerActionPerformed

    private void tblAgendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAgendaMouseClicked
        int linha = tblAgenda.rowAtPoint(evt.getPoint());
        if (linha < 0) return;
        if (evt.getClickCount() == 2 || tblAgenda.columnAtPoint(evt.getPoint()) == 6) {
            abrirDetalhes(agendamentos.get(linha));
        }
    }//GEN-LAST:event_tblAgendaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnAnterior;
    private barbearia.view.Botao btnHoje;
    private barbearia.view.Botao btnNovoAgendamento;
    private barbearia.view.Botao btnProximo;
    private barbearia.view.Botao btnVer;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblResumo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcaoCabecalho;
    private javax.swing.JPanel pnlBarraData;
    private javax.swing.JPanel pnlCabecalho;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlCorpo;
    private javax.swing.JPanel pnlTitulos;
    private javax.swing.JScrollPane rolTabela;
    private javax.swing.JTable tblAgenda;
    private javax.swing.JFormattedTextField txtData;
    // End of variables declaration//GEN-END:variables
}
