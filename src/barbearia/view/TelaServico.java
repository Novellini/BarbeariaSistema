
package barbearia.view;


public class TelaServico extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaServico.class.getName());

    
    public TelaServico() {
        initComponents();
        configurarTabela();
        carregarTabela();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        lblDuracao = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        txtDuracao = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        chkAtivo = new javax.swing.JCheckBox();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        lblBuscaNome = new javax.swing.JLabel();
        txtBuscaNome = new javax.swing.JTextField();
        btnBuscaNome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaServico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNome.setText("Nome:*");

        lblPreco.setText("Preço (R$):*");

        lblDuracao.setText("Duração (min):*");

        lblDescricao.setText("Descrição:");

        chkAtivo.setText("Ativo");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(this::btnDeletarActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome)
                                    .addComponent(lblPreco)
                                    .addComponent(lblDuracao)
                                    .addComponent(lblDescricao))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                    .addComponent(txtDuracao)
                                    .addComponent(txtPreco)
                                    .addComponent(txtNome)))
                            .addComponent(chkAtivo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracao)
                    .addComponent(txtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAtivo)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnDeletar)
                    .addComponent(btnLimpar)
                    .addComponent(btnSalvar)))
        );

        lblBuscaNome.setText("Buscar por nome:");

        txtBuscaNome.addActionListener(this::txtBuscaNomeActionPerformed);

        btnBuscaNome.setText("Buscar");
        btnBuscaNome.addActionListener(this::btnBuscaNomeActionPerformed);

        tabelaServico.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaServico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscaNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaNome)
                .addGap(18, 18, 18)
                .addComponent(btnBuscaNome)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscaNome)
                    .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscaNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaNomeActionPerformed
        
    }//GEN-LAST:event_txtBuscaNomeActionPerformed

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

    private void btnBuscaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaNomeActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscaNomeActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new TelaServico().setVisible(true));
    }
    private barbearia.dao.ServicoDAO dao = new barbearia.dao.ServicoDAO();
private javax.swing.table.DefaultTableModel modeloTabela;
private int idSelecionado = -1;

private void configurarTabela() {
    String[] colunas = {"ID", "Nome", "Descrição", "Preço", "Duração", "Ativo"};
    modeloTabela = new javax.swing.table.DefaultTableModel(colunas, 0) {
        public boolean isCellEditable(int row, int col) { return false; }
    };
    tabelaServico.setModel(modeloTabela);
    tabelaServico.getColumnModel().getColumn(0).setMaxWidth(40);
    tabelaServico.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) selecionarDaTabela();
    });
}

private void carregarTabela() {
    modeloTabela.setRowCount(0);
    try {
        java.util.List<barbearia.model.Servico> lista = dao.buscarTodos();
        for (barbearia.model.Servico s : lista) {
            modeloTabela.addRow(new Object[]{
                s.getIdServico(), s.getNome(), s.getDescricao(),
                "R$ " + s.getPreco(), s.getDuracaoMin() + " min",
                s.isAtivo() ? "Sim" : "Não"
            });
        }
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
    }
}

private void salvar() {
    System.out.println("Nome: " + txtNome.getText());
    System.out.println("Preco: " + txtPreco.getText());
    System.out.println("Duracao: " + txtDuracao.getText());
    if (!validar()) return;
    try {
        dao.inserir(new barbearia.model.Servico(
            txtNome.getText().trim(),
            txtDescricao.getText().trim().isEmpty() ? null : txtDescricao.getText().trim(),
            new java.math.BigDecimal(txtPreco.getText().trim().replace(",", ".")),
            Integer.parseInt(txtDuracao.getText().trim()),
            chkAtivo.isSelected()
        ));
        javax.swing.JOptionPane.showMessageDialog(this, "Serviço salvo!");
        limpar();
        carregarTabela();
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
    }
}

private void editar() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um serviço primeiro!");
        return;
    }
    if (!validar()) return;
    try {
        barbearia.model.Servico s = new barbearia.model.Servico(
            txtNome.getText().trim(),
            txtDescricao.getText().trim().isEmpty() ? null : txtDescricao.getText().trim(),
            new java.math.BigDecimal(txtPreco.getText().trim().replace(",", ".")),
            Integer.parseInt(txtDuracao.getText().trim()),
            chkAtivo.isSelected()
        );
        s.setIdServico(idSelecionado);
        dao.atualizar(s);
        javax.swing.JOptionPane.showMessageDialog(this, "Serviço atualizado!");
        limpar();
        carregarTabela();
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
    }
}

private void deletar() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um serviço primeiro!");
        return;
    }
    int ok = javax.swing.JOptionPane.showConfirmDialog(this,
        "Deletar este serviço?", "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);
    if (ok == javax.swing.JOptionPane.YES_OPTION) {
        try {
            dao.deletar(idSelecionado);
            javax.swing.JOptionPane.showMessageDialog(this, "Serviço deletado!");
            limpar();
            carregarTabela();
        } catch (java.sql.SQLException ex) {
            if (ex.getMessage().contains("foreign key constraint")) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Não é possível deletar pois está vinculado a um agendamento!");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao deletar: " + ex.getMessage());
            }
        }
    }
}
        
private void buscar() {
    try {
        modeloTabela.setRowCount(0);
        java.util.List<barbearia.model.Servico> lista = txtBuscaNome.getText().trim().isEmpty()
            ? dao.buscarTodos()
            : dao.buscarPorNome(txtBuscaNome.getText().trim());
        for (barbearia.model.Servico s : lista) {
            modeloTabela.addRow(new Object[]{
                s.getIdServico(), s.getNome(), s.getDescricao(),
                "R$ " + s.getPreco(), s.getDuracaoMin() + " min",
                s.isAtivo() ? "Sim" : "Não"
            });
        }
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
    }
}

private void selecionarDaTabela() {
    int linha = tabelaServico.getSelectedRow();
    if (linha == -1) return;
    idSelecionado = (int) modeloTabela.getValueAt(linha, 0);
    txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
    txtDescricao.setText(modeloTabela.getValueAt(linha, 2) != null
        ? (String) modeloTabela.getValueAt(linha, 2) : "");
    txtPreco.setText(modeloTabela.getValueAt(linha, 3).toString().replace("R$ ", ""));
    txtDuracao.setText(modeloTabela.getValueAt(linha, 4).toString().replace(" min", ""));
    chkAtivo.setSelected(modeloTabela.getValueAt(linha, 5).equals("Sim"));
}

private void limpar() {
    txtNome.setText("");
    txtDescricao.setText("");
    txtPreco.setText("");
    txtDuracao.setText("");
    txtBuscaNome.setText("");
    chkAtivo.setSelected(true);
    idSelecionado = -1;
    tabelaServico.clearSelection();
}

private boolean validar() {
    if (txtNome.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
        return false;
    }
    if (txtPreco.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Preço é obrigatório!");
        return false;
    }
    if (txtDuracao.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Duração é obrigatória!");
        return false;
    }
    try {
        new java.math.BigDecimal(txtPreco.getText().trim().replace(",", "."));
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Preço inválido! Ex: 35,00");
        return false;
    }
    try {
        Integer.parseInt(txtDuracao.getText().trim());
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Duração inválida! Ex: 30");
        return false;
    }
    return true;
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscaNome;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscaNome;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDuracao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JTable tabelaServico;
    private javax.swing.JTextField txtBuscaNome;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDuracao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
