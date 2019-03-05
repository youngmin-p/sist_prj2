package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.model.SCACalculationManageDAO;
import kr.co.sist.sc.admin.view.SCACalculationManageView;
import kr.co.sist.sc.admin.vo.SCACalculationMovieSalesVO;
import kr.co.sist.sc.admin.vo.SCACalculationSnackSalesVO;

public class SCACalculationManageController extends WindowAdapter implements ActionListener {
	private SCACalculationManageView scacmv;
	private int totalMovieSales, totalSnackSales;
	
	public SCACalculationManageController(SCACalculationManageView scacmv) {
		this.scacmv = scacmv;
		
		totalMovieSales = 0;
		totalSnackSales = 0;
		
		setInitialize();
		
	} // SCACalculationManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scacmv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scacmv.getJcbMonth() 
				|| ae.getSource() == scacmv.getJcbYear()) {
			checkLeapYear();
		} // end if
		
		if (ae.getSource() == scacmv.getJbtCalculation()) {
			searchMovieSales();
			searchSnackSales();
			printTotalSales();
		} // end if
		
		if (ae.getSource() == scacmv.getJbtClose()) {
			scacmv.dispose();
		} // end if
	} // actionPerformed
	
	/**
	 * 초기 작업
	 */
	private void setInitialize() {
		setCalculationDate();
		
		searchMovieSales();
		searchSnackSales();
		printTotalSales();
	} // setInitialize
	
	/**
	 * sysdate
	 */
	private void setCalculationDate() {
		JComboBox<String> jcbYear = scacmv.getJcbYear();
		JComboBox<String> jcbMonth = scacmv.getJcbMonth();
		JComboBox<String> jcbDay = scacmv.getJcbDay();
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		// 년
		for (int i = 0; i < 5; i++) {
			jcbYear.addItem(String.valueOf(year - 4 + i));
			
		} // end for
		
		// 월
		for (int i = 0; i < 12; i++) {
			String tempMonth = "";
			
			if (i < 9) {
				tempMonth = "0" + String.valueOf(i + 1);
			} else {
				tempMonth = String.valueOf(i + 1);
			} // end else
			
			jcbMonth.addItem(tempMonth);
		} // end for
		
		// 일
		for (int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++) {
			String tempDay = "";
			
			if (i < 9) {
				tempDay = "0" + String.valueOf(i + 1);
			} else {
				tempDay = String.valueOf(i + 1);
			} // end else
			
			jcbDay.addItem(tempDay);
		} // end for
		
		jcbYear.setSelectedItem(String.valueOf(year));
		jcbMonth.setSelectedItem(month < 10 ? "0" + String.valueOf(month) : String.valueOf(month));
		jcbDay.setSelectedItem(day < 10 ? "0" + String.valueOf(day) : String.valueOf(day));
	} // setCalculationDate
	
	/**
	 * 선택된 연월에 따라 일수를 바꾸는 일
	 */
	private void checkLeapYear() {
		JComboBox<String> jcbYear = scacmv.getJcbYear();
		JComboBox<String> jcbMonth = scacmv.getJcbMonth();
		JComboBox<String> jcbDay = scacmv.getJcbDay();
		
		jcbDay.removeAllItems();
		
		Calendar cal = Calendar.getInstance();

		int year = Integer.parseInt((String) jcbYear.getSelectedItem());
		int month = Integer.parseInt((String) jcbMonth.getSelectedItem());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		// 연월을 변경
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		
		if (month == 2) {
			if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
				// 윤년일 떄
				day = 29;
			} else {
				// 윤년이 아닐 때
				day = 28;
			} // end else
		} else {
			day = cal.getActualMaximum(Calendar.DATE);
		} // end else
		
		// 일
		for (int i = 0; i < day; i++) {
			String tempDay = "";
			
			if (i < 9) {
				tempDay = "0" + String.valueOf(i + 1);
			} else {
				tempDay = String.valueOf(i + 1);
			} // end else
			
			jcbDay.addItem(tempDay);
		} // end for
	} // checkLeapYear
	
	/**
	 * 영화 상영 수익 조회
	 */
	private void searchMovieSales() {
		DefaultTableModel MovieSalesList = scacmv.getDtmMovieSalesList();
		
		MovieSalesList.setRowCount(0);
		
		try {
			String searchDate = 
					(String) scacmv.getJcbYear().getSelectedItem() + "-" + 
					(String) scacmv.getJcbMonth().getSelectedItem() + "-" + 
					(String) scacmv.getJcbDay().getSelectedItem();
			
			List<SCACalculationMovieSalesVO> list = SCACalculationManageDAO.getInstance().selectMovieSales(searchDate);
			
			SCACalculationMovieSalesVO scacms_vo = null;
			
			Object[] rowData = null;
			
			for (int i = 0; i < list.size(); i++) {
				scacms_vo = list.get(i);
				
				rowData = new Object[5];
				
				rowData[0] = new Integer(i + 1);
				rowData[1] = scacms_vo.getMovie_code();
				rowData[2] = scacms_vo.getMovie_title();
				rowData[3] = scacms_vo.getPersonnel();
				rowData[4] = scacms_vo.getTotal_price();
				
				totalMovieSales += Integer.parseInt(scacms_vo.getTotal_price().replaceAll(",", ""));
				
				MovieSalesList.addRow(rowData);
			} // end for
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scacmv, "영화 상영 수익 조회 중 문제가 발생했습니다.");
			sqle.printStackTrace();
		} // end catch
	} // searchMovieSales
	
	/**
	 * 스낵 판매 수익 조회
	 */
	private void searchSnackSales() {
		DefaultTableModel SnackSalesList = scacmv.getDtmSnackSalesList();
		
		SnackSalesList.setRowCount(0);
		
		try {
			String searchDate = 
					(String) scacmv.getJcbYear().getSelectedItem() + "-" + 
					(String) scacmv.getJcbMonth().getSelectedItem() + "-" + 
					(String) scacmv.getJcbDay().getSelectedItem();
			
			List<SCACalculationSnackSalesVO> list = SCACalculationManageDAO.getInstance().selectSnackSales(searchDate);
			
			SCACalculationSnackSalesVO scacss_vo = null;
			
			Object[] rowData = null;
			
			for (int i = 0; i < list.size(); i++) {
				scacss_vo = list.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = new Integer(i + 1);
				rowData[1] = scacss_vo.getSnack_name();
				rowData[2] = scacss_vo.getQuan();
				rowData[3] = scacss_vo.getTotal_price();
				
				totalSnackSales += Integer.parseInt(scacss_vo.getTotal_price().replaceAll(",", ""));
				
				SnackSalesList.addRow(rowData);
			} // end for
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scacmv, "스낵 판매 수익 조회 중 문제가 발생했습니다.");
			sqle.printStackTrace();
		} // end catch
	} // searchSnackSales
	
	/**
	 * 총 매출 조회
	 */
	private void printTotalSales() {
		JLabel jlblMovieSales = scacmv.getJlblMovieSales();
		JLabel jlblSnackSales = scacmv.getJlblSnackSales();
		JLabel jlblTotalSales = scacmv.getJlblTotalSales();
		
		int totalMovieSales = this.totalMovieSales;
		int totalSnackSales = this.totalSnackSales;
		int totalSales = totalMovieSales + totalSnackSales;
		
		// 3자릿수마다 ',' 붙이기
		StringBuffer convMovie = new StringBuffer(String.valueOf(totalMovieSales)).reverse();
		StringBuffer convSnack = new StringBuffer(String.valueOf(totalSnackSales)).reverse();
		StringBuffer convTotal = new StringBuffer(String.valueOf(totalSales)).reverse();
		
		for (int i = 0; i < convTotal.length(); i++) {
			if (i % 4 == 3) {
				if (!"0".equals(convMovie.toString())) {
					convMovie.insert(i, ",");
				} // end if
				
				if (!"0".equals(convSnack.toString())) {
					convSnack.insert(i, ",");
				} // end if
				
				if (!"0".equals(convTotal.toString())) {
					convTotal.insert(i, ",");
				} // end if
			} // end if
		} // end for
		
		jlblMovieSales.setText(convMovie.reverse().toString() + "원");
		jlblSnackSales.setText(convSnack.reverse().toString() + "원");
		jlblTotalSales.setText(convTotal.reverse().toString() + "원");
		
		this.totalMovieSales = 0;
		this.totalSnackSales = 0;
	} // printTotalSales
	
} // class
