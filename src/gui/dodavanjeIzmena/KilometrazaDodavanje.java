package gui.dodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entiteti.osoba.Dispecer;
import entiteti.osoba.Musterija;
import entiteti.osoba.Vozac;
import entiteti.voznja.MobilnaAplikacija;
import entiteti.voznja.TelefonskiPoziv;
import entiteti.voznja.Voznja;
import gui.prikaz.Prikaz;
import gui.prikaz.PrikazVoznjiDispecer;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;
import utils.Tools;

public class DodavanjeIzmenaDispecer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JLabel lblAdresa;
	protected JTextField txtAdresa;
	protected JLabel lblMusterija;
	protected JTextField txtMusterija;
	protected JComboBox<String> cbMusterija;
	protected JLabel lblVozac;
	protected JComboBox<String> cbVozac;
	protected JLabel lblDispecer;
	protected JTextField txtDispecer;
	protected JLabel lblKoordinate;
	protected JTextField txtKoordinate;
	protected JLabel lblNapomena;
	protected JTextField txtNapomena;
	protected JLabel lblKilometraza;
	protected JTextField txtKilometraza;
	protected JLabel lblCena;
	protected JTextField txtCena;

	protected JButton btnOk;
	protected JButton btnCancel;

	protected Voznja voznja;
	protected Prikaz prikaz;
	protected TaksiSluzba taksiSluzba;
	protected TipObrade tip;

	public DodavanjeIzmenaDispecer(TaksiSluzba taksiSluzba, Voznja voznja, Prikaz prikaz, TipObrade tip) {
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
		lblMusterija = new JLabel("Musterija");
		cbMusterija = new JComboBox<>();
		for (Musterija m : taksiSluzba.getMusterije()) {
			cbMusterija.addItem(m.getKorisnickoIme());
		}
		lblVozac = new JLabel("Vozac");
		cbVozac = new JComboBox<>();
		cbVozac.addItem(null);
		for (Vozac v : taksiSluzba.getVozaci()) {
			cbVozac.addItem(v.getKorisnickoIme());
		}
		txtDispecer = new JTextField(20);
		lblDispecer = new JLabel("Dispecer");
		lblKoordinate = new JLabel("Koordinate");
		txtKoordinate = new JTextField(20);
		lblNapomena = new JLabel("Napomena");
		txtNapomena = new JTextField(20);
		lblKilometraza = new JLabel("Kilometraza");
		txtKilometraza = new JTextField(20);
		lblCena = new JLabel("Cena");
		txtCena = new JTextField(20);
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOk);

		add(lblAdresa);
		add(txtAdresa);
		add(lblVozac);
		add(cbVozac);
		add(lblMusterija);
		add(cbMusterija);

		if (tip == TipObrade.IZMENA) {
			add(lblDispecer);
			add(txtDispecer);
			add(lblKoordinate);
			add(txtKoordinate);
			add(lblNapomena);
			add(txtNapomena);
			add(lblKilometraza);
			add(txtKilometraza);
			add(lblCena);
			add(txtCena);
			txtCena.setEnabled(false);
		}

		add(btnOk, "split 2");
		add(btnCancel);

	}

	private void initVrednosti() {
		txtAdresa.setText(voznja.getAdresaPolaska());
		cbMusterija.setSelectedItem(voznja.getMusterija().getKorisnickoIme());
		if (voznja.getVozac() != null) {
			cbVozac.setSelectedItem(voznja.getVozac().getKorisnickoIme());
		}
		txtCena.setText(String.valueOf(voznja.getCena()));
		txtKilometraza.setText(String.valueOf(voznja.getKilometraza()));
		txtDispecer.setEnabled(false);
		if (voznja instanceof MobilnaAplikacija) {
			txtKoordinate.setText(((MobilnaAplikacija) voznja).getKoordinate());
			txtNapomena.setText(((MobilnaAplikacija) voznja).getNapomena());

		} else {
			txtDispecer.setText(((TelefonskiPoziv) voznja).getDispecer().getKorisnickoIme());
			txtKoordinate.setEnabled(false);
			txtNapomena.setEnabled(false);
		}
	}

	private void initAkcija() {
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean emptyOk = true;
				boolean formatkOk = true;

				String koordinate = null;
				String napomena = null;
				String km = null;

				String adresa = txtAdresa.getText();
				if (adresa.equals("")) {
					emptyOk = false;
				}
				if (voznja instanceof MobilnaAplikacija) {
					koordinate = txtKoordinate.getText();
					if (koordinate.equals("")) {
						emptyOk = false;
					}
					napomena = txtNapomena.getText();
					if (napomena.equals("")) {
						emptyOk = false;
					}

				}
				if (tip == TipObrade.IZMENA) {
					km = txtKilometraza.getText();
					if (km.equals("")) {
						emptyOk = false;
					}
					formatkOk = Tools.isDouble(km);
				}

				if (!formatkOk) {
					JOptionPane.showMessageDialog(null, "Format unetih podataka nije ispravan.");
				}
				if (!emptyOk) {
					JOptionPane.showMessageDialog(null, "Niste uneli sva polja.");
				}

				if (emptyOk && formatkOk) {
					if (tip == TipObrade.DODAVANJE) {
						if (voznja instanceof MobilnaAplikacija) {
							voznja = new MobilnaAplikacija();
						} else {
							voznja = new TelefonskiPoziv();
						}
						voznja.setId(Tools.generateID(taksiSluzba));
					}
					voznja.setAdresaPolaska(adresa);
					voznja.setVozac(Pretraga.pronadjiVozacaPoKorIm(taksiSluzba, (String) cbVozac.getSelectedItem()));
					voznja.setDatumVreme(new Date());
					if (km != null) {
						voznja.setKilometraza(Double.parseDouble(km));
					}
					voznja.izracunajCenu();
					if (voznja instanceof TelefonskiPoziv) {
						voznja.setMusterija(
								Pretraga.pronadjiMusterijuPoKorIm(taksiSluzba, (String) cbMusterija.getSelectedItem()));
						if (tip == TipObrade.DODAVANJE) {
							((TelefonskiPoziv) voznja).setDispecer((Dispecer) taksiSluzba.getUlogovan());
							taksiSluzba.getPozivi().add((TelefonskiPoziv) voznja);
						}
					} else {
						((MobilnaAplikacija) voznja).setKoordinate(koordinate);
						((MobilnaAplikacija) voznja).setNapomena(napomena);
						if (tip == TipObrade.DODAVANJE) {
							voznja.setMusterija((Musterija) taksiSluzba.getUlogovan());
							taksiSluzba.getMobilneAplikacije().add((MobilnaAplikacija) voznja);
						}
					}
					Snimanje.snimiSve(taksiSluzba);
					DodavanjeIzmenaDispecer.this.setVisible(false);
					DodavanjeIzmenaDispecer.this.dispose();
					prikaz.setVisible(false);
					prikaz.dispose();
					PrikazVoznjiDispecer pvD = new PrikazVoznjiDispecer(taksiSluzba);
					pvD.setVisible(true);
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
