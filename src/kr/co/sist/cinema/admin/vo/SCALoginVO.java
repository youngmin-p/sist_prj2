package kr.co.sist.cinema.admin.vo;

public class SCALoginVO {
	private String admin_id, name;

	public SCALoginVO(String admin_id, String name) {
		this.admin_id = admin_id;
		this.name = name;
	} // SCALoginVO
	
	public String getAdmin_id() {
		return admin_id;
	}

	public String getName() {
		return name;
	}
	
} // class
