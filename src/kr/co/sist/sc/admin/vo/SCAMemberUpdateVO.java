package kr.co.sist.sc.admin.vo;

public class SCAMemberUpdateVO {
	
	private String memberId, name, phone;

	public SCAMemberUpdateVO(String memberId, String name, String phone) {
		this.memberId = memberId;
		this.name = name;
		this.phone = phone;
	} // SCAMemberUpdateVO

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}
	
} // class
