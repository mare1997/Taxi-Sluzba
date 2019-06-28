package taksi_sluzba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import entiteti.automobil.Automobil;
import entiteti.osoba.Dispecer;
import entiteti.osoba.Musterija;
import entiteti.osoba.Osoba;
import entiteti.osoba.Vozac;
import entiteti.voznja.MobilnaAplikacija;
import entiteti.voznja.TelefonskiPoziv;
import utils.Ucitavanje;

public class TaksiSluzba {

	private int pib;
	private String adresa;
	private String naziv;
	private Osoba ulogovan;
	private ArrayList<Dispecer> dispeceri = new ArrayList<Dispecer>();
	private ArrayList<Vozac> vozaci = new ArrayList<Vozac>();
	private ArrayList<Musterija> musterije = new ArrayList<Musterija>();
	private ArrayList<Automobil> automobili = new ArrayList<Automobil>();
	private ArrayList<MobilnaAplikacija> mobilneAplikacije = new ArrayList<MobilnaAplikacija>();
	private ArrayList<TelefonskiPoziv> pozivi = new ArrayList<TelefonskiPoziv>();

	public TaksiSluzba() {
		super();
		initTaksiSluzba();
	}

	public int getPib() {
		return pib;
	}

	public void setPib(int pib) {
		this.pib = pib;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Osoba getUlogovan() {
		return ulogovan;
	}

	public void setUlogovan(Osoba ulogovan) {
		this.ulogovan = ulogovan;
	}

	public ArrayList<Dispecer> getDispeceri() {
		return dispeceri;
	}

	public void setDispeceri(ArrayList<Dispecer> dispeceri) {
		this.dispeceri = dispeceri;
	}

	public ArrayList<Vozac> getVozaci() {
		return vozaci;
	}

	public void setVozaci(ArrayList<Vozac> vozaci) {
		this.vozaci = vozaci;
	}

	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}

	public void setMusterije(ArrayList<Musterija> musterije) {
		this.musterije = musterije;
	}

	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}

	public ArrayList<MobilnaAplikacija> getMobilneAplikacije() {
		return mobilneAplikacije;
	}

	public void setMobilneAplikacije(ArrayList<MobilnaAplikacija> mobilneAplikacije) {
		this.mobilneAplikacije = mobilneAplikacije;
	}

	public ArrayList<TelefonskiPoziv> getPozivi() {
		return pozivi;
	}

	public void setPozivi(ArrayList<TelefonskiPoziv> pozivi) {
		this.pozivi = pozivi;
	}

	public void initTaksiSluzba() {
		try {
			File taksiSluzbaFile = new File("src/fajlovi/taksiSluzba.txt");
			BufferedReader x = new BufferedReader(new FileReader(taksiSluzbaFile));
			String line = null;
			while ((line = x.readLine()) != null) {
				String[] split = line.split("\\|");
				int pib = Integer.parseInt(split[0]);
				String naziv = split[1];
				String adresa = split[2];
				this.adresa = adresa;
				this.pib = pib;
				this.naziv = naziv;
			}

			x.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setMusterije(Ucitavanje.ucitajMusterije());
		this.setDispeceri(Ucitavanje.ucitajDispecere());
		this.setAutomobili(Ucitavanje.ucitajAutomobile());
		this.setVozaci(Ucitavanje.ucitajVozace(this));
		this.setMobilneAplikacije(Ucitavanje.ucitajMobilneAplikacije(this));
		this.setPozivi(Ucitavanje.ucitajTelefonskePozive(this));

	}

}
