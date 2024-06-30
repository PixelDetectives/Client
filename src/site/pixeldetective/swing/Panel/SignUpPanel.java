package site.pixeldetective.swing.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void paintComponent(Graphics g) {
    	backgroundImage = new ImageIcon("resource/image/login.png");
        JPanel background = new JPanel();
    	g.drawImage(backgroundImage.getImage(), 0, 0, null);
    	setOpaque(false);
    	super.paintComponent(g);
    }
	
	public SignUpPanel() {
		
		setLayout(new FlowLayout());

		JPanel jpn_logo = new JPanel();
		JPanel jpn_jtf = new JPanel(new GridLayout(5,2));
		jpn_jtf.setBorder(BorderFactory.createEmptyBorder(100, 100, 0, 150));
		JPanel jpn_jbt = new JPanel();
		
		jpn_jbt.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		
		add(jpn_logo);
		add(jpn_jtf);
		add(jpn_jbt);
		
		jlb_logo = new JLabel("회원가입");
		jlb_logo.setFont(jlb_logo.getFont().deriveFont(110f));
		jlb_id = new JLabel("아이디");
		jlb_id.setFont(jlb_id.getFont().deriveFont(30f));
        jlb_id.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		jlb_pw = new JLabel("비밀번호");
		jlb_pw.setFont(jlb_pw.getFont().deriveFont(30f));
        jlb_pw.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		jlb_name = new JLabel("닉네임");
		jlb_name.setFont(jlb_pw.getFont().deriveFont(30f));
		jlb_name.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		jtf_id = new JTextField();
		jtf_id.setPreferredSize(new Dimension(300, 30));
		jtf_pw = new JTextField();
		jtf_pw.setPreferredSize(new Dimension(300, 30));
		jtf_name = new JTextField(20);
		jtf_name.setPreferredSize(new Dimension(300, 30));
		jbt_signup = new JButton("회원가입");
		jbt_signup.setFont(jbt_signup.getFont().deriveFont(30f));
		jbt_signup.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		jbt_cancel = new JButton("뒤로가기");
		jbt_cancel.setFont(jbt_cancel.getFont().deriveFont(30f));
		jbt_cancel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		jpn_logo.add(jlb_logo);
		
		jpn_jtf.add(jlb_id);
		jpn_jtf.add(jtf_id);
		jpn_jtf.add(new JLabel());
        jpn_jtf.add(new JLabel());
		jpn_jtf.add(jlb_pw);
		jpn_jtf.add(jtf_pw);
		jpn_jtf.add(new JLabel());
        jpn_jtf.add(new JLabel());
		jpn_jtf.add(jlb_name);
		jpn_jtf.add(jtf_name);
		
		jpn_jbt.add(jbt_signup);
		jpn_jbt.add(new JLabel("                                                                          "));
		jpn_jbt.add(jbt_cancel);
		
        jbt_signup.setBackground(Color.white);
        jbt_cancel.setBackground(Color.white);
        jlb_logo.setForeground(Color.white);
        jlb_id.setForeground(Color.white);
        jlb_pw.setForeground(Color.white);
        jlb_name.setForeground(Color.white);
        jpn_logo.setOpaque(false); 
        jpn_jtf.setOpaque(false); 
        jpn_jbt.setOpaque(false); 
	}
}
