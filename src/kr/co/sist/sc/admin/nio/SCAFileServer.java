package kr.co.sist.sc.admin.nio;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * 파일 서버
 * @author owner
 */
public class SCAFileServer {
	private static SCAFileServer sca_fs;
	public ServerSocket scServer;
	
	private SCAFileServer() { }
	
	public static SCAFileServer getInstance() {
		if (sca_fs == null) {
			sca_fs = new SCAFileServer();
		} // end if
		
		return sca_fs;
	} // getInstance
	
	/**
	 * 서버 소켓의 오픈 상태를 확인하는 일 (현재 필요 X)
	 * @return
	 */
//	public boolean checkPortState() {
//		boolean flag = false;
//		
//		// 3333번 포트가 open 상태일 때
//		if (!scServer.isClosed()) {
//			// 서버 소켓이 닫혀있지 않다면
//			flag = true;
//		} // end if
//		
//		return flag;
//	} // checkPortState
	
	/**
	 * 서버 소켓 오픈 (3333번 포트)
	 */
	public void openServer() throws IOException {
		if (scServer == null) {
			int port = 3333;
			
			scServer = new ServerSocket(port);
		} // end if
	} // openServer
	
	/**
	 * admin.images 패키지에 있는 이미지를 Helper Class로 전송하는 일
	 * @param revMsg "movie" or "snack"
	 * @return
	 */
	public String[] sendImageList(String revMsg) {
		String[] fileList = null;
		
		String imgPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/" + revMsg + "/";
		
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

//	public static void main(String[] args) {
//		SCAFileServer fs = new SCAFileServer();
//		
//		fs.sendImageList("movie");
//		
//	} // main
	
} // class
