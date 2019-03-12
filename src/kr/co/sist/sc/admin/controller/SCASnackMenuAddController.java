package kr.co.sist.sc.admin.controller;

import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.co.sist.sc.admin.model.SCASnackManageDAO;
import kr.co.sist.sc.admin.view.SCASnackManageView;
import kr.co.sist.sc.admin.view.SCASnackMenuAddView;
import kr.co.sist.sc.admin.vo.SCASnackMenuAddVO;
import kr.co.sist.sc.admin.vo.SCASnackMenuTableSelectVO;


public class SCASnackMenuAddController extends WindowAdapter implements ActionListener {
	
	private SCASnackManageView scasmv;
	private SCASnackMenuAddView scasmav;
	

	public SCASnackMenuAddController(SCASnackManageView scasmv, SCASnackMenuAddView scasmav) {
		this.scasmv = scasmv;
		this.scasmav = scasmav;
	} // SCASnackMenuAddController

	@Override
	public void actionPerformed(ActionEvent ae) {
		// 이미지 등록 버튼
		if(ae.getSource() == scasmav.getJbtSnackImg()) {
			addSnackImg();
		} // end if
		
		// 메뉴 등록 버튼
		if(ae.getSource() == scasmav.getJbtSnackInsert()) {
			addSnackMenu();
		} // end if
		
		// 닫기 버튼
		if(ae.getSource() == scasmav.getJbtClose()) {
			scasmav.dispose();
		} // end if
	} // actionPerformed
	
	private void addSnackImg() {
		FileDialog fdOpenImg = new FileDialog(scasmav, "스낵 이미지 등록", FileDialog.LOAD);
		fdOpenImg.setVisible(true);
		
		String path = fdOpenImg.getDirectory();
		String name = fdOpenImg.getFile();
		
		boolean flag = false;
		if (path != null) {
			String imgsFormat = "png";
			
			if(name.toLowerCase().endsWith(imgsFormat)){
				flag = true;
			} // end if
			
			if(flag) {
				String uploadImg = path + name;
				scasmav.getJlSnackImg().setIcon(new ImageIcon(uploadImg));
			} else {
				JOptionPane.showMessageDialog(scasmav, "등록가능한 이미지 형식은 png 입니다.");
			} // end else
		} // end if
		
	} // addSnackImg
	
	private void addSnackMenu() {
		File imageFile = new File(scasmav.getJlSnackImg().getIcon().toString());
		String snackName = scasmav.getJtfSnackName().getText();
		String snackPrice = scasmav.getJtfPrice().getText();
		String snackInfo = scasmav.getjtaSnackInfo().getText();
		
		if(imageFile.getName().endsWith("admin_snack_default_img(325x325).png")) {
			JOptionPane.showMessageDialog(scasmav, "이미지를 등록해주세요.");
			return;
		} // end if
		
		if(snackName.equals("")) {
			JOptionPane.showMessageDialog(scasmav, "스낵명은 필수로 입력해주세요.");
			scasmav.getJtfSnackName().requestFocus();
			return;
		} // end if
		
		if(snackPrice.equals("")) {
			JOptionPane.showMessageDialog(scasmav, "스낵의 가격은 필수로 입력해주세요.");
			scasmav.getJtfPrice().requestFocus();
			return;
		} // end if
		
		if(snackPrice.length() > 5) {
			JOptionPane.showMessageDialog(scasmav, "스낵의 가격은 최대 5자로 입력해주세요.");
			scasmav.getJtfPrice().requestFocus();
			return;
		} // end if
		
		int price = 0;
		try {
			price = Integer.parseInt(snackPrice);
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(scasmav, "스낵의 가격은 숫자만 입력해주세요.");
			scasmav.getJtfPrice().setText("");
			scasmav.getJtfPrice().requestFocus();
			return;
		} // end catch
		
		if(snackInfo.equals("")) {
			JOptionPane.showMessageDialog(scasmav, "스낵의 특장점은 필수로 입력해주세요.");
			scasmav.getJtfPrice().requestFocus();
			return;
		} // end if
		
		try {
			// 기존에 등록된 스낵 메뉴가 8개 미만인지?
			if(SCASnackManageDAO.getInstance().selectSnackMenuTable().size() != 8) {
				SCASnackMenuAddVO scasmavo = new SCASnackMenuAddVO(snackName, imageFile.getName(), snackInfo, price);
			
				try {
					// DB에 새로운 스낵메뉴를 추가
					SCASnackManageDAO.getInstance().insertSnackMenu(scasmavo);
					// 이미지를 지정 폴더에 업로드
					uploadSnackImg(imageFile);
					refreshSnackMenu();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} // end catch
				
				JOptionPane.showMessageDialog(scasmav, "새로운 스낵 메뉴가 추가되었습니다.");
				scasmav.dispose();
				
				
//				// 컴포넌트 초기화
//				scasmav.getJtfSnackName().setText("");
//				scasmav.getJtfPrice().setText("");
//				scasmav.getjtaSnackInfo().setText("");
//				scasmav.getJtfSnackName().requestFocus();
//				scasmav.getJlSnackImg().setIcon(new ImageIcon("C:/dev/workspace/cinema_prj/src/kr/co/sist/sc/admin/images/admin_snack_default_img(325x325).png"));
				
				
				
			} else {
				JOptionPane.showMessageDialog(scasmav, "더 이상 스낵 메뉴를 추가할 수 없습니다. (최대 메뉴 개수 8개)");
			} // end else
			
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scasmav, "DB상의 문제가 발생했습니다. 잠시후 다시 시도해주세요.");
			sqle.printStackTrace();
		} // end catch
		
		
	} // addSnackMenu
	
	private void uploadSnackImg(File imageFile) throws IOException{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			// 큰 스낵 이미지 업로드
			fis = new FileInputStream(imageFile);
			byte[] readData = new byte[512];
			
			int length = 0;
			String uploadPath = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack/";
			fos = new FileOutputStream(uploadPath+"l_snack_"+imageFile.getName());
			
			while((length = fis.read(readData)) != -1) {
				fos.write(readData, 0, length);
				fos.flush();
			} // end while
			
			fis.close();
			fos.close();
			
			// 작은 스낵 이미지 업로드
			fis = new FileInputStream(imageFile.getParent()+"/s_snack_"+imageFile.getName());
			
			length = 0;
			fos = new FileOutputStream(uploadPath+"s_snack_"+imageFile.getName());
			
			while( (length=fis.read(readData)) != -1 ) {
				fos.write(readData, 0, length);
				fos.flush();
			} // end while
			
		} finally {
			if( fis != null ) { fis.close(); } // end if
			if( fos != null ) { fos.close(); } // end if
		} // end finally
		
	} // uploadSnackImg
	
	private void refreshSnackMenu() {
		JButton[][] jbtSnack = scasmv.getJbtSnackImg();
		List<SCASnackMenuTableSelectVO> snackList = new ArrayList<SCASnackMenuTableSelectVO>();
		try {
			snackList = SCASnackManageDAO.getInstance().selectSnackMenuTable();
			
			
			int listLength = 0;
			String imgDir = "C:/dev/workspace/cinema_prj/src/kr/co/sist/sc/admin/images/";
			
			for(int i=0; i < jbtSnack.length; i++) {
				for(int j=0; j < jbtSnack[i].length; j++) {
					jbtSnack[i][j].setIcon(null);
					jbtSnack[i][j].setText("");
					listLength++;
				} // end for
			} // end for
			
			listLength = 0;
			for(int k=0; k < jbtSnack.length; k++) {
				for(int l=0; l < jbtSnack[k].length; l++) {
					if(listLength == snackList.size()) {
						return;
					} // end if
					jbtSnack[k][l].setIcon(new ImageIcon(imgDir+snackList.get(listLength).getSnackImg()));
					jbtSnack[k][l].setText(snackList.get(listLength).getSnackName());
					listLength++;
				} // end for
			} // end for
			
			listLength = 0;
			
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scasmav, "DB상의 문제가 발생하였습니다. 스낵관리 창을 닫고 다시 켜주세요.");
		} // end catch
	} // refreshSnackMenu
	
} // class
