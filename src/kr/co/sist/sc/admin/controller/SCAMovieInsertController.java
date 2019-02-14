package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCAMovieInsertView;

public class SCAMovieInsertController extends WindowAdapter implements ActionListener {
	private SCAMovieInsertView scamiv;
	
	public SCAMovieInsertController(SCAMovieInsertView scamiv) {
		this.scamiv = scamiv;
		
	} // SCAMovieInsertController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scamiv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
