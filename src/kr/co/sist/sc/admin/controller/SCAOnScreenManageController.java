package kr.co.sist.sc.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SCAOnScreenManageDAO;
import kr.co.sist.sc.admin.view.SCAOnScreenManageView;
import kr.co.sist.sc.admin.vo.SCAOnScreenInsertVO;
import kr.co.sist.sc.admin.vo.SCAOnScreenMovieListVO;
import kr.co.sist.sc.admin.vo.SCAOnScreenMovieListVO2;
import kr.co.sist.sc.admin.vo.insertSelectiveVO;

public class SCAOnScreenManageController extends WindowAdapter implements ActionListener {

	private SCAOnScreenManageView smv;
	private SCAOnScreenManageDAO sm_Dao;
//	private SCAOnScreenManageController ssmc;
	private List<SCAOnScreenMovieListVO> listmovie; //윗 영화 목록 select
	private List<SCAOnScreenMovieListVO2> screenMovieList; //아래 영화 목록 select
	private List<insertSelectiveVO> selectiveVO ;// 테이블에 값 넣기
	

//	SCAOnScreenManageView smv

	public SCAOnScreenManageController(SCAOnScreenManageView smv) {
		super();
		this.smv = smv;
		sm_Dao = SCAOnScreenManageDAO.getInstance();
		try {
			searchMovieList();
			nowScreen();
			screenMovieList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 상영 관리의 영화 선택 목록을 설정 윗부분 을말함
	public void searchMovieList() throws SQLException {

		listmovie = sm_Dao.MovieList();

		for (int i = 0; i < listmovie.size(); i++) {
//		smv.getDjcbSearchMovie().addElement(listmovie.get(i).getMovie_title());
			smv.getDjcbMovieSelect().addElement(listmovie.get(i).getMovie_title());
		}
	}
	
//상영중인 영화의 영화선택 부분을 조회 테이블을말함
	public void nowScreen() throws SQLException {
		//"순번", "상영번호","영화코드", "포스터", "제목","상영관","상영 시작시간","상영종료시간
		String path="C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/movie/s_movie_";
		selectiveVO=sm_Dao.selectiveList();
		smv.getDtmModel().setRowCount(0);
		Object[] rowData = null;
		
		for(int i=0; i< selectiveVO.size();i++) {
			rowData = new Object[8];
			rowData[0] = i + 1;
			rowData[1]=  selectiveVO.get(i).getScreen_num().toString();
			rowData[2] = selectiveVO.get(i).getMovie_code().toString();
			rowData[3] = new ImageIcon(setImg(selectiveVO.get(i).getMovie_img().toString()));
			rowData[4] = selectiveVO.get(i).getMovie_title().toString();
			rowData[5] = selectiveVO.get(i).getScreen_name().toString();
			rowData[6] = selectiveVO.get(i).getStart_time().toString();
			rowData[7] = selectiveVO.get(i).getEnd_time().toString();
			
			smv.getDtmModel().addRow(rowData);
		}
		
	}
	/**
	 * 현재 상영중인 영화 목록 을 보여줌
	 * @throws SQLException
	 */
	public void screenMovieList() throws SQLException {
		
		screenMovieList=sm_Dao.showScreenMovieList();
		
		for (int i = 0; i < screenMovieList.size(); i++) {
//		smv.getDjcbSearchMovie().addElement(listmovie.get(i).getMovie_title());
			smv.getDjcbSearchMovie().addElement(screenMovieList.get(i).getMovie_title());
		}
		
	}
	
	
	//////////////////////////////////////위로 초반 에 보여줄부분들 아래는 뭔가를 선택시 실행하는것들
///////////////////////////////////////////////////////////////////////////////
	
// 상영관리의 사영 등록 을 눌렀을때 시간 중복을 방지해야함

	public void register() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(2);

		String m_title = smv.getJcbMovieSelect().getSelectedItem().toString();
		String screenArea = smv.getJcbTheaterSelect().getSelectedItem().toString();
		String m_code = "";
		int year = Integer.parseInt(smv.getJcbInsertYear().getSelectedItem().toString().substring(2));
		int dateYear = Integer.parseInt(smv.getJcbInsertYear().getSelectedItem().toString());
		int month = Integer.parseInt(smv.getJcbInsertMonth().getSelectedItem().toString());
		int day = Integer.parseInt(smv.getJcbInsertDay().getSelectedItem().toString());
		int s_hour = Integer.parseInt(smv.getJcbInsertHour().getSelectedItem().toString());
		int s_minute = Integer.parseInt(smv.getJcbInsertMinute().getSelectedItem().toString());
		int runningTime = 0; // 러닝타임 가져오기
		int e_hour = 0;
		int e_minute = 0;

		for (int i = 0; i < listmovie.size(); i++) {
			if (listmovie.get(i).getMovie_title().equals(m_title)) {
				m_code = listmovie.get(i).getMovie_code();
				runningTime = listmovie.get(i).getRunningtime();
			}
		}

//		System.out.println("러닝타임" + runningTime);
		if ("일반".equals(screenArea)) {
			screenArea = "N";
		}
		if ("프리미엄".equals(screenArea)) {
			screenArea = "P";
		}
		
		// ent_time를 위한 계산이 들어가야함 60으로 나누어서 몫은 시간과 더하고
		// 나머지는 분과더해서 60이 넘어가면 시를 더해주고 그 나머지를 분으로 한다
		e_hour = s_hour + (runningTime / 60);
		e_minute = s_minute + (runningTime % 60);

		if (e_minute > 59) {
			e_hour += 1;
			e_minute = e_minute - 60;
		}
//		System.out.println(s_hour+"시작시분"+s_minute+"/////////"+e_hour+"끝시간 분"+e_minute);
		// sysdate
//SCREEN_NUM	SCREEN_DATE  	START_TIME	 end_time	MOVIE_CODE	SCREEN_NAME	
//	N_19 0129 0830 	2019-01-29	       0830			계산  			M_000020			N	

//	System.out.println(m_code+"/"+screenArea+"/"+year+"/"+month+"/"+day+"/"+s_hour+"/"+s_minute);
//		System.out.println(nf.format(s_hour)+"시/분"+nf.format(s_minute));
		StringBuilder screen_num = new StringBuilder();
		screen_num.append(screenArea).append("_").append(year).append(nf.format(month)).append(nf.format(day))
				.append(nf.format(s_hour)).append(nf.format(s_minute));
		StringBuilder onDate = new StringBuilder();
		onDate.append(dateYear).append("-").append(nf.format(month)).append("-").append(nf.format(day));
		StringBuilder start_time = new StringBuilder();
		start_time.append(nf.format(s_hour)).append(nf.format(s_minute));
		StringBuilder end_time = new StringBuilder();
		end_time.append(nf.format(e_hour)).append(nf.format(e_minute));


		
/////////////////////////////////////////////////
//		System.out.println(screen_num+"/"+screenArea+"/"+dateYear+"/"+s_hour+"/"+e_hour);
//		 System.out.println(screen_num+"/"+onDate+"/"+start_time+"/"+end_time+"/"+m_code+"/"+screenArea);
		//seletiveVO 에서 값을 가지고 와서 for을 돌려 값을 비교
		//n,p 비교 후 러닝타임 비교 
		 
		 
		 System.out.println("상영등록---등록하기전 중복 검사---------------");
		 int st_time=0;
		 int en_time=0;
		 int temp_st_date=Integer.parseInt(screen_num.substring(2).toString());
		 int temp_end_date= Integer.parseInt(screen_num.substring(2,8)+end_time);
		 boolean flag_st=false;
		 boolean flag_en=false;
		 String date="";
		 String temp_date=screen_num.substring(2,8);
		 String sc_code="";
		 String temp_code=screenArea.toString();
		 
		 System.out.println(sc_code+"/////"+temp_code);
		 for(int i=0; i< selectiveVO.size();i++) {
			 st_time=Integer.parseInt(selectiveVO.get(i).getScreen_num().substring(2));
			 en_time=Integer.parseInt(selectiveVO.get(i).getScreen_num().substring(2, 8)+selectiveVO.get(i).getEnd_time());
			 date=selectiveVO.get(i).getScreen_num().substring(2, 8);
			 sc_code=selectiveVO.get(i).getScreen_num().substring(0, 1);
			 
			 
			//SCREEN_NUM	SC
//				N_19 0129 0830
			 // 스크린넘버
//			 if(screen_num.toString().equals(selectiveVO.get(i).getScreen_num())) {
//				 System.out.println("--"+selectiveVO.get(i).getScreen_num()+"---st--"+st_time+"//en/"+en_time+"////"+temp_st_date+"////"+end_time+"///"+temp_end_date);	
				
//			 System.out.println("1--"+selectiveVO.get(i).getScreen_num()+"-저장-"+st_time+"/en//"+en_time+"//입력/"+temp_st_date+"///////"+temp_end_date);	
			if(temp_code.equals(sc_code)) {
				if(temp_date.equals(date)) {// 날짜가 같은지
///					 System.err.println(selectiveVO.get(i));
//					 System.out.println(sc_code+"////"+temp_code+"/////"+date+"////"+temp_date);
//					 System.out.println("같은 코드의 같은 날짜");
						 if( st_time<=temp_st_date) {// 입력받은  temp 가  st 보다 높은가
//								 System.out.println("1--"+selectiveVO.get(i).getScreen_num()+"-저장-"+st_time+"/en//"+en_time+"//입력/"+temp_st_date+"///en//"+temp_end_date);	
//								 System.out.println();
									 if(temp_st_date<=en_time) {// 입력받은  tempr 가  en 보다 작은가
										 flag_st=true;
										 System.out.println(flag_st+"//////"+flag_en+"-저장-"+st_time+"/en//"+en_time+"//입력/"+temp_st_date+"///en//"+temp_end_date);
//										 System.out.println("111111111111111111111111111111111111아래");
									 }
						 }
					 System.out.println();
					 //시작시간에 대한  if 끝
					 	if(st_time<=temp_end_date) {
							 if(temp_end_date<=en_time) {
								 flag_en=true;
								 System.out.println("안됨");
								 System.out.println(flag_st+"//////"+flag_en+"-저장-"+st_time+"/en//"+en_time+"//입력/"+temp_st_date+"///en//"+temp_end_date);
							 }
					 	}
			 }
			}//N,P if
		 }
			if(flag_en==flag_st) {
			System.out.println("마지막"+flag_st+"//////"+flag_en+"-저장-"+st_time+"/en//"+en_time+"//입력/"+temp_st_date+"///en//"+temp_end_date);
			}
			/* if(st_time<=temp_st_date && temp_st_date <=st_time) {// 시작시간
					 System.out.println("111111111111111111111111111111111111아래");
					 System.out.println("1--"+selectiveVO.get(i).getScreen_num()+"-저장-"+st_time+"/en//"+en_time+"//입력/"+temp_st_date+"///////"+temp_end_date);	
					 System.out.println();
					 if(temp_st_date<=en_time) {
						 System.out.println("222222222222222222222222222222222222222아래.");
						 System.out.println("1--"+selectiveVO.get(i).getScreen_num()+"---저장--"+st_time+"/en//"+en_time+"/입력//"+temp_st_date+"/////"+temp_end_date);
						 System.out.println();
						 StringBuilder che=new StringBuilder();
						 che.append("[ ").append(smv.getJcbMovieSelect().getSelectedItem()).append(" ]").append("\n")
						 .append("[ ").append(smv.getJcbTheaterSelect().getSelectedItem()).append(" ]").append("\n")
						 .append("[ ").append(onDate).append(" ]").append("\n")
						 .append(" 에는 이미 상영중인 영화가 있습니다");
						 JOptionPane.showMessageDialog(smv, che,"알림",JOptionPane.CLOSED_OPTION);
						 return;
					 }
					 
				 }*/
				 
	//		 }
//		 }
		////////////////////////////////////////////////////////////////////////////////////////// 아래는 추가 부분
		/* StringBuilder insertChe=new StringBuilder();
		 insertChe.append("[ ").append(smv.getJcbMovieSelect().getSelectedItem()).append(" ]").append("\n")
		 .append("[ ").append(smv.getJcbTheaterSelect().getSelectedItem()).append(" ]").append("\n")
		 .append(onDate).append("\n").append("을 등록 하시겠습니까?");
		 SCAOnScreenInsertVO sivo = new SCAOnScreenInsertVO(screen_num.toString(), onDate.toString(),
					start_time.toString(), end_time.toString(), m_code.toString(), screenArea.toString());
		 switch (JOptionPane.showConfirmDialog(smv,insertChe, "알림", JOptionPane.OK_CANCEL_OPTION)) {
		case JOptionPane.OK_OPTION:
			
			try {
				int cnt=0;
				cnt=sm_Dao.screenRegister(sivo);
				if (cnt == 1) {
					JOptionPane.showMessageDialog(null, insertChe);
					insertChe.append("[ ").append(smv.getJcbMovieSelect().getSelectedItem()).append(" ]").append("\n")
					 .append("[ ").append(smv.getJcbTheaterSelect().getSelectedItem()).append(" ]").append("\n")
					 .append(onDate).append("을 등록하였습니다");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case JOptionPane.CANCEL_OPTION:
			
			return;*/
	
		
	}
	
	/**
	 * 상영 조회를 할때
	 */
	public void screenCheck() {
		// djcbSearchMovie =new DefaultComboBoxModel<String>();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(2);
		String m_title = smv.getJcbSearchMovie().getSelectedItem().toString();
		String m_code = "";
		int year = Integer.parseInt(smv.getJcbSearchYear().getSelectedItem().toString()); //2자리
		int month = Integer.parseInt(smv.getJcbSearchMonth().getSelectedItem().toString());
		int day = Integer.parseInt(smv.getJcbSearchDay().getSelectedItem().toString());
		
		for (int i = 0; i < screenMovieList.size(); i++) {
			if (screenMovieList.get(i).getMovie_title().equals(m_title)) {
				m_code = screenMovieList.get(i).getMovie_code();
			}
		}
		//코드와 년월일 만 넘기자
		StringBuilder temp=new StringBuilder();
		temp.append(year).append("-").append(nf.format(month)).append("-").append(nf.format(day));
		
		System.out.println("controller 조회 ");
		try {
			List<insertSelectiveVO>list =sm_Dao.screeningSelectMovieInfo(m_code,temp.toString());
			System.out.println("리스트값은      "+list.get(0).getMovie_code());
			if(null==list.get(0).getMovie_code()) {
				System.out.println("controller-screenCheck()   자료없음"+list);
				JOptionPane.showMessageDialog(smv, "결과 없음");
				nowScreen();
				return;
			}
			else if("0".equals(list.get(0).getMovie_code())) {
				System.out.println("controller-screenCheck()   자료없음"+list);
				JOptionPane.showMessageDialog(smv, "결과 없음");
				nowScreen();
				return;
			}
			smv.getDtmModel().setRowCount(0);
			Object[] rowData = null;
			
			for(int i=0; i< list.size();i++) {
				rowData = new Object[8];
				rowData[0] = i + 1;
				rowData[1]=  list.get(i).getScreen_num().toString();
				rowData[2] = list.get(i).getMovie_code().toString();
				rowData[3] =	new ImageIcon(setImg(list.get(i).getMovie_img().toString()).toString());
				rowData[4] = list.get(i).getMovie_title().toString();
				rowData[5] = list.get(i).getScreen_name().toString();
				rowData[6] = list.get(i).getStart_time().toString();
				rowData[7] = list.get(i).getEnd_time().toString();
				
				smv.getDtmModel().addRow(rowData);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 선택된 테이블의 레코드에 대해 삭제 과정
	 */
	public void removeScreen() {
		System.out.println("삭제");
		System.out.println( smv.getTableMovieList().getSelectedRow());
		
		
		if(-1==smv.getTableMovieList().getSelectedRow()) {
			JOptionPane.showMessageDialog(smv, "먼저 상영 정보를 삭제할 영화를 목록에서 선택해 주세요","알림!",JOptionPane.CLOSED_OPTION);
			return;
			
			
			
		}else{
			StringBuilder deletemess=new StringBuilder();
			deletemess.append("[ ").append(smv.getTableMovieList().getValueAt(smv.getTableMovieList().getSelectedRow(), 1).toString()).append(" ]")
								.append("[ ").append(smv.getTableMovieList().getValueAt(smv.getTableMovieList().getSelectedRow(), 4).toString()).append(" ]")
								.append("\n").append("을 삭제 하시겟습니까?");
			
			StringBuilder deleteChe=new StringBuilder();
			deleteChe.append("[ ").append(smv.getTableMovieList().getValueAt(smv.getTableMovieList().getSelectedRow(), 1).toString()).append(" ]")
			.append("[ ").append(smv.getTableMovieList().getValueAt(smv.getTableMovieList().getSelectedRow(), 4).toString()).append(" ]")
			.append("\n").append(" 이 삭제되었습니다");
			String delete= smv.getTableMovieList().getValueAt(smv.getTableMovieList().getSelectedRow(), 1).toString();
				switch (JOptionPane.showConfirmDialog(smv, deletemess, "삭제확인",JOptionPane.OK_CANCEL_OPTION)) {
				case JOptionPane.OK_OPTION:
					try {
						int cnt=0;
						cnt=sm_Dao.remove(delete);
						nowScreen();
						if (cnt == 1) {
							JOptionPane.showMessageDialog(null, deleteChe,"알림",JOptionPane.CLOSED_OPTION);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case JOptionPane.CANCEL_OPTION:
					return;
					}	
		}
			
		
		
	}
	public String setImg(String imgFileName) {
		String path="C:/dev/Workspace/Cinema/src/kr/co/sist/sc/admin/images/movie/s_movie_";
		
		String allpath=path+imgFileName;
//		System.out.println(allpath);
		return allpath;
	}
	
	public void day() {
		//해당 달에 대해 날짜
		String  month = smv.getJcbInsertMonth().getSelectedItem().toString();
		int temp_month=Integer.parseInt(month);
		Calendar cal = new GregorianCalendar(2019,temp_month-1,1);
		int day = cal.getActualMaximum(Calendar.DATE);
		int nowday=cal.get(cal.DAY_OF_MONTH);
		smv.getDjcbInsertDay().removeAllElements();
		smv.getDjcbSearchDay().removeAllElements();
		for(int i=1;i<day+1;i++) {
		
			smv.getDjcbInsertDay().addElement(i);
			smv.getDjcbSearchDay().addElement(i);
//		smv.getdcbInsertDay.add/*Item(i);
//		jcbSearchDay.addItem(i);*/
		}
//		jcbInsertDay.setSelectedItem(new Integer(nowday));
//		jcbSearchDay.setSelectedItem(new Integer(nowday));
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == smv.getJbtOnScreenInsert()) {
			if("영화 선택".equals(smv.getJcbMovieSelect().getSelectedItem())) {
				JOptionPane.showMessageDialog(smv, "영화 선택을 하세요");
				return;
			}
			else {
				if("상영관".equals(smv.getJcbTheaterSelect().getSelectedItem())) {
					
					JOptionPane.showMessageDialog(smv, "영화관 선택 을 하세요");
				}else {
					register();
				}
			}
		}
		if(ae.getSource() == smv.getjbtOnScreenSearch()) {//상영조회
			if("영화 선택".equals(smv.getDjcbSearchMovie().getSelectedItem())) {
				JOptionPane.showMessageDialog(smv, "영화 선택을 하세요");
				return;
			}
			else {
				screenCheck();
				
			}
		}
		if(ae.getSource() == smv.getJbtOnScreenDelete()) {
			removeScreen();
		}
		if(ae.getSource() == smv.getJbtClose()) {
			smv.dispose();
		}
		
		//////////////////////////////////////////////////
		if(ae.getSource() == smv.getJcbInsertMonth() ) {
			//날짜
			day();
		}
		if(ae.getSource() == smv.getJcbSearchMonth()) {
			day();
		}
		
		
	}
	
	

	@Override
	public void windowClosing(WindowEvent e) {
		smv.dispose();
	}
//	 public static void main(String[] args) { 
//			SCAOnScreenManageController a=new SCAOnScreenManageController();
//			
//			 
//			 try {
//				 a.searchMovieList();
//			 } 
//			 
//			 catch (SQLException e) { 
//				 // TODO	 Auto-generated catch block 
//				 e.printStackTrace(); }
//	 }

}
