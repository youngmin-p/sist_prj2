package kr.co.sist.sc.admin.nio;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * 1) 영화가 추가된 경우
 * 2) 스낵이 추가된 경우
 * 클라이언트 측에서 접속이 발생하면 ? 이미 접속한 클라이언트의 경우에는?
 * s_ : thumbnail image
 * l_ : original image
 * @author owner
 */
public class SCAFileServer {
	private static SCAFileServer sca_fs;
	protected ServerSocket scServer;
	
	private SCAFileServer() {
		openServer();
		
	} // SCAFileServer
	
	public static SCAFileServer getInstance() {
		if (sca_fs == null) {
			sca_fs = new SCAFileServer();
		} // end if
		
		return sca_fs;
	} // getInstance
	
	/**
	 * 서버 소켓 오픈 (3333번 PORT)
	 */
	private void openServer() {
		if (scServer == null) {
			try {
				int port = 3333;
				
				scServer = new ServerSocket(port);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} // end catch
		} // end if
	} // openServer
	
	/**
	 * images 패키지에 있는 이미지를 Helper Class로 전송하는 일
	 * @param revMsg "movie" or "snack"
	 * @return
	 */
	public String[] sendImageList(String revMsg) {
		String[] fileList = null;
		
		String imgPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/";
		
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
	
	/**
	 * 
	 */
	public void receiveMessage() {
		
	} // receiveMessage

//	public static void main(String[] args) {
//		SCAFileServer fs = new SCAFileServer();
//		
//		fs.sendImageList("movie");
//		
//	} // main
	
} // class
