package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import kr.co.sist.sc.admin.controller.SCAMovieManageController;
import kr.co.sist.sc.admin.controller.SCAMovieDetailsController;
import kr.co.sist.sc.admin.vo.SCAMovieDatailsVO;

@SuppressWarnings("serial")
public class SCAMovieDetailsView extends JDialog {
	//830x820px
	public SCAMovieDatailsVO scamd_vo;
	public JTextField jtfmovieCode, jtfmovieTitle, jtfGenre, jtfCountry, jtfDirector,jtfmovieGrade
							, jtfRunningtime, jtfPlaydate, jtfActor;
	public JTextArea  jtaSysnopsis;
	public JButton delete,exit;
	
	public SCAMovieDetailsView(SCAMovieManageView scv, SCAMovieDatailsVO scamd_vo, SCAMovieManageController scc) {
		super(scv ," 영화 상세 정보", true);//모달로 받아야함
		this.scamd_vo=scamd_vo;
		
		Font fontSet =new Font("나눔고딕", Font.BOLD, 25);
		
		JLabel moviedetailTitle=new JLabel("영화 상세");
		JLabel img=new JLabel("이미지 부분");
		JLabel movieCode =new JLabel("영화코드");
		JLabel movieTitle =new JLabel("영화제목");
		JLabel genre =new JLabel("장르");
		JLabel country =new JLabel("제작 국가");
		JLabel director =new JLabel("감독");
		JLabel runningtime =new JLabel("러닝타임");
		JLabel playdate =new JLabel("개봉일");
		JLabel movieGrade=new JLabel("관람등급");
		JLabel actor =new JLabel("배우");
		JLabel synopsis =new JLabel("줄거리");
		JLabel backColor = new JLabel();
		backColor.setIcon(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/admin_detail_movie_bg(830x820).png"));
		backColor.setBounds(0, 0, 830, 820);
		String path="C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/movie/l_movie_";
		img.setIcon(new ImageIcon(path+scamd_vo.getMovie_img()));
		
		 jtfmovieCode=new JTextField(50);
		 jtfmovieCode.setText(scamd_vo.getMovie_code());
		 jtfmovieTitle=new JTextField();
		 jtfmovieTitle.setText(scamd_vo.getMovie_title());
		 jtfGenre=new JTextField();
		 jtfGenre.setText(scamd_vo.getGenre());
		 jtfCountry=new JTextField();
		 jtfCountry.setText(scamd_vo.getCountry());
		 jtfDirector=new JTextField();
		 jtfDirector.setText(scamd_vo.getDirector());
		 //등급이 빠짐
		 jtfmovieGrade=new JTextField();
		 jtfmovieGrade.setText(scamd_vo.getgrade());
		 
		 
		 jtfRunningtime=new JTextField();
		 jtfRunningtime.setText(Integer.toString(scamd_vo.getRunningtime()));// 러닝타임은 숫자여서 변환해야함
		 jtfPlaydate=new JTextField();
		 jtfPlaydate.setText(scamd_vo.getPlaydate());
		 jtfActor=new JTextField();
		 jtfActor.setText(scamd_vo.getActor());
		
		JTextArea jtaSysnopsis=new JTextArea();
		jtaSysnopsis.setText(scamd_vo.getSynopsis());
		jtaSysnopsis.setEditable(false);
		jtaSysnopsis.setLineWrap(true);
		jtaSysnopsis.setFont(fontSet);
		jtaSysnopsis.setForeground(Color.white);
		jtaSysnopsis.setBackground(new Color(20,44,84));
//		jtaSysnopsis.setOpaque(false);
		
		JScrollPane jsSysnopsis=new JScrollPane(jtaSysnopsis);
		
		delete= new JButton();
		delete.setIcon(
				new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_delete(125x40).png"));
		
		delete.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		
		exit=new JButton();
		exit.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
		
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		
		moviedetailTitle.setOpaque(false);
		moviedetailTitle.setFont(fontSet);
		moviedetailTitle.setForeground(Color.WHITE);
		img.setOpaque(false);
		
		movieCode .setOpaque(false);
		movieCode.setFont(fontSet);
		movieCode.setForeground(Color.WHITE);
		movieTitle.setOpaque(false);
		movieTitle.setFont(fontSet);
		movieTitle.setForeground(Color.WHITE);
		genre.setOpaque(false);
		genre.setFont(fontSet);
		genre.setForeground(Color.WHITE);
		country.setOpaque(false);
		country.setFont(fontSet);
		country.setForeground(Color.WHITE);
		director.setOpaque(false);
		director.setFont(fontSet);
		director.setForeground(Color.WHITE);
		runningtime.setOpaque(false);
		runningtime.setFont(fontSet);
		runningtime.setForeground(Color.WHITE);
		playdate.setOpaque(false);
		playdate.setFont(fontSet);
		playdate.setForeground(Color.WHITE);
		movieGrade.setOpaque(false);
		movieGrade.setFont(fontSet);
		movieGrade.setForeground(Color.WHITE);
		actor.setOpaque(false);
		actor.setFont(fontSet);
		actor.setForeground(Color.WHITE);
		synopsis.setOpaque(false);
		synopsis.setFont(fontSet);
		synopsis.setForeground(Color.WHITE);
		
		jtfmovieCode.setOpaque(false);
		jtfmovieTitle.setOpaque(false);
		jtfGenre.setOpaque(false); 
		jtfCountry.setOpaque(false); 
		jtfDirector.setOpaque(false);
		jtfmovieGrade.setOpaque(false);
		jtfRunningtime.setOpaque(false);
		jtfPlaydate.setOpaque(false);
		jtfActor.setOpaque(false);
		
		
		
		//830x820px
		moviedetailTitle.setBounds(10,20,250,30);//왼쪽
		img.setBounds(30,65,288,413); //왼쪽
		//오른쪽 부분 라벨 
		movieCode.setBounds(400, 55, 100, 50);//30->50 20
		movieTitle.setBounds(400,95, 100, 50);///30
		genre.setBounds(400,140 , 100, 50);//40
		country.setBounds(400, 185, 120, 50);///50
		director.setBounds(400, 230, 100, 50);///60
		movieGrade.setBounds(400,275, 100, 50);//70
		runningtime.setBounds(400, 320, 100, 50);//80
		playdate.setBounds(400,365, 100, 50);///90
		actor.setBounds(400, 410, 100, 50);//100
		
//		jtaSysnopsis.setBounds(270, 700, 100, 50);
//		jsSysnopsis.setBounds(270, 700, 100, 30);
		
		delete.setBounds(280, 730, 125, 40);
		exit.setBounds(415, 730, 125, 40);
		//필드 설정
		jtfmovieCode.setBounds(610, 55, 185, 50);
		jtfmovieCode.setEditable(false);        
		jtfmovieCode.setForeground(Color.WHITE);
		jtfmovieCode.setFont(fontSet);
		jtfmovieCode.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		jtfmovieTitle.setBounds(610, 95, 185, 50);
		jtfmovieTitle.setEditable(false);
		jtfmovieTitle.setForeground(Color.WHITE);
		jtfmovieTitle.setFont(fontSet);
		jtfmovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfGenre.setBounds(610,140 , 185, 50);
		jtfGenre.setEditable(false);
		jtfGenre.setForeground(Color.WHITE);
		jtfGenre.setFont(fontSet);
		jtfGenre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfCountry.setBounds(610, 185, 185, 50);
		jtfCountry.setEditable(false);
		jtfCountry.setForeground(Color.WHITE);
		jtfCountry.setFont(fontSet);
		jtfCountry.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfDirector.setBounds(610, 230, 185, 50);
		jtfDirector.setEditable(false);
		jtfDirector.setForeground(Color.WHITE);
		jtfDirector.setFont(fontSet);
		jtfDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfmovieGrade.setBounds(610, 275, 185, 50);
		jtfmovieGrade.setEditable(false);
		jtfmovieGrade.setForeground(Color.WHITE);
		jtfmovieGrade.setFont(fontSet);
		jtfmovieGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfRunningtime.setBounds(610,320, 185, 50);
		jtfRunningtime.setEditable(false);
		jtfRunningtime.setForeground(Color.WHITE);
		jtfRunningtime.setFont(fontSet);
		jtfRunningtime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfPlaydate.setBounds(610, 365, 185, 50);
		jtfPlaydate.setEditable(false);
		jtfPlaydate.setForeground(Color.WHITE);
		jtfPlaydate.setFont(fontSet);
		jtfPlaydate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfActor.setBounds(610, 410, 185, 50);
		jtfActor.setEditable(false);
		jtfActor.setForeground(Color.WHITE);
		jtfActor.setFont(fontSet);
		jtfActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//줄거리
		jsSysnopsis.setBounds(10, 500, 800, 200);
		TitledBorder tb=new TitledBorder("영화목록");
		tb.setTitleFont(fontSet);
		tb.setTitleColor(Color.white);
		jsSysnopsis.setBorder(tb);
		jsSysnopsis.setForeground(Color.BLUE);
		jsSysnopsis.setOpaque(false);
		jsSysnopsis.getVerticalScrollBar().setUI(new BasicScrollBarUI() { 
 			@Override 
			protected void configureScrollBarColors() { 
 				this.thumbColor = new Color(163, 184, 204); 
 			} // configureScrollBarColors 
		}); 
		
		jsSysnopsis.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
	
		
		setLayout(null);
		
		backColor.add(moviedetailTitle);
		backColor.add(img);
		backColor.add(movieCode);
		backColor.add(movieTitle);
		backColor.add(genre);
		backColor.add(country);
		backColor.add(director);
		backColor.add(movieGrade);
		backColor.add(runningtime);
		backColor.add(playdate);
		backColor.add(actor);
		
		backColor.add(synopsis);// 라벨
		backColor.add(delete);
		backColor.add(exit);
		
		
		backColor.add(jtfmovieCode);
		backColor.add(jtfmovieTitle);
		backColor.add(jtfGenre);
		backColor.add(jtfCountry);
		backColor.add(jtfDirector);
		backColor.add(jtfmovieGrade);
		backColor.add(jtfRunningtime);
		backColor.add(jtfPlaydate);
		backColor.add(jtfActor);
		//줄거리 추가
		backColor.add(jsSysnopsis);
	
		
		add(backColor);
		
		SCAMovieDetailsController scdc =new SCAMovieDetailsController(this,scc);
		addWindowListener(scdc);
		
		delete.addActionListener(scdc);
		exit.addActionListener(scdc);
		
		setBounds(scv.getX()+50,scv.getY()+50, 830, 820);
		setVisible(true);
		setEnabled(false);
		setResizable(false);
	}

	public JTextField getJtfmovieCode() {
		return jtfmovieCode;
	}


	public JTextField getJtfmovieTitle() {
		return jtfmovieTitle;
	}


	public JTextField getJtfGenre() {
		return jtfGenre;
	}


	public JTextField getJtfCountry() {
		return jtfCountry;
	}


	public JTextField getJtfDirector() {
		return jtfDirector;
	}


	public JTextField getJtfmovieGrade() {
		return jtfmovieGrade;
	}


	public JTextField getJtfRunningtime() {
		return jtfRunningtime;
	}


	public JTextField getJtfPlaydate() {
		return jtfPlaydate;
	}


	public JTextField getJtfActor() {
		return jtfActor;
	}


	public JTextArea getJtaSysnopsis() {
		return jtaSysnopsis;
	}


	public JButton getDelete() {
		return delete;
	}


	public JButton getExit() {
		return exit;
	}
//	public static void main(String[] args) {
//		new SsangyoungCinemaDetailView(scv, scamd_vo, scc)
//	}


}
