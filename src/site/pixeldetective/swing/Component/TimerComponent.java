package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;

public class TimerComponent extends JPanel {
    public String time ;
    public JLabel timeLabel;
    public TimerComponent() {
        setOpaque(false);

        setLayout(new BorderLayout());
        setBackground(Color.YELLOW);
        this.timeLabel = new JLabel(time, SwingConstants.CENTER);
        this.timeLabel.setFont(new Font("Serif", Font.BOLD, 24));
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
