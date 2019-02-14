package kr.co.sist.cinema.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.cinema.admin.view.SCACalculationManageView;

public class SCACalculationManageController extends WindowAdapter implements ActionListener {
	private SCACalculationManageView scacmv;
	
	public SCACalculationManageController(SCACalculationManageView scacmv) {
		this.scacmv = scacmv;
		
	} // SCACalculationManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scacmv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
