package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCAMovieInsertView;
import kr.co.sist.sc.admin.view.SCAMovieManageView;

public class SCAMovieManageController extends WindowAdapter implements ActionListener {
	private SCAMovieManageView scammv;
	
	public SCAMovieManageController(SCAMovieManageView scammv) {
		this.scammv = scammv;
		
	} // SCAMovieManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scammv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scammv.getJbtMovieInsert()) {
			new SCAMovieInsertView(scammv);
		} // end if
		
		if (ae.getSource() == scammv.getJbtClose()) {
			scammv.dispose();
		} // end if
	} // actionPerformed
	
} // class
