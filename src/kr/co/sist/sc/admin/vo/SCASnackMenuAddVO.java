package kr.co.sist.sc.admin.vo;

/**
 * @author owner
 * 스낵 관리 - 스낵 메뉴 추가 버튼
 */
public class SCASnackMenuAddVO {
	private String snack_name, snack_img, snack_info;
	private int snack_price;
	
	public SCASnackMenuAddVO(String snack_name, String snack_img, String snack_info, int snack_price) {
		this.snack_name = snack_name;
		this.snack_img = snack_img;
		this.snack_info = snack_info;
		this.snack_price = snack_price;
	} // SCASnackMenuAddVO

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

	@Override
	public String toString() {
		return "SCASnackMenuAddVO [snack_name=" + snack_name + ", snack_img=" + snack_img + ", snack_info=" + snack_info
				+ ", snack_price=" + snack_price + "]";
	}
	
} // class
