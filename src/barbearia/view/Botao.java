package barbearia.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Botão plano arredondado usado nos .form (pode ser arrastado da paleta do NetBeans).
// Preenchido: pinta com a cor de fundo (background). Contorno: fundo branco e borda na cor do texto.
public class Botao extends JButton {

    private boolean contorno;
    private boolean hover;

    public Botao() {
        this("Botão");
    }

    public Botao(String texto) {
        super(texto);
        setFont(Estilo.NEGRITO);
        setBackground(Estilo.PRIMARIA);
        setForeground(Estilo.BRANCO);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(9, 20, 9, 20));
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { hover = true; repaint(); }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { hover = false; repaint(); }
        });
    }

    public boolean isContorno() {
        return contorno;
    }

    public void setContorno(boolean contorno) {
        this.contorno = contorno;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (contorno) {
            g2.setColor(hover && isEnabled() ? new Color(0xF3F4F6) : Estilo.BRANCO);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            g2.setColor(isEnabled() ? getForeground() : Estilo.BORDA);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        } else {
            Color base = isEnabled() ? getBackground() : Estilo.BORDA;
            g2.setColor(hover && isEnabled() ? base.darker() : base);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
