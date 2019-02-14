package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCAMovieDetailsView;

public class SCAMovieDetailsController extends WindowAdapter implements ActionListener {
	private SCAMovieDetailsView scamdv;
	
	public SCAMovieDetailsController(SCAMovieDetailsView scamdv) {
		this.scamdv = scamdv;
		
	} // SCAMovieDetailsController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scamdv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
