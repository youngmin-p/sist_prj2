package kr.co.sist.sc.admin.vo;

public class SCAMemberSelectVO {

	private String member_id, name, birthDate;

	public SCAMemberSelectVO(String member_id, String name, String birthDate) {
		this.member_id = member_id;
		this.name = name;
		this.birthDate = birthDate;
	} // SCAMemberSelectVO

	public String getMember_id() {
		return member_id;
	}

	public String getName() {
		return name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "SCAMemberSelectVO [member_id=" + member_id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
} // class
