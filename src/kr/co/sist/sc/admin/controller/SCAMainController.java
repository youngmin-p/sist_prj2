package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCABookManageView;
import kr.co.sist.sc.admin.view.SCACalculationManageView;
import kr.co.sist.sc.admin.view.SCAMainView;
import kr.co.sist.sc.admin.view.SCAMemberManageView;
import kr.co.sist.sc.admin.view.SCAMovieManageView;
import kr.co.sist.sc.admin.view.SCAOnScreenManageView;
import kr.co.sist.sc.admin.view.SCASnackManageView;

public class SCAMainController extends WindowAdapter implements ActionListener {
	private SCAMainView scamv;
	
	public SCAMainController(SCAMainView scamv) {
		this.scamv = scamv;
		
	} // SCAMainController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scamv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scamv.getJbtMovieManagement()) {
			showMovieManage();
		} // end if
		
		if (ae.getSource() == scamv.getJbtOnScreenManagement()) {
			showOnScreenManage();
		} // end if
		
		if (ae.getSource() == scamv.getJbtBookManagement()) {
			showBookManage();
		} // end if
		
		if (ae.getSource() == scamv.getJbtMemberManagement()) {
			showMemberManage();
		} // end if
		
		if (ae.getSource() == scamv.getJbtCalculationManagement()) {
			showCalculationManage();
		} // end if
		
		if (ae.getSource() == scamv.getJbtSnackManagement()) {
			showSnackManage();
		} // end if
	} // actionPerformed
	
	private void showMovieManage() {
		String admin_id = scamv.getTitle().substring(scamv.getTitle().indexOf("[") + 1, scamv.getTitle().indexOf("/")).trim();
		
		new SCAMovieManageView(scamv, admin_id);
	} // showMovieManage
	
	private void showOnScreenManage() {
		new SCAOnScreenManageView(scamv); 
	} // showOnScreenManage
	
	private void showBookManage() {
		new SCABookManageView(scamv);
	} // showBookManage
	
	private void showMemberManage() {
		new SCAMemberManageView(scamv);
	} // showMemberManage
	
	private void showCalculationManage() {
		new SCACalculationManageView(scamv);
	} // showCalculationManage
	
	private void showSnackManage() {
		new SCASnackManageView(scamv);
	} // showSnackManage
	
} // class
