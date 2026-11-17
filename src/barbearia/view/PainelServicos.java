package barbearia.view;

import barbearia.dao.ServicoDAO;
import barbearia.model.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PainelServicos extends javax.swing.JPanel {

    private final ServicoDAO dao = new ServicoDAO();
    private List<Servico> todos = new ArrayList<>();
    private final List<Servico> visiveis = new ArrayList<>();

    public PainelServicos() {
        initComponents();
        Estilo.estilizarTabela(tblServicos);
        Estilo.estilizarRolagem(rolTabela);
        Estilo.colunaAcao(tblServicos, COLUNA_ACAO, "EDITAR");
        tblServicos.getColumnModel().getColumn(4).setMaxWidth(80);
        txtBusca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });
    }

    private static final int COLUNA_ACAO = 5;

    public void atualizar() {
        try {
            todos = dao.buscarTodos();
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao carregar: " + ex.getMessage());
            todos = new ArrayList<>();
        }
        filtrar();
    }

    private void filtrar() {
        String termo = txtBusca.getText().trim().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) tblServicos.getModel();
        modelo.setRowCount(0);
        visiveis.clear();
        for (Servico s : todos) {
            String texto = s.getNome() + " " + (s.getDescricao() != null ? s.getDescricao() : "");
            if (termo.isEmpty() || texto.toLowerCase().contains(termo)) {
                visiveis.add(s);
                modelo.addRow(new Object[]{s.getNome(), s.getDescricao() != null ? s.getDescricao() : "-",
                    Estilo.moeda(s.getPreco()), s.getDuracaoMin() + " min", s.isAtivo() ? "Sim" : "Não", ""});
            }
        }
        lblContagem.setText(termo.isEmpty()
                ? todos.size() + " cadastrado(s)"
                : visiveis.size() + " de " + todos.size() + " encontrado(s)");
    }

    // item == null: novo cadastro
    private void abrirFormulario(Servico item) {
        DialogServico d = new DialogServico((java.awt.Frame) SwingUtilities.getWindowAncestor(this), item);
        d.setVisible(true);
        if (d.isAlterado()) atualizar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlConteudo = new javax.swing.JPanel();
        pnlCabecalho = new javax.swing.JPanel();
        pnlTitulos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlAcaoCabecalho = new javax.swing.JPanel();
        btnNovo = new barbearia.view.Botao();
        pnlCorpo = new javax.swing.JPanel();
        pnlBusca = new javax.swing.JPanel();
        lblBusca = new javax.swing.JLabel();
        pnlLinhaBusca = new javax.swing.JPanel();
        txtBusca = new barbearia.view.CampoTexto();
        lblContagem = new javax.swing.JLabel();
        rolTabela = new javax.swing.JScrollPane();
        tblServicos = new javax.swing.JTable();

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
        lblTitulo.setText("Serviços");
        pnlTitulos.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSubtitulo.setForeground(new java.awt.Color(107, 114, 128));
        lblSubtitulo.setText("Clique em EDITAR (ou dê dois cliques na linha) para alterar um cadastro");
        lblSubtitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pnlTitulos.add(lblSubtitulo);

        pnlCabecalho.add(pnlTitulos, java.awt.BorderLayout.WEST);

        pnlAcaoCabecalho.setOpaque(false);
        pnlAcaoCabecalho.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        btnNovo.setText("+ NOVO SERVIÇO");
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        pnlAcaoCabecalho.add(btnNovo);

        pnlCabecalho.add(pnlAcaoCabecalho, java.awt.BorderLayout.EAST);

        pnlConteudo.add(pnlCabecalho, java.awt.BorderLayout.NORTH);

        pnlCorpo.setOpaque(false);
        pnlCorpo.setLayout(new java.awt.BorderLayout(0, 16));

        pnlBusca.setOpaque(false);
        pnlBusca.setLayout(new java.awt.BorderLayout(0, 6));

        lblBusca.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblBusca.setForeground(new java.awt.Color(17, 24, 39));
        lblBusca.setText("Buscar serviço");
        pnlBusca.add(lblBusca, java.awt.BorderLayout.NORTH);

        pnlLinhaBusca.setOpaque(false);
        pnlLinhaBusca.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        txtBusca.setPlaceholder("Nome ou descrição");
        txtBusca.setPreferredSize(new java.awt.Dimension(380, 36));
        pnlLinhaBusca.add(txtBusca);

        lblContagem.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblContagem.setForeground(new java.awt.Color(107, 114, 128));
        lblContagem.setText(" ");
        lblContagem.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 0));
        pnlLinhaBusca.add(lblContagem);

        pnlBusca.add(pnlLinhaBusca, java.awt.BorderLayout.CENTER);

        pnlCorpo.add(pnlBusca, java.awt.BorderLayout.NORTH);

        rolTabela.setBorder(Estilo.bordaLinha());

        tblServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Descrição", "Preço", "Duração", "Ativo", "Ação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServicosMouseClicked(evt);
            }
        });
        rolTabela.setViewportView(tblServicos);

        pnlCorpo.add(rolTabela, java.awt.BorderLayout.CENTER);

        pnlConteudo.add(pnlCorpo, java.awt.BorderLayout.CENTER);

        add(pnlConteudo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        abrirFormulario(null);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tblServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServicosMouseClicked
        int linha = tblServicos.rowAtPoint(evt.getPoint());
        if (linha < 0) return;
        if (evt.getClickCount() == 2 || tblServicos.columnAtPoint(evt.getPoint()) == COLUNA_ACAO) {
            abrirFormulario(visiveis.get(linha));
        }
    }//GEN-LAST:event_tblServicosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnNovo;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblContagem;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcaoCabecalho;
    private javax.swing.JPanel pnlBusca;
    private javax.swing.JPanel pnlCabecalho;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlCorpo;
    private javax.swing.JPanel pnlLinhaBusca;
    private javax.swing.JPanel pnlTitulos;
    private javax.swing.JScrollPane rolTabela;
    private javax.swing.JTable tblServicos;
    private barbearia.view.CampoTexto txtBusca;
    // End of variables declaration//GEN-END:variables
}
