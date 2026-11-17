package barbearia.view;

import barbearia.dao.RelatorioDAO;
import barbearia.dao.RelatorioDAO.Relatorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class PainelRelatorios extends javax.swing.JPanel {
    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private final RelatorioDAO dao = new RelatorioDAO();
    private Relatorio relatorio;

    public PainelRelatorios() {
        initComponents();
        LocalDate hoje = LocalDate.now();
        txtInicio.setText(DATA.format(hoje.withDayOfMonth(1)));
        txtFim.setText(DATA.format(hoje));
        lblTitulo.setFont(Estilo.TITULO);
        lblInicio.setLabelFor(txtInicio);
        lblFim.setLabelFor(txtFim);
        Estilo.estilizarTabela(tblRelatorio);
        Estilo.estilizarRolagem(rolTabela);
        preencherTabela();
    }

    public void atualizar() {
        if (!btnConsultar.isEnabled()) return;
        LocalDate inicio;
        LocalDate fim;
        try {
            inicio = LocalDate.parse(txtInicio.getText(), DATA);
            fim = LocalDate.parse(txtFim.getText(), DATA);
        } catch (DateTimeParseException ex) {
            lblResumo.setText("Informe as datas no formato DD/MM/AAAA.");
            return;
        }
        if (inicio.isBefore(LocalDate.of(1000, 1, 1)) || fim.getYear() > 9998
                || inicio.isAfter(fim) || fim.isAfter(inicio.plusDays(365))) {
            lblResumo.setText("Use um intervalo válido de até 366 dias.");
            return;
        }
        btnConsultar.setEnabled(false);
        txtInicio.setEnabled(false);
        txtFim.setEnabled(false);
        relatorio = null;
        preencherTabela();
        lblResumo.setText("Consultando...");
        new SwingWorker<Relatorio, Void>() {
            @Override protected Relatorio doInBackground() throws Exception {
                return dao.consultar(inicio, fim);
            }

            @Override protected void done() {
                try {
                    relatorio = get();
                    lblResumo.setText("Na agenda: " + relatorio.agendados() + "   |   Concluídos: "
                            + relatorio.concluidos() + "   |   Cancelados: " + relatorio.cancelados()
                            + "   |   Recebido: " + Estilo.moeda(relatorio.recebido()));
                    preencherTabela();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    lblResumo.setText("Consulta interrompida.");
                } catch (ExecutionException ex) {
                    lblResumo.setText("Não foi possível carregar o relatório. Confira a conexão com o banco.");
                } finally {
                    btnConsultar.setEnabled(true);
                    txtInicio.setEnabled(true);
                    txtFim.setEnabled(true);
                }
            }
        }.execute();
    }

    private void preencherTabela() {
        int visao = cboVisao.getSelectedIndex();
        String[] colunas = switch (visao) {
            case 1 -> new String[]{"Barbeiro", "Serviços vendidos", "Atendimentos pagos", "Recebido"};
            case 2 -> new String[]{"Serviço", "Quantidade vendida", "Valor dos serviços"};
            default -> new String[]{"Data", "Na agenda", "Concluídos", "Cancelados", "Recebido"};
        };
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int linha, int coluna) { return false; }
        };
        switch (visao) {
            case 1 -> {
                lblCriterio.setText("Ranking por serviços em atendimentos pagos no período.");
                if (relatorio != null) {
                    for (var barbeiro : relatorio.barbeiros()) {
                        modelo.addRow(new Object[]{barbeiro.nome(), barbeiro.quantidade(),
                            barbeiro.atendimentos(), Estilo.moeda(barbeiro.recebido())});
                    }
                }
            }
            case 2 -> {
                lblCriterio.setText("Vendas pagas no período, pelo preço registrado no agendamento.");
                if (relatorio != null) {
                    for (var servico : relatorio.servicos()) {
                        modelo.addRow(new Object[]{servico.nome(), servico.quantidade(), Estilo.moeda(servico.valor())});
                    }
                }
            }
            default -> {
                lblCriterio.setText("Agenda pela data do atendimento e recebimentos pela data do pagamento.");
                if (relatorio != null) {
                    for (var dia : relatorio.dias()) {
                        modelo.addRow(new Object[]{DATA.format(dia.data()), dia.agendados(), dia.concluidos(),
                            dia.cancelados(), Estilo.moeda(dia.recebido())});
                    }
                }
            }
        }
        if (relatorio != null && modelo.getRowCount() == 0) {
            modelo.addRow(new Object[]{"Sem registros"});
        }
        tblRelatorio.setModel(modelo);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlTopo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlFiltros = new javax.swing.JPanel();
        lblInicio = new javax.swing.JLabel();
        txtInicio = Estilo.campoData();
        lblFim = new javax.swing.JLabel();
        txtFim = Estilo.campoData();
        btnConsultar = new javax.swing.JButton();
        lblResumo = new javax.swing.JLabel();
        rolTabela = new javax.swing.JScrollPane();
        tblRelatorio = new javax.swing.JTable();
        pnlRodape = new javax.swing.JPanel();
        cboVisao = new javax.swing.JComboBox<>();
        lblCriterio = new javax.swing.JLabel();

        setBorder(Estilo.bordaConteudo());
        setLayout(new java.awt.BorderLayout(0, 10));
        pnlTopo.setLayout(new java.awt.BorderLayout(0, 8));
        lblTitulo.setText("Relatórios");
        pnlTopo.add(lblTitulo, java.awt.BorderLayout.NORTH);

        pnlFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 4));
        lblInicio.setText("De");
        pnlFiltros.add(lblInicio);
        txtInicio.setPreferredSize(new java.awt.Dimension(125, 36));
        pnlFiltros.add(txtInicio);
        lblFim.setText("Até");
        pnlFiltros.add(lblFim);
        txtFim.setPreferredSize(new java.awt.Dimension(125, 36));
        pnlFiltros.add(txtFim);
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(this::consultarAcao);
        pnlFiltros.add(btnConsultar);
        pnlTopo.add(pnlFiltros, java.awt.BorderLayout.CENTER);
        lblResumo.setText("Selecione o período.");
        pnlTopo.add(lblResumo, java.awt.BorderLayout.SOUTH);
        add(pnlTopo, java.awt.BorderLayout.NORTH);

        rolTabela.setViewportView(tblRelatorio);
        add(rolTabela, java.awt.BorderLayout.CENTER);

        pnlRodape.setLayout(new java.awt.BorderLayout(12, 0));
        cboVisao.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Movimento por dia", "Ranking de barbeiros", "Serviços mais vendidos"}));
        cboVisao.addActionListener(this::visaoAcao);
        pnlRodape.add(cboVisao, java.awt.BorderLayout.WEST);
        lblCriterio.setText("Agenda pela data do atendimento.");
        pnlRodape.add(lblCriterio, java.awt.BorderLayout.CENTER);
        add(pnlRodape, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void consultarAcao(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarAcao
        atualizar();
    }//GEN-LAST:event_consultarAcao

    private void visaoAcao(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visaoAcao
        preencherTabela();
    }//GEN-LAST:event_visaoAcao

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> cboVisao;
    private javax.swing.JLabel lblCriterio;
    private javax.swing.JLabel lblFim;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblResumo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlTopo;
    private javax.swing.JScrollPane rolTabela;
    private javax.swing.JTable tblRelatorio;
    private javax.swing.JFormattedTextField txtFim;
    private javax.swing.JFormattedTextField txtInicio;
    // End of variables declaration//GEN-END:variables
}
