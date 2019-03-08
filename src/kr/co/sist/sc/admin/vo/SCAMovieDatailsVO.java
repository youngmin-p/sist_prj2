package kr.co.sist.sc.admin.vo;

/**
 * 영화 디테일
 * @author owner
 */
public class SCAMovieDatailsVO {
	
	private String 	movie_code,movie_title,movie_img,genre,country,director,grade,playdate,synopsis,actor;
	private int runningtime;
	public SCAMovieDatailsVO(String movie_code, String movie_title, String movie_img, String genre,
			String country, String director, String grade, String playdate, String synopsis, String actor,
			int runningtime) {
		super();
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.movie_img = movie_img;
		this.genre = genre;
		this.country = country;
		this.director = director;
		this.grade = grade;
		this.playdate = playdate;
		this.synopsis = synopsis;
		this.actor = actor;
		this.runningtime = runningtime;
	}
	public String getMovie_code() {
		return movie_code;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public String getMovie_img() {
		return movie_img;
	}
	public String getGenre() {
		return genre;
	}
	public String getCountry() {
		return country;
	}
	public String getDirector() {
		return director;
	}
	public String getgrade() {
		return grade;
	}
	public String getPlaydate() {
		return playdate;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public String getActor() {
		return actor;
	}
	public int getRunningtime() {
		return runningtime;
	}
	@Override
	public String toString() {
		return "SsangyongCinemaDetailVO [movie_code=" + movie_code + ", movie_title=" + movie_title + ", movie_img="
				+ movie_img + ", genre=" + genre + ", country=" + country + ", director=" + director + ", grade="
				+ grade + ", playdate=" + playdate + ", synopsis=" + synopsis + ", actor=" + actor
				+ ", runningtime=" + runningtime + "]";
	}
	
	
	
	

	
	
	
	
	

}
