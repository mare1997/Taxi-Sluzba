package gui.dodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import entiteti.automobil.Automobil;
import entiteti.automobil.Vrsta;
import gui.prikaz.PrikazAutomobila;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Snimanje;
import utils.Tools;

public class AutomobiliDodavanjeIzmena extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblModel;
	private JLabel lblProizvodjac;
	private JLabel lblGodProizvodnje;
	private JLabel lblBrRegistracije;
	private JLabel lblBrTaksiVozila;
	private JLabel lblVrsta;

	private JTextField txtModel;
	private JTextField txtProizvodjac;
	private JTextField txtGodProizvodnje;
	private JTextField txtBrRegistracije;
	private JTextField txtBrTaksiVozila;
	private ButtonGroup btButtonGroupVrsta;
	private JRadioButton rbtnPutnicki;
	private JRadioButton rbtnKombi;
	private JButton btnOK;
	private JButton btnCancel;

	private TaksiSluzba taksiSluzba;
	private Automobil auto;
	private PrikazAutomobila prikazAutomobila;
	private TipObrade tipObrade;

	public AutomobiliDodavanjeIzmena(TaksiSluzba taksiSluzba, Automobil auto, PrikazAutomobila pa, TipObrade tip) {
		super();
		this.taksiSluzba = taksiSluzba;
		this.auto = auto;
		this.prikazAutomobila = pa;
		this.tipObrade = tip;
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

	private void initGui() {

		MigLayout layout = new MigLayout("wrap 1", "[][]", "[][][][][][][][][][][][][][][][][]");
		setLayout(layout);

		lblModel = new JLabel("Model");
		txtModel = new JTextField(20);
		lblProizvodjac = new JLabel("Proizvodjac");
		txtProizvodjac = new JTextField(20);
		lblGodProizvodnje = new JLabel("Godina proizvodnje");
		txtGodProizvodnje = new JTextField(20);
		lblBrRegistracije = new JLabel("Broj registracije");
		txtBrRegistracije = new JTextField(20);
		lblBrTaksiVozila = new JLabel("Broj taksi vozila");
		txtBrTaksiVozila = new JTextField(20);
		lblVrsta = new JLabel("Vrsta automobila");
		btButtonGroupVrsta = new ButtonGroup();
		rbtnKombi = new JRadioButton("Kombi");
		rbtnPutnicki = new JRadioButton("Putnicki");
		btButtonGroupVrsta.add(rbtnKombi);
		btButtonGroupVrsta.add(rbtnPutnicki);
		rbtnKombi.setSelected(true);
		btnOK = new JButton("OK");
		btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOK);

		add(lblModel);
		add(txtModel);
		add(lblProizvodjac);
		add(txtProizvodjac);
		add(lblGodProizvodnje);
		add(txtGodProizvodnje);
		add(lblBrRegistracije);
		add(txtBrRegistracije);
		add(lblBrTaksiVozila);
		add(txtBrTaksiVozila);
		add(lblVrsta);
		add(rbtnKombi);
		add(rbtnPutnicki);
		add(btnOK, "split 2");
		add(btnCancel);

		if (tipObrade == TipObrade.IZMENA) {
			initVrednosti();
		}

	}

	private void initVrednosti() {
		txtModel.setText(auto.getModel());
		txtProizvodjac.setText(auto.getProizvodjac());
		txtGodProizvodnje.setText(String.valueOf(auto.getGodinaProizvodnje()));
		txtBrRegistracije.setText(auto.getBrojRegistracije());
		txtBrTaksiVozila.setText(String.valueOf(auto.getBrojVozila()));
		txtBrTaksiVozila.setEnabled(false);
		if (auto.getVrstaAutomobila() == Vrsta.KOMBI) {
			rbtnKombi.setSelected(true);
		} else {
			rbtnPutnicki.setSelected(true);
		}

	}

	private void initAkcija() {
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean formatOk = true;
				boolean emptyOk = true;
				boolean jedinBrTaksiVozila = true;

				String model = txtModel.getText();
				if (model.equals("")) {
					emptyOk = false;
				}
				String proizvodjac = txtProizvodjac.getText();
				if (proizvodjac.equals("")) {
					emptyOk = false;
				}
				String godinaProizvodnje = txtGodProizvodnje.getText();
				if (godinaProizvodnje.equals("")) {
					emptyOk = false;
				}
				if (!Tools.isNumeric(godinaProizvodnje)) {
					formatOk = false;
				}
				String brRegistracije = txtBrRegistracije.getText();
				if (brRegistracije.equals("")) {
					emptyOk = false;
				}
				String brTaksiVozila = txtBrTaksiVozila.getText();
				if (!brTaksiVozila.equals(String.valueOf(auto.getBrojVozila()))) {
					jedinBrTaksiVozila = Tools.proveriJedinstvenostBrTaksiVozila(taksiSluzba, brTaksiVozila);
					formatOk = Tools.isNumeric(brTaksiVozila);

				}
				if (!formatOk) {
					JOptionPane.showMessageDialog(null, "Format unetih podataka nije ispravan");
				}
				if (!emptyOk) {
					JOptionPane.showMessageDialog(null, "Niste uneli sva polja");
				}
				if (!jedinBrTaksiVozila) {
					JOptionPane.showMessageDialog(null, "Automobil sa tim brojem vec postoji");
				}

				if (formatOk && emptyOk && jedinBrTaksiVozila) {
					auto.setModel(model);
					auto.setProizvodjac(proizvodjac);
					auto.setGodinaProizvodnje(Integer.parseInt(godinaProizvodnje));
					auto.setBrojRegistracije(brRegistracije);
					auto.setBrojVozila(Integer.parseInt(brTaksiVozila));
					if (rbtnKombi.isSelected()) {
						auto.setVrstaAutomobila(Vrsta.KOMBI);
					} else {
						auto.setVrstaAutomobila(Vrsta.PUTNICKI);
					}
					if (tipObrade == TipObrade.DODAVANJE) {
						taksiSluzba.getAutomobili().add(auto);
					}
					Snimanje.snimiSve(taksiSluzba);
					prikazAutomobila.setVisible(false);
					prikazAutomobila.dispose();
					AutomobiliDodavanjeIzmena.this.setVisible(false);
					AutomobiliDodavanjeIzmena.this.dispose();
					PrikazAutomobila pa = new PrikazAutomobila(taksiSluzba);
					pa.setVisible(true);
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
