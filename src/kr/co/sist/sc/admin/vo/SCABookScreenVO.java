package kr.co.sist.sc.admin.vo;

public class SCABookScreenVO {
	private String movie_start, screen_num;
	private int personnel;
	
	public SCABookScreenVO(String movie_start, String screen_num, int personnel) {
		this.movie_start = movie_start;
		this.screen_num = screen_num;
		this.personnel = personnel;
	} // SCABookScreenVO

	public String getMovie_start() {
		return movie_start;
	}

	public String getScreen_num() {
		return screen_num;
	}

	public int getPersonnel() {
		return personnel;
	}

	@Override
	public String toString() {
		return "SCABookScreenVO [movie_start=" + movie_start + ", screen_num=" + screen_num + ", personnel=" + personnel
				+ "]";
	} // toString
	
} // class
