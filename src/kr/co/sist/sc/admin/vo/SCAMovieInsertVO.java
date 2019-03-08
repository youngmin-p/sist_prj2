package kr.co.sist.sc.admin.vo;

public class SCAMovieInsertVO {
	private String 	movie_title,movie_img,genre,country,director,movie_grade,playdate,synopsis,actor,admin_id;
	private int runningtime;
		// 다시 생성	
	public SCAMovieInsertVO(String movie_title, String movie_img, String genre, String country, String director,
			String movie_grade, String playdate, String synopsis, String actor, String admin_id, int runningtime) {
		super();
		this.movie_title = movie_title;
		this.movie_img = movie_img;
		this.genre = genre;
		this.country = country;
		this.director = director;
		this.movie_grade = movie_grade;
		this.playdate = playdate;
		this.synopsis = synopsis;
		this.actor = actor;
		this.admin_id = admin_id;
		this.runningtime = runningtime;
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
	public String getMovie_grade() {
		return movie_grade;
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
	public String getAdmin_id() {
		return admin_id;
	}
	public int getRunningtime() {
		return runningtime;
	}
	@Override
	public String toString() {
		return "SCAMovieRegisterVO [movie_title=" + movie_title + ", movie_img=" + movie_img + ", genre=" + genre
				+ ", country=" + country + ", director=" + director + ", movie_grade=" + movie_grade + ", playdate="
				+ playdate + ", synopsis=" + synopsis + ", actor=" + actor + ", admin_id=" + admin_id + ", runningtime="
				+ runningtime + "]";
	}

	
	
	
	
	
}
