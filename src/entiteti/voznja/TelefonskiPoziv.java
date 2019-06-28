package entiteti.osoba;

public abstract class Zaposleni extends Osoba {

	protected double plata;

	public Zaposleni() {
		super();
	}

	public Zaposleni(String ime, String prezime, String jMBG, String adresa, Pol pol, String brojTelefona,
			String korisnickoIme, String lozinka, double plata) {
		super(ime, prezime, jMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

}
