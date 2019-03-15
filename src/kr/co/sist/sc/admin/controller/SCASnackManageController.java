package kr.co.sist.sc.admin.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.model.SCASnackManageDAO;
import kr.co.sist.sc.admin.view.SCASnackManageView;
import kr.co.sist.sc.admin.view.SCASnackMenuAddView;
import kr.co.sist.sc.admin.view.SCASnackMenuRemoveView;
import kr.co.sist.sc.admin.view.SCASnackOrderAddView;
import kr.co.sist.sc.admin.vo.SCASnackMenuTableSelectVO;
import kr.co.sist.sc.admin.vo.SCASnackPaymentVO;

public class SCASnackManageController extends WindowAdapter implements ActionListener {

	private SCASnackManageView scasmv;

	public SCASnackManageController(SCASnackManageView scasmv) {
		this.scasmv = scasmv;
		selectSnackMenuTable();
	} // SCASnackManageController

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() instanceof JButton) {
			String btnName = ((JButton) ae.getSource()).getText();
			if (btnName != "") {
				new SCASnackOrderAddView(scasmv, btnName, scasmv.getDtmOrderList());
			} // end if
		} // end if

		if (ae.getSource() == scasmv.getJbtSnackPayment()) {
			if (scasmv.getJtabOrderList().getRowCount() > 0) {
				progressPayment();
			} else {
				JOptionPane.showMessageDialog(scasmv, "주문 목록이 비어있습니다.");
				return;
			} // end else
		} // end if

		if (ae.getSource() == scasmv.getJbtSnackOrderDelete()) {
			int selectedRow = scasmv.getJtabOrderList().getSelectedRow();
			int[] selectedRows = scasmv.getJtabOrderList().getSelectedRows();

			if (selectedRow != -1) {
				String selectedSnackName = (String) scasmv.getJtabOrderList().getValueAt(selectedRow, 1);
				switch (JOptionPane.showConfirmDialog(scasmv, "[" + selectedSnackName + "]\n주문 목록에서 삭제하시겠습니까?", "주문 삭제",
						JOptionPane.YES_NO_OPTION)) {
				case JOptionPane.OK_OPTION:
					for(int i = 0; i < selectedRows.length; i++) {
						scasmv.getDtmOrderList().removeRow(selectedRows[i]);
					} // end for
					break;
				} // end switch
			} else {
				JOptionPane.showMessageDialog(scasmv, "먼저 삭제할 주문을 선택해주세요.");
			} // end else
		} // end if

		if (ae.getSource() == scasmv.getJbtSnackMenuInsert()) {
			
			try {
				if(SCASnackManageDAO.getInstance().selectSnackMenuTable().size() < 8) {
					showSnackMenuAdd();
				} else {
					JOptionPane.showMessageDialog(scasmv, "더 이상 스낵 메뉴를 추가할 수 없습니다.");
				} // end else
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(scasmv, "DB상의 문제가 발생하였습니다. 잠시후 다시 시도해주세요.");
			} // end if
			
		} // end if

		if (ae.getSource() == scasmv.getJbtSnackMenuDelete()) {
			new SCASnackMenuRemoveView(scasmv);
		} // end if

		if (ae.getSource() == scasmv.getJbtClose()) {
			scasmv.dispose();
		} // end if
	} // actionPerformed

	private void selectSnackMenuTable() {
		JButton[][] jbtSnack = scasmv.getJbtSnackImg();
		List<SCASnackMenuTableSelectVO> snackList = new ArrayList<SCASnackMenuTableSelectVO>();
		try {
			snackList = SCASnackManageDAO.getInstance().selectSnackMenuTable();

			int listLength = 0;
			String imgDir = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack/";

			for (int i = 0; i < jbtSnack.length; i++) {
				for (int j = 0; j < jbtSnack[i].length; j++) {
					jbtSnack[i][j].setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jl_no_snack_image(187x162).png"));
					jbtSnack[i][j].setText("");
					listLength++;
				} // end for
			} // end for

			listLength = 0;
			for (int k = 0; k < jbtSnack.length; k++) {
				for (int l = 0; l < jbtSnack[k].length; l++) {
					if (listLength == snackList.size()) {
						return;
					} // end if
					jbtSnack[k][l].setIcon(new ImageIcon(imgDir +"s_snack_"+ snackList.get(listLength).getSnackImg()));
					jbtSnack[k][l].setText(snackList.get(listLength).getSnackName());
					jbtSnack[k][l].setHorizontalTextPosition(JButton.CENTER);
					jbtSnack[k][l].setVerticalTextPosition(JButton.CENTER);
					jbtSnack[k][l].setForeground(new Color(255, 255, 255, 0));
					listLength++;
				} // end for
			} // end for
			listLength = 0;

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scasmv, "DB상의 문제가 발생하였습니다. 잠시후 다시 시도해주세요.");
		} // end catch
	} // selectSnackMenuTable

	private void showSnackMenuAdd() {
		new SCASnackMenuAddView(scasmv, scasmv.getJbtSnackMenuInsert());
	} // showSnackMenuAdd

	private void progressPayment() {
		SCASnackPaymentVO scaspvo = null;
		DefaultTableModel dtmOrderlist = scasmv.getDtmOrderList();
		String snackName = "";
		int price = 0, quan = 0, itemPrice = 0, totalPrice = 0;

		StringBuilder receipt = new StringBuilder();
		
		String[] cols = {"스낵명","수　량","가　격"};
		DefaultTableModel dtmReceipt = new DefaultTableModel(cols, 0);
		JTable jtabReceipt = new JTable(dtmReceipt){
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		jtabReceipt.getTableHeader().setReorderingAllowed(false);
		jtabReceipt.getTableHeader().setResizingAllowed(false);
		jtabReceipt.setRowHeight(25);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		jtabReceipt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		jtabReceipt.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		jtabReceipt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		
		JScrollPane jspReceipt = new JScrollPane(jtabReceipt);
		
		JPanel jpReceipt = new JPanel();
		jpReceipt.setLayout(new BorderLayout());
		
		Object[] rowData = new Object[3];
		for (int i = 0; i < dtmOrderlist.getRowCount(); i++) {
			snackName = String.valueOf(scasmv.getDtmOrderList().getValueAt(i, 1));
			price = Integer.parseInt(scasmv.getDtmOrderList().getValueAt(i, 2).toString());
			quan = Integer.parseInt(scasmv.getDtmOrderList().getValueAt(i, 3).toString());
			itemPrice = price * quan;
			totalPrice += itemPrice;
			
			rowData[0] = (Object)snackName;
			rowData[1] = (Object)quan;
			rowData[2] = (Object)itemPrice;
			
			dtmReceipt.addRow(rowData);
		} // end for
			
		receipt.append("[총가격] - ").append(totalPrice).append("원\n\n")
		.append(" 결제 하시겠습니까?");
		
		JLabel jlReceipt = new JLabel(receipt.toString());
		jlReceipt.setFont(new Font("나눔고딕", Font.BOLD, 16));

		jpReceipt.add("Center",jspReceipt);
		jpReceipt.add("South",jlReceipt);
			
		switch(JOptionPane.showConfirmDialog(scasmv, jpReceipt, "영수증", JOptionPane.YES_NO_OPTION)){
			case JOptionPane.OK_OPTION :
			try {
				for(int j=0; j < dtmOrderlist.getRowCount(); j++) {
					snackName = String.valueOf(scasmv.getDtmOrderList().getValueAt(j, 1));
					quan = Integer.parseInt(scasmv.getDtmOrderList().getValueAt(j, 3).toString());
					scaspvo = new SCASnackPaymentVO(snackName, quan);
					SCASnackManageDAO.getInstance().insertPayment(scaspvo);
				} // end for
			} catch (SQLException se) {
				se.printStackTrace();
			} // end catch
			JOptionPane.showMessageDialog(scasmv, "결제가 완료되었습니다.");
			dtmOrderlist.setRowCount(0);
		} // end switch
	} // progressPayment

	@Override
	public void windowClosing(WindowEvent we) {
		scasmv.dispose();
	} // windowClosing
	
} // class
