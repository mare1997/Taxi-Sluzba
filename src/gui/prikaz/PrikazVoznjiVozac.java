package gui.prikaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import entiteti.osoba.Dispecer;
import entiteti.osoba.Odeljenje;
import entiteti.voznja.MobilnaAplikacija;
import entiteti.voznja.Voznja;
import gui.dodavanjeIzmena.DodavanjeIzmenaDispecer;
import gui.dodavanjeIzmena.TipObrade;
import taksi_sluzba.TaksiSluzba;
import utils.Pretraga;
import utils.Snimanje;

public class PrikazVoznjiDispecer extends PrikazVoznji {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaksiSluzba taksiSluzba;

	public PrikazVoznjiDispecer(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		initAkcija();
	}

	public void initAkcija() {
		btnPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (taksiSluzba.getUlogovan() instanceof Dispecer
						&& ((Dispecer) taksiSluzba.getUlogovan()).getOdeljenjeNaPoslu() == Odeljenje.PRIJEM_VOZNJE) {
					DodavanjeIzmenaDispecer diD1 = new DodavanjeIzmenaDispecer(taksiSluzba, null,
							PrikazVoznjiDispecer.this, TipObrade.DODAVANJE);
					diD1.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Nemate pristup zeljenoj funkciji.");
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
					String id = String.valueOf(tabela.getValueAt(selektovanRed, 0));
					Voznja v = Pretraga.pronadjiVoznju(taksiSluzba, id);
					if (v == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage");
					} else {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Brisanje voznje",
								JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							if (v instanceof MobilnaAplikacija) {
								taksiSluzba.getMobilneAplikacije().remove(v);
							} else {
								taksiSluzba.getPozivi().remove(v);
							}
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							model.removeRow(selektovanRed);
							Snimanje.snimiSve(taksiSluzba);

						}
					}
				}

			}
		});

		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selektovanRed = tabela.getSelectedRow();
				if (selektovanRed == -1) {
					JOptionPane.showMessageDialog(null, "Morate selektovati red");
				} else {
					String id = (String) tabela.getValueAt(selektovanRed, 0);
					Voznja voznja = Pretraga.pronadjiVoznju(taksiSluzba, id);
					if (voznja != null) {
						if (taksiSluzba.getUlogovan() instanceof Dispecer && ((Dispecer) taksiSluzba.getUlogovan())
								.getOdeljenjeNaPoslu() == Odeljenje.REKLAMACIJE) {
							DodavanjeIzmenaDispecer diD1 = new DodavanjeIzmenaDispecer(taksiSluzba, voznja,
									PrikazVoznjiDispecer.this, TipObrade.IZMENA);
							diD1.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Nemate pristup zeljenoj funkciji.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Greska prilikom pretrage.");
					}
				}
			}
		});

		btnTaxi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nemate pristup datoj funkciji.");
			}
		});
	}
}