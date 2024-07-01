package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.DrawingPanel;
import site.pixeldetective.swing.Panel.GamePanel;
import site.pixeldetective.swing.Panel.GameResult;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public LobbyFrame lf;
    public MakeRoomFrame mk;
    public GameFrame() {
        super("Game");
        try {
            setSize(1280, 720);
            setLocationRelativeTo(null);
            System.out.println("Creating GameFrame...");
            GamePanel gp = new GamePanel();
            gp.gf = this;
            add(gp);
            setVisible(false);
            System.out.println("GameFrame created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}