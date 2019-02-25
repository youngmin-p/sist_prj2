package kr.co.sist.sc.admin.nio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	protected ServerSocket scServer;
	
	private SCAFileServer() { }
	
	public static SCAFileServer getInstance() {
		if (sca_fs == null) {
			sca_fs = new SCAFileServer();
		} // end if
		
		return sca_fs;
	} // getInstance
	
	/**
	 * 서버 소켓 오픈 (3333번 포트)
	 */
	protected void open() throws IOException {
		if (scServer == null) {
			int port = 3333;
			
			scServer = new ServerSocket(port);
		} // end if
	} // open
	
	/**
	 * 서버 소켓 종료
	 * @throws IOException
	 */
	protected void close() throws IOException {
		// 서버 소켓이 오픈 상태라면
		if (scServer != null) {
			scServer.close();
		} // end if
	} // close
	
	/**
	 * admin.images 패키지 하위 폴더에 있는 이미지를 Helper Class로 전송하는 일
	 * @param revMsg "movie" or "snack"
	 * @return
	 */
	protected String[] sendImageList(String revMsg) {
		String[] fileList = null;
		
		String imgPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/" + revMsg + "/";
		
		File file = new File(imgPath);
		
		List<String> list = new ArrayList<String>();
		
		for (String fileName : file.list()) {
			if ((fileName.startsWith("l_" + revMsg + "_") && fileName.endsWith(".png")) 
					|| (fileName.startsWith("s_" + revMsg + "_") && fileName.endsWith(".png"))) {
				list.add(fileName);
			} // end if	
		} // end for
		
		fileList = new String[list.size()];
		
		list.toArray(fileList);
		
		return fileList;
	} // sendImageList
	
	/**
	 * 이미지를 클라이언트로 전송하는 일
	 * @param dos
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void sendFile(DataOutputStream dos, String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis = null;
		
		String imgPath = "";
		
		int fileLen = 0;
		int fileData = 0;
		
		byte[] readData = null;
		
		try {
			imgPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/" + fileName.split("_")[1] + "/";
			
			fis = new FileInputStream(imgPath + fileName);
			
			readData = new byte[512];
			
			while ((fileLen = fis.read(readData)) != -1) {
				fileData++;
			} // end while
			
			fis.close();
			
			// 파일 크기 전송
			dos.writeInt(fileData);
			dos.flush();
			
			// 파일 이름 전송
			dos.writeUTF(fileName);
			dos.flush();
			
			fis = new FileInputStream(imgPath + fileName);
			
			// 파일 데이터 전송
			while ((fileLen = fis.read(readData)) != -1) {
				dos.write(readData, 0, fileLen);
				dos.flush();
				fileData--;
			} // end while
		} finally {
			if (fis != null) { fis.close(); } // end if
		} // end finally
	} // sendFile
	
} // class
