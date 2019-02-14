package kr.co.sist.cinema.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.cinema.admin.vo.SCALoginCheckVO;
import kr.co.sist.cinema.admin.vo.SCALoginVO;

public class SCALoginDAO {
	private static SCALoginDAO scal_dao;
	
	private SCALoginDAO() {
		
	} // SCALoginDAO
	
	public static SCALoginDAO getInstance() {
		if (scal_dao == null) {
			scal_dao = new SCALoginDAO();
		} // end if
		
		return scal_dao;
	} // getInstance

	public SCALoginVO loginAdmin(SCALoginCheckVO scalc_vo) throws SQLException {
		SCALoginVO scal_vo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			String selectQuery = 
					"select admin_id, password, name " + 
					"from admin " + 
					"where admin_id = ? and password = ?";
			
			pstmt = con.prepareStatement(selectQuery);
			
			pstmt.setString(1, scalc_vo.getAdmin_id());
			pstmt.setString(2, scalc_vo.getPassword());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (scalc_vo.getAdmin_id().equals(rs.getString("admin_id")) 
						&& scalc_vo.getPassword().equals(rs.getString("password"))) {
					scal_vo = new SCALoginVO(
							rs.getString("admin_id"), 
							rs.getString("name"));
				} // end if
			} // end while
		} finally {
			if (pstmt != null) { pstmt.close(); } // end if
			if (con != null) { con.close(); } // end if
		} // end finally
		
		return scal_vo;
	} // loginCheck
	
} // class
