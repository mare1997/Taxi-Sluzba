package entiteti.osoba;

public class Musterija extends Osoba {

	private Boolean mobapp;

	public Musterija() {
		super();
	}

	public Musterija(String ime, String prezime, String JMBG, String adresa, Pol pol, String brojTelefona,
			String korisnickoIme, String lozinka, Boolean mobapp) {
		super(ime, prezime, JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka);
		this.mobapp = mobapp;
	}

	public Boolean getMobapp() {
		return mobapp;
	}

	public void setMobapp(Boolean mobapp) {
		this.mobapp = mobapp;
	}

}
