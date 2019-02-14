package kr.co.sist.sc.admin.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCASnackManageController;

@SuppressWarnings("serial")
public class SCASnackManageView extends JDialog {
	private JButton[][] jbtSnackImg;
	private JButton jbtSnackMenuInsert, jbtSnackMenuDelete, jbtSnackPayment, jbtSnackOrderDelete, jbtClose;
	private DefaultTableModel dtmOrderList;
	private JTable jtabOrderList;
	
	public SCASnackManageView(SCAMainView scamv) {
		super(scamv, "½º³¼ °ü¸®", true);
		
		jbtSnackImg = new JButton[2][4];
		
		
		// action
		SCASnackManageController scasmc = new SCASnackManageController(this);
		
		
		
		addWindowListener(scasmc);
		
		// size 800X720
		setSize(800, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCASnackManageView

	public JButton[][] getJbtSnackImg() {
		return jbtSnackImg;
	}

	public JButton getJbtSnackMenuInsert() {
		return jbtSnackMenuInsert;
	}

	public JButton getJbtSnackMenuDelete() {
		return jbtSnackMenuDelete;
	}

	public JButton getJbtSnackPayment() {
		return jbtSnackPayment;
	}

	public JButton getJbtSnackOrderDelete() {
		return jbtSnackOrderDelete;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}

	public JTable getJtabOrderList() {
		return jtabOrderList;
	}
	
} // class
