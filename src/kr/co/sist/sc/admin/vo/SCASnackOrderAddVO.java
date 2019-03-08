package kr.co.sist.sc.admin.vo;

public class SCASnackOrderAddVO {

	private String snackName;
	private int snackPrice, quan;
	
	public SCASnackOrderAddVO(String snackName, int snackPrice, int quan) {
		this.snackName = snackName;
		this.snackPrice = snackPrice;
		this.quan = quan;
	} // SCASnackOrderAddVO

	public String getSnackName() {
		return snackName;
	} // getSnackName

	public int getSnackPrice() {
		return snackPrice;
	} // getSnackPrice

	public int getQuan() {
		return quan;
	} // getQuan
	
	
} // class
