package kr.co.sist.sc.admin.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.sc.admin.controller.SCABookStandardScreenController;
import kr.co.sist.sc.admin.vo.SCABookOnScreenVO;

/**
 * 예매 관리
 * 스탠다드 좌석 스크린
 * @author owner
 */
@SuppressWarnings("serial")
public class SCABookStandardScreenView extends JDialog {
	private JButton[][] jbtSeat;
	private JButton jbtSelect, jbtClose;
	private SCABookOnScreenVO scabos_vo;
	
	public SCABookStandardScreenView(SCABookManageView scabmv, SCABookOnScreenVO scabos_vo) {
		super(scabmv, "예매 관리 - 스탠다드 좌석 선택", true);
		
		this.scabos_vo = scabos_vo;
		
		JLabel jlblTitle = new JLabel("STANDARD SCREEN");
		jlblTitle.setBounds(115, 20, 300, 60);
		
		// seat
		jbtSeat = new JButton[4][5];
		
		int x = 100, y = 100;
		
		for (int i = 0; i < jbtSeat.length; i++) {
			for (int j = 0; j < jbtSeat[0].length; j++) {
				jbtSeat[i][j] = new JButton((j + 1) + "");
				jbtSeat[i][j].setBounds(x, y, 60, 60);
				x += 65;
				
				add(jbtSeat[i][j]);
			} // end for
			
			x = 100;
			y += 65;
		} // end for
		
		// jp
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.LIGHT_GRAY);
		jp.setBounds(0, 0, 500, 600);
		
		add(jlblTitle);
		add(jp);
		
		// action
		SCABookStandardScreenController scabssc = new SCABookStandardScreenController(this);
		
		addWindowListener(scabssc);
		
		// size 500X600
		setSize(500, 620);
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
