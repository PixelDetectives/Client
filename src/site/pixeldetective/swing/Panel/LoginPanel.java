package site.pixeldetective.swing.Panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

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
    public JLabel jlb_logo;
    public JLabel jlb_id;
    public JLabel jlb_pw;
    public JTextField jtf_id;
    public JPasswordField jtf_pw;
    public JButton jbt_login;
    public JButton jbt_signup;
    
    ImageIcon backgroundImage;

    public void paintComponent(Graphics g) {
    	backgroundImage = new ImageIcon("resource/image/login.png");
        JPanel background = new JPanel();
    	g.drawImage(backgroundImage.getImage(), 0, 0, null);
    	setOpaque(false);
    	super.paintComponent(g);
    }
    
    public LoginPanel() {
    	setLayout(new FlowLayout());
        
        JPanel jpn_logo = new JPanel();
        jpn_logo.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        JPanel jpn_jtf = new JPanel(new GridLayout(3,2));
        jpn_jtf.setBorder(BorderFactory.createEmptyBorder(80, 100, 0, 150));
        JPanel jpn_jbt = new JPanel(new FlowLayout());

        jpn_jbt.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

        add(jpn_logo);
        add(jpn_jtf);
        add(jpn_jbt);

        jlb_logo = new JLabel("픽셀탐정단");
        jlb_logo.setFont(jlb_logo.getFont().deriveFont(110f));
        jlb_id = new JLabel("아이디");
        jlb_id.setFont(jlb_id.getFont().deriveFont(30f));
        jlb_id.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        jlb_pw = new JLabel("비밀번호");
        jlb_pw.setFont(jlb_pw.getFont().deriveFont(30f));
        jlb_pw.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        jtf_id = new JTextField();
        jtf_id.setPreferredSize(new Dimension(300, 30));
        jtf_pw = new JPasswordField();
        jtf_pw.setEchoChar('*'); 
        jtf_pw.setPreferredSize(new Dimension(300, 30));
        jbt_login = new JButton("로그인");
        jbt_login.setFont(jbt_login.getFont().deriveFont(30f));
        jbt_login.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        jbt_signup = new JButton("회원가입");
        jbt_signup.setFont(jbt_signup.getFont().deriveFont(30f));
        jbt_signup.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        jpn_logo.add(jlb_logo);

        jpn_jtf.add(jlb_id);
        jpn_jtf.add(jtf_id);
        jpn_jtf.add(new JLabel());
        jpn_jtf.add(new JLabel());
        jpn_jtf.add(jlb_pw);
        jpn_jtf.add(jtf_pw);

        jpn_jbt.add(jbt_login);
        jpn_jbt.add(new JLabel("                                                                          "));
        jpn_jbt.add(jbt_signup); 
        

        jbt_login.setBackground(Color.white);
        jbt_signup.setBackground(Color.white);
        jlb_logo.setForeground(Color.white);
        jlb_id.setForeground(Color.white);
        jlb_pw.setForeground(Color.white);
        jpn_logo.setOpaque(false); 
        jpn_jtf.setOpaque(false); 
        jpn_jbt.setOpaque(false); 
        
        
    }

}
