package kr.co.sist.sc.admin.vo;

import java.util.List;

public class SCABookSeatVO {
	private String book_number;
	private List<Integer> seat_num;
	
	public SCABookSeatVO(String book_number, List<Integer> seat_num) {
		this.book_number = book_number;
		this.seat_num = seat_num;
	} // SCABookSeatVO

	public String getBook_number() {
		return book_number;
	}

	public List<Integer> getSeat_num() {
		return seat_num;
	}

	@Override
	public String toString() {
		return "SCABookSeatVO [book_number=" + book_number + ", seat_num=" + seat_num + "]";
	} // toString
	
} // class
