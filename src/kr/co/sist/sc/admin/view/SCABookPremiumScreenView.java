package kr.co.sist.sc.admin.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.sc.admin.controller.SCABookPremiumScreenController;
import kr.co.sist.sc.admin.vo.SCABookOnScreenVO;

@SuppressWarnings("serial")
public class SCABookPremiumScreenView extends JDialog {
	private JButton[][] jbtSeat;
	private JButton jbtSelect, jbtClose;
	private SCABookOnScreenVO scabos_vo;
	
	public SCABookPremiumScreenView(SCABookManageView scabmv) {
		super(scabmv, "예매 관리 - 프리미엄 좌석 선택", true);
		
		this.scabos_vo = scabos_vo;
		
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
		SCABookPremiumScreenController scabpsc = new SCABookPremiumScreenController(this);
		
		addWindowListener(scabpsc);
		
		jbtSelect.addActionListener(scabpsc);
		jbtClose.addActionListener(scabpsc);
		
		// size 520X600
		setSize(520, 620);
		setLocationRelativeTo(null);
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

	public SCABookOnScreenVO getScabos_vo() {
		return scabos_vo;
	}
	
} // class
