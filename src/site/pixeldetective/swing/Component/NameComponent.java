package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;

public class NameComponent extends JPanel {
    public NameComponent(String name) {
        setBackground(Color.CYAN);
        setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Color.BLACK));
        // JLabel 생성
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // GridBagLayout 및 Constraints 설정
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 가운데 열
        gbc.gridy = 0; // 가운데 행
        gbc.weightx = 1.0; // 가로 방향으로 공간을 최대한 사용
        gbc.weighty = 1.0; // 세로 방향으로 공간을 최대한 사용
        gbc.anchor = GridBagConstraints.CENTER; // 가운데 정렬

        // JLabel 추가
        add(nameLabel, gbc);
    }
}
