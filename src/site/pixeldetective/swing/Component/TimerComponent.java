package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TimerComponent extends JPanel {
    public String time ;
    public JLabel timeLabel;
    Font customFont;
    public TimerComponent() {
        setOpaque(false);

        setLayout(new BorderLayout());
        setBackground(Color.YELLOW);
        this.timeLabel = new JLabel(time, SwingConstants.CENTER);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        this.timeLabel.setFont(customFont);
        this.timeLabel.setForeground(Color.BLACK); // 폰트 색상 설정

        // timeLabel을 추가하기 전에 TimerComponent의 크기를 설정
        setPreferredSize(new Dimension(100, 75));

        add(timeLabel, BorderLayout.CENTER);
    }
    // timeLabel의 텍스트를 업데이트하는 메서드 추가
    public void updateTime(String time) {
        this.timeLabel.setText(time);
    }
}
