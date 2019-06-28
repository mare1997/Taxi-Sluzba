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

import entiteti.automobil.Automobil;
import entiteti.osoba.Vozac;
import gui.dodavanjeIzmena.AutomobiliDodavanjeIzmena;
import gui.dodavanjeIzmena.TipObrade;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;

public class PrikazAutomobila extends Prikaz {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaksiSluzba taksiSluzba;

	public PrikazAutomobila(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		setTitle("Prikaz automobila");
		popuniTabelu();
		initAkcija();
	}

	private void popuniTabelu() {

		String[] zaglavlje = { "Model", "Proizvodjac", "Godina proizvodnje", "Broj registracije", "Broj taksi vozila",
				"Vrsta automobila" };
		ArrayList<Automobil> automobili = new ArrayList<>();
		automobili.addAll(taksiSluzba.getAutomobili());
		Object[][] podaci = new Object[automobili.size()][zaglavlje.length];
		for (int i = 0; i < automobili.size(); i++) {
			Automobil auto = automobili.get(i);
			podaci[i][0] = auto.getModel();
			podaci[i][1] = auto.getProizvodjac();
			podaci[i][2] = auto.getGodinaProizvodnje();
			podaci[i][3] = auto.getBrojRegistracije();
			podaci[i][4] = auto.getBrojVozila();
			podaci[i][5] = auto.getVrstaAutomobila();
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
				AutomobiliDodavanjeIzmena adi = new AutomobiliDodavanjeIzmena(taksiSluzba, new Automobil(),
						PrikazAutomobila.this, TipObrade.DODAVANJE);
				adi.setVisible(true);
			}
		});

		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selektovanRed = tabela.getSelectedRow();
				if (selektovanRed == -1) {
					JOptionPane.showMessageDialog(null, "Morate selektovati red");
				} else {
					String brTaksiVozila = String.valueOf(tabela.getValueAt(selektovanRed, 4));
					Automobil a = Pretraga.pronadjiAutomobilPoBrVozila(taksiSluzba, brTaksiVozila);
					if (a == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage");
					} else {
						AutomobiliDodavanjeIzmena adi = new AutomobiliDodavanjeIzmena(taksiSluzba, a,
								PrikazAutomobila.this, TipObrade.IZMENA);
						adi.setVisible(true);
					}
				}

			}
		});

		btnMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selektovanRed = tabela.getSelectedRow();
				if (selektovanRed == -1) {
					JOptionPane.showMessageDialog(null, "Morate selektovati red");
				} else {
					String brTaksiVozila = String.valueOf(tabela.getValueAt(selektovanRed, 4));
					Automobil a = Pretraga.pronadjiAutomobilPoBrVozila(taksiSluzba, brTaksiVozila);
					if (a == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage");
					} else {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Brisanje automobila",
								JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							for (Vozac v : a.getListaVozaca()) {
								v.setAutomobil(null);
							}
							taksiSluzba.getAutomobili().remove(a);
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							model.removeRow(selektovanRed);
							Snimanje.snimiSve(taksiSluzba);
						}

					}
				}
			}
		});
	}

}
