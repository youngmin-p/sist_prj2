package kr.co.sist.sc.admin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCAOnScreenManageController;

@SuppressWarnings("serial")
public class SCAOnScreenManageView extends JDialog implements Runnable{
	private DefaultComboBoxModel<String>  djcbMovieSelect,djcbTheaterSelect, djcbSearchMovie;
	
	public JComboBox<String> jcbMovieSelect, jcbTheaterSelect, jcbSearchMovie;
	
	public JButton jbtOnScreenInsert,jbtOnScreenSearch, jbtOnScreenDelete, jbtClose;
	
	private DefaultTableModel dtmModel;
	private JTable tableMovieList;
	private DefaultComboBoxModel<Integer> djcbInsertYear, djcbInsertMonth, djcbInsertDay, djcbInsertHour,djcbInsertMinute
																,djcbSearchYear,djcbSearchMonth, djcbSearchDay;
	private JComboBox<Integer> jcbYear, jcbMonth, jcbDay
										,jcbInsertYear, jcbInsertMonth, jcbInsertDay, jcbInsertHour, jcbInsertMinute
										,jcbSearchYear,jcbSearchMonth, jcbSearchDay;
	private JTable jtLunch, jtOrder;
	private JLabel si,bun;
	private int year,month;
//	private SimpleDateFormat sdf;
	private String admin_id;
	private JTextField time;
	private String nowTime;
	
	
	public SCAOnScreenManageView(SCAMainView scamv) {
//		public SCAOnScreenManageView() {
			admin_id="hee"; // 관리자 iD 
			setTitle("관리자 :"+admin_id+"\t"+nowTime);
		JLabel backColor = new JLabel();
		backColor.setIcon(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/screen_management_2-1_main_bg(870x670).png"));
		backColor.setBounds(0, 0, 870, 670);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	      dtcr.setHorizontalAlignment(SwingConstants.CENTER);
	      Font setFont=new Font("나눔고딕", Font.BOLD, 19);
		
		djcbMovieSelect =new DefaultComboBoxModel<String>();
		jcbMovieSelect =new JComboBox<>(djcbMovieSelect);
		jcbMovieSelect.addItem("영화선택");
		jcbMovieSelect.setPreferredSize(new Dimension(150, 30));
		
		djcbTheaterSelect =new DefaultComboBoxModel<String>();
		jcbTheaterSelect =new JComboBox<>(djcbTheaterSelect);
		jcbTheaterSelect.addItem("상영관");
		jcbTheaterSelect.addItem("일반");
		jcbTheaterSelect.addItem("프리미엄");
		jcbTheaterSelect.setPreferredSize(new Dimension(150, 30));
		
		djcbInsertYear = new DefaultComboBoxModel<Integer>();
		jcbInsertYear = new JComboBox<Integer>(djcbInsertYear);
//		jcbInsertYear.addItem("년");
		jcbInsertYear.setPreferredSize(new Dimension(60, 30));
		
		djcbInsertMonth = new DefaultComboBoxModel<Integer>();
		jcbInsertMonth = new JComboBox<Integer>(djcbInsertMonth);
//		jcbInsertMonth.addItem("월");
		jcbInsertMonth.setPreferredSize(new Dimension(50, 30));
		
		djcbInsertDay =new DefaultComboBoxModel<Integer>();
		jcbInsertDay =new JComboBox<Integer>(djcbInsertDay);
//		jcbInsertDay.addItem("일");
		jcbInsertDay.setPreferredSize(new Dimension(50, 30));
		
		djcbInsertHour =new DefaultComboBoxModel<Integer>();
		jcbInsertHour=new JComboBox<Integer>(djcbInsertHour);
//		jcbInsertHour.addItem("시");
		jcbInsertHour.setPreferredSize(new Dimension(50, 30));
		
		si=new JLabel("시");
		si.setForeground(Color.white);	
		djcbInsertMinute =new DefaultComboBoxModel<Integer>();
		jcbInsertMinute =new JComboBox<Integer>(djcbInsertMinute);
//		jcbInsertMinute.addItem("분");
		jcbInsertMinute.setPreferredSize(new Dimension(50, 30));
		
		bun=new JLabel("분");
		bun.setForeground(Color.white);
		
		jbtOnScreenInsert=new JButton();
		jbtOnScreenInsert.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_on_add_screen(100x30).png"));
		jbtOnScreenInsert.setContentAreaFilled(false);
		jbtOnScreenInsert.setBorderPainted(false);
		
		
		JPanel northJp=new JPanel();
		northJp.add(jcbMovieSelect);
		northJp.add(jcbTheaterSelect);
		northJp.add(jcbInsertYear);
		northJp.add(jcbInsertMonth);
		northJp.add(jcbInsertDay);
		northJp.add(jcbInsertHour);
		northJp.add(si);
		northJp.add(jcbInsertMinute);
		northJp.add(bun);
		northJp.add(jbtOnScreenInsert);
		northJp.setBounds(20, 20, 800, 50);
		northJp.setOpaque(false);
		backColor.add(northJp);
		
		// 아랬부분
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		 djcbSearchMovie =new DefaultComboBoxModel<String>();
		 jcbSearchMovie =new JComboBox<>(djcbSearchMovie);
		 jcbSearchMovie.addItem("영화 선택");
		 jcbSearchMovie.setPreferredSize(new Dimension(150, 30));
		 
		 djcbSearchYear =new DefaultComboBoxModel<Integer>();
		 jcbSearchYear = new JComboBox<Integer>(djcbSearchYear);
//		 jcbSearchYear.addItem("년");
		 jcbSearchYear.setPreferredSize(new Dimension(60, 30));
		 
		 djcbSearchMonth =new DefaultComboBoxModel<Integer>();
		 jcbSearchMonth =new JComboBox<Integer>(djcbSearchMonth);
//		 jcbSearchMonth.addItem("월");
		 jcbSearchMonth.setPreferredSize(new Dimension(50, 30));
		 
		 djcbSearchDay =new DefaultComboBoxModel<Integer>();
		 jcbSearchDay=new JComboBox<Integer>(djcbSearchDay);
//		 jcbInsertDay.addItem("일");
		 jcbInsertDay.setPreferredSize(new Dimension(50, 30));
		 
		 jbtOnScreenSearch=new JButton();
		 jbtOnScreenSearch.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_on_join_screen(100X30).png"));
		 jbtOnScreenSearch.setContentAreaFilled(false);
		 jbtOnScreenSearch.setBorderPainted(false);
		 
		 jbtOnScreenDelete=new JButton();
		 jbtOnScreenDelete.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_on_delete_screen(100x30).png"));
		 jbtOnScreenDelete.setContentAreaFilled(false);
		 jbtOnScreenDelete.setBorderPainted(false);
		 
		 jbtClose=new JButton();
		 jbtClose.setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_on_close(125x40).png"));
		 jbtClose.setContentAreaFilled(false);
		 jbtClose.setBorderPainted(false);
		 jbtClose.setBounds(350,590,125,40);
			
		 String colum[] = { "순번", "상영번호","영화코드", "포스터", "제목","상영관","시작시간","종료시간"};
			dtmModel = new DefaultTableModel(colum, 8) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
				@Override
				public Class<?> getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}
		
			};
		tableMovieList =new JTable(dtmModel);
		tableMovieList.setBounds(0, 0, 620, 600);
		tableMovieList.getTableHeader().setReorderingAllowed(false);
		tableMovieList.setOpaque(false);
		tableMovieList.setAutoscrolls(true);
///////////////////////////////////////////////////////////////////////////////		
		tableMovieList.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 22));
		tableMovieList.getTableHeader().setBorder(new LineBorder(Color.WHITE));
		tableMovieList.getTableHeader().setForeground(Color.white);
		tableMovieList.getTableHeader().setBackground(new Color(20,30,65));
		tableMovieList.getTableHeader().setResizingAllowed(false);
		tableMovieList.getTableHeader().setPreferredSize(new Dimension(100, 30));
		tableMovieList.getTableHeader().setReorderingAllowed(false);
		tableMovieList.getTableHeader().setOpaque(false);
		tableMovieList.setBorder(new LineBorder(Color.WHITE));
//		tableMovieList.setGridColor(new Color(20,35,65));
//		tableMovieList.setShowVerticalLines(false);//별로임
		tableMovieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMovieList.setSelectionForeground(Color.white);
		tableMovieList.setSelectionBackground(new Color(20,35,65));
		
		tableMovieList.setFont(setFont);
		for(int i=0; i<colum.length;i++) {
			if(i==3) {
				i++;
			}
			tableMovieList.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		tableMovieList.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableMovieList.getColumnModel().getColumn(1).setPreferredWidth(110);
		tableMovieList.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableMovieList.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableMovieList.getColumnModel().getColumn(5).setPreferredWidth(25);
		
/*		tableMovieList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		tableMovieList.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		tableMovieList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		*/
		
		
		tableMovieList.setRowHeight(175);
		JScrollPane jscTable=new JScrollPane(tableMovieList);
//		jscTable.setBounds(0, 0, 600, 600);
		jscTable.setOpaque(false);
		jscTable.setAutoscrolls(true);
		
//		jscTable.setPreferredSize(new Dimension(300, 300));
		
		
		//현재 날짜와 시간
		time=new JTextField();
		time.setBounds(0, 0, 50,60);
//		jcbSearchMovie,jcbSearchYear,jcbSearchMonth, jcbSearchDay;
		JPanel innerNorth=new JPanel()	;
		innerNorth.add(jcbSearchMovie);
		innerNorth.add(jcbSearchYear);
		innerNorth.add(jcbSearchMonth);
		innerNorth.add(jcbSearchDay);
		innerNorth.add(jbtOnScreenSearch);
		innerNorth.add(jbtOnScreenDelete);
//		innerNorth.add(time);
		
		
		innerNorth.setOpaque(false);
		innerNorth.setBounds(20, 100,850, 470);
		
		// 테이블
		JPanel Southmiddle=new JPanel();
		Southmiddle.setLayout(new BorderLayout());
		Southmiddle.add(jscTable);
		Southmiddle.setBounds(10, 150,850, 400);
		
		backColor.add(innerNorth);// 검색
		backColor.add(Southmiddle);//테이블
		backColor.add(jbtClose);//버튼
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		SetYear();
		SetMonth();
		
		SetHour();
		SetMinute();
		
		SCAOnScreenManageController ssmc =new SCAOnScreenManageController(this);
		addWindowListener(ssmc);
		jbtOnScreenInsert.addActionListener(ssmc);
		jbtOnScreenSearch.addActionListener(ssmc);
		 jbtOnScreenDelete.addActionListener(ssmc);
		jbtClose.addActionListener(ssmc);
		jcbInsertMonth.addActionListener(ssmc);
//		jcbSearchMonth.addActionListener(ssmc);
		
		
		add(backColor);
		
		setBounds(50, 50, 870,670);
		setVisible(true);
		setResizable(false);
		new Thread(this).start();

      




//		 870, 670
	}
		public void run(){
            while(true)  {
                   display();

                   try{
                          Thread.sleep(1000);

                   }catch(Exception ex){}
            }
      }
		public void display(){

            Calendar cal=Calendar.getInstance();

            int y=cal.get(Calendar.YEAR);

            int m=cal.get(Calendar.MONTH)+1;

            int d=cal.get(Calendar.DATE);

            int h=cal.get(Calendar.HOUR);

            int min=cal.get(Calendar.MINUTE);

            int sec=cal.get(Calendar.SECOND);

            time.setText(y+"년 "+m+"월 "+d+"일 "+h+"시 "+min+"분 "+sec+"초");
            nowTime=time.getText().toString();
            setTitle("관리자 :"+admin_id+"                                                                   "+nowTime);

      }




	//년월일 시분 상영등록에
	private void SetYear() {

		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		for(int temp=0;temp<4;temp++) {
			jcbInsertYear.addItem(year+temp);
			jcbSearchYear.addItem(year+temp);
		}
			
	}

	private void SetMonth() {
		Calendar cal = Calendar.getInstance();
//		month = cal.get(Calendar.MONTH);
		month = cal.getActualMaximum(Calendar.MONTH);
		int now=cal.get(Calendar.MONTH)+1;
		for(int i=1;i<month+2;i++) {
				jcbInsertMonth.addItem(i);
				jcbSearchMonth.addItem(i);
//				System.out.println(sdf.format(i+1));
			}
		jcbInsertMonth.setSelectedItem(new Integer(now));
		jcbSearchMonth.setSelectedItem(new Integer(now));
		SetDay();
	}

	private void SetDay() {
/*		if(jcbInsertMonth.getActionListeners() != null) {
			System.out.println(jcbInsertMonth.getSelectedItem());
		}*/
		
		Calendar cal = new GregorianCalendar(year,month,1);
		int day = cal.getActualMaximum(Calendar.DATE);
		int nowday=cal.get(Calendar.DAY_OF_MONTH);
		for(int i=1;i<day+1;i++) {
		
		jcbInsertDay.addItem(i);
		jcbSearchDay.addItem(i);
		}
		jcbInsertDay.setSelectedItem(new Integer(nowday));
		jcbSearchDay.setSelectedItem(new Integer(nowday));
	}
	//시 분 
	private void SetHour() {
		Calendar cal = new GregorianCalendar();
		int hour =cal.getActualMaximum(Calendar.HOUR_OF_DAY);
		int now=cal.get(Calendar.HOUR_OF_DAY);
		for(int i=0;i<hour+1;i++) {
			jcbInsertHour.addItem(i);
			}
		jcbInsertHour.setSelectedItem(new Integer(now));
	}
	private void SetMinute() {
		Calendar cal = new GregorianCalendar();
		int mi =cal.getActualMaximum(Calendar.MINUTE);
//		int now=cal.get(Calendar.MINUTE);
		for(int i=0;i<mi+1;i++) {
			if(i%5==0) {
			jcbInsertMinute.addItem(i);
			}
		}
		
	}
	
	public DefaultComboBoxModel<String> getDjcbMovieSelect() {
		return djcbMovieSelect;
	}

	public DefaultComboBoxModel<String> getDjcbTheaterSelect() {
		return djcbTheaterSelect;
	}

	public DefaultComboBoxModel<String> getDjcbSearchMovie() {
		return djcbSearchMovie;
	}

	public JComboBox<String> getJcbMovieSelect() {
		return jcbMovieSelect;
	}

	public JComboBox<String> getJcbTheaterSelect() {
		return jcbTheaterSelect;
	}

	public JComboBox<String> getJcbSearchMovie() {
		return jcbSearchMovie;
	}

	public JButton getJbtOnScreenInsert() {
		return jbtOnScreenInsert;
	}

	public JButton getjbtOnScreenSearch() {
		return jbtOnScreenSearch;
	}

	public JButton getJbtOnScreenDelete() {
		return jbtOnScreenDelete;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public DefaultTableModel getDtmModel() {
		return dtmModel;
	}

	public JTable getTableMovieList() {
		return tableMovieList;
	}

	public DefaultComboBoxModel<Integer> getDjcbInsertYear() {
		return djcbInsertYear;
	}

	public DefaultComboBoxModel<Integer> getDjcbInsertMonth() {
		return djcbInsertMonth;
	}

	public DefaultComboBoxModel<Integer> getDjcbInsertDay() {
		return djcbInsertDay;
	}

	public DefaultComboBoxModel<Integer> getDjcbInsertHour() {
		return djcbInsertHour;
	}

	public DefaultComboBoxModel<Integer> getDjcbInsertMinute() {
		return djcbInsertMinute;
	}

	public DefaultComboBoxModel<Integer> getDjcbSearchYear() {
		return djcbSearchYear;
	}

	public DefaultComboBoxModel<Integer> getDjcbSearchMonth() {
		return djcbSearchMonth;
	}

	public DefaultComboBoxModel<Integer> getDjcbSearchDay() {
		return djcbSearchDay;
	}

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<Integer> getJcbDay() {
		return jcbDay;
	}

	public JComboBox<Integer> getJcbInsertYear() {
		return jcbInsertYear;
	}

	public JComboBox<Integer> getJcbInsertMonth() {
		return jcbInsertMonth;
	}

	public JComboBox<Integer> getJcbInsertDay() {
		return jcbInsertDay;
	}

	public JComboBox<Integer> getJcbInsertHour() {
		return jcbInsertHour;
	}

	public JComboBox<Integer> getJcbInsertMinute() {
		return jcbInsertMinute;
	}

	public JComboBox<Integer> getJcbSearchYear() {
		return jcbSearchYear;
	}

	public JComboBox<Integer> getJcbSearchMonth() {
		return jcbSearchMonth;
	}

	public JComboBox<Integer> getJcbSearchDay() {
		return jcbSearchDay;
	}

	public JTable getJtLunch() {
		return jtLunch;
	}

	public JTable getJtOrder() {
		return jtOrder;
	}

	public JLabel getSi() {
		return si;
	}

	public JLabel getBun() {
		return bun;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}
/*	public static void main(String[] args) {
		SCAOnScreenManageView sss=new SCAOnScreenManageView();
	}*/
	
	
	
}
