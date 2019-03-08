package kr.co.sist.sc.admin.vo;

public class SCASnackMenuSelectVO {

	private String snackName, snackImg, snackInfo;
	private int snackPrice;

	public SCASnackMenuSelectVO(String snackName, String snackImg, String snackInfo, int snackPrice) {
		this.snackName = snackName;
		this.snackImg = snackImg;
		this.snackInfo = snackInfo;
		this.snackPrice = snackPrice;
	} // SCASnackMenuSelectVO

	public String getSnackName() {
		return snackName;
	} // getSnackName

	public String getSnackImg() {
		return snackImg;
	} // getSnackImg

	public String getSnackInfo() {
		return snackInfo;
	} // getSnackInfo

	public int getSnackPrice() {
		return snackPrice;
	} // getSnackPrice
	
} // class
