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
    private BufferedImage image;
    public List<List<Integer>> drawCircles = new ArrayList<>();
    public List<Color> drawColors = new ArrayList<>();
    public int x;
    public int y;
    public GamePanel gp;
    private boolean showX;
    public List<Boolean> answerMark;
    public Map<Integer, List<Integer>> answers;
    public int TOTAL_HITS;

    public DrawingComponent(String imgURL) {

        try {
            if (!imgURL.equals("")) {
                URL url = new URL(imgURL);
                image = ImageIO.read(url);
            }
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
        for (int i = 0; i < drawCircles.size(); i++) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(drawColors.get(i));
            List<Integer> rect = drawCircles.get(i);
            g2d.drawOval(rect.get(0) - rect.get(2), rect.get(1) - rect.get(2), rect.get(2) * 2, rect.get(2) * 2);
            g2d.dispose();
        }
        if (showX) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawLine(x - 30, y - 30, x + 30, y + 30);
            g2d.drawLine(x + 30, y - 30, x - 30, y + 30);
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
    public void printCorrect(int answerIndex, Color color) {
        // answers 맵에 정답 정보가 있는지 확인
        if (answers.containsKey(answerIndex)) {
            answerMark.set(answerIndex, true);
            List<Integer> location = answers.get(answerIndex);
            Graphics g = getGraphics(); // Graphics 객체 얻기
            if (g != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(color); // 전달받은 색상 사용
                g2d.drawOval(
                        location.get(0) - location.get(2),
                        location.get(1) - location.get(2),
                        location.get(2) * 2,
                        location.get(2) * 2);
                g2d.dispose();
            }
            drawCircles.add(Arrays.asList(location.get(0), location.get(1), location.get(2)));
            drawColors.add(color);
        } else {
            System.err.println("Error: Invalid answer index in printCorrect - " + answerIndex);
        }
        repaint();
        if (drawCircles.size() == TOTAL_HITS) {
            System.out.println("gameOver");
        }
    }
}
