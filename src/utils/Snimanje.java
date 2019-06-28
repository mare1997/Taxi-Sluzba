package taksi_sluzba;

import gui.LoginProzor;

public class TaksiSluzbaMain {

	public static void main(String[] args) {

		TaksiSluzba taksiSluzba = new TaksiSluzba();

		LoginProzor lp = new LoginProzor(taksiSluzba);
		lp.setVisible(true);

	}

}
