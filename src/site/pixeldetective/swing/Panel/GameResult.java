package site.pixeldetective.swing.Panel;

import site.pixeldetective.swing.Frame.GameFrame;
import site.pixeldetective.swing.Frame.LobbyFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameResult extends JFrame {
    private String myName;
    private String otherName;
    private int hits;
    private int miss;
    private int total;
    private int time;
    public String result; // WIN, LOSE, DRAW
    public GameFrame gf;
    int otherHits;
    int otherMiss;
    int otherTotal;

    public GameResult(String myName, String otherName, int hits, int miss, int total, int time, String result
    , int otherHits, int otherMiss, int otherTotal) {
        this.myName = myName;
        this.otherName = otherName;
        this.hits = hits;
        this.miss = miss;
        this.total = total;
        this.time = time;
        this.result = result;
        this.otherHits = otherHits;
        this.otherMiss = otherMiss;
        this.otherTotal = otherTotal;
        GameFrame gf;


        setTitle("게임 결과");
        setSize(600, 400);
        setLocationRelativeTo(null); // 화면 중앙에 위치하도록 설정
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때 종료하지 않고 닫기

        initComponents();
    }

    private void initComponents() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        // 상단 패널 추가
        JPanel p_north = new JPanel();
        JLabel additionalLabel;
        if (result.equals("WIN")) {
            additionalLabel = new JLabel("WIN");
            additionalLabel.setFont(new Font("Arial", Font.BOLD, 60));
            additionalLabel.setForeground(Color.decode("#00FF00")); // 초록색
        } else if (result.equals("LOSE")) {
            additionalLabel = new JLabel("LOSE");
            additionalLabel.setFont(new Font("Arial", Font.BOLD, 60));
            additionalLabel.setForeground(Color.decode("#FF0000")); // 빨간색
        } else {
            additionalLabel = new JLabel("DRAW");
            additionalLabel.setFont(new Font("Arial", Font.BOLD, 60));
            additionalLabel.setForeground(Color.decode("#FFA500")); // 주황색
        }

        p_north.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        p_north.setBorder(new EmptyBorder(0, 20, 0, 20)); // 상단 패널 여백 설정
        p_north.add(additionalLabel);

        // 첫 번째 줄 패널 (라벨)
        JPanel labelsPanel = new JPanel(new GridLayout(1, 5, 0, 0));
        labelsPanel.setBorder(new EmptyBorder(0, 10, 0, 10)); // 라벨 패널 여백 설정
        JLabel nameLabel1 = new JLabel("닉네임");
        JLabel hitLabel1 = new JLabel("hit");
        JLabel missLabel1 = new JLabel("miss");
        JLabel totalLabel1 = new JLabel("total");
        JLabel timeLabel1 = new JLabel("time");
        labelsPanel.add(nameLabel1);
        labelsPanel.add(hitLabel1);
        labelsPanel.add(missLabel1);
        labelsPanel.add(totalLabel1);
        labelsPanel.add(timeLabel1);

        // 두 번째 줄 패널 (값) - 플레이어 1
        JPanel valuesPanel1 = new JPanel(new GridLayout(1, 5, 0, 0));
        valuesPanel1.setBorder(new EmptyBorder(0, 10, 0, 10)); // 값 패널 여백 설정
        JLabel nameLabel2_1 = new JLabel(myName); // 실제 값으로 변경
        JLabel hitLabel2_1 = new JLabel(String.valueOf(hits)); // 실제 값으로 변경
        JLabel missLabel2_1 = new JLabel(String.valueOf(miss)); // 실제 값으로 변경
        JLabel totalLabel2_1 = new JLabel(String.valueOf(total)); // 실제 값으로 변경
        JLabel timeLabel2_1 = new JLabel(String.valueOf(time) + "s"); // 실제 값으로 변경
        valuesPanel1.add(nameLabel2_1);
        valuesPanel1.add(hitLabel2_1);
        valuesPanel1.add(missLabel2_1);
        valuesPanel1.add(totalLabel2_1);
        valuesPanel1.add(timeLabel2_1);

        // 세 번째 줄 패널 (값) - 플레이어 2
        JPanel valuesPanel2 = new JPanel(new GridLayout(1, 5, 0, 0));
        valuesPanel2.setBorder(new EmptyBorder(0, 10, 0, 10)); // 값 패널 여백 설정
        JLabel nameLabel2_2 = new JLabel(otherName); // 실제 값으로 변경
        JLabel hitLabel2_2 = new JLabel(String.valueOf(otherHits)); // 실제 값으로 변경
        JLabel missLabel2_2 = new JLabel(String.valueOf(otherMiss)); // 실제 값으로 변경
        JLabel totalLabel2_2 = new JLabel(String.valueOf(otherTotal)); // 실제 값으로 변경
        JLabel timeLabel2_2 = new JLabel(String.valueOf(time) + "s"); // 실제 값으로 변경
        valuesPanel2.add(nameLabel2_2);
        valuesPanel2.add(hitLabel2_2);
        valuesPanel2.add(missLabel2_2);
        valuesPanel2.add(totalLabel2_2);
        valuesPanel2.add(timeLabel2_2);

        // 전체 패널 생성
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(labelsPanel, BorderLayout.NORTH); // 첫 번째 줄 라벨 패널을 상단에 추가
        panel.add(valuesPanel1, BorderLayout.CENTER); // 두 번째 줄 값 패널(플레이어 1)을 중앙에 추가
        panel.add(valuesPanel2, BorderLayout.SOUTH); // 세 번째 줄 값 패널(플레이어 2)을 하단에 추가
        contentPane.add(p_north, BorderLayout.NORTH); // 상단 패널 추가
        contentPane.add(panel, BorderLayout.CENTER); // 전체 패널 추가

        // 확인 버튼 패널 생성 및 추가
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("확인");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LobbyFrame lp = new LobbyFrame();
                lp.setVisible(true); // LobbyFrame 보이도록 설정
                gf.setVisible(false);
                dispose(); // 현재 창 닫기
            }
        });
        buttonPanel.add(okButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH); // 확인 버튼 패널을 하단에 추가

        setContentPane(contentPane); // 컨텐츠 패널을 프레임에 설정
    }

}