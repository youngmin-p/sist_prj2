package kr.co.sist.sc.admin.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.admin.controller.SCAMemberInformController;

@SuppressWarnings("serial")
public class SCAMemberInformView extends JDialog {
	private JTextField jtfMemberId, jtfName, jtfHiredate, jtfPhone, jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate;
	private JPasswordField jpfPassword;
	private JButton jbtMemberUpdate, jbtMemberDelete, jbtClose;
	
	public SCAMemberInformView(SCAMemberManageView scammv) {
		super(scammv, "회원 관리 - 회원 정보", true);
		
		SCAMemberInformController scamic = new SCAMemberInformController(this);
		
		addWindowListener(scamic);
		
		// size 320X500
		setSize(320, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCAMemberInformView

	public JTextField getJtfMemberId() {
		return jtfMemberId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfHiredate() {
		return jtfHiredate;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JTextField getJtfMembership() {
		return jtfMembership;
	}

	public JTextField getJtfHoldPoint() {
		return jtfHoldPoint;
	}

	public JTextField getJtfAccPoint() {
		return jtfAccPoint;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public JPasswordField getJpfPassword() {
		return jpfPassword;
	}

	public JButton getJbtMemberUpdate() {
		return jbtMemberUpdate;
	}

	public JButton getJbtMemberDelete() {
		return jbtMemberDelete;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}
	
} // class
