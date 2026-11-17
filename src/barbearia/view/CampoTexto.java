package barbearia.view;

import java.awt.*;
import javax.swing.*;

// JTextField com o visual do sistema e texto de exemplo enquanto está vazio
public class CampoTexto extends JTextField {

    private String placeholder = "";

    public CampoTexto() {
        Estilo.campo(this);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && placeholder != null && !placeholder.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(Estilo.TEXTO_SUAVE);
            Insets in = getInsets();
            FontMetrics fm = g2.getFontMetrics(getFont());
            g2.drawString(placeholder, in.left, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
            g2.dispose();
        }
    }
}
