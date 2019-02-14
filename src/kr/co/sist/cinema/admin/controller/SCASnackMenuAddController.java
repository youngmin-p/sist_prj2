package kr.co.sist.cinema.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.cinema.admin.view.SCASnackMenuAddView;

public class SCASnackMenuAddController extends WindowAdapter implements ActionListener {
	private SCASnackMenuAddView scasmav;
	
	public SCASnackMenuAddController(SCASnackMenuAddView scasmav) {
		this.scasmav = scasmav;
		
	} // SCASnackMenuAddController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scasmav.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
