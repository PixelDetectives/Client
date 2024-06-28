package site.pixeldetective.swing.Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import site.pixeldetective.swing.requestApi.SignUpApi;
import site.pixeldetective.swing.requestApi.UserAPI;

public class UserFrame extends JFrame{
	public UserSignUpFrame signup;
	public UserLoginFrame login;
	public JPanel user;
	public SignUpApi suapi = new SignUpApi();
	
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
					boolean signUpSuccess = suapi.postSign(signup.jtf_id.getText(), signup.jtf_name.getText(), signup.jtf_pw.getText());
					if(signUpSuccess) {
						JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "성공 메시지", JOptionPane.INFORMATION_MESSAGE);
						cardLayout.show(user, "panel login");
					}else {
						JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "실패 메시지", JOptionPane.INFORMATION_MESSAGE);
					}
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
