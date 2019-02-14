package kr.co.sist.cinema.admin.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SCASnackMenuAddView extends JDialog {
	private JButton jbtSnackImg, jbtSnackInsert, jbtClose;
	private JTextField jtfSnackName, jtfPrice, jtfSnackInfo;
	
	public SCASnackMenuAddView(SCASnackManageView scasmv, JButton jbtSnackImg) {
		super(scasmv, "스낵 관리 - 메뉴 추가", true);
		
		this.jbtSnackImg = jbtSnackImg;
		
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
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

	public JTextField getJtfSnackInfo() {
		return jtfSnackInfo;
	}
	
} // class
