package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Frame.MakeRoomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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


        //방만들기 버튼 Frame전환 기능이 만들어져야함  !!!
        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button1");
                // LobbyFrame > MakeRoomFrame

                new MakeRoomFrame(); // 새로운 프레임 열기
                setVisible(false);


            }
        });


        //랜덤으로 현재 만들어진 방들 중에서 방에 입장할 수 있어야 함
        button2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 생성자에서 프레임을 받아오는 방식.
                //dispose(); // 현재 프레임 닫기
                new MakeRoomFrame(); // 새로운 프레임 열기
            }
        });

    }

}