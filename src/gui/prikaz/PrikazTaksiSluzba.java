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

import entiteti.osoba.Musterija;
import gui.dodavanjeIzmena.OsobeDodavanjeIzmena;
import gui.dodavanjeIzmena.TipObrade;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;

public class PrikazMusterije extends Prikaz {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaksiSluzba taksiSluzba;

	public PrikazMusterije(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		setTitle("Prikaz musterija");
		tabelaMusterije();
		initAction();

	}

	private void tabelaMusterije() {
		String[] zaglavlje = { "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Broj telefona", "Korisnicko Ime", "Lozinka",
				"Mobilna Aplikacija" };
		ArrayList<Musterija> sveMusterije = new ArrayList<>();
		sveMusterije.addAll(taksiSluzba.getMusterije());
		Object[][] podaci = new Object[sveMusterije.size()][zaglavlje.length];
		for (int i = 0; i < sveMusterije.size(); i++) {
			Musterija musterija = sveMusterije.get(i);
			podaci[i][0] = musterija.getIme();
			podaci[i][1] = musterija.getPrezime();
			podaci[i][2] = musterija.getJMBG();
			podaci[i][3] = musterija.getAdresa();
			podaci[i][4] = musterija.getPol();
			podaci[i][5] = musterija.getBrojTelefona();
			podaci[i][6] = musterija.getKorisnickoIme();
			podaci[i][7] = musterija.getLozinka();
			podaci[i][8] = musterija.getMobapp();
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

	private void initAction() {
		btnPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				OsobeDodavanjeIzmena os = new OsobeDodavanjeIzmena(taksiSluzba, new Musterija(), PrikazMusterije.this,
						TipObrade.DODAVANJE);
				os.setVisible(true);

			}
		});

		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selektovanRed = tabela.getSelectedRow();
				if (selektovanRed == -1) {
					JOptionPane.showMessageDialog(null, "Morate selektovati red u tabeli");

				} else {
					String JMBG = (String) tabela.getValueAt(selektovanRed, 2);
					Musterija musterija = Pretraga.pronadjiMusterijuPoJMBG(taksiSluzba, JMBG);
					if (musterija == null) {
						JOptionPane.showMessageDialog(null, "Musterija nije pronadjena");
					} else {
						OsobeDodavanjeIzmena odi = new OsobeDodavanjeIzmena(taksiSluzba, musterija,
								PrikazMusterije.this, TipObrade.IZMENA);
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
					Musterija musterija = Pretraga.pronadjiMusterijuPoJMBG(taksiSluzba, JMBG);
					if (musterija != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Brisanje musterije",
								JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							taksiSluzba.getMusterije().remove(musterija);
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							model.removeRow(selektovanRed);
							Snimanje.snimiSve(taksiSluzba);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage musterije");
					}

				}

			}
		});
	}
}
