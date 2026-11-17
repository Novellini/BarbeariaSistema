package barbearia.view;

import barbearia.dao.ServicoDAO;
import barbearia.model.Servico;
import java.math.BigDecimal;

public class DialogServico extends javax.swing.JDialog {

    private final ServicoDAO dao = new ServicoDAO();
    private final Servico item;
    private boolean alterado;

    // item == null: novo cadastro; senão, edição do item
    public DialogServico(java.awt.Frame parent, Servico item) {
        super(parent, true);
        this.item = item;
        initComponents();
        if (item == null) {
            btnExcluir.setVisible(false);
        } else {
            setTitle("Editar serviço");
            lblTitulo.setText("Editar serviço");
            txtNome.setText(item.getNome());
            txtDescricao.setText(item.getDescricao() != null ? item.getDescricao() : "");
            txtPreco.setText(item.getPreco().toPlainString().replace(".", ","));
            spnDuracao.setValue(item.getDuracaoMin());
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
        BigDecimal preco;
        try {
            preco = new BigDecimal(txtPreco.getText().trim().replace("R$", "").trim().replace(",", "."));
            if (preco.signum() < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            Estilo.erro(this, "Preço inválido! Ex.: 35,00");
            txtPreco.requestFocusInWindow();
            return false;
        }
        String desc = txtDescricao.getText().trim();
        Servico s = new Servico(nome, desc.isEmpty() ? null : desc, preco,
                (Integer) spnDuracao.getValue(), chkAtivo.isSelected());
        try {
            if (item == null) {
                dao.inserir(s);
            } else {
                s.setIdServico(item.getIdServico());
                dao.atualizar(s);
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
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new barbearia.view.CampoTexto();
        lblPreco = new javax.swing.JLabel();
        txtPreco = new barbearia.view.CampoTexto();
        lblDuracao = new javax.swing.JLabel();
        spnDuracao = new javax.swing.JSpinner();
        chkAtivo = new javax.swing.JCheckBox();
        pnlBotoes = new javax.swing.JPanel();
        btnExcluir = new barbearia.view.Botao();
        pnlBotoesDireita = new javax.swing.JPanel();
        btnSalvar = new barbearia.view.Botao();
        btnCancelar = new barbearia.view.Botao();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo serviço");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlFundo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFundo.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));
        pnlFundo.setLayout(new java.awt.BorderLayout(0, 20));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblTitulo.setText("Novo serviço");
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

        txtNome.setPlaceholder("Ex.: Corte masculino");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtNome, gridBagConstraints);

        lblDescricao.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblDescricao.setForeground(new java.awt.Color(17, 24, 39));
        lblDescricao.setText("Descrição");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblDescricao, gridBagConstraints);

        txtDescricao.setPlaceholder("Opcional");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtDescricao, gridBagConstraints);

        lblPreco.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblPreco.setForeground(new java.awt.Color(17, 24, 39));
        lblPreco.setText("Preço (R$) *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblPreco, gridBagConstraints);

        txtPreco.setPlaceholder("Ex.: 35,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(txtPreco, gridBagConstraints);

        lblDuracao.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblDuracao.setForeground(new java.awt.Color(17, 24, 39));
        lblDuracao.setText("Duração (minutos) *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCampos.add(lblDuracao, gridBagConstraints);

        spnDuracao.setFont(new java.awt.Font("SansSerif", 0, 13));
        spnDuracao.setModel(new javax.swing.SpinnerNumberModel(30, 5, 480, 5));
        spnDuracao.setPreferredSize(new java.awt.Dimension(260, 36));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlCampos.add(spnDuracao, gridBagConstraints);

        chkAtivo.setFont(new java.awt.Font("SansSerif", 0, 13));
        chkAtivo.setOpaque(false);
        chkAtivo.setSelected(true);
        chkAtivo.setText("Serviço ativo (aparece no agendamento)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
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
        if (!Estilo.confirmar(this, "Excluir este serviço?")) return;
        try {
            dao.deletar(item.getIdServico());
            alterado = true;
            dispose();
        } catch (java.sql.SQLException ex) {
            Estilo.erroExclusao(this, "serviço", ex);
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
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDuracao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlBotoesDireita;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlFundo;
    private javax.swing.JSpinner spnDuracao;
    private barbearia.view.CampoTexto txtDescricao;
    private barbearia.view.CampoTexto txtNome;
    private barbearia.view.CampoTexto txtPreco;
    // End of variables declaration//GEN-END:variables
}
