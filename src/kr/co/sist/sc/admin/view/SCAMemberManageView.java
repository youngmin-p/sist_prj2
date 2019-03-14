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
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCAMemberManageController;

@SuppressWarnings("serial")
public class SCAMemberManageView extends JDialog {

	private JButton jbtMemberSelectAll, jbtMemberSelectOne, jbtClose;
	private JTextField jtfMemberId;
	private DefaultTableModel dtmMemberList;
	private JTable jtabMemberList;
	
	public SCAMemberManageView(SCAMainView scamv) {
		super(scamv, "È¸¿ø°ü¸®", true);
		setLayout(null);
		
		JLabel jlBg = new JLabel();
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/member_management_4-1_main_bg(600x800).png"));
		jlBg.setBounds(0, 0, 600, 800);

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
		
		
		String[] tabColNames = {"¾ÆÀÌµð","ÀÌ¸§","»ý³â¿ùÀÏ"};
		dtmMemberList = new DefaultTableModel(tabColNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		jtabMemberList = new JTable(dtmMemberList);
		
		jtabMemberList.getTableHeader().setFont(new Font("³ª´®°íµñ", Font.BOLD, 14));
		jtabMemberList.getTableHeader().setForeground(Color.WHITE);
		jtabMemberList.getTableHeader().setBackground(new Color(20, 35, 65));
		jtabMemberList.getTableHeader().setReorderingAllowed(false);
		jtabMemberList.getTableHeader().setResizingAllowed(false);
		jtabMemberList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		
		jtabMemberList.setOpaque(false);
		
		
		jtabMemberList.setRowHeight(30);
		jtabMemberList.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
		
		JScrollPane jspMemberList = new JScrollPane(jtabMemberList);
		jspMemberList.setBounds(230, 35, 340, 575);
		
		
		jtfMemberId = new JTextField();
		jtfMemberId.setBounds(230, 630, 230, 30);
		jtfMemberId.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		
		
		jlBg.add(jspMemberList);
		jlBg.add(jbtMemberSelectAll);
		jlBg.add(jbtMemberSelectOne);
		jlBg.add(jtfMemberId);
		jlBg.add(jbtClose);
		
		add(jlBg);
		
		SCAMemberManageController scammc = new SCAMemberManageController(this);
		addWindowListener(scammc);
		jtabMemberList.addMouseListener(scammc);
		jbtMemberSelectAll.addActionListener(scammc);
		jbtMemberSelectAll.addMouseListener(scammc);
		jbtMemberSelectOne.addActionListener(scammc);
		jbtMemberSelectOne.addMouseListener(scammc);
		jbtClose.addActionListener(scammc);
		jbtClose.addMouseListener(scammc);
		jtfMemberId.addActionListener(scammc);
		
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
