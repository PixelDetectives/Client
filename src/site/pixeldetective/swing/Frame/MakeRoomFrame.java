package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.RoomSettingPanel;

import javax.swing.*;
import java.awt.*;

public class MakeRoomFrame extends JFrame {
    public LobbyFrame lobbyFrame;
    public GameFrame gameFrame;

    public MakeRoomFrame(){
        super("MakeRoom");
        RoomSettingPanel rsp =  new RoomSettingPanel();
        rsp.mrf = this;
        add(rsp);

        setVisible(false);
        setResizable(false);
        setSize(1280, 720);
    }

    public void changeFrame() {
        Point location = this.getLocation();
        Dimension size = this.getSize();
        lobbyFrame.setLocation(location);
        lobbyFrame.setSize(size);
        lobbyFrame.setVisible(true);
        this.setVisible(false);
    }

}
