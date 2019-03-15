package kr.co.sist.sc.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.admin.vo.SCABookListVO;
import kr.co.sist.sc.admin.vo.SCABookMovieListVO;
import kr.co.sist.sc.admin.vo.SCABookOnScreenVO;
import kr.co.sist.sc.admin.vo.SCABookScreenVO;
import kr.co.sist.sc.admin.vo.SCABookSeatVO;

public class SCABookManageDAO {
	private static SCABookManageDAO scabm_dao;
	
	private SCABookManageDAO() {
		
	} // SCABookManageDAO
	
	public static SCABookManageDAO getInstance() {
		if (scabm_dao == null) {
			scabm_dao = new SCABookManageDAO();
		} // end if
		
		return scabm_dao;
	} // getInstance
	
	/**
	 * 영화명 조회
	 * @return
	 * @throws SQLException
	 */
	public List<SCABookMovieListVO> selectMovieList() throws SQLException {
		List<SCABookMovieListVO> list = new ArrayList<SCABookMovieListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			// 당일 상영 중인 영화 조회
			String selectQuery = 
					"SELECT DISTINCT M.MOVIE_CODE, M.MOVIE_TITLE " + 
					"FROM MOVIE M, ON_SCREEN OS " + 
					"WHERE (M.MOVIE_CODE = OS.MOVIE_CODE) " + 
//					"AND SCREEN_DATE = '2019-04-01' " + 
					"AND SCREEN_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') " + 
					"ORDER BY M.MOVIE_CODE";
			
			pstmt = con.prepareStatement(selectQuery);
			
			rs = pstmt.executeQuery();
			
			SCABookMovieListVO scabml_vo = null;
			
			while (rs.next()) {
				scabml_vo = new SCABookMovieListVO(rs.getString("movie_code"), rs.getString("movie_title"));
				
				list.add(scabml_vo);
			} // end while
		} finally {
			if (rs != null) { rs.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return list;
	} // selectMovieList
	
	/**
	 * 1) 최초 수행 시 당일의 전체 상영 영화 정보를 조회
	 * 2) 영화명으로 조회 시 당일의 해당 상영 영화 정보를 조회
	 * @param movie_code
	 * @return
	 * @throws SQLException
	 */
	public List<SCABookOnScreenVO> selectBookOnScreen(String movie_code) throws SQLException {
		List<SCABookOnScreenVO>	list = new ArrayList<SCABookOnScreenVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			// 당일 상영 중인 영화 조회
			StringBuilder selectQuery = new StringBuilder();
			
			selectQuery
			.append(" SELECT M.MOVIE_CODE, M.MOVIE_TITLE, OS.SCREEN_NUM, T.SCREEN_NAME, OS.START_TIME, OS.END_TIME, ")
			.append("        (T.SEAT_COUNT - (SELECT NVL(MAX((SELECT SUM(PERSONNEL) FROM BOOK WHERE SCREEN_NUM = OS.SCREEN_NUM)), 0) SEAT_REMAIN FROM BOOK)) SEAT_REMAIN, ")
			.append("        T.SEAT_COUNT ")
			.append(" FROM MOVIE M, ON_SCREEN OS, THEATER T ")
			.append(" WHERE (OS.MOVIE_CODE = M.MOVIE_CODE) ")
			.append("   AND (OS.SCREEN_NAME = T.SCREEN_NAME) ")
			.append("   AND OS.SCREEN_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') ");
//			.append("   AND OS.SCREEN_DATE = '2019-04-01' ");
			
			if (!movie_code.equals("")) {
				// movie_code가 empty가 아니라면, movie_code로 해당 영화를 조회
				selectQuery
				.append("   AND OS.MOVIE_CODE = ? ");
			} // end if
			
			selectQuery
			.append(" ORDER BY OS.START_TIME ");
			
			pstmt = con.prepareStatement(selectQuery.toString());
			
			if (!movie_code.equals("")) {
				pstmt.setString(1, movie_code);
			} // end if 
			
			rs = pstmt.executeQuery();
			
			SCABookOnScreenVO scabos_vo = null;
			
			while (rs.next()) {
				scabos_vo = new SCABookOnScreenVO(
						rs.getString("movie_code"), rs.getString("movie_title"), 
						rs.getString("screen_num"), rs.getString("screen_name"), 
						rs.getString("start_time"), rs.getString("end_time"), 
						rs.getInt("seat_remain"), rs.getInt("seat_count"));
				
				list.add(scabos_vo);
			} // end while
		} finally {
			if (rs != null) { rs.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return list;
	} // selectBookOnScreen
	
	/**
	 * 예매 좌석 조회
	 * 상영 번호 첫 자리가 'N'인 경우에는 스탠다드관, 'P'인 경우에는 프리미엄관
	 * @param screen_num
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> selectBookSeat(String screen_num) throws SQLException {
		List<Integer> list = new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			StringBuilder selectQuery = new StringBuilder();
			
			selectQuery
			.append(" SELECT SEAT_NUM ");
			
			if (screen_num.substring(0, 1).equals("N")) {
				selectQuery
				.append(" FROM BOOK B, STANDARD_SEAT SS ")
				.append(" WHERE (SS.BOOK_NUMBER = B.BOOK_NUMBER) ")
				.append("   AND B.SCREEN_NUM = ? ");
			} // end if
			
			if (screen_num.substring(0, 1).equals("P")) {
				selectQuery
				.append(" FROM BOOK B, PREMIUM_SEAT PS ")
				.append(" WHERE (PS.BOOK_NUMBER = B.BOOK_NUMBER) ")
				.append("   AND B.SCREEN_NUM = ? ");
			} // end if
			
			selectQuery
			.append(" ORDER BY SEAT_NUM ");
			
			pstmt = con.prepareStatement(selectQuery.toString());
			
			pstmt.setString(1, screen_num);
			
			rs = pstmt.executeQuery();
			
			int seatNum = 0;
			
			while (rs.next()) {
				seatNum = rs.getInt("seat_num");
				
				list.add(seatNum);
			} // end while
		} finally {
			if (rs != null) { rs.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return list;
	} //selectBookSeat
	
	/**
	 * 당일 예매 조회
	 * 해당 예매 번호가 존재하는 좌석 테이블에서 좌석 번호를 가져오는 일
	 * @param movie_code
	 * @return
	 * @throws SQLException
	 */
	public List<SCABookListVO> selectBookList(String movie_code) throws SQLException {
		List<SCABookListVO> list = new ArrayList<SCABookListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			StringBuilder selectQuery = new StringBuilder(); 
			
			selectQuery
			.append(" SELECT * FROM ( ")
			.append(" 	SELECT * FROM ( ")
			.append(" 		SELECT M.MEMBER_ID, B.BOOK_NUMBER, B.PERSONNEL, SS.SEAT_NUM, B.PAYMENT_DATE ")
			.append(" 		FROM MEMBER M, BOOK B, STANDARD_SEAT SS, ON_SCREEN OS ")
			.append(" 		WHERE (B.MEMBER_ID = M.MEMBER_ID) ")
			.append(" 		  AND (SS.BOOK_NUMBER = B.BOOK_NUMBER) ")
			.append(" 		  AND (OS.SCREEN_NUM = B.SCREEN_NUM) ")
			.append(" 		  AND OS.SCREEN_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') ");
//			.append(" 		  AND OS.SCREEN_DATE = '2019-04-01' ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  AND OS.MOVIE_CODE = ? ");
			} // end if
			
			selectQuery
			.append(" 		UNION ")
			.append(" 		SELECT M.MEMBER_ID, B.BOOK_NUMBER, B.PERSONNEL, PS.SEAT_NUM, B.PAYMENT_DATE ")
			.append(" 		FROM MEMBER M, BOOK B, PREMIUM_SEAT PS, ON_SCREEN OS ")
			.append(" 		WHERE (B.MEMBER_ID = M.MEMBER_ID) ")
			.append(" 		  AND (PS.BOOK_NUMBER = B.BOOK_NUMBER) ")
			.append(" 		  AND (OS.SCREEN_NUM = B.SCREEN_NUM) ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  AND OS.MOVIE_CODE = ? ");
			} // end if
			
			selectQuery
			.append(" 		  AND OS.SCREEN_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD')) ")
//			.append(" 		  AND OS.SCREEN_DATE = '2019-04-01') ")
			.append(" 		UNION ")
			.append(" 		SELECT * FROM ( ")
			.append(" 		SELECT B.MEMBER_ID, B.BOOK_NUMBER, B.PERSONNEL, SS.SEAT_NUM, B.PAYMENT_DATE ")
			.append(" 		FROM BOOK B, STANDARD_SEAT SS, ON_SCREEN OS ")
			.append(" 		WHERE (SS.BOOK_NUMBER = B.BOOK_NUMBER) ")
			.append(" 		  AND (OS.SCREEN_NUM = B.SCREEN_NUM) ")
			.append(" 		  AND OS.SCREEN_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') ");
//			.append(" 		  AND OS.SCREEN_DATE = '2019-04-01' ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  AND OS.MOVIE_CODE = ? ");
			} // end if
			
			selectQuery
			.append(" 		  AND MEMBER_ID IS NULL ")
			.append(" 		UNION ")
			.append(" 		SELECT B.MEMBER_ID, B.BOOK_NUMBER, B.PERSONNEL, PS.SEAT_NUM, B.PAYMENT_DATE ")
			.append(" 		FROM BOOK B, PREMIUM_SEAT PS, ON_SCREEN OS ")
			.append(" 		WHERE (PS.BOOK_NUMBER = B.BOOK_NUMBER) ")
			.append(" 		  AND (OS.SCREEN_NUM = B.SCREEN_NUM) ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  AND OS.MOVIE_CODE = ? ");
			} // end if
			
			selectQuery
			.append(" 		  AND OS.SCREEN_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') ")
//			.append(" 		  AND OS.SCREEN_DATE = '2019-04-01' ")
			.append(" 		  AND MEMBER_ID IS NULL)) ")
			.append(" ORDER BY PAYMENT_DATE, SEAT_NUM ");
			
			pstmt = con.prepareStatement(selectQuery.toString());
			
			if (!movie_code.equals("")) {
				for (int i = 1; i < 5; i++) {
					pstmt.setString(i, movie_code);
				} // end for
			} // end if
			
			rs = pstmt.executeQuery();
			
			SCABookListVO scabl_vo = null;
			
			while (rs.next()) {
				scabl_vo = new SCABookListVO(
						rs.getString("member_id"), rs.getString("book_number"), rs.getString("payment_date"), 
						rs.getInt("personnel"), rs.getInt("seat_num"));
				
				list.add(scabl_vo);
			} // end while
		} finally {
			if (rs != null) { rs.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return list;
	} // selectBookList
	
	/**
	 * 트랜잭션 처리
	 * @throws SQLException
	 */
	public boolean insertBookTransfer(SCABookScreenVO scabsc_vo, List<Integer> seat_num) throws SQLException {
		boolean flag = false;
		
		Connection con = null;
		
		boolean transFlag = false;
		
		try {
			con = SCAConnect.getInstance().getConn();
			con.setAutoCommit(false);
			
			String book_number = SCABookManageDAO.getInstance().insertBookScreen(con, scabsc_vo);
			
			SCABookSeatVO scabse_vo = new SCABookSeatVO(book_number, seat_num);
			
			transFlag = SCABookManageDAO.getInstance().insertSeatScreen(con, scabse_vo);
		} finally {
			try {
				if (transFlag) {
					con.commit();
					con.setAutoCommit(true);
					flag = true;
				} else {
					con.rollback();
				} // end else
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} // end catch
			
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return flag;
	} // insertBookTransfer
	
	/**
	 * 예매 테이블 삽입
	 * @param con
	 * @param scabs_vo
	 * @return
	 * @throws SQLException
	 */
	public String insertBookScreen(Connection con, SCABookScreenVO scabs_vo) throws SQLException {
		String book_number = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String insertQuery = 
					" INSERT INTO BOOK (BOOK_NUMBER, PERSONNEL, PAYMENT_DATE, MOVIE_START, MEMBER_ID, SCREEN_NUM) " + 
					" VALUES (CONCAT(?, TRIM(TO_CHAR(SEQ_BOOK.NEXTVAL, '000'))), ?, SYSDATE, " + 
					" TO_DATE(?, 'YYYY-MM-DD HH24:MI'), '', ?) ";
			
			// 1, book_number = (screen_name + movie_code)
			// 2, personnel
			// 3, movie_start ('yyyy-mm-dd hh24:mi')
			// 4, screen_num
			
			pstmt = con.prepareStatement(insertQuery);
			
			book_number = scabs_vo.getScreen_name() + scabs_vo.getMovie_code();
			String[] arrDate = scabs_vo.getMovie_start().split("/");
			
			pstmt.setString(1, book_number);
			pstmt.setInt(2, scabs_vo.getPersonnel());
			pstmt.setString(3, arrDate[0] + " " + arrDate[1]);
			pstmt.setString(4, scabs_vo.getScreen_num());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			// 예매 번호 조회
			String selectQuery = 
					" SELECT BOOK_NUMBER " + 
					" FROM (SELECT BOOK_NUMBER " + 
					"       FROM BOOK " + 
					"       ORDER BY PAYMENT_DATE DESC) " + 
					" WHERE ROWNUM = '1' ";
			
			pstmt = con.prepareStatement(selectQuery);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				book_number = rs.getString("book_number");
			} // end if
		} finally {
			if (rs != null) { rs.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
		} // end finally
		
		return book_number;
	} // insertBookScreen
	
	/**
	 * 좌석 테이블 삽입
	 * @param con
	 * @param scabs_vo
	 * @return
	 * @throws SQLException
	 */
	public boolean insertSeatScreen(Connection con, SCABookSeatVO scabs_vo) throws SQLException {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		try {
			String screen_name = scabs_vo.getBook_number().substring(0, 1);
			
			StringBuilder insertQuery = new StringBuilder();
			
			if (screen_name.equals("N")) {
				insertQuery.append(" INSERT INTO STANDARD_SEAT (SEAT_NUM, BOOK_NUMBER) ");
			} // end if
			
			if (screen_name.equals("P")) {
				insertQuery.append(" INSERT INTO PREMIUM_SEAT (SEAT_NUM, BOOK_NUMBER) ");
			} // end if
			
			insertQuery.append(" VALUES (?, ?) ");
			
			pstmt = con.prepareStatement(insertQuery.toString());
			
			List<Integer> list = scabs_vo.getSeat_num();
			
			for (int i = 0; i < list.size(); i++) {
				pstmt.setInt(1, list.get(i));
				pstmt.setString(2, scabs_vo.getBook_number());
				
				pstmt.executeUpdate();
			} // end for
			
			flag = true;
		} finally {
			if (pstmt != null) { pstmt.close(); } // end if
		} // end finally
		
		return flag;
	} // insertSeatScreen
	
} // class
