package kr.co.sist.sc.admin.vo;

public class SCAOnScreenMovieListVO2 {
	private String movie_code,movie_title;
	public SCAOnScreenMovieListVO2(String movie_code, String movie_title) {
		this.movie_code = movie_code;
		this.movie_title = movie_title;
	}
	public String getMovie_code() {
		return movie_code;
	}
	public String getMovie_title() {
		return movie_title;
	}
	@Override
	public String toString() {
		return "SCAOnScreenMovieListVO2 [movie_code=" + movie_code + ", movie_title=" + movie_title + "]";
	}

	
	

}
