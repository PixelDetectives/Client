package site.pixeldetective.swing.Panel;

// LobbyFrame 프레임의 빠른시작 방만들기 패널

import javax.swing.*;
import java.awt.*;

public class GameButtonPanel extends JPanel {
    public GameButtonPanel() {
        setLayout(new GridLayout(1,2)); // 수직 레이아웃 설정

        // 첫 번째 버튼 생성 및 설정
        JButton button1 = new JButton("방만들기");
        button1.setPreferredSize(new Dimension(297, 101));
        button1.setMaximumSize(new Dimension(297, 101));
        button1.setMinimumSize(new Dimension(297, 101));
        button1.setAlignmentX(CENTER_ALIGNMENT); // 버튼 중앙 정렬

        // 두 번째 버튼 생성 및 설정
        JButton button2 = new JButton("빠른시작");
        button2.setPreferredSize(new Dimension(297, 101));
        button2.setMaximumSize(new Dimension(297, 101));
        button2.setMinimumSize(new Dimension(297, 101));
        button2.setAlignmentX(CENTER_ALIGNMENT); // 버튼 중앙 정렬

        // 패널에 버튼 추가 및 간격 설정
        add(button1);
        add(Box.createVerticalStrut(25)); // 버튼 간의 간격 설정
        add(button2);

        System.out.println(button1.getSize());
        System.out.println(button2.getSize());
    }

}
