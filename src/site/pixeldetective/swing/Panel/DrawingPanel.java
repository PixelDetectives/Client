package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Component.CurrentBoardPanel;
import site.pixeldetective.swing.Component.DrawingComponent;
import site.pixeldetective.swing.Component.NameComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DrawingPanel extends JPanel {
    public int userid;
    public DrawingComponent dc;

    public DrawingPanel(String imgURL) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        setLayout(new BorderLayout());
        dc = new DrawingComponent(imgURL);
        dc.userid = userid;
        add(dc, BorderLayout.CENTER);

        NameComponent nc = new NameComponent();
        nc.setPreferredSize(new Dimension(430, 100));
        add(nc, BorderLayout.SOUTH);

    }
}
