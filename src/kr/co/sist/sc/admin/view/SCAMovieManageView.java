package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCAMovieManageController;

@SuppressWarnings("serial")
public class SCAMovieManageView extends JDialog {

	private JButton regesterMovie, exit;
	private DefaultTableModel dtmModel;
	private JTable tableMovieList;
	private JScrollPane jspList;
	private JLabel backColor;
	private String admin_id;

	public SCAMovieManageView(SCAMainView scamv, String admin_id) {
		admin_id="hee"; // 관리자 iD 
		setTitle("관리자 :"+admin_id);
		  // 테이블 내용 가운데 정렬하기
	      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	      dtcr.setHorizontalAlignment(SwingConstants.CENTER);

	    Font setFont=new Font("나눔고딕", Font.BOLD, 20);
		JLabel movieManagement = new JLabel("영화관리");
		movieManagement.setBounds(20, 20, 125, 60);
//		movieManagement.setContentAreaFilled(false);
//		movieManagement.setBorderPainted(false);
		movieManagement.setOpaque(false); // 라벨의 글자 이외의 부분을 투명하게
		movieManagement.setFont(new Font("나눔고딕", Font.BOLD, 25));
		movieManagement.setForeground(Color.WHITE);
		String colum[] = { "순번", "코드","포스터", "제목" };
		dtmModel = new DefaultTableModel(colum, 4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
			

	/*		protected void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				super.dtmModel(g);
			}*/
		};
//		dtmModel.
//		tempColumSet = new JTable(dtmModel);
		tableMovieList = new JTable(dtmModel);
		tableMovieList.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 22));
		tableMovieList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		tableMovieList.getTableHeader().setForeground(Color.white);
		tableMovieList.getTableHeader().setBackground(new Color(20,30,65));
		tableMovieList.getTableHeader().setResizingAllowed(false);
		tableMovieList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		// 테이블의 위치 이동 불가설정
		tableMovieList.getTableHeader().setReorderingAllowed(false);
		tableMovieList.getTableHeader().setOpaque(false);
		tableMovieList.setBorder(new LineBorder(Color.WHITE));
//		tableMovieList.setGridColor(new Color(20,35,65));
//		tableMovieList.setShowVerticalLines(false);//별로임
		tableMovieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMovieList.setSelectionForeground(Color.white);
		tableMovieList.setSelectionBackground(new Color(20,35,65));
		tableMovieList.setFocusable(false);
		tableMovieList.setOpaque(false);
//		각 속성명의 사이즈 설정
		tableMovieList.getColumnModel().getColumn(0).setPreferredWidth(60); // 순번
		tableMovieList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		tableMovieList.setFont(setFont);
		
		tableMovieList.getColumnModel().getColumn(1).setPreferredWidth(120); // 영화 코드
		tableMovieList.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		tableMovieList.getColumnModel().getColumn(2).setPreferredWidth(80); // 포스터 이미지
		tableMovieList.getColumnModel().getColumn(3).setPreferredWidth(150); // 영화 제목
		tableMovieList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		tableMovieList.setRowHeight(160);
		jspList = new JScrollPane(tableMovieList);
		jspList.setBounds(10, 100, 565, 450);
		jspList.setOpaque(false);

		//////////////////////////////////
		// 등록
		regesterMovie = new JButton();
		regesterMovie.setIcon(
				new ImageIcon("C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/jbt_resist(125x40).png"));
		regesterMovie.setBounds(150, 580, 125, 40);
		regesterMovie.setContentAreaFilled(false);
		regesterMovie.setBorderPainted(false);

		// 닫기
		exit = new JButton();
		exit.setIcon(new ImageIcon("C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
		exit.setBounds(285, 580, 125, 40);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);

		////////////////////////////////////// 전체 배경 색 추가
		backColor = new JLabel();
		backColor.setIcon(new ImageIcon(
				"C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/admin_movie_management_bg(600x700).png"));
		backColor.setBounds(0, 0, 600, 700);

		setLayout(null);
		TitledBorder tb=new TitledBorder("영화목록");
		tb.setTitleFont(setFont);
		tb.setTitleColor(Color.white);
		jspList.setBorder(tb);
//		jspList.setFont(new Font("나눔궁서", Font.BOLD, 22));
		jspList.getVerticalScrollBar().setUI(new BasicScrollBarUI() { 
			 			@Override 
						protected void configureScrollBarColors() { 
			 				this.thumbColor = new Color(163, 184, 204); 
			 			} // configureScrollBarColors 
					}); 

		
		jspList.setForeground(Color.BLUE);
		jspList.setBackground(Color.BLUE);
		jspList.setOpaque(false);
		
		

		backColor.add(movieManagement);
		backColor.add(jspList);
		backColor.add(regesterMovie);
		backColor.add(exit);


		add(backColor);

		SCAMovieManageController scammc = new SCAMovieManageController(this);
		addWindowListener(scammc);

		////////// 더블클릭
		tableMovieList.addMouseListener(scammc);

		regesterMovie.addActionListener(scammc);
		exit.addActionListener(scammc);

		setSize(600, 720);
		setLocationRelativeTo(scamv);
		setResizable(false);
		setVisible(true);

	}

	public JButton getRegesterMovie() {
		return regesterMovie;
	}

	public JButton getExit() {
		return exit;
	}

	public DefaultTableModel getDtmModel() {
		return dtmModel;
	}

	public JTable getTableMovieList() {
		return tableMovieList;
	}

	public JScrollPane getJspList() {
		return jspList;
	}

	public JLabel getBackColor() {
		return backColor;
	}

	public String getAdmin_id() {
		return admin_id;
	}
	
}
