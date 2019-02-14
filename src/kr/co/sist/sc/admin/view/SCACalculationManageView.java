package kr.co.sist.sc.admin.view;

import javax.swing.JDialog;

import kr.co.sist.sc.admin.controller.SCACalculationManageController;

@SuppressWarnings("serial")
public class SCACalculationManageView extends JDialog {
	
	public SCACalculationManageView(SCAMainView scamv) {
		super(scamv, "정산 관리", true);
		
		SCACalculationManageController scacmc = new SCACalculationManageController(this);
		
		
		
		addWindowListener(scacmc);
		
		// size 900X700
		setSize(900, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCACalculationManageView
	
} // class
