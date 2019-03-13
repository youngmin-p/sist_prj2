package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SCAMemberManageDAO;
import kr.co.sist.sc.admin.view.SCAMemberInformView;
import kr.co.sist.sc.admin.view.SCAMemberManageView;
import kr.co.sist.sc.admin.vo.SCAMemberSelectVO;
import kr.co.sist.sc.admin.vo.SCAMemberUpdateVO;

public class SCAMemberInformController extends WindowAdapter implements ActionListener {

	private SCAMemberManageView scammv;
	private SCAMemberInformView scamiv;
	
	public SCAMemberInformController(SCAMemberInformView scamiv, SCAMemberManageView scammv) {
		super();
		this.scamiv = scamiv;
		this.scammv = scammv;
	} // SCAMemberInformController
	
	// 액션 이벤트
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == scamiv.getJbtMemberUpdate()) {
			modifyMember();
		} // end if
		
		if(ae.getSource() == scamiv.getJbtMemberDelete()) {
			removeMember();
		} // end if
		
		if(ae.getSource() == scamiv.getJbtClose()) {
			scamiv.dispose();
		} // end if
	} // actionPerformed

	// 정보 수정
	private void modifyMember() {
		String memberId = scamiv.getJtfMemberId().getText();
		String name = scamiv.getJtfName().getText();
		String phone = scamiv.getJtfPhone().getText();
		String[] phoneDiv = scamiv.getJtfPhone().getText().split("-");
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(scamiv, "변경할 이름을 입력하세요.");
			scamiv.getJtfName().requestFocus();
			return;
		} // end if
		if(name.contains(" ")) {
			JOptionPane.showMessageDialog(scamiv, "이름에 공백이 들어갈 수 없습니다.");
			scamiv.getJtfName().requestFocus();
			return;
		} // end if
		if(phone.equals("")) {
			JOptionPane.showMessageDialog(scamiv, "변경할 휴대폰 번호를 입력하세요.");
			scamiv.getJtfPhone().requestFocus();
			return;
		} // end if
		if(phone.contains(" ")) {
			JOptionPane.showMessageDialog(scamiv, "휴대폰번호에 공백이 들어갈 수 없습니다.");
			scamiv.getJtfName().requestFocus();
			return;
		} // end if
		for(int i=0; i < phoneDiv.length; i++) {
			try {
				Integer.parseInt(phoneDiv[i]);
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(scamiv, "휴대폰 번호는 숫자와 \"-\"로만 입력해주세요.");
				scamiv.getJtfPhone().requestFocus();
				return;
			} // 
		} // end for
		
		if(!phoneDiv[0].equals("010") && !phoneDiv[0].equals("011") && !phoneDiv[0].equals("016") && !phoneDiv[0].equals("017") && !phoneDiv[0].equals("019")) {
			JOptionPane.showMessageDialog(scamiv, "휴대폰 앞자리를 확인해주세요.");
			scamiv.getJtfPhone().requestFocus();
			return;
		} // end if
		
		if(phoneDiv.length != 3 || phoneDiv[0].length() != 3 || phoneDiv[1].length() != 4 || phoneDiv[2].length() != 4) {
			JOptionPane.showMessageDialog(scamiv, "휴대폰 번호의 자리수를 확인해주세요.");
			scamiv.getJtfPhone().requestFocus();
			return;
		} // end if
		
		// confirm dialog 에 들어갈 메시지
		StringBuilder modifyMsg = new StringBuilder();
		modifyMsg.append("이름 : [").append(name).append("]\n")
					.append("휴대폰 번호 : [").append(phone).append("]\n")
					.append("회원 정보를 수정 하시겠습니까?");
		
		// switch 문
		switch(JOptionPane.showConfirmDialog(scamiv, modifyMsg, "회원 정보 수정", JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.OK_OPTION:
			SCAMemberUpdateVO scamuvo = new SCAMemberUpdateVO(memberId, name, phone);
			// try 문
			try {
				if(SCAMemberManageDAO.getInstance().updateMember(scamuvo)) {
					JOptionPane.showMessageDialog(scamiv, "회원정보가 수정되었습니다.");
					scamiv.dispose();
					refreshAllMember();
				} else {
					JOptionPane.showMessageDialog(scamiv, "정보 수정에 실패하였습니다.");
				} // end else
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(scamiv, "DB상의 문제가 발생하였습니다. 잠시후 다시 시도해주세요.");
			} // end catch
		
			break;
		} // end switch
		
	} // modifyMember
	
	private void removeMember() {
		String memberId = scamiv.getJtfMemberId().getText();
		StringBuilder removeMsg = new StringBuilder();
		removeMsg.append("[").append(memberId).append("]님을 회원 탈퇴 처리 하시겠습니까?");
		
		switch(JOptionPane.showConfirmDialog(scamiv, removeMsg, "탈퇴 확인", JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.OK_OPTION:
			try {
				if(SCAMemberManageDAO.getInstance().deleteMember(memberId)) {
					JOptionPane.showMessageDialog(scamiv, "["+memberId+"]님을 회원 탈퇴 처리 하였습니다.");
					refreshAllMember();
					scamiv.dispose();
				} else {
					JOptionPane.showMessageDialog(scamiv, "["+memberId+"]님을 탈퇴처리하지 못하였습니다. 잠시후 다시 시도해주세요.");
				} // end else
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(scamiv, "DB상의 문제가 발생하였습니다. 잠시후 다시 시도해주세요.");
				sqle.printStackTrace();
				return;
			} // end catch
			break;
		} // end switch	
	} // removeMember
	
	private void refreshAllMember() {
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
	} // refreshAllMember
	
	@Override
	public void windowClosing(WindowEvent we) {
		scamiv.dispose();
	} // windowClosing
	
} // class
