package site.pixeldetective.swing.Frame;

import javax.swing.*;
import java.awt.*;

public class LobbyFrame extends JFrame {

    public LobbyFrame() {
        setTitle("LobbyFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(new BorderLayout(0,0));


        JPanel left = new JPanel();
        left.setLayout(null); // 절대 위치 배치
        left.setPreferredSize(new Dimension(535, 720)); // Preferred size
        left.setSize(535, 720); // Actual size

        JPanel right = new JPanel();
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
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel3.setBounds(20, 20, 715, 155);
        right.add(panel3);

        // 패널 4 설정
        JPanel panel4 = new JPanel();
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel4.setBounds(20, 195, 715, 505); // 패널 4의 크기 조정
        right.add(panel4);

        // 상단 및 하단 패널을 메인 프레임에 추가
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

        //frame.pack()을 호출하여 프레임의 크기를 패널의 선호 크기에 맞게 조정합니다.
        this.pack();
        // 프레임을 보이게 설정
        setVisible(true);

    /*
        System.out.println(panel1.getSize());
        System.out.println(panel2.getSize());
        System.out.println(panel3.getSize());
        System.out.println(panel4.getSize());
        System.out.println(left.getSize());
        System.out.println(right.getSize());
    */

    }

    public static void main(String[] args) {
        new LobbyFrame();
    }
}
