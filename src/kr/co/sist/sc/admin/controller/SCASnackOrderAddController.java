package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCASnackOrderAddView;

public class SCASnackOrderAddController extends WindowAdapter implements ActionListener {
	private SCASnackOrderAddView scasoav;
	
	public SCASnackOrderAddController(SCASnackOrderAddView scasoav) {
		this.scasoav = scasoav;
		
	} // SCASnackOrderAddController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scasoav.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
