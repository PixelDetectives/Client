package site.pixeldetective.swing.Panel;

import javax.swing.*;
import java.awt.*;

// LobbyFrame >> LobbyPanel >> GameButtonPanel 방만들기 빠른매칭 버튼 클릭
public class GameButtonPanel extends JPanel {
    public GameButtonPanel() {
        setLayout(new GridBagLayout()); // 더 유연한 레이아웃 관리

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 25, 25, 25); // 위, 왼쪽, 아래, 오른쪽 간격 설정
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // 공통 폰트 설정 (글자 크기를 24로 설정)
        Font buttonFont = new Font("Arial", Font.BOLD, 30);


        // 첫 번째 버튼 생성 및 설정
        JButton button1 = new JButton("방만들기");
        button1.setPreferredSize(new Dimension(297, 101));
        button1.setFont(buttonFont);
        gbc.gridx = 0; // 첫 번째 버튼의 열 인덱스
        gbc.gridy = 0; // 첫 번째 버튼의 행 인덱스
        add(button1, gbc);

        // 두 번째 버튼 생성 및 설정
        JButton button2 = new JButton("빠른시작");
        button2.setPreferredSize(new Dimension(297, 101));
        button2.setFont(buttonFont);
        gbc.gridx = 1; // 두 번째 버튼의 열 인덱스
        gbc.gridy = 0; // 두 번째 버튼의 행 인덱스
        add(button2, gbc);
    }

}