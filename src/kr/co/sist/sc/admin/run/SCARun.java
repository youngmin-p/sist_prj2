package kr.co.sist.sc.admin.run;

import kr.co.sist.sc.admin.view.SCALoginView;

public class SCARun {

//	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// 프로그램 중복 실행을 막는 코드
		// LoginController에서 MainView로 넘어갈 때 
		// 서버 소켓을 오픈하면 중복 실행을 막을 수 있을 듯
//		try {
//			new ServerSocket(3333);
//			new SCALoginView();
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, "프로그램 중복 실행으로 인해 종료됩니다.");
//			e.printStackTrace();
//		} // end catch
		
		new SCALoginView();
		
	} // main

} // class
