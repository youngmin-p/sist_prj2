package kr.co.sist.cinema.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.cinema.admin.controller.SCALoginController;

@SuppressWarnings("serial")
public class SCALoginView extends JFrame {
	private JTextField jtfId;
	private JPasswordField jpfPasswd;
	private JButton jbtLogin;
	
	public SCALoginView() {
		super("½Ö¿ë°ü  - ·Î±×ÀÎ");
		
		jtfId = new JTextField("young");
		jtfId.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jtfId.setForeground(Color.WHITE);
		jtfId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		jtfId.setOpaque(false);
		jtfId.setBounds(85, 150, 125, 40);
		
		jpfPasswd = new JPasswordField("1234");
		jpfPasswd.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jpfPasswd.setForeground(Color.WHITE);
		jpfPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		jpfPasswd.setOpaque(false);
		jpfPasswd.setBounds(85, 200, 125, 40);
		
		jbtLogin = new JButton(new ImageIcon(
				"C:/dev/workspace/cinema_prj/src/kr/co/sist/cinema/admin/images/jbt_login(125x40).png"));
		jbtLogin.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jbtLogin.setFocusable(false);
		jbtLogin.setContentAreaFilled(false);
		jbtLogin.setBorderPainted(false);
		jbtLogin.setBounds(85, 350, 125, 40);
		
		JLabel jlblBackground = new JLabel(new ImageIcon(
				"C:/dev/workspace/cinema_prj/src/kr/co/sist/cinema/admin/images/admin_login_bg(300x500).png"));
		jlblBackground.setLayout(null);
		jlblBackground.setBounds(0, 0, 300, 500);
		
		jlblBackground.add(jtfId);
		jlblBackground.add(jpfPasswd);
		jlblBackground.add(jbtLogin);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		
		jp.add(jlblBackground);
		
		add(jp);
		
		SCALoginController scalc = new SCALoginController(this);
		
		jtfId.addActionListener(scalc);
		jpfPasswd.addActionListener(scalc);
		jbtLogin.addActionListener(scalc);
		
		addWindowListener(scalc);
		
		setSize(300, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCALoginView

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPasswd() {
		return jpfPasswd;
	}

	public JButton getJbtLogin() {
		return jbtLogin;
	}
	
} // class
