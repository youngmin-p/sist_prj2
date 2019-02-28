package kr.co.sist.sc.admin.vo;

/**
 * 정산 관리 - 영화 상영 수익 테이블
 * 영화 코드, 영화 제목, 인원 수, 총 가격
 * @author owner
 */
public class SCACalculationMovieSalesVO {
	private String movie_code, movie_title, total_price;
	private int personnel;
	
	public SCACalculationMovieSalesVO(String movie_code, String movie_title, String total_price, int personnel) {
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.total_price = total_price;
		this.personnel = personnel;
	} // SCACalculationMovieSalesVO

	public String getMovie_code() {
		return movie_code;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public String getTotal_price() {
		return total_price;
	}

	public int getPersonnel() {
		return personnel;
	}

	@Override
	public String toString() {
		return "SCACalculationMovieSalesVO [movie_code=" + movie_code + ", movie_title=" + movie_title
				+ ", total_price=" + total_price + ", personnel=" + personnel + "]";
	} // toString
	
} // class
