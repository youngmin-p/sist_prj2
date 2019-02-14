package kr.co.sist.cinema.admin.model;

public class SCAMovieManageDAO {
	private static SCAMovieManageDAO scamm_dao;
	
	private SCAMovieManageDAO() {
		
	} // SCAMovieManageDAO
	
	public static SCAMovieManageDAO getInstance() {
		if (scamm_dao == null) {
			scamm_dao = new SCAMovieManageDAO();
		} // end if
		
		return scamm_dao;
	} // getInstance
	
	
	
} // class
