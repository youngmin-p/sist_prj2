package kr.co.sist.sc.admin.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import kr.co.sist.sc.admin.controller.SCASnackOrderAddController;

@SuppressWarnings("serial")
public class SCASnackOrderAddView extends JDialog {
	private JButton jbtSnackMenuDelete, jbtOrderAdd, jbtClose;
	private JComboBox<String> jcbQuan;
	private JTextField jtfSnackName, jtfPrice, jtfTotalPrice;
	
	public SCASnackOrderAddView(SCASnackManageView scasmv, String image) {
		super(scasmv, "스낵 관리 - 주문 추가", true);
		
		
		
		// 
		SCASnackOrderAddController scasoac = new SCASnackOrderAddController(this);
		
		
		
		addWindowFocusListener(scasoac);
		
		// size 620X470
		setSize(620, 490);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCASnackOrderAddView

	public JButton getJbtSnackMenuDelete() {
		return jbtSnackMenuDelete;
	}

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
	
} // class
