package entiteti.voznja;

import java.util.Date;

import entiteti.osoba.Musterija;
import entiteti.osoba.Vozac;

public class MobilnaAplikacija extends Voznja {

	private String koordinate;
	private String napomena;

	public MobilnaAplikacija() {
		super();
	}

	public MobilnaAplikacija(int id, Date datumVreme, String adresaPolaska, Musterija musterija, Vozac vozac, Double km,
			Double cena, String koordinate, String napomena) {
		super(id, datumVreme, adresaPolaska, musterija, vozac, km, cena);
		this.koordinate = koordinate;
		this.napomena = napomena;
	}

	public String getKoordinate() {
		return koordinate;
	}

	public void setKoordinate(String koordinate) {
		this.koordinate = koordinate;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

}
