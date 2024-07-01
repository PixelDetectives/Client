package site.pixeldetective.swing.Panel;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // 이미지를 로드합니다.
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 배경 이미지를 그립니다.
        g.drawImage(backgroundImage, 1280, 720, getWidth(), getHeight(), this);
    }
}
