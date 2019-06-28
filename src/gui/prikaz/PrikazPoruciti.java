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

import entiteti.osoba.Dispecer;
import gui.dodavanjeIzmena.OsobeDodavanjeIzmena;
import gui.dodavanjeIzmena.TipObrade;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;

public class PrikazDispecera extends Prikaz {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected TaksiSluzba taksiSluzba;

	public PrikazDispecera(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		setTitle("Prikaz dispecera");
		tabelaOsobe();
		initAkcija();

	}

	private void tabelaOsobe() {
		String[] zaglavlje = { "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Broj telefona", "Plata", "Korisnicko Ime",
				"Lozinka", "Broj tel. linije", "Odeljenje" };
		ArrayList<Dispecer> sveOsobe = new ArrayList<>();
		sveOsobe.addAll(taksiSluzba.getDispeceri());
		Object[][] podaci = new Object[sveOsobe.size()][zaglavlje.length];
		for (int i = 0; i < sveOsobe.size(); i++) {
			Dispecer osoba = sveOsobe.get(i);
			podaci[i][0] = osoba.getIme();
			podaci[i][1] = osoba.getPrezime();
			podaci[i][2] = osoba.getJMBG();
			podaci[i][3] = osoba.getAdresa();
			podaci[i][4] = osoba.getPol();
			podaci[i][5] = osoba.getBrojTelefona();
			podaci[i][6] = osoba.getPlata();
			podaci[i][7] = osoba.getKorisnickoIme();
			podaci[i][8] = osoba.getLozinka();
			podaci[i][9] = osoba.getBrojTelLinije();
			podaci[i][10] = osoba.getOdeljenjeNaPoslu();

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
				OsobeDodavanjeIzmena odi = new OsobeDodavanjeIzmena(taksiSluzba, new Dispecer(), PrikazDispecera.this,
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
					Dispecer dispecer = Pretraga.pronadjiDispeceraPoJMBG(taksiSluzba, JMBG);
					if (dispecer == null) {
						JOptionPane.showMessageDialog(null, "Dispecer sa tim JMBG-om ne postoji!");
					} else {
						OsobeDodavanjeIzmena odi = new OsobeDodavanjeIzmena(taksiSluzba, dispecer, PrikazDispecera.this,
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
					JOptionPane.showMessageDialog(null, "Morate izabrati musteriju");
				} else {
					String JMBG = String.valueOf(tabela.getValueAt(selektovanRed, 2));
					Dispecer dispecer = Pretraga.pronadjiDispeceraPoJMBG(taksiSluzba, JMBG);
					if (dispecer != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Brisanje dispecera",
								JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							taksiSluzba.getDispeceri().remove(dispecer);
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							model.removeRow(selektovanRed);
							Snimanje.snimiSve(taksiSluzba);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage dispecera");
					}
				}
			}
		});

	}

}
