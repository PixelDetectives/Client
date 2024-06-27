package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.GameChoicePanel;
import site.pixeldetective.swing.Panel.LobbyPanel;
import site.pixeldetective.swing.etc.Room;

import javax.swing.*;
import java.util.ArrayList;

public class LobbyFrame extends JFrame {
    public MakeRoomFrame makeRoomFrame;
    public LobbyFrame() {
        setTitle("LobbyFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        add(new LobbyPanel());
        setVisible(true);
    }



    public static void main(String[] args) {
        ArrayList<Room> rArr = new ArrayList<>();

        LobbyFrame frame = new LobbyFrame();
        frame.setGameChoicePanel(new GameChoicePanel(rArr));
    }

    public void setGameChoicePanel(GameChoicePanel gameChoicePanel) {

        ( (LobbyPanel) getContentPane().getComponent(0)).setGameChoicePanel(gameChoicePanel);
    }
}
