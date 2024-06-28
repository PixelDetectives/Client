package site.pixeldetective.swing.webSocketClient;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

public class Main {
    private static SocketClient webSocketClient;
    private static JTextArea messageArea;
    private static JTextField nicknameField;
    private static JTextField chatMessageField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("WebSocket Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        frame.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);

        JButton connectButton = new JButton("Connect");
        panel.add(connectButton);

        JButton helloButton = new JButton("Send Hello");
        panel.add(helloButton);

        JButton userCountButton = new JButton("Get User Count");
        panel.add(userCountButton);

        nicknameField = new JTextField(10);
        nicknameField.setText("nickname");
        panel.add(nicknameField);

        chatMessageField = new JTextField(20);
        chatMessageField.setText("This is a chat message");
        panel.add(chatMessageField);

        JButton chatButton = new JButton("Send Chat");
        panel.add(chatButton);



        connectButton.addActionListener(e -> {
            try {
                webSocketClient = new SocketClient();
                webSocketClient.connect();
                messageArea.append("Connecting to WebSocket server...\n");
            } catch (URISyntaxException ex) {
                messageArea.append("Invalid WebSocket URI\n");
            }
        });

        helloButton.addActionListener(e -> {
            if (webSocketClient != null && webSocketClient.isOpen()) {
                try {
                    String response = webSocketClient.sendHello();
                    messageArea.append("Received response: " + response + "\n");
                } catch (Exception ex) {
                    messageArea.append("Error sending hello: " + ex.getMessage() + "\n");
                }
            } else {
                messageArea.append("WebSocket connection is not open\n");
            }
        });

        userCountButton.addActionListener(e -> {
            if (webSocketClient != null && webSocketClient.isOpen()) {
                try {
                    webSocketClient.sendGetUserCount();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                messageArea.append("Sent: getUserCount\n");
            } else {
                messageArea.append("WebSocket connection is not open\n");
            }
        });

        chatButton.addActionListener(e -> {
            if (webSocketClient != null && webSocketClient.isOpen()) {
                try {
                    String nickname = nicknameField.getText();
                    String chatMessage = chatMessageField.getText();
                    String response = webSocketClient.sendChatMessage(nickname, chatMessage);
                    messageArea.append("Received response: " + response + "\n");
                } catch (Exception ex) {
                    messageArea.append("Error sending chat message: " + ex.getMessage() + "\n");
                }
            } else {
                messageArea.append("WebSocket connection is not open\n");
            }
        });

        frame.setVisible(true);
    }
}
