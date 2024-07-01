package site.pixeldetective.swing.Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
    public JLabel jlb_id;
    public JLabel jlb_pw;
    public JTextField jtf_id;
    public JPasswordField jtf_pw;
    public JButton jbt_login;
    public JButton jbt_signup;
    ImageIcon backgroundImage;
    Font customFont;

    public void paintComponent(Graphics g) {
    	backgroundImage = new ImageIcon("resource/image/login2.png");
        JPanel background = new JPanel();
    	g.drawImage(backgroundImage.getImage(), 0, 0, null);
    	setOpaque(false);
    	super.paintComponent(g);
    }
    
    public LoginPanel() {
        setLayout(null);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/DungGeunMo.otf")).deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30); // 오류 발생 시 기본 폰트로 대체
        }

        jlb_id = new JLabel("아이디");
        jlb_id.setFont(customFont);
        jlb_id.setForeground(Color.white);
        jlb_id.setBounds(400, 340, 100, 40); // 위치와 크기 설정
        add(jlb_id);

        jtf_id = new JTextField();
        jtf_id.setPreferredSize(new Dimension(300, 30));
        jtf_id.setBounds(550, 340, 300, 40);
        add(jtf_id);

        jlb_pw = new JLabel("비밀번호");
        jlb_pw.setFont(customFont);
        jlb_pw.setForeground(Color.white);
        jlb_pw.setBounds(400, 390, 150, 40);
        add(jlb_pw);

        jtf_pw = new JPasswordField();
        jtf_pw.setEchoChar('*');
        jtf_pw.setPreferredSize(new Dimension(300, 30));
        jtf_pw.setBounds(550, 390, 300, 40);
        add(jtf_pw);

        jbt_login = new JButton("로그인");
        jbt_login.setFont(customFont);
        jbt_login.setBackground(Color.white);
        jbt_login.setBounds(400, 440, 200, 50);
        add(jbt_login);

        jbt_signup = new JButton("회원가입");
        jbt_signup.setFont(customFont);
        jbt_signup.setBackground(Color.white);
        jbt_signup.setBounds(650, 440, 200, 50);
        add(jbt_signup);
        
    }

}

