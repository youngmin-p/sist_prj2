package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.sc.admin.controller.SCABookStandardScreenController;
import kr.co.sist.sc.admin.vo.SCABookOnScreenVO;

/**
 * ¿¹¸Å °ü¸®
 * ½ºÅÄ´Ùµå ÁÂ¼® ½ºÅ©¸°
 * @author owner
 */
@SuppressWarnings("serial")
public class SCABookStandardScreenView extends JDialog {
	private JButton[][] jbtSeat;
	private JButton jbtSelect, jbtClose;
	private SCABookOnScreenVO scabos_vo;
	
	public SCABookStandardScreenView(SCABookManageView scabmv, SCABookOnScreenVO scabos_vo) {
		super(scabmv, "¿¹¸Å °ü¸® - ½ºÅÄ´Ùµå ÁÂ¼® ¼±ÅÃ", true);
		
		this.scabos_vo = scabos_vo;
		
		// seat 67X61
		jbtSeat = new JButton[4][5];
		
		int x = 77, y = 154, seat_num = 0;
		
		for (int i = 0; i < jbtSeat.length; i++) {
			for (int j = 0; j < jbtSeat[0].length; j++) {
				jbtSeat[i][j] = new JButton((seat_num++ + 1) + "", new ImageIcon(
						"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_s_seat_selectable(67x61).png"));
				jbtSeat[i][j].setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
				jbtSeat[i][j].setForeground(new Color(0, 134, 255));
				jbtSeat[i][j].setHorizontalTextPosition(JButton.CENTER);
				jbtSeat[i][j].setVerticalTextPosition(JButton.CENTER);
				jbtSeat[i][j].setBorderPainted(false);
				jbtSeat[i][j].setContentAreaFilled(false);
				jbtSeat[i][j].setBounds(x, y, 67, 61);
				x += 72;
				
				add(jbtSeat[i][j]);
			} // end for
			
			x = 77;
			y += 66;
		} // end for
		
		jbtSelect = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_book(125x40).png"));
		jbtSelect.setFocusable(false);
		jbtSelect.setBorderPainted(false);
		jbtSelect.setContentAreaFilled(false);
		jbtSelect.setBounds(130, 470, 125, 40);
		
		jbtClose = new JButton(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_cancel(125x40).png"));
		jbtClose.setFocusable(false);
		jbtClose.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBounds(265, 470, 125, 40);
		
		// bg
		JLabel jlblTitle = new JLabel(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/imageicon_standard_screen(440x65).png"));
		jlblTitle.setBounds(35, 35, 440, 65);
		
		JLabel jlblBackground = new JLabel(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/book_management_3-2_stage_bg(520x600).png"));
		jlblBackground.setLayout(null);
		jlblBackground.setBounds(0, 0, 520, 600);
		
		jlblBackground.add(jlblTitle);
		jlblBackground.add(jbtSelect);
		jlblBackground.add(jbtClose);
		
		// jp
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.LIGHT_GRAY);
		jp.setBounds(0, 0, 520, 620);
		
		jp.add(jlblBackground);
		
		add(jp);
		
		// action
		SCABookStandardScreenController scabssc = new SCABookStandardScreenController(this);
		
		addWindowListener(scabssc);
		
		jbtSelect.addActionListener(scabssc);
		jbtClose.addActionListener(scabssc);
		
		for (int i = 0; i < jbtSeat.length; i++) {
			for (int j = 0; j < jbtSeat[0].length; j++) {
				jbtSeat[i][j].addActionListener(scabssc);
			} // end for
		} // end for
		
		// size 520X600
		setSize(520, 620);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCABookStandardScreenView

	public JButton[][] getJbtSeat() {
		return jbtSeat;
	}

	public JButton getJbtSelect() {
		return jbtSelect;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public SCABookOnScreenVO getScabos_vo() {
		return scabos_vo;
	}
	
} // class
