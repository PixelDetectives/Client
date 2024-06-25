package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.GameButtonPanel;
import site.pixeldetective.swing.Panel.GameChoicePanel;
import site.pixeldetective.swing.etc.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyFrame extends JFrame {

    JScrollPane scrollPane;
    GameChoicePanel panel4;
    JPanel right;
    JPanel left;

    public LobbyFrame() {
        setTitle("LobbyFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(new BorderLayout(0,0));

        left = new JPanel();
        left.setLayout(null); // 절대 위치 배치
        left.setPreferredSize(new Dimension(535, 720)); // Preferred size
        left.setSize(535, 720); // Actual size

        right = new JPanel();
        right.setLayout(null); // 절대 위치 배치
        right.setPreferredSize(new Dimension(745, 720)); // Preferred size
        right.setSize(745, 720); // Actual size

        // 패널 1 설정
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.setBounds(20, 20, 505, 272); // 패널 크기 조정
        left.add(panel1);

        // 패널 2 설정
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel2.setBounds(20, 312, 505, 388); // 패널 2의 크기 조정
        left.add(panel2);

        // 패널 3 설정
        GameButtonPanel panel3 = new GameButtonPanel();
        panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel3.setBounds(20, 20, 715, 155);
        right.add(panel3);

        // 패널 4 설정
        panel4 = new GameChoicePanel();
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // JScrollPane에 GameChoicePanel 추가
        scrollPane = new JScrollPane(panel4);
        scrollPane.setBounds(20, 195, 715, 505); // JScrollPane 크기 조정
        right.add(scrollPane);

        // 상단 및 하단 패널을 메인 프레임에 추가
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

        // 프레임을 보이게 설정
        setVisible(true);
        System.out.println(panel3.getSize());
    }

    public void setGameChoicePanel(GameChoicePanel gameChoicePanel) {
        // 기존 JScrollPane 제거
        right.remove(scrollPane);

        // 새로운 GameChoicePanel 설정
        panel4 = gameChoicePanel;
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // 새로운 JScrollPane 생성 및 설정
        scrollPane = new JScrollPane(panel4);
        scrollPane.setBounds(20, 195, 715, 505); // JScrollPane 크기 조정

        // 새로운 JScrollPane 추가
        right.add(scrollPane);

        // 레이아웃 다시 설정
        right.revalidate();
        right.repaint();
    }

    public static void main(String[] args) {
        ArrayList<Room> rArr = new ArrayList<>();
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));
        rArr.add(new Room("나의 첫 게임", "중간"));

        LobbyFrame frame = new LobbyFrame();
        frame.setGameChoicePanel(new GameChoicePanel(rArr));
    }
}
