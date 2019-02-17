package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
		JButton[][] jbtSeat = scabssv.getJbtSeat();
		
		for (int i = 0; i < jbtSeat.length; i++) {
			for (int j = 0; j < jbtSeat[0].length; j++) {
				if (ae.getSource() == jbtSeat[i][j]) {
					String seat_num = jbtSeat[i][j].getText();
					
					JOptionPane.showMessageDialog(scabssv, "선택한 좌석 번호는 "+ seat_num);
				} // end if
			} // end for
		} // end for
		
		// seat test
		
		if (ae.getSource() == scabssv.getJbtSelect()) {
			
		} // end if
		
		if (ae.getSource() == scabssv.getJbtClose()) {
			scabssv.dispose();
		} // end if
	} // actionPerformed
	
	private void searchStandardScreen() {
		
	} // searchStandardScreen
	
	private void setStandardScreen() {
		
	} // setStandardScreen
	
	private void addBook() {
		
	} // addBook
	
} // class
