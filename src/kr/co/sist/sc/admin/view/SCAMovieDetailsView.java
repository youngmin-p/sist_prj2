package kr.co.sist.sc.admin.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SCAMovieDetailsView extends JDialog {
	private JTextField jtfMovieCode, jtfMovieTitle, jtfMovieImg, jtfMovieGenre, jtfMovieCountry, jtfMovieDirector, 
                       jtfMovieGrade, jtfMovieRunningtime, jtfMoviePlaydate, jtfMovieActor, jtfMovieDate;
	private JTextArea jtaMovieSynopsis;
	private JButton jbtMovieDelete, jbtMovieClose;
	
	public SCAMovieDetailsView(SCAMovieManageView scammv) {
		super(scammv, "영화 관리 - 상세", true);
		
		
		
		// size 800X700
		setSize(800, 720);
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

	public JButton getJbtMovieDelete() {
		return jbtMovieDelete;
	}

	public JButton getJbtMovieClose() {
		return jbtMovieClose;
	}
	
} // class
