package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.model.SCABookManageDAO;
import kr.co.sist.sc.admin.view.SCABookManageView;
import kr.co.sist.sc.admin.view.SCABookPremiumScreenView;
import kr.co.sist.sc.admin.view.SCABookStandardScreenView;
import kr.co.sist.sc.admin.vo.SCABookListVO;
import kr.co.sist.sc.admin.vo.SCABookMovieListVO;
import kr.co.sist.sc.admin.vo.SCABookOnScreenVO;

public class SCABookManageController extends WindowAdapter implements ActionListener {
	private SCABookManageView scabmv;
	private List<SCABookMovieListVO> movieList;
	
	public SCABookManageController(SCABookManageView scabmv) {
		this.scabmv = scabmv;
		
		searchMovieList();
		
		// 1) 영화명이 선택되지 않았을 경우 (최초 1회 전체 조회 수행)
		searchBookOnScreen("");
		searchBookList("");
		
	} // SCABookManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scabmv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scabmv.getJbtSearch()) {
			String movieTitle = (String) scabmv.getJcbMovieTitle().getSelectedItem();
			String movieCode = "";
			
			for (int i = 0; i < movieList.size(); i++) {
				if (movieList.get(i).getMovie_title().equals(movieTitle)) {
					movieCode = movieList.get(i).getMovie_code();
				} // end if
			} // end for
			
			// 2) 영화명이 선택됐을 경우 (특정 영화명 조회 시마다 수행)
			searchBookOnScreen(movieCode);
			searchBookList(movieCode);
		} // end if
		
		if (ae.getSource() == scabmv.getJbtBook()) {
			showBookScreen();
		} // end if
		
		if (ae.getSource() == scabmv.getJbtClose()) {
			scabmv.dispose();
		} // end if
	} // actionPerformed
	
	/**
	 * 영화명을 조회하는 메서드
	 * 해당 영화명으로 조회 시 현장 예매 테이블에 
	 * 당일에 상영 중인 영화 정보를 보여준다.
	 */
	private void searchMovieList() {
		try {
			movieList = SCABookManageDAO.getInstance().selectMovieList();
			
//			Set<String> set = new HashSet<String>();
//			
//			for (int i = 0; i < list.size(); i++) {
//				set.add(list.get(i).getMovie_title());
//			} // end for
//			
//			Iterator<String> ita = set.iterator();
//			
//			while (ita.hasNext()) {
//				scabmv.getJcbMovieTitle().addItem(ita.next());	
//			} // end while
			
			for (int i = 0; i < movieList.size(); i++) {
				scabmv.getJcbMovieTitle().addItem(movieList.get(i).getMovie_title());
				
				// value check
				System.out.println((i + 1) + " / " + movieList.get(i).getMovie_code() + " / " + movieList.get(i).getMovie_title());
			} // end for
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} // end catch
	} // searchMovieList
	
	/**
	 * 현장 예매
	 * 1) 최초 수행 시 당일의 전체 상영 영화 정보를 조회
	 * 2) 영화명으로 조회 시 당일의 해당 상영 영화 정보를 조회
	 */
	private void searchBookOnScreen(String movieCode) {
		DefaultTableModel dtmOnScreenList = scabmv.getDtmOnScreenList();
		
		dtmOnScreenList.setRowCount(0);
		
		try {
			List<SCABookOnScreenVO> list = SCABookManageDAO.getInstance().selectBookOnScreen(movieCode);
			
			SCABookOnScreenVO scabos_vo = null;
			
			Object[] rowData = null;
			
			for (int i = 0; i < list.size(); i++) {
				scabos_vo = list.get(i);
				
				rowData = new Object[9];
				
				rowData[0] = new Integer(i + 1);
				rowData[1] = scabos_vo.getMovie_code();
				rowData[2] = scabos_vo.getMovie_title();
				rowData[3] = scabos_vo.getScreen_num();
				rowData[4] = scabos_vo.getScreen_name();
				rowData[5] = scabos_vo.getStart_time();
				rowData[6] = scabos_vo.getEnd_time();
				rowData[7] = new Integer(1);
				rowData[8] = scabos_vo.getSeat_count();
				
				dtmOnScreenList.addRow(rowData);
			} // end for
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scabmv, "상영 중인 영화 조회 중 문제가 발생했습니다.");
			sqle.printStackTrace();
		} // end catch
	} // searchBookOnScreen
	
	/**
	 * 예매 현황
	 * 1) 최초 수행 시 전체 영화의 예매 정보를 조회
	 * 2) 영화명으로 조회 시 해당 영화의 예매 정보를 조회
	 */
	private void searchBookList(String movieCode) {
		DefaultTableModel dtmBookList = scabmv.getDtmBookList();
		
		dtmBookList.setRowCount(0);
		
		try {
			List<SCABookListVO> list = SCABookManageDAO.getInstance().selectBookList(movieCode);
			
//			Set<SCABookListVO> set = new HashSet<SCABookListVO>();
//			
//			for (int i = 0; i < list.size(); i++) {
//				set.add(list.get(i).getMovie_title());
//			} // end for
//			
//			Iterator<String> ita = set.iterator();
//			
//			while (ita.hasNext()) {
//				scabmv.getJcbMovieTitle().addItem(ita.next());	
//			} // end while
			
			SCABookListVO scabl_vo = null;
			
			Object[] rowData = null;
			
			for (int i = 0; i < list.size(); i++) {
				scabl_vo = list.get(i);
				
				rowData = new Object[6];
				
				rowData[0] = new Integer(i + 1);
				rowData[1] = scabl_vo.getMember_id();
				rowData[2] = scabl_vo.getBook_number();
				rowData[3] = scabl_vo.getPersonnel();
				rowData[4] = scabl_vo.getSeat_num();
				rowData[5] = scabl_vo.getPayment_date();
				
				dtmBookList.addRow(rowData);
			} // end for
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scabmv, "예매 현황 조회 중 문제가 발생했습니다.");
			sqle.printStackTrace();
		} // end catch
	} // searchBookList
	
	/**
	 * 예매 버튼 클릭 시 상영관에 따라 다른 스크린을 보여주는 메서드
	 */
	public void showBookScreen() {
		// 선택한 테이블의 해당 row의 상영관 정보를 가져와서 다른 
		SCABookOnScreenVO scabos_vo = null;
		
		String move = JOptionPane.showInputDialog(scabmv, "스탠다드 : 1, 프리미엄 : 2");
		
		if (move.equals("1")) {
			new SCABookStandardScreenView(scabmv, scabos_vo);
		} // end if
		
		if (move.equals("2")) {
			new SCABookPremiumScreenView(scabmv);
		} // end if
	} // showBookScreen
	
} // class
