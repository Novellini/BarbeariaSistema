package barbearia.view;


public class TelaAgendamento extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAgendamento.class.getName());

    
    public TelaAgendamento() {
        initComponents();
        painelServicos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        carregarBarbeiros();  
        carregarServicos();
        carregarClientes();
    try {
    javax.swing.text.MaskFormatter mascaraData = new javax.swing.text.MaskFormatter("##/##/####");
    mascaraData.setPlaceholderCharacter('_');
    txtData.setFormatterFactory(
        new javax.swing.text.DefaultFormatterFactory(mascaraData));
} catch (java.text.ParseException e) {}

try {
    javax.swing.text.MaskFormatter mascaraHora = new javax.swing.text.MaskFormatter("##:##");
    mascaraHora.setPlaceholderCharacter('_');
    txtHora.setFormatterFactory(
        new javax.swing.text.DefaultFormatterFactory(mascaraHora));
} catch (java.text.ParseException e) {}
   
listaClientes.addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int idx = listaClientes.getSelectedIndex();
        if (idx != -1 && idx < resultadoClientes.size()) {
            idClienteSelecionado = resultadoClientes.get(idx).getIdCliente();
        }
    }
});
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblBuscarCliente = new javax.swing.JLabel();
        txtBuscaCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lblPainelCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        lblPainelDados = new javax.swing.JLabel();
        lblBarbeiros = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblObservação = new javax.swing.JLabel();
        cboBarbeiro = new javax.swing.JComboBox<>();
        txtObservacao = new javax.swing.JTextField();
        txtData = new javax.swing.JFormattedTextField();
        txtHora = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        painelServicos = new javax.swing.JPanel();
        lblPainelServicos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblBuscarCliente.setText("Buscar cliente:*");

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(this::btnBuscarClienteActionPerformed);

        lblPainelCliente.setText("Painel - Cliente");

        listaClientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPainelCliente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBuscarCliente)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPainelCliente)
                .addGap(25, 25, 25)
                .addComponent(lblBuscarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPainelDados.setText("Painel - Dados");

        lblBarbeiros.setText("Barbeiro:");

        lblData.setText("Data:");

        lblHora.setText("Hora:");

        lblObservação.setText("Observação:");

        cboBarbeiro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPainelDados)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBarbeiros)
                            .addComponent(lblData)
                            .addComponent(lblHora)
                            .addComponent(lblObservação))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboBarbeiro, 0, 185, Short.MAX_VALUE)
                            .addComponent(txtObservacao)
                            .addComponent(txtHora)
                            .addComponent(txtData))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPainelDados)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBarbeiros)
                    .addComponent(cboBarbeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHora)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObservação)
                    .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        lblTotal.setText("Total:");

        javax.swing.GroupLayout painelServicosLayout = new javax.swing.GroupLayout(painelServicos);
        painelServicos.setLayout(painelServicosLayout);
        painelServicosLayout.setHorizontalGroup(
            painelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        painelServicosLayout.setVerticalGroup(
            painelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(painelServicos);

        lblPainelServicos.setText("Painel - Serviços");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPainelServicos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPainelServicos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpar)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnLimpar)
                    .addComponent(lblTotal))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        buscarCliente();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaAgendamento().setVisible(true));
    }
    private barbearia.dao.AgendamentoDAO agendamentoDAO = new barbearia.dao.AgendamentoDAO();
private barbearia.dao.ClienteDAO clienteDAO = new barbearia.dao.ClienteDAO();
private barbearia.dao.BarbeirDAO barbeiroDAO = new barbearia.dao.BarbeirDAO();
private barbearia.dao.ServicoDAO servicoDAO = new barbearia.dao.ServicoDAO();
private java.util.List<barbearia.model.Cliente> resultadoClientes = new java.util.ArrayList<>();
private java.util.List<barbearia.model.Barbeiro> listaBarbeiros = new java.util.ArrayList<>();
private java.util.List<barbearia.model.Servico> listaServicos = new java.util.ArrayList<>();
private java.util.List<javax.swing.JCheckBox> checkboxServicos = new java.util.ArrayList<>();
private int idClienteSelecionado = -1;

private void carregarClientes() {
    try {
        resultadoClientes = clienteDAO.buscarTodos();
        javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();
        for (barbearia.model.Cliente c : resultadoClientes) {
            modelo.addElement(c.getNome() + " — " + c.getTelefone());
        }
        listaClientes.setModel(modelo);
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar clientes: " + ex.getMessage());
    }
}


private void carregarBarbeiros() {
    try {
        listaBarbeiros = barbeiroDAO.buscarTodos();
        cboBarbeiro.removeAllItems();
        for (barbearia.model.Barbeiro b : listaBarbeiros) {
            if (b.isAtivo()) cboBarbeiro.addItem(b.getNome());
        }
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar barbeiros: " + ex.getMessage());
    }
}

private void carregarServicos() {
    try {
        listaServicos = servicoDAO.buscarTodos();
        painelServicos.removeAll();
        checkboxServicos.clear();
        for (barbearia.model.Servico s : listaServicos) {
            if (s.isAtivo()) {
                javax.swing.JCheckBox chk = new javax.swing.JCheckBox(s.getNome() + " — R$ " + s.getPreco());
                chk.addActionListener(e -> calcularTotal());
                checkboxServicos.add(chk);
                painelServicos.add(chk);
            }
        }
        painelServicos.revalidate();
        painelServicos.repaint();
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar serviços: " + ex.getMessage());
    }
}

private void buscarCliente() {
    String nome = txtBuscaCliente.getText().trim();
    if (nome.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Digite um nome para buscar!");
        return;
    }
    try {
        resultadoClientes = clienteDAO.buscarPorNome(nome);
        javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();
        for (barbearia.model.Cliente c : resultadoClientes) {
            modelo.addElement(c.getNome() + " — " + c.getTelefone());
        }
        listaClientes.setModel(modelo);
        if (resultadoClientes.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado!");
        }
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
    }
}

private void calcularTotal() {
    java.math.BigDecimal total = java.math.BigDecimal.ZERO;
    for (int i = 0; i < checkboxServicos.size(); i++) {
        if (checkboxServicos.get(i).isSelected()) {
            total = total.add(listaServicos.get(i).getPreco());
        }
    }
    lblTotal.setText("Total: R$ " + total);
}

private void salvar() {
    if (idClienteSelecionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente!");
        return;
    }
    if (cboBarbeiro.getSelectedIndex() == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um barbeiro!");
        return;
    }
    if (txtData.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Preencha a data!");
        return;
    }
    if (txtHora.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Preencha o horário!");
        return;
    }
    boolean algumSelecionado = false;
    for (javax.swing.JCheckBox chk : checkboxServicos) {
        if (chk.isSelected()) { algumSelecionado = true; break; }
    }
    if (!algumSelecionado) {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecione pelo menos um serviço!");
        return;
    }
    try {
        String dataHoraStr = txtData.getText() + " " + txtHora.getText();
        java.time.LocalDateTime dataHora = java.time.LocalDateTime.parse(dataHoraStr,
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        int idxBarbeiro = cboBarbeiro.getSelectedIndex();
        int idBarbeiro = listaBarbeiros.get(idxBarbeiro).getId_barbeiro();
        barbearia.model.Agendamento ag = new barbearia.model.Agendamento(
            idClienteSelecionado, idBarbeiro, dataHora, "agendado",
            txtObservacao.getText().trim().isEmpty() ? null : txtObservacao.getText().trim()
        );
        int idAgendamento = agendamentoDAO.inserir(ag);
        for (int i = 0; i < checkboxServicos.size(); i++) {
            if (checkboxServicos.get(i).isSelected()) {
                agendamentoDAO.inserirServicos(idAgendamento,
                    listaServicos.get(i).getIdServico(),
                    listaServicos.get(i).getPreco());
            }
        }
        javax.swing.JOptionPane.showMessageDialog(this, "Agendamento salvo!");
        limpar();
    } catch (java.time.format.DateTimeParseException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Data ou hora inválida!");
    } catch (java.sql.SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
    }
}

private void limpar() {
    txtBuscaCliente.setText("");
    listaClientes.setModel(new javax.swing.DefaultListModel<>());

    txtData.setText("");
    txtHora.setText("");
    txtObservacao.setText("");
    idClienteSelecionado = -1;
    for (javax.swing.JCheckBox chk : checkboxServicos) chk.setSelected(false);
    lblTotal.setText("Total: R$ 0,00");
}
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cboBarbeiro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBarbeiros;
    private javax.swing.JLabel lblBuscarCliente;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblObservação;
    private javax.swing.JLabel lblPainelCliente;
    private javax.swing.JLabel lblPainelDados;
    private javax.swing.JLabel lblPainelServicos;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JList<String> listaClientes;
    private javax.swing.JPanel painelServicos;
    private javax.swing.JTextField txtBuscaCliente;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtObservacao;
    // End of variables declaration//GEN-END:variables
}
