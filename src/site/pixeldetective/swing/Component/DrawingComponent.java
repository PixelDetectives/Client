package site.pixeldetective.swing.Component;

import site.pixeldetective.swing.Panel.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class DrawingComponent extends JPanel {
    public int userid;
    private BufferedImage image;
    public List<List<Integer>> drawCircles = new ArrayList<>();
    public Map<Integer, List<Integer>> answers = new HashMap<>();
    public int hit = 0;
    public int miss = 0;
    public CurrentBoardPanel cbp;
    public CorrectPanel cp;
    public int TOTAL_HITS = 5;
    public boolean showX = false;
    public int x;
    public int y;
    public GamePanel gp;
    public List<Boolean> answerCheck;

    public DrawingComponent(String imgURL) {
        answers.put(0, new ArrayList<>(Arrays.asList(217, 377, 130)));
        answers.put(1, new ArrayList<>(Arrays.asList(77, 87, 75)));
        answers.put(2, new ArrayList<>(Arrays.asList(388, 349, 75)));
        answers.put(3, new ArrayList<>(Arrays.asList(37, 413, 40)));
        answerCheck = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            answerCheck.add(false);
        }

        try {
            URL url = new URL(imgURL);
            image = ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                x = e.getX();
                y = e.getY();
                double dist = 100000.0;
                int idx = -1;

                List lst = null;
                for (int k : answers.keySet()) {
                    List<Integer> val = answers.get(k);
                    double temp = Math.pow((x - val.get(0)) * (x - val.get(0))  + (y - val.get(1)) * (y - val.get(1)), 0.5);
                    if (temp <= val.get(2)) {
                        if (temp <= dist) {
                            dist = temp;
                            idx = k;
                            lst = new ArrayList<>(Arrays.asList(val.get(0), val.get(1), val.get(2), userid));
                        }
                    }
                }
                if (idx != -1) {
                    System.out.println(idx + " " + answerCheck.get(idx));
                }
                System.out.println(idx);
                if (idx != -1 && !answerCheck.get(idx)) { // idx가 -1이 아닐 경우에만 실행
                    gp.hits += 1;
                    cbp.hitLabel.setText(gp.hits + "");
                    cbp.totalLabel.setText(gp.hits + gp.miss + "");
                    drawCircles.add(lst);
                    cp.correctLabel.setText(gp.hits + "/" + gp.TOTAL_HITS);
                    answerCheck.set(idx, true);
                    repaint();
                } else if (idx == -1) {
                    gp.miss += 1;
                    cbp.missLabel.setText(gp.miss + "");
                    cbp.totalLabel.setText(gp.hits + gp.miss + "");
                    showX = true;
                    repaint(); // X 표시를 그리기 위해 repaint 호출
                    // 1초 후 X 표시 지우기
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            repaint(); // X 표시를 지우기 위해 repaint 호출
                            showX = false;
                            timer.cancel(); // 타이머 취소
                        }
                    }, 1000); // 1초 후 실행

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);
        }
        for (List<Integer> rect : drawCircles) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setStroke(new BasicStroke(3));
            if (rect.get(3) == userid) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLUE);
            }
            g2d.drawOval(rect.get(0) - rect.get(2), rect.get(1) - rect.get(2), rect.get(2) * 2, rect.get(2) * 2);
            g2d.dispose();
        }
        if (showX) { // X 표시를 그릴지 여부를 나타내는 변수
            g.setColor(Color.RED);
            g.drawLine(x - 30, y - 30, x + 30, y + 30);
            g.drawLine(x + 30, y - 30, x - 30, y + 30);
        }

    }
}
