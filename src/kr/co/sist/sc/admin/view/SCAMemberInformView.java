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

public class SCAMemberInformView extends JDialog{

	private JTextField jtfMemberId, jtfName, jtfBirthdate, jtfPhone, jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate;
	private JPasswordField jpfPassword;
	private JButton jbtMemberUpdate, jbtMemberDelete, jbtClose;
	
	public SCAMemberInformView(SCAMemberManageView scammv, SCAMemberInformVO scamivo) {
		super(scammv,"["+scamivo.getMember_id()+"]님의 회원정보", true);
		
		// 멤버 아이디를 받아야 하는가? 재 검토 필요함
		
		// 배경, 타이틀 라벨
		JLabel jlBg = new JLabel();
		jlBg.setBounds(0, 0, 390, 550);
		jlBg.setIcon(new ImageIcon("C:/dev/workspace/cinema_prj/src/kr/co/sist/sc/admin/images/member_management_4-2_info_bg(390x520).png"));
		
		JLabel jlTitle = new JLabel("회원 정보");
		jlTitle.setFont(new Font("나눔바른고딕", Font.BOLD, 24));
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setBounds(25, 15, 150, 40);
		
		JLabel jlMemberId = new JLabel("아이디");
		JLabel jlPassword = new JLabel("비밀번호");
		JLabel jlName = new JLabel("이름");
		JLabel jlBirthDate = new JLabel("생년월일");
		JLabel jlPhone = new JLabel("휴대폰 번호");
		JLabel jlMembership = new JLabel("회원 등급");
		JLabel jlHoldPoint = new JLabel("보유 포인트");
		JLabel jlAccPoint = new JLabel("누적 포인트");
		JLabel jlInputDate = new JLabel("회원 가입일");
		
		
		// 라벨 글꼴 설정
		jlMemberId.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlPassword.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlBirthDate.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlPhone.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlMembership.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlHoldPoint.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlAccPoint.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jlInputDate.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		
		// 라벨 텍스트 색상 설정
		jlMemberId.setForeground(Color.WHITE);
		jlPassword.setForeground(Color.WHITE);
		jlName.setForeground(Color.WHITE);
		jlBirthDate.setForeground(Color.WHITE);
		jlPhone.setForeground(Color.WHITE);
		jlMembership.setForeground(Color.WHITE);
		jlHoldPoint.setForeground(Color.WHITE);
		jlAccPoint.setForeground(Color.WHITE);
		jlInputDate.setForeground(Color.WHITE);
		
		// 라벨 위치, 크기 설정
		jlMemberId.setBounds(25, 84, 80, 22);
		jlPassword.setBounds(25, 121, 80, 22);
		jlName.setBounds(25, 158, 80, 22);
		jlBirthDate.setBounds(25, 195, 80, 22);
		jlPhone.setBounds(25, 232, 80, 22);
		jlMembership.setBounds(25, 269, 80, 22);
		jlHoldPoint.setBounds(25, 306, 80, 22);
		jlAccPoint.setBounds(25, 343, 80, 22);
		jlInputDate.setBounds(25, 380, 80, 22);
		
		// JTF, JPF 생성
		jtfMemberId = new JTextField(scamivo.getMember_id());
		jtfName = new JTextField(scamivo.getName());
		jtfBirthdate = new JTextField(scamivo.getBirthdate());
		jtfPhone = new JTextField(scamivo.getPhone());
		jtfMembership = new JTextField(scamivo.getMembership());
		jtfHoldPoint = new JTextField(String.valueOf(scamivo.getHold_point()));
		jtfAccPoint = new JTextField(String.valueOf(scamivo.getAcc_point()));
		jtfInputDate = new JTextField(scamivo.getInput_date());
		jpfPassword = new JPasswordField(scamivo.getPassword());
		
		// JTF, JPF 편집 금지
		jtfMemberId.setEditable(false);
		jtfBirthdate.setEditable(false);
		jtfMembership.setEditable(false);
		jtfHoldPoint.setEditable(false);
		jtfAccPoint.setEditable(false);
		jtfInputDate.setEditable(false);
		jpfPassword.setEditable(false);
		
		// JTF, JPF 글꼴 설정
		jtfMemberId.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		jtfName.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jtfBirthdate.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		jtfPhone.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
		jtfMembership.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		jtfHoldPoint.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		jtfAccPoint.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		jtfInputDate.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		jpfPassword.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		
		// JTF 위치 설정
		jtfMemberId.setBounds(105, 84, 250, 22);
		jpfPassword.setBounds(105, 121, 250, 22);
		jtfName.setBounds(105, 158, 250, 22);
		jtfBirthdate.setBounds(105, 195, 250, 22);
		jtfPhone.setBounds(105, 232, 250, 22);
		jtfMembership.setBounds(105, 269, 250, 22);
		jtfHoldPoint.setBounds(105, 306, 250, 22);
		jtfAccPoint.setBounds(105, 343, 250, 22);
		jtfInputDate.setBounds(105, 380, 250, 22);
		
		// JButton 생성
		jbtMemberUpdate = new JButton();
		jbtMemberDelete = new JButton();
		jbtClose = new JButton();
		
		// JButton 아이콘 설정
		jbtMemberUpdate.setIcon(new ImageIcon("C:/dev/workspace/cinema_prj/src/kr/co/sist/sc/admin/images/jbt_member_update(100x30).png"));
		jbtMemberDelete.setIcon(new ImageIcon("C:/dev/workspace/cinema_prj/src/kr/co/sist/sc/admin/images/jbt_member_delete(100x30).png"));
		jbtClose.setIcon(new ImageIcon("C:/dev/workspace/cinema_prj/src/kr/co/sist/sc/admin/images/jbt_close(100x30).png"));
		
		// JButton 위치, 크기
		jbtMemberUpdate.setBounds(25, 430, 100, 30);
		jbtMemberDelete.setBounds(140, 430, 100, 30);
		jbtClose.setBounds(255, 430, 100, 30);
		
		// JButton 배경색상 없애기
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
		
		// 이벤트 처리
		SCAMemberInformController scamic = new SCAMemberInformController(this, scammv);
		addWindowListener(scamic);
		jbtMemberUpdate.addActionListener(scamic);
		jbtMemberDelete.addActionListener(scamic);
		jbtClose.addActionListener(scamic);
		
		jbtMemberUpdate.addMouseListener(scamic);
		jbtMemberDelete.addMouseListener(scamic);
		jbtClose.addMouseListener(scamic);
		
		
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
