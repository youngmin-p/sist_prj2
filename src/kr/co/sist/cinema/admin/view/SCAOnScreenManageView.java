package kr.co.sist.cinema.admin.view;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class SCAOnScreenManageView extends JDialog {
	
	public SCAOnScreenManageView(SCAMainView scamv) {
		super(scamv, "상영 관리", true);
		
		
		
		// size 900X700
		setSize(900, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCAOnScreenManageView
	
} // class
