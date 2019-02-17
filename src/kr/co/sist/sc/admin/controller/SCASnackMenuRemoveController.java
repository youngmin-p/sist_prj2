package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCASnackMenuRemoveView;

public class SCASnackMenuRemoveController extends WindowAdapter implements ActionListener {
	private SCASnackMenuRemoveView scasmrv;
	
	public SCASnackMenuRemoveController(SCASnackMenuRemoveView scasmrv) {
		this.scasmrv = scasmrv;
	} // SCASnackMenuRemoveController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scasmrv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
