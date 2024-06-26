package site.pixeldetective.swing.Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserFrame extends JFrame{
	public UserSignUpFrame signup;
	public UserLoginFrame login;
	public JPanel user;
	
	
	public UserFrame() {
		setTitle("픽셀탐정단");
		
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		this.user = new JPanel();
		CardLayout cardLayout = new CardLayout();
		this.user.setLayout(cardLayout);
		add(this.user);
		
		this.signup = new UserSignUpFrame();
		this.login = new UserLoginFrame();
		this.user.add(login, "panel login");
		this.user.add(signup, "panel signup");
		
		login.jbt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.isVisible()) {
					cardLayout.show(user,"panel signup");
				}else {
				}
			}
		});
		
		signup.jbt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(signup.isVisible()) {
					cardLayout.show(user, "panel login");
				}else {
				}
			}
		});
		
		signup.jbt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(signup.isVisible()) {
					cardLayout.show(user, "panel login");
				}else {
				}
			}
		});
		
		
	}
}
