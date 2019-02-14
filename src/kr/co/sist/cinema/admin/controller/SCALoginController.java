package kr.co.sist.cinema.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.cinema.admin.model.SCALoginDAO;
import kr.co.sist.cinema.admin.view.SCALoginView;
import kr.co.sist.cinema.admin.view.SCAMainView;
import kr.co.sist.cinema.admin.vo.SCALoginCheckVO;
import kr.co.sist.cinema.admin.vo.SCALoginVO;

public class SCALoginController extends WindowAdapter implements ActionListener {
	private SCALoginView scalv;
	
	public SCALoginController(SCALoginView scalv) {
		this.scalv = scalv;
		
	} // SCALoginController
	
	@Override
	public void windowClosing(WindowEvent we) {
		scalv.dispose();
	} // windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JTextField jtfId = scalv.getJtfId();
		JPasswordField jpfPasswd = scalv.getJpfPasswd();
		
		if (ae.getSource() == scalv.getJtfId()) {
			jpfPasswd.requestFocus();
		} // end if
		
		if (ae.getSource() == scalv.getJpfPasswd() || ae.getSource() == scalv.getJbtLogin()) {
			String id = jtfId.getText().trim();
			String passwd = new String(jpfPasswd.getPassword()).trim();
			
			if (id.equals("")) {
				JOptionPane.showMessageDialog(scalv, "아이디를 입력해주세요.");
				jpfPasswd.setText("");
				jtfId.setText("");
				jtfId.requestFocus();
				return;
			} // end if
			
			if (passwd.equals("")) {
				JOptionPane.showMessageDialog(scalv, "비밀번호를 입력해주세요.");
				jpfPasswd.setText("");
				jpfPasswd.requestFocus();
				return;
			} // end if
			
			if (!id.equals("") && !passwd.equals("")) {
				try {
					SCALoginCheckVO scalc_vo = new SCALoginCheckVO(id, passwd);
					
					SCALoginVO scal_vo = 
							SCALoginDAO.getInstance().loginAdmin(scalc_vo);
					
					String name = scal_vo.getName();
					
					if (!name.equals("")) {
						JOptionPane.showMessageDialog(scalv, name + "님, 접속에 성공했습니다.");
						
						new SCAMainView(scal_vo);
						
						scalv.dispose();
					} // end if
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(scalv, "아이디 또는 비밀번호를 확인해주세요.");
					jpfPasswd.setText("");
					jtfId.setText("");
					jtfId.requestFocus();
					npe.printStackTrace();
				} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(scalv, "로그인 중 문제가 발생했습니다.");
					sqle.printStackTrace();
				} // end catch			
			} // end if
		} // end if
	} // actionPerformed
	
} // class
