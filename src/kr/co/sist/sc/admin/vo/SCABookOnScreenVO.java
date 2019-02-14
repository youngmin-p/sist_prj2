package kr.co.sist.sc.admin.vo;

/**
 * 현장 예매
 * 상영 중인 영화 정보를 조회
 * @author owner
 */
public class SCABookOnScreenVO {
	private String movie_code, movie_title, screen_num, screen_name, start_time, end_time;
	private int seat_remain, seat_count;
	
	public SCABookOnScreenVO(String movie_code, String movie_title, String screen_num, String screen_name,
			String start_time, String end_time, int seat_remain, int seat_count) {
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.screen_num = screen_num;
		this.screen_name = screen_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.seat_remain = seat_remain;
		this.seat_count = seat_count;
	} // SCABookOnScreenVO

	public String getMovie_code() {
		return movie_code;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public String getScreen_num() {
		return screen_num;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public int getSeat_remain() {
		return seat_remain;
	}

	public int getSeat_count() {
		return seat_count;
	}

	@Override
	public String toString() {
		return "SCABookOnScreenVO [movie_code=" + movie_code + ", movie_title=" + movie_title + ", screen_num="
				+ screen_num + ", screen_name=" + screen_name + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", seat_remain=" + seat_remain + ", seat_count=" + seat_count + "]";
	} // toString
	
} // class
