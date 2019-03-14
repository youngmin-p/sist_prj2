package kr.co.sist.sc.admin.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SCAMovieManageDAO;
import kr.co.sist.sc.admin.view.SCAMovieInsertView;
import kr.co.sist.sc.admin.vo.SCAMovieInsertVO;

public class SCAMovieInsertController extends WindowAdapter implements ActionListener {
	private SCAMovieInsertView scrv;
	private SCAMovieManageDAO scamm_dao;
	private SCAMovieManageController scmc;
	private String uploadImgName;

	public SCAMovieInsertController(SCAMovieInsertView scrv, SCAMovieManageController scmc) {
		this.scrv = scrv;
		this.scmc = scmc;
		scamm_dao = SCAMovieManageDAO.getInstance();
		uploadImgName="null";
		
		scrv.addWindowListener( new WindowAdapter() {
		    public void windowOpened( java.awt.event.WindowEvent e ){
		        scrv.jtfmovieTitle.requestFocus();
		    }
		}); 
	}

	public void registerMovie() {
		
			if (scrv.getJtfmovieTitle().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "타이틀 입력하세요");
				scrv.getJtfmovieTitle().setText("");
				scrv.getJtfmovieTitle().requestFocus();
				return;

			}
			// 이미지삽입 해야함
			if ("null".equals(uploadImgName)) {
				JOptionPane.showMessageDialog(scrv, " 이미지를 선택해주세요.");
				return;
			} // end if
			if (scrv.getJtfGenre().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "장르.입력하세요");
				scrv.getJtfGenre().setText("");
				scrv.getJtfGenre().requestFocus();
				return;
			}
			if (scrv.getJtfCountry().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "국가.입력하세요");
				scrv.getJtfCountry().setText("");
				scrv.getJtfCountry().requestFocus();
				return;
			}
			if (scrv.getJtfDirector().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "감독 입력하세요");
				scrv.getJtfDirector().setText("");
				scrv.getJtfDirector().requestFocus();
				return;
			}
			if (scrv.getJtfmovieGrade().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "등급 입력하세요");
				scrv.getJtfmovieGrade().setText("");
				scrv.getJtfmovieGrade().requestFocus();
				return;
			}
			if(scrv.getJtfmovieGrade().getText().length()>2) {
				JOptionPane.showMessageDialog(scrv, "등급은 2 자리 입니다");
				scrv.getJtfmovieGrade().setText("");
				scrv.getJtfmovieGrade().requestFocus();
			}
			if (scrv.getJtfPlaydate().getText().equals("")) {//빈공간일때
				JOptionPane.showMessageDialog(scrv, "날짜 입력");
				scrv.getJtfPlaydate().setText("");
				scrv.getJtfPlaydate().requestFocus();
				return;
			}
			if(scrv.getJtfPlaydate().getText().length()>8) {
				JOptionPane.showMessageDialog(scrv, "날짜의 형식은 EX) 20191111 ");
				scrv.getJtfPlaydate().setText("");
				scrv.getJtfPlaydate().requestFocus();
				return;
			}
			if (scrv.getJtaSysnopsis().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "줄거리 입력하세요");
				scrv.getJtaSysnopsis().setText("");
				scrv.getJtaSysnopsis().requestFocus();
				return;
			}
			if (scrv.getJtfActor().getText().equals("")) {
				JOptionPane.showMessageDialog(scrv, "배우 입력하세요" );
				scrv.getJtfActor().setText("");
				scrv.getJtfActor().requestFocus();
				return;
			}
			// 숫자 로 넘버 입셋션 으로 처리
			@SuppressWarnings("unused")
			int num = 0;
			try {
				num = Integer.parseInt(scrv.getJtfRunningtime().getText().trim());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(scrv, "러닝 타임은 숫자만 입력가능");
				return;
			}
			
		// 추가
		// 파일을 등록 후 파일명을 가지고 와야함
		SCAMovieInsertVO scami_vo = new SCAMovieInsertVO(scrv.getJtfmovieTitle().getText(), uploadImgName,
				scrv.getJtfGenre().getText(), scrv.getJtfCountry().getText(), scrv.getJtfDirector().getText(),
				scrv.getJtfmovieGrade().getText(), scrv.getJtfPlaydate().getText(), scrv.getJtaSysnopsis().getText(),
				scrv.getJtfActor().getText(), scrv.getAdminId(), Integer.parseInt(scrv.getJtfRunningtime().getText()));

		try {
			StringBuilder tempTitle=new StringBuilder();
			tempTitle.append("[ ").append(scrv.getJtfmovieTitle().getText()).append(" ]").append("을").append("\n").append("등록 하시겟습니까?");
			switch (JOptionPane.showConfirmDialog(scrv, tempTitle,"등록 확인", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION:
				scamm_dao.registerMovie(scami_vo);
				JOptionPane.showMessageDialog(null, "추가완료");
				scrv.dispose();
				scmc.showMovie();
				break;
			case JOptionPane.CANCEL_OPTION:
				JOptionPane.showMessageDialog(null, "취소");
				break;
			}
			
			
			
			
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void setImg() {
		FileDialog fdOpen = new FileDialog(scrv, "포스터 업로드", FileDialog.LOAD);
		
		fdOpen.setVisible(true);
	
		String path = fdOpen.getDirectory();
		String name = fdOpen.getFile();
		
		if(null==path) {
			return;
		}
		
		name=name.substring(8);
		
		boolean flag = false;
		if (path != null) {
			String[] extFlag = { "jpg", "gif", "jpeg", "png", "bmp" };

			for (String ext : extFlag) {

				if (name.toLowerCase().endsWith(ext)) { // 업로드 가능 확장자
					flag = true;
				} // end if

			} // end for
			if (flag) {
				uploadImgName = name;
				scrv.getImg().setIcon(new ImageIcon(path+"l_movie_" + name));
			} else {
				JOptionPane.showMessageDialog(scrv, name + "은 이미지가 아닙니다.");
			} // end if

		} // end if

	} // setImg

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == scrv.getImgRegister()) {
			
			setImg();
		}
		if (ae.getSource() == scrv.getRegister()) {
			registerMovie();
		}
		if (ae.getSource() == scrv.getExit()) {
			scrv.dispose();
		}
	}

}
