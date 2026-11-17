package barbearia.view;

import barbearia.dao.BarbeirDAO;
import barbearia.model.Barbeiro;

public class DialogBarbeiro extends javax.swing.JDialog {

    private final BarbeirDAO dao = new BarbeirDAO();
    private final Barbeiro item;
    private boolean alterado;

    // item == null: novo cadastro; senão, edição do item
    public DialogBarbeiro(java.awt.Frame parent, Barbeiro item) {
        super(parent, true);
        this.item = item;
        initComponents();
        if (item == null) {
            btnExcluir.setVisible(false);
        } else {
            setTitle("Editar barbeiro");
            lblTitulo.setText("Editar barbeiro");
            txtNome.setText(item.getNome());
            txtTelefone.setText(item.getTelefone());
            txtEspecialidade.setText(item.getEspecialidade() != null ? item.getEspecialidade() : "");
            chkAtivo.setSelected(item.isAtivo());
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
        String esp = txtEspecialidade.getText().trim();
        Barbeiro b = new Barbeiro(nome, txtTelefone.getText().trim(), esp.isEmpty() ? null : esp, chkAtivo.isSelected());
        try {
            if (item == null) {
                dao.inserir(b);
            } else {
                b.setIdBarbeiro(item.getId_barbeiro());
                dao.atualizar(b);
            }
            return true;
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao salvar: " + ex.getMessage());
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
        lblEspecialidade = new javax.swing.JLabel();
        txtEspecialidade = new barbearia.view.CampoTexto();
        chkAtivo = new javax.swing.JCheckBox();
        pnlBotoes = new javax.swing.JPanel();
        btnExcluir = new barbearia.view.Botao();
        pnlBotoesDireita = new javax.swing.JPanel();
        btnSalvar = new barbearia.view.Botao();
        btnCancelar = new barbearia.view.Botao();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo barbeiro");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlFundo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFundo.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));
        pnlFundo.setLayout(new java.awt.BorderLayout(0, 20));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblTitulo.setText("Novo barbeiro");
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

        lblEspecialidade.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblEspecialidade.setForeground(new java.awt.Color(17, 24, 39));
        lblEspecialidade.setText("Especialidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblEspecialidade, gridBagConstraints);

        txtEspecialidade.setPlaceholder("Ex.: Barba, degradê");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtEspecialidade, gridBagConstraints);

        chkAtivo.setFont(new java.awt.Font("SansSerif", 0, 13));
        chkAtivo.setOpaque(false);
        chkAtivo.setSelected(true);
        chkAtivo.setText("Barbeiro ativo (aparece no agendamento)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlCampos.add(chkAtivo, gridBagConstraints);

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
        if (!Estilo.confirmar(this, "Excluir este barbeiro?")) return;
        try {
            dao.deletar(item.getId_barbeiro());
            alterado = true;
            dispose();
        } catch (java.sql.SQLException ex) {
            Estilo.erroExclusao(this, "barbeiro", ex);
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
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JLabel lblEspecialidade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlBotoesDireita;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlFundo;
    private barbearia.view.CampoTexto txtEspecialidade;
    private barbearia.view.CampoTexto txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
