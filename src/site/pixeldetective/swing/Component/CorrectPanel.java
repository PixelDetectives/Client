package site.pixeldetective.swing.Component;

import javax.swing.*;
import java.awt.*;

public class CorrectPanel extends JPanel {

    public JLabel correctLabel;
    public CorrectPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.YELLOW);
        correctLabel = new JLabel("", SwingConstants.CENTER);
        setPreferredSize(new Dimension(100, 75));
        correctLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(correctLabel, BorderLayout.CENTER);
    }
    public void updateLabel(int hits, int total) {
        correctLabel.setText(hits + "/" + total);

    }
}
