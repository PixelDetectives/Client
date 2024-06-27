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
        
		user = new JPanel();
		CardLayout cardLayout = new CardLayout();
		user.setLayout(cardLayout);
		add(user);
		
		signup = new UserSignUpFrame();
		login = new UserLoginFrame();
		user.add(login, "panel login");
		user.add(signup, "panel signup");
		
		login.jbt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.isVisible()) {
					cardLayout.show(user,"panel signup");
				}
			}
		});
		
		signup.jbt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(signup.isVisible()) {
					
					cardLayout.show(user, "panel login");
				}
			}
		});
		
		signup.jbt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(signup.isVisible()) {
					cardLayout.show(user, "panel login");
				}
			}
		});
		
		
	}
}
