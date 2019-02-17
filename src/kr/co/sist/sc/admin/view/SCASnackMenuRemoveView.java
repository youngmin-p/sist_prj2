package kr.co.sist.sc.admin.view;

import javax.swing.JDialog;

import kr.co.sist.sc.admin.controller.SCASnackMenuRemoveController;

@SuppressWarnings("serial")
public class SCASnackMenuRemoveView extends JDialog {
	
	public SCASnackMenuRemoveView(SCASnackMenuRemoveView scasmrv) {
		super(scasmrv, "스낵 관리 - 메뉴 삭제", true);
		
		// action
		SCASnackMenuRemoveController scasmrc = new SCASnackMenuRemoveController(this);
		
		addWindowListener(scasmrc);
		
		// size 400X620
		setSize(400, 640);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCASnackMenuRemoveView
	
} // class
