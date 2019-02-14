package kr.co.sist.sc.admin.model;

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
	
	
	
} // class
