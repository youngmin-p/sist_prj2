package kr.co.sist.cinema.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.cinema.admin.view.SCAMemberManageView;

public class SCAMemberManageController extends WindowAdapter implements ActionListener, MouseListener {
	private SCAMemberManageView scammv;
	
	public SCAMemberManageController(SCAMemberManageView scammv) {
		this.scammv = scammv;
		
	} // SCAMemberManageController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scammv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	} // actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		
	} // mouseClicked

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
} // class
