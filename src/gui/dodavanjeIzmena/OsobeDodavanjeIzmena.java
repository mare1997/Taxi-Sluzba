package gui.dodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entiteti.osoba.Musterija;
import entiteti.voznja.MobilnaAplikacija;
import entiteti.voznja.Voznja;
import gui.prikaz.Prikaz;
import gui.prikaz.PrikazVoznjiMusterija;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Snimanje;
import utils.Tools;

public class DodavanjeIzmenaMusterija extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JLabel lblAdresa;
	protected JTextField txtAdresa;
	protected JLabel lblKoordinate;
	protected JTextField txtKoordinate;
	protected JLabel lblNapomena;
	protected JTextField txtNapomena;

	protected JButton btnOk;
	protected JButton btnCancel;

	protected Voznja voznja;
	protected Prikaz prikaz;
	protected TaksiSluzba taksiSluzba;
	protected TipObrade tip;

	public DodavanjeIzmenaMusterija(TaksiSluzba taksiSluzba, Voznja voznja, Prikaz prikaz, TipObrade tip) {
		this.taksiSluzba = taksiSluzba;
		this.prikaz = prikaz;
		this.voznja = voznja;
		this.tip = tip;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Dodavanje voznji");
		initGui();
		if (TipObrade.IZMENA == tip) {
			setTitle("Izmena voznji");
			initVrednosti();
		}
		initAkcija();
		pack();
	}

	private void initGui() {
		MigLayout layout = new MigLayout("wrap 1", "[][]", "[][][][]");
		setLayout(layout);

		lblAdresa = new JLabel("Adresa");
		txtAdresa = new JTextField(20);
		lblKoordinate = new JLabel("Koordinate");
		txtKoordinate = new JTextField(20);
		lblNapomena = new JLabel("Napomena");
		txtNapomena = new JTextField(20);
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOk);

		add(lblAdresa);
		add(txtAdresa);
		add(lblKoordinate);
		add(txtKoordinate);
		add(lblNapomena);
		add(txtNapomena);

		add(btnOk, "split 2");
		add(btnCancel);
	}

	private void initVrednosti() {
		txtAdresa.setText(voznja.getAdresaPolaska());
		txtKoordinate.setText(((MobilnaAplikacija) voznja).getKoordinate());
		txtNapomena.setText(((MobilnaAplikacija) voznja).getNapomena());

	}

	private void initAkcija() {
		btnOk.addActionListener(new ActionListener() {

			boolean emptyOk = true;
			String koordinate = null;
			String napomena = null;

			@Override
			public void actionPerformed(ActionEvent e) {

				String adresa = txtAdresa.getText();
				if (adresa.equals("")) {
					emptyOk = false;
				}
				koordinate = txtKoordinate.getText();
				if (koordinate.equals("")) {
					emptyOk = false;
				}
				napomena = txtNapomena.getText();
				if (napomena.equals("")) {
					emptyOk = false;
				}
				if (!emptyOk) {
					JOptionPane.showMessageDialog(null, "Niste uneli sva polja.");
				} else {
					if (tip == TipObrade.DODAVANJE) {
						voznja = new MobilnaAplikacija();
						voznja.setId(Tools.generateID(taksiSluzba));
						voznja.setMusterija((Musterija) taksiSluzba.getUlogovan());
						taksiSluzba.getMobilneAplikacije().add((MobilnaAplikacija) voznja);
					}
					voznja.setAdresaPolaska(adresa);
					voznja.setDatumVreme(new Date());
					((MobilnaAplikacija) voznja).setKoordinate(koordinate);
					((MobilnaAplikacija) voznja).setNapomena(napomena);
				}
				Snimanje.snimiSve(taksiSluzba);
				DodavanjeIzmenaMusterija.this.setVisible(false);
				DodavanjeIzmenaMusterija.this.dispose();
				prikaz.setVisible(false);
				prikaz.dispose();
				PrikazVoznjiMusterija pvM = new PrikazVoznjiMusterija(taksiSluzba);
				pvM.setVisible(true);
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