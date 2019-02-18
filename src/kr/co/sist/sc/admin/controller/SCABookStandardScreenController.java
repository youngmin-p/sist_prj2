package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SCABookManageDAO;
import kr.co.sist.sc.admin.view.SCABookStandardScreenView;
import kr.co.sist.sc.admin.vo.SCABookScreenVO;

public class SCABookStandardScreenController extends WindowAdapter implements ActionListener {
	private SCABookStandardScreenView scabssv;
	private SCABookScreenVO scabs_vo;
	private boolean[][] seatFlag;
	
	public SCABookStandardScreenController(SCABookStandardScreenView scabssv, SCABookScreenVO scabs_vo) {
		this.scabssv = scabssv;
		this.scabs_vo = scabs_vo;
		
		seatFlag = new boolean[4][5];
		
		for (int i = 0; i < seatFlag.length; i++) {
			for (int j = 0; j < seatFlag[0].length; j++) {
				seatFlag[i][j] = false;
			} // end for
		} // end for
		
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
					checkSeat(jbtSeat[i][j], i, j);
				} // end if
			} // end for
		} // end for
		
		if (ae.getSource() == scabssv.getJbtSelect()) {
			
		} // end if
		
		if (ae.getSource() == scabssv.getJbtClose()) {
			scabssv.dispose();
		} // end if
	} // actionPerformed
	
	private void checkSeat(JButton jbtSeat, int i, int j) {
		String seat_num = jbtSeat.getText();
		
		int personnel = scabs_vo.getPersonnel();
		int selCnt = 0;
		
		if (!seatFlag[i][j]) {
			if (JOptionPane.showConfirmDialog(scabssv, seat_num + "번 좌석을 선택하시겠습니까?") 
					== JOptionPane.OK_OPTION) {
				for (int n = 0; n < seatFlag.length; n++) {
					for (int m = 0; m < seatFlag[0].length; m++) {
						if (seatFlag[n][m]) {
							selCnt++;
						} // end if
					} // end for
				} // end for
				
				if ((selCnt + 1) > personnel) {
					JOptionPane.showMessageDialog(scabssv, "선택 가능한 예매 좌석을 초과했습니다.");
					return;
				} // end if
				
				jbtSeat.setIcon(new ImageIcon(
						"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_s_seat_selected(67x61).png"));
				seatFlag[i][j] = true;
				return;
			} // end if
		} // end if
		
		if (seatFlag[i][j]) {
			if (JOptionPane.showConfirmDialog(scabssv, seat_num + "번 좌석을 취소하시겠습니까?") 
					== JOptionPane.OK_OPTION) {
				jbtSeat.setIcon(new ImageIcon(
						"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_s_seat_selectable(67x61).png"));
				seatFlag[i][j] = false;
			} // end if
		} // end if
	} // checkSeat
	
	private void searchStandardScreen() {
//		try {
//			List<Integer> list = SCABookManageDAO.getInstance().selectBookSeat(screen_num);
//		} catch (SQLException sqle) {
//			
//			sqle.printStackTrace();
//		} // end catch
	} // searchStandardScreen
	
	private void setStandardScreen() {
		
	} // setStandardScreen
	
	private void addBook() {
		
	} // addBook
	
} // class
