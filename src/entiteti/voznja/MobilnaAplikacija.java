package entiteti.osoba;

import java.util.ArrayList;

import entiteti.automobil.Automobil;
import entiteti.voznja.Voznja;

public class Vozac extends Zaposleni {

	private String brojClanskeKarte;
	private Automobil automobil;
	private ArrayList<Voznja> listaVoznji;

	public Vozac() {
		super();
		this.listaVoznji = new ArrayList<Voznja>();
	}

	public Vozac(String ime, String prezime, String jMBG, String adresa, Pol pol, String brojTelefona,
			String korisnickoIme, String lozinka, double plata, String brClanske, Automobil automobil) {
		super(ime, prezime, jMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka, plata);
		this.brojClanskeKarte = brClanske;
		this.automobil = automobil;
		this.listaVoznji = new ArrayList<Voznja>();

	}

	public String getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(String brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public ArrayList<Voznja> getListaVoznji() {
		return listaVoznji;
	}

	public void setListaVoznji(ArrayList<Voznja> listaVoznji) {
		this.listaVoznji = listaVoznji;
	}

	public void addVoznja(Voznja voznja) {
		this.listaVoznji.add(voznja);
	}

}
