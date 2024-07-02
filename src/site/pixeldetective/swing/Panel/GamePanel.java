package site.pixeldetective.swing.Panel;

import org.json.JSONArray;
import org.json.JSONObject;
import site.pixeldetective.swing.Component.CorrectPanel;
import site.pixeldetective.swing.Component.CurrentBoardPanel;
import site.pixeldetective.swing.Component.DrawingComponent;
import site.pixeldetective.swing.Panel.DrawingPanel;
import site.pixeldetective.swing.Frame.GameFrame;
import site.pixeldetective.swing.Panel.GameResult;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GamePanel extends JPanel implements PropertyChangeListener {
    private final ImageIcon backgroundImage = new ImageIcon("resource/image/bgGame.png");
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
    public GameFrame gf;
    public GameResult gr;

    private SocketClient socketClient;

    private String imgURL1;
    private String imgURL2;
    public DrawingPanel dp;

    public GamePanel() {
        this.socketClient = SocketClient.getInstance();
        SocketClient.getInstance().addPropertyChangeListener("gameOver", this);
        SocketClient.getInstance().addPropertyChangeListener("gameStart", this);
        SocketClient.getInstance().addPropertyChangeListener("quickStart", this);
        SocketClient.getInstance().addPropertyChangeListener("pointResult", this);
        SocketClient.getInstance().addPropertyChangeListener("gameResult", this);
    }
    private void UIInitialize() {
        try {
            setPreferredSize(new Dimension(1280, 720));

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
            topDummy.setOpaque(false); // 투명하게 설정

            gbc.gridx = 0;
            gbc.gridy = 0;
            // gbc.gridwidth = 3;

            // 수직 방향으로 공간을 차지하는 비율
            gbc.weighty = 1.0;
            add(topDummy, gbc);
            // jp1 설정
            leftPanel = new DrawingPanel(imgURL1, myName);
            ((DrawingPanel) leftPanel).getDrawingComponent().gp = this;
            ((DrawingPanel) leftPanel).getDrawingComponent().answers = answers;
            ((DrawingPanel) leftPanel).getDrawingComponent().answerMark = answerMark;
            ((DrawingPanel) leftPanel).getDrawingComponent().TOTAL_HITS = TOTAL_HITS;
            leftPanel.setPreferredSize(drawDeinsion);
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(leftPanel, gbc);

            rightPanel = new DrawingPanel(imgURL2, otherName);

            ((DrawingPanel) rightPanel).getDrawingComponent().gp = this;
            ((DrawingPanel) rightPanel).getDrawingComponent().answers = answers;
            ((DrawingPanel) rightPanel).getDrawingComponent().answerMark = answerMark;
            ((DrawingPanel) rightPanel).getDrawingComponent().TOTAL_HITS = TOTAL_HITS;
            rightPanel.setPreferredSize(drawDeinsion);
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(rightPanel, gbc);

            JPanel jp3 = new DummyPanel();
            jp3.setPreferredSize(new Dimension(60, 640));
            jp3.setOpaque(false); // 투명하게 설정
            gbc.gridx = 2;
            gbc.gridy = 1;
            add(jp3, gbc);

            JPanel jp4 = new GameBoardPanel();
            currentBoardPanel = ((GameBoardPanel)jp4).cbp;
            ((GameBoardPanel)jp4).cp.updateLabel(hits, TOTAL_HITS);
            cp = ((GameBoardPanel)jp4).cp;
            jp4.setPreferredSize(new Dimension(250, 640));
            jp4.setOpaque(false); // 투명하지 않게 설정
            // jp4.setBackground(Color.red); // 색상 추가
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
                            handleGameOver();
                        }
                    });
                }
            });
            t.start();

            JPanel bottomDummy = new DummyPanel();
            topDummy.setPreferredSize(new Dimension(1280, 20));
            jp3.setOpaque(false); // 투명하게 설정
            gbc.gridx = 0;
            gbc.gridy = 2;
            // 수직 방향으로 공간을 차지하는 비율
            gbc.weighty = 1.0;
            add(bottomDummy, gbc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("gameOver".equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::handleGameOver);
        } else if ("gameStart".equals(evt.getPropertyName())) {
            JSONObject gameData = (JSONObject) evt.getNewValue();
            SwingUtilities.invokeLater(() -> handleGameStart(gameData));
        } else if ("quickStart".equals(evt.getPropertyName())) {
            JSONObject gameData = (JSONObject) evt.getNewValue();
            SwingUtilities.invokeLater(() -> {
                gf.setVisible(true);
                updateGameData(gameData);
            });
        } else if ("pointResult".equals(evt.getPropertyName())) {
            JSONObject pointResultData = (JSONObject) evt.getNewValue();
            SwingUtilities.invokeLater(() -> handlePointResult(pointResultData));
        } else if ("gameResult".equals(evt.getPropertyName())) {
            JSONObject gameResultData = (JSONObject) evt.getNewValue();
            SwingUtilities.invokeLater(() -> handleGameResult(gameResultData));
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
    }


    public void handleClick(int x, int y, DrawingComponent source) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "gamePoint");
        JSONObject pointer = new JSONObject();
        pointer.put("x", x);
        pointer.put("y", y);
        jsonObject.put("data", pointer);

        if (source == leftPanel.getDrawingComponent()) {
            this.dp = leftPanel;
        } else if (source == rightPanel.getDrawingComponent()) {
            this.dp = rightPanel;
        }

        socketClient.send(jsonObject.toString());

    }

    public void handleGameOver() {

        if (t != null) {
            t.stop();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "gameOver");
        JSONObject data = new JSONObject();
        data.put("hits" , hits);
        data.put("miss", miss);
        jsonObject.put("data", data.toString());

        socketClient.send(jsonObject.toString());



    }
    public void handleGameStart(JSONObject gameData) {
        SwingUtilities.invokeLater(() -> {
            try {
                if (gameData != null) {
                    System.out.println(gameData);
                    int gDifficulty = gameData.getInt("gDifficulty");
                    if (gDifficulty == 1) {
                        time = 180;
                    } else if (gDifficulty == 2) {
                        time = 300;
                    } else {
                        time = 420;
                    }
                    imgURL1 = gameData.getString("gImage1");
                    imgURL2 = gameData.getString("gImage2");
                    myName = gameData.getString("myName");
                    otherName = gameData.getString("otherName");
                    UIInitialize();
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                new RuntimeException(exception);
            }
        });
    }

    private void updateGameData(JSONObject gameData) {
        System.out.println("update Game Data");
        int gDifficulty = gameData.optInt("gDifficulty", 1);
        if (gDifficulty == 0) {
            time = 180;
        } else if (gDifficulty == 1) {
            time = 300;
        } else {
            time = 420;
        }
        this.answerMark = new ArrayList<>();
        myName = gameData.optString("myName");
        otherName = gameData.optString("otherName");
        imgURL1 = gameData.optString("gImage1");
        imgURL2 = gameData.optString("gImage2");
        this.answers = new HashMap<>();
        JSONArray answersArray = gameData.getJSONArray("answers");
        TOTAL_HITS = answersArray.length();
        for (int i = 0; i < TOTAL_HITS; i++) {
            JSONObject answer = answersArray.getJSONObject(i);
            int aX = answer.getInt("aX");
            int aY = answer.getInt("aY");
            int aRadius = answer.getInt("aRadius");
            answers.put(i, Arrays.asList(aX, aY, aRadius));
        }
        for (int i = 0; i < TOTAL_HITS; i++) {
            this.answerMark.add(false);
        }
        this.hits = 0;
        this.miss = 0;
        this.total = 0;
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            UIInitialize();
        });
    }
    public void handlePointResult(JSONObject object) {
        String status = object.getString("status");
        String nickname = object.getString("currUserName");
        if (status.equals("none")) {
            return ;
        }
        if (status.equals("hits") && nickname.equals(socketClient.uName)) {
            this.hits += 1;
        }
        if (status.equals("hits")) {
            int correctIdx = object.getInt("correctIdx");
            if (nickname.equals(myName)) {
                leftPanel.dc.printCorrect(correctIdx, Color.RED);
                rightPanel.dc.printCorrect(correctIdx, Color.RED);
            } else {
                leftPanel.dc.printCorrect(correctIdx, Color.BLUE);
                rightPanel.dc.printCorrect(correctIdx, Color.BLUE);
            }
            cp.updateLabel(this.hits, TOTAL_HITS);
        } else if (status.equals("miss") && nickname.equals(socketClient.uName)) {
            this.miss += 1;
            this.dp.getDrawingComponent().printMiss();
        }
        if (nickname.equals(socketClient.uName)) {
            currentBoardPanel.updateLabel(this.hits, this.miss, this.hits + this.miss);
        }
    }
    public void handleGameResult(JSONObject object) {
        System.out.println("handleGameResult");
        int myHits = object.getInt("myHits");
        int myMiss = object.getInt("myMiss");
        int otherHits = object.getInt("otherHits");
        int otherMiss = object.getInt("otherMiss");
        int time = object.getInt("time") / 1000;
        String otherNickName = object.getString("otherNickName");
        String myNickName = object.getString("myNickName");
        String result = "";
        if (myHits > otherHits || (myHits == otherHits && myMiss < otherMiss)) {
            result += "WIN";
        } else if (otherHits > myHits || (myHits == otherHits && myMiss > otherMiss)) {
            result += "LOSE";
        } else {
            result += "DRAW";
        }
        String finalResult = result;
        SwingUtilities.invokeLater(() -> {
            GameResult gr = new GameResult(myNickName, otherNickName, myHits, myMiss, myHits + myMiss, time, finalResult, otherHits, otherMiss, otherHits + otherMiss);
            gr.setVisible(true);
//            gr.gf = this.gf;
        });
    }
}