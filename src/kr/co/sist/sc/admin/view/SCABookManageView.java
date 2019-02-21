package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCABookManageController;

@SuppressWarnings("serial")
public class SCABookManageView extends JDialog {
	private JComboBox<String> jcbMovieTitle;
	private JComboBox<Integer> jcbPersonnel;
	private JButton jbtSearch, jbtBook, jbtClose;
	private DefaultTableModel dtmOnScreenList, dtmBookList;
	private JTable jtabOnScreenList, jtabBookList;
	
	public SCABookManageView(SCAMainView scamv) {
		super(scamv, "쌍용관 - 예매 관리", true);
		
		// jtab
		String[] onScreenColumnNames = {
			"번호", "영화 코드", "영화 제목", "상영 번호", "상영관", "상영 시작 시간", "상영 종료 시간", "잔여 좌석수", "총 좌석수"	
		};
		
		dtmOnScreenList = new DefaultTableModel(onScreenColumnNames, 15) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			} // isCellEditable
		};
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		jtabOnScreenList = new JTable(dtmOnScreenList);
		jtabOnScreenList.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 14));
		jtabOnScreenList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		jtabOnScreenList.getTableHeader().setForeground(Color.WHITE);
		jtabOnScreenList.getTableHeader().setBackground(new Color(20, 35, 65));
		jtabOnScreenList.getTableHeader().setReorderingAllowed(false);
		jtabOnScreenList.getTableHeader().setResizingAllowed(false);
		jtabOnScreenList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		jtabOnScreenList.getTableHeader().setOpaque(false);
		
		jtabOnScreenList.setBorder(new LineBorder(Color.WHITE));
		jtabOnScreenList.setGridColor(new Color(20, 35, 65));
		jtabOnScreenList.setShowVerticalLines(false);
		jtabOnScreenList.setOpaque(false);
		
		jtabOnScreenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtabOnScreenList.setSelectionForeground(Color.WHITE);
		jtabOnScreenList.setSelectionBackground(new Color(20, 35, 65));
		jtabOnScreenList.setFocusable(false);
		
		jtabOnScreenList.setRowHeight(30);
		
		// 800X200
		jtabOnScreenList.getColumnModel().getColumn(0).setPreferredWidth(50);	// num
		jtabOnScreenList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(1).setPreferredWidth(70);	// movie_code
		jtabOnScreenList.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(2).setPreferredWidth(140);	// movie_title
		jtabOnScreenList.getColumnModel().getColumn(3).setPreferredWidth(100);	// screen_num
		jtabOnScreenList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(4).setPreferredWidth(60);	// screen_name
		jtabOnScreenList.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(5).setPreferredWidth(100);	// start_time
		jtabOnScreenList.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(6).setPreferredWidth(100);	// end_time
		jtabOnScreenList.getColumnModel().getColumn(6).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(7).setPreferredWidth(90);	// seat_remain
		jtabOnScreenList.getColumnModel().getColumn(7).setCellRenderer(dtcr);
		jtabOnScreenList.getColumnModel().getColumn(8).setPreferredWidth(90);	// seat_count
		jtabOnScreenList.getColumnModel().getColumn(8).setCellRenderer(dtcr);
		
		JScrollPane jspOnScreenList = new JScrollPane(jtabOnScreenList);
		jspOnScreenList.setBounds(15, 60, 815, 265);
		
		jspOnScreenList.setBorder(new LineBorder(new Color(20, 45, 87)));
		jspOnScreenList.setBackground(new Color(20, 45, 87));
		jspOnScreenList.getViewport().setBackground(Color.WHITE);
		jspOnScreenList.getVerticalScrollBar().setBackground(new Color(20, 46, 87));
		jspOnScreenList.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(163, 184, 204);
			} // configureScrollBarColors
		});
		
		String[] bookColumnNames = {
				"번호", "아이디", "예매 번호", "예매 수", "좌석 번호", "결제일시"
			};
		
		dtmBookList = new DefaultTableModel(bookColumnNames, 15) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			} // isCellEditable
		};
		
		jtabBookList = new JTable(dtmBookList);
		jtabBookList.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 14));
		jtabBookList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		jtabBookList.getTableHeader().setForeground(Color.WHITE);
		jtabBookList.getTableHeader().setBackground(new Color(20, 35, 65));
		jtabBookList.getTableHeader().setReorderingAllowed(false);
		jtabBookList.getTableHeader().setResizingAllowed(false);
		jtabBookList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		jtabBookList.getTableHeader().setOpaque(false);
		
		jtabBookList.setBorder(new LineBorder(Color.WHITE));
		jtabBookList.setGridColor(new Color(20, 35, 65));
		jtabBookList.setShowVerticalLines(false);
		jtabBookList.setOpaque(false);
		
		jtabBookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtabBookList.setSelectionForeground(Color.WHITE);
		jtabBookList.setSelectionBackground(new Color(20, 35, 65));
		jtabBookList.setFocusable(false);
		
		jtabBookList.setRowHeight(30);
		
		// 800X215
		jtabBookList.getColumnModel().getColumn(0).setPreferredWidth(50);	// num
		jtabBookList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtabBookList.getColumnModel().getColumn(1).setPreferredWidth(100);	// member_id
		jtabBookList.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		jtabBookList.getColumnModel().getColumn(2).setPreferredWidth(150);	// book_number
		jtabBookList.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		jtabBookList.getColumnModel().getColumn(3).setPreferredWidth(150);	// personnel
		jtabBookList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		jtabBookList.getColumnModel().getColumn(4).setPreferredWidth(150);	// seat_num
		jtabBookList.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		jtabBookList.getColumnModel().getColumn(5).setPreferredWidth(200);	// payment_date
		jtabBookList.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		
		JScrollPane jspBookList = new JScrollPane(jtabBookList);
		jspBookList.setBounds(15, 25, 815, 240);
		
		jspBookList.setBorder(new LineBorder(new Color(20, 47, 90)));
		jspBookList.setBackground(new Color(20, 47, 90));
		jspBookList.getViewport().setBackground(Color.WHITE);
		jspBookList.getVerticalScrollBar().setBackground(new Color(20, 46, 87));
		jspBookList.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(163, 184, 204);
			} // configureScrollBarColors
		});
		
		// jcb
		JLabel jlblMovieTitle = new JLabel("영화명");
		jlblMovieTitle.setFont(new Font("나눔고딕", Font.BOLD, 16));
		jlblMovieTitle.setForeground(Color.WHITE);
		jlblMovieTitle.setBounds(105, 20, 80, 30);
		
		jcbMovieTitle = new JComboBox<String>();
		jcbMovieTitle.setBounds(155, 20, 150, 30);
		
		JLabel jlblPersonnel = new JLabel("인원수");
		jlblPersonnel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		jlblPersonnel.setForeground(Color.WHITE);
		jlblPersonnel.setBounds(495, 20, 80, 30);
		
		Integer[] arrPersonnel = {1, 2, 3, 4, 5};
		
		jcbPersonnel = new JComboBox<Integer>(arrPersonnel);
		jcbPersonnel.setBounds(545, 20, 100, 30);
		
		// jbt
		jbtSearch = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_search(100x30).png"));
		jbtSearch.setFocusable(false);
		jbtSearch.setBorderPainted(false);
		jbtSearch.setContentAreaFilled(false);
		jbtSearch.setBounds(310, 20, 100, 30);
		
		jbtBook = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_book(100x30).png"));
		jbtBook.setFocusable(false);
		jbtBook.setBorderPainted(false);
		jbtBook.setContentAreaFilled(false);
		jbtBook.setBounds(650, 20, 100, 30);
		
		jbtClose = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
		jbtClose.setFocusable(false);
		jbtClose.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBounds(375, 685, 125, 40);
		
		// bg
		JLabel jlblBackground = new JLabel(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/book_management_3-1_main_bg(870x760).png"));
		jlblBackground.setLayout(null);
		jlblBackground.setBounds(0, 0, 870, 760);
		
		// jp
		TitledBorder tbSiteBook = new TitledBorder(new LineBorder(Color.WHITE), "현장 예매");
		tbSiteBook.setTitleColor(Color.WHITE);
		
		JPanel jpSiteBook = new JPanel();
		jpSiteBook.setLayout(null);
		jpSiteBook.setBorder(tbSiteBook);
		jpSiteBook.setOpaque(false);
		jpSiteBook.setBounds(10, 10, 845, 340);
		
		jpSiteBook.add(jlblMovieTitle);
		jpSiteBook.add(jcbMovieTitle);
		jpSiteBook.add(jbtSearch);
		jpSiteBook.add(jlblPersonnel);
		jpSiteBook.add(jcbPersonnel);
		jpSiteBook.add(jbtBook);
		jpSiteBook.add(jspOnScreenList);
		
		TitledBorder tbBookList = new TitledBorder(new LineBorder(Color.WHITE), "예매 현황");
		tbBookList.setTitleColor(Color.WHITE);
		
		JPanel jpBookList = new JPanel();
		jpBookList.setLayout(null);
		jpBookList.setBorder(tbBookList);
		jpBookList.setOpaque(false);
		jpBookList.setBounds(10, 380, 845, 280);
		
		jpBookList.add(jspBookList);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.LIGHT_GRAY);
		jp.setBounds(0, 0, 870, 760);
		
		jp.add(jpSiteBook);
		jp.add(jpBookList);
		jp.add(jbtClose);
		jp.add(jlblBackground);
		
		add(jp);
		
		// action
		SCABookManageController scabmc = new SCABookManageController(this);
		
		addWindowListener(scabmc);
		
		jbtSearch.addActionListener(scabmc);
		jbtBook.addActionListener(scabmc);
		jbtClose.addActionListener(scabmc);
		
		// size 870X760
		setSize(870, 780);
		setLocationRelativeTo(scamv);
		setResizable(false);
		setVisible(true);
		
	} // SCABookManageView

	public JComboBox<String> getJcbMovieTitle() {
		return jcbMovieTitle;
	}

	public JComboBox<Integer> getJcbPersonnel() {
		return jcbPersonnel;
	}

	public JButton getJbtSearch() {
		return jbtSearch;
	}

	public JButton getJbtBook() {
		return jbtBook;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public DefaultTableModel getDtmOnScreenList() {
		return dtmOnScreenList;
	}

	public DefaultTableModel getDtmBookList() {
		return dtmBookList;
	}

	public JTable getJtabOnScreenList() {
		return jtabOnScreenList;
	}

	public JTable getJtabBookList() {
		return jtabBookList;
	}
	
} // class
