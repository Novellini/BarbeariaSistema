package barbearia.view;

import barbearia.dao.ClienteDAO;
import barbearia.model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TelaCliente extends JFrame {

    private JTextField txtNome, txtEmail, txtBusca;
    private JTextField txtTelefone, txtDataNascimento;
    private JButton btnSalvar, btnEditar, btnDeletar, btnLimpar, btnBuscar;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;
    private ClienteDAO dao = new ClienteDAO();
    private int idSelecionado = -1;

    public TelaCliente() {
        initComponentes();
        carregarTabela();
    }

    private void initComponentes() {
        setTitle("Cadastro de Clientes");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelForm = new JPanel(new GridLayout(5, 2, 8, 8));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        txtNome  = new JTextField();
        txtEmail = new JTextField();

        try {
            MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
            mascaraTelefone.setPlaceholderCharacter('_');
            txtTelefone = new JFormattedTextField(mascaraTelefone);

            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtDataNascimento = new JFormattedTextField(mascaraData);
        } catch (java.text.ParseException e) {
            txtTelefone       = new JTextField();
            txtDataNascimento = new JTextField();
        }

        painelForm.add(new JLabel("Nome:*"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("Telefone:*"));
        painelForm.add(txtTelefone);
        painelForm.add(new JLabel("Email:"));
        painelForm.add(txtEmail);
        painelForm.add(new JLabel("Nascimento (DD/MM/AAAA):"));
        painelForm.add(txtDataNascimento);

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

        String[] colunas = {"ID", "Nome", "Telefone", "Email", "Nascimento"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelaClientes = new JTable(modeloTabela);
        tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(40);

        JPanel painelSul = new JPanel(new BorderLayout());
        painelSul.add(painelBusca, BorderLayout.NORTH);
        painelSul.add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);

        add(painelForm, BorderLayout.NORTH);
        add(painelSul,  BorderLayout.CENTER);

        btnSalvar.addActionListener(e  -> salvar());
        btnEditar.addActionListener(e  -> editar());
        btnDeletar.addActionListener(e -> deletar());
        btnLimpar.addActionListener(e  -> limpar());
        btnBuscar.addActionListener(e  -> buscar());

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
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }

    private void salvar() {
    if (!validar()) return;

    // Verifica a data antes de salvar
    String textoData = txtDataNascimento.getText().trim();
    String apenasDigitos = textoData.replace("/", "").replace(" ", "").trim();

    LocalDate data = null;
    if (!apenasDigitos.isEmpty()) {
        data = parseData(textoData);
        if (data == null) return; // data foi digitada mas é inválida — para aqui
    }

    try {
        dao.inserir(new Cliente(
            txtNome.getText().trim(),
            txtTelefone.getText().trim(),
            txtEmail.getText().trim().isEmpty() ? null : txtEmail.getText().trim(),
            data
        ));
        JOptionPane.showMessageDialog(this, "Cliente salvo!");
        limpar();
        carregarTabela();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
    }
}

    private void editar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente primeiro!");
            return;
        }
        if (!validar()) return;
        LocalDate data = parseData(txtDataNascimento.getText().trim());
        try {
            Cliente c = new Cliente(
                txtNome.getText().trim(),
                txtTelefone.getText().trim(),
                txtEmail.getText().trim().isEmpty() ? null : txtEmail.getText().trim(),
                data
            );
            c.setIdCliente(idSelecionado);
            dao.atualizar(c);
            JOptionPane.showMessageDialog(this, "Cliente atualizado!");
            limpar();
            carregarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
        }
    }

    private void deletar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente primeiro!");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
            "Deletar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                dao.deletar(idSelecionado);
                JOptionPane.showMessageDialog(this, "Cliente deletado!");
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
            List<Cliente> lista = txtBusca.getText().trim().isEmpty()
                ? dao.buscarTodos()
                : dao.buscarPorNome(txtBusca.getText().trim());
            for (Cliente c : lista) {
                modeloTabela.addRow(new Object[]{
                    c.getIdCliente(), c.getNome(), c.getTelefone(), c.getEmail(),
                    c.getDataNascimento() != null
                        ? c.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
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
        txtBusca.setText("");
        idSelecionado = -1;
        tabelaClientes.clearSelection();
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
                "Telefone inválido! Preencha no formato (XX) XXXXX-XXXX");
            return false;
        }
        return true;
    }

   private LocalDate parseData(String texto) {
    if (texto == null) return null;

    String apenasDigitos = texto.replace("/", "").replace(" ", "").trim();

    if (apenasDigitos.isEmpty()) return null;

    if (apenasDigitos.length() < 8) {
        JOptionPane.showMessageDialog(this,
            "Data incompleta! Preencha no formato DD/MM/AAAA");
        return null;
    }

    try {
        LocalDate data = LocalDate.parse(texto.trim(),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (data.getYear() < 1900 || data.getYear() > LocalDate.now().getYear()) {
            JOptionPane.showMessageDialog(this,
                "Ano inválido! Digite um ano entre 1900 e " + LocalDate.now().getYear());
            return null;
        }
        return data;

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Data inválida! Use DD/MM/AAAA");
        return null;
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCliente().setVisible(true));
    }
}