package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.model.SCACalculationManageDAO;
import kr.co.sist.sc.admin.view.SCACalculationManageView;
import kr.co.sist.sc.admin.vo.SCACalculationSnackSalesVO;

public class SCACalculationManageController extends WindowAdapter implements ActionListener {
	private SCACalculationManageView scacmv;
	private int totalMovieSales, totalSnackSales;
	
	public SCACalculationManageController(SCACalculationManageView scacmv) {
		this.scacmv = scacmv;
		
		totalMovieSales = 0;
		totalSnackSales = 0;
		
	} // SCACalculationManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scacmv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scacmv.getJbtCalculation()) {
			// 정산 처리
			searchSnackSales();
		} // end if
		
		if (ae.getSource() == scacmv.getJbtClose()) {
			scacmv.dispose();
		} // end if
	} // actionPerformed
	
	private void searchMovieSales() {
		
	} // searchMovieSales
	
	private void searchSnackSales() {
		DefaultTableModel SnackSalesList = scacmv.getDtmSnackSalesList();
		
		SnackSalesList.setRowCount(0);
		
		try {
			List<SCACalculationSnackSalesVO> list = SCACalculationManageDAO.getInstance().selectSnackSales("2019-02-21");
			
			SCACalculationSnackSalesVO scacss_vo = null;
			
			Object[] rowData = null;
			
			for (int i = 0; i < list.size(); i++) {
				scacss_vo = list.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = new Integer(i + 1);
				rowData[1] = scacss_vo.getSnack_name();
				rowData[2] = scacss_vo.getQuan();
				rowData[3] = scacss_vo.getTotal_price();
				
				SnackSalesList.addRow(rowData);
			} // end for
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scacmv, "스낵 판매 수익 조회 중 문제가 발생했습니다.");
			sqle.printStackTrace();
		} // end catch
	} // searchSnackSales
	
	private void printTotalSales() {
		
	} // printTotalSales
	
	private void printTotalCalculation() {
		
	} // printTotalCalculation
	
} // class
