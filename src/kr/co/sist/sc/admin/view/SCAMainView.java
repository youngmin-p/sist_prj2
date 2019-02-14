package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.sc.admin.controller.SCAMainController;
import kr.co.sist.sc.admin.vo.SCALoginVO;

@SuppressWarnings("serial")
public class SCAMainView extends JFrame {
	public static String version = "0.0.1-alpha";
	
	private JButton jbtMovieManagement, jbtOnScreenManagement, jbtBookManagement, 
	                jbtMemberManagement, jbtCalculationManagement, jbtSnackManagement;
	private JLabel jlblAdminId, jlblName;
	
	public SCAMainView(SCALoginVO scal_vo) {
		super("½Ö¿ë°ü - ¸ÞÀÎ È­¸é");
		
		setTitle(getTitle() + "-" + version);
		
		jlblAdminId = new JLabel("°ü¸®ÀÚ ¾ÆÀÌµð - " + scal_vo.getAdmin_id());
		jlblAdminId.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
		jlblAdminId.setForeground(Color.WHITE);
		jlblAdminId.setBounds(750, 40, 250, 40);
		
		jlblName = new JLabel("°ü¸®ÀÚ ÀÌ¸§    - " + scal_vo.getName());
		jlblName.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
		jlblName.setForeground(Color.WHITE);
		jlblName.setBounds(750, 80, 250, 40);
		
		// function
		jbtMovieManagement = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_movie_management(145x220).png"));
		jbtMovieManagement.setContentAreaFilled(false);
		jbtMovieManagement.setFocusable(false);
		jbtMovieManagement.setBorderPainted(false);
		jbtMovieManagement.setBounds(35, 300, 145, 220);
		
		jbtOnScreenManagement = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_onscreen_management(145x220).png"));
		jbtOnScreenManagement.setContentAreaFilled(false);
		jbtOnScreenManagement.setFocusable(false);
		jbtOnScreenManagement.setBorderPainted(false);
		jbtOnScreenManagement.setBounds(190, 300, 145, 220);
		
		jbtBookManagement = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_book_management(145x220).png"));
		jbtBookManagement.setContentAreaFilled(false);
		jbtBookManagement.setFocusable(false);
		jbtBookManagement.setBorderPainted(false);
		jbtBookManagement.setBounds(345, 300, 145, 220);
		
		jbtMemberManagement = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_member_management(145x220).png"));
		jbtMemberManagement.setContentAreaFilled(false);
		jbtMemberManagement.setFocusable(false);
		jbtMemberManagement.setBorderPainted(false);
		jbtMemberManagement.setBounds(500, 300, 145, 220);
		
		jbtCalculationManagement = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_calculation_management(145x220).png"));
		jbtCalculationManagement.setContentAreaFilled(false);
		jbtCalculationManagement.setFocusable(false);
		jbtCalculationManagement.setBorderPainted(false);
		jbtCalculationManagement.setBounds(655, 300, 145, 220);
		
		jbtSnackManagement = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_snack_management(145x220).png"));
		jbtSnackManagement.setContentAreaFilled(false);
		jbtSnackManagement.setFocusable(false);
		jbtSnackManagement.setBorderPainted(false);
		jbtSnackManagement.setBounds(810, 300, 145, 220);
		
		JLabel jlblBackground = new JLabel(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_bg(1000x800).png"));
		jlblBackground.setLayout(null);
		jlblBackground.setBounds(0, 0, 1000, 800);
		
		jlblBackground.add(jlblAdminId);
		jlblBackground.add(jlblName);
		jlblBackground.add(jbtMovieManagement);
		jlblBackground.add(jbtOnScreenManagement);
		jlblBackground.add(jbtBookManagement);
		jlblBackground.add(jbtMemberManagement);
		jlblBackground.add(jbtCalculationManagement);
		jlblBackground.add(jbtSnackManagement);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		
		jp.add(jlblBackground);
		
		add(jp);
		
		// action
		SCAMainController scamc = new SCAMainController(this);
		
		jbtMovieManagement.addActionListener(scamc);
		jbtOnScreenManagement.addActionListener(scamc);
		jbtBookManagement.addActionListener(scamc);
		jbtMemberManagement.addActionListener(scamc);
		jbtCalculationManagement.addActionListener(scamc);
		jbtSnackManagement.addActionListener(scamc);
		
		addWindowListener(scamc);
		
		setSize(1000, 820);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCAMainView

	public JButton getJbtMovieManagement() {
		return jbtMovieManagement;
	}

	public JButton getJbtOnScreenManagement() {
		return jbtOnScreenManagement;
	}

	public JButton getJbtBookManagement() {
		return jbtBookManagement;
	}

	public JButton getJbtMemberManagement() {
		return jbtMemberManagement;
	}

	public JButton getJbtCalculationManagement() {
		return jbtCalculationManagement;
	}

	public JButton getJbtSnackManagement() {
		return jbtSnackManagement;
	}

	public JLabel getJlblAdminId() {
		return jlblAdminId;
	}

	public JLabel getJlblName() {
		return jlblName;
	}
	
} // class
