package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kr.co.sist.sc.admin.controller.SCASnackManageController;
import kr.co.sist.sc.admin.controller.SCASnackMenuRemoveController;

public class SCASnackMenuRemoveView extends JDialog {

	private JButton jbtSnackDelete, jbtClose;
	private DefaultListModel<String> dlmSnackName;
	private JList<String> jlstSnackName;

	public SCASnackMenuRemoveView(SCASnackManageView scasmv) {
		super(scasmv, "삭제할 메뉴를 선택하세요.", true);
		setLayout(null);

		// 배경 라벨
		JLabel jlBg = new JLabel();

		// 버튼
		jbtSnackDelete = new JButton();
		jbtClose = new JButton();

		// 이미지 아이콘 설정
		jlBg.setIcon(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack_management_6-4_delete_snack_bg(400x620).png"));
		jbtSnackDelete.setIcon(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_snack_delete(125x40).png"));
		jbtClose.setIcon(
				new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));

		// 기본 버튼 스타일 제거
		jbtSnackDelete.setContentAreaFilled(false);
		jbtSnackDelete.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);

		// J리스트
		dlmSnackName = new DefaultListModel<String>();
		jlstSnackName = new JList<String>(dlmSnackName);
		jlstSnackName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// J리스트 설정
		jlstSnackName.setFixedCellHeight(58);
		jlstSnackName.setFont(new Font("나눔바른고딕", Font.PLAIN, 24));
		jlstSnackName.setForeground(Color.BLACK);
		DefaultListCellRenderer jlstRenderer = (DefaultListCellRenderer) jlstSnackName.getCellRenderer();
		jlstRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		// J리스트는 setOpaque 와 setBackground(흰색설정)을 통해 배경색을 없앨 수 있다.
//		jlstSnackName.setBackground(new Color(0, 0, 0, 0));
//		jlstSnackName.setOpaque(false);
		
		jlstSnackName.setBorder(new LineBorder(Color.WHITE));
		
		
		// 위치, 크기 설정
		jlBg.setBounds(0, 0, 400, 620);
		jlstSnackName.setBounds(12, 30, 370, 470);
		jbtSnackDelete.setBounds(68, 520, 125, 40);
		jbtClose.setBounds(208, 520, 125, 40);

		// 배경 라벨에 컴포넌트 추가
		jlBg.add(jlstSnackName);
		jlBg.add(jbtSnackDelete);
		jlBg.add(getJbtClose());

		// 배경 라벨 추가
		add(jlBg);

		// 이벤트 처리
		SCASnackMenuRemoveController scasmrc = new SCASnackMenuRemoveController(scasmv, this);
		addWindowListener(scasmrc);
		jbtSnackDelete.addActionListener(scasmrc);
		jbtClose.addActionListener(scasmrc);
		jbtSnackDelete.addMouseListener(scasmrc);
		jbtClose.addMouseListener(scasmrc);

		setBounds(scasmv.getX() + scasmv.getWidth(), scasmv.getY(), 400, 620);
		setResizable(false);
		setVisible(true);

	} // SCASnackMenuRemoveView

	public JButton getJbtSnackDelete() {
		return jbtSnackDelete;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public DefaultListModel<String> getDlmSnackName() {
		return dlmSnackName;
	}

	public JList<String> getJlstSnackName() {
		return jlstSnackName;
	}

} // class
