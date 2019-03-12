package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCAMemberController;

public class SCAMemberManageView extends JDialog {

	private JButton jbtMemberSelectAll, jbtMemberSelectOne, jbtClose;
	private JTextField jtfMemberId;
	private DefaultTableModel dtmMemberList;
	private JTable jtabMemberList;
	
	public SCAMemberManageView(SCAMainView scamv) {
		super(scamv, "회원관리", true);
		setLayout(null);
		
		// 배경이미지가 들어간 JLabel 생성, 아이콘, 크기, 위치 설정  
		JLabel jlBg = new JLabel();
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/member_management_4-1_main_bg(600x800).png"));
		jlBg.setBounds(0, 0, 600, 800);

		// 등급별 누적 포인트 조건을 설명하기 위한 StringBuilder 생성
		StringBuilder sbSilver = new StringBuilder();
		StringBuilder sbGold = new StringBuilder();
		StringBuilder sbVip = new StringBuilder();
		sbSilver.append("Silver<br>").append("10000~50000 포인트");
		sbGold.append("Gold<br>").append("50000~100000 포인트");
		sbVip.append("VIP<br>").append("100000 포인트 이상");
		
		JLabel jlSilver, jlGold, jlVip;
		jlSilver = new JLabel(sbSilver.toString());
		jlGold = new JLabel(sbGold.toString());
		jlVip = new JLabel(sbVip.toString());
		
		jlSilver.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		jlSilver.setForeground(Color.WHITE);
		jlGold.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		jlGold.setForeground(Color.WHITE);
		jlVip.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
		jlVip.setForeground(Color.WHITE);
		
		jlSilver.setBounds(30, 50, 350, 60);
		jlSilver.setBounds(30, 110, 350, 60);
		jlSilver.setBounds(30, 170, 350, 60);
		
		jbtMemberSelectAll = new JButton();
		jbtMemberSelectOne = new JButton();
		jbtClose = new JButton();
		
		jbtMemberSelectAll.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_member_select_all(125x40).png"));
		jbtMemberSelectOne.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_member_select_one(100x30).png"));
		jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
		
		jbtMemberSelectAll.setBounds(275, 690, 125, 40);
		jbtMemberSelectOne.setBounds(470, 630, 100, 30);
		jbtClose.setBounds(415, 690, 125, 40);
		
		
		jbtMemberSelectAll.setContentAreaFilled(false);
		jbtMemberSelectAll.setBorderPainted(false);
		jbtMemberSelectOne.setContentAreaFilled(false);
		jbtMemberSelectOne.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		
		
		String[] tabColNames = {"아이디","이름","생년월일"};
		dtmMemberList = new DefaultTableModel(tabColNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		jtabMemberList = new JTable(dtmMemberList);
		
		jtabMemberList.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 14));
		jtabMemberList.getTableHeader().setForeground(Color.WHITE);
//		jtabMemberList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		jtabMemberList.getTableHeader().setBackground(new Color(20, 35, 65));
		jtabMemberList.getTableHeader().setReorderingAllowed(false);
		jtabMemberList.getTableHeader().setResizingAllowed(false);
		jtabMemberList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		
		jtabMemberList.setOpaque(false);
		
		
		jtabMemberList.setRowHeight(30);
		jtabMemberList.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		
		JScrollPane jspMemberList = new JScrollPane(jtabMemberList);
		jspMemberList.setBounds(230, 35, 340, 575);
		
		
		jtfMemberId = new JTextField();
		jtfMemberId.setBounds(230, 630, 230, 30);
		jtfMemberId.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		
		
		
		jlBg.add(jspMemberList);
//		jlBg.add(jlSilver);
//		jlBg.add(jlGold);
//		jlBg.add(jlVip);
		jlBg.add(jbtMemberSelectAll);
		jlBg.add(jbtMemberSelectOne);
		jlBg.add(jtfMemberId);
		jlBg.add(jbtClose);
		
		add(jlBg);
		
		SCAMemberController scamc = new SCAMemberController(this);
		addWindowListener(scamc);
//		addMouseListener(scamc);
		jtabMemberList.addMouseListener(scamc);
		jbtMemberSelectAll.addActionListener(scamc);
		jbtMemberSelectAll.addMouseListener(scamc);
		jbtMemberSelectOne.addActionListener(scamc);
		jbtMemberSelectOne.addMouseListener(scamc);
		jbtClose.addActionListener(scamc);
		jbtClose.addMouseListener(scamc);
		jtfMemberId.addActionListener(scamc);
		
		setBounds(scamv.getX()+(scamv.getWidth() - 600) / 2, scamv.getY(), 600, 800);
		setResizable(false);
		setVisible(true);
		
		
	} // SCAMemberManageView

	public JButton getJbtMemberSelectAll() {
		return jbtMemberSelectAll;
	}

	public JButton getJbtMemberSelectOne() {
		return jbtMemberSelectOne;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public JTextField getJtfMemberId() {
		return jtfMemberId;
	}

	public DefaultTableModel getDtmMemberList() {
		return dtmMemberList;
	}

	public JTable getJtabMemberList() {
		return jtabMemberList;
	}

	

} // class
