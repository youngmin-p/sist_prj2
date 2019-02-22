package kr.co.sist.sc.admin.nio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 스트림을 관리하는 중간 관리자
 * @author owner
 */
public class SCAFileHelper extends Thread {
	private static SCAFileHelper sca_fh;
	private String revMsg;
	
	private SCAFileHelper() {
		this.revMsg = "";
		
	} // SCAFileHelper
	
	public static SCAFileHelper getInstance() {
		if (sca_fh == null) {
			sca_fh = new SCAFileHelper();
		} // end if
		
		return sca_fh;
	} // getInstance
	
	@Override
	public void run() {
		try {
			Socket scClient = null;
			
			DataOutputStream dos = null;
			DataInputStream dis = null;
			
			File file = null;
			FileOutputStream fos = null;
			
			String revMsg = "";
			
			while (true) {
				// 클라이언트 접속 대기
				System.out.println("바인딩 전");
				scClient = SCAFileServer.getInstance().scServer.accept();
				System.out.println("바인딩 후");
				
				// 1) 클라이언트 접속이 존재할 때
				if (scClient != null) {
					revMsg = this.revMsg;
					
					// 2) addEvent가 발생했을 때 (영화/스낵 추가)
					if (!revMsg.equals("")) {
						System.out.println("접속자 IP : " + scClient.getInetAddress().getHostAddress());
						
						// 1. 클라이언트 측에 이미지 목록을 요청한다.
						dos = new DataOutputStream(scClient.getOutputStream());
						
						dos.writeUTF(revMsg);
						dos.flush();
						
						// 2. 클라이언트 측에서 응답한 데이터를 획득한다.
						dis = new DataInputStream(scClient.getInputStream());
						
						
						
						
						
						// admin.images 패키지에 존재하는 영화/스낵 이미지를 검색해서 
						String[] fileNames = SCAFileServer.getInstance().sendImageList(revMsg);
						
						System.out.println("파일명 : " + fileNames.toString() + " / 파일 길이 : " + fileNames.length);
						
						// 
						
						// 코드 기술
						
						revMsg = "";
					} // end if
					
					if (revMsg.equals("")) {
						System.out.println("revMsg가 존재하지 않음.");
					}
					
//					for (String imgName : fileNames) {
//						System.out.println(imgName + " / " + imgName.length());
//					} // end for
					
					// 클라이언트에 해당 이미지 목록을 전송
					
					// 1) 영화가 추가된 경우
					// scamic에서 Helper의 
					
					// 서버에서 먼저 보낼 것인지, 클라이언트에서 먼저 받을 것인지.
					
					// 1-1)
					// 서버에서 영화 등록, 스낵 추가 이벤트가 발생하면 
					// FileServer에서 클라이언트 측으로 sendMsg를 전송
					// sendMsg를 받은 클라이언트 측은 서버 측으로 파일 목록을 전송
					
					// 1-2)
					// 클라이언트에서 조회를 수행하면 (즉, query가 발생하면)
					// FileClient에서 서버 측으로 sendMsg를 전송
					
					// 2) 스낵이 추가된 경우

				} // end if
				
				// 5초 대기
				sleep(5000);
			} // end while
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	} // run
	
	/**
	 * 영화/스낵 추가 이벤트가 발생했을 때
	 * @param revMsg
	 */
	public void addEvent(String revMsg) {
		this.revMsg = revMsg;
	} // addEvent
	
	/**
	 * Unit Test
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SCAFileServer.getInstance().openServer();
			SCAFileHelper.getInstance().start();
			
			SCAFileHelper.getInstance().addEvent("movie");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
	} // main
	
} // class
