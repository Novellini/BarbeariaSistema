package barbearia.view;

import barbearia.dao.AgendamentoDAO;
import barbearia.dao.BarbeirDAO;
import barbearia.dao.ClienteDAO;
import barbearia.dao.ServicoDAO;
import barbearia.model.Agendamento;
import barbearia.model.Barbeiro;
import barbearia.model.Cliente;
import barbearia.model.Servico;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import barbearia.service.GradeHorarios;
import java.util.List;
import javax.swing.JCheckBox;

public class PainelNovoAgendamento extends javax.swing.JPanel {

    private final TelaPrincipal principal;
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final BarbeirDAO barbeiroDAO = new BarbeirDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();

    // Cria uma caixa de seleção para cada serviço cadastrado.
    private final List<JCheckBox> checkServicos = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();
    private boolean carregando;

    public PainelNovoAgendamento(TelaPrincipal principal) {
        this.principal = principal;
        initComponents();
        Estilo.estilizarRolagem(rolServicos);
        Estilo.estilizarRolagem(rolObservacao);
        txtData.addPropertyChangeListener("value", e -> { carregarHorarios(); atualizarResumo(); });
    }

    // Recarrega listas do banco e limpa o formulário
    public void preparar(LocalDate data) {
        carregando = true;
        try {
            cboCliente.removeAllItems();
            cboCliente.addItem("Selecionar cliente");
            for (Cliente c : clienteDAO.buscarTodos()) cboCliente.addItem(c);

            cboBarbeiro.removeAllItems();
            cboBarbeiro.addItem("Selecionar barbeiro");
            for (Barbeiro b : barbeiroDAO.buscarTodos()) if (b.isAtivo()) cboBarbeiro.addItem(b);

            servicos = new ArrayList<>();
            for (Servico s : servicoDAO.buscarTodos()) if (s.isAtivo()) servicos.add(s);
            pnlServicos.removeAll();
            checkServicos.clear();
            for (Servico s : servicos) {
                JCheckBox chk = new JCheckBox(s.getNome() + "  (" + Estilo.moeda(s.getPreco()) + " · " + s.getDuracaoMin() + " min)");
                chk.setOpaque(false);
                chk.setFont(Estilo.NORMAL);
                chk.addActionListener(e -> { carregarHorarios(); atualizarResumo(); });
                checkServicos.add(chk);
                pnlServicos.add(chk);
            }
            if (servicos.isEmpty()) {
                javax.swing.JLabel vazio = new javax.swing.JLabel("  Nenhum serviço ativo cadastrado.");
                vazio.setForeground(Estilo.TEXTO_SUAVE);
                pnlServicos.add(vazio);
            }
            pnlServicos.revalidate();
            pnlServicos.repaint();
        } catch (java.sql.SQLException ex) {
            Estilo.erro(this, "Erro ao carregar dados: " + ex.getMessage());
        }
        txtObservacao.setText("");
        LocalDate d = data != null && !data.isBefore(LocalDate.now()) ? data : LocalDate.now();
        txtData.setValue(d.format(Estilo.DATA));
        carregando = false;
        carregarHorarios();
        atualizarResumo();
    }

    private LocalDate dataSelecionada() {
        try {
            return LocalDate.parse(txtData.getText(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(java.time.format.ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    // Lista os horários livres do barbeiro escolhido na data escolhida
    private void carregarHorarios() {
        if (carregando) return;
        Object anterior = cboHorario.getSelectedItem();
        cboHorario.removeAllItems();
        LocalDate data = dataSelecionada();
        if (data == null) {
            cboHorario.addItem("Informe uma data válida");
            return;
        }
        if (!(cboBarbeiro.getSelectedItem() instanceof Barbeiro barbeiro)) {
            cboHorario.addItem("Selecione o barbeiro");
            return;
        }
        int minutos = 0;
        for (int i = 0; i < checkServicos.size(); i++) {
            if (checkServicos.get(i).isSelected()) minutos += servicos.get(i).getDuracaoMin();
        }
        if (minutos <= 0) {
            cboHorario.addItem("Selecione os serviços");
            return;
        }
        if (data.isBefore(LocalDate.now()) || data.getYear() > 9998) {
            cboHorario.addItem("Informe uma data válida");
            return;
        }
        try {
            var reservas = agendamentoDAO.buscarReservas(barbeiro.getId_barbeiro(), data);
            var livres = GradeHorarios.livres(data, minutos, reservas);
            if (livres.isEmpty()) {
                cboHorario.addItem("Sem horários livres nesta data");
            } else {
                cboHorario.addItem("Selecione o horário");
                for (String horario : livres) cboHorario.addItem(horario);
                if (livres.contains(anterior)) cboHorario.setSelectedItem(anterior);
            }
        } catch (java.sql.SQLException ex) {
            cboHorario.addItem("Falha ao consultar horários");
            Estilo.erro(this, "Erro ao consultar horários: " + ex.getMessage());
        }
    }

    private LocalTime horarioSelecionado() {
        Object h = cboHorario.getSelectedItem();
        if (h == null) return null;
        try {
            return LocalTime.parse(h.toString(), Estilo.HORA);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    private void atualizarResumo() {
        if (carregando) return;
        resCliente.setText(cboCliente.getSelectedItem() instanceof Cliente c ? c.getNome() : "-");
        resBarbeiro.setText(cboBarbeiro.getSelectedItem() instanceof Barbeiro b ? b.getNome() : "-");
        List<String> nomes = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        int duracao = 0;
        for (int i = 0; i < checkServicos.size(); i++) {
            if (checkServicos.get(i).isSelected()) {
                nomes.add(servicos.get(i).getNome());
                total = total.add(servicos.get(i).getPreco());
                duracao += servicos.get(i).getDuracaoMin();
            }
        }
        resServicos.setText(nomes.isEmpty() ? "-" : "<html>" + String.join("<br>", nomes) + "</html>");
        LocalDate data = dataSelecionada();
        resData.setText(data != null ? data.format(Estilo.DATA) : "-");
        LocalTime hora = horarioSelecionado();
        resHorario.setText(hora != null ? hora.format(Estilo.HORA) : "-");
        resDuracao.setText(duracao > 0 ? duracao + " min" : "-");
        resTotal.setText(Estilo.moeda(total));
    }

    private void salvar() {
        if (!(cboCliente.getSelectedItem() instanceof Cliente cliente)) {
            Estilo.erro(this, "Selecione um cliente!");
            cboCliente.requestFocusInWindow();
            return;
        }
        if (!(cboBarbeiro.getSelectedItem() instanceof Barbeiro barbeiro)) {
            Estilo.erro(this, "Selecione um barbeiro!");
            cboBarbeiro.requestFocusInWindow();
            return;
        }
        boolean algum = checkServicos.stream().anyMatch(JCheckBox::isSelected);
        if (!algum) {
            Estilo.erro(this, "Selecione pelo menos um serviço!");
            return;
        }
        LocalDate data = dataSelecionada();
        if (data == null) {
            Estilo.erro(this, "Data inválida! Use o formato dd/mm/aaaa.");
            txtData.requestFocusInWindow();
            return;
        }
        if (data.isBefore(LocalDate.now())) {
            Estilo.erro(this, "Não é possível agendar em uma data passada.");
            txtData.requestFocusInWindow();
            return;
        }
        LocalTime hora = horarioSelecionado();
        if (hora == null) {
            Estilo.erro(this, "Selecione um horário disponível!");
            cboHorario.requestFocusInWindow();
            return;
        }
        try {
            String obs = txtObservacao.getText().trim();
            List<Integer> ids = new ArrayList<>();
            for (int i = 0; i < checkServicos.size(); i++) {
                if (checkServicos.get(i).isSelected()) ids.add(servicos.get(i).getIdServico());
            }
            agendamentoDAO.inserir(new Agendamento(
                    cliente.getIdCliente(), barbeiro.getId_barbeiro(), LocalDateTime.of(data, hora),
                    "agendado", obs.isEmpty() ? null : obs), ids);
            Estilo.info(this, "Agendamento confirmado para " + cliente.getNome()
                    + " em " + data.format(Estilo.DATA) + " às " + hora.format(Estilo.HORA) + ".");
            principal.mostrarAgenda(data);
        } catch (IllegalArgumentException ex) {
            Estilo.erro(this, ex.getMessage());
            carregarHorarios();
        } catch (java.sql.SQLException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("uq_barbeiro_horario")) {
                Estilo.erro(this, "Esse barbeiro já tem um agendamento neste horário. Escolha outro.");
                carregarHorarios();
            } else {
                Estilo.erro(this, "Erro ao salvar: " + ex.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;
        pnlConteudo = new javax.swing.JPanel();
        pnlCabecalho = new javax.swing.JPanel();
        pnlTitulos = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlColunas = new javax.swing.JPanel();
        pnlFormulario = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox<>();
        lblBarbeiro = new javax.swing.JLabel();
        cboBarbeiro = new javax.swing.JComboBox<>();
        lblServicos = new javax.swing.JLabel();
        rolServicos = new javax.swing.JScrollPane();
        pnlServicos = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        txtData = Estilo.campoData();
        lblHorario = new javax.swing.JLabel();
        cboHorario = new javax.swing.JComboBox<>();
        lblObservacao = new javax.swing.JLabel();
        rolObservacao = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        pnlResumo = new javax.swing.JPanel();
        lblResumoTitulo = new javax.swing.JLabel();
        pnlResumoLinhas = new javax.swing.JPanel();
        lblResCliente = new javax.swing.JLabel();
        resCliente = new javax.swing.JLabel();
        lblResBarbeiro = new javax.swing.JLabel();
        resBarbeiro = new javax.swing.JLabel();
        lblResServicos = new javax.swing.JLabel();
        resServicos = new javax.swing.JLabel();
        lblResData = new javax.swing.JLabel();
        resData = new javax.swing.JLabel();
        lblResHorario = new javax.swing.JLabel();
        resHorario = new javax.swing.JLabel();
        lblResDuracao = new javax.swing.JLabel();
        resDuracao = new javax.swing.JLabel();
        pnlTotal = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        resTotal = new javax.swing.JLabel();
        pnlRodape = new javax.swing.JPanel();
        btnConfirmar = new barbearia.view.Botao();
        btnCancelar = new barbearia.view.Botao();

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
        lblTitulo.setText("Novo agendamento");
        pnlTitulos.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblSubtitulo.setForeground(new java.awt.Color(107, 114, 128));
        lblSubtitulo.setText("Preencha os dados do atendimento");
        lblSubtitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pnlTitulos.add(lblSubtitulo);

        pnlCabecalho.add(pnlTitulos, java.awt.BorderLayout.WEST);

        pnlConteudo.add(pnlCabecalho, java.awt.BorderLayout.NORTH);

        pnlColunas.setOpaque(false);
        pnlColunas.setLayout(new java.awt.GridBagLayout());

        pnlFormulario.setOpaque(false);
        pnlFormulario.setPreferredSize(new java.awt.Dimension(380, 10));
        pnlFormulario.setLayout(new java.awt.GridBagLayout());

        lblCliente.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblCliente.setForeground(new java.awt.Color(17, 24, 39));
        lblCliente.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlFormulario.add(lblCliente, gridBagConstraints);

        cboCliente.setBackground(new java.awt.Color(255, 255, 255));
        cboCliente.setFont(new java.awt.Font("SansSerif", 0, 13));
        cboCliente.setMinimumSize(new java.awt.Dimension(80, 36));
        cboCliente.setPreferredSize(new java.awt.Dimension(260, 36));
        cboCliente.addActionListener(this::cboClienteActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(cboCliente, gridBagConstraints);

        lblBarbeiro.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblBarbeiro.setForeground(new java.awt.Color(17, 24, 39));
        lblBarbeiro.setText("Barbeiro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblBarbeiro, gridBagConstraints);

        cboBarbeiro.setBackground(new java.awt.Color(255, 255, 255));
        cboBarbeiro.setFont(new java.awt.Font("SansSerif", 0, 13));
        cboBarbeiro.setMinimumSize(new java.awt.Dimension(80, 36));
        cboBarbeiro.setPreferredSize(new java.awt.Dimension(260, 36));
        cboBarbeiro.addActionListener(this::cboBarbeiroActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(cboBarbeiro, gridBagConstraints);

        lblServicos.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblServicos.setForeground(new java.awt.Color(17, 24, 39));
        lblServicos.setText("Serviços (marque um ou mais)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblServicos, gridBagConstraints);

        rolServicos.setBorder(Estilo.bordaLinha());
        rolServicos.setMinimumSize(new java.awt.Dimension(80, 90));
        rolServicos.setPreferredSize(new java.awt.Dimension(260, 110));

        pnlServicos.setBackground(new java.awt.Color(255, 255, 255));
        pnlServicos.setLayout(new javax.swing.BoxLayout(pnlServicos, javax.swing.BoxLayout.PAGE_AXIS));

        rolServicos.setViewportView(pnlServicos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(rolServicos, gridBagConstraints);

        lblData.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblData.setForeground(new java.awt.Color(17, 24, 39));
        lblData.setText("Data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 12);
        pnlFormulario.add(lblData, gridBagConstraints);

        txtData.setPreferredSize(new java.awt.Dimension(150, 36));
        txtData.addActionListener(this::txtDataActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        pnlFormulario.add(txtData, gridBagConstraints);

        lblHorario.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblHorario.setForeground(new java.awt.Color(17, 24, 39));
        lblHorario.setText("Horário disponível");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblHorario, gridBagConstraints);

        cboHorario.setBackground(new java.awt.Color(255, 255, 255));
        cboHorario.setFont(new java.awt.Font("SansSerif", 0, 13));
        cboHorario.setMinimumSize(new java.awt.Dimension(80, 36));
        cboHorario.setPreferredSize(new java.awt.Dimension(150, 36));
        cboHorario.addActionListener(this::cboHorarioActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFormulario.add(cboHorario, gridBagConstraints);

        lblObservacao.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblObservacao.setForeground(new java.awt.Color(17, 24, 39));
        lblObservacao.setText("Observação (opcional)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        pnlFormulario.add(lblObservacao, gridBagConstraints);

        rolObservacao.setBorder(Estilo.bordaLinha());
        rolObservacao.setMinimumSize(new java.awt.Dimension(80, 60));
        rolObservacao.setPreferredSize(new java.awt.Dimension(260, 60));

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("SansSerif", 0, 13));
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(3);
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8));
        rolObservacao.setViewportView(txtObservacao);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlFormulario.add(rolObservacao, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 24);
        pnlColunas.add(pnlFormulario, gridBagConstraints);

        pnlResumo.setBackground(new java.awt.Color(249, 250, 251));
        pnlResumo.setBorder(Estilo.bordaCartao(18, 20, 18, 20));
        pnlResumo.setPreferredSize(new java.awt.Dimension(280, 10));
        pnlResumo.setLayout(new java.awt.BorderLayout(0, 14));

        lblResumoTitulo.setFont(new java.awt.Font("SansSerif", 1, 15));
        lblResumoTitulo.setForeground(new java.awt.Color(17, 24, 39));
        lblResumoTitulo.setText("Resumo do agendamento");
        pnlResumo.add(lblResumoTitulo, java.awt.BorderLayout.NORTH);

        pnlResumoLinhas.setOpaque(false);
        pnlResumoLinhas.setLayout(new java.awt.GridBagLayout());

        lblResCliente.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblResCliente.setForeground(new java.awt.Color(17, 24, 39));
        lblResCliente.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(lblResCliente, gridBagConstraints);

        resCliente.setFont(new java.awt.Font("SansSerif", 0, 13));
        resCliente.setForeground(new java.awt.Color(17, 24, 39));
        resCliente.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(resCliente, gridBagConstraints);

        lblResBarbeiro.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblResBarbeiro.setForeground(new java.awt.Color(17, 24, 39));
        lblResBarbeiro.setText("Barbeiro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(lblResBarbeiro, gridBagConstraints);

        resBarbeiro.setFont(new java.awt.Font("SansSerif", 0, 13));
        resBarbeiro.setForeground(new java.awt.Color(17, 24, 39));
        resBarbeiro.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(resBarbeiro, gridBagConstraints);

        lblResServicos.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblResServicos.setForeground(new java.awt.Color(17, 24, 39));
        lblResServicos.setText("Serviços:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(lblResServicos, gridBagConstraints);

        resServicos.setFont(new java.awt.Font("SansSerif", 0, 13));
        resServicos.setForeground(new java.awt.Color(17, 24, 39));
        resServicos.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(resServicos, gridBagConstraints);

        lblResData.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblResData.setForeground(new java.awt.Color(17, 24, 39));
        lblResData.setText("Data:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(lblResData, gridBagConstraints);

        resData.setFont(new java.awt.Font("SansSerif", 0, 13));
        resData.setForeground(new java.awt.Color(17, 24, 39));
        resData.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(resData, gridBagConstraints);

        lblResHorario.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblResHorario.setForeground(new java.awt.Color(17, 24, 39));
        lblResHorario.setText("Horário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(lblResHorario, gridBagConstraints);

        resHorario.setFont(new java.awt.Font("SansSerif", 0, 13));
        resHorario.setForeground(new java.awt.Color(17, 24, 39));
        resHorario.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(resHorario, gridBagConstraints);

        lblResDuracao.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblResDuracao.setForeground(new java.awt.Color(17, 24, 39));
        lblResDuracao.setText("Duração:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(lblResDuracao, gridBagConstraints);

        resDuracao.setFont(new java.awt.Font("SansSerif", 0, 13));
        resDuracao.setForeground(new java.awt.Color(17, 24, 39));
        resDuracao.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlResumoLinhas.add(resDuracao, gridBagConstraints);

        pnlResumo.add(pnlResumoLinhas, java.awt.BorderLayout.CENTER);

        pnlTotal.setOpaque(false);
        pnlTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, Estilo.BORDA));
        pnlTotal.setLayout(new java.awt.BorderLayout());

        lblTotal.setFont(new java.awt.Font("SansSerif", 1, 15));
        lblTotal.setForeground(new java.awt.Color(17, 24, 39));
        lblTotal.setText("Total:");
        lblTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        pnlTotal.add(lblTotal, java.awt.BorderLayout.WEST);

        resTotal.setFont(new java.awt.Font("SansSerif", 1, 18));
        resTotal.setForeground(new java.awt.Color(17, 24, 39));
        resTotal.setText("R$ 0,00");
        resTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        pnlTotal.add(resTotal, java.awt.BorderLayout.EAST);

        pnlResumo.add(pnlTotal, java.awt.BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        pnlColunas.add(pnlResumo, gridBagConstraints);

        pnlConteudo.add(pnlColunas, java.awt.BorderLayout.CENTER);

        pnlRodape.setOpaque(false);
        pnlRodape.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 0, 0, 0));
        pnlRodape.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        btnConfirmar.setText("CONFIRMAR");
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
        pnlRodape.add(btnConfirmar);

        btnCancelar.setText("CANCELAR");
        btnCancelar.setContorno(true);
        btnCancelar.setForeground(new java.awt.Color(17, 24, 39));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        pnlRodape.add(btnCancelar);

        pnlConteudo.add(pnlRodape, java.awt.BorderLayout.SOUTH);

        add(pnlConteudo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClienteActionPerformed
        atualizarResumo();
    }//GEN-LAST:event_cboClienteActionPerformed

    private void cboBarbeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBarbeiroActionPerformed
        carregarHorarios();
        atualizarResumo();
    }//GEN-LAST:event_cboBarbeiroActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        carregarHorarios();
        atualizarResumo();
    }//GEN-LAST:event_txtDataActionPerformed

    private void cboHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHorarioActionPerformed
        atualizarResumo();
    }//GEN-LAST:event_cboHorarioActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        salvar();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        principal.mostrar(TelaPrincipal.AGENDA);
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barbearia.view.Botao btnCancelar;
    private barbearia.view.Botao btnConfirmar;
    private javax.swing.JComboBox<Object> cboBarbeiro;
    private javax.swing.JComboBox<Object> cboCliente;
    private javax.swing.JComboBox<String> cboHorario;
    private javax.swing.JLabel lblBarbeiro;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblResBarbeiro;
    private javax.swing.JLabel lblResCliente;
    private javax.swing.JLabel lblResData;
    private javax.swing.JLabel lblResDuracao;
    private javax.swing.JLabel lblResHorario;
    private javax.swing.JLabel lblResServicos;
    private javax.swing.JLabel lblResumoTitulo;
    private javax.swing.JLabel lblServicos;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlCabecalho;
    private javax.swing.JPanel pnlColunas;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlResumo;
    private javax.swing.JPanel pnlResumoLinhas;
    private javax.swing.JPanel pnlRodape;
    private javax.swing.JPanel pnlServicos;
    private javax.swing.JPanel pnlTitulos;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JLabel resBarbeiro;
    private javax.swing.JLabel resCliente;
    private javax.swing.JLabel resData;
    private javax.swing.JLabel resDuracao;
    private javax.swing.JLabel resHorario;
    private javax.swing.JLabel resServicos;
    private javax.swing.JLabel resTotal;
    private javax.swing.JScrollPane rolObservacao;
    private javax.swing.JScrollPane rolServicos;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables
}
