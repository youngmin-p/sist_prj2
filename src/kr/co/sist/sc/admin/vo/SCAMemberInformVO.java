package kr.co.sist.sc.admin.vo;

public class SCAMemberInformVO {
	String member_id, password, name, birthdate, phone, membership, input_date;
	int hold_point, acc_point;
	public SCAMemberInformVO(String member_id, String password, String name, String birthdate, String phone,
			String membership, String input_date, int hold_point, int acc_point) {
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.phone = phone;
		this.membership = membership;
		this.input_date = input_date;
		this.hold_point = hold_point;
		this.acc_point = acc_point;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public String getPhone() {
		return phone;
	}
	public String getMembership() {
		return membership;
	}
	public String getInput_date() {
		return input_date;
	}
	public int getHold_point() {
		return hold_point;
	}
	public int getAcc_point() {
		return acc_point;
	}
	
} // class
