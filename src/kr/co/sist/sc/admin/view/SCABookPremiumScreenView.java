package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.sc.admin.controller.SCABookPremiumScreenController;
import kr.co.sist.sc.admin.vo.SCABookScreenVO;

@SuppressWarnings("serial")
public class SCABookPremiumScreenView extends JDialog {
	private JButton[][] jbtSeat;
	private JButton jbtSelect, jbtClose;
	
	public SCABookPremiumScreenView(SCABookManageView scabmv, SCABookScreenVO scabs_vo) {
		super(scabmv, "���� ���� - �����̾� �¼� ����", true);
		
		this.setTitle(this.getTitle().concat(
				" [ " + scabmv.getJtabOnScreenList().getValueAt(scabmv.getJtabOnScreenList().getSelectedRow(), 2) + 
				" / " + scabmv.getJcbPersonnel().getSelectedItem() + "�� ]"));
		
		// seat 77X49
		// need editable
		jbtSeat = new JButton[2][5];
		
		int x = 33, y = 240, seat_num = 0;
		
		for (int i = 0; i < jbtSeat.length; i++) {
			for (int j = 0; j < jbtSeat[0].length; j++) {
				jbtSeat[i][j] = new JButton((seat_num++ + 1) + "", new ImageIcon(
						"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_p_seat_selectable(77x49).png"));
				jbtSeat[i][j].setFont(new Font("��������", Font.BOLD, 20));
				jbtSeat[i][j].setForeground(new Color(0, 134, 255));
				jbtSeat[i][j].setHorizontalTextPosition(JButton.CENTER);
				jbtSeat[i][j].setVerticalTextPosition(JButton.CENTER);
				jbtSeat[i][j].setBorderPainted(false);
				jbtSeat[i][j].setContentAreaFilled(false);
				jbtSeat[i][j].setBounds(x, y, 77, 49);
				
				if (j < 2) {
					x += 92;
					y += 25;
				} // end if
				
				if (j > 1) {
					x += 92;
					y -= 25;
				} // end if
				
				add(jbtSeat[i][j]);
			} // end for
			
			x = 33;
			y += 99;
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
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/imageicon_premium_screen(440x85).png"));
		jlblTitle.setBounds(35, 35, 440, 85);
		
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
		SCABookPremiumScreenController scabpsc = new SCABookPremiumScreenController(this, scabs_vo);
		
		addWindowListener(scabpsc);
		
		jbtSelect.addActionListener(scabpsc);
		jbtClose.addActionListener(scabpsc);
		
		for (int i = 0; i < jbtSeat.length; i++) {
			for (int j = 0; j < jbtSeat[0].length; j++) {
				jbtSeat[i][j].addActionListener(scabpsc);
			} // end for
		} // end for
		
		// size 520X600
		setSize(520, 620);
		setLocationRelativeTo(scabmv);
		setResizable(false);
		setVisible(true);
		
	} // SCABookPremiumScreenView

	public JButton[][] getJbtSeat() {
		return jbtSeat;
	}

	public JButton getJbtSelect() {
		return jbtSelect;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}
	
} // class
