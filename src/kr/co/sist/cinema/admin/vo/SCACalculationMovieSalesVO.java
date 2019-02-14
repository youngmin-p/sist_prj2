package kr.co.sist.cinema.admin.vo;

/**
 * @author owner
 * 정산 관리 - 영화 상영 수익 테이블
 * 영화 코드, 영화 제목, 인원 수, 총 가격
 */
public class SCACalculationMovieSalesVO {
	private String movie_code, movie_title;
	private int personnel, total_price;
	
	public SCACalculationMovieSalesVO(String movie_code, String movie_title, int personnel, int total_price) {
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.personnel = personnel;
		this.total_price = total_price;
	} // SCACalculationMovieSalesVO

	public String getMovie_code() {
		return movie_code;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public int getPersonnel() {
		return personnel;
	}

	public int getTotal_price() {
		return total_price;
	}
	
} // class
