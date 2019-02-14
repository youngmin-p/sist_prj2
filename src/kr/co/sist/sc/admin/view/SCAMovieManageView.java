package kr.co.sist.sc.admin.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.admin.controller.SCAMovieManageController;

@SuppressWarnings("serial")
public class SCAMovieManageView extends JDialog {
	private DefaultTableModel dtmMovieList;
	private JTable jtabMovieList;
	private JButton jbtMovieInsert, jbtClose;
	private String admin_id;
	
	public SCAMovieManageView(SCAMainView scamv, String admin_id) {
		super(scamv, "쌍용관 - 영화 관리", true);
		
		this.admin_id = admin_id;
		
		jbtMovieInsert = new JButton("영화 등록");
		jbtMovieInsert.setBounds(0, 0, 80, 40);
		
		jbtClose = new JButton("닫기");
		jbtClose.setBounds(90, 0, 80, 40);
		
		// bg
		JLabel jlblBackground = new JLabel(new ImageIcon(
				"C:/dev/workspace/sist_prj2/src/kr/co/sist/sc/admin/images/admin_movie_management_bg(600x700).png"));
		jlblBackground.setLayout(null);
		jlblBackground.setBounds(0, 0, 600, 700);
		
		jlblBackground.add(jbtMovieInsert);
		jlblBackground.add(jbtClose);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.LIGHT_GRAY);
		
		jp.add(jlblBackground);
		
		add(jp);
		
		//
		SCAMovieManageController scammc = new SCAMovieManageController(this);
		
		jbtMovieInsert.addActionListener(scammc);
		jbtClose.addActionListener(scammc);
		
		addWindowListener(scammc);
		
		// size 600X700
		setSize(600, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SCAMovieManageView

	public DefaultTableModel getDtmMovieList() {
		return dtmMovieList;
	}

	public JTable getJtabMovieList() {
		return jtabMovieList;
	}

	public JButton getJbtMovieInsert() {
		return jbtMovieInsert;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public String getAdmin_id() {
		return admin_id;
	}
	
} // class
