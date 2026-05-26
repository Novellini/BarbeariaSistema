package barbearia.view;

import barbearia.dao.ClienteDAO;
import barbearia.model.Cliente;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TelaCliente extends javax.swing.JFrame {
private ClienteDAO dao = new ClienteDAO();
private DefaultTableModel modeloTabela;
private int idSelecionado = -1;
    
    
    public TelaCliente() {
    initComponents();
    
    try {
        javax.swing.text.MaskFormatter mascaraTel = new javax.swing.text.MaskFormatter("(##) #####-####");
        mascaraTel.setPlaceholderCharacter('_');
        txtTelefone.setFormatterFactory(
            new javax.swing.text.DefaultFormatterFactory(mascaraTel));
    } catch (java.text.ParseException e) {}
    
    try {
        javax.swing.text.MaskFormatter mascaraData = new javax.swing.text.MaskFormatter("##/##/####");
        mascaraData.setPlaceholderCharacter('_');
        txtDataNascimento.setFormatterFactory(
            new javax.swing.text.DefaultFormatterFactory(mascaraData));
    } catch (java.text.ParseException e) {}
    
    configurarTabela();
    carregarTabela();
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelClientes = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        lblBuscaNome = new javax.swing.JLabel();
        txtBuscaNome = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNome.setText("Nome:*");

        lblTelefone.setText("Telefone:*");

        lblEmail.setText("Email:");

        lblDataNascimento.setText("Data Nascimento:*");

        txtNome.addActionListener(this::txtNomeActionPerformed);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(this::btnDeletarActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        txtTelefone.addActionListener(this::txtTelefoneActionPerformed);

        javax.swing.GroupLayout painelClientesLayout = new javax.swing.GroupLayout(painelClientes);
        painelClientes.setLayout(painelClientesLayout);
        painelClientesLayout.setHorizontalGroup(
            painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelClientesLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnDeletar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpar)
                .addGap(55, 55, 55))
            .addGroup(painelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome)
                    .addComponent(lblTelefone)
                    .addComponent(lblEmail)
                    .addComponent(lblDataNascimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtTelefone)
                    .addComponent(txtDataNascimento))
                .addContainerGap())
        );
        painelClientesLayout.setVerticalGroup(
            painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClientesLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataNascimento)
                    .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnDeletar)
                    .addComponent(btnLimpar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblBuscaNome.setText("Busca por nome:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscaNome)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscaNome)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscaNome)
                    .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

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

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaCliente().setVisible(true));
    }
    private void configurarTabela() {
    String[] colunas = {"ID", "Nome", "Telefone", "Email", "Nascimento"};
    modeloTabela = new DefaultTableModel(colunas, 0) {
        public boolean isCellEditable(int row, int col) { return false; }
    };
    tabelaClientes.setModel(modeloTabela);
    tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(40);
    tabelaClientes.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) selecionarDaTabela();
    });
}

private void carregarTabela() {
    modeloTabela.setRowCount(0);
    try {
        List<Cliente> lista = dao.buscarTodos();
        for (Cliente c : lista) {
            modeloTabela.addRow(new Object[]{
                c.getIdCliente(), c.getNome(), c.getTelefone(), c.getEmail(),
                c.getDataNascimento() != null
                    ? c.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""
            });
        }
    } catch (SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
    }
}

private void salvar() {
    if (!validar()) return;
    String textoData = txtDataNascimento.getText().trim();
    String apenasDigitos = textoData.replace("/", "").replace(" ", "").trim();
    LocalDate data = null;
    if (!apenasDigitos.isEmpty()) {
        data = parseData(textoData);
        if (data == null) return;
    }
    try {
        dao.inserir(new Cliente(
            txtNome.getText().trim(),
            txtTelefone.getText().trim(),
            txtEmail.getText().trim().isEmpty() ? null : txtEmail.getText().trim(),
            data
        ));
        javax.swing.JOptionPane.showMessageDialog(this, "Cliente salvo!");
        limpar();
        carregarTabela();
    } catch (SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
    }
}

private void editar() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente primeiro!");
        return;
    }
    if (!validar()) return;
    String textoData = txtDataNascimento.getText().trim();
    String apenasDigitos = textoData.replace("/", "").replace(" ", "").trim();
    LocalDate data = null;
    if (!apenasDigitos.isEmpty()) {
        data = parseData(textoData);
        if (data == null) return;
    }
    try {
        Cliente c = new Cliente(
            txtNome.getText().trim(),
            txtTelefone.getText().trim(),
            txtEmail.getText().trim().isEmpty() ? null : txtEmail.getText().trim(),
            data
        );
        c.setIdCliente(idSelecionado);
        dao.atualizar(c);
        javax.swing.JOptionPane.showMessageDialog(this, "Cliente atualizado!");
        limpar();
        carregarTabela();
    } catch (SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
    }
}

private void deletar() {
    if (idSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente primeiro!");
        return;
    }
    int ok = javax.swing.JOptionPane.showConfirmDialog(this,
        "Deletar este cliente?", "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);
    if (ok == javax.swing.JOptionPane.YES_OPTION) {
        try {
            dao.deletar(idSelecionado);
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente deletado!");
            limpar();
            carregarTabela();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao deletar: " + ex.getMessage());
        }
    }
}

private void buscar() {
    try {
        modeloTabela.setRowCount(0);
        List<Cliente> lista = txtBuscaNome.getText().trim().isEmpty()
            ? dao.buscarTodos()
            : dao.buscarPorNome(txtBuscaNome.getText().trim());
        for (Cliente c : lista) {
            modeloTabela.addRow(new Object[]{
                c.getIdCliente(), c.getNome(), c.getTelefone(), c.getEmail(),
                c.getDataNascimento() != null
                    ? c.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""
            });
        }
    } catch (SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
    }
}

private void selecionarDaTabela() {
    int linha = tabelaClientes.getSelectedRow();
    if (linha == -1) return;
    idSelecionado = (int) modeloTabela.getValueAt(linha, 0);
    txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
    txtTelefone.setText((String) modeloTabela.getValueAt(linha, 2));
    txtEmail.setText(modeloTabela.getValueAt(linha, 3) != null
        ? (String) modeloTabela.getValueAt(linha, 3) : "");
    txtDataNascimento.setText((String) modeloTabela.getValueAt(linha, 4));
}

private void limpar() {
    txtNome.setText("");
    txtTelefone.setText("");
    txtEmail.setText("");
    txtDataNascimento.setText("");
    txtBuscaNome.setText("");
    idSelecionado = -1;
    tabelaClientes.clearSelection();
}

private boolean validar() {
    if (txtNome.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
        return false;
    }
    String tel = txtTelefone.getText()
        .replace("(","").replace(")","")
        .replace("-","").replace(" ","")
        .replace("_","").trim();
    if (tel.isEmpty() || tel.length() < 11) {
        javax.swing.JOptionPane.showMessageDialog(this, "Telefone inválido!");
        return false;
    }
    return true;
}

private LocalDate parseData(String texto) {
    if (texto == null) return null;
    String apenasDigitos = texto.replace("/", "").replace(" ", "").trim();
    if (apenasDigitos.isEmpty()) return null;
    if (apenasDigitos.length() < 8) {
        javax.swing.JOptionPane.showMessageDialog(this, "Data incompleta!");
        return null;
    }
    try {
        LocalDate data = LocalDate.parse(texto.trim(),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (data.getYear() < 1900 || data.getYear() > LocalDate.now().getYear()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ano inválido!");
            return null;
        }
        return data;
    } catch (DateTimeParseException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Data inválida! Use DD/MM/AAAA");
        return null;
    }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscaNome;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JPanel painelClientes;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtBuscaNome;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
