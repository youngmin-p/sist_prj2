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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.view.SCAMovieManageView;
import kr.co.sist.sc.admin.view.SCAMovieInsertView;
import kr.co.sist.sc.admin.model.SCAMovieManageDAO;
import kr.co.sist.sc.admin.view.SCAMovieDetailsView;
import kr.co.sist.sc.admin.vo.SCAMovieDatailsVO;
import kr.co.sist.sc.admin.vo.SCAMovieManageVO;

public class SCAMovieManageController extends WindowAdapter implements ActionListener, MouseListener {

	private String selectedMovieCode;
	private SCAMovieManageView scv;
	private SCAMovieManageDAO scamm_dao;

	public SCAMovieManageController(SCAMovieManageView scv) {
		this.scv = scv;
		scamm_dao = SCAMovieManageDAO.getInstance();
		showMovie();

	}

	public void showMovie() {
		DefaultTableModel dtmMovie = scv.getDtmModel();
		dtmMovie.setRowCount(0);
		try {
			SCAMovieManageVO slv = null;
			String imgPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/movie/s_movie_";
			List<SCAMovieManageVO> listmovie = scamm_dao.showMovie();
			Object[] rowData = null;

			for (int i = 0; i < listmovie.size(); i++) {
				slv = listmovie.get(i);
				rowData = new Object[4];
				rowData[0] = i + 1;
				rowData[1]= slv.getMovie_Code();
//				rowData[2] =slv.getMovie_Img();// new ImageIcon(imgPath + slv.getMovieImg());
				rowData[2] = new ImageIcon(imgPath +slv.getMovie_Img());// new ImageIcon(imgPath + slv.getMovieImg());
				rowData[3] = slv.getMovie_Title();

				dtmMovie.addRow(rowData);

			}
			if (listmovie.isEmpty()) {
				JOptionPane.showMessageDialog(scv, "입력 없음");

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scv, "db문제발생");
			e.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {

		if (me.getClickCount() == 2) {
			int selectedRow = 0;
			String temp = "";
			JTable jta = scv.getTableMovieList();
			int p = jta.rowAtPoint(me.getPoint());
			if (p >= 0 && p < jta.getRowCount()) {
				jta.setRowSelectionInterval(p, p);// 입력된 시작행과 끝행 사이의 행을 선택하는 일
			} else {
				jta.clearSelection();
			}

			JTable jt = scv.getTableMovieList();

			selectedRow = p;
			selectedMovieCode = (String) jt.getValueAt(jt.getSelectedRow(), 1);
			try {
				SCAMovieDatailsVO scamd_vo=scamm_dao.selectMovie(selectedMovieCode);
				new SCAMovieDetailsView(scv,scamd_vo,this);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == scv.getRegesterMovie()) {
			// 영화 등록 버튼 누를시 작동 코드
			new SCAMovieInsertView(scv, this);
		}
		if (ae.getSource() == scv.getExit()) {
			scv.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		scv.dispose();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

//	public String getselectedMovieCode() {
//		return selectedMovieCode;
//	}

}
