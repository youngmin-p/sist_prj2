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
	 * @param today
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
			// "and screen_date = to_char(sysdate, 'YYYY-MM-DD') " + 
			String selectQuery = 
					"select distinct m.movie_code, m.movie_title " + 
					"from movie m, on_screen os " + 
					"where (m.movie_code = os.movie_code) " + 
					"and screen_date = '2019-01-29' " + 
					"order by m.movie_code";
			
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
			// "and screen_date = to_char(sysdate, 'YYYY-MM-DD') " + 
			StringBuilder selectQuery = new StringBuilder();
			
			selectQuery
			.append(" select m.movie_code, m.movie_title, os.screen_num, t.screen_name, os.start_time, os.end_time, ")
			.append("        (t.seat_count - (select nvl(max((select sum(personnel) from book where screen_num = os.screen_num)), 0) seat_remain from book)) seat_remain, ")
			.append("        t.seat_count ")
			.append(" from movie m, on_screen os, theater t ")
			.append(" where (os.movie_code = m.movie_code) ")
			.append("   and (os.screen_name = t.screen_name) ")
			.append("   and os.screen_date = '2019-01-29' ");
			
			if (!movie_code.equals("")) {
				// movie_code가 empty가 아니라면, movie_code로 해당 영화를 조회
				selectQuery
				.append("   and os.movie_code = ? ");
			} // end if
			
			selectQuery
			.append(" order by os.start_time ");
			
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
			.append(" select seat_num ");
			
			if (screen_num.substring(0, 1).equals("N")) {
				selectQuery
				.append(" from book b, standard_seat ss ")
				.append(" where (ss.book_number = b.book_number) ")
				.append("   and b.screen_num = ? ");
			} // end if
			
			if (screen_num.substring(0, 1).equals("P")) {
				selectQuery
				.append(" from book b, premium_seat ps ")
				.append(" where (ps.book_number = b.book_number) ")
				.append("   and b.screen_num = ? ");
			} // end if
			
			selectQuery
			.append(" order by seat_num ");
			
			pstmt = con.prepareStatement(selectQuery.toString());
			
			if (screen_num.substring(0, 1).equals("N")) {
				pstmt.setString(1, screen_num);
			} // end if
			
			if (screen_num.substring(0, 1).equals("P")) {
				pstmt.setString(1, screen_num);
			} // end if
			
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
	 * 해당 예매 번호가 존재하는 좌석 테이블에서 좌석 번호를 가져와야 한다.
	 * 예매 번호가 S에 존재하는지, P에 존재하는지 동적 쿼리를 작성?
	 * 
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
			.append(" select * from ( ")
			.append(" 	select * from ( ")
			.append(" 		select m.member_id, b.book_number, b.personnel, ss.seat_num, b.payment_date ")
			.append(" 		from member m, book b, standard_seat ss, on_screen os ")
			.append(" 		where (b.member_id = m.member_id) ")
			.append(" 		  and (ss.book_number = b.book_number) ")
			.append(" 		  and (os.screen_num = b.screen_num) ")
			.append(" 		  and os.screen_date = '2019-01-29' ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  and os.movie_code = ? ");
			} // end if
			
			selectQuery
			.append(" 		union ")
			.append(" 		select m.member_id, b.book_number, b.personnel, ps.seat_num, b.payment_date ")
			.append(" 		from member m, book b, premium_seat ps, on_screen os ")
			.append(" 		where (b.member_id = m.member_id) ")
			.append(" 		  and (ps.book_number = b.book_number) ")
			.append(" 		  and (os.screen_num = b.screen_num) ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  and os.movie_code = ? ");
			} // end if
			
			selectQuery
			.append(" 		  and os.screen_date = '2019-01-29') ")
			.append(" 		union ")
			.append(" 		select * from ( ")
			.append(" 		select b.member_id, b.book_number, b.personnel, ss.seat_num, b.payment_date ")
			.append(" 		from book b, standard_seat ss, on_screen os ")
			.append(" 		where (ss.book_number = b.book_number) ")
			.append(" 		  and (os.screen_num = b.screen_num) ")
			.append(" 		  and os.screen_date = '2019-01-29' ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  and os.movie_code = ? ");
			} // end if
			
			selectQuery
			.append(" 		  and member_id is null ")
			.append(" 		union ")
			.append(" 		select b.member_id, b.book_number, b.personnel, ps.seat_num, b.payment_date ")
			.append(" 		from book b, premium_seat ps, on_screen os ")
			.append(" 		where (ps.book_number = b.book_number) ")
			.append(" 		  and (os.screen_num = b.screen_num) ");
			
			if (!movie_code.equals("")) {
				selectQuery.append(" 		  and os.movie_code = ? ");
			} // end if
			
			selectQuery
			.append(" 		  and os.screen_date = '2019-01-29' ")
			.append(" 		  and member_id is null)) ")
			.append(" order by payment_date, seat_num ");
			
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
					System.out.println("transaction is rollback.");
				} // end else
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} // end catch
			
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return flag;
	} // insertBookTransfer
	
	public String insertBookScreen(Connection con, SCABookScreenVO scabs_vo) throws SQLException {
		String book_number = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String insertQuery = 
					" insert into book (book_number, personnel, payment_date, movie_start, member_id, screen_num) " + 
					" values (concat(?, trim(to_char(seq_book.nextval, '000'))), ?, sysdate, " + 
					" to_date(?, 'yyyy-mm-dd hh24:mi'), '', ?) ";
			
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
			
			// 
			String selectQuery = 
					" select book_number " + 
					" from (select book_number " + 
					"       from book " + 
					"       order by payment_date desc) " + 
					" where rownum = '1' ";
			
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
	 * 좌석 테이블 insert
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
				insertQuery.append(" insert into standard_seat (seat_num, book_number) ");
			} // end if
			
			if (screen_name.equals("P")) {
				insertQuery.append(" insert into premium_seat (seat_num, book_number) ");
			} // end if
			
			insertQuery.append(" values (?, ?) ");
			
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
