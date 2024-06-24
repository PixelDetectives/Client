package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;

public class TimerComponent extends JPanel {
    public String time ;
    public JLabel timeLabel;
    public TimerComponent() {

        setLayout(new BorderLayout());
        setBackground(Color.YELLOW);
        this.timeLabel = new JLabel(time, SwingConstants.CENTER);
        this.timeLabel.setFont(new Font("Serif", Font.BOLD, 24));

        // timeLabel을 추가하기 전에 TimerComponent의 크기를 설정
        setPreferredSize(new Dimension(100, 75));

        add(timeLabel, BorderLayout.CENTER);
    }
}
