package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Component.CorrectPanel;
import site.pixeldetective.swing.Component.CurrentBoardPanel;
import site.pixeldetective.swing.Component.DrawingComponent;
import site.pixeldetective.swing.Panel.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class GamePanel extends JPanel {
    private Dimension drawDeinsion = new Dimension(450, 640);
    public int time  = 0;
    public int hits = 0;
    public int miss = 0;
    public int total = 0;
    public int TOTAL_HITS = 0;
    public Map<Integer, List<Integer>> answers;
    public List<Boolean> answerMark;
    public String myName;
    public String otherName;
    public CurrentBoardPanel currentBoardPanel;
    public DrawingPanel leftPanel;
    public DrawingPanel rightPanel;
    public CorrectPanel cp;
    public Timer t;
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
        gameInitialize(1);
        // jp1 설정
        String imgURL1 = "https://sesac-projects-s3.s3.ap-northeast-2.amazonaws.com/image1.png";
        leftPanel = new DrawingPanel(imgURL1, myName);
        // 자신의 pk
        ((DrawingPanel)leftPanel).userid = 1;
        ((DrawingPanel)leftPanel).dc.gp = this;
        leftPanel.dc.answerMark = this.answerMark;
        leftPanel.dc.answers = this.answers;
        leftPanel.setPreferredSize(drawDeinsion);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(leftPanel, gbc);

        String imgURL2 = "https://sesac-projects-s3.s3.ap-northeast-2.amazonaws.com/image2.png";
        rightPanel = new DrawingPanel(imgURL2, otherName);

        // 상대의 pk
        ((DrawingPanel)rightPanel).userid = 1;
        ((DrawingPanel)rightPanel).dc.gp = this;
        rightPanel.dc.answerMark = this.answerMark;
        rightPanel.dc.answers = this.answers;
        rightPanel.setPreferredSize(drawDeinsion);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(rightPanel, gbc);

        JPanel jp3 = new DummyPanel();
        jp3.setPreferredSize(new Dimension(60, 640));
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(jp3, gbc);

        JPanel jp4 = new GameBoardPanel();
        currentBoardPanel = ((GameBoardPanel)jp4).cbp;
        ((GameBoardPanel)jp4).cp.updateLabel(hits, TOTAL_HITS);
        cp = ((GameBoardPanel)jp4).cp;
        jp4.setPreferredSize(new Dimension(250, 640));
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(jp4, gbc);
        ((GameBoardPanel) jp4).tc.timeLabel.setText(time + "");
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                // EDT에서 JLabel 텍스트 업데이트
                SwingUtilities.invokeLater(() -> {
                    ((GameBoardPanel) jp4).tc.timeLabel.setText(time + "");
                    if (time <= 0) {
                        gameOver();
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
    private void gameInitialize(int drawingNumber) {
        try {
            myName = "나";
            otherName = "상대방";
            time = 5;
            // drawingNumber를 통한 값 가져오기
            answers = new HashMap<>();
            hits = 0;
            miss = 0;
            total = 0;
            // answer의 전체 길이
            TOTAL_HITS = 4;
            // get answers
            answerMark = new ArrayList<>();
            for (int i = 0; i < TOTAL_HITS; i++) {
                answerMark.add(false);
            }
            answers.put(0, new ArrayList<>(Arrays.asList(217, 377, 130)));
            answers.put(1, new ArrayList<>(Arrays.asList(77, 87, 75)));
            answers.put(2, new ArrayList<>(Arrays.asList(388, 349, 75)));
            answers.put(3, new ArrayList<>(Arrays.asList(37, 413, 40)));
        } catch (Exception exception) {

        }
    }

    public void handleClick(int x, int y, DrawingComponent source) {
        double minDist = 100000000.0;
        int answerCandidate = -1;
        // 정답을 순회하면서 어떤 클릭 이벤트가 일어난 곳과 가까운 곳, 정답 안에 들어온 곳을 찾아냄.
        for (int k : answers.keySet()) {
            List<Integer> val = answers.get(k);
            double tempDist = Math.hypot(x - val.get(0), y - val.get(1));
            if (tempDist <= val.get(2)) {
                if (tempDist <= minDist) {
                    minDist = tempDist;
                    answerCandidate = k;
                }
            }
        }
        if (answerCandidate != -1 && !answerMark.get(answerCandidate)) {
            answerMark.set(answerCandidate, true);
            hits += 1;
            leftPanel.dc.printCorrect();
            rightPanel.dc.printCorrect();
            if (hits == TOTAL_HITS) {
                gameOver();
            }
        } else if (answerCandidate == -1) {
            miss += 1;
            source.printMiss();
        }
        total = hits + miss;
        currentBoardPanel.updateLabel(hits, miss, total);
        cp.updateLabel(hits, TOTAL_HITS);
    }
    private void setUpdateText(JPanel component) {
        if (component != null) {
            try {
                Field[] fields = component.getClass().getDeclaredFields();
                for (Field field : fields) {
                    System.out.println(field.getName());
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
    public void gameOver() {
        System.out.println("game over");
        if (t != null) {
            t.stop();
        }
        JOptionPane.showMessageDialog(
                GamePanel.this,
                "game over",
                "타이머",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
