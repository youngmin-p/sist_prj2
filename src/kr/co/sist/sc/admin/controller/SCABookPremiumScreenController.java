package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.admin.view.SCABookPremiumScreenView;

public class SCABookPremiumScreenController extends WindowAdapter implements ActionListener {
	private SCABookPremiumScreenView scabpsv;
	
	public SCABookPremiumScreenController(SCABookPremiumScreenView scabpsv) {
		this.scabpsv = scabpsv;
	} // SCABookPremiumScreenController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scabpsv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scabpsv.getJbtSelect()) {
			
		} // end if

		if (ae.getSource() == scabpsv.getJbtClose()) {
			scabpsv.dispose();
		} // end if
	} // actionPerformed
	
} // class
