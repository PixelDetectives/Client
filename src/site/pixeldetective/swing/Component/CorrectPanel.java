package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CorrectPanel extends JPanel {

    public JLabel correctLabel;
    Font customFont;
    public CorrectPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        setBackground(Color.GREEN);
        correctLabel = new JLabel("", SwingConstants.CENTER);
        setPreferredSize(new Dimension(100, 75));

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        correctLabel.setFont(customFont);
        add(correctLabel, BorderLayout.CENTER);
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
    public void updateLabel(int hits, int total) {
        correctLabel.setText(hits + "/" + total);

    }
}
