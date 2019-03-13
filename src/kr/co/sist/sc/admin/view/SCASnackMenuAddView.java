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

import kr.co.sist.sc.admin.controller.SCAMainController;
import kr.co.sist.sc.admin.controller.SCASnackMenuAddController;

public class SCASnackMenuAddView extends JDialog {

	private JButton jbtSnackImg, jbtSnackInsert, jbtClose;
	private JTextField jtfSnackName, jtfPrice;
	private JLabel jlSnackImg;
	
	private JTextArea jtaSnackInfo;
	public SCASnackMenuAddView(SCASnackManageView scasmv, JButton jbt) {
		super(scasmv, "메뉴 추가하기", true);
		setLayout(null);
		
		// 배경 설정
		JLabel jlBg = new JLabel();
		jlBg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack_management_6-3_add_menu_bg(620x555).png"));
		jlBg.setBounds(0, 0, 620, 555);
		
		/*라벨 생성*/
		// 스낵 기본 이미지 라벨 생성 및 설정
		jlSnackImg = new JLabel();
		jlSnackImg.setBounds(17, 20, 325, 325);
		jlSnackImg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jl_no_snack_image(325x325).png"));
		
		// JTF옆에 위치할 라벨 생성
		JLabel jlSnackName = new JLabel("스낵명");
		JLabel jlPrice = new JLabel("가　격");
		JLabel jlSnackInfo = new JLabel("특장점");
		
		// 라벨 글꼴, 색상 설정
		jlSnackName.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		jlPrice.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		jlSnackInfo.setFont(new Font("나눔바른고딕", Font.BOLD, 20));

		jlSnackName.setForeground(Color.WHITE);
		jlPrice.setForeground(Color.WHITE);
		jlSnackInfo.setForeground(Color.WHITE);
		
		// 라벨 위치, 크기 설정
		jlSnackName.setBounds(360, 20, 80, 30);
		jlPrice.setBounds(360, 65, 80, 30);
		jlSnackInfo.setBounds(360, 110, 80, 30);
		
		// JTF 생성
		jtfSnackName = new JTextField();
		jtfPrice = new JTextField();
		
		// JTA 생성
		jtaSnackInfo = new JTextArea();
		
		// JScrollPane 생성
		JScrollPane jspSnackInfo = new JScrollPane(jtaSnackInfo);
		jspSnackInfo.setBounds(360, 150, 240, 193);
		
		// 글꼴 설정
		jtfSnackName.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		jtfPrice.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		jtaSnackInfo.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		
		// JTA 자동 줄바꿈 설정
		jtaSnackInfo.setLineWrap(true);
		
		// JButton 생성
		jbtSnackImg = new JButton();
		jbtSnackInsert = new JButton();
		jbtClose = new JButton();

		// 아이콘
		jbtSnackImg.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_image(125x40).png"));
		jbtSnackInsert.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_menu(125x40).png"));
		jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));

		// setbounds
		jtfSnackName.setBounds(430, 20, 170, 30);
		jtfPrice.setBounds(430, 65, 170, 30);
		jtaSnackInfo.setBounds(360, 150, 240, 193);
		
		jbtSnackImg.setBounds(120, 355, 125, 40);
		jbtSnackInsert.setBounds(178, 450, 125, 40);
		jbtClose.setBounds(313, 450, 125, 40);

		
		// 색상 설정
		jbtSnackImg.setContentAreaFilled(false);
		jbtSnackImg.setBorderPainted(false);
		
		jbtSnackInsert.setContentAreaFilled(false);
		jbtSnackInsert.setBorderPainted(false);
		
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		// .setContentAreaFilled(boolean) : 클릭될 때의 인터랙션을 제거하는 메서드
		// .setBorderPainted(boolean) : 마우스가 해당 버튼 위로 올라갔을 때의 인터랙션을 제거하는 메서드		
		
		
		// jlBg에 컴포넌트 넣기
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
		
		// 이벤트 처리
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
	
	
	
}
