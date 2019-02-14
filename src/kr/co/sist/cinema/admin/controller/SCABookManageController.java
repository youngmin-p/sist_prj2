package kr.co.sist.cinema.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.cinema.admin.view.SCABookManageView;
import kr.co.sist.cinema.admin.view.SCABookPremiumScreenView;
import kr.co.sist.cinema.admin.view.SCABookStandardScreenView;
import kr.co.sist.cinema.admin.vo.SCABookOnScreenVO;

public class SCABookManageController extends WindowAdapter implements ActionListener {
	private SCABookManageView scabmv;
	
	public SCABookManageController(SCABookManageView scabmv) {
		this.scabmv = scabmv;
		
	} // SCABookManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scabmv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scabmv.getJbtSearch()) {
			
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
	 */
	private void searchMovieList() {
		
	} // searchMovieList
	
	/**
	 * 현장 예매
	 * 1) 최초 수행 시 당일의 전체 상영 영화 정보를 조회
	 * 2) 영화명으로 조회 시 당일의 해당 상영 영화 정보를 조회
	 */
	private void searchBookOnScreen() {
		
	} // searchBookOnScreen
	
	/**
	 * 예매 현황
	 * 1) 최초 수행 시 전체 영화의 예매 정보를 조회
	 * 2) 영화명으로 조회 시 해당 영화의 예매 정보를 조회
	 */
	private void searchBookList() {
		
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
