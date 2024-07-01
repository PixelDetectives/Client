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
    public int x;
    public int y;
    public GamePanel gp;
    private boolean showX;
    public List<Boolean> answerMark;
    public Map<Integer, List<Integer>> answers;

    public DrawingComponent(String imgURL) {

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
                x = e.getX();
                y = e.getY();
                System.out.println(x + " " + y);
                gp.handleClick(x, y, DrawingComponent.this);


//                            lst = new ArrayList<>(Arrays.asList(val.get(0), val.get(1), val.get(2), userid));

//                if (idx != -1) {
////                    System.out.println(idx + " " + answerCheck.get(idx));
//                }
////                System.out.println(idx);
//                if (idx != -1 && !answerCheck.get(idx)) { // idx가 -1이 아닐 경우에만 실행
//                    drawCircles.add(lst);
//                    cp.correctLabel.setText(gp.hits + "/" + gp.TOTAL_HITS);
//                    answerCheck.set(idx, true);
//                    repaint();
//                } else if (idx == -1) {
//                    showX = true;
//                    repaint(); // X 표시를 그리기 위해 repaint 호출
//                    // 1초 후 X 표시 지우기
//                    Timer timer = new Timer();
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            repaint(); // X 표시를 지우기 위해 repaint 호출
//                            showX = false;
//                            timer.cancel(); // 타이머 취소
//                        }
//                    }, 1000); // 1초 후 실행
//
//                }

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
        for (int i = 0; i < answerMark.size(); i++) {
            if (answerMark.get(i)) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(Color.RED);
                List<Integer> location = answers.get(i);
                g2d.drawOval(
                        location.get(0) - location.get(2),
                        location.get(1) - location.get(2),
                        location.get(2) * 2,
                        location.get(2) * 2);
                g2d.dispose();
            }
        }
//        for (List<Integer> rect : drawCircles) {
//            Graphics2D g2d = (Graphics2D) g.create();
//            g2d.setStroke(new BasicStroke(3));
//            if (rect.get(3) == userid) {
//                g2d.setColor(Color.RED);
//            } else {
//                g2d.setColor(Color.BLUE);
//            }
//            g2d.drawOval(rect.get(0) - rect.get(2), rect.get(1) - rect.get(2), rect.get(2) * 2, rect.get(2) * 2);
//            g2d.dispose();
//        }

        if (showX) { // X 표시를 그릴지 여부를 나타내는 변수
            g.setColor(Color.RED);
            g.drawLine(x - 30, y - 30, x + 30, y + 30);
            g.drawLine(x + 30, y - 30, x - 30, y + 30);
        }
    }
    public void printMiss() {
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
    public void printCorrect() {
        repaint();
    }
}
