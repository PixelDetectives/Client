package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Component.CorrectPanel;
import site.pixeldetective.swing.Component.CurrentBoardPanel;
import site.pixeldetective.swing.Component.TimerComponent;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {
    public TimerComponent tc;
    public CurrentBoardPanel cbp;
    public CorrectPanel cp;

    public GameBoardPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        setLayout(new GridBagLayout());

        this.tc = new TimerComponent();
        gbc.gridy = 0;
        add(this.tc, gbc);


        this.cbp = new CurrentBoardPanel();
        gbc.gridy = 1;
        add(this.cbp, gbc);

        this.cp = new CorrectPanel();
        gbc.gridy = 2;
        add(this.cp, gbc);

    }
}
