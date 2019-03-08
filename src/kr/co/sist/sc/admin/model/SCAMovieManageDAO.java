package kr.co.sist.sc.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import kr.co.sist.sc.admin.vo.SCAMovieDatailsVO;
import kr.co.sist.sc.admin.vo.SCAMovieManageVO;
import kr.co.sist.sc.admin.vo.SCAMovieInsertVO;

public class SCAMovieManageDAO {
	private static SCAMovieManageDAO s_Dao;

	private SCAMovieManageDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	///////////////////////////////

	public static SCAMovieManageDAO getInstance() {
		if (s_Dao == null) {
			s_Dao = new SCAMovieManageDAO();
		}
		return s_Dao;
	}

//	public Connection getconn() throws SQLException {
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String id = "scott";
//		String pass = "tiger";
//		Connection con = DriverManager.getConnection(url, id, pass);
//		return con;
//
//	}
	
	public Connection getconn() throws SQLException {
	String url = "jdbc:oracle:thin:@211.63.89.142:1521:orcl";
	String id = "scadmin";
	String pass = "sc1234";
	Connection con = DriverManager.getConnection(url, id, pass);
	return con;
	
}

	public List<SCAMovieManageVO> showMovie() throws SQLException {
//	관리 화면에 접속 하였을때 전체 영화 목록을 db에서 읽어 오늘 부분		
		List<SCAMovieManageVO> list = new ArrayList<SCAMovieManageVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getconn();

			String selectMovie = "SELECT Movie_code, MOVIE_IMG, MOVIE_TITLE FROM MOVIE";
			pstmt = con.prepareStatement(selectMovie);
			rs = pstmt.executeQuery();

			SCAMovieManageVO slv = null;
			while (rs.next()) {
				slv = new SCAMovieManageVO(rs.getString("movie_code"), rs.getString("movie_img"),
						rs.getString("movie_title"));
				list.add(slv);
			}
		}

		finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	public SCAMovieDatailsVO selectMovie(String code) throws SQLException {
		// 마우스의 의해 더블클릭된 레코드의 영화 제목을 읽어와 제목으로 db에서 조회하여 상세 정보를 읽어옴

		SCAMovieDatailsVO scdvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getconn();

			String selectMovie = "select movie_code,movie_title,movie_img,genre,country,director,movie_grade"
					+ ",playdate,synopsis,actor,runningtime from movie where movie_code=?";
			pstmt = con.prepareStatement(selectMovie);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				scdvo = new SCAMovieDatailsVO(rs.getString("movie_code"), rs.getString("movie_title"),
						rs.getString("movie_img"), rs.getString("genre"), rs.getString("country"),
						rs.getString("director"), rs.getString("movie_grade"), rs.getString("playdate"),
						rs.getString("synopsis"), rs.getString("actor"), rs.getInt("runningtime"));
			}
		}
		finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return scdvo;
	}

	public boolean selectDeleteMovie(String code) throws SQLException {
		// db에서 영화 삭제시 확인을하고 확인 값을 받고 commit를 날려야하나? 아니면 rollback 를 내가 해야하나?

		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getconn();
			String deleteQuery = "delete from movie where movie_code=?";
			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, code);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;

			}

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return flag;

	}

	public void registerMovie(SCAMovieInsertVO scrvo) throws SQLException {
		// 추가
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getconn();
			StringBuilder register = new StringBuilder();
			register.append("insert into movie ").append(
					"(MOVIE_CODE,	MOVIE_TITLE,MOVIE_IMG,GENRE,COUNTRY,DIRECTOR,MOVIE_GRADE,RUNNINGTIME,PLAYDATE,SYNOPSIS,ACTOR,MOVIE_DATE,ADMIN_ID)")
					.append("values(movie_code,?,?,?,?,?,?,?,?,?,?,sysdate,?)");
			pstmt = con.prepareStatement(register.toString());
			pstmt.setString(1, scrvo.getMovie_title());
			pstmt.setString(2, scrvo.getMovie_img());
			pstmt.setString(3, scrvo.getGenre());
			pstmt.setString(4, scrvo.getCountry());
			pstmt.setString(5, scrvo.getDirector());
			pstmt.setString(6, scrvo.getMovie_grade());
			pstmt.setInt(7, scrvo.getRunningtime());
			pstmt.setString(8, scrvo.getPlaydate());
			pstmt.setString(9, scrvo.getSynopsis());
			pstmt.setString(10, scrvo.getActor());
			pstmt.setString(11, scrvo.getAdmin_id());
			
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}

	/*
	 * public static void main(String[] args) { SsangyongCinemaDAO ff=new
	 * SsangyongCinemaDAO();
	 * 
	 * try { ff.selectDeleteMovie("M_0000012"); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * }
	 */

}
