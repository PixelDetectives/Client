package site.pixeldetective.swing.Panel;


import site.pixeldetective.swing.Frame.LobbyFrame;
import site.pixeldetective.swing.Frame.MakeRoomFrame;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URISyntaxException;

// LobbyFrame >> LobbyPanel 패널 통합 및 관리를 담당
public class LobbyPanel extends JPanel {

    public LobbyFrame lf;
    public String jwt;

    JScrollPane scrollPane;
    UserListPanel panel1;
    public ChatPanel panel2;
    GameButtonPanel panel3;
    GameChoicePanel panel4;
    JPanel right;
    JPanel left;

    public SocketClient socketClient;




    public LobbyPanel() {


        setLayout(null); // 절대 위치 배치로 변경

        left = new JPanel();
        left.setLayout(null); // 절대 위치 배치
        left.setBounds(0, 0, 535, 720); // Absolute positioning

        right = new JPanel();
        right.setLayout(null); // 절대 위치 배치
        right.setBounds(535, 0, 745, 720); // Absolute positioning

        // 패널 1 설정
        panel1 = new UserListPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.setBounds(5, 5, 505, 272); // 패널 크기 조정
        left.add(panel1);

        // 패널 2 설정
        panel2 = new ChatPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel2.setBounds(5, 297, 505, 378); // 패널 2의 크기 조정
        left.add(panel2);

        // 패널 3 설정
        panel3 = new GameButtonPanel();
        panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel3.setBounds(5, 5, 715, 155);
        right.add(panel3);

        // 패널 4 설정
        panel4 = new GameChoicePanel();
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // JScrollPane에 GameChoicePanel 추가
        scrollPane = new JScrollPane(panel4);
        scrollPane.setBounds(5, 180, 715, 495); // JScrollPane 크기 조정
        right.add(scrollPane);

        // 상단 및 하단 패널을 메인 패널에 추가
        add(left);
        add(right);

        socketClient = SocketClient.getInstance();
        socketClient.connect();

        panel2.socketClient = socketClient;
        panel1.socketClient = socketClient;
        panel4.socketClient = socketClient;
        socketClient.chatPanel = panel2;
        socketClient.userListPanel = panel1;
        socketClient.gameChoicePanel = panel4;


        try {
            //socketClient.getCurrentUserList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        panel3.button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lf.changeFrame();
            }
        });

        // 디버깅을 위한 크기 출력
        System.out.println(panel1.getSize());
        System.out.println(panel2.getSize());
        System.out.println(panel3.getSize());
        System.out.println(scrollPane.getSize());
        System.out.println(this.getSize());
        System.out.println(left.getSize());
        System.out.println(right.getSize());
    }

    public void setGameChoicePanel(GameChoicePanel gameChoicePanel) {
        // 기존 JScrollPane 제거
        right.remove(scrollPane);

        // 새로운 GameChoicePanel 설정
        panel4 = gameChoicePanel;
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // 새로운 JScrollPane 생성 및 설정
        scrollPane = new JScrollPane(panel4);
        scrollPane.setBounds(5, 180, 715, 505); // JScrollPane 크기 조정

        // 새로운 JScrollPane 추가
        right.add(scrollPane);

        // 레이아웃 다시 설정
        right.revalidate();
        right.repaint();
    }
}
