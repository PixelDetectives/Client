package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.etc.Room;
import site.pixeldetective.swing.requestApi.RoomAPI;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

// LobbyFrame >> LobbyPanel >> GameChoicePanel 게임 선택 기능을 담당
public class GameChoicePanel extends JPanel {

    public SocketClient socketClient;

    public GameChoicePanel() {
        setLayout(new GridBagLayout()); // GridBagLayout 사용
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // 간격 설정 (위, 왼쪽, 아래, 오른쪽)
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int row = 0;
        int col = 0;

         //new RoomAPI().getRoomList();
        ArrayList<Room> rList = null;

        addRoom(new Room(1,1,"한녕하세욘"));

        // 빈 공간을 채우기 위한 패널 추가
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        add(Box.createVerticalGlue(), gbc);
    }

    public void setEmpty(){
        this.removeAll();
    }

    public void addRoom(Room room){

            setLayout(new GridBagLayout()); // GridBagLayout 사용
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10); // 간격 설정 (위, 왼쪽, 아래, 오른쪽)
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.NORTHWEST;

            int row = 0;
            int col = 0;

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
                    case 1:
                        easyCheckBox.setSelected(true);
                        break;
                    case 2:
                        mediumCheckBox.setSelected(true);
                        break;
                    case 3:
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
                System.out.println(room.getrId() + ":방을 플레이어가 선택했습니다. ");
                try {
                    socketClient.joinRoom(room.getrId());
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

                gbc.gridx = col;
                gbc.gridy = row;
                add(roomPanel, gbc);

                col++;
                if (col == 2) {
                    col = 0;
                    row++;
                }
            this.updateUI();
    }


}
