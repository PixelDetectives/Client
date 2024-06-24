package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;

public class CurrentBoardPanel extends JPanel {
    public int hit = 0;
    public int miss = 0;
    public int total = 0;
    public JLabel hitLabel;
    public JLabel missLabel;
    public JLabel totalLabel;

    public CurrentBoardPanel() {

        setLayout(new GridLayout(3,2, 0, 10));
        setPreferredSize(new Dimension(100, 300));

        JLabel label1 = new JLabel("Hit");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.PLAIN, 28));
        add(label1);

        hitLabel = new JLabel("0");
        hitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hitLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        add(hitLabel);

        JLabel label3 = new JLabel("Miss");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Arial", Font.PLAIN, 28));
        add(label3);

        missLabel = new JLabel("0");
        missLabel.setHorizontalAlignment(SwingConstants.CENTER);
        missLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        add(missLabel);

        JLabel label5 = new JLabel("Total");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Arial", Font.PLAIN, 28));
        add(label5);

        totalLabel = new JLabel("0");
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        add(totalLabel);
    }
}
