package barbearia.view;

import barbearia.dao.ClienteDAO;
import barbearia.model.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DialogCliente extends javax.swing.JDialog {

    private final ClienteDAO dao = new ClienteDAO();
    private final Cliente item;
    private boolean alterado;

    // item == null: novo cadastro; senão, edição do item
    public DialogCliente(java.awt.Frame parent, Cliente item) {
        super(parent, true);
        this.item = item;
        initComponents();
        if (item == null) {
            btnExcluir.setVisible(false);
        } else {
            setTitle("Editar cliente");
            lblTitulo.setText("Editar cliente");
            txtNome.setText(item.getNome());
            txtTelefone.setText(item.getTelefone());
            txtEmail.setText(item.getEmail() != null ? item.getEmail() : "");
            if (item.getDataNascimento() != null) txtNascimento.setText(item.getDataNascimento().format(Estilo.DATA));
        }
        Estilo.prepararDialogo(this, btnSalvar, 440);
    }

    public boolean isAlterado() {
        return alterado;
    }

    private boolean salvar() {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            Estilo.erro(this, "Nome é obrigatório!");
            txtNome.requestFocusInWindow();
            return false;
        }
        if (Estilo.apenasDigitos(txtTelefone.getText()).length() < 11) {
            Estilo.erro(this, "Telefone inválido! Informe DDD + número.");
            txtTelefone.requestFocusInWindow();
            return false;
        }
        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            Estilo.erro(this, "Email inválido!");
            txtEmail.requestFocusInWindow();
            return false;
        }
        LocalDate nascimento = null;
        if (!Estilo.apenasDigitos(txtNascimento.getText()).isEmpty()) {
            try {
                nascimento = LocalDate.parse(txtNascimento.getText(), Estilo.DATA);
            } catch (DateTimeParseException ex) {
                Estilo.erro(this, "Data de nascimento inválida! Use dd/mm/aaaa.");
                txtNascimento.requestFocusInWindow();
                return false;
            }
            if (nascimento.isAfter(LocalDate.now()) || nascimento.getYear() < 1900) {
                Estilo.erro(this, "Data de nascimento fora do intervalo permitido.");
                txtNascimento.requestFocusInWindow();
                return false;
            }
        }
        Cliente c = new Cliente(nome, txtTelefone.getText().trim(), email.isEmpty() ? null : email, nascimento);
        try {
            if (item == null) {
                dao.inserir(c);
            } else {
                c.setIdCliente(item.getIdCliente());
                dao.atualizar(c);
            }
            return true;
        } catch (java.sql.SQLException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("uq_cliente_telefone")) {
                Estilo.erro(this, "Já existe um cliente com esse telefone.");
            } else {
                Estilo.erro(this, "Erro ao salvar: " + ex.getMessage());
            }
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;
        pnlFundo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new barbearia.view.CampoTexto();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = Estilo.campoTelefone();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new barbearia.view.CampoTexto();
        lblNascimento = new javax.swing.JLabel();
        txtNascimento = Estilo.campoData();
        pnlBotoes = new javax.swing.JPanel();
        btnExcluir = new barbearia.view.Botao();
        pnlBotoesDireita = new javax.swing.JPanel();
        btnSalvar = new barbearia.view.Botao();
        btnCancelar = new barbearia.view.Botao();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo cliente");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlFundo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFundo.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));
        pnlFundo.setLayout(new java.awt.BorderLayout(0, 20));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblTitulo.setText("Novo cliente");
        pnlFundo.add(lblTitulo, java.awt.BorderLayout.NORTH);

        pnlCampos.setOpaque(false);
        pnlCampos.setLayout(new java.awt.GridBagLayout());

        lblNome.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblNome.setForeground(new java.awt.Color(17, 24, 39));
        lblNome.setText("Nome *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlCampos.add(lblNome, gridBagConstraints);

        txtNome.setPlaceholder("Nome completo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtNome, gridBagConstraints);

        lblTelefone.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblTelefone.setForeground(new java.awt.Color(17, 24, 39));
        lblTelefone.setText("Telefone *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblTelefone, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtTelefone, gridBagConstraints);

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblEmail.setForeground(new java.awt.Color(17, 24, 39));
        lblEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblEmail, gridBagConstraints);

        txtEmail.setPlaceholder("exemplo@email.com");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtEmail, gridBagConstraints);

        lblNascimento.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblNascimento.setForeground(new java.awt.Color(17, 24, 39));
        lblNascimento.setText("Data de nascimento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblNascimento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCampos.add(txtNascimento, gridBagConstraints);

        pnlFundo.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlBotoes.setOpaque(false);
        pnlBotoes.setLayout(new java.awt.BorderLayout());

        btnExcluir.setText("EXCLUIR");
        btnExcluir.setContorno(true);
        btnExcluir.setForeground(new java.awt.Color(220, 38, 38));
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        pnlBotoes.add(btnExcluir, java.awt.BorderLayout.WEST);

        pnlBotoesDireita.setOpaque(false);
        pnlBotoesDireita.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        pnlBotoesDireita.add(btnSalvar);

        btnCancelar.setText("CANCELAR");
        btnCancelar.setContorno(true);
        btnCancelar.setForeground(new java.awt.Color(17, 24, 39));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        pnlBotoesDireita.add(btnCancelar);

        pnlBotoes.add(pnlBotoesDireita, java.awt.BorderLayout.EAST);

        pnlFundo.add(pnlBotoes, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlFundo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (!Estilo.confirmar(this, "Excluir este cliente?")) return;
        try {
            dao.deletar(item.getIdCliente());
            alterado = true;
            dispose();
        } catch (java.sql.SQLException ex) {
            Estilo.erroExclusao(this, "cliente", ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (salvar()) {
            alterado = true;
            dispose();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnCancelar;
    private barbearia.view.Botao btnExcluir;
    private barbearia.view.Botao btnSalvar;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNascimento;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlBotoesDireita;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlFundo;
    private barbearia.view.CampoTexto txtEmail;
    private javax.swing.JFormattedTextField txtNascimento;
    private barbearia.view.CampoTexto txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
