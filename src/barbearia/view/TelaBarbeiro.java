
package barbearia.view;


public class TelaBarbeiro extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaBarbeiro.class.getName());

    
    public TelaBarbeiro() {
    initComponents();
    
    try {
        javax.swing.text.MaskFormatter mascaraTel = new javax.swing.text.MaskFormatter("(##) #####-####");
        mascaraTel.setPlaceholderCharacter('_');
        txtTelefone.setFormatterFactory(
            new javax.swing.text.DefaultFormatterFactory(mascaraTel));
    } catch (java.text.ParseException e) {}
    
    configurarTabela();
    carregarTabela();
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblEspecialidade = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEspecialidade = new javax.swing.JTextField();
        chkAtivo = new javax.swing.JCheckBox();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblBuscaNome = new javax.swing.JLabel();
        txtBuscaNome = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaBarbeiros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNome.setText("Nome:*");

        lblTelefone.setText("Telefone:*");

        lblEspecialidade.setText("Especialidade:");

        chkAtivo.setText("Ativo");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(this::btnDeletarActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(lblTelefone)
                            .addComponent(lblEspecialidade))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtEspecialidade)
                            .addComponent(txtTelefone)))
                    .addComponent(chkAtivo))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnDeletar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpar)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspecialidade)
                    .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAtivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnDeletar)
                    .addComponent(btnLimpar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblBuscaNome.setText("Busca por nome:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tabelaBarbeiros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaBarbeiros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscaNome)
                        .addGap(28, 28, 28)
                        .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 24, Short.MAX_VALUE)
                        .addComponent(btnBuscar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscaNome)
                    .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        deletar();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

   
    public static void main(String args[]) {
        
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
       
        java.awt.EventQueue.invokeLater(() -> new TelaBarbeiro().setVisible(true));
    }
    private barbearia.dao.BarbeirDAO dao = new barbearia.dao.BarbeirDAO();
private javax.swing.table.DefaultTableModel modeloTabela;
private int idSelecionado = -1;

private void configurarTabela() {
    String[] colunas = {"ID", "Nome", "Telefone", "Especialidade", "Ativo"};
    modeloTabela = new javax.swing.table.DefaultTableModel(colunas, 0) {
        public boolean isCellEditable(int row, int col) { return false; }
    };
    tabelaBarbeiros.setModel(modeloTabela);
    tabelaBarbeiros.getColumnModel().getColumn(0).setMaxWidth(40);
    tabelaBarbeiros.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) selecionarDaTabela();
    });
}

private void carregarTabela() {
    modeloTabela.setRowCount(0);
    try {
        java.util.List<barbearia.model.Barbeiro> lista = dao.buscarTodos();
        for (barbearia.model.Barbeiro b : lista) {
            modeloTabela.addRow(new Object[]{
                b.getId_barbeiro(), b.getNome(), b.getTelefone(),
                b.getEspecialidade(), b.isAtivo() ? "Sim" : "Não"
            });
        }
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
    }
}

private void salvar() {
    if (!validar()) return;
    try {
        dao.inserir(new barbearia.model.Barbeiro(
            txtNome.getText().trim(),
            txtTelefone.getText().trim(),
            txtEspecialidade.getText().trim().isEmpty() ? null : txtEspecialidade.getText().trim(),
            chkAtivo.isSelected()
        ));
        javax.swing.JOptionPane.showMessageDialog(this, "Barbeiro salvo!");
        limpar();
        carregarTabela();
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
    }
}

private void editar() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um barbeiro primeiro!");
        return;
    }
    if (!validar()) return;
    try {
        barbearia.model.Barbeiro b = new barbearia.model.Barbeiro(
            txtNome.getText().trim(),
            txtTelefone.getText().trim(),
            txtEspecialidade.getText().trim().isEmpty() ? null : txtEspecialidade.getText().trim(),
            chkAtivo.isSelected()
        );
        b.setIdBarbeiro(idSelecionado);
        dao.atualizar(b);
        javax.swing.JOptionPane.showMessageDialog(this, "Barbeiro atualizado!");
        limpar();
        carregarTabela();
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
    }
}

private void deletar() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um barbeiro primeiro!");
        return;
    }
    int ok = javax.swing.JOptionPane.showConfirmDialog(this,
        "Deletar este barbeiro?", "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);
    if (ok == javax.swing.JOptionPane.YES_OPTION) {
        try {
            dao.deletar(idSelecionado);
            javax.swing.JOptionPane.showMessageDialog(this, "Barbeiro deletado!");
            limpar();
            carregarTabela();
        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao deletar: " + ex.getMessage());
        }
    }
}

private void buscar() {
    try {
        modeloTabela.setRowCount(0);
        java.util.List<barbearia.model.Barbeiro> lista = txtBuscaNome.getText().trim().isEmpty()
            ? dao.buscarTodos()
            : dao.buscarPorNome(txtBuscaNome.getText().trim());
        for (barbearia.model.Barbeiro b : lista) {
            modeloTabela.addRow(new Object[]{
                b.getId_barbeiro(), b.getNome(), b.getTelefone(),
                b.getEspecialidade(), b.isAtivo() ? "Sim" : "Não"
            });
        }
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
    }
}

private void selecionarDaTabela() {
    int linha = tabelaBarbeiros.getSelectedRow();
    if (linha == -1) return;
    idSelecionado = (int) modeloTabela.getValueAt(linha, 0);
    txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
    txtTelefone.setText((String) modeloTabela.getValueAt(linha, 2));
    txtEspecialidade.setText(modeloTabela.getValueAt(linha, 3) != null
        ? (String) modeloTabela.getValueAt(linha, 3) : "");
    chkAtivo.setSelected(modeloTabela.getValueAt(linha, 4).equals("Sim"));
}

private void limpar() {
    txtNome.setText("");
    txtTelefone.setText("");
    txtEspecialidade.setText("");
    txtBuscaNome.setText("");
    chkAtivo.setSelected(true);
    idSelecionado = -1;
    tabelaBarbeiros.clearSelection();
}

private boolean validar() {
    if (txtNome.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
        return false;
    }
    if (txtTelefone.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Telefone é obrigatório!");
        return false;
    }
    return true;
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscaNome;
    private javax.swing.JLabel lblEspecialidade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JTable tabelaBarbeiros;
    private javax.swing.JTextField txtBuscaNome;
    private javax.swing.JTextField txtEspecialidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
