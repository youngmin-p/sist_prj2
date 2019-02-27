package kr.co.sist.sc.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.admin.vo.SCACalculationMovieSalesVO;
import kr.co.sist.sc.admin.vo.SCACalculationSnackSalesVO;

public class SCACalculationManageDAO {
	private static SCACalculationManageDAO scacm_dao;
	
	private SCACalculationManageDAO() {
		
	} // SCACalculationManageDAO
	
	public static SCACalculationManageDAO getInstance() {
		if (scacm_dao == null) {
			scacm_dao = new SCACalculationManageDAO();
		} // end if
		
		return scacm_dao;
	} // getInstance
	
	/**
	 * 영화 상영 수익 조회
	 * @param a
	 * @return
	 * @throws SQLException
	 */
	public List<SCACalculationMovieSalesVO> selectMovieSales(String searchDate) throws SQLException {
		List<SCACalculationMovieSalesVO> list = new ArrayList<SCACalculationMovieSalesVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			
		} finally {
			if (con != null) { con.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (rs != null) { rs.close(); } // end if
		} // end finally
		
		return list;
	} // selectMovieSales
	
	/**
	 * 스낵 판매 수익 조회
	 * @param a
	 * @return
	 * @throws SQLException
	 */
	public List<SCACalculationSnackSalesVO> selectSnackSales(String searchDate) throws SQLException {
		List<SCACalculationSnackSalesVO> list = new ArrayList<SCACalculationSnackSalesVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			
			String selectQuery = 
					" select snack_name, quan, total_price " + 
					" from (select s.snack_name, sum(ss.quan) quan, sum(s.snack_price * ss.quan) total_price, to_char(ss.snack_sale_date, 'yyyy-mm-dd') snack_sale_date " + 
					"       from snack s, snack_sale ss " + 
					"       where (s.snack_name = ss.snack_name) " + 
					"       group by s.snack_name, to_char(ss.snack_sale_date, 'yyyy-mm-dd') " + 
					"       order by total_price desc, quan desc) " + 
					" where snack_sale_date = ? ";
			
			pstmt = con.prepareStatement(selectQuery);
			
			pstmt.setString(1, searchDate);
			
			rs = pstmt.executeQuery();
			
			SCACalculationSnackSalesVO scacss_vo = null;
			
			while (rs.next()) {
				scacss_vo = new SCACalculationSnackSalesVO(
						rs.getString("snack_name"), rs.getInt("quan"), rs.getInt("total_price"));
				
				list.add(scacss_vo);
			} // end while
		} finally {
			if (con != null) { con.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (rs != null) { rs.close(); } // end if
		} // end finally
		
		return list;
	} // selectSnackSales
	
} // class
