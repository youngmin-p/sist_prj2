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

import kr.co.sist.sc.admin.controller.SCASnackMenuAddController;
import kr.co.sist.sc.admin.controller.SCASnackOrderAddController;

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
		
		// 배경 설정
		JLabel jlBg = new JLabel();
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack_management_6-2_add_order_bg(620x470).png"));
		jlBg.setBounds(0, 0, 620, 470);
		
		// 공통 폰트
		Font font = new Font("나눔바른고딕", Font.BOLD, 20);
		
		/*라벨 생성*/
		// 스낵 기본 이미지 라벨 생성 및 설정
		jlSnackImg = new JLabel();
		jlSnackImg.setBounds(17, 20, 325, 325);
		jlSnackImg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_snack_default_img(325x325).png"));
		
		// JTF옆에 위치할 라벨 생성
		JLabel jlSnackName = new JLabel("스낵명");
		JLabel jlPrice = new JLabel("가　격");
		JLabel jlQuan = new JLabel("수　량");
		JLabel jlTotalPrice = new JLabel("총가격");
		JLabel jlSnackInfo = new JLabel("특장점");
		
		// 라벨 글꼴, 색상 설정
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
		
		// JTF 생성
		jtfSnackName = new JTextField(snackName);
		jtfPrice = new JTextField();
		jtfTotalPrice = new JTextField();
		
		// JTA 생성
		jtaSnackInfo = new JTextArea();

		// JTF, JTA 편집 막기
		jtfSnackName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfTotalPrice.setEditable(false);
		jtaSnackInfo.setEditable(false);
		
		// JScrollPane 생성
		JScrollPane jspSnackInfo = new JScrollPane(jtaSnackInfo);
		jspSnackInfo.setBounds(360, 215, 240, 160);
		
		// 글꼴 설정
		jtfSnackName.setFont(font);
		jtfPrice.setFont(font);
		jtfTotalPrice.setFont(font);
		jtaSnackInfo.setFont(font);
		
		// JTA 자동 줄바꿈 설정
		jtaSnackInfo.setLineWrap(true);
		
		// JButton 생성
		jbtOrderAdd = new JButton();
		jbtClose = new JButton();

		// JButton 아이콘
		jbtOrderAdd.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_order(125x40).png"));
		jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));

		// 콤보박스
		String[] quan = {"1개","2개","3개","4개","5개"};
		DefaultComboBoxModel<String> dcmQuan = new DefaultComboBoxModel<String>(quan);
		jcbQuan = new JComboBox<String>(dcmQuan);
		jcbQuan.setFont(font);
		((JLabel)jcbQuan.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		// setbounds
		jtfSnackName.setBounds(430, 20, 170, 30);
		jtfPrice.setBounds(430, 65, 170, 30);
		jcbQuan.setBounds(430, 110, 170, 30);
		jtfTotalPrice.setBounds(430, 155, 170, 30);
		jtaSnackInfo.setBounds(360, 245, 240, 100);
		
		jbtOrderAdd.setBounds(178, 370, 125, 40);
		jbtClose.setBounds(313, 370, 125, 40);

		
		// 색상 설정
		
		jbtOrderAdd.setContentAreaFilled(false);
		jbtOrderAdd.setBorderPainted(false);
		
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		// .setContentAreaFilled(boolean) : 클릭될 때의 인터랙션을 제거하는 메서드
		// .setBorderPainted(boolean) : 마우스가 해당 버튼 위로 올라갔을 때의 인터랙션을 제거하는 메서드		
		
		
		// jlBg에 컴포넌트 넣기
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
		
		// 이벤트 처리
		SCASnackOrderAddController scasoac = new SCASnackOrderAddController(this, scasmv);
		addWindowListener(scasoac);
		jcbQuan.addActionListener(scasoac);
		jbtOrderAdd.addActionListener(scasoac);
		jbtOrderAdd.addMouseListener(scasoac);
		jbtClose.addActionListener(scasoac);
		jbtClose.addMouseListener(scasoac);
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
