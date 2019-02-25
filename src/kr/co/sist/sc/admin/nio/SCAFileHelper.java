package kr.co.sist.sc.admin.nio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 스트림을 통제하는 중간 관리자
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
			
			int cnt = 0;
			
			String revMsg = "";
			
			String[] serverFileNames = null;
			String[] clientFileNames = null;
			
			List<String> checkFileNames = null;
			
			while (true) {
				// 클라이언트 접속 대기
				scClient = SCAFileServer.getInstance().scServer.accept();
				
				// 1) 클라이언트 접속이 존재할 때
				if (scClient != null) {
					revMsg = this.revMsg;
					
					if (revMsg.equals("")) {
						return;
					} // end if
					
					// 2) addEvent가 발생했을 때 (영화/스낵 추가)
					if (!revMsg.equals("")) {
						// 1. 클라이언트 측에 이미지 목록을 요청한다.
						dos = new DataOutputStream(scClient.getOutputStream());
						
						dos.writeUTF(revMsg);
						dos.flush();
						
						// 2. 클라이언트 측에서 송신한 데이터를 획득한다.
						dis = new DataInputStream(scClient.getInputStream());
						
						// 전송받은 클라이언트 파일의 개수
						cnt = dis.readInt();
						
						// 클라이언트 파일의 개수만큼 데이터를 전송받는다.
						clientFileNames = new String[cnt];
						
						for (int i = 0; i < cnt; i++) {
							clientFileNames[i] = dis.readUTF();
						} // end for
						
						// admin.images 패키지에 존재하는 영화/스낵 이미지를 검색해서 
						serverFileNames = SCAFileServer.getInstance().sendImageList(revMsg);
						
						// 파일 서버와 클라이언트가 보유하고 있는 이미지 목록을 비교하여 
						checkFileNames = new ArrayList<String>();
						
						for (String sfn : serverFileNames) {
							checkFileNames.add(sfn);
						} // end for
						
						for (String cfn : clientFileNames) {
							checkFileNames.remove(cfn);
						} // end for
						
						// 클라이언트가 보유하고 있지 않은 이미지를 전송해준다.
						dos = new DataOutputStream(scClient.getOutputStream());
						
						// 클라이언트로 보낼 파일의 개수를 전송한다.
						dos.writeInt(checkFileNames.size());
						dos.flush();
						
						for (String fileName : checkFileNames) {
							SCAFileServer.getInstance().sendFile(dos, fileName);
							sleep(100);
						} // end for
						
						revMsg = "";
					} // end if
					
					// 1) 영화가 추가된 경우
					// scamic에서 Helper의 addEvent method 호출
					
					// 1-1)
					// 서버에서 영화 등록, 스낵 추가 이벤트가 발생하면 
					// FileServer에서 클라이언트 측으로 sendMsg를 전송
					// sendMsg를 받은 클라이언트 측은 서버 측으로 파일 목록을 전송
					
					// 2) 스낵이 추가된 경우
					// scasic에서 Helper의 addEvent method 호출
					
					// 2-1)
					// 1-1 상동
					
				} // end if
			} // end while
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
	} // run
	
	/**
	 * 영화/스낵 추가 이벤트가 발생했을 때
	 * @param revMsg
	 */
	public void addEvent(String revMsg) {
		this.revMsg = revMsg;
	} // addEvent
	
	/**
	 * 파일 서버 오픈
	 * @throws IOException
	 */
	public void openServer() throws IOException {
		SCAFileServer.getInstance().open();
	} // openServer
	
	/**
	 * 파일 서버 종료
	 * @throws IOException
	 */
	public void closeServer() throws IOException {
		SCAFileServer.getInstance().close();
	} // closeServer
	
} // class
