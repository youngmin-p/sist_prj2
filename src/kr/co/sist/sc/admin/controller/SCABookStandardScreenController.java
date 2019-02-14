package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCABookStandardScreenView;

public class SCABookStandardScreenController extends WindowAdapter implements ActionListener {
	private SCABookStandardScreenView scabssv;
	
	public SCABookStandardScreenController(SCABookStandardScreenView scabssv) {
		this.scabssv = scabssv;
		
	} // SCABookStandardScreenController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scabssv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
	private void searchStandardScreen() {
		
	} // searchStandardScreen
	
} // class
