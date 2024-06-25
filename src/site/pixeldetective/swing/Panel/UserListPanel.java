package site.pixeldetective.swing.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserListPanel extends JPanel {

    public UserListPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 제목 패널 설정
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.BLUE);
        JLabel titleLabel = new JLabel("현재 유저");
        titleLabel.setForeground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(505, 50));
        titlePanel.setMinimumSize(new Dimension(505, 50));
        titlePanel.setMaximumSize(new Dimension(505, 50));

        titlePanel.add(titleLabel);

        // 유저 이름 패널 설정
        JPanel userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));
        userListPanel.setBackground(Color.WHITE);

        // 유저 이름 목록
        String[] userNames = {"이재원", "박준수", "김예은", "조영훈", "변시우", "유저6", "유저7", "유저8", "유저9", "유저10", "유저11", "유저12", "유저13", "유저14", "유저15"};

        // 유저 이름 라벨 추가
        for (String userName : userNames) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            userPanel.setOpaque(false);
            userPanel.setPreferredSize(new Dimension(505, 31));
            userPanel.setMinimumSize(new Dimension(505, 31));
            userPanel.setMaximumSize(new Dimension(505, 31));

            JLabel spacer = new JLabel();
            spacer.setPreferredSize(new Dimension(70, 31));
            spacer.setMinimumSize(new Dimension(70, 31));
            spacer.setMaximumSize(new Dimension(70, 31));

            JLabel userLabel = new JLabel(userName);
            userLabel.setOpaque(true); // JLabel 배경 설정을 위해 불투명하게 만듦
            userLabel.setBackground(Color.WHITE); // 배경 색상 설정
            userLabel.setPreferredSize(new Dimension(230, 31));
            userLabel.setMinimumSize(new Dimension(230, 31));
            userLabel.setMaximumSize(new Dimension(230, 31));
            userLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            userLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(userName + " clicked!");
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    userLabel.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    userLabel.setForeground(Color.BLACK);
                }
            });

            userPanel.add(spacer);
            userPanel.add(userLabel);
            userListPanel.add(userPanel);
        }

        // 스크롤 패널 추가
        JScrollPane scrollPane = new JScrollPane(userListPanel);
        scrollPane.setPreferredSize(new Dimension(505, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 패널 추가
        add(titlePanel);
        add(scrollPane);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User List Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(505, 272);
        frame.add(new UserListPanel());
        frame.setVisible(true);
    }
}
