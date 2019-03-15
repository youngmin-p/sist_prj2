package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SCAMovieManageDAO;
import kr.co.sist.sc.admin.view.SCAMovieDetailsView;

public class SCAMovieDetailsController extends WindowAdapter implements ActionListener {

	private SCAMovieDetailsView scdv;
	private SCAMovieManageController scc;
	private SCAMovieManageDAO scamm_dao;

	public SCAMovieDetailsController(SCAMovieDetailsView scdv,SCAMovieManageController scc) {
		super();
		this.scdv = scdv;
		scamm_dao=SCAMovieManageDAO.getInstance();
		this.scc=scc;
	}

	public void detailDelete() {
		
		StringBuilder confirmMsg = new StringBuilder();
		confirmMsg.append("[").append(scdv.getJtfmovieTitle().getText()).append("]\n").append("삭제 ?");
		String tempCode = scdv.getJtfmovieCode().getText();
		String tempTitle =scdv.getJtfmovieTitle().getText();

		try {
			switch (JOptionPane.showConfirmDialog(scdv, confirmMsg.toString(), "삭제확인", JOptionPane.OK_CANCEL_OPTION)) {
			
			case JOptionPane.OK_OPTION:
				if(	scamm_dao.selectDeleteMovie(tempCode)){
					JOptionPane.showMessageDialog(null, "["+tempTitle+"] 삭제완료");
					scdv.dispose();
				};
				scc.showMovie();
				break;
			case JOptionPane.NO_OPTION:
					JOptionPane.showMessageDialog(scdv, "취소");
				break;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scdv.getDelete()) {

			detailDelete();

		}
		if (ae.getSource() == scdv.exit) {
			scdv.dispose();
		}

	}

}
