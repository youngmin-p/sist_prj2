package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SsangyongCinemaDAO;
import kr.co.sist.sc.admin.view.SCAMovieDetailsView;
import kr.co.sist.sc.admin.vo.SCAMovieDatailVO;

public class SCAMovieDetailController extends WindowAdapter implements ActionListener {

	private SCAMovieDetailsView scdv;
	private SCAMovieManageController scc;
	private SCAMovieDatailVO scdvo;
	private SsangyongCinemaDAO scdao;

	public SCAMovieDetailController(SCAMovieDetailsView scdv,SCAMovieManageController scc) {
		super();
		this.scdv = scdv;
		scdao=SsangyongCinemaDAO.getInstance();
		this.scc=scc;
//		scdv.getJtfmovie_code().setText(scdvo.getMovie_code());
	}

	public void detailDelete() {
		
		StringBuilder confirmMsg = new StringBuilder();
		confirmMsg.append("[").append(scdv.getJtfmovieTitle().getText()).append("]\n").append("삭제 원해?");
		String tempCode = scdv.getJtfmovieCode().getText();
		String tempTitle =scdv.getJtfmovieTitle().getText();

		try {
			switch (JOptionPane.showConfirmDialog(scdv, confirmMsg.toString(), "삭제확인", JOptionPane.OK_CANCEL_OPTION)) {
			
			case JOptionPane.OK_OPTION:
				if(	scdao.selectDeleteMovie(tempCode)){
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
			// TODO Auto-generated catch block
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
