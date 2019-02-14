package kr.co.sist.cinema.admin.vo;

public class SCAMovieDetailsVO {
	private String id, passwd, name;

	public SCAMovieDetailsVO(String id, String passwd, String name) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
	} // SCAMovieDetailsVO

	public String getId() {
		return id;
	}

	public String getPasswd() {
		return passwd;
	}
	
	public String getName() {
		return name;
	}
	
} // class
