package site.pixeldetective.swing.Panel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButtonUI extends BasicButtonUI {

    private Color backgroundColor;
    private Color textColor;
    private int cornerRadius;

    public RoundedButtonUI(Color backgroundColor, Color textColor, int cornerRadius) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(textColor); // 외부에서 설정한 전경색 유지
        // button.setFont(button.getFont().deriveFont(Font.BOLD, 16f)); // 이 부분을 주석 처리하여 외부에서 설정한 폰트 유지
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = b.getWidth();
        int height = b.getHeight();

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        super.paint(g, c);
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = g.getFontMetrics();

        g.setColor(textColor);
        g.drawString(text, (b.getWidth() - fm.stringWidth(text)) / 2, (b.getHeight() + fm.getAscent()) / 2);
    }
}