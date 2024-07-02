package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.etc.Room;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class GameChoicePanel extends JPanel {

    public SocketClient socketClient;
    private int row = 0;
    private int col = 0;
    private Component verticalGlue;
    private JLabel noRoomsLabel;
    Font customFont;
    public GameChoicePanel() {
        setLayout(new GridBagLayout());
        verticalGlue = Box.createVerticalGlue();
        addVerticalGlue();

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        noRoomsLabel = new JLabel("현재 방이 없습니다");
        noRoomsLabel.setFont(customFont.deriveFont(20f));
        noRoomsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noRoomsLabel.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; // 패널의 가운데에 위치하도록 설정
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 한 줄에 하나의 컴포넌트만 있도록 설정
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 수평 방향으로 컴포넌트를 채움
        add(noRoomsLabel, gbc);
    }

    private void addVerticalGlue() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(verticalGlue, gbc);
    }

    public void setEmpty() {
        this.removeAll();
        row = 0;
        col = 0;
        noRoomsLabel.setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(noRoomsLabel, gbc);
        addVerticalGlue();
        revalidate();
        repaint();
    }

    public void addRoom(Room room) {
        noRoomsLabel.setVisible(false);
        remove(verticalGlue);

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
        roomPanel.setBorder(new LineBorder(Color.BLACK, 1));

        Dimension fixedSize = new Dimension(340, 100);
        roomPanel.setPreferredSize(fixedSize);
        roomPanel.setMinimumSize(fixedSize);
        roomPanel.setMaximumSize(fixedSize);

        roomPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(room.getrName());
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        roomPanel.add(titleLabel);

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new FlowLayout());
        difficultyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        difficultyPanel.setBackground(Color.WHITE);

        JCheckBox easyCheckBox = new JCheckBox("쉬움");
        JCheckBox mediumCheckBox = new JCheckBox("중간");
        JCheckBox hardCheckBox = new JCheckBox("어려움");

        easyCheckBox.setEnabled(false);
        mediumCheckBox.setEnabled(false);
        hardCheckBox.setEnabled(false);

        switch (room.getrDifficulty()) {
            case 0:
                easyCheckBox.setSelected(true);
                break;
            case 1:
                mediumCheckBox.setSelected(true);
                break;
            case 2:
                hardCheckBox.setSelected(true);
                break;
        }

        difficultyPanel.add(easyCheckBox);
        difficultyPanel.add(mediumCheckBox);
        difficultyPanel.add(hardCheckBox);
        roomPanel.add(difficultyPanel);

        roomPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시 실행될 코드
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 마우스 버튼을 누를 때 실행될 코드
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // 마우스 버튼을 놓을 때 실행될 코드
                System.out.println(room.getrId() + ": 방을 플레이어가 선택했습니다.");
                try {
                    socketClient.joinRoom(room.getrId());
                    // Frame 전환 혹인 모달 띄우기

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스가 컴포넌트에 들어올 때 실행될 코드
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 컴포넌트에서 나갈 때 실행될 코드
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // 간격 설정 (위, 왼쪽, 아래, 오른쪽)
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = col;
        gbc.gridy = row;
        add(roomPanel, gbc);

        col++;
        if (col == 2) {
            col = 0;
            row++;
        }

        addVerticalGlue();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameChoicePanel gameChoicePanel = new GameChoicePanel();
        frame.add(gameChoicePanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // 예시로 빈 방 패널을 표시
        gameChoicePanel.setEmpty();

        // 예시로 몇 개의 방을 추가
        gameChoicePanel.addRoom(new Room(2, 2, "테스트 방 1"));
        // gameChoicePanel.addRoom(new Room(3, 3, "테스트 방 2"));
        // gameChoicePanel.addRoom(new Room(4, 1, "테스트 방 3"));
        // gameChoicePanel.addRoom(new Room(5, 2, "테스트 방 4"));
    }
}
