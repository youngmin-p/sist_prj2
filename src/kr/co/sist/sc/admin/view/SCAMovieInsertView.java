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
import kr.co.sist.sc.admin.controller.SCAMovieInsertController;

@SuppressWarnings("serial")
public class SCAMovieInsertView extends JDialog {
		//830x820px
		public JLabel img;
		public JTextField jtfmovieCode, jtfmovieTitle, jtfGenre, jtfCountry, jtfDirector,jtfmovieGrade
								, jtfRunningtime, jtfPlaydate, jtfActor;
		public JTextArea  jtaSysnopsis;
		public JButton imgRegister,register,exit;
		private String adminId;
		
		public SCAMovieInsertView(SCAMovieManageView scammv, SCAMovieManageController scammc) {
			super(scammv ,"관리자"+scammv.getAdmin_id()+" 영화 등록", true);//모달로 받아야함
			adminId=scammv.getAdmin_id();
//			setTitle("관리자 :"+adminId);
			
			Font fontSet =new Font("나눔고딕", Font.BOLD, 25);
			
			JLabel moviedetailTitle=new JLabel("영화 상세");
			moviedetailTitle.setFont(new Font("나눔고딕", Font.BOLD, 25));
			img=new JLabel();
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
			//vo 에서 값을받아 설정
			 jtfmovieCode=new JTextField(50);
			 jtfmovieCode.setText("자동 생성");
			 jtfmovieTitle=new JTextField();
			 jtfGenre=new JTextField();
			 jtfCountry=new JTextField();
			 jtfDirector=new JTextField();
			 jtfmovieGrade=new JTextField();
			 jtfRunningtime=new JTextField();
			 jtfPlaydate=new JTextField();
			 jtfActor=new JTextField();
			jtaSysnopsis=new JTextArea();
			jtaSysnopsis.setLineWrap(true);
			JScrollPane jsSysnopsis=new JScrollPane(jtaSysnopsis);
			imgRegister =new JButton();
			imgRegister.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_add_img(125x40).png"));
			imgRegister.setContentAreaFilled(false);
			imgRegister.setBorderPainted(false);
			register= new JButton();
			register.setIcon(
					new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_resist(125x40).png"));
			
			register.setContentAreaFilled(false);
			register.setBorderPainted(false);
			
			exit=new JButton();
			exit.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
			
			exit.setContentAreaFilled(false);
			exit.setBorderPainted(false);
			
			moviedetailTitle.setOpaque(false);
			moviedetailTitle.setFont(fontSet);
			moviedetailTitle.setForeground(Color.WHITE);
			img.setOpaque(false);
			img.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/NoImage.png"));
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
			//////////////////////////////////////
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
			movieGrade.setBounds(350, 270, 100, 100);//70
			runningtime.setBounds(350, 315, 100, 100);//80
			playdate.setBounds(350,355, 100, 100);///90
			actor.setBounds(350, 400, 100, 100);//100
			
			
			imgRegister.setBounds(180, 20, 125, 40);
			register.setBounds(250, 730, 125, 40);
			exit.setBounds(385, 730, 125, 40);
			//필드 설정

			
			jtfmovieCode.setBounds(550,80, 185, 30); //45 씩차이남
			jtfmovieCode.setEditable(false);        
			jtfmovieCode.setForeground(Color.WHITE);
			jtfmovieCode.setFont(fontSet);
			jtfmovieCode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfmovieTitle.setBounds(550, 125, 185, 30);
			jtfmovieTitle.setForeground(Color.WHITE);
			jtfmovieTitle.setFont(fontSet);
			jtfmovieTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfmovieTitle.setCaretColor(Color.white);
			jtfGenre.setBounds(550,170 , 185, 30);
			jtfGenre.setForeground(Color.WHITE);
			jtfGenre.setFont(fontSet);
			jtfGenre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfGenre.setCaretColor(Color.white);
			jtfCountry.setBounds(550, 215, 185, 30);
			jtfCountry.setForeground(Color.WHITE);
			jtfCountry.setFont(fontSet);
			jtfCountry.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfCountry.setCaretColor(Color.white);
			jtfDirector.setBounds(550, 260, 185, 30);
			jtfDirector.setForeground(Color.WHITE);
			jtfDirector.setFont(fontSet);
			jtfDirector.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfDirector.setCaretColor(Color.white);
			
			jtfmovieGrade.setBounds(550,305, 185, 30);
			jtfmovieGrade.setForeground(Color.WHITE);
			jtfmovieGrade.setFont(fontSet);
			jtfmovieGrade.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfmovieGrade.setCaretColor(Color.white);
			jtfRunningtime.setBounds(550, 350, 185, 30);
			jtfRunningtime.setForeground(Color.WHITE);
			jtfRunningtime.setFont(fontSet);
			jtfRunningtime.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfRunningtime.setCaretColor(Color.white);
			
			jtfPlaydate.setBounds(550, 395, 185, 30);
			jtfPlaydate.setForeground(Color.WHITE);
			jtfPlaydate.setFont(fontSet);
			jtfPlaydate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfPlaydate.setCaretColor(Color.white);
			jtfActor.setBounds(550, 440, 185, 30);
			jtfActor.setForeground(Color.WHITE);
			jtfActor.setFont(fontSet);
			jtfActor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
			jtfActor.setCaretColor(Color.white);
			
			//줄거리
			
			jtaSysnopsis.setFont(fontSet);
			jtaSysnopsis.setForeground(Color.WHITE);
			jtaSysnopsis.setBackground(new Color(20,44,84));
			TitledBorder tb=new TitledBorder("영화목록");
			tb.setTitleFont(fontSet);
			tb.setTitleColor(Color.white);
			jsSysnopsis.setBorder(tb);
			jsSysnopsis.setForeground(Color.BLUE);
			jsSysnopsis.setOpaque(false);
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
			backColor.add(imgRegister);
			backColor.add(register);
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
			
			SCAMovieInsertController scamic =new SCAMovieInsertController(this, scammc);
			addWindowListener(scamic);
			
			
			imgRegister.addActionListener(scamic);
			register.addActionListener(scamic);
			exit.addActionListener(scamic);
			
			setBounds(scammv.getX()+50,scammv.getY()+50, 830, 820);
			setVisible(true);
			setEnabled(false);
			setResizable(false);
		}

		
		

		public String getAdminId() {
			return adminId;
		}
		public JLabel getImg() {
			return img;
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
		public JButton getImgRegister() {
			return imgRegister;
		}
		public JButton getRegister() {
			return register;
		}
		public JButton getExit() {
			return exit;
		}

	}

