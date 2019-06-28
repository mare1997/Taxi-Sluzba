package gui.prikaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import entiteti.osoba.Musterija;
import entiteti.osoba.Vozac;
import entiteti.voznja.MobilnaAplikacija;
import entiteti.voznja.TelefonskiPoziv;
import entiteti.voznja.Voznja;
import taksi_sluzba.TaksiSluzba;
import utils.Tools;

public class PrikazVoznji extends PrikazPoruciti {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaksiSluzba taksiSluzba;

	public PrikazVoznji(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		setTitle("Prikaz voznji");
		popuniTabelu();
	}

	protected void popuniTabelu() {
		String[] zaglavlje = { "ID", "Datum i vreme porudzbine", "Adresa polaska", "Musterija", "Vozac", "Dispecer",
				"Koordinate", "Napomena", "Kilometraza", "Cena" };
		ArrayList<Voznja> voznje = new ArrayList<>();
		voznje.addAll(taksiSluzba.getMobilneAplikacije());
		voznje.addAll(taksiSluzba.getPozivi());

		if (taksiSluzba.getUlogovan() instanceof Vozac) {
			ArrayList<Voznja> temp = new ArrayList<Voznja>();
			temp.addAll(voznje);
			for (Voznja voznja : temp) {
				if ((voznja.getVozac() != null && voznja.getVozac() != (Vozac) taksiSluzba.getUlogovan())
						|| voznja instanceof TelefonskiPoziv) {
					voznje.remove(voznja);
				}
			}
		}

		if (taksiSluzba.getUlogovan() instanceof Musterija) {
			voznje.clear();
			for (Voznja voznja : taksiSluzba.getMobilneAplikacije()) {
				if (voznja.getMusterija() == (Musterija) taksiSluzba.getUlogovan()) {
					voznje.add(voznja);
				}
			}
		}

		Object[][] podaci = new Object[voznje.size()][zaglavlje.length];
		for (int i = 0; i < voznje.size(); i++) {
			Voznja voznja = voznje.get(i);
			podaci[i][0] = String.valueOf(voznja.getId());
			podaci[i][1] = Tools.formatDate(voznja.getDatumVreme());
			podaci[i][2] = voznja.getAdresaPolaska();
			if (voznja.getMusterija() != null) {
				podaci[i][3] = voznja.getMusterija().getKorisnickoIme();
			} else {
				podaci[i][3] = "Musterija obrisana!";
			}
			if (voznja.getVozac() != null) {
				podaci[i][4] = voznja.getVozac().getKorisnickoIme();
			} else {
				podaci[i][4] = "";
			}
			if (voznja instanceof TelefonskiPoziv) {
				podaci[i][5] = ((TelefonskiPoziv) voznja).getDispecer().getKorisnickoIme();
				podaci[i][6] = "";
				podaci[i][7] = "";
			}
			if (voznja instanceof MobilnaAplikacija) {
				podaci[i][5] = "";
				podaci[i][6] = ((MobilnaAplikacija) voznja).getKoordinate();
				podaci[i][7] = ((MobilnaAplikacija) voznja).getNapomena();
			}
			podaci[i][8] = String.valueOf(voznja.getKilometraza());
			podaci[i][9] = String.valueOf(voznja.getCena());
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
}
