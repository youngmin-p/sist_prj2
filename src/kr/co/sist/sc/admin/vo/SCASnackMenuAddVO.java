package kr.co.sist.sc.admin.vo;

public class SCASnackMenuAddVO {

	private String snack_name, snack_img, snack_info;
	private int snack_price;

	public SCASnackMenuAddVO(String snack_name, String snack_img, String snack_info, int snack_price) {
		this.snack_name = snack_name;
		this.snack_img = snack_img;
		this.snack_info = snack_info;
		this.snack_price = snack_price;
	}

	public String getSnack_name() {
		return snack_name;
	}

	public String getSnack_img() {
		return snack_img;
	}

	public String getSnack_info() {
		return snack_info;
	}

	public int getSnack_price() {
		return snack_price;
	}
	
} // class
