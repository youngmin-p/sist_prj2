package kr.co.sist.sc.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.admin.vo.SCASnackMenuAddVO;
import kr.co.sist.sc.admin.vo.SCASnackMenuSelectVO;
import kr.co.sist.sc.admin.vo.SCASnackMenuTableSelectVO;
import kr.co.sist.sc.admin.vo.SCASnackPaymentVO;

public class SCASnackManageDAO {

	private static SCASnackManageDAO scasm_dao;

	private SCASnackManageDAO() {
	} // SCASnackMangeDAO

	public static SCASnackManageDAO getInstance() {
		if (scasm_dao == null) {
			scasm_dao = new SCASnackManageDAO();
		} // end if

		return scasm_dao;
	} // getInstance

	public SCASnackMenuSelectVO selectSnackMenuDetails(String snackName) throws SQLException {
		SCASnackMenuSelectVO scasmsvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = SCAConnect.getInstance().getConn();
			String snackMenuSelect = "SELECT SNACK_NAME, SNACK_PRICE, SNACK_IMG, SNACK_INFO FROM SNACK WHERE SNACK_NAME = ?";
			pstmt = con.prepareStatement(snackMenuSelect);
			pstmt.setString(1, snackName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				scasmsvo = new SCASnackMenuSelectVO(rs.getString("snack_name"), rs.getString("snack_img"),
						rs.getString("snack_info"), rs.getInt("snack_price"));
			} // end if

		} finally {
			if (rs != null) {rs.close();	} // end if
			if (pstmt != null) {	pstmt.close();} // end if
			if (con != null) {con.close();	} // end if
		} // end finally

		return scasmsvo;
	} // selectSnackMenuDetails

	public List<SCASnackMenuTableSelectVO> selectSnackMenuTable() throws SQLException {
		List<SCASnackMenuTableSelectVO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = SCAConnect.getInstance().getConn();
			String selectSnackMenu = "SELECT SNACK_NAME, SNACK_IMG FROM SNACK WHERE CHECK_DELETE = 'N' ORDER BY SNACK_DATE ASC";
			pstmt = con.prepareStatement(selectSnackMenu);
			rs = pstmt.executeQuery();

			SCASnackMenuTableSelectVO scasmtsvo = null;
			list = new ArrayList<SCASnackMenuTableSelectVO>();

			while (rs.next()) {
				scasmtsvo = new SCASnackMenuTableSelectVO(rs.getString("snack_name"), rs.getString("snack_img"));
				list.add(scasmtsvo);
			} // end while

		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

		return list;
	} // selectSnackMenuTable
	
	public boolean insertSnackMenu(SCASnackMenuAddVO scasmavo) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = SCAConnect.getInstance().getConn();
			StringBuilder insertSnackMenu = new StringBuilder();
			insertSnackMenu.append(
					"INSERT INTO SNACK(SNACK_NAME, SNACK_PRICE, SNACK_IMG, SNACK_INFO, SNACK_DATE, CHECK_DELETE)")
					.append("VALUES(?, ?, ?, ?, SYSDATE, 'N')");
			pstmt = con.prepareStatement(insertSnackMenu.toString());
			pstmt.setString(1, scasmavo.getSnack_name());
			pstmt.setInt(2, scasmavo.getSnack_price());
			pstmt.setString(3, scasmavo.getSnack_img());
			pstmt.setString(4, scasmavo.getSnack_info());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;
			} // end if

		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

		return flag;
	} // inserSnackMenu

	public boolean deleteSnackMenu(String snackName) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = SCAConnect.getInstance().getConn();
			String deleteSnackMenuFK = "UPDATE SNACK SET CHECK_DELETE = 'Y' WHERE SNACK_NAME = ?";
			pstmt = con.prepareStatement(deleteSnackMenuFK.toString());
			pstmt.setString(1, snackName);

			int cnt = pstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
			} // end if

		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

		return flag;
	} // deleteSnackMenu

	public void insertPayment(SCASnackPaymentVO scaspvo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			StringBuilder insertPayment = new StringBuilder();
			insertPayment.append("INSERT INTO SNACK_SALE(SNACK_ORDER_NUM, QUAN, CHECK_EXCHANGE, SNACK_SALE_DATE, SNACK_NAME) ")
			.append(" VALUES(SNACK_CODE, 	?, 'Y', SYSDATE, ?)");
			
			pstmt = con.prepareStatement(insertPayment.toString());
			pstmt.setInt(1, scaspvo.getQuan());
			pstmt.setString(2, scaspvo.getSnackName());
			
			pstmt.executeUpdate();
			
		} finally {
			if( pstmt != null ) { pstmt.close(); } // end if
			if( con != null ) { con.close(); } // end if
		} // end finally
		
	} // insertPayment
	
} // class
