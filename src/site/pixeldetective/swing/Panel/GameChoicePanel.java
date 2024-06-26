package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.etc.Room;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

// LobbyFrame >> LobbyPanel >> GameChoicePanel 게임 선택 기능을 담당
public class GameChoicePanel extends JPanel {

    public GameChoicePanel() {
    }

    public GameChoicePanel(List<Room> rooms) {
        setLayout(new GridBagLayout()); // GridBagLayout 사용
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 간격 설정
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int row = 0;
        int col = 0;

        for (Room room : rooms) {
            JPanel roomPanel = new JPanel();
            roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
            roomPanel.setBorder(new LineBorder(Color.BLACK, 1));

            Dimension fixedSize = new Dimension(340, 100);
            roomPanel.setPreferredSize(fixedSize);
            roomPanel.setMinimumSize(fixedSize);
            roomPanel.setMaximumSize(fixedSize);

            roomPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel(room.getTitle());
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

            switch (room.getDifficulty()) {
                case "쉬움":
                    easyCheckBox.setSelected(true);
                    break;
                case "중간":
                    mediumCheckBox.setSelected(true);
                    break;
                case "어려움":
                    hardCheckBox.setSelected(true);
                    break;
            }

            difficultyPanel.add(easyCheckBox);
            difficultyPanel.add(mediumCheckBox);
            difficultyPanel.add(hardCheckBox);
            roomPanel.add(difficultyPanel);




            gbc.gridx = col;
            gbc.gridy = row;
            add(roomPanel, gbc);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }
}
