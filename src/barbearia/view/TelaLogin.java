package barbearia.view;

public class TelaLogin extends javax.swing.JFrame {

    // Credenciais do responsável pelo sistema
    private static final String USUARIO = "admin";
    private static final String SENHA   = "admin";

    public TelaLogin() {
        initComponents();
        getRootPane().setDefaultButton(btnEntrar);
        setSize(560, 520);
        setMinimumSize(new java.awt.Dimension(480, 480));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;
        pnlFundo = new javax.swing.JPanel();
        pnlCartao = new javax.swing.JPanel();
        lblMarca = new javax.swing.JLabel();
        lblSistema = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new barbearia.view.CampoTexto();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new barbearia.view.CampoSenha();
        lblErro = new javax.swing.JLabel();
        btnEntrar = new barbearia.view.Botao();
        lblAviso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Barbearia - Login");
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlFundo.setBackground(new java.awt.Color(245, 246, 248));
        pnlFundo.setLayout(new java.awt.GridBagLayout());

        pnlCartao.setBackground(new java.awt.Color(255, 255, 255));
        pnlCartao.setBorder(javax.swing.BorderFactory.createCompoundBorder(Estilo.bordaLinha(), javax.swing.BorderFactory.createEmptyBorder(36, 40, 32, 40)));
        pnlCartao.setLayout(new java.awt.GridBagLayout());

        lblMarca.setFont(new java.awt.Font("SansSerif", 1, 28));
        lblMarca.setForeground(new java.awt.Color(17, 24, 39));
        lblMarca.setText("BARBEARIA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlCartao.add(lblMarca, gridBagConstraints);

        lblSistema.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSistema.setForeground(new java.awt.Color(107, 114, 128));
        lblSistema.setText("Sistema de gerenciamento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 28, 0);
        pnlCartao.add(lblSistema, gridBagConstraints);

        lblUsuario.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblUsuario.setForeground(new java.awt.Color(17, 24, 39));
        lblUsuario.setText("Usuário");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlCartao.add(lblUsuario, gridBagConstraints);

        txtUsuario.setPlaceholder("Digite seu usuário");
        txtUsuario.setPreferredSize(new java.awt.Dimension(320, 36));
        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlCartao.add(txtUsuario, gridBagConstraints);

        lblSenha.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblSenha.setForeground(new java.awt.Color(17, 24, 39));
        lblSenha.setText("Senha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlCartao.add(lblSenha, gridBagConstraints);

        txtSenha.setPlaceholder("Digite sua senha");
        txtSenha.addActionListener(this::txtSenhaActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlCartao.add(txtSenha, gridBagConstraints);

        lblErro.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblErro.setForeground(new java.awt.Color(220, 38, 38));
        lblErro.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlCartao.add(lblErro, gridBagConstraints);

        btnEntrar.setText("ENTRAR");
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlCartao.add(btnEntrar, gridBagConstraints);

        lblAviso.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblAviso.setForeground(new java.awt.Color(107, 114, 128));
        lblAviso.setText("Acesso restrito ao usuário responsável pelo sistema.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        pnlCartao.add(lblAviso, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        pnlFundo.add(pnlCartao, gridBagConstraints);

        getContentPane().add(pnlFundo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        txtSenha.requestFocusInWindow();
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        entrar();
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        entrar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void entrar() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());
        if (usuario.isEmpty() || senha.isEmpty()) {
            lblErro.setText("Informe usuário e senha.");
            return;
        }
        if (!USUARIO.equals(usuario) || !SENHA.equals(senha)) {
            lblErro.setText("Usuário ou senha inválidos.");
            txtSenha.setText("");
            txtSenha.requestFocusInWindow();
            return;
        }
        // Testa a conexão antes de abrir a tela principal.
        try (java.sql.Connection con = barbearia.conexao.ConexaoMySQL.obterConexao()) {
        } catch (java.sql.SQLException ex) {
            String detalhe = ex.getMessage() != null && ex.getMessage().contains("Access denied")
                    ? "O MySQL recusou o usuário/senha configurados em\nconfig/database.properties ou nas variáveis de ambiente."
                    : "Verifique se o MySQL está rodando e se o banco \"barbearia\" existe.";
            Estilo.erro(this, "Não foi possível conectar ao banco de dados.\n\n" + detalhe
                    + "\n\nDetalhe técnico: " + ex.getMessage());
            return;
        }
        dispose();
        new TelaPrincipal().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnEntrar;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSistema;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlCartao;
    private javax.swing.JPanel pnlFundo;
    private barbearia.view.CampoSenha txtSenha;
    private barbearia.view.CampoTexto txtUsuario;
    // End of variables declaration//GEN-END:variables
}
