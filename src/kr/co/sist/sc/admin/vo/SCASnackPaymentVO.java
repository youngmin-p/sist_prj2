package kr.co.sist.sc.admin.vo;

public class SCASnackPaymentVO {
	private String snackName;
	private int quan;
	
	public SCASnackPaymentVO(String snackName, int quan) {
		this.snackName = snackName;
		this.quan = quan;
	} // SCASnackPaymentVO

	public String getSnackName() {
		return snackName;
	} // getSnackName

	public int getQuan() {
		return quan;
	} // getQuan
	
} // class
