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
import java.io.File;
import java.io.IOException;
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
    Font customFont;
    SocketClient sc;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backgroundImage = new ImageIcon("resource/image/bg01.png"); // 배경 이미지 경로
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public RoomSettingPanel(){
        setLayout(null); // null 레이아웃 설정

//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Malgun Gothic", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        // Main title
        mainTitle = new JLabel("방만들기");
        mainTitle.setFont(customFont.deriveFont(100f));
        mainTitle.setForeground(Color.black); // 폰트 색상 설정
        mainTitle.setBounds(450, 50, 600, 100); // 위치와 크기 설정
        add(mainTitle);

        // Room label
        roomLabel = new JLabel("방제목");
        roomLabel.setFont(customFont.deriveFont(30f));
        roomLabel.setForeground(Color.black); // 폰트 색상 설정
        roomLabel.setBounds(420, 220, 150, 40); // 위치와 크기 설정
        add(roomLabel);

        roomTextField = new JTextField();
        roomTextField.setPreferredSize(new Dimension(280, 50));
        roomTextField.setBounds(550, 220, 300, 40); // 위치와 크기 설정
        add(roomTextField);

        // Difficulty label
        difficultyLabel = new JLabel("난이도 설정");
        difficultyLabel.setFont(customFont.deriveFont(30f));
        difficultyLabel.setForeground(Color.black); // 폰트 색상 설정
        difficultyLabel.setBounds(420, 340, 400, 50); // 위치와 크기 설정
        add(difficultyLabel);

        hardButton = new JRadioButton("어려움");
        hardButton.setFont(customFont.deriveFont(20f));
        hardButton.setForeground(Color.BLACK); // 폰트 색상 설정
        hardButton.setBounds(800, 390, 150, 50); // 위치와 크기 설정
        hardButton.setOpaque(false); // 배경 투명 설정
        add(hardButton);

        normalButton = new JRadioButton("보통");
        normalButton.setFont(customFont.deriveFont(20f));
        normalButton.setForeground(Color.BLACK); // 폰트 색상 설정
        normalButton.setBounds(600, 390, 150, 50); // 위치와 크기 설정
        normalButton.setOpaque(false); // 배경 투명 설정
        add(normalButton);

        easyButton = new JRadioButton("쉬움");
        easyButton.setFont(customFont.deriveFont(20f));
        easyButton.setForeground(Color.BLACK); // 폰트 색상 설정
        easyButton.setBounds(420, 390, 150, 50); // 위치와 크기 설정
        easyButton.setOpaque(false); // 배경 투명 설정
        add(easyButton);

        ButtonGroup group = new ButtonGroup();
        group.add(hardButton);
        group.add(normalButton);
        group.add(easyButton);

//        // Yes, No Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        Font ynBtnFont = new Font("Malgun Gothic", Font.BOLD, 25);

        // Yes button
        yesBtn = new JButton("방만들기");
        yesBtn.setFont(customFont.deriveFont(25f));
        yesBtn.setForeground(Color.BLACK); // 폰트 색상 설정
        yesBtn.setPreferredSize(new Dimension(220, 65));
        yesBtn.setBounds(420, 500, 220, 65); // 위치와 크기 설정
        add(yesBtn);

        // No button
        noBtn = new JButton("취소");
        noBtn.setFont(customFont.deriveFont(25f));
        noBtn.setForeground(Color.BLACK); // 폰트 색상 설정
        noBtn.setPreferredSize(new Dimension(220, 65));
        noBtn.setBounds(650, 500, 220, 65); // 위치와 크기 설정
        add(noBtn);

        // 둥근 모서리 반투명 패널 추가
        RoundedPanel roundedPanel = new RoundedPanel(20, 20);
        roundedPanel.setBounds(380, 200, 600, 100); // 위치와 크기 설정
        add(roundedPanel);

        RoundedPanel roundedPanel2 = new RoundedPanel(20, 20);
        roundedPanel2.setBounds(380, 320, 600, 150); // 위치와 크기 설정
        add(roundedPanel2);

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

                    if(mrf.lobbyFrame.lp.socketClient != null){
                        mrf.lobbyFrame.lp.socketClient.createRoom(roomTextField.getText(),difficultyToInt(selectedButtonText));
                    }else{
                        System.out.println(" mrf.socketClient이 없음");
                        throw new Exception();
                    }
                    // 방 생성 및 매칭 요청
                    //MakeRoomAPI api = new MakeRoomAPI();
                    // api.postRoom(inputRoomTitle, selectedButtonText, 1, 2, 3);
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
                                if (mrf.lobbyFrame.lp.socketClient  != null) {
                                    mrf.lobbyFrame.lp.socketClient .cancelMatching();
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



                    mrf.lobbyFrame.lp.socketClient .createRoom(inputRoomTitle, difficultyToInt(selectedButtonText));
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