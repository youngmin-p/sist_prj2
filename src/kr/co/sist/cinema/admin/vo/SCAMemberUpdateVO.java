package kr.co.sist.cinema.admin.vo;

/**
 * @author owner
 * 회원 관리 - 회원 정보 (정보 수정 버튼)
 */
public class SCAMemberUpdateVO {
	private String password, name, phone;

	public SCAMemberUpdateVO(String password, String name, String phone) {
		this.password = password;
		this.name = name;
		this.phone = phone;
	} // SCAMemberUpdateVO

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}
	
} // class
