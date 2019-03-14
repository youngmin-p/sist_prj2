package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.sc.admin.controller.SCASnackMenuAddController;

@SuppressWarnings("serial")
public class SCASnackMenuAddView extends JDialog {

	private JButton jbtSnackImg, jbtSnackInsert, jbtClose;
	private JTextField jtfSnackName, jtfPrice;
	private JLabel jlSnackImg;
	
	private JTextArea jtaSnackInfo;
	public SCASnackMenuAddView(SCASnackManageView scasmv, JButton jbt) {
		super(scasmv, "¸Þ´º Ãß°¡ÇÏ±â", true);
		setLayout(null);
		
		JLabel jlBg = new JLabel();
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack_management_6-3_add_menu_bg(620x555).png"));
		jlBg.setBounds(0, 0, 620, 555);
		
		jlSnackImg = new JLabel();
		jlSnackImg.setBounds(17, 20, 325, 325);
		jlSnackImg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jl_no_snack_image(325x325).png"));
		
		JLabel jlSnackName = new JLabel("½º³¼¸í");
		JLabel jlPrice = new JLabel("°¡¡¡°Ý");
		JLabel jlSnackInfo = new JLabel("Æ¯ÀåÁ¡");
		
		jlSnackName.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		jlPrice.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		jlSnackInfo.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));

		jlSnackName.setForeground(Color.WHITE);
		jlPrice.setForeground(Color.WHITE);
		jlSnackInfo.setForeground(Color.WHITE);
		
		jlSnackName.setBounds(360, 20, 80, 30);
		jlPrice.setBounds(360, 65, 80, 30);
		jlSnackInfo.setBounds(360, 110, 80, 30);
		
		jtfSnackName = new JTextField();
		jtfPrice = new JTextField();
		
		jtaSnackInfo = new JTextArea();
		
		JScrollPane jspSnackInfo = new JScrollPane(jtaSnackInfo);
		jspSnackInfo.setBounds(360, 150, 240, 193);
		
		jtfSnackName.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		jtfPrice.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		jtaSnackInfo.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		
		jtaSnackInfo.setLineWrap(true);
		
		jbtSnackImg = new JButton();
		jbtSnackInsert = new JButton();
		jbtClose = new JButton();

		jbtSnackImg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_image(125x40).png"));
		jbtSnackInsert.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_menu(125x40).png"));
		jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));

		jtfSnackName.setBounds(430, 20, 170, 30);
		jtfPrice.setBounds(430, 65, 170, 30);
		jtaSnackInfo.setBounds(360, 150, 240, 193);
		
		jbtSnackImg.setBounds(120, 355, 125, 40);
		jbtSnackInsert.setBounds(178, 450, 125, 40);
		jbtClose.setBounds(313, 450, 125, 40);

		jbtSnackImg.setContentAreaFilled(false);
		jbtSnackImg.setBorderPainted(false);
		
		jbtSnackInsert.setContentAreaFilled(false);
		jbtSnackInsert.setBorderPainted(false);
		
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		
		jlBg.add(jlSnackImg);
		jlBg.add(jlSnackName);
		jlBg.add(jlPrice);
		jlBg.add(jlSnackInfo);
		
		jlBg.add(jtfSnackName);
		jlBg.add(jtfPrice);
		jlBg.add(jspSnackInfo);
		jlBg.add(jbtSnackImg);
		jlBg.add(jbtSnackInsert);
		jlBg.add(jbtClose);
		
		add(jlBg);
		
		SCASnackMenuAddController scasmac = new SCASnackMenuAddController(scasmv, this);
		addWindowListener(scasmac);
		jbtSnackImg.addActionListener(scasmac);
		jbtSnackInsert.addActionListener(scasmac);
		jbtClose.addActionListener(scasmac);
		setBounds(scasmv.getX()+scasmv.getWidth(), scasmv.getY(), 620, 555);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	} // SCASnackMenuAddView

	public JButton getJbtSnackImg() {
		return jbtSnackImg;
	}

	public JButton getJbtSnackInsert() {
		return jbtSnackInsert;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public JTextField getJtfSnackName() {
		return jtfSnackName;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextArea getjtaSnackInfo() {
		return jtaSnackInfo;
	}

	public JLabel getJlSnackImg() {
		return jlSnackImg;
	}
	
} // class
