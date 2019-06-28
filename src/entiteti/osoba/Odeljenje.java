package entiteti.osoba;

public class Dispecer extends Zaposleni {

	private int brojTelLinije;
	private Odeljenje odeljenjeNaPoslu;

	public Dispecer() {
		super();
	}

	public Dispecer(String ime, String prezime, String jMBG, String adresa, Pol pol, String brojTelefona,
			String korisnickoIme, String lozinka, double plata, int brojTelLinije, Odeljenje odeljenje) {
		super(ime, prezime, jMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka, plata);
		this.brojTelLinije = brojTelLinije;
		this.odeljenjeNaPoslu = odeljenje;
	}

	public int getBrojTelLinije() {
		return brojTelLinije;
	}

	public void setBrojTelLinije(int brojTelLinije) {
		this.brojTelLinije = brojTelLinije;
	}

	public Odeljenje getOdeljenjeNaPoslu() {
		return odeljenjeNaPoslu;
	}

	public void setOdeljenjeNaPoslu(Odeljenje odeljenjeNaPoslu) {
		this.odeljenjeNaPoslu = odeljenjeNaPoslu;
	}

}
