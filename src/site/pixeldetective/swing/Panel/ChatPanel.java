package site.pixeldetective.swing.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {
    private JPanel chatArea;
    private JTextField chatInputField;
    private JButton sendButton;
    private JLabel nicknameLabel;

    public ChatPanel() {
        setLayout(new BorderLayout());

        // 채팅 내용을 표시할 영역
        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // 사용자 입력 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // 닉네임 라벨
        nicknameLabel = new JLabel("픽셀 탐정1호기 :  ");
        inputPanel.add(nicknameLabel, BorderLayout.WEST);

        // 채팅 입력 필드
        chatInputField = new JTextField();
        inputPanel.add(chatInputField, BorderLayout.CENTER);

        // 전송 버튼
        sendButton = new JButton("Send");
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // 전송 버튼 클릭 이벤트
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendChat();
            }
        });

        // 채팅 입력 필드에서 Enter 키 이벤트
        chatInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendChat();
            }
        });
    }

    private void sendChat() {
        String nickname = nicknameLabel.getText().trim();
        String message = chatInputField.getText().trim();

        if (!message.isEmpty()) {
            appendToChatArea(nickname + ": " + message);
            chatInputField.setText("");
            chatInputField.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "메시지를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void appendToChatArea(String message) {
        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chatArea.add(messageLabel);
        chatArea.add(Box.createRigidArea(new Dimension(0, 5))); // 메시지 간 간격
        chatArea.revalidate();
        chatArea.repaint();
        JScrollBar vertical = ((JScrollPane) chatArea.getParent().getParent()).getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(505, 388);
        frame.add(new ChatPanel());
        frame.setVisible(true);
    }
}
