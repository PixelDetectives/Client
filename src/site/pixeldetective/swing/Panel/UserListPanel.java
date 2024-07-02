package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.etc.User;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

// LobbyFrame >> LobbyPanel >> UserListPanel 현재 가입 회원 리스트를 담당
public class UserListPanel extends JPanel {

    public SocketClient socketClient;

    JPanel userListPanel;
    JLabel userLabel;
    Font customFont;
    public UserListPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }


        // 제목 패널 설정
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(107, 76, 178));
        JLabel titleLabel = new JLabel("현재 유저");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(customFont);
        titlePanel.setPreferredSize(new Dimension(505, 50));
        titlePanel.setMinimumSize(new Dimension(505, 50));
        titlePanel.setMaximumSize(new Dimension(505, 50));
        titlePanel.add(titleLabel);

        // 유저 이름 패널 설정
        userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));
        userListPanel.setBackground(Color.WHITE);

        // 스크롤 패널 추가
        JScrollPane scrollPane = new JScrollPane(userListPanel);
        scrollPane.setPreferredSize(new Dimension(505, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 패널 추가
        add(titlePanel);
        add(scrollPane);
    }

    public void setEmpty() {
        userListPanel.removeAll();
        userListPanel.revalidate();
        userListPanel.repaint();
    }

    public void setUserList(User user) {
        System.out.println("setUserList");
        System.out.println(user.getuName());
        String userName = user.getuName();
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
        JLabel userId = new JLabel(userName);
        userId.setVisible(false);
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
        userListPanel.revalidate(); // 레이아웃 매니저가 변경사항을 반영하도록 재요청
        userListPanel.repaint(); // 패널을 다시 그리도록 요청
    }

    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("User List Test");

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of UserListPanel
        UserListPanel userListPanel = new UserListPanel();

        // Add the UserListPanel to the frame
        frame.add(userListPanel);

        // Set the frame size
        frame.setSize(600, 400);

        // Set the frame to be visible
        frame.setVisible(true);

        // Test clearing the user list and adding new users
        userListPanel.setEmpty(); // This should clear the list

        // Add users to the list
        userListPanel.setUserList(new User(123, "User 1", "join"));
        userListPanel.setEmpty();
        userListPanel.setUserList(new User(124, "User 2", "join"));
        userListPanel.setUserList(new User(125, "User 3", "join"));
    }
}
