package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Frame.MakeRoomFrame;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// LobbyFrame >> LobbyPanel >> GameButtonPanel 방만들기 빠른매칭 버튼 클릭
public class GameButtonPanel extends JPanel {
    JButton button1;
    Font customFont;
    public GameButtonPanel() {
        setLayout(new GridBagLayout()); // 더 유연한 레이아웃 관리
        setOpaque(false); // 패널을 투명하게 설정
        setBorder(null); // 패널의 border를 제거

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 25, 25, 25); // 위, 왼쪽, 아래, 오른쪽 간격 설정
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        Color backgroundColor1 = new Color(37, 46, 145); // White
        Color textColor1 = new Color(255, 255, 255); // Black

        // 첫 번째 버튼 생성 및 설정
        button1 = new JButton("방만들기");
        button1.setPreferredSize(new Dimension(297, 100));
        button1.setFont(customFont.deriveFont(30f)); // 폰트 크기 설정
        button1.setForeground(Color.WHITE); // 버튼의 전경색(글자색)
        button1.setBackground(Color.black); // 버튼의 배경색
        button1.setBorderPainted(false); // 버튼의 테두리 여부
        button1.setFocusPainted(false); // 버튼의 포커스 표시 여부
        button1.setUI(new RoundedButtonUI(backgroundColor1, textColor1, 30));
        gbc.gridx = 0; // 첫 번째 버튼의 열 인덱스
        gbc.gridy = 0; // 첫 번째 버튼의 행 인덱스
        add(button1, gbc);

        // RGB로 지정된 색상
        Color backgroundColor2 = new Color(255, 128, 134); // White
        Color textColor2 = new Color(255, 255, 255); // Black

        // 두 번째 버튼 생성 및 설정
        JButton button2 = new JButton("빠른매칭");
        button2.setPreferredSize(new Dimension(297, 100));
        button2.setFont(customFont.deriveFont(30f)); // 폰트 크기 설정
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.black);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setUI(new RoundedButtonUI(backgroundColor2, textColor2, 30)); // 글자, 배경
        gbc.gridx = 1; // 두 번째 버튼의 열 인덱스
        gbc.gridy = 0; // 두 번째 버튼의 행 인덱스
        add(button2, gbc);

        //방만들기 버튼 Frame전환 기능이 만들어져야함  !!!
        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button1");




            }
        });


        //랜덤으로 현재 만들어진 방들 중에서 방에 입장할 수 있어야 함
        button2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 생성자에서 프레임을 받아오는 방식.
                //dispose(); // 현재 프레임 닫기
//                new MakeRoomFrame(); // 새로운 프레임 열기
                System.out.println("빠른 시작");
                try {
                    SocketClient.getInstance().quickMatching();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        });

    }

}