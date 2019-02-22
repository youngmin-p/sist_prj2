package kr.co.sist.sc.admin.nio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 서버 측의 요청을 받으면 클라이언트가 보유하고 있는 이미지 목록을 Helper Class로 전송하고, 
 * 서버 측 보유 이미지와 비교하여 보유하고 있지 않은 이미지를 전송받는 일
 * 실행 위치 : 로그인 성공 시 (?)
 * @author owner
 */
public class SCUFileClient {
	private static SCUFileClient scu_fc;
	
	private SCUFileClient() { } // SCUFileClient
	
	public static SCUFileClient getInstance() {
		if (scu_fc == null) {
			scu_fc = new SCUFileClient();
		} // end if
		
		return scu_fc;
	} // getInstance
	
	/**
	 * user.images 패키지에 있는 이미지를 Helper Class로 전송하는 일
	 * @param revMsg
	 * @return
	 */
	public String[] sendImageList(String revMsg) {
		String[] fileList = null;
		
		String imgPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/user/images/" + revMsg + "/";
		
		File file = new File(imgPath);
		
		List<String> list = new ArrayList<String>();
		
		for (String fileName : file.list()) {
			if (fileName.startsWith("l_" + revMsg + "_") && fileName.endsWith(".png")) {
				list.add(fileName);
			} // end if	
		} // end for
		
		fileList = new String[list.size()];
		
		list.toArray(fileList);
		
		return fileList;
	} // sendImageList
	
//	public void requestImageList() { }
	
	/**
	 * Helper Class로부터 이미지 목록 전송 요청을 받으면, 
	 * 이미지 목록을 Helper로 전송하고 존재하지 않는 이미지를 다운로드한다.
	 * @throws IOException
	 */
	public void connectToServer() throws IOException {
		Socket scClient = null;
		
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		FileOutputStream fos = null;
		
		try {
			String address = "211.63.89.132";
			int port = 3333;
			
			scClient = new Socket(address, port);
			
			// Helper로부터 서버에서 영화/스낵이 추가되었다는 메시지를 받고, 
			dis = new DataInputStream(scClient.getInputStream());
			
			String revMsg = dis.readUTF();
			
			if (!revMsg.equals("")) {
				// 이미지 목록을 검색하여 
				String[] imgList = SCUFileClient.getInstance().sendImageList(revMsg);
				
				// Helper로 전송한 후 
				dos = new DataOutputStream(scClient.getOutputStream());
				dos.writeInt(imgList.length);
				dos.flush();
				
				System.out.println(imgList.length);
				
				for (int i = 0; i < imgList.length; i++) {
					dos.writeUTF(imgList[i]);
					dos.flush();
					
					System.out.println(imgList[i]);
				} // end for
				
				// Helper에서 보내오는 이미지 목록을 저장한다.
				dis = new DataInputStream(scClient.getInputStream());
				
				
				
			} // end if
		} finally {
			
			if (scClient != null) { scClient.close(); } // end if
		} // end finally
	} // connectToServer
	
	/**
	 * Unit Test
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SCUFileClient.getInstance().connectToServer();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} // end catch
		
	} // main
	
} // class
