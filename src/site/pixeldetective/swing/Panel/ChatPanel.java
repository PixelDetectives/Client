package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.etc.Chat;
import site.pixeldetective.swing.requestApi.ChatApi;
import site.pixeldetective.swing.requestApi.ChattingApi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// LobbyFrame >> LobbyPanel >> ChatPanel 채팅기능을 담당
public class ChatPanel extends JPanel {
    private JPanel chatArea;
    private JTextField chatInputField;
    private JButton sendButton;
    private JLabel nicknameLabel;

    public ChatPanel() {

        ChatApi cApi = new ChatApi();
        ArrayList<Chat> cList = cApi.getChatList();

        setLayout(new BorderLayout());

        // 채팅 내용을 표시할 영역
        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // 사용자 입력 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // 닉네임 라벨 // 로그인시 서버로부터 전달받아와야함
        nicknameLabel = new JLabel("픽셀 탐정1호기 :  ");
        inputPanel.add(nicknameLabel, BorderLayout.WEST);

        // 채팅 입력 필드
        chatInputField = new JTextField();
        inputPanel.add(chatInputField, BorderLayout.CENTER);

        // 전송 버튼
        sendButton = new JButton("전송");
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

    //메서지 전송,Enter 버튼 클릭시 실행될 메서드
    private void sendChat() {

        String nickname = nicknameLabel.getText().trim();
        String message = chatInputField.getText().trim();

        //1.서버에 보내는 채팅 닉네임 채팅 보내는 명령어

        // message의 내용이 없을 경우를 처리함
        if (!message.isEmpty() && message.length() < 30 ) {

            //1.서버에 보내는 채팅 닉네임 채팅 보내는 명령어
            // appendToChatArea(nickname + ": " + message);
            appendToChatArea(nickname + ": " + message);

            //2.보낸 채팅 내용을 지워준다.
            chatInputField.setText("");
            chatInputField.requestFocus();

        } else {
            JOptionPane.showMessageDialog(this, "메시지는 null이 안되고 30자 이하만 가능합니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 처음에 서버에 저장된 모든 chatting를 가져온다
    private void getChatConstruct(){
        // 24시간 동안의 Message내역을 가져오는
        // 서버의 정보를 가져오는 명령어를 호출하는 명령어.

        String[] str = null; // 서버로 채팅 리스트를 가져오는 명령어
        for(int i = 0; i < str.length; i++ ){
            appendToChatArea(str[i]);
        }
    }

    // 메서지를 채팅창에 추가해주는 메서드
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

        ChatPanel chatPanel = new ChatPanel();
        chatPanel.appendToChatArea("호랑나비 : 감자맨");
        frame.add(chatPanel);
        frame.setVisible(true);

    }
}
