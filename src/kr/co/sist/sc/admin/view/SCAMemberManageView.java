package kr.co.sist.sc.admin.view;

import javax.swing.JDialog;

import kr.co.sist.sc.admin.controller.SCAMemberManageController;

@SuppressWarnings("serial")
public class SCAMemberManageView extends JDialog {
	
	public SCAMemberManageView(SCAMainView scamv) {
		super(scamv, "회원 관리", true);
		
		SCAMemberManageController scammc = new SCAMemberManageController(this);
		
		addWindowListener(scammc);
		
		// size 600X700
		setSize(600, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCAMemberManageView
	
} // class
