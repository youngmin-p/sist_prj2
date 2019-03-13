package kr.co.sist.sc.admin.vo;

/**
 *
 * @author owner
 */
public class SCAOnscreenSelectiveVO {
	private String screen_num,movie_code,movie_img,movie_title,screen_name,start_time,end_time,screen_date;

	public SCAOnscreenSelectiveVO(String screen_num, String movie_code, String movie_img, String movie_title, String screen_name,
			String start_time, String end_time, String screen_date) {
		super();
		this.screen_num = screen_num;
		this.movie_code = movie_code;
		this.movie_img = movie_img;
		this.movie_title = movie_title;
		this.screen_name = screen_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.screen_date = screen_date;
	}

	public String getScreen_num() {
		return screen_num;
	}

	public String getMovie_code() {
		return movie_code;
	}

	public String getMovie_img() {
		return movie_img;
	}

	public String getMovie_title() {
		return movie_title;
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

	public String getScreen_date() {
		return screen_date;
	}

	@Override
	public String toString() {
		return "SelectiveVO [screen_num=" + screen_num + ", movie_code=" + movie_code + ", movie_img=" + movie_img
				+ ", movie_title=" + movie_title + ", screen_name=" + screen_name + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", screen_date=" + screen_date + "]";
	}

	
	
}
