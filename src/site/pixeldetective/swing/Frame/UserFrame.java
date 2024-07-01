package site.pixeldetective.swing.Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import site.pixeldetective.swing.Panel.LoginPanel;
import site.pixeldetective.swing.Panel.SignUpPanel;
import site.pixeldetective.swing.requestApi.SignUpApi;
import site.pixeldetective.swing.requestApi.UserAPI;

public class UserFrame extends JFrame{


	public String jwt;
	public SignUpPanel signup;
	public LoginPanel login;
	public JPanel user;
	public SignUpApi suapi = new SignUpApi();
	public LobbyFrame lf;
	public UserAPI userapi = new UserAPI();

	public UserFrame() {

		MakeRoomFrame makeRoomFrame = new MakeRoomFrame();
		makeRoomFrame.setVisible(false);
		GameFrame gm = new GameFrame();
		gm.setVisible(false);
		lf = new LobbyFrame();
		lf.setVisible(false);
		makeRoomFrame.lobbyFrame = lf;
		lf.makeRoomFrame = makeRoomFrame;
		lf.gameFrame = gm;
		makeRoomFrame.gameFrame = gm;
		gm.lf = lf;
		gm.mk = makeRoomFrame;





		lf.setVisible(false);
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

		signup = new SignUpPanel();
		login = new LoginPanel();
		user.add(login, "panel login");
		user.add(signup, "panel signup");


		// 로그인 페이지 > 게임 로비 페이지
		login.jbt_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("login clicked");
				System.out.println(login.jtf_id.getText());
				System.out.println(login.jtf_pw.getText());
				System.out.println("login clicked");
				if (userapi.postLogin(login.jtf_id.getText(), login.jtf_pw.getText() , UserFrame.this) == false
						|| login.jtf_id.getText().length() > 11 || login.jtf_id.getText().length() < 6
						|| login.jtf_pw.getText().length() > 11 || login.jtf_pw.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.", "", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "로그인에 성공했습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					lf.jwt = jwt;
					lf.lp.jwt = jwt;
					lf.lp.socketClient.jwt = jwt;
					System.out.println("소켓의 jwt"+lf.lp.socketClient.jwt);
					setVisible(false);
					lf.setVisible(true);
				}


			}
		});

		// 로그인 페이지 > 회원가입 페이지
		login.jbt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.isVisible()) {
					cardLayout.show(user,"panel signup");
				}
			}
		});

		// 회원가입 페이지 - 회원가입 기능
		signup.jbt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(signup.isVisible()) {
					if (signup.jtf_id.getText().length() > 11 || signup.jtf_id.getText().length() < 6
							|| signup.jtf_pw.getText().length() > 11 || signup.jtf_pw.getText().length() < 4
							|| signup.jtf_name.getText().length() > 11 || signup.jtf_name.getText().length() < 3) {
						JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					}else {
						boolean signUpSuccess = suapi.postSign(signup.jtf_id.getText(), signup.jtf_name.getText(), signup.jtf_pw.getText());
						if(signUpSuccess) {
							JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "", JOptionPane.INFORMATION_MESSAGE);
							cardLayout.show(user, "panel login");
						}else {
							JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});

		// 회원가입 페이지 > 로그인 페이지
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
