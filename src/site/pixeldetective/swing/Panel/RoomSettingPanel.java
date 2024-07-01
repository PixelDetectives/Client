package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Frame.LobbyFrame;
import site.pixeldetective.swing.Frame.MakeRoomFrame;
import site.pixeldetective.swing.requestApi.MakeRoomAPI;
import site.pixeldetective.swing.webSocketClient.SocketClient;
import site.pixeldetective.swing.webSocketClient.SocketClient2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class RoomSettingPanel extends JPanel{
    public MakeRoomFrame mrf;
    private JLabel mainTitle;
    private JLabel roomLabel;
    private JTextField roomTextField;
    private JLabel difficultyLabel;
    private JRadioButton hardButton;
    private JRadioButton normalButton;
    private JRadioButton easyButton;
    private JButton yesBtn;
    private JButton noBtn;

    SocketClient sc;


    public RoomSettingPanel(){
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Main title
        mainTitle = new JLabel("방만들기");
        mainTitle.setFont(new Font("Malgun Gothic", Font.BOLD, 100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(mainTitle, gbc);

        // Room panel
        JPanel roomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints roomGbc = new GridBagConstraints();

        roomLabel = new JLabel("방제목");
        roomLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        roomLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 30));
        roomPanel.setBorder(new EmptyBorder(20, 22, 20, 22));
        roomPanel.setBackground(Color.LIGHT_GRAY);

        roomGbc.gridx = 0;
        roomGbc.gridy = 0;
        roomGbc.anchor = GridBagConstraints.EAST;
        roomPanel.add(roomLabel, roomGbc);

        roomTextField = new JTextField();
        roomTextField.setPreferredSize(new Dimension(280, 50));
        roomTextField.setBorder(new LineBorder(Color.WHITE));
        roomGbc.gridx = 1;
        roomGbc.gridy = 0;
        roomGbc.anchor = GridBagConstraints.WEST;
        roomPanel.add(roomTextField, roomGbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        add(roomPanel, gbc);

        // Difficulty panel
        JPanel difficultyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints difficultyGbc = new GridBagConstraints();

        difficultyPanel.setBorder(new EmptyBorder(10, 27, 20, 27));
        difficultyPanel.setBackground(Color.LIGHT_GRAY);

        difficultyLabel = new JLabel("난이도 설정");
        difficultyLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 40));
        difficultyLabel.setBorder(new EmptyBorder(0, 0, 20, 160));

        difficultyGbc.gridx = 0;
        difficultyGbc.gridy = 0;
        difficultyGbc.gridwidth = 3;
        difficultyGbc.anchor = GridBagConstraints.CENTER;
        difficultyPanel.add(difficultyLabel, difficultyGbc);

        hardButton = new JRadioButton("어려움");
        hardButton.setBorder(new EmptyBorder(25,10,25,10));
        normalButton = new JRadioButton("보통");
        normalButton.setBorder(new EmptyBorder(25,10,25,10));
        easyButton = new JRadioButton("쉬움");
        easyButton.setBorder(new EmptyBorder(25,10,25,10));

        Font radioFont = new Font("Malgun Gothic", Font.BOLD, 40);
        hardButton.setFont(radioFont);
        normalButton.setFont(radioFont);
        easyButton.setFont(radioFont);

        ButtonGroup group = new ButtonGroup();
        group.add(hardButton);
        group.add(normalButton);
        group.add(easyButton);

        difficultyGbc.gridwidth = 1;
        difficultyGbc.gridx = 0;
        difficultyGbc.gridy = 1;
        difficultyGbc.anchor = GridBagConstraints.EAST;
        difficultyPanel.add(hardButton, difficultyGbc);

        difficultyGbc.gridx = 1;
        difficultyGbc.gridy = 1;
        difficultyGbc.anchor = GridBagConstraints.CENTER;
        difficultyPanel.add(normalButton, difficultyGbc);

        difficultyGbc.gridx = 2;
        difficultyGbc.gridy = 1;
        difficultyGbc.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(easyButton, difficultyGbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(difficultyPanel, gbc);

        // Yes, No Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        Font ynBtnFont = new Font("Malgun Gothic", Font.BOLD, 25);

        yesBtn = new JButton("방만들기");
        yesBtn.setPreferredSize(new Dimension(220,65));
        yesBtn.setBorder(new LineBorder(Color.LIGHT_GRAY));
        yesBtn.setBackground(Color.LIGHT_GRAY);
        yesBtn.setFont(ynBtnFont);

        noBtn = new JButton("취소");
        noBtn.setPreferredSize(new Dimension(220,65));
        noBtn.setBorder(new LineBorder(Color.LIGHT_GRAY));
        noBtn.setBackground(Color.LIGHT_GRAY);
        noBtn.setFont(ynBtnFont);

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // 방 제목 입력 길이를 10자로 제한함
        roomTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (roomTextField.getText().length() >= 10) {
                    e.consume(); // 이벤트 소비 (입력 무시)
                }
            }
        });

        yesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedButtonText = getSelectedButtonText(group);
                String inputRoomTitle = roomTextField.getText();

                if (inputRoomTitle.isEmpty()){
                    JOptionPane.showMessageDialog(null,"방제목을 입력해주세요", "확인", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (selectedButtonText == null){
                    JOptionPane.showMessageDialog(null,"난이도를 선택해주세요", "확인", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                    // 방 생성 및 매칭 요청
                    MakeRoomAPI api = new MakeRoomAPI();
                    api.postRoom(inputRoomTitle, selectedButtonText, 1, 2, 3);
                    System.out.println("방이름 : " + roomTextField.getText() + "\n난이도 : " + selectedButtonText);

                    // 매칭 중 상태를 표시하는 모달 다이얼로그
                    JDialog matchingDialog = new JDialog((Frame) null, "매칭중", true);
                    matchingDialog.setLayout(new BorderLayout());

                    JLabel matchingLabel = new JLabel("매칭중 입니다 ...", SwingConstants.CENTER);
                    matchingDialog.add(matchingLabel, BorderLayout.CENTER);

                    JButton cancelButton = new JButton("매칭 취소");
                    matchingDialog.add(cancelButton, BorderLayout.SOUTH);

                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                if (sc != null) {
                                    sc.cancelMatching();
                                }
                                matchingDialog.dispose();
                                mrf.setVisible(true); // MakeRoomFrame 다시 표시
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    matchingDialog.setSize(300, 200);
                    matchingDialog.setLocationRelativeTo(null);
                    matchingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    matchingDialog.setVisible(true);

                    // rooName
                    // difficulty


                    // 웹소켓을 통해 매칭 요청
                    sc = new SocketClient();
                    sc.connect();
                    sc.createRoom(inputRoomTitle, difficultyToInt(selectedButtonText));
//                    sc.setMatchingDialog(matchingDialog);
//                    sc.waitForMatch();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

//                MakeRoomAPI api = new MakeRoomAPI();
//                api.postRoom(inputRoomTitle,selectedButtonText , 1, 2, 3);
//                System.out.println("방이름 : "+roomTextField.getText()+"\n난이도 : "+selectedButtonText);
//
//                JOptionPane.showMessageDialog(null, "매칭중 입니다 ...", "매칭중", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        noBtn.addActionListener(e -> mrf.changeFrame());
    }

    // 선택된 라디오 버튼의 텍스트를 리턴
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private int difficultyToInt(String difficulty) {
        switch (difficulty) {
            case "어려움":
                return 3;
            case "보통":
                return 2;
            case "쉬움":
                return 1;
            default:
                return 0;
        }
    }


}