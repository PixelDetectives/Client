package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CurrentBoardPanel extends JPanel {

    public JLabel hitLabel;
    public JLabel missLabel;
    public JLabel totalLabel;

    public CurrentBoardPanel() {
        setOpaque(false);
        setLayout(new GridLayout(3,2, 0, 10));
        setPreferredSize(new Dimension(100, 200));

        JLabel label1 = new JLabel("Hit");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.PLAIN, 28));
        add(label1);

        hitLabel = new JLabel("0");
        hitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hitLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        add(hitLabel);

        JLabel label3 = new JLabel("Miss");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Arial", Font.PLAIN, 28));
        add(label3);

        missLabel = new JLabel("0");
        missLabel.setHorizontalAlignment(SwingConstants.CENTER);
        missLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        add(missLabel);

        JLabel label5 = new JLabel("Total");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Arial", Font.PLAIN, 28));
        add(label5);

        totalLabel = new JLabel("0");
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        add(totalLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D 객체 생성
        Graphics2D g2d = (Graphics2D) g.create();

        // 라운드된 네모 형태의 배경 그리기
        int width = getWidth();
        int height = getHeight();
        int arcWidth = 20; // 라운드의 폭
        int arcHeight = 20; // 라운드의 높이

        g2d.setColor(new Color(255, 255, 255, 150)); // 배경색 (투명도 설정)
        g2d.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);

        g2d.dispose();
    }
    public void updateLabel(int hits, int miss, int total) {
        hitLabel.setText(hits + "");
        missLabel.setText(miss + "");
        totalLabel.setText(total + "");
    }
}
