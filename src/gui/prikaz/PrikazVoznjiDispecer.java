package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import entiteti.osoba.Vozac;
import gui.dodavanjeIzmena.OsobeDodavanjeIzmena;
import gui.dodavanjeIzmena.TipObrade;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;

public class PrikazVozaca extends Prikaz {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TaksiSluzba taksiSluzba;

	public PrikazVozaca(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		setTitle("Prikaz Vozaca");
		tabelaOsobe();
		initAkcija();

	}

	private void tabelaOsobe() {
		String[] zaglavlje = { "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Broj telefona", "Plata", "Korisnicko Ime",
				"Lozinka", "Broj clanske karte", "Automobil" };
		ArrayList<Vozac> sveOsobe = new ArrayList<>();
		sveOsobe.addAll(taksiSluzba.getVozaci());
		Object[][] podaci = new Object[sveOsobe.size()][zaglavlje.length];
		for (int i = 0; i < sveOsobe.size(); i++) {
			Vozac osoba = sveOsobe.get(i);
			podaci[i][0] = osoba.getIme();
			podaci[i][1] = osoba.getPrezime();
			podaci[i][2] = osoba.getJMBG();
			podaci[i][3] = osoba.getAdresa();
			podaci[i][4] = osoba.getPol();
			podaci[i][5] = osoba.getBrojTelefona();
			podaci[i][6] = osoba.getPlata();
			podaci[i][7] = osoba.getKorisnickoIme();
			podaci[i][8] = osoba.getLozinka();
			podaci[i][9] = osoba.getBrojClanskeKarte();
			if (osoba.getAutomobil() == null) {
				podaci[i][10] = "";
			} else {
				podaci[i][10] = osoba.getAutomobil().getBrojVozila();
			}
		}

		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		tabela = new JTable(model);
		tabela.setCellSelectionEnabled(false);
		tabela.setRowSelectionAllowed(true);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);

		tableScroll = new JScrollPane(tabela);
		add(tableScroll, BorderLayout.CENTER);

	}

	private void initAkcija() {
		btnPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OsobeDodavanjeIzmena odi = new OsobeDodavanjeIzmena(taksiSluzba, new Vozac(), PrikazVozaca.this,
						TipObrade.DODAVANJE);
				odi.setVisible(true);
			}
		});

		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				selektovanRed = tabela.getSelectedRow();
				if (selektovanRed == -1) {
					JOptionPane.showMessageDialog(null, "Morate selektovati red u tabeli");
				} else {
					String JMBG = (String) tabela.getValueAt(selektovanRed, 2);
					Vozac vozac = Pretraga.pronadjiVozacaPoJMBG(taksiSluzba, JMBG);
					if (vozac == null) {
						JOptionPane.showMessageDialog(null, "Vozac sa tim JMBG-om ne postoji!");
					} else {
						OsobeDodavanjeIzmena odi = new OsobeDodavanjeIzmena(taksiSluzba, vozac, PrikazVozaca.this,
								TipObrade.IZMENA);
						odi.setVisible(true);
					}
				}
			}
		});

		btnMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selektovanRed = tabela.getSelectedRow();
				if (selektovanRed == -1) {
					JOptionPane.showMessageDialog(null, "Morate izabrati vozaca");
				} else {
					String JMBG = String.valueOf(tabela.getValueAt(selektovanRed, 2));
					Vozac vozac = Pretraga.pronadjiVozacaPoJMBG(taksiSluzba, JMBG);
					if (vozac != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Brisanje vozaca",
								JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							taksiSluzba.getVozaci().remove(vozac);
							if (vozac.getAutomobil() != null) {
								vozac.getAutomobil().getListaVozaca().remove(vozac);
							}
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							model.removeRow(selektovanRed);
							Snimanje.snimiSve(taksiSluzba);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage vozaca");
					}

				}

			}
		});

	}

}
