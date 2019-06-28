package gui.dodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import entiteti.automobil.Automobil;
import entiteti.osoba.Dispecer;
import entiteti.osoba.Musterija;
import entiteti.osoba.Odeljenje;
import entiteti.osoba.Osoba;
import entiteti.osoba.Pol;
import entiteti.osoba.Vozac;
import entiteti.osoba.Zaposleni;
import gui.prikaz.Prikaz;
import gui.prikaz.PrikazDispecera;
import gui.prikaz.PrikazMusterije;
import gui.prikaz.PrikazVozaca;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;
import utils.Tools;

public class OsobeDodavanjeIzmena extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblIme;
	private JTextField txtIme;
	private JLabel lblPrezime;
	private JTextField txtPrezime;
	private JLabel lblJMBG;
	private JTextField txtJMBG;
	private JLabel lblAdresa;
	private JTextField txtAdresa;
	private JLabel lblPol;
	private ButtonGroup grupaPol;
	private JRadioButton rbtnMuski;
	private JRadioButton rbtnZenski;
	private JLabel lblBrojTelefona;
	private JTextField txtBrojTelefona;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblLozinka;
	private JTextField txtLozinka;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblPlata;
	private JTextField txtPlata;
	private JLabel lblBrClKarte;
	private JTextField txtBrClKarte;
	private JLabel lblAutomobil;
	private JComboBox<String> cmbAutomobili;
	private JLabel lblBrTelLinije;
	private JTextField txtBrTelLinije;
	private JLabel lblOdeljenje;
	private ButtonGroup grupaOdeljenje;
	private JRadioButton rbtnPrijem;
	private JRadioButton rbtnReklamacije;
	private JLabel lblMobApp;
	private JCheckBox cbMobApp;

	private TaksiSluzba taksiSluzba;
	protected Osoba osoba;
	private Prikaz prikazOsoba;
	private TipObrade tip;

	public static MigLayout layout;

	public OsobeDodavanjeIzmena(TaksiSluzba taksiSLuzba, Osoba osoba, Prikaz prikazOsobe, TipObrade tip) {
		this.taksiSluzba = taksiSLuzba;
		this.osoba = osoba;
		this.prikazOsoba = prikazOsobe;
		this.tip = tip;
		setTitle("Dodavanje");
		if (tip == TipObrade.IZMENA) {
			setTitle("Izmena");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		initGui();
		initAkcija();
		pack();
		setLocationRelativeTo(null);

	}

	protected void initGui() {

		MigLayout layout = new MigLayout("wrap 1", "[][]", "[][][][][][][][][][][][][][][][][]");
		setLayout(layout);

		lblIme = new JLabel("Ime");
		add(lblIme);
		txtIme = new JTextField(20);
		add(txtIme);

		lblPrezime = new JLabel("Prezime");
		add(lblPrezime);

		txtPrezime = new JTextField(20);
		add(txtPrezime);

		lblJMBG = new JLabel("JMBG");
		add(lblJMBG);

		txtJMBG = new JTextField(20);
		add(txtJMBG);

		lblAdresa = new JLabel("Adresa");
		add(lblAdresa);

		txtAdresa = new JTextField(20);
		add(txtAdresa);

		lblPol = new JLabel("Pol");
		add(lblPol);

		rbtnMuski = new JRadioButton("Muski");
		add(rbtnMuski, "split 2");

		rbtnZenski = new JRadioButton("Zenski");
		add(rbtnZenski);

		grupaPol = new ButtonGroup();
		grupaPol.add(rbtnMuski);

		grupaPol.add(rbtnZenski);
		rbtnMuski.setSelected(true);

		lblBrojTelefona = new JLabel("Broj telefona");
		add(lblBrojTelefona);

		txtBrojTelefona = new JTextField(20);
		add(txtBrojTelefona);

		lblKorisnickoIme = new JLabel("Korisncko ime");
		add(lblKorisnickoIme);

		txtKorisnickoIme = new JTextField(20);
		add(txtKorisnickoIme);

		lblLozinka = new JLabel("Lozinka");
		add(lblLozinka);

		txtLozinka = new JTextField(20);
		add(txtLozinka);

		if (osoba instanceof Musterija) {
			lblMobApp = new JLabel("Mobilna aplikacija");
			cbMobApp = new JCheckBox("Koristi");
			add(lblMobApp);
			add(cbMobApp);
		}

		if (osoba instanceof Zaposleni) {
			lblPlata = new JLabel("Plata");
			txtPlata = new JTextField(20);
			add(lblPlata);
			add(txtPlata);
		}

		if (osoba instanceof Vozac) {
			lblBrClKarte = new JLabel("Broj clanske karte");
			cmbAutomobili = new JComboBox<>();
			txtBrClKarte = new JTextField(20);
			lblAutomobil = new JLabel("Automobil");
			cmbAutomobili.addItem(null);
			for (Automobil auto : taksiSluzba.getAutomobili()) {
				cmbAutomobili.addItem(String.valueOf(auto.getBrojVozila()));
			}
			add(lblBrClKarte);
			add(txtBrClKarte);
			add(lblAutomobil);
			add(cmbAutomobili);

		}

		if (osoba instanceof Dispecer) {
			lblBrTelLinije = new JLabel("Broj telefonske linije");
			rbtnPrijem = new JRadioButton("Prijem");
			rbtnReklamacije = new JRadioButton("Reklamacije");
			txtBrTelLinije = new JTextField(20);
			grupaOdeljenje = new ButtonGroup();
			grupaOdeljenje.add(rbtnReklamacije);
			grupaOdeljenje.add(rbtnPrijem);
			rbtnPrijem.setSelected(true);
			add(lblBrTelLinije);
			add(txtBrTelLinije);
			add(rbtnReklamacije);
			add(rbtnPrijem);
		}

		add(new JLabel());

		btnOk = new JButton("Ok");
		this.getRootPane().setDefaultButton(btnOk);

		add(btnOk, "split 2");

		btnCancel = new JButton("Cancel");
		add(btnCancel);

		this.getRootPane().setDefaultButton(btnOk);

		if (tip == TipObrade.IZMENA) {
			initVrednosti();
		}

	}

	protected void initVrednosti() {
		txtIme.setText(this.osoba.getIme());
		txtPrezime.setText(this.osoba.getPrezime());
		txtJMBG.setText(this.osoba.getJMBG());
		txtJMBG.setEnabled(false);
		txtAdresa.setText(this.osoba.getAdresa());
		if (this.osoba.getPol().equals(Pol.MUSKI)) {
			rbtnMuski.setSelected(true);
		} else {
			rbtnZenski.setSelected(true);
		}
		txtBrojTelefona.setText(this.osoba.getBrojTelefona());
		txtKorisnickoIme.setText(this.osoba.getKorisnickoIme());
		txtLozinka.setText(this.osoba.getLozinka());
		if (osoba instanceof Zaposleni) {
			txtPlata.setText(String.valueOf(((Zaposleni) this.osoba).getPlata()));
		}
		if (osoba instanceof Vozac) {
			txtBrClKarte.setText(((Vozac) this.osoba).getBrojClanskeKarte());
			txtBrClKarte.setEnabled(false);
			if (((Vozac) osoba).getAutomobil() == null) {
				cmbAutomobili.setSelectedItem(null);
			} else {
				cmbAutomobili.setSelectedItem(String.valueOf(((Vozac) this.osoba).getAutomobil().getBrojVozila()));
			}
		}
		if (osoba instanceof Dispecer) {
			txtBrTelLinije.setText(String.valueOf(((Dispecer) this.osoba).getBrojTelLinije()));
			if (((Dispecer) osoba).getOdeljenjeNaPoslu() == Odeljenje.PRIJEM_VOZNJE) {
				rbtnPrijem.setSelected(true);
			} else {
				rbtnReklamacije.setSelected(true);
			}
		}

		if (osoba instanceof Musterija) {
			cbMobApp.setSelected(((Musterija) osoba).getMobapp());
		}
	}

	protected void initAkcija() {

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

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean emptyOk = true;
				boolean formatOk = true;
				boolean jedinJMBG = true;
				boolean jedinCK = true;
				boolean jedinTel = true;
				boolean jedinKorIme = true;

				String ime = txtIme.getText();
				if (ime.equals("")) {
					emptyOk = false;
				}

				String prezime = txtPrezime.getText();
				if (prezime.equals("")) {
					emptyOk = false;
				}

				String JMBG = txtJMBG.getText();
				if (JMBG.equals("")) {
					emptyOk = false;
				}
				formatOk = Tools.proveriIspravnostJMBG(JMBG);

				if (TipObrade.DODAVANJE == tip) {
					jedinJMBG = Tools.proveriJedinstevnostJMBG(taksiSluzba, JMBG);
				}

				String adresa = txtAdresa.getText();
				if (adresa.equals("")) {
					emptyOk = false;
				}

				Pol pol = Pol.MUSKI;
				if (rbtnMuski.isSelected()) {
					pol = Pol.MUSKI;
				} else {
					pol = Pol.ZENSKI;
				}
				String brojTelefona = txtBrojTelefona.getText();
				if (brojTelefona.equals("")) {
					emptyOk = false;
				}
				String korisnickoIme = txtKorisnickoIme.getText();
				if (korisnickoIme.equals("")) {
					emptyOk = false;
				}

				if (!(korisnickoIme.equals(osoba.getKorisnickoIme()))) {

					if (!Tools.proveriJedinstvenostiKorisnickogImena(taksiSluzba, korisnickoIme)) {
						jedinKorIme = false;
					}
				}

				String lozinka = txtLozinka.getText();
				if (lozinka.equals("")) {
					emptyOk = false;
				}
				String plata = null;
				String brCK = null;
				String brAuto = null;
				String telLinija = null;

				if (osoba instanceof Zaposleni) {
					plata = txtPlata.getText();
					if (!Tools.isDouble(plata)) {
						formatOk = false;
					}
				}

				if (osoba instanceof Vozac) {
					brCK = txtBrClKarte.getText();
					if (brCK.equals("")) {
						emptyOk = false;
					}

					if (!(brCK.equals(((Vozac) osoba).getBrojClanskeKarte()))) {
						if (!Tools.proveriJedinstevnostBrClKarte(taksiSluzba, brCK)) {
							jedinCK = false;
						}
					}

					brAuto = (String) cmbAutomobili.getSelectedItem();
					if (plata.equals("")) {
						emptyOk = false;
					}
				}

				if (osoba instanceof Dispecer) {
					telLinija = txtBrTelLinije.getText();
					if (telLinija.equals("")) {
						emptyOk = false;
					}
					if (!telLinija.equals(String.valueOf(((Dispecer) osoba).getBrojTelLinije()))) {
						jedinTel = Tools.proveriJedinstvenostTelLinije(taksiSluzba, telLinija);
					}
					if (!Tools.isNumeric(telLinija)) {
						formatOk = false;
					}
				}

				if (!emptyOk) {
					JOptionPane.showMessageDialog(null, "Niste uneli sva polja.");
				}

				if (!formatOk) {
					JOptionPane.showMessageDialog(null, "Format unesenih podataka nije dobar.");
				}

				if (!jedinJMBG) {
					JOptionPane.showMessageDialog(null, "Korisnik vec sa datim JMBG-om vec postoji.");
				}

				if (!jedinCK) {
					JOptionPane.showMessageDialog(null, "Broj clanske karte vec postoji.");
				}

				if (!jedinTel) {
					JOptionPane.showMessageDialog(null, "Broj telefonske linije vec postoji.");
				}

				if (!jedinKorIme) {
					JOptionPane.showMessageDialog(null, "Korisnicko ime je vec u upotrebi.");
				}

				if (emptyOk && formatOk && jedinJMBG && jedinCK && jedinTel && jedinKorIme) {
					if (tip == TipObrade.DODAVANJE) {
						osoba.setIme(ime);
						osoba.setPrezime(prezime);
						osoba.setJMBG(JMBG);
						osoba.setAdresa(adresa);
						osoba.setKorisnickoIme(korisnickoIme);
						osoba.setLozinka(lozinka);
						osoba.setPol(pol);
						osoba.setBrojTelefona(brojTelefona);
						if (osoba instanceof Musterija) {
							if (cbMobApp.isSelected()) {
								((Musterija) osoba).setMobapp(true);
							} else {
								((Musterija) osoba).setMobapp(false);
							}
							taksiSluzba.getMusterije().add((Musterija) osoba);
							PrikazMusterije pm = new PrikazMusterije(taksiSluzba);
							pm.setVisible(true);
						}
						if (osoba instanceof Vozac) {
							((Vozac) osoba).setPlata(Double.parseDouble(plata));
							((Vozac) osoba).setBrojClanskeKarte(brCK);
							Automobil a = Pretraga.pronadjiAutomobilPoBrVozila(taksiSluzba, brAuto);
							((Vozac) osoba).setAutomobil(a);
							if (a != null) {
								a.getListaVozaca().add((Vozac) osoba);
							}
							taksiSluzba.getVozaci().add((Vozac) osoba);
							PrikazVozaca pv = new PrikazVozaca(taksiSluzba);
							pv.setVisible(true);
						}

						if (osoba instanceof Dispecer) {
							((Dispecer) osoba).setPlata(Double.parseDouble(plata));
							((Dispecer) osoba).setBrojTelLinije(Integer.parseInt(telLinija));
							if (rbtnPrijem.isSelected()) {
								((Dispecer) osoba).setOdeljenjeNaPoslu(Odeljenje.PRIJEM_VOZNJE);
							} else {
								((Dispecer) osoba).setOdeljenjeNaPoslu(Odeljenje.REKLAMACIJE);
							}
							taksiSluzba.getDispeceri().add((Dispecer) osoba);
							PrikazDispecera pd = new PrikazDispecera(taksiSluzba);
							pd.setVisible(true);
						}

						Snimanje.snimiSve(taksiSluzba);
						setVisible(false);
						dispose();
						prikazOsoba.setVisible(false);
						prikazOsoba.dispose();
					} else {
						osoba.setIme(ime);
						osoba.setPrezime(prezime);
						osoba.setAdresa(adresa);
						osoba.setPol(pol);
						osoba.setBrojTelefona(brojTelefona);
						osoba.setKorisnickoIme(korisnickoIme);
						osoba.setLozinka(lozinka);
						if (osoba instanceof Zaposleni) {
							((Zaposleni) osoba).setPlata(Double.parseDouble(plata));
						}
						if (osoba instanceof Dispecer) {
							((Dispecer) osoba).setBrojTelLinije(Integer.parseInt(telLinija));
							if (rbtnPrijem.isSelected()) {
								((Dispecer) osoba).setOdeljenjeNaPoslu(Odeljenje.PRIJEM_VOZNJE);
							} else {
								((Dispecer) osoba).setOdeljenjeNaPoslu(Odeljenje.REKLAMACIJE);
							}
							PrikazDispecera pd = new PrikazDispecera(taksiSluzba);
							pd.setVisible(true);
						}
						if (osoba instanceof Vozac) {
							String brAuta = (String) cmbAutomobili.getSelectedItem();
							Automobil automobil = null;
							if (cmbAutomobili.getSelectedItem() == null) {
								if (((Vozac) osoba).getAutomobil() != null) {
									((Vozac) osoba).getAutomobil().getListaVozaca().remove(osoba);
								}
							} else {
								automobil = Pretraga.pronadjiAutomobilPoBrVozila(taksiSluzba, brAuta);
							}
							((Vozac) osoba).setAutomobil(automobil);
							if (automobil != null) {
								automobil.getListaVozaca().add((Vozac) osoba);
							}
							PrikazVozaca pv = new PrikazVozaca(taksiSluzba);
							pv.setVisible(true);
						}
						if (osoba instanceof Musterija) {
							((Musterija) osoba).setMobapp(cbMobApp.isSelected());
							PrikazMusterije pm = new PrikazMusterije(taksiSluzba);
							pm.setVisible(true);
						}
						Snimanje.snimiSve(taksiSluzba);
						setVisible(false);
						dispose();
						prikazOsoba.setVisible(false);
						prikazOsoba.dispose();

					}
				}

			}

		});

	}

}
