package kr.co.sist.cinema.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SCAConnect {
	private static SCAConnect sca_conn;
	
	private SCAConnect() {
		
	} // SCAConnect
	
	public static SCAConnect getInstance() {
		if (sca_conn == null) {
			sca_conn = new SCAConnect();
		} // end if
		
		return sca_conn;
	} // getInstance
	
	public Connection getConn() throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} // end catch
		
		String url = "jdbc:oracle:thin:@211.63.89.142:1521:orcl";
		String user = "scadmin";
		String password = "sc1234";
		
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
	} // getConn

} // class
