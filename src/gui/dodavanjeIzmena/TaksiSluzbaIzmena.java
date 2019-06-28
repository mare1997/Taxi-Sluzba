package gui.dodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entiteti.osoba.Vozac;
import entiteti.voznja.Voznja;
import gui.prikaz.Prikaz;
import gui.prikaz.PrikazVoznjiVozac;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Snimanje;
import utils.Tools;

public class KilometrazaDodavanje extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JLabel lblKm;
	protected JTextField txtKm;
	protected JButton btnOk;
	protected JButton btnCancel;

	protected Voznja voznja;
	protected Prikaz prikaz;
	protected TaksiSluzba taksiSluzba;
	protected TipObrade tipObrade;

	public KilometrazaDodavanje(Voznja voznja, Prikaz prikaz, TaksiSluzba taksiSluzba, TipObrade tipObrade) {
		this.voznja = voznja;
		this.prikaz = prikaz;
		this.taksiSluzba = taksiSluzba;
		this.tipObrade = tipObrade;
		setTitle("Dodavanje kilometraze");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		initGui();
		if (tipObrade == TipObrade.IZMENA) {
			setTitle("Izmena kilometraze");
			initVrednosti();
		}
		initAkcija();
		pack();
	}

	private void initGui() {
		MigLayout layout = new MigLayout("wrap 1", "[][]", "[][][][]");
		setLayout(layout);
		lblKm = new JLabel("Kilometraza");
		txtKm = new JTextField(20);
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOk);

		add(lblKm);
		add(txtKm);
		add(btnOk, "split 2");
		add(btnCancel);

	}

	private void initVrednosti() {
		txtKm.setText(String.valueOf(voznja.getKilometraza()));
	}

	private void initAkcija() {
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String kilometraza = txtKm.getText();
				if (Tools.isDouble(kilometraza) && Double.parseDouble(kilometraza) > 0) {
					voznja.setVozac((Vozac) taksiSluzba.getUlogovan());
					voznja.setKilometraza(Double.parseDouble(kilometraza));
					voznja.izracunajCenu();
					JOptionPane.showMessageDialog(null, "Ukupno morate da naplatite: " + voznja.getCena());
					Snimanje.snimiSve(taksiSluzba);
					prikaz.setVisible(false);
					prikaz.dispose();
					KilometrazaDodavanje.this.setVisible(false);
					KilometrazaDodavanje.this.dispose();

					PrikazVoznjiVozac pvv = new PrikazVoznjiVozac(taksiSluzba);
					pvv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Format unetih podataka nije ispravan.");
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int odgovor = JOptionPane.showConfirmDialog(null, "Da li zelite da nastavite", "Potvrda",
						JOptionPane.YES_NO_OPTION);
				if (odgovor == JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();
				}

			}
		});
	}

}
