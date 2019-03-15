package kr.co.sist.sc.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.vo.SCAMemberInformVO;
import kr.co.sist.sc.admin.vo.SCAMemberSelectVO;
import kr.co.sist.sc.admin.vo.SCAMemberUpdateVO;

public class SCAMemberManageDAO {

	private static SCAMemberManageDAO scamm_dao;

	private SCAMemberManageDAO() {

	}

	public static SCAMemberManageDAO getInstance() {
		if (scamm_dao == null) {
			scamm_dao = new SCAMemberManageDAO();
		} // end if
		return scamm_dao;
	} // getInstance

	public List<SCAMemberSelectVO> selectAllMember() throws SQLException {
		List<SCAMemberSelectVO> list = new ArrayList<SCAMemberSelectVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = SCAConnect.getInstance().getConn();
			String selectAllMember = " SELECT MEMBER_ID, NAME, BIRTHDATE FROM MEMBER WHERE PASSWORD != ' ' ";

			pstmt = con.prepareStatement(selectAllMember);
			rs = pstmt.executeQuery();

			SCAMemberSelectVO scamsvo = null;
			while (rs.next()) {
				scamsvo = new SCAMemberSelectVO(rs.getString("member_id"), rs.getString("name"),
						rs.getString("birthdate"));
				list.add(scamsvo);
			} // end while

		} finally {
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
	} // selectAllMember

	public SCAMemberSelectVO selectOneMember(String memberId) throws SQLException {
		SCAMemberSelectVO scamsvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = SCAConnect.getInstance().getConn();
			String selectOneMember = " SELECT MEMBER_ID, NAME, BIRTHDATE FROM MEMBER WHERE MEMBER_ID=? ";
			pstmt = con.prepareStatement(selectOneMember);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				scamsvo = new SCAMemberSelectVO(rs.getString("member_id"), rs.getString("name"),
						rs.getString("birthdate"));
			} // end if

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

		return scamsvo;
	} // selectOneMember

	public SCAMemberInformVO selectMemberInform(String memberId) throws SQLException{
		SCAMemberInformVO scamivo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			String memberInform = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
			pstmt = con.prepareStatement(memberInform);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				scamivo = new SCAMemberInformVO(
						rs.getString("member_id"), 
						rs.getString("password"), 
						rs.getString("name"), 
						rs.getString("birthdate"), 
						rs.getString("phone"), 
						rs.getString("membership"),
						rs.getString("input_date"), 
						rs.getInt("hold_point"), 
						rs.getInt("acc_point"));
			} // end if
			
		} finally {
			if( rs != null ) { rs.close(); } // end if
			if( pstmt != null ) { pstmt.close(); } // end if
			if( con != null ) { con.close(); } // end if
		} // end finally
		
		return scamivo;
	} // selectMemberInform
	
	public boolean updateMember(SCAMemberUpdateVO scamuvo) throws SQLException{
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			String updateMember = "UPDATE MEMBER SET NAME=?, PHONE=? WHERE MEMBER_ID=?";
			pstmt = con.prepareStatement(updateMember);
			pstmt.setString(1, scamuvo.getName());
			pstmt.setString(2, scamuvo.getPhone());
			pstmt.setString(3, scamuvo.getMemberId());
			
			int cnt = pstmt.executeUpdate();
			
			if( cnt == 1) {
				flag = true;
			} // end if
			
		} finally {
			if( pstmt != null ) { pstmt.close(); }
			if( con != null ) { con.close(); }
		} // end finally

		return flag;
	}
	
	public boolean deleteMember(String memberId) throws SQLException{
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = SCAConnect.getInstance().getConn();
			String deleteMember = " update member set password=' ', name=' ', birthdate=' ', phone=' ', membership=' ', hold_point=0, acc_point=0 where member_id=? ";
			pstmt = con.prepareStatement(deleteMember);
			pstmt.setString(1, memberId.trim());
			
			int cnt = pstmt.executeUpdate();
			if( cnt == 1) {
				flag = true;
			} // end if
			
		} finally {
			if ( pstmt != null ) { pstmt.close(); } // end if
			if ( con != null ) { con.close(); } // end if
		} // end finally
		
		return flag;
	} // deleteMember
	
} // class
