package barbearia.view;

import barbearia.dao.ServicoDAO;
import barbearia.model.Servico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class TelaServico extends JFrame {

    // ---- Campos do formulário ----
    private JTextField txtNome;        // campo nome do serviço
    private JTextField txtDescricao;   // campo descrição
    private JTextField txtPreco;       // campo preço (só números)
    private JTextField txtDuracao;     // campo duração em minutos (só números)
    private JTextField txtBusca;       // campo de busca por nome
    private JCheckBox  chkAtivo;       // checkbox para marcar se está ativo

    // ---- Botões ----
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnDeletar;
    private JButton btnLimpar;
    private JButton btnBuscar;

    // ---- Tabela ----
    private JTable tabelaServicos;
    private DefaultTableModel modeloTabela; // modelo que armazena os dados da tabela

    // ---- Outros ----
    private ServicoDAO dao = new ServicoDAO(); // objeto para acessar o banco
    private int idSelecionado = -1;            // guarda o id do serviço clicado na tabela

    // ---- Construtor ----
    public TelaServico() {
        initComponentes(); // monta a tela
        carregarTabela();  // carrega os serviços do banco na tabela
    }

    // ---- Monta todos os componentes visuais da tela ----
    private void initComponentes() {
        setTitle("Cadastro de Serviços");
        setSize(750, 550);
        setLocationRelativeTo(null); // centraliza na tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---- PAINEL DO FORMULÁRIO ----
        // GridLayout(6,2) = 6 linhas e 2 colunas
        JPanel painelForm = new JPanel(new GridLayout(6, 2, 8, 8));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Serviço"));

        // Criação dos campos
        txtNome      = new JTextField();
        txtDescricao = new JTextField();
        txtPreco     = new JTextField();
        txtDuracao   = new JTextField();
        chkAtivo     = new JCheckBox("Ativo");
        chkAtivo.setSelected(true); // novo serviço começa ativo por padrão

        // ---- BLOQUEIO DE LETRAS NO CAMPO PREÇO ----
        // Permite apenas: números, vírgula, ponto e backspace
        txtPreco.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != ',' && c != '.' && c != '\b') {
                    e.consume(); // cancela o caractere inválido
                }
            }
        });

        // ---- BLOQUEIO DE LETRAS NO CAMPO DURAÇÃO ----
        // Permite apenas: números e backspace
        txtDuracao.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    e.consume(); // cancela o caractere inválido
                }
            }
        });

        // Adiciona labels e campos no painel
        painelForm.add(new JLabel("Nome:*"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("Descrição:"));
        painelForm.add(txtDescricao);
        painelForm.add(new JLabel("Preço (R$):*"));
        painelForm.add(txtPreco);
        painelForm.add(new JLabel("Duração (min):*"));
        painelForm.add(txtDuracao);
        painelForm.add(new JLabel("Status:"));
        painelForm.add(chkAtivo);

        // ---- PAINEL DOS BOTÕES ----
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnSalvar  = new JButton("Salvar");
        btnEditar  = new JButton("Editar");
        btnDeletar = new JButton("Deletar");
        btnLimpar  = new JButton("Limpar");

        // Cores dos botões
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

        // ---- PAINEL DE BUSCA ----
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBusca  = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        painelBusca.add(new JLabel("Buscar por nome:"));
        painelBusca.add(txtBusca);
        painelBusca.add(btnBuscar);

        // ---- TABELA ----
        // Colunas da tabela
        String[] colunas = {"ID", "Nome", "Descrição", "Preço", "Duração", "Ativo"};

        // DefaultTableModel controla os dados da tabela
        // isCellEditable retorna false para impedir edição direta na tabela
        modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tabelaServicos = new JTable(modeloTabela);
        tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaServicos.getColumnModel().getColumn(0).setMaxWidth(40); // coluna ID pequena

        // ---- MONTAGEM FINAL DA TELA ----
        JPanel painelSul = new JPanel(new BorderLayout());
        painelSul.add(painelBusca, BorderLayout.NORTH);
        painelSul.add(new JScrollPane(tabelaServicos), BorderLayout.CENTER);

        add(painelForm, BorderLayout.NORTH);
        add(painelSul,  BorderLayout.CENTER);

        // ---- EVENTOS DOS BOTÕES ----
        // Cada botão chama seu método correspondente
        btnSalvar.addActionListener(e  -> salvar());
        btnEditar.addActionListener(e  -> editar());
        btnDeletar.addActionListener(e -> deletar());
        btnLimpar.addActionListener(e  -> limpar());
        btnBuscar.addActionListener(e  -> buscar());

        // Quando o usuário clica em uma linha da tabela
        // preenche o formulário com os dados daquela linha
        tabelaServicos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) selecionarDaTabela();
        });
    }

    // ---- Busca todos os serviços do banco e exibe na tabela ----
    private void carregarTabela() {
        modeloTabela.setRowCount(0); // limpa a tabela antes de carregar
        try {
            List<Servico> lista = dao.buscarTodos();
            for (Servico s : lista) {
                // Adiciona uma linha na tabela para cada serviço
                modeloTabela.addRow(new Object[]{
                    s.getIdServico(),
                    s.getNome(),
                    s.getDescricao(),
                    "R$ " + s.getPreco(),
                    s.getDuracaoMin() + " min",
                    s.isAtivo() ? "Sim" : "Não"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }

    // ---- Salva um novo serviço no banco ----
    private void salvar() {
        if (!validar()) return; // para se campos obrigatórios estiverem vazios
        try {
            dao.inserir(new Servico(
                txtNome.getText().trim(),
                txtDescricao.getText().trim().isEmpty() ? null : txtDescricao.getText().trim(),
                new BigDecimal(txtPreco.getText().trim().replace(",", ".")), // vírgula vira ponto
                Integer.parseInt(txtDuracao.getText().trim()),
                chkAtivo.isSelected()
            ));
            JOptionPane.showMessageDialog(this, "Serviço salvo!");
            limpar();
            carregarTabela();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço ou duração inválidos!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    // ---- Atualiza o serviço selecionado na tabela ----
    private void editar() {
        // Verifica se algum serviço foi selecionado
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um serviço primeiro!");
            return;
        }
        if (!validar()) return;
        try {
            Servico s = new Servico(
                txtNome.getText().trim(),
                txtDescricao.getText().trim().isEmpty() ? null : txtDescricao.getText().trim(),
                new BigDecimal(txtPreco.getText().trim().replace(",", ".")),
                Integer.parseInt(txtDuracao.getText().trim()),
                chkAtivo.isSelected()
            );
            s.setIdServico(idSelecionado); // seta o id para o UPDATE saber qual linha atualizar
            dao.atualizar(s);
            JOptionPane.showMessageDialog(this, "Serviço atualizado!");
            limpar();
            carregarTabela();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço ou duração inválidos!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
        }
    }

    // ---- Remove o serviço selecionado do banco ----
    private void deletar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um serviço primeiro!");
            return;
        }
        // Pede confirmação antes de deletar
        int ok = JOptionPane.showConfirmDialog(this,
            "Deletar este serviço?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                dao.deletar(idSelecionado);
                JOptionPane.showMessageDialog(this, "Serviço deletado!");
                limpar();
                carregarTabela();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao deletar: " + ex.getMessage());
            }
        }
    }

    // ---- Busca serviços pelo nome digitado ----
    private void buscar() {
        try {
            modeloTabela.setRowCount(0);
            // Se o campo busca estiver vazio, busca todos
            // Se tiver algo digitado, busca pelo nome
            List<Servico> lista = txtBusca.getText().trim().isEmpty()
                ? dao.buscarTodos()
                : dao.buscarPorNome(txtBusca.getText().trim());
            for (Servico s : lista) {
                modeloTabela.addRow(new Object[]{
                    s.getIdServico(), s.getNome(), s.getDescricao(),
                    "R$ " + s.getPreco(), s.getDuracaoMin() + " min",
                    s.isAtivo() ? "Sim" : "Não"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro na busca: " + ex.getMessage());
        }
    }

    // ---- Preenche o formulário ao clicar em uma linha da tabela ----
    private void selecionarDaTabela() {
        int linha = tabelaServicos.getSelectedRow();
        if (linha == -1) return;

        // Guarda o id da linha selecionada para usar no editar/deletar
        idSelecionado = (int) modeloTabela.getValueAt(linha, 0);
        txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
        txtDescricao.setText(modeloTabela.getValueAt(linha, 2) != null
            ? (String) modeloTabela.getValueAt(linha, 2) : "");
        // Remove "R$ " antes de colocar no campo preço
        txtPreco.setText(modeloTabela.getValueAt(linha, 3).toString().replace("R$ ", ""));
        // Remove " min" antes de colocar no campo duração
        txtDuracao.setText(modeloTabela.getValueAt(linha, 4).toString().replace(" min", ""));
        chkAtivo.setSelected(modeloTabela.getValueAt(linha, 5).equals("Sim"));
    }

    // ---- Limpa todos os campos do formulário ----
    private void limpar() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtDuracao.setText("");
        txtBusca.setText("");
        chkAtivo.setSelected(true);
        idSelecionado = -1;            // reseta o id selecionado
        tabelaServicos.clearSelection(); // deseleciona a linha da tabela
    }

    // ---- Valida os campos obrigatórios antes de salvar ou editar ----
    private boolean validar() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório!");
            return false;
        }
        if (txtPreco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preço é obrigatório!");
            return false;
        }
        if (txtDuracao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Duração é obrigatória!");
            return false;
        }
        // Valida se o preço é um número válido
        try {
            new BigDecimal(txtPreco.getText().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido! Use vírgula ou ponto. Ex: 35,00");
            return false;
        }
        // Valida se a duração é um número inteiro válido
        try {
            Integer.parseInt(txtDuracao.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duração inválida! Digite só números. Ex: 30");
            return false;
        }
        return true;
    }

    // ---- Método main para testar a tela isoladamente ----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaServico().setVisible(true));
    }
}