package kr.co.sist.cinema.admin.vo;

/**
 * 예매 관리 - 예매 현황 조회
 * @author owner
 */
public class SCABookListVO {
	private String member_id, book_number, payment_date;
	private int personnel, seat_num;
	
	public SCABookListVO(String member_id, String book_number, String payment_date, int personnel, int seat_num) {
		this.member_id = member_id;
		this.book_number = book_number;
		this.payment_date = payment_date;
		this.personnel = personnel;
		this.seat_num = seat_num;
	} // SCABookListVO

	public String getMember_id() {
		return member_id;
	}

	public String getBook_number() {
		return book_number;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public int getPersonnel() {
		return personnel;
	}

	public int getSeat_num() {
		return seat_num;
	}

	@Override
	public String toString() {
		return "SCABookListVO [member_id=" + member_id + ", book_number=" + book_number + ", payment_date="
				+ payment_date + ", personnel=" + personnel + ", seat_num=" + seat_num + "]";
	} // toString
	
} // class
