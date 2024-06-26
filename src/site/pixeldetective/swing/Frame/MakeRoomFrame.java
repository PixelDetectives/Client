package site.pixeldetective.swing.Frame;

import site.pixeldetective.swing.Panel.RoomSettingPanel;

import javax.swing.*;

public class MakeRoomFrame extends JFrame {
    public MakeRoomFrame(){
        super("MakeRoom");

        add(new RoomSettingPanel());

        setVisible(true);
        setResizable(false);
        setSize(1280, 720);
    }
}
