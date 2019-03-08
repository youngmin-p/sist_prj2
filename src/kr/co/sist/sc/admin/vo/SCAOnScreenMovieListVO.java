package kr.co.sist.sc.admin.vo;

public class SCAOnScreenMovieListVO {
	private String movie_code,movie_title;
	private int runningtime;
	public SCAOnScreenMovieListVO(String movie_code, String movie_title, int runningtime) {
		super();
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.runningtime = runningtime;
	}
	public String getMovie_code() {
		return movie_code;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public int getRunningtime() {
		return runningtime;
	}
	@Override
	public String toString() {
		return "SCAOnScreenMovieListVO [movie_code=" + movie_code + ", movie_title=" + movie_title + ", runningtime="
				+ runningtime + "]";
	}

	
	

}
