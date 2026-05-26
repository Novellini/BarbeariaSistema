
package barbearia.view;


public class TelaAgendaDia extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAgendaDia.class.getName());

    
    public TelaAgendaDia() {
    initComponents();
    
    try {
        javax.swing.text.MaskFormatter mascaraData = new javax.swing.text.MaskFormatter("##/##/####");
        mascaraData.setPlaceholderCharacter('_');
        txtData.setFormatterFactory(
            new javax.swing.text.DefaultFormatterFactory(mascaraData));
    } catch (java.text.ParseException e) {}
    
    configurarTabela();
    txtData.setText(java.time.LocalDate.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        btnVerData = new javax.swing.JButton();
        scrollAgendamentos = new javax.swing.JScrollPane();
        tabelaAgendamentos = new javax.swing.JTable();
        txtData = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        btnConcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnPagamento = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Painel- Agendamentos do dia");

        lblData.setText("Data:");

        btnVerData.setText("Ver Agenda");
        btnVerData.addActionListener(this::btnVerDataActionPerformed);

        tabelaAgendamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollAgendamentos.setViewportView(tabelaAgendamentos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerData)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollAgendamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(btnVerData)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollAgendamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
        );

        btnConcluir.setText("Concluir");
        btnConcluir.addActionListener(this::btnConcluirActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnPagamento.setText("Pagamento");
        btnPagamento.addActionListener(this::btnPagamentoActionPerformed);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConcluir)
                .addGap(36, 36, 36)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagamento)
                .addGap(35, 35, 35)
                .addComponent(btnExcluir)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcluir)
                    .addComponent(btnCancelar)
                    .addComponent(btnPagamento)
                    .addComponent(btnExcluir))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirActionPerformed
       atualizarStatus("Concluido");
    }//GEN-LAST:event_btnConcluirActionPerformed

    private void btnVerDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDataActionPerformed
        buscar();
    }//GEN-LAST:event_btnVerDataActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        atualizarStatus("Cancelado");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagamentoActionPerformed
        registrarPagamento();
    }//GEN-LAST:event_btnPagamentoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaAgendaDia().setVisible(true));
    }
    private barbearia.dao.AgendamentoDAO dao = new barbearia.dao.AgendamentoDAO();
private javax.swing.table.DefaultTableModel modeloTabela;
private int idSelecionado = -1;
private String totalSelecionado = "0";

private void configurarTabela() {
    String[] colunas = {"ID", "Cliente", "Barbeiro", "Hora", "Serviços", "Total", "Status"};
    modeloTabela = new javax.swing.table.DefaultTableModel(colunas, 0) {
        public boolean isCellEditable(int row, int col) { return false; }
    };
    tabelaAgendamentos.setModel(modeloTabela);
    tabelaAgendamentos.getColumnModel().getColumn(0).setMaxWidth(40);
    tabelaAgendamentos.getColumnModel().getColumn(3).setMaxWidth(50);
    tabelaAgendamentos.getColumnModel().getColumn(5).setMaxWidth(80);
    tabelaAgendamentos.getColumnModel().getColumn(6).setMaxWidth(80);
    tabelaAgendamentos.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) selecionarDaTabela();
    });
}

private void buscar() {
    if (txtData.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Preencha a data!");
        return;
    }
    try {
        java.time.LocalDate data = java.time.LocalDate.parse(txtData.getText(),
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String dataFormatada = data.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        modeloTabela.setRowCount(0);
        java.util.List<String[]> lista = dao.buscarAgendamentosDodia(dataFormatada);
        if (lista.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nenhum agendamento para essa data!");
            return;
        }
        for (String[] ag : lista) {
            modeloTabela.addRow(new Object[]{
                ag[0], ag[1], ag[2], ag[3], ag[4], ag[5], ag[6]
            });
        }
    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
    }
}

private void selecionarDaTabela() {
    int linha = tabelaAgendamentos.getSelectedRow();
    if (linha == -1) return;
    idSelecionado = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
    totalSelecionado = modeloTabela.getValueAt(linha, 5).toString().replace("R$ ", "");
}

private void atualizarStatus(String novoStatus) {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um agendamento primeiro!");
        return;
    }
    String mensagem = novoStatus.equals("concluido")
        ? "Marcar como concluído?" : "Cancelar este agendamento?";
    int ok = javax.swing.JOptionPane.showConfirmDialog(this,
        mensagem, "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);
    if (ok == javax.swing.JOptionPane.YES_OPTION) {
        try {
            dao.atualizarStatus(idSelecionado, novoStatus);
            javax.swing.JOptionPane.showMessageDialog(this, "Status atualizado!");
            buscar();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}

private void registrarPagamento() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um agendamento primeiro!");
        return;
    }
    String[] formas = {"dinheiro", "pix", "cartao_debito", "cartao_credito"};
    String forma = (String) javax.swing.JOptionPane.showInputDialog(
        this, "Forma de pagamento:", "Registrar Pagamento",
        javax.swing.JOptionPane.QUESTION_MESSAGE, null, formas, formas[0]);
    if (forma == null) return;
    try {
        java.math.BigDecimal valor = new java.math.BigDecimal(totalSelecionado);
        dao.registrarPagamento(idSelecionado, valor, forma);
        dao.atualizarStatus(idSelecionado, "concluido");
        javax.swing.JOptionPane.showMessageDialog(this,
            "Pagamento de R$ " + totalSelecionado + " registrado!");
        buscar();
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
    }
}

private void excluir() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um agendamento primeiro!");
        return;
    }
    int ok = javax.swing.JOptionPane.showConfirmDialog(this,
        "Tem certeza que deseja excluir este agendamento?",
        "Confirmar Exclusão", javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.WARNING_MESSAGE);
    if (ok == javax.swing.JOptionPane.YES_OPTION) {
        try {
            dao.deletar(idSelecionado);
            javax.swing.JOptionPane.showMessageDialog(this, "Agendamento excluído!");
            idSelecionado = -1;
            buscar();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConcluir;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPagamento;
    private javax.swing.JButton btnVerData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollAgendamentos;
    private javax.swing.JTable tabelaAgendamentos;
    private javax.swing.JFormattedTextField txtData;
    // End of variables declaration//GEN-END:variables
}
