package barbearia.view;

import barbearia.dao.AgendamentoDAO;
import java.time.LocalDate;

public class DialogDetalhesAgendamento extends javax.swing.JDialog {

    private final AgendamentoDAO dao = new AgendamentoDAO();
    private final int id;
    private boolean alterado;
    private boolean pagamentoSolicitado;

    // ag: linha de AgendamentoDAO.buscarAgendamentosDodia (0 id, 1 cliente, 2 barbeiro, 3 hora,
    // 4 serviços, 5 total, 6 status, 7 observação, 8 status do pagamento)
    public DialogDetalhesAgendamento(java.awt.Frame parent, String[] ag, LocalDate data) {
        super(parent, true);
        initComponents();
        id = Integer.parseInt(ag[0]);
        String status = ag[6].toLowerCase();
        boolean pago = ag[8].equalsIgnoreCase("pago");
        boolean encerrado = status.equals("cancelado") || status.equals("concluido");

        lblTitulo.setText("Agendamento das " + ag[3]);
        valCliente.setText(ag[1]);
        valBarbeiro.setText(ag[2]);
        valData.setText(data.format(Estilo.DATA));
        valHorario.setText(ag[3]);
        valServicos.setText(ag[4]);
        valTotal.setText(ag[5]);
        valStatus.setText(Estilo.rotuloStatus(status));
        valStatus.setForeground(Estilo.corStatus(status));
        valPagamento.setText(ag[8].isEmpty() ? "Não registrado" : Estilo.rotuloStatus(ag[8]));
        valObservacao.setText(ag[7].isEmpty() ? "-" : ag[7]);

        btnConfirmar.setEnabled(status.equals("agendado"));
        btnConcluir.setEnabled(!encerrado);
        btnPagar.setEnabled(!pago && !status.equals("cancelado"));
        btnCancelarAgendamento.setEnabled(!encerrado);

        Estilo.prepararDialogo(this, null, 560);
    }

    public boolean isAlterado() {
        return alterado;
    }

    public boolean isPagamentoSolicitado() {
        return pagamentoSolicitado;
    }

    private void mudarStatus(String status, String pergunta) {
        if (pergunta != null && !Estilo.confirmar(this, pergunta)) return;
        try {
            dao.atualizarStatus(id, status);
            alterado = true;
            dispose();
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFundo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        valCliente = new javax.swing.JLabel();
        lblBarbeiro = new javax.swing.JLabel();
        valBarbeiro = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        valData = new javax.swing.JLabel();
        lblHorario = new javax.swing.JLabel();
        valHorario = new javax.swing.JLabel();
        lblServicos = new javax.swing.JLabel();
        valServicos = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        valTotal = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        valStatus = new javax.swing.JLabel();
        lblPagamento = new javax.swing.JLabel();
        valPagamento = new javax.swing.JLabel();
        lblObservacao = new javax.swing.JLabel();
        valObservacao = new javax.swing.JLabel();
        pnlAcoes = new javax.swing.JPanel();
        btnConfirmar = new barbearia.view.Botao();
        btnConcluir = new barbearia.view.Botao();
        btnPagar = new barbearia.view.Botao();
        btnCancelarAgendamento = new barbearia.view.Botao();
        btnExcluir = new barbearia.view.Botao();
        btnFechar = new barbearia.view.Botao();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalhes do agendamento");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlFundo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFundo.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));
        pnlFundo.setLayout(new java.awt.BorderLayout(0, 18));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblTitulo.setText("Agendamento");
        pnlFundo.add(lblTitulo, java.awt.BorderLayout.NORTH);

        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new java.awt.GridLayout(0, 2, 12, 8));

        lblCliente.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblCliente.setForeground(new java.awt.Color(17, 24, 39));
        lblCliente.setText("Cliente");
        pnlInfo.add(lblCliente);

        valCliente.setFont(new java.awt.Font("SansSerif", 0, 13));
        valCliente.setForeground(new java.awt.Color(17, 24, 39));
        valCliente.setText("-");
        pnlInfo.add(valCliente);

        lblBarbeiro.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblBarbeiro.setForeground(new java.awt.Color(17, 24, 39));
        lblBarbeiro.setText("Barbeiro");
        pnlInfo.add(lblBarbeiro);

        valBarbeiro.setFont(new java.awt.Font("SansSerif", 0, 13));
        valBarbeiro.setForeground(new java.awt.Color(17, 24, 39));
        valBarbeiro.setText("-");
        pnlInfo.add(valBarbeiro);

        lblData.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblData.setForeground(new java.awt.Color(17, 24, 39));
        lblData.setText("Data");
        pnlInfo.add(lblData);

        valData.setFont(new java.awt.Font("SansSerif", 0, 13));
        valData.setForeground(new java.awt.Color(17, 24, 39));
        valData.setText("-");
        pnlInfo.add(valData);

        lblHorario.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblHorario.setForeground(new java.awt.Color(17, 24, 39));
        lblHorario.setText("Horário");
        pnlInfo.add(lblHorario);

        valHorario.setFont(new java.awt.Font("SansSerif", 0, 13));
        valHorario.setForeground(new java.awt.Color(17, 24, 39));
        valHorario.setText("-");
        pnlInfo.add(valHorario);

        lblServicos.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblServicos.setForeground(new java.awt.Color(17, 24, 39));
        lblServicos.setText("Serviços");
        pnlInfo.add(lblServicos);

        valServicos.setFont(new java.awt.Font("SansSerif", 0, 13));
        valServicos.setForeground(new java.awt.Color(17, 24, 39));
        valServicos.setText("-");
        pnlInfo.add(valServicos);

        lblTotal.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblTotal.setForeground(new java.awt.Color(17, 24, 39));
        lblTotal.setText("Total");
        pnlInfo.add(lblTotal);

        valTotal.setFont(new java.awt.Font("SansSerif", 0, 13));
        valTotal.setForeground(new java.awt.Color(17, 24, 39));
        valTotal.setText("-");
        pnlInfo.add(valTotal);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblStatus.setForeground(new java.awt.Color(17, 24, 39));
        lblStatus.setText("Status");
        pnlInfo.add(lblStatus);

        valStatus.setFont(new java.awt.Font("SansSerif", 1, 13));
        valStatus.setForeground(new java.awt.Color(17, 24, 39));
        valStatus.setText("-");
        pnlInfo.add(valStatus);

        lblPagamento.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblPagamento.setForeground(new java.awt.Color(17, 24, 39));
        lblPagamento.setText("Pagamento");
        pnlInfo.add(lblPagamento);

        valPagamento.setFont(new java.awt.Font("SansSerif", 0, 13));
        valPagamento.setForeground(new java.awt.Color(17, 24, 39));
        valPagamento.setText("-");
        pnlInfo.add(valPagamento);

        lblObservacao.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblObservacao.setForeground(new java.awt.Color(17, 24, 39));
        lblObservacao.setText("Observação");
        pnlInfo.add(lblObservacao);

        valObservacao.setFont(new java.awt.Font("SansSerif", 0, 13));
        valObservacao.setForeground(new java.awt.Color(17, 24, 39));
        valObservacao.setText("-");
        pnlInfo.add(valObservacao);

        pnlFundo.add(pnlInfo, java.awt.BorderLayout.CENTER);

        pnlAcoes.setOpaque(false);
        pnlAcoes.setLayout(new java.awt.GridLayout(2, 3, 8, 8));

        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBackground(new java.awt.Color(22, 163, 74));
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
        pnlAcoes.add(btnConfirmar);

        btnConcluir.setText("Concluir");
        btnConcluir.setContorno(true);
        btnConcluir.setForeground(new java.awt.Color(22, 163, 74));
        btnConcluir.addActionListener(this::btnConcluirActionPerformed);
        pnlAcoes.add(btnConcluir);

        btnPagar.setText("Registrar pagamento");
        btnPagar.addActionListener(this::btnPagarActionPerformed);
        pnlAcoes.add(btnPagar);

        btnCancelarAgendamento.setText("Cancelar agendamento");
        btnCancelarAgendamento.setContorno(true);
        btnCancelarAgendamento.setForeground(new java.awt.Color(220, 38, 38));
        btnCancelarAgendamento.addActionListener(this::btnCancelarAgendamentoActionPerformed);
        pnlAcoes.add(btnCancelarAgendamento);

        btnExcluir.setText("Excluir");
        btnExcluir.setBackground(new java.awt.Color(220, 38, 38));
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        pnlAcoes.add(btnExcluir);

        btnFechar.setText("Fechar");
        btnFechar.setContorno(true);
        btnFechar.setForeground(new java.awt.Color(17, 24, 39));
        btnFechar.addActionListener(this::btnFecharActionPerformed);
        pnlAcoes.add(btnFechar);

        pnlFundo.add(pnlAcoes, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlFundo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        mudarStatus("confirmado", null);
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirActionPerformed
        mudarStatus("concluido", "Marcar este atendimento como concluído?");
    }//GEN-LAST:event_btnConcluirActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        pagamentoSolicitado = true;
        dispose();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnCancelarAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAgendamentoActionPerformed
        mudarStatus("cancelado", "Cancelar este agendamento?");
    }//GEN-LAST:event_btnCancelarAgendamentoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (!Estilo.confirmar(this, "Excluir definitivamente este agendamento?\nEssa ação não pode ser desfeita.")) return;
        try {
            dao.deletar(id);
            alterado = true;
            dispose();
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao excluir: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnCancelarAgendamento;
    private barbearia.view.Botao btnConcluir;
    private barbearia.view.Botao btnConfirmar;
    private barbearia.view.Botao btnExcluir;
    private barbearia.view.Botao btnFechar;
    private barbearia.view.Botao btnPagar;
    private javax.swing.JLabel lblBarbeiro;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblPagamento;
    private javax.swing.JLabel lblServicos;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlAcoes;
    private javax.swing.JPanel pnlFundo;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JLabel valBarbeiro;
    private javax.swing.JLabel valCliente;
    private javax.swing.JLabel valData;
    private javax.swing.JLabel valHorario;
    private javax.swing.JLabel valObservacao;
    private javax.swing.JLabel valPagamento;
    private javax.swing.JLabel valServicos;
    private javax.swing.JLabel valStatus;
    private javax.swing.JLabel valTotal;
    // End of variables declaration//GEN-END:variables
}
