package kr.co.sist.sc.admin.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SCAMovieInsertView extends JDialog {
	private JTextField jtfMovieCode, jtfMovieTitle, jtfMovieImg, jtfMovieGenre, jtfMovieCountry, jtfMovieDirector, 
                       jtfMovieGrade, jtfMovieRunningtime, jtfMoviePlaydate, jtfMovieActor, jtfMovieDate;
	private JTextArea jtaMovieSynopsis;
	private JButton jbtImageInsert, jbtMovieInsert, jbtClose;
	
	public SCAMovieInsertView(SCAMovieManageView scammv) {
		super(scammv, "영화 관리 - 등록", true);
		
		
		
		// size 830X915
		setSize(830, 935);
		setLocationRelativeTo(scammv);
		setResizable(false);
		setVisible(true);
		
	} // SCAMovieDetailsView

	public JTextField getJtfMovieCode() {
		return jtfMovieCode;
	}

	public JTextField getJtfMovieTitle() {
		return jtfMovieTitle;
	}

	public JTextField getJtfMovieImg() {
		return jtfMovieImg;
	}

	public JTextField getJtfMovieGenre() {
		return jtfMovieGenre;
	}

	public JTextField getJtfMovieCountry() {
		return jtfMovieCountry;
	}

	public JTextField getJtfMovieDirector() {
		return jtfMovieDirector;
	}

	public JTextField getJtfMovieGrade() {
		return jtfMovieGrade;
	}

	public JTextField getJtfMovieRunningtime() {
		return jtfMovieRunningtime;
	}

	public JTextField getJtfMoviePlaydate() {
		return jtfMoviePlaydate;
	}

	public JTextField getJtfMovieActor() {
		return jtfMovieActor;
	}

	public JTextField getJtfMovieDate() {
		return jtfMovieDate;
	}

	public JTextArea getJtaMovieSynopsis() {
		return jtaMovieSynopsis;
	}

	public JButton getJbtImageInsert() {
		return jbtImageInsert;
	}

	public JButton getJbtMovieInsert() {
		return jbtMovieInsert;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}
	
} // class
