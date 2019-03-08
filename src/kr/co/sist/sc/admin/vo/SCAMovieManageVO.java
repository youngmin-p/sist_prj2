package kr.co.sist.sc.admin.vo;

public class SCAMovieManageVO {

	private String movie_Code, movie_Img,movie_Title;

	public SCAMovieManageVO(String movie_Code, String movie_Img, String movie_Title) {
		super();
		this.movie_Code = movie_Code;
		this.movie_Img = movie_Img;
		this.movie_Title = movie_Title;
	}

	public String getMovie_Code() {
		return movie_Code;
	}

	public String getMovie_Img() {
		return movie_Img;
	}

	public String getMovie_Title() {
		return movie_Title;
	}

	@Override
	public String toString() {
		return "SCAMovieManageVO [movie_Code=" + movie_Code + ", movie_Img=" + movie_Img + ", movie_Title="
				+ movie_Title + "]";
	}



	
	

	
	
}
