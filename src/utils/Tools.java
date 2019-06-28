package utils;

import java.util.ArrayList;

import entiteti.automobil.Automobil;
import entiteti.osoba.Dispecer;
import entiteti.osoba.Musterija;
import entiteti.osoba.Vozac;
import entiteti.voznja.Voznja;
import taksi_sluzba.TaksiSluzba;

public class Pretraga {

	public static Musterija pronadjiMusterijuPoJMBG(TaksiSluzba ts, String param) {
		Musterija x = null;
		for (Musterija musterija : ts.getMusterije()) {
			if (musterija.getJMBG().equals(param)) {
				x = musterija;
			}

		}
		return x;
	}

	public static Musterija pronadjiMusterijuPoKorIm(TaksiSluzba ts, String param) {
		Musterija m = null;
		for (Musterija musterija : ts.getMusterije()) {
			if (musterija.getKorisnickoIme().equals(param)) {
				m = musterija;
			}
		}
		return m;
	}

	public static Dispecer pronadjiDispeceraPoJMBG(TaksiSluzba ts, String param) {
		Dispecer x = null;
		for (Dispecer dispecer : ts.getDispeceri()) {
			if (dispecer.getJMBG().equals(param)) {
				x = dispecer;
			}

		}
		return x;
	}

	public static Vozac pronadjiVozacaPoKorIm(TaksiSluzba ts, String param) {
		Vozac retVal = null;
		for (Vozac vozac : ts.getVozaci()) {
			if (vozac.getKorisnickoIme().equals(param)) {
				retVal = vozac;
				break;
			}
		}

		return retVal;
	}

	public static Vozac pronadjiVozacaPoJMBG(TaksiSluzba ts, String param) {
		Vozac x = null;
		for (Vozac vozac : ts.getVozaci()) {
			if (vozac.getJMBG().equals(param)) {
				x = vozac;
			}

		}
		return x;
	}

	public static Automobil pronadjiAutomobilPoBrVozila(TaksiSluzba ts, String param) {
		Automobil x = null;
		for (Automobil automobil : ts.getAutomobili()) {
			if (String.valueOf(automobil.getBrojVozila()).equals(param)) {
				x = automobil;
			}

		}
		return x;
	}

	public static Voznja pronadjiVoznju(TaksiSluzba taksiSluzba, String param) {
		Voznja retVal = null;
		ArrayList<Voznja> voznje = new ArrayList<>();
		voznje.addAll(taksiSluzba.getMobilneAplikacije());
		voznje.addAll(taksiSluzba.getPozivi());
		for (Voznja voznja : voznje) {
			if (String.valueOf(voznja.getId()).equals(param)) {
				retVal = voznja;
				break;
			}
		}

		return retVal;
	}

}
