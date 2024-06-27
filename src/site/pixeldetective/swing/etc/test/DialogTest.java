package site.pixeldetective.swing.etc.test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogTest extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public DialogTest() {
        // JFrame 설정
        setTitle("로그인 프레임");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 배치

        // CardLayout 설정
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 패널들을 카드 패널에 추가
        cardPanel.add(new FirstPanel(this), "firstPanel");
        cardPanel.add(new SecondPanel(), "secondPanel");
        cardPanel.add(new ThirdPanel(this), "thirdPanel");

        // 프레임에 카드 패널 추가
        add(cardPanel);

        // 프레임 표시
        setVisible(true);
    }
	
	 // 로그인 화면을 나타내는 패널
	 class FirstPanel extends JPanel {
	
	     public FirstPanel(DialogTest mainFrame) {
	         setLayout(new BorderLayout());

	         // 버튼 패널 생성 및 레이아웃 설정
	         JPanel buttonPanel = new JPanel();
	         buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 150)); // 중앙 정렬과 여백 설정

	         Color loginHex = Color.decode("#84B5FE");
	         
	         // 확인 버튼 생성 (배경, 글자)
	         RoundedButton confirmButton = new RoundedButton("로그인", loginHex, Color.WHITE, 20);
	         confirmButton.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 mainFrame.showDialog("로그인 되었습니다", "secondPanel");
	             }
	         });

	         Color signupHex = Color.decode("#BB83FE");
	         
	         // 회원가입 버튼 생성
	         RoundedButton nextButton = new RoundedButton("회원가입", signupHex, Color.WHITE, 20);
	         nextButton.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 mainFrame.animateTransition("thirdPanel");
	             }
	         });

	         // 버튼을 버튼 패널에 추가
	         buttonPanel.add(confirmButton);
	         buttonPanel.add(nextButton);

	         // 버튼 패널을 메인 패널의 아래쪽에 추가
	         add(buttonPanel, BorderLayout.SOUTH);
	     }
	 }
	
	 // 로비 화면을 나타내는 패널
	 class SecondPanel extends JPanel {
	
	     public SecondPanel() {
	         setLayout(new BorderLayout());
	
	         // 필요한 추가 컴포넌트 설정 가능
	         JLabel label = new JLabel("로비 프레임");
	         label.setHorizontalAlignment(SwingConstants.CENTER);
	         add(label, BorderLayout.CENTER);
	     }
	 }
	 
	 // 회원 가입 화면을 나타내는 패널
	    class ThirdPanel extends JPanel {

	        public ThirdPanel(DialogTest mainFrame) {
	            setLayout(new BorderLayout());
	         // 버튼 패널 생성 및 레이아웃 설정
	            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // 중앙 정렬된 FlowLayout 사용
	            buttonPanel.setBorder(BorderFactory.createEmptyBorder(471, 0, 0, 0)); // 버튼 패널에 여백 추가 (상, 하, 좌, 우)

	            // 가입하기 버튼 생성
	            Color signupHex = Color.decode("#FFB33E");
	            RoundedButton signUpButton = new RoundedButton("가입하기", signupHex, Color.WHITE, 20);
	            signUpButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    // 여기에 가입 로직 추가
	                	mainFrame.showDialog("회원가입에 성공했습니다.", "firstPanel");
	                }
	            });

	            // 뒤로가기 버튼 생성
	            Color backHex = Color.decode("#66DFA4");
	            RoundedButton backButton = new RoundedButton("뒤로가기", backHex, Color.WHITE, 20);
	            backButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    mainFrame.animateTransition("firstPanel"); // 첫 번째 패널로 이동
	                }
	            });

	            buttonPanel.add(signUpButton);
	            buttonPanel.add(backButton);

	            // 버튼 패널을 중앙에 추가
	            add(buttonPanel, BorderLayout.CENTER);
	        }
	    }

    public void animateTransition(String target) {
        // 애니메이션 타이머 설정
        Timer timer = new Timer(5, new ActionListener() {
            int x = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (x < cardPanel.getWidth()) {
                    cardPanel.scrollRectToVisible(new Rectangle(x, 0, cardPanel.getWidth(), cardPanel.getHeight()));
                    x += 60; // 애니메이션 속도 조절
                } else {
                    cardLayout.show(cardPanel, target);
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    
    // 다이얼로그 생성 메서드
    public void showDialog(String message, String targetPanel) {
	    JDialog dialog = new JDialog(this, "알림", true);
	    dialog.setSize(400, 200);
	    dialog.setLocationRelativeTo(this);
     
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.add(label, BorderLayout.CENTER);

   
        Color signupHex = Color.decode("#BB83FE");
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 중앙 정렬된 FlowLayout 사용

	     // 버튼 패널에 여백 추가 (상, 하, 좌, 우)
	     buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); 
	
	     RoundedButton confirmButton  = new RoundedButton("확인", signupHex, Color.WHITE, 20);
	     confirmButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	             animateTransition(targetPanel); // 다음 패널로 전환
	             dialog.dispose();
	         }
	     });
	
	     // 버튼을 버튼 패널에 추가
	     buttonPanel.add(confirmButton);
	
	     // 버튼 패널을 다이얼로그의 아래쪽에 추가
	     dialog.add(buttonPanel, BorderLayout.SOUTH);
	        dialog.setVisible(true);
	    }

    
    public static void main(String[] args) {
        // 이벤트 스레드에서 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DialogTest();
            }
        });
    }
}