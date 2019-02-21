package kr.co.sist.sc.admin.vo;

public class SCALoginCheckVO {
	String admin_id, password;
	
	public SCALoginCheckVO(String admin_id, String password) {
		this.admin_id = admin_id;
		this.password = password;
	} // SCALoginCheckVO
	
	public String getAdmin_id() {
		return admin_id;
	}

	public String getPassword() {
		return password;
	}
	
} // class
