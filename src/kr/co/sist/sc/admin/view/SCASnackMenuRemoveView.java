package kr.co.sist.sc.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import kr.co.sist.sc.admin.controller.SCASnackMenuRemoveController;

@SuppressWarnings("serial")
public class SCASnackMenuRemoveView extends JDialog {

	private JButton jbtSnackDelete, jbtClose;
	private DefaultListModel<String> dlmSnackName;
	private JList<String> jlstSnackName;

	public SCASnackMenuRemoveView(SCASnackManageView scasmv) {
		super(scasmv, "삭제할 메뉴를 선택하세요.", true);
		setLayout(null);

		JLabel jlBg = new JLabel();

		jbtSnackDelete = new JButton();
		jbtClose = new JButton();

		jlBg.setIcon(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/snack_management_6-4_delete_snack_bg(400x620).png"));
		jbtSnackDelete.setIcon(new ImageIcon(
				"C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_snack_delete(125x40).png"));
		jbtClose.setIcon(
				new ImageIcon("C:/Users/owner/git/sist_prj2/src/kr/co/sist/sc/admin/images/jbt_close(125x40).png"));

		jbtSnackDelete.setContentAreaFilled(false);
		jbtSnackDelete.setBorderPainted(false);
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);

		dlmSnackName = new DefaultListModel<String>();
		jlstSnackName = new JList<String>(dlmSnackName);
		jlstSnackName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jlstSnackName.setFixedCellHeight(58);
		jlstSnackName.setFont(new Font("나눔바른고딕", Font.PLAIN, 24));
		jlstSnackName.setForeground(Color.WHITE);
		DefaultListCellRenderer jlstRenderer = (DefaultListCellRenderer) jlstSnackName.getCellRenderer();
		jlstRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		jlstSnackName.setBackground(new Color(255, 255, 255, 0));
		jlstSnackName.setOpaque(false);
		
		
		TitledBorder titledBorder = BorderFactory.createTitledBorder("삭제할 메뉴를 선택하세요.");
		jlstSnackName.setBorder(titledBorder);
		titledBorder.setTitleColor(Color.WHITE);
		titledBorder.setTitleFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		
		jlBg.setBounds(0, 0, 400, 620);
		jlstSnackName.setBounds(12, 30, 370, 470);
		jbtSnackDelete.setBounds(68, 520, 125, 40);
		jbtClose.setBounds(208, 520, 125, 40);
		
		jlBg.add(jlstSnackName);
		jlBg.add(jbtSnackDelete);
		jlBg.add(getJbtClose());

		add(jlBg);

		SCASnackMenuRemoveController scasmrc = new SCASnackMenuRemoveController(scasmv, this);
		addWindowListener(scasmrc);
		jbtSnackDelete.addActionListener(scasmrc);
		jbtClose.addActionListener(scasmrc);

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
