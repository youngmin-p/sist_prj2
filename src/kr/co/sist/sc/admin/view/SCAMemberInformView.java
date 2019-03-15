package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.admin.controller.SCAMemberInformController;
import kr.co.sist.sc.admin.vo.SCAMemberInformVO;

@SuppressWarnings("serial")
public class SCAMemberInformView extends JDialog{

	private JTextField jtfMemberId, jtfName, jtfBirthdate, jtfPhone, jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate;
	private JPasswordField jpfPassword;
	private JButton jbtMemberUpdate, jbtMemberDelete, jbtClose;
	
	public SCAMemberInformView(SCAMemberManageView scammv, SCAMemberInformVO scamivo) {
		super(scammv,"["+scamivo.getMember_id()+"]´ÔÀÇ È¸¿øÁ¤º¸", true);
		
		JLabel jlBg = new JLabel();
		jlBg.setBounds(0, 0, 390, 550);
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/member_management_4-2_info_bg(390x520).png"));
		
		JLabel jlTitle = new JLabel("È¸¿ø Á¤º¸");
		jlTitle.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 24));
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setBounds(25, 15, 150, 40);
		
		JLabel jlMemberId = new JLabel("¾ÆÀÌµð");
		JLabel jlPassword = new JLabel("ºñ¹Ð¹øÈ£");
		JLabel jlName = new JLabel("ÀÌ¸§");
		JLabel jlBirthDate = new JLabel("»ý³â¿ùÀÏ");
		JLabel jlPhone = new JLabel("ÈÞ´ëÆù ¹øÈ£");
		JLabel jlMembership = new JLabel("È¸¿ø µî±Þ");
		JLabel jlHoldPoint = new JLabel("º¸À¯ Æ÷ÀÎÆ®");
		JLabel jlAccPoint = new JLabel("´©Àû Æ÷ÀÎÆ®");
		JLabel jlInputDate = new JLabel("È¸¿ø °¡ÀÔÀÏ");
		
		jlMemberId.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlPassword.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlName.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlBirthDate.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlPhone.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlMembership.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlHoldPoint.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlAccPoint.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jlInputDate.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		
		jlMemberId.setForeground(Color.WHITE);
		jlPassword.setForeground(Color.WHITE);
		jlName.setForeground(Color.WHITE);
		jlBirthDate.setForeground(Color.WHITE);
		jlPhone.setForeground(Color.WHITE);
		jlMembership.setForeground(Color.WHITE);
		jlHoldPoint.setForeground(Color.WHITE);
		jlAccPoint.setForeground(Color.WHITE);
		jlInputDate.setForeground(Color.WHITE);
		
		jlMemberId.setBounds(25, 84, 80, 22);
		jlPassword.setBounds(25, 121, 80, 22);
		jlName.setBounds(25, 158, 80, 22);
		jlBirthDate.setBounds(25, 195, 80, 22);
		jlPhone.setBounds(25, 232, 80, 22);
		jlMembership.setBounds(25, 269, 80, 22);
		jlHoldPoint.setBounds(25, 306, 80, 22);
		jlAccPoint.setBounds(25, 343, 80, 22);
		jlInputDate.setBounds(25, 380, 80, 22);
		
		jtfMemberId = new JTextField(scamivo.getMember_id());
		jtfName = new JTextField(scamivo.getName());
		jtfBirthdate = new JTextField(scamivo.getBirthdate());
		jtfPhone = new JTextField(scamivo.getPhone());
		jtfMembership = new JTextField(scamivo.getMembership());
		jtfHoldPoint = new JTextField(String.valueOf(scamivo.getHold_point()));
		jtfAccPoint = new JTextField(String.valueOf(scamivo.getAcc_point()));
		jtfInputDate = new JTextField(scamivo.getInput_date());
		jpfPassword = new JPasswordField(scamivo.getPassword());
		
		jtfMemberId.setEditable(false);
		jtfBirthdate.setEditable(false);
		jtfMembership.setEditable(false);
		jtfHoldPoint.setEditable(false);
		jtfAccPoint.setEditable(false);
		jtfInputDate.setEditable(false);
		jpfPassword.setEditable(false);
		
		jtfMemberId.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		jtfName.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jtfBirthdate.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		jtfPhone.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 15));
		jtfMembership.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		jtfHoldPoint.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		jtfAccPoint.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		jtfInputDate.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		jpfPassword.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 15));
		
		jtfMemberId.setBounds(105, 84, 250, 22);
		jpfPassword.setBounds(105, 121, 250, 22);
		jtfName.setBounds(105, 158, 250, 22);
		jtfBirthdate.setBounds(105, 195, 250, 22);
		jtfPhone.setBounds(105, 232, 250, 22);
		jtfMembership.setBounds(105, 269, 250, 22);
		jtfHoldPoint.setBounds(105, 306, 250, 22);
		jtfAccPoint.setBounds(105, 343, 250, 22);
		jtfInputDate.setBounds(105, 380, 250, 22);
		
		jtfMemberId.setBackground(Color.LIGHT_GRAY);
		jpfPassword.setBackground(Color.LIGHT_GRAY);
		jtfBirthdate.setBackground(Color.LIGHT_GRAY);
		jtfMembership.setBackground(Color.LIGHT_GRAY);
		jtfHoldPoint.setBackground(Color.LIGHT_GRAY);
		jtfAccPoint.setBackground(Color.LIGHT_GRAY);
		jtfInputDate.setBackground(Color.LIGHT_GRAY);
		
		jbtMemberUpdate = new JButton();
		jbtMemberDelete = new JButton();
		jbtClose = new JButton();
		
		jbtMemberUpdate.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_member_update(100x30).png"));
		jbtMemberDelete.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_member_delete(100x30).png"));
		jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(100x30).png"));
		
		jbtMemberUpdate.setBounds(25, 430, 100, 30);
		jbtMemberDelete.setBounds(140, 430, 100, 30);
		jbtClose.setBounds(255, 430, 100, 30);
		
		jbtMemberUpdate.setContentAreaFilled(false);
		jbtMemberUpdate.setBorderPainted(false);
		jbtMemberDelete.setContentAreaFilled(false);
		jbtMemberDelete.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		
		jlBg.add(jlTitle);
		jlBg.add(jlMemberId);
		jlBg.add(jlPassword);
		jlBg.add(jlName);
		jlBg.add(jlBirthDate);
		jlBg.add(jlPhone);
		jlBg.add(jlMembership);
		jlBg.add(jlHoldPoint);
		jlBg.add(jlAccPoint);
		jlBg.add(jlTitle);
		jlBg.add(jlInputDate);
		
		jlBg.add(jtfMemberId);
		jlBg.add(jtfName);
		jlBg.add(jtfBirthdate);
		jlBg.add(jtfPhone);
		jlBg.add(jtfMembership);
		jlBg.add(jtfHoldPoint);
		jlBg.add(jtfAccPoint);
		jlBg.add(jtfInputDate);
		jlBg.add(jpfPassword);
		
		jlBg.add(jbtMemberUpdate);
		jlBg.add(jbtMemberDelete);
		jlBg.add(jbtClose);
		
		add(jlBg);
		
		SCAMemberInformController scamic = new SCAMemberInformController(this, scammv);
		addWindowListener(scamic);
		jbtMemberUpdate.addActionListener(scamic);
		jbtMemberDelete.addActionListener(scamic);
		jbtClose.addActionListener(scamic);
		
		setBounds(scammv.getX()+scammv.getWidth(), scammv.getY(), 390, 520);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	} // SCAMemberInformView

	public JTextField getJtfMemberId() {
		return jtfMemberId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfBirthdate() {
		return jtfBirthdate;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JTextField getJtfMembership() {
		return jtfMembership;
	}

	public JTextField getJtfHoldPoint() {
		return jtfHoldPoint;
	}

	public JTextField getJtfAccPoint() {
		return jtfAccPoint;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public JPasswordField getJpfPassword() {
		return jpfPassword;
	}

	public JButton getJbtMemberUpdate() {
		return jbtMemberUpdate;
	}

	public JButton getJbtMemberDelete() {
		return jbtMemberDelete;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

} // class
