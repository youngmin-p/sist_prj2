package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCASnackManageView;

public class SCASnackManageController extends WindowAdapter implements ActionListener {
	private SCASnackManageView scasmv;
	
	public SCASnackManageController(SCASnackManageView scasmv) {
		this.scasmv = scasmv;
		
	} // SCASnackManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scasmv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
