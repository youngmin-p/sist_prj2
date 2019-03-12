package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import kr.co.sist.sc.admin.model.SCAMemberManageDAO;
import kr.co.sist.sc.admin.view.SCAMemberInformView;
import kr.co.sist.sc.admin.view.SCAMemberManageView;
import kr.co.sist.sc.admin.vo.SCAMemberInformVO;
import kr.co.sist.sc.admin.vo.SCAMemberSelectVO;

public class SCAMemberManageController extends WindowAdapter implements ActionListener, MouseListener {

	private SCAMemberManageView scammv;

	public SCAMemberManageController(SCAMemberManageView scammv) {
		this.scammv = scammv;
		
		searchAllMember();
		
	} // SCAMemberController
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == scammv.getJbtMemberSelectAll()) {
			searchAllMember();
		} // end if

		if (ae.getSource() == scammv.getJtfMemberId() || ae.getSource() == scammv.getJbtMemberSelectOne()) {
			searchOneMember();
		} // end if

		if (ae.getSource() == scammv.getJbtClose()) {
			scammv.dispose();
		} // end if

	} // actionPerformed

	private void searchAllMember() {
		try {
			List<SCAMemberSelectVO> list = SCAMemberManageDAO.getInstance().selectAllMember();

			Object[] rowData = new Object[3];
			SCAMemberSelectVO scamsvo = null;

			scammv.getDtmMemberList().setRowCount(0);
			for (int i = 0; i < list.size(); i++) {
				scamsvo = list.get(i);
				rowData[0] = scamsvo.getMember_id();
				rowData[1] = scamsvo.getName();
				rowData[2] = scamsvo.getBirthDate();

				
				scammv.getDtmMemberList().addRow(rowData);
			} // end for
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	} // searchAllMember

	private void searchOneMember() {
		String memberId = scammv.getJtfMemberId().getText();
		
		if(memberId.equals("")) {
			JOptionPane.showMessageDialog(scammv, "먼저 검색할 아이디를 입력하세요.");
			return;
		} // end if
		
		try {
			SCAMemberSelectVO	scamvo = SCAMemberManageDAO.getInstance().selectOneMember(memberId.trim());
			Object[] rowData = new Object[3];
			rowData[0] = scamvo.getMember_id();
			rowData[1] = scamvo.getName();
			rowData[2] = scamvo.getBirthDate();
			
			scammv.getDtmMemberList().setRowCount(0);
			scammv.getDtmMemberList().addRow(rowData);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(scammv, "["+memberId+"] - 조회된 결과가 없는 ID 입니다.");
			scammv.getJtfMemberId().setText("");
			scammv.getJtfMemberId().requestFocus();
		} // end catch
		
	} // searchOneMember

	private void showMemberInform() {
		JTable jt = scammv.getJtabMemberList();
		String selectedItem = jt.getValueAt(jt.getSelectedRow(), 0).toString();
		
		try {
			SCAMemberInformVO scamivo = SCAMemberManageDAO.getInstance().selectMemberInform(selectedItem);
			new SCAMemberInformView(scammv, scamivo);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} // end catch
		
	} // showMemberInform
	
	@Override
	public void windowClosing(WindowEvent we) {
		scammv.dispose();
	} // windowClosing

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount() == 2 && me.getSource() == scammv.getJtabMemberList()) {
			showMemberInform();
		} // end if
	} // mouseClicked

	
	@Override
	public void mousePressed(MouseEvent me) {
	}
	@Override
	public void mouseReleased(MouseEvent me) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
} // class
