package site.pixeldetective.swing.Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpPanel extends JPanel{
	public JLabel jlb_logo;
	public JLabel jlb_id;
	public JLabel jlb_pw;
	public JLabel jlb_name;
	public JTextField jtf_id;
	public JTextField jtf_pw;
	public JTextField jtf_name;
	public JButton jbt_signup;
	public JButton jbt_cancel;
	ImageIcon backgroundImage;
	Font customFont;

    public void paintComponent(Graphics g) {
    	backgroundImage = new ImageIcon("resource/image/signup.png");
        JPanel background = new JPanel();
    	g.drawImage(backgroundImage.getImage(), 0, 0, null);
    	setOpaque(false);
    	super.paintComponent(g);
    }
	
	public SignUpPanel() {
		setLayout(null); // 절대 위치 사용

		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
		}

		jlb_logo = new JLabel("회원가입");
		jlb_logo.setFont(customFont.deriveFont(80f));
		jlb_logo.setForeground(Color.white);
		jlb_logo.setBounds(480, 180, 500, 100); // 위치와 크기 설정
		add(jlb_logo);

		jlb_id = new JLabel("아이디");
		jlb_id.setFont(customFont);
		jlb_id.setForeground(Color.white);
		jlb_id.setBounds(420, 290, 100, 40);
		add(jlb_id);

		jtf_id = new JTextField();
		jtf_id.setPreferredSize(new Dimension(300, 30));
		jtf_id.setBounds(550, 290, 300, 40);
		add(jtf_id);

		jlb_pw = new JLabel("비밀번호");
		jlb_pw.setFont(customFont);
		jlb_pw.setForeground(Color.white);
		jlb_pw.setBounds(420, 340, 150, 40);
		add(jlb_pw);

		jtf_pw = new JTextField();
		jtf_pw.setPreferredSize(new Dimension(300, 30));
		jtf_pw.setBounds(550, 340, 300, 40);
		add(jtf_pw);

		jlb_name = new JLabel("닉네임");
		jlb_name.setFont(customFont);
		jlb_name.setForeground(Color.white);
		jlb_name.setBounds(420, 390, 150, 40);
		add(jlb_name);

		jtf_name = new JTextField();
		jtf_name.setPreferredSize(new Dimension(300, 30));
		jtf_name.setBounds(550, 390, 300, 40);
		add(jtf_name);

		Color backgroundColor1 = new Color(37, 46, 145); // White
		Color textColor1 = new Color(255, 255, 255); // Black

		jbt_signup = new JButton("회원가입");
		jbt_signup.setFont(customFont);
		jbt_signup.setBackground(Color.white);
		jbt_signup.setBounds(420, 450, 200, 50);
		jbt_signup.setUI(new RoundedButtonUI(backgroundColor1, textColor1, 25)); // 라운드 버튼 UI 적용
		add(jbt_signup);

		Color backgroundColor2 = new Color(149, 156, 210); // White
		Color textColor2 = new Color(255, 255, 255); // Black

		jbt_cancel = new JButton("뒤로가기");
		jbt_cancel.setFont(customFont);
		jbt_cancel.setBackground(Color.white);
		jbt_cancel.setBounds(650, 450, 200, 50);
		jbt_cancel.setUI(new RoundedButtonUI(backgroundColor2, textColor2, 25)); // 라운드 버튼 UI 적용
		add(jbt_cancel);

		setOpaque(false); // 배경 이미지 표시를 위해 투명 설정
	}
}