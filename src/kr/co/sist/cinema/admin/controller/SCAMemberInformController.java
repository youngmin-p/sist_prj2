package kr.co.sist.cinema.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.cinema.admin.view.SCAMemberInformView;

public class SCAMemberInformController extends WindowAdapter implements ActionListener {
	private SCAMemberInformView scamiv;
	
	public SCAMemberInformController(SCAMemberInformView scamiv) {
		this.scamiv = scamiv;
		
	} // SCAMemberInformController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scamiv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed
	
} // class
