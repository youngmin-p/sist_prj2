package kr.co.sist.sc.admin.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import kr.co.sist.sc.admin.controller.SCACalculationManageController;

@SuppressWarnings("serial")
public class SCACalculationManageView extends JDialog {
	private JComboBox<String> jcbYear, jcbMonth, jcbDay;
	private JButton jbtCalculation, jbtClose;
	private DefaultTableModel dtmMovieSalesList, dtmSnackSalesList;
	private JTable jtabMovieSalesList, jtabSnackSalesList;
	private JLabel jlblMovieSales, jlblSnackSales, jlblTotalSales;
	
	public SCACalculationManageView(SCAMainView scamv) {
		super(scamv, "½Ö¿ë°ü - Á¤»ê °ü¸®", true);
		
		// jtab
		String[] movieSalesColumnNames = {"¹øÈ£", "¿µÈ­ ÄÚµå", "¿µÈ­ Á¦¸ñ", "¿¹¸Å ¼ö", "ÃÑ °¡°Ý"};
		
		dtmMovieSalesList = new DefaultTableModel(movieSalesColumnNames, 15) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			} // isCellEditable
		};
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		jtabMovieSalesList = new JTable(dtmMovieSalesList);
		jtabMovieSalesList.getTableHeader().setFont(new Font("³ª´®°íµñ", Font.BOLD, 14));
		jtabMovieSalesList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		jtabMovieSalesList.getTableHeader().setForeground(Color.WHITE);
		jtabMovieSalesList.getTableHeader().setBackground(new Color(20, 35, 65));
		jtabMovieSalesList.getTableHeader().setReorderingAllowed(false);
		jtabMovieSalesList.getTableHeader().setResizingAllowed(false);
		jtabMovieSalesList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		jtabMovieSalesList.getTableHeader().setOpaque(false);
		
		jtabMovieSalesList.setBorder(new LineBorder(Color.WHITE));
		jtabMovieSalesList.setGridColor(new Color(20, 35, 65));
		jtabMovieSalesList.setShowVerticalLines(false);
		jtabMovieSalesList.setOpaque(false);
		
		jtabMovieSalesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtabMovieSalesList.setSelectionForeground(Color.WHITE);
		jtabMovieSalesList.setSelectionBackground(new Color(20, 35, 65));
		jtabMovieSalesList.setFocusable(false);
		
		jtabMovieSalesList.setRowHeight(30);
		
		// 450X410
		jtabMovieSalesList.getColumnModel().getColumn(0).setPreferredWidth(50);		// num
		jtabMovieSalesList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtabMovieSalesList.getColumnModel().getColumn(1).setPreferredWidth(70);		// movie_code
		jtabMovieSalesList.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		jtabMovieSalesList.getColumnModel().getColumn(2).setPreferredWidth(140);	// movie_title
		jtabMovieSalesList.getColumnModel().getColumn(3).setPreferredWidth(90);		// personnel
		jtabMovieSalesList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		jtabMovieSalesList.getColumnModel().getColumn(4).setPreferredWidth(100);	// total_price
		jtabMovieSalesList.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		
		JScrollPane jspMovieSalesList = new JScrollPane(jtabMovieSalesList);
		jspMovieSalesList.setBounds(15, 25, 450, 410);
		
		jspMovieSalesList.setBorder(new LineBorder(new Color(20, 45, 87)));
		jspMovieSalesList.setBackground(new Color(20, 45, 87));
		jspMovieSalesList.getViewport().setBackground(Color.WHITE);
		jspMovieSalesList.getVerticalScrollBar().setBackground(new Color(20, 46, 87));
		jspMovieSalesList.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(163, 184, 204);
			} // configureScrollBarColors
		});
		
		String[] snackSalesColumnNames = {"¹øÈ£", "½º³¼¸í", "¼ö·®", "ÃÑ °¡°Ý"};
		
		dtmSnackSalesList = new DefaultTableModel(snackSalesColumnNames, 15) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			} // isCellEditable
		};
		
		jtabSnackSalesList = new JTable(dtmSnackSalesList);
		jtabSnackSalesList.getTableHeader().setFont(new Font("³ª´®°íµñ", Font.BOLD, 14));
		jtabSnackSalesList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		jtabSnackSalesList.getTableHeader().setForeground(Color.WHITE);
		jtabSnackSalesList.getTableHeader().setBackground(new Color(20, 35, 65));
		jtabSnackSalesList.getTableHeader().setReorderingAllowed(false);
		jtabSnackSalesList.getTableHeader().setResizingAllowed(false);
		jtabSnackSalesList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		jtabSnackSalesList.getTableHeader().setOpaque(false);
		
		jtabSnackSalesList.setBorder(new LineBorder(Color.WHITE));
		jtabSnackSalesList.setGridColor(new Color(20, 35, 65));
		jtabSnackSalesList.setShowVerticalLines(false);
		jtabSnackSalesList.setOpaque(false);
		
		jtabSnackSalesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtabSnackSalesList.setSelectionForeground(Color.WHITE);
		jtabSnackSalesList.setSelectionBackground(new Color(20, 35, 65));
		jtabSnackSalesList.setFocusable(false);
		
		jtabSnackSalesList.setRowHeight(30);
		
		// 450X410
		jtabSnackSalesList.getColumnModel().getColumn(0).setPreferredWidth(50);		// num
		jtabSnackSalesList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtabSnackSalesList.getColumnModel().getColumn(1).setPreferredWidth(150);	// snack_name
		jtabSnackSalesList.getColumnModel().getColumn(2).setPreferredWidth(100);	// quan
		jtabSnackSalesList.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		jtabSnackSalesList.getColumnModel().getColumn(3).setPreferredWidth(150);	// total_price
		jtabSnackSalesList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		
		JScrollPane jspSnackSalesList = new JScrollPane(jtabSnackSalesList);
		jspSnackSalesList.setBounds(15, 25, 450, 410);
		
		jspSnackSalesList.setBorder(new LineBorder(new Color(20, 47, 90)));
		jspSnackSalesList.setBackground(new Color(20, 47, 90));
		jspSnackSalesList.getViewport().setBackground(Color.WHITE);
		jspSnackSalesList.getVerticalScrollBar().setBackground(new Color(20, 46, 87));
		jspSnackSalesList.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(163, 184, 204);
			} // configureScrollBarColors
		});
		
		// temp value
		String[] yearArr = {"2019", "2018"};
		String[] monthArr = {"01", "02"};
		String[] dayArr = {"21", "29"};
		
		// jcb
		jcbYear = new JComboBox<String>(yearArr);
		jcbYear.setBounds(230, 40, 100, 30);
		
		JLabel jlblYear = new JLabel("³â");
		jlblYear.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblYear.setForeground(Color.WHITE);
		jlblYear.setBounds(340, 40, 30, 30);
		
		jcbMonth = new JComboBox<String>(monthArr);
		jcbMonth.setBounds(370, 40, 100, 30);
		
		JLabel jlblMonth = new JLabel("¿ù");
		jlblMonth.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblMonth.setForeground(Color.WHITE);
		jlblMonth.setBounds(480, 40, 80, 30);
		
		jcbDay = new JComboBox<String>(dayArr);
		jcbDay.setBounds(510, 40, 100, 30);
		
		JLabel jlblDay = new JLabel("ÀÏ");
		jlblDay.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblDay.setForeground(Color.WHITE);
		jlblDay.setBounds(620, 40, 80, 30);
		
		// jbt
		// Á¤»ê ¹öÆ°À¸·Î ÀÌ¹ÌÁö ¼öÁ¤
		jbtCalculation = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_search(100x30).png"));
		jbtCalculation.setFocusable(false);
		jbtCalculation.setBorderPainted(false);
		jbtCalculation.setContentAreaFilled(false);
		jbtCalculation.setBounds(650, 40, 100, 30);
		
		jbtClose = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));
		jbtClose.setFocusable(false);
		jbtClose.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBounds(437, 685, 125, 40);
		
		// jlbl
		JLabel jlblMovieSalesTitle = new JLabel("»ó¿µ ¸ÅÃâ");
		jlblMovieSalesTitle.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblMovieSalesTitle.setForeground(Color.WHITE);
		jlblMovieSalesTitle.setBounds(10, 10, 80, 30);
		
		jlblMovieSales = new JLabel("99,999,999¿ø");
		jlblMovieSales.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblMovieSales.setForeground(Color.WHITE);
		jlblMovieSales.setBounds(90, 10, 120, 30);
		
		JLabel jlblSnackSalesTitle = new JLabel("½º³¼ ¸ÅÃâ");
		jlblSnackSalesTitle.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblSnackSalesTitle.setForeground(Color.WHITE);
		jlblSnackSalesTitle.setBounds(10, 40, 80, 30);
		
		jlblSnackSales = new JLabel("99,999,999¿ø");
		jlblSnackSales.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblSnackSales.setForeground(Color.WHITE);
		jlblSnackSales.setBounds(90, 40, 120, 30);
		
		JLabel jlblTotalSalesTitle = new JLabel("    ÃÑ ¸ÅÃâ");
		jlblTotalSalesTitle.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblTotalSalesTitle.setForeground(Color.WHITE);
		jlblTotalSalesTitle.setBounds(10, 70, 80, 30);
		
		jlblTotalSales = new JLabel("99,999,999¿ø");
		jlblTotalSales.setFont(new Font("³ª´®°íµñ", Font.BOLD, 16));
		jlblTotalSales.setForeground(Color.WHITE);
		jlblTotalSales.setBounds(90, 70, 120, 30);
		
		// bg
		JLabel jlblBackground = new JLabel(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/calculation_management_5-1_main_bg(1000x800).png"));
		jlblBackground.setLayout(null);
		jlblBackground.setBounds(0, 0, 1000, 800);
		
		// jp
		TitledBorder tbCalculationDate = new TitledBorder(new LineBorder(Color.WHITE), "Á¤»ê ÀÏÀÚ ¼±ÅÃ");
		tbCalculationDate.setTitleColor(Color.WHITE);
		
		JPanel jpCalculationDate = new JPanel();
		jpCalculationDate.setLayout(null);
		jpCalculationDate.setBorder(tbCalculationDate);
		jpCalculationDate.setOpaque(false);
		jpCalculationDate.setBounds(10, 10, 975, 100);
		
		jpCalculationDate.add(jcbYear);
		jpCalculationDate.add(jlblYear);
		jpCalculationDate.add(jcbMonth);
		jpCalculationDate.add(jlblMonth);
		jpCalculationDate.add(jcbDay);
		jpCalculationDate.add(jlblDay);
		jpCalculationDate.add(jbtCalculation);
		
		TitledBorder tbMovieSales = new TitledBorder(new LineBorder(Color.WHITE), "¿µÈ­ »ó¿µ ¼öÀÍ");
		tbMovieSales.setTitleColor(Color.WHITE);
		
		JPanel jpMovieSales = new JPanel();
		jpMovieSales.setLayout(null);
		jpMovieSales.setBorder(tbMovieSales);
		jpMovieSales.setOpaque(false);
		jpMovieSales.setBounds(10, 120, 480, 450);
		
		jpMovieSales.add(jspMovieSalesList);
		
		TitledBorder tbSnackSales = new TitledBorder(new LineBorder(Color.WHITE), "½º³¼ ÆÇ¸Å ¼öÀÍ");
		tbSnackSales.setTitleColor(Color.WHITE);
		
		JPanel jpSnackSales = new JPanel();
		jpSnackSales.setLayout(null);
		jpSnackSales.setBorder(tbSnackSales);
		jpSnackSales.setOpaque(false);
		jpSnackSales.setBounds(505, 120, 480, 450);
		
		jpSnackSales.add(jspSnackSalesList);
		
		// ¸ÅÃâ
		JPanel jpTotalSales = new JPanel();
		jpTotalSales.setLayout(null);
//		jpTotalSales.setBorder(new LineBorder(Color.WHITE));
		jpTotalSales.setOpaque(false);
//		jpTotalSales.setBounds(778, 583, 205, 120);
		jpTotalSales.setBounds(778, 573, 205, 120);
		
		jpTotalSales.add(jlblMovieSalesTitle);
		jpTotalSales.add(jlblMovieSales);
		jpTotalSales.add(jlblSnackSalesTitle);
		jpTotalSales.add(jlblSnackSales);
		jpTotalSales.add(jlblTotalSalesTitle);
		jpTotalSales.add(jlblTotalSales);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.LIGHT_GRAY);
		jp.setBounds(0, 0, 1000, 800);
		
		jp.add(jpCalculationDate);
		jp.add(jpMovieSales);
		jp.add(jpSnackSales);
		jp.add(jpTotalSales);
		jp.add(jbtClose);
		jp.add(jlblBackground);
		
		add(jp);
		
		// action
		SCACalculationManageController scacmc = new SCACalculationManageController(this);
		
		addWindowListener(scacmc);
		
		jbtCalculation.addActionListener(scacmc);
		jbtClose.addActionListener(scacmc);
		
		// size 1000X800
		setSize(1000, 820);
		setLocationRelativeTo(scamv);
		setResizable(false);
		setVisible(true);
		
	} // SCACalculationManageView

	public JComboBox<String> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<String> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<String> getJcbDay() {
		return jcbDay;
	}

	public JButton getJbtCalculation() {
		return jbtCalculation;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public DefaultTableModel getDtmMovieSalesList() {
		return dtmMovieSalesList;
	}

	public DefaultTableModel getDtmSnackSalesList() {
		return dtmSnackSalesList;
	}

	public JTable getJtabMovieSalesList() {
		return jtabMovieSalesList;
	}

	public JTable getJtabSnackSalesList() {
		return jtabSnackSalesList;
	}

	public JLabel getJlblMovieSales() {
		return jlblMovieSales;
	}

	public JLabel getJlblSnackSales() {
		return jlblSnackSales;
	}

	public JLabel getJlblTotalSales() {
		return jlblTotalSales;
	}
	
} // class
