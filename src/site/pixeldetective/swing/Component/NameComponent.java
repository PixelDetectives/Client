package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NameComponent extends JPanel {
    Font customFont;
    public NameComponent(String name) {
        // setBackground(new Color(108, 146, 229));
        // setBackground(new Color(118, 84, 197));
        setBackground(new Color(107, 76, 178));
        setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Color.BLACK));

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        // JLabel 생성
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(customFont);

        nameLabel.setForeground(new Color(255, 255, 255)); // RGB 코드로 텍스트 색상 설정

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
