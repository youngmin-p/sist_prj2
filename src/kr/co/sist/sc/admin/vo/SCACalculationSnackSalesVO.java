package kr.co.sist.sc.admin.vo;

/**
 * @author owner
 * 정산 관리 - 스낵 판매 수익 테이블
 * 스낵명, 수량, 총 가격
 */
public class SCACalculationSnackSalesVO {
	private String snack_name;
	private int quan, total_price;
	
	public SCACalculationSnackSalesVO(String snack_name, int quan, int total_price) {
		this.snack_name = snack_name;
		this.quan = quan;
		this.total_price = total_price;
	} // SCACalculationSnackSalesVO

	public String getSnack_name() {
		return snack_name;
	}

	public int getQuan() {
		return quan;
	}

	public int getTotal_price() {
		return total_price;
	}
	
} // class
