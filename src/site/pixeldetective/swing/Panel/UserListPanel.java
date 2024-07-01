package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.etc.User;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URISyntaxException;

// LobbyFrame >> LobbyPanel >> UserListPanel 현재 가입 회원 리스트를 담당
public class UserListPanel extends JPanel {

    public SocketClient socketClient;



    JPanel userListPanel;
    public UserListPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 제목 패널 설정
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.BLUE);
        JLabel titleLabel = new JLabel("현재 유저");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
        titlePanel.setPreferredSize(new Dimension(505, 50));
        titlePanel.setMinimumSize(new Dimension(505, 50));
        titlePanel.setMaximumSize(new Dimension(505, 50));
        titlePanel.add(titleLabel);

        // 유저 이름 패널 설정
        userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));
        userListPanel.setBackground(Color.WHITE);

        setUserList(new User(123,"한수엽","join"));
        setUserList(new User(123,"한수엽","join"));
        setUserList(new User(123,"한수엽","join"));


        // 스크롤 패널 추가
        JScrollPane scrollPane = new JScrollPane(userListPanel);
        scrollPane.setPreferredSize(new Dimension(505, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 패널 추가
        add(titlePanel);
        add(scrollPane);
    }

    public void setEmpty(){
        userListPanel.removeAll();
    }

    public void setUserList(User user) {
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
          this.updateUI();

    }public static void main(String[] args) {
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

        // Add users to the list
        userListPanel.setUserList(new User(123, "User 1", "join"));
        userListPanel.setUserList(new User(124, "User 2", "join"));
        userListPanel.setUserList(new User(125, "User 3", "join"));
    }



}
