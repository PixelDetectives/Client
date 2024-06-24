package site.pixeldetective.swing.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private Dimension drawDeinsion = new Dimension(450, 640);
    public int time  = 180;
    public int hits = 0;
    public int miss = 0;

    public int TOTAL_HITS = 5;

    public GamePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        // 컴포넌트 크기 고정
        gbc.fill = GridBagConstraints.NONE;
        // 가운데 정렬
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        // 가로 방향 비율 추가 분배 x
        gbc.weightx = 1.0;
        // 세로 방향 비율 추가 분배 x
        gbc.weighty = 1.0;

        JPanel topDummy = new DummyPanel();
        topDummy.setPreferredSize(new Dimension(1280, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.gridwidth = 3;
        // 수직 방향으로 공간을 차지하는 비율
        gbc.weighty = 1.0;
        add(topDummy, gbc);

        // jp1 설정
        String imgURL1 = "https://sesac-projects-s3.s3.ap-northeast-2.amazonaws.com/image1.png";
        JPanel jp1 = new DrawingPanel(imgURL1);
        // 자신의 pk
        ((DrawingPanel)jp1).userid = 1;
        ((DrawingPanel)jp1).dc.gp = this;
        jp1.setPreferredSize(drawDeinsion);
        gbc.gridx = 0;
        gbc.gridy = 1;

        add(jp1, gbc);

        String imgURL2 = "https://sesac-projects-s3.s3.ap-northeast-2.amazonaws.com/image2.png";
        JPanel jp2 = new DrawingPanel(imgURL2);

        // 상대의 pk
        ((DrawingPanel)jp2).userid = 1;
        ((DrawingPanel)jp2).dc.gp = this;
        jp2.setPreferredSize(drawDeinsion);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(jp2, gbc);

        JPanel jp3 = new DummyPanel();
        jp3.setPreferredSize(new Dimension(60, 640));
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(jp3, gbc);

        JPanel jp4 = new GameBoardPanel();
        ((DrawingPanel)jp1).dc.cbp = ((GameBoardPanel)jp4).cbp;
        ((DrawingPanel)jp2).dc.cbp = ((GameBoardPanel)jp4).cbp;
        ((DrawingPanel)jp1).dc.cp = ((GameBoardPanel)jp4).cp;
        ((DrawingPanel)jp2).dc.cp = ((GameBoardPanel)jp4).cp;


        jp4.setPreferredSize(new Dimension(250, 640));
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(jp4, gbc);
        ((GameBoardPanel) jp4).tc.timeLabel.setText(time + "");
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                // EDT에서 JLabel 텍스트 업데이트
                SwingUtilities.invokeLater(() -> {
                    ((GameBoardPanel) jp4).tc.timeLabel.setText(time + "");

                    if (time <= 0) {
                        ((Timer) e.getSource()).stop();
                        JOptionPane.showMessageDialog(
                                GamePanel.this,
                                "time over",
                                "타이머",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });
            }
        });
        t.start();


        JPanel bottomDummy = new DummyPanel();
        topDummy.setPreferredSize(new Dimension(1280, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        // 수직 방향으로 공간을 차지하는 비율
        gbc.weighty = 1.0;
        add(bottomDummy, gbc);
    }
}
