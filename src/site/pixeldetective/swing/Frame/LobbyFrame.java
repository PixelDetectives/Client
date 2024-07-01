package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.GameChoicePanel;
import site.pixeldetective.swing.Panel.LobbyPanel;
import site.pixeldetective.swing.etc.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyFrame extends JFrame {

    String jwt;
    public LobbyPanel lp;
    public GameFrame gameFrame;

    public MakeRoomFrame makeRoomFrame;
    public LobbyFrame() {
        lp = new LobbyPanel();

        setTitle("LobbyFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        lp.lf = this;
        add(lp);
        setVisible(false);
        makeRoomFrame = new MakeRoomFrame();
        gameFrame = new GameFrame();
    }

    public void changeFrame() {
        Point location = this.getLocation();
        Dimension size = this.getSize();
        makeRoomFrame.setLocation(location);
        makeRoomFrame.setSize(size);
        makeRoomFrame.setVisible(true);
        this.setVisible(false);
    }


    public static void main(String[] args) {
        new LobbyFrame();
    }

    public void setGameChoicePanel(GameChoicePanel gameChoicePanel) {

        ( (LobbyPanel) getContentPane().getComponent(0)).setGameChoicePanel(gameChoicePanel);
    }
}
