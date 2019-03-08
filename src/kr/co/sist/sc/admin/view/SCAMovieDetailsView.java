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
import javax.swing.plaf.basic.BasicScrollBarUI;

import kr.co.sist.sc.admin.controller.SCAMovieManageController;
import kr.co.sist.sc.admin.controller.SCAMovieDetailController;
import kr.co.sist.sc.admin.vo.SCAMovieDatailVO;

@SuppressWarnings("serial")
public class SCAMovieDetailsView extends JDialog {
	//830x820px
	public SCAMovieDatailVO scdvo;
	public JTextField jtfmovieCode, jtfmovieTitle, jtfGenre, jtfCountry, jtfDirector,jtfmovieGrade
							, jtfRunningtime, jtfPlaydate, jtfActor;
	public JTextArea  jtaSysnopsis;
	public JButton delete,exit;
	
	public SCAMovieDetailsView(SCAMovieManageView scv, SCAMovieDatailVO scdvo, SCAMovieManageController scc) {
		super(scv ," 영화 상세 정보", true);//모달로 받아야함
		this.scdvo=scdvo;
		
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
				"C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/admin_detail_movie_bg(830x820).png"));
		backColor.setBounds(0, 0, 830, 820);
		String path="C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/movie/l_movie_";
		img.setIcon(new ImageIcon(path+scdvo.getMovie_img()));
		
		 jtfmovieCode=new JTextField(50);
		 jtfmovieCode.setText(scdvo.getMovie_code());
		 jtfmovieTitle=new JTextField();
		 jtfmovieTitle.setText(scdvo.getMovie_title());
		 jtfGenre=new JTextField();
		 jtfGenre.setText(scdvo.getGenre());
		 jtfCountry=new JTextField();
		 jtfCountry.setText(scdvo.getCountry());
		 jtfDirector=new JTextField();
		 jtfDirector.setText(scdvo.getDirector());
		 //등급이 빠짐
		 jtfmovieGrade=new JTextField();
		 jtfmovieGrade.setText(scdvo.getgrade());
		 
		 
		 jtfRunningtime=new JTextField();
		 jtfRunningtime.setText(Integer.toString(scdvo.getRunningtime()));// 러닝타임은 숫자여서 변환해야함
		 jtfPlaydate=new JTextField();
		 jtfPlaydate.setText(scdvo.getPlaydate());
		 jtfActor=new JTextField();
		 jtfActor.setText(scdvo.getActor());
		
		JTextArea jtaSysnopsis=new JTextArea();
		jtaSysnopsis.setText(scdvo.getSynopsis());
		jtaSysnopsis.setEditable(false);
		jtaSysnopsis.setLineWrap(true);
		jtaSysnopsis.setFont(fontSet);
		jtaSysnopsis.setForeground(Color.white);
		jtaSysnopsis.setBackground(new Color(20,44,84));
//		jtaSysnopsis.setOpaque(false);
		
		JScrollPane jsSysnopsis=new JScrollPane(jtaSysnopsis);
		
		delete= new JButton();
		delete.setIcon(
				new ImageIcon("C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/jbt_delete(125x40).png"));
		
		delete.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		
		exit=new JButton();
		exit.setIcon(new ImageIcon("C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
		
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
		img.setBounds(20,65,288,413); //왼쪽
		//오른쪽 부분 라벨 
		movieCode.setBounds(350, 50, 100, 100);//30->50 20
		movieTitle.setBounds(350,95, 100, 100);///30
		genre.setBounds(350,140 , 100, 100);//40
		country.setBounds(350, 185, 120, 100);///50
		director.setBounds(350, 230, 100, 100);///60
		movieGrade.setBounds(350, 275, 100, 100);//70
		runningtime.setBounds(350, 320, 100, 100);//80
		playdate.setBounds(350,365, 100, 100);///90
		actor.setBounds(350, 400, 100, 100);//100
		
//		jtaSysnopsis.setBounds(270, 700, 100, 50);
//		jsSysnopsis.setBounds(270, 700, 100, 30);
		
		delete.setBounds(260, 730, 125, 40);
		exit.setBounds(395, 730, 125, 40);
		//필드 설정
		jtfmovieCode.setBounds(550, 50, 185, 100);
		jtfmovieCode.setEditable(false);        
		jtfmovieCode.setForeground(Color.WHITE);
		jtfmovieCode.setFont(fontSet);
		jtfmovieCode.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		jtfmovieTitle.setBounds(550, 95, 185, 100);
		jtfmovieTitle.setEditable(false);
		jtfmovieTitle.setForeground(Color.WHITE);
		jtfmovieTitle.setFont(fontSet);
		jtfmovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfGenre.setBounds(550,140 , 185, 100);
		jtfGenre.setEditable(false);
		jtfGenre.setForeground(Color.WHITE);
		jtfGenre.setFont(fontSet);
		jtfGenre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfCountry.setBounds(550, 185, 185, 100);
		jtfCountry.setEditable(false);
		jtfCountry.setForeground(Color.WHITE);
		jtfCountry.setFont(fontSet);
		jtfCountry.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfDirector.setBounds(550, 230, 185, 100);
		jtfDirector.setEditable(false);
		jtfDirector.setForeground(Color.WHITE);
		jtfDirector.setFont(fontSet);
		jtfDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfmovieGrade.setBounds(550, 275, 185, 100);
		jtfmovieGrade.setEditable(false);
		jtfmovieGrade.setForeground(Color.WHITE);
		jtfmovieGrade.setFont(fontSet);
		jtfmovieGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfRunningtime.setBounds(550, 320, 185, 100);
		jtfRunningtime.setEditable(false);
		jtfRunningtime.setForeground(Color.WHITE);
		jtfRunningtime.setFont(fontSet);
		jtfRunningtime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfPlaydate.setBounds(550, 365, 185, 100);
		jtfPlaydate.setEditable(false);
		jtfPlaydate.setForeground(Color.WHITE);
		jtfPlaydate.setFont(fontSet);
		jtfPlaydate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfActor.setBounds(550, 400, 185, 100);
		jtfActor.setEditable(false);
		jtfActor.setForeground(Color.WHITE);
		jtfActor.setFont(fontSet);
		jtfActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		//줄거리
		jsSysnopsis.setBounds(10, 500, 800, 200);
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
		
		SCAMovieDetailController scdc =new SCAMovieDetailController(this,scc);
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
//		new SsangyoungCinemaDetailView(scv, scdvo, scc)
//	}


}
