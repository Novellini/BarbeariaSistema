package barbearia.view;

import barbearia.dao.AgendamentoDAO;
import java.awt.Cursor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PainelPagamento extends javax.swing.JPanel {

    private final TelaPrincipal principal;
    private final AgendamentoDAO dao = new AgendamentoDAO();

    private LocalDate data = LocalDate.now();
    private List<String[]> pendentes = new ArrayList<>();
    private final List<JToggleButton> botoesForma;

    // A ordem dos valores precisa ser a mesma dos botões.
    private static final String[] FORMAS = {"dinheiro", "pix", "cartao_debito", "cartao_credito"};

    public PainelPagamento(TelaPrincipal principal) {
        this.principal = principal;
        initComponents();
        botoesForma = List.of(tglDinheiro, tglPix, tglDebito, tglCredito);
        for (JToggleButton b : botoesForma) b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        atualizarFormas();
    }

    // Carrega os atendimentos sem pagamento da data e pré-seleciona idAgendamento (se != -1)
    public void preparar(LocalDate data, int idAgendamento) {
        this.data = data;
        txtData.setText(data.format(Estilo.DATA));
        grupoForma.clearSelection();
        cboStatus.setSelectedIndex(0);
        carregar(idAgendamento);
    }

    private void trocarData() {
        try {
            preparar(LocalDate.parse(txtData.getText(), Estilo.DATA), -1);
        } catch (DateTimeParseException ex) {
            Estilo.erro(this, "Data inválida! Use o formato dd/mm/aaaa.");
        }
    }

    private void carregar(int idSelecionar) {
        cboAgendamento.removeAllItems();
        pendentes = new ArrayList<>();
        try {
            for (String[] ag : dao.buscarAgendamentosDodia(data.format(Estilo.DATA_SQL))) {
                if (ag[6].equalsIgnoreCase("cancelado") || ag[8].equalsIgnoreCase("pago")) continue;
                pendentes.add(ag);
            }
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao carregar agendamentos: " + ex.getMessage());
        }
        if (pendentes.isEmpty()) {
            cboAgendamento.addItem("Nenhum atendimento pendente de pagamento nesta data");
            cboAgendamento.setEnabled(false);
            txtValor.setText("");
            return;
        }
        cboAgendamento.setEnabled(true);
        int indice = 0;
        for (int i = 0; i < pendentes.size(); i++) {
            String[] ag = pendentes.get(i);
            cboAgendamento.addItem(ag[1] + " - " + ag[4] + " - " + ag[3]);
            if (Integer.parseInt(ag[0]) == idSelecionar) indice = i;
        }
        cboAgendamento.setSelectedIndex(indice);
        preencherValor();
    }

    private String[] selecionado() {
        int i = cboAgendamento.getSelectedIndex();
        return cboAgendamento.isEnabled() && i >= 0 && i < pendentes.size() ? pendentes.get(i) : null;
    }

    // Sugere o total dos serviços como valor
    private void preencherValor() {
        String[] ag = selecionado();
        if (ag == null) return;
        txtValor.setText(ag[5].replace("R$", "").trim().replace(".", ","));
    }

    // Destaca o botão da forma de pagamento escolhida
    private void atualizarFormas() {
        for (JToggleButton b : botoesForma) {
            boolean sel = b.isSelected();
            b.setBackground(sel ? new java.awt.Color(0xDBEAFE) : Estilo.BRANCO);
            b.setForeground(sel ? Estilo.PRIMARIA_ESC : Estilo.TEXTO);
            b.setBorder(new CompoundBorder(new LineBorder(sel ? Estilo.PRIMARIA : Estilo.BORDA, sel ? 2 : 1, true),
                    new EmptyBorder(sel ? 8 : 9, 10, sel ? 8 : 9, 10)));
        }
    }

    private void confirmar() {
        String[] ag = selecionado();
        if (ag == null) {
            Estilo.erro(this, "Selecione um agendamento!");
            return;
        }
        BigDecimal valor;
        try {
            valor = new BigDecimal(txtValor.getText().trim().replace("R$", "").trim().replace(",", "."));
            if (valor.signum() <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            Estilo.erro(this, "Valor inválido! Ex.: 50,00");
            txtValor.requestFocusInWindow();
            return;
        }
        String forma = null;
        for (int i = 0; i < botoesForma.size(); i++) {
            if (botoesForma.get(i).isSelected()) forma = FORMAS[i];
        }
        if (forma == null) {
            Estilo.erro(this, "Escolha a forma de pagamento!");
            return;
        }
        boolean pago = cboStatus.getSelectedIndex() == 0;
        int id = Integer.parseInt(ag[0]);
        try {
            dao.registrarPagamento(id, valor, forma, pago ? "pago" : "pendente");
            if (pago) dao.atualizarStatus(id, "concluido");
            Estilo.info(this, (pago ? "Pagamento de " : "Pagamento pendente de ") + Estilo.moeda(valor)
                    + " registrado para " + ag[1] + ".");
            preparar(data, -1);
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao registrar pagamento: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;
        grupoForma = new javax.swing.ButtonGroup();
        pnlConteudo = new javax.swing.JPanel();
        pnlCabecalho = new javax.swing.JPanel();
        pnlTitulos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        pnlFormulario = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        pnlLinhaData = new javax.swing.JPanel();
        txtData = Estilo.campoData();
        btnBuscar = new barbearia.view.Botao();
        lblAgendamento = new javax.swing.JLabel();
        cboAgendamento = new javax.swing.JComboBox<>();
        lblValor = new javax.swing.JLabel();
        txtValor = new barbearia.view.CampoTexto();
        lblForma = new javax.swing.JLabel();
        pnlFormas = new javax.swing.JPanel();
        tglDinheiro = new javax.swing.JToggleButton();
        tglPix = new javax.swing.JToggleButton();
        tglDebito = new javax.swing.JToggleButton();
        tglCredito = new javax.swing.JToggleButton();
        lblStatus = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        pnlRodape = new javax.swing.JPanel();
        btnVoltar = new barbearia.view.Botao();
        btnConfirmar = new barbearia.view.Botao();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlConteudo.setBackground(new java.awt.Color(255, 255, 255));
        pnlConteudo.setBorder(Estilo.bordaConteudo());
        pnlConteudo.setLayout(new java.awt.BorderLayout());

        pnlCabecalho.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 18, 0));
        pnlCabecalho.setOpaque(false);
        pnlCabecalho.setLayout(new java.awt.BorderLayout());

        pnlTitulos.setOpaque(false);
        pnlTitulos.setLayout(new javax.swing.BoxLayout(pnlTitulos, javax.swing.BoxLayout.PAGE_AXIS));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblTitulo.setText("Registrar pagamento");
        pnlTitulos.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSubtitulo.setForeground(new java.awt.Color(107, 114, 128));
        lblSubtitulo.setText("Relacionar pagamento ao atendimento selecionado");
        lblSubtitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pnlTitulos.add(lblSubtitulo);

        pnlCabecalho.add(pnlTitulos, java.awt.BorderLayout.WEST);

        pnlConteudo.add(pnlCabecalho, java.awt.BorderLayout.NORTH);

        pnlCentro.setOpaque(false);
        pnlCentro.setLayout(new java.awt.BorderLayout());

        pnlFormulario.setOpaque(false);
        pnlFormulario.setPreferredSize(new java.awt.Dimension(680, 400));
        pnlFormulario.setLayout(new java.awt.GridBagLayout());

        lblData.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblData.setForeground(new java.awt.Color(17, 24, 39));
        lblData.setText("Data do atendimento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlFormulario.add(lblData, gridBagConstraints);

        pnlLinhaData.setOpaque(false);
        pnlLinhaData.setLayout(new java.awt.GridBagLayout());

        txtData.setPreferredSize(new java.awt.Dimension(130, 36));
        txtData.addActionListener(this::txtDataActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlLinhaData.add(txtData, gridBagConstraints);

        btnBuscar.setText("Buscar");
        btnBuscar.setContorno(true);
        btnBuscar.setForeground(new java.awt.Color(17, 24, 39));
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlLinhaData.add(btnBuscar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(pnlLinhaData, gridBagConstraints);

        lblAgendamento.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblAgendamento.setForeground(new java.awt.Color(17, 24, 39));
        lblAgendamento.setText("Agendamento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblAgendamento, gridBagConstraints);

        cboAgendamento.setBackground(new java.awt.Color(255, 255, 255));
        cboAgendamento.setFont(new java.awt.Font("SansSerif", 0, 13));
        cboAgendamento.setMinimumSize(new java.awt.Dimension(80, 36));
        cboAgendamento.setPreferredSize(new java.awt.Dimension(260, 36));
        cboAgendamento.addActionListener(this::cboAgendamentoActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(cboAgendamento, gridBagConstraints);

        lblValor.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblValor.setForeground(new java.awt.Color(17, 24, 39));
        lblValor.setText("Valor (R$)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblValor, gridBagConstraints);

        txtValor.setPlaceholder("0,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(txtValor, gridBagConstraints);

        lblForma.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblForma.setForeground(new java.awt.Color(17, 24, 39));
        lblForma.setText("Forma de pagamento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblForma, gridBagConstraints);

        pnlFormas.setOpaque(false);
        pnlFormas.setLayout(new java.awt.GridLayout(1, 4, 8, 0));

        grupoForma.add(tglDinheiro);
        tglDinheiro.setBackground(new java.awt.Color(255, 255, 255));
        tglDinheiro.setFont(new java.awt.Font("SansSerif", 1, 13));
        tglDinheiro.setForeground(new java.awt.Color(17, 24, 39));
        tglDinheiro.setText("DINHEIRO");
        tglDinheiro.setFocusPainted(false);
        tglDinheiro.addItemListener(this::tglFormaItemStateChanged);
        pnlFormas.add(tglDinheiro);

        grupoForma.add(tglPix);
        tglPix.setBackground(new java.awt.Color(255, 255, 255));
        tglPix.setFont(new java.awt.Font("SansSerif", 1, 13));
        tglPix.setForeground(new java.awt.Color(17, 24, 39));
        tglPix.setText("PIX");
        tglPix.setFocusPainted(false);
        tglPix.addItemListener(this::tglFormaItemStateChanged);
        pnlFormas.add(tglPix);

        grupoForma.add(tglDebito);
        tglDebito.setBackground(new java.awt.Color(255, 255, 255));
        tglDebito.setFont(new java.awt.Font("SansSerif", 1, 13));
        tglDebito.setForeground(new java.awt.Color(17, 24, 39));
        tglDebito.setText("CARTÃO DÉBITO");
        tglDebito.setFocusPainted(false);
        tglDebito.addItemListener(this::tglFormaItemStateChanged);
        pnlFormas.add(tglDebito);

        grupoForma.add(tglCredito);
        tglCredito.setBackground(new java.awt.Color(255, 255, 255));
        tglCredito.setFont(new java.awt.Font("SansSerif", 1, 13));
        tglCredito.setForeground(new java.awt.Color(17, 24, 39));
        tglCredito.setText("CARTÃO CRÉDITO");
        tglCredito.setFocusPainted(false);
        tglCredito.addItemListener(this::tglFormaItemStateChanged);
        pnlFormas.add(tglCredito);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(pnlFormas, gridBagConstraints);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblStatus.setForeground(new java.awt.Color(17, 24, 39));
        lblStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblStatus, gridBagConstraints);

        cboStatus.setBackground(new java.awt.Color(255, 255, 255));
        cboStatus.setFont(new java.awt.Font("SansSerif", 0, 13));
        cboStatus.setMinimumSize(new java.awt.Dimension(80, 36));
        cboStatus.setPreferredSize(new java.awt.Dimension(260, 36));
        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pago", "Pendente" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlFormulario.add(cboStatus, gridBagConstraints);

        pnlCentro.add(pnlFormulario, java.awt.BorderLayout.WEST);

        pnlConteudo.add(pnlCentro, java.awt.BorderLayout.CENTER);

        pnlRodape.setOpaque(false);
        pnlRodape.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 0, 0, 0));
        pnlRodape.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        btnVoltar.setText("VOLTAR PARA AGENDA");
        btnVoltar.setContorno(true);
        btnVoltar.setForeground(new java.awt.Color(17, 24, 39));
        btnVoltar.addActionListener(this::btnVoltarActionPerformed);
        pnlRodape.add(btnVoltar);

        btnConfirmar.setText("CONFIRMAR PAGAMENTO");
        btnConfirmar.setBackground(new java.awt.Color(22, 163, 74));
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
        pnlRodape.add(btnConfirmar);

        pnlConteudo.add(pnlRodape, java.awt.BorderLayout.SOUTH);

        add(pnlConteudo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        trocarData();
    }//GEN-LAST:event_txtDataActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        trocarData();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cboAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAgendamentoActionPerformed
        preencherValor();
    }//GEN-LAST:event_cboAgendamentoActionPerformed

    private void tglFormaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglFormaItemStateChanged
        if (botoesForma != null) atualizarFormas();
    }//GEN-LAST:event_tglFormaItemStateChanged

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        principal.mostrarAgenda(data);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnBuscar;
    private barbearia.view.Botao btnConfirmar;
    private barbearia.view.Botao btnVoltar;
    private javax.swing.JComboBox<String> cboAgendamento;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.ButtonGroup grupoForma;
    private javax.swing.JLabel lblAgendamento;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblForma;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlCabecalho;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlFormas;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlLinhaData;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlTitulos;
    private javax.swing.JToggleButton tglCredito;
    private javax.swing.JToggleButton tglDebito;
    private javax.swing.JToggleButton tglDinheiro;
    private javax.swing.JToggleButton tglPix;
    private javax.swing.JFormattedTextField txtData;
    private barbearia.view.CampoTexto txtValor;
    // End of variables declaration//GEN-END:variables
}
