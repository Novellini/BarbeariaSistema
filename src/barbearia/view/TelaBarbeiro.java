package barbearia.view;

import barbearia.dao.BarbeirDAO;
import barbearia.model.Barbeiro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaBarbeiro extends JFrame {

    private JTextField txtNome, txtEspecialidade, txtBusca;
    private JTextField txtTelefone;
    private JCheckBox  chkAtivo;
    private JButton    btnSalvar, btnEditar, btnDeletar, btnLimpar, btnBuscar;
    private JTable     tabelaBarbeiros;
    private DefaultTableModel modeloTabela;
    private BarbeirDAO dao = new BarbeirDAO();
    private int idSelecionado = -1;

    public TelaBarbeiro() {
        initComponentes();
        carregarTabela();
    }

    private void initComponentes() {
        setTitle("Cadastro de Barbeiros");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelForm = new JPanel(new GridLayout(5, 2, 8, 8));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Barbeiro"));

        txtNome         = new JTextField();
        txtEspecialidade = new JTextField();
        chkAtivo        = new JCheckBox("Ativo");
        chkAtivo.setSelected(true); // novo barbeiro começa ativo por padrão

        try {
            MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
            mascaraTelefone.setPlaceholderCharacter('_');
            txtTelefone = new JFormattedTextField(mascaraTelefone);
        } catch (java.text.ParseException e) {
            txtTelefone = new JTextField();
        }

        painelForm.add(new JLabel("Nome:*"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("Telefone:*"));
        painelForm.add(txtTelefone);
        painelForm.add(new JLabel("Especialidade:"));
        painelForm.add(txtEspecialidade);
        painelForm.add(new JLabel("Status:"));
        painelForm.add(chkAtivo);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnSalvar  = new JButton("Salvar");
        btnEditar  = new JButton("Editar");
        btnDeletar = new JButton("Deletar");
        btnLimpar  = new JButton("Limpar");

        btnSalvar.setBackground(new Color(34, 139, 34));
        btnSalvar.setForeground(Color.WHITE);
        btnDeletar.setBackground(new Color(178, 34, 34));
        btnDeletar.setForeground(Color.WHITE);

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnDeletar);
        painelBotoes.add(btnLimpar);
        painelForm.add(new JLabel(""));
        painelForm.add(painelBotoes);

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBusca  = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        painelBusca.add(new JLabel("Buscar por nome:"));
        painelBusca.add(txtBusca);
        painelBusca.add(btnBuscar);

        String[] colunas = {"ID", "Nome", "Telefone", "Especialidade", "Ativo"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelaBarbeiros = new JTable(modeloTabela);
        tabelaBarbeiros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaBarbeiros.getColumnModel().getColumn(0).setMaxWidth(40);

        JPanel painelSul = new JPanel(new BorderLayout());
        painelSul.add(painelBusca, BorderLayout.NORTH);
        painelSul.add(new JScrollPane(tabelaBarbeiros), BorderLayout.CENTER);

        add(painelForm, BorderLayout.NORTH);
        add(painelSul,  BorderLayout.CENTER);

        btnSalvar.addActionListener(e  -> salvar());
        btnEditar.addActionListener(e  -> editar());
        btnDeletar.addActionListener(e -> deletar());
        btnLimpar.addActionListener(e  -> limpar());
        btnBuscar.addActionListener(e  -> buscar());

        tabelaBarbeiros.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) selecionarDaTabela();
        });
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        try {
            List<Barbeiro> lista = dao.buscarTodos();
            for (Barbeiro b : lista) {
                modeloTabela.addRow(new Object[]{
                    b.getId_barbeiro(),
                    b.getNome(),
                    b.getTelefone(),
                    b.getEspecialidade(),
                    b.isAtivo() ? "Sim" : "Não"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }

    private void salvar() {
        if (!validar()) return;
        try {
            dao.inserir(new Barbeiro(
                txtNome.getText().trim(),
                txtTelefone.getText().trim(),
                txtEspecialidade.getText().trim().isEmpty() ? null : txtEspecialidade.getText().trim(),
                chkAtivo.isSelected()
            ));
            JOptionPane.showMessageDialog(this, "Barbeiro salvo!");
            limpar();
            carregarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void editar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um barbeiro primeiro!");
            return;
        }
        if (!validar()) return;
        try {
            Barbeiro b = new Barbeiro(
                txtNome.getText().trim(),
                txtTelefone.getText().trim(),
                txtEspecialidade.getText().trim().isEmpty() ? null : txtEspecialidade.getText().trim(),
                chkAtivo.isSelected()
            );
            b.setIdBarbeiro(idSelecionado);
            dao.atualizar(b);
            JOptionPane.showMessageDialog(this, "Barbeiro atualizado!");
            limpar();
            carregarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
        }
    }

    private void deletar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um barbeiro primeiro!");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
            "Deletar este barbeiro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                dao.deletar(idSelecionado);
                JOptionPane.showMessageDialog(this, "Barbeiro deletado!");
                limpar();
                carregarTabela();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao deletar: " + ex.getMessage());
            }
        }
    }

    private void buscar() {
        try {
            modeloTabela.setRowCount(0);
            List<Barbeiro> lista = txtBusca.getText().trim().isEmpty()
                ? dao.buscarTodos()
                : dao.buscarPorNome(txtBusca.getText().trim());
            for (Barbeiro b : lista) {
                modeloTabela.addRow(new Object[]{
                    b.getId_barbeiro(), b.getNome(), b.getTelefone(),
                    b.getEspecialidade(), b.isAtivo() ? "Sim" : "Não"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
        }
    }

    private void selecionarDaTabela() {
        int linha = tabelaBarbeiros.getSelectedRow();
        if (linha == -1) return;
        idSelecionado = (int) modeloTabela.getValueAt(linha, 0);
        txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
        txtTelefone.setText((String) modeloTabela.getValueAt(linha, 2));
        txtEspecialidade.setText(modeloTabela.getValueAt(linha, 3) != null
            ? (String) modeloTabela.getValueAt(linha, 3) : "");
        chkAtivo.setSelected(modeloTabela.getValueAt(linha, 4).equals("Sim"));
    }

    private void limpar() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEspecialidade.setText("");
        txtBusca.setText("");
        chkAtivo.setSelected(true);
        idSelecionado = -1;
        tabelaBarbeiros.clearSelection();
    }

    private boolean validar() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
            return false;
        }
        String tel = txtTelefone.getText()
            .replace("(","").replace(")","")
            .replace("-","").replace(" ","")
            .replace("_","").trim();
        if (tel.isEmpty() || tel.length() < 11) {
            JOptionPane.showMessageDialog(this,
                "Telefone inválido! Use o formato (XX) XXXXX-XXXX");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaBarbeiro().setVisible(true));
    }
}