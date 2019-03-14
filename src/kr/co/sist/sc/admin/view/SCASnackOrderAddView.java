package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCASnackOrderAddController;

@SuppressWarnings("serial")
public class SCASnackOrderAddView extends JDialog {

	private JButton jbtOrderAdd, jbtClose;
	private JComboBox<String> jcbQuan;
	private JTextField jtfSnackName, jtfPrice, jtfTotalPrice;
	private JLabel jlSnackImg;
	private JTextArea jtaSnackInfo;
	private DefaultTableModel dtmOrderList;
	
	public SCASnackOrderAddView(SCASnackManageView scasmv, String snackName, DefaultTableModel dtmOrderList) {
		super(scasmv, "주문 추가하기", true);
		this.dtmOrderList = dtmOrderList;
		setLayout(null);
		
		JLabel jlBg = new JLabel();
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack_management_6-2_add_order_bg(620x470).png"));
		jlBg.setBounds(0, 0, 620, 470);
		
		Font font = new Font("나눔바른고딕", Font.BOLD, 20);
		
		jlSnackImg = new JLabel();
		jlSnackImg.setBounds(17, 20, 325, 325);
		
		JLabel jlSnackName = new JLabel("스낵명");
		JLabel jlPrice = new JLabel("가　격");
		JLabel jlQuan = new JLabel("수　량");
		JLabel jlTotalPrice = new JLabel("총가격");
		JLabel jlSnackInfo = new JLabel("특장점");
		
		jlSnackName.setFont(font);
		jlPrice.setFont(font);
		jlQuan.setFont(font);
		jlTotalPrice.setFont(font);
		jlSnackInfo.setFont(font);

		jlSnackName.setForeground(Color.WHITE);
		jlPrice.setForeground(Color.WHITE);
		jlQuan.setForeground(Color.WHITE);
		jlTotalPrice.setForeground(Color.WHITE);
		jlSnackInfo.setForeground(Color.WHITE);
		
		// 라벨 위치, 크기 설정
		jlSnackName.setBounds(360, 20, 80, 30);
		jlPrice.setBounds(360, 65, 80, 30);
		jlQuan.setBounds(360, 110, 80, 30);
		jlTotalPrice.setBounds(360, 155, 80, 30);
		jlSnackInfo.setBounds(360, 200, 80, 30);
		
		jtfSnackName = new JTextField(snackName);
		jtfPrice = new JTextField();
		jtfTotalPrice = new JTextField();
		
		jtaSnackInfo = new JTextArea();

		jtfSnackName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfTotalPrice.setEditable(false);
		jtaSnackInfo.setEditable(false);
		
		JScrollPane jspSnackInfo = new JScrollPane(jtaSnackInfo);
		jspSnackInfo.setBounds(360, 215, 240, 160);
		
		jtfSnackName.setFont(font);
		jtfPrice.setFont(font);
		jtfTotalPrice.setFont(font);
		jtaSnackInfo.setFont(font);
		
		jtaSnackInfo.setLineWrap(true);
		
		jbtOrderAdd = new JButton();
		jbtClose = new JButton();

		jbtOrderAdd.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_order(125x40).png"));
		jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));

		String[] quan = {"1개","2개","3개","4개","5개"};
		DefaultComboBoxModel<String> dcmQuan = new DefaultComboBoxModel<String>(quan);
		jcbQuan = new JComboBox<String>(dcmQuan);
		jcbQuan.setFont(font);
		((JLabel)jcbQuan.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		jtfSnackName.setBounds(430, 20, 170, 30);
		jtfPrice.setBounds(430, 65, 170, 30);
		jcbQuan.setBounds(430, 110, 170, 30);
		jtfTotalPrice.setBounds(430, 155, 170, 30);
		jtaSnackInfo.setBounds(360, 245, 240, 100);
		
		jbtOrderAdd.setBounds(178, 370, 125, 40);
		jbtClose.setBounds(313, 370, 125, 40);

		
		jbtOrderAdd.setContentAreaFilled(false);
		jbtOrderAdd.setBorderPainted(false);
		
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		
		jlBg.add(jlSnackImg);
		jlBg.add(jlSnackName);
		jlBg.add(jlPrice);
		jlBg.add(jlQuan);
		jlBg.add(jlTotalPrice);
		jlBg.add(jlSnackInfo);
		
		jlBg.add(jtfSnackName);
		jlBg.add(jtfPrice);
		jlBg.add(jcbQuan);
		jlBg.add(jtfTotalPrice);
		jlBg.add(jtaSnackInfo);
		jlBg.add(jbtOrderAdd);
		jlBg.add(jbtClose);
		
		add(jlBg);
		
		SCASnackOrderAddController scasoac = new SCASnackOrderAddController(this, scasmv);
		addWindowListener(scasoac);
		jcbQuan.addActionListener(scasoac);
		jbtOrderAdd.addActionListener(scasoac);
		jbtClose.addActionListener(scasoac);
		setBounds(scasmv.getX()+scasmv.getWidth(), scasmv.getY(), 620, 470);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	} // SCASnackOrderAddView

	public JButton getJbtOrderAdd() {
		return jbtOrderAdd;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public JComboBox<String> getJcbQuan() {
		return jcbQuan;
	}

	public JTextField getJtfSnackName() {
		return jtfSnackName;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextField getJtfTotalPrice() {
		return jtfTotalPrice;
	}

	public JLabel getJlSnackImg() {
		return jlSnackImg;
	}

	public JTextArea getJtaSnackInfo() {
		return jtaSnackInfo;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	} // getDtmOrderList
	
} // class
