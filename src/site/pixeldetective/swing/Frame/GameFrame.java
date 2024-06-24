package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.DrawingPanel;
import site.pixeldetective.swing.Panel.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("Game");
        setSize(1280, 720);
        setLocationRelativeTo(null);

        add(new GamePanel());
        setVisible(true);
    }
}
