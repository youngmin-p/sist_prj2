package kr.co.sist.sc.admin.controller;

import java.awt.Color;
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
		if(ae.getSource() == scasmav.getJbtSnackImg()) {
			addSnackImg();
		} // end if
		
		if(ae.getSource() == scasmav.getJbtSnackInsert()) {
			addSnackMenu();
		} // end if
		
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
		
		if(imageFile.getName().endsWith("jl_no_snack_image(325x325).png")) {
			JOptionPane.showMessageDialog(scasmav, "이미지를 등록해주세요.");
			return;
		} // end if
		
		if(snackName.equals("")) {
			JOptionPane.showMessageDialog(scasmav, "스낵명은 필수로 입력해주세요.");
			scasmav.getJtfSnackName().requestFocus();
			return;
		} // end if
		
		if(snackName.contains(" ")) {
			JOptionPane.showMessageDialog(scasmav, "스낵명에 공백이 들어갈 수 없습니다.");
			scasmav.getJtfSnackName().setText("");
			scasmav.getJtfSnackName().requestFocus();
			return;
		} // end if
		
		if(snackPrice.equals("")) {
			JOptionPane.showMessageDialog(scasmav, "스낵의 가격은 필수로 입력해주세요.");
			scasmav.getJtfPrice().requestFocus();
			return;
		} // end if
		
		if(snackPrice.contains(" ")) {
			JOptionPane.showMessageDialog(scasmav, "스낵의 가격에 공백이 들어갈 수 없습니다.");
			scasmav.getJtfPrice().setText("");
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
				SCASnackMenuAddVO scasmavo = new SCASnackMenuAddVO(snackName, imageFile.getName(), snackInfo, price);
			
				try {
					SCASnackManageDAO.getInstance().insertSnackMenu(scasmavo);
					uploadSnackImg(imageFile);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} // end catch
				
				refreshSnackMenu();
				JOptionPane.showMessageDialog(scasmav, "새로운 스낵 메뉴가 추가되었습니다.");
				scasmav.dispose();
			
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scasmav, "DB상의 문제가 발생했습니다. 잠시후 다시 시도해주세요.");
			sqle.printStackTrace();
		} // end catch
		
	} // addSnackMenu
	
	private void uploadSnackImg(File imageFile) throws IOException{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
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
			String imgDir = "C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack/";
			
			for(int i=0; i < jbtSnack.length; i++) {
				for(int j=0; j < jbtSnack[i].length; j++) {
					jbtSnack[i][j].setIcon(new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jl_no_snack_image(187x162).png"));
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
					jbtSnack[k][l].setIcon(new ImageIcon(imgDir+"s_snack_"+snackList.get(listLength).getSnackImg()));
					jbtSnack[k][l].setText(snackList.get(listLength).getSnackName());
					jbtSnack[k][l].setHorizontalTextPosition(JButton.CENTER);
					jbtSnack[k][l].setVerticalTextPosition(JButton.CENTER);
					jbtSnack[k][l].setForeground(new Color(255, 255, 255, 0));
					listLength++;
				} // end for
			} // end for
			listLength = 0;
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(scasmav, "DB상의 문제가 발생하였습니다. 스낵관리 창을 닫고 다시 켜주세요.");
		} // end catch
	} // refreshSnackMenu
	
} // class
