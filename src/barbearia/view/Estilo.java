package barbearia.view;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

// Cores, fontes e componentes reutilizados por todas as telas (padrão do wireframe)
public final class Estilo {

    public static final Color FUNDO        = new Color(0xF5F6F8);
    public static final Color BRANCO       = Color.WHITE;
    public static final Color LATERAL      = new Color(0x1F2933);
    public static final Color LATERAL_HOVER= new Color(0x323F4B);
    public static final Color PRIMARIA     = new Color(0x2563EB);
    public static final Color PRIMARIA_ESC = new Color(0x1D4ED8);
    public static final Color PERIGO       = new Color(0xDC2626);
    public static final Color SUCESSO      = new Color(0x16A34A);
    public static final Color ALERTA       = new Color(0xD97706);
    public static final Color BORDA        = new Color(0xD5D9E0);
    public static final Color TEXTO        = new Color(0x111827);
    public static final Color TEXTO_SUAVE  = new Color(0x6B7280);

    public static final Font TITULO    = new Font(Font.SANS_SERIF, Font.BOLD, 22);
    public static final Font SUBTITULO = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    public static final Font SECAO     = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    public static final Font NORMAL    = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    public static final Font NEGRITO   = new Font(Font.SANS_SERIF, Font.BOLD, 13);
    public static final Font NUMERO    = new Font(Font.SANS_SERIF, Font.BOLD, 28);

    public static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATA_SQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Estilo() {}

    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        UIManager.put("Panel.background", FUNDO);
        UIManager.put("OptionPane.background", BRANCO);
        UIManager.put("OptionPane.messageFont", NORMAL);
        UIManager.put("Label.font", NORMAL);
        UIManager.put("Button.font", NEGRITO);
        UIManager.put("ComboBox.font", NORMAL);
        UIManager.put("ComboBox.background", BRANCO);
        UIManager.put("CheckBox.font", NORMAL);
        UIManager.put("TextField.font", NORMAL);
        UIManager.put("FormattedTextField.font", NORMAL);
        UIManager.put("PasswordField.font", NORMAL);
        UIManager.put("Table.font", NORMAL);
        UIManager.put("ToolTip.font", NORMAL);
    }

    public static String moeda(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"))
                .format(valor != null ? valor : BigDecimal.ZERO);
    }

    // "agendado" -> "Agendado"
    public static String rotuloStatus(String status) {
        if (status == null || status.isEmpty()) return "";
        String s = status.toLowerCase();
        return switch (s) {
            case "concluido" -> "Concluído";
            default -> Character.toUpperCase(s.charAt(0)) + s.substring(1);
        };
    }

    public static Color corStatus(String status) {
        if (status == null) return TEXTO_SUAVE;
        return switch (status.toLowerCase()) {
            case "confirmado", "concluido", "pago" -> SUCESSO;
            case "cancelado" -> PERIGO;
            default -> ALERTA;
        };
    }

    // Borda usada nos painéis do NetBeans.
    public static Border bordaConteudo() {
        return new CompoundBorder(new LineBorder(BORDA), new EmptyBorder(24, 28, 24, 28));
    }

    // Cartão com contorno arredondado e espaçamento interno
    public static Border bordaCartao(int topo, int esquerda, int baixo, int direita) {
        return new CompoundBorder(new LineBorder(BORDA, 1, true), new EmptyBorder(topo, esquerda, baixo, direita));
    }

    public static Border bordaLinha() {
        return new LineBorder(BORDA);
    }

    public static Border bordaCampo() {
        return new CompoundBorder(new LineBorder(BORDA, 1, true), new EmptyBorder(7, 10, 7, 10));
    }

    public static <T extends JComponent> T campo(T c) {
        c.setBorder(bordaCampo());
        c.setFont(NORMAL);
        c.setBackground(BRANCO);
        Dimension d = c.getPreferredSize();
        c.setPreferredSize(new Dimension(Math.max(d.width, 260), 36));
        c.setMinimumSize(new Dimension(80, 36));
        if (c instanceof javax.swing.text.JTextComponent tc) {
            c.addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) {
                    tc.setBorder(new CompoundBorder(new LineBorder(PRIMARIA, 1, true), new EmptyBorder(7, 10, 7, 10)));
                }
                @Override public void focusLost(FocusEvent e) { tc.setBorder(bordaCampo()); }
            });
        }
        return c;
    }

    public static <T> JComboBox<T> combo(JComboBox<T> c) {
        c.setFont(NORMAL);
        c.setBackground(BRANCO);
        c.setPreferredSize(new Dimension(260, 36));
        c.setMinimumSize(new Dimension(80, 36));
        return c;
    }

    public static JFormattedTextField campoData() {
        JFormattedTextField f = new JFormattedTextField();
        try {
            javax.swing.text.MaskFormatter m = new javax.swing.text.MaskFormatter("##/##/####");
            m.setPlaceholderCharacter('_');
            f.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(m));
        } catch (java.text.ParseException ignored) {}
        f.setFocusLostBehavior(JFormattedTextField.COMMIT);
        return campo(f);
    }

    public static JFormattedTextField campoTelefone() {
        JFormattedTextField f = new JFormattedTextField();
        try {
            javax.swing.text.MaskFormatter m = new javax.swing.text.MaskFormatter("(##) #####-####");
            m.setPlaceholderCharacter('_');
            f.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(m));
        } catch (java.text.ParseException ignored) {}
        f.setFocusLostBehavior(JFormattedTextField.COMMIT);
        return campo(f);
    }

    public static String apenasDigitos(String texto) {
        return texto == null ? "" : texto.replaceAll("\\D", "");
    }

    public static void estilizarTabela(JTable t) {
        t.setRowHeight(40);
        t.setFont(NORMAL);
        t.setShowVerticalLines(false);
        t.setGridColor(new Color(0xECEEF2));
        t.setSelectionBackground(new Color(0xDBEAFE));
        t.setSelectionForeground(TEXTO);
        t.setFillsViewportHeight(true);
        t.setIntercellSpacing(new Dimension(0, 1));
        t.getTableHeader().setReorderingAllowed(false);
        JTableHeader h = t.getTableHeader();
        h.setPreferredSize(new Dimension(h.getPreferredSize().width, 36));
        DefaultTableCellRenderer hr = new DefaultTableCellRenderer();
        hr.setFont(NEGRITO);
        hr.setBackground(new Color(0xF3F4F6));
        hr.setForeground(TEXTO_SUAVE);
        hr.setBorder(new EmptyBorder(0, 12, 0, 12));
        h.setDefaultRenderer((table, value, sel, foc, row, col) -> {
            JLabel l = (JLabel) hr.getTableCellRendererComponent(table, value, false, false, row, col);
            l.setFont(NEGRITO);
            l.setOpaque(true);
            l.setBorder(new EmptyBorder(0, 12, 0, 12));
            return l;
        });
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setBorder(new EmptyBorder(0, 12, 0, 12));
        t.setDefaultRenderer(Object.class, (table, value, sel, foc, row, col) -> {
            JLabel l = (JLabel) r.getTableCellRendererComponent(table, value, sel, false, row, col);
            l.setBorder(new EmptyBorder(0, 12, 0, 12));
            return l;
        });
    }

    // Renderiza a coluna de status com a cor correspondente
    public static void colunaStatus(JTable t, int coluna) {
        t.getColumnModel().getColumn(coluna).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean sel,
                                                           boolean foc, int row, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table,
                        rotuloStatus(value != null ? value.toString() : ""), sel, false, row, col);
                l.setBorder(new EmptyBorder(0, 12, 0, 12));
                l.setFont(NEGRITO);
                l.setForeground(corStatus(value != null ? value.toString() : null));
                return l;
            }
        });
    }

    // Renderiza uma coluna como "link" de ação (DETALHES / EDITAR)
    public static void colunaAcao(JTable t, int coluna, String texto) {
        t.getColumnModel().getColumn(coluna).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean sel,
                                                           boolean foc, int row, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, texto, sel, false, row, col);
                l.setBorder(new EmptyBorder(0, 12, 0, 12));
                l.setFont(NEGRITO);
                l.setForeground(PRIMARIA);
                return l;
            }
        });
        t.getColumnModel().getColumn(coluna).setMaxWidth(120);
        t.getColumnModel().getColumn(coluna).setMinWidth(100);
        t.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override public void mouseMoved(java.awt.event.MouseEvent e) {
                t.setCursor(t.columnAtPoint(e.getPoint()) == coluna
                        ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor());
            }
        });
    }

    public static void estilizarRolagem(JScrollPane s) {
        s.getViewport().setBackground(BRANCO);
        s.getVerticalScrollBar().setUnitIncrement(16);
    }

    public static void erro(Component pai, String msg) {
        JOptionPane.showMessageDialog(pai, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public static void info(Component pai, String msg) {
        JOptionPane.showMessageDialog(pai, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component pai, String msg) {
        return JOptionPane.showConfirmDialog(pai, msg, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    // Mensagem amigável quando o registro está em uso por outra tabela
    public static void erroExclusao(Component pai, String item, java.sql.SQLException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("foreign key constraint")) {
            erro(pai, "Não é possível excluir: este " + item + " está vinculado a um ou mais agendamentos.");
        } else {
            erro(pai, "Erro ao excluir: " + ex.getMessage());
        }
    }

    // Enter aciona o botão padrão, Esc fecha; ajusta o tamanho e centraliza sobre a janela principal
    public static void prepararDialogo(JDialog d, JButton padrao, int larguraMinima) {
        if (padrao != null) d.getRootPane().setDefaultButton(padrao);
        d.getRootPane().registerKeyboardAction(e -> d.dispose(),
                KeyStroke.getKeyStroke("ESCAPE"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        d.pack();
        d.setMinimumSize(new Dimension(larguraMinima, d.getHeight()));
        d.setLocationRelativeTo(d.getOwner());
    }
}
