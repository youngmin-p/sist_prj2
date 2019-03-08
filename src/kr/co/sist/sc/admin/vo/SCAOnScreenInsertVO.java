package kr.co.sist.sc.admin.vo;

public class SCAOnScreenInsertVO {
	private String screen_num,	screen_date,start_time,end_time,movie_code,	screen_name;

	public SCAOnScreenInsertVO(String screen_num, String screen_date, String start_time, String end_time,
			String movie_code, String screen_name) {
		super();
		this.screen_num = screen_num;
		this.screen_date = screen_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.movie_code = movie_code;
		this.screen_name = screen_name;
	}

	public String getScreen_num() {
		return screen_num;
	}

	public String getScreen_date() {
		return screen_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public String getMovie_code() {
		return movie_code;
	}

	public String getScreen_name() {
		return screen_name;
	}

	@Override
	public String toString() {
		return "SCAOnScreenInsertVO [screen_num=" + screen_num + ", screen_date=" + screen_date + ", start_time="
				+ start_time + ", end_time=" + end_time + ", movie_code=" + movie_code + ", screen_name=" + screen_name
				+ "]";
	}

	
}
