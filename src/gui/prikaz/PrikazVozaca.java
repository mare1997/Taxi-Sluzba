package gui.prikaz;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import taksi_sluzba.TaksiSluzba;

public abstract class PrikazPoruciti extends Prikaz {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JButton btnTaxi;

	public PrikazPoruciti(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);

	}

	@Override
	public void initOpcijeTabele() {
		super.initOpcijeTabele();
		ImageIcon taxiIcon = new ImageIcon(getClass().getResource("/img/taxi.png"));
		this.btnTaxi = new JButton(taxiIcon);
		mainToolbar.add(btnTaxi);
	}
}
