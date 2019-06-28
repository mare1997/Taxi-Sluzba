package entiteti.automobil;

import java.util.ArrayList;

import entiteti.osoba.Vozac;

public class Automobil {

	private String model;
	private String proizvodjac;
	private int godinaProizvodnje;
	private String brojRegistracije;
	private int brojVozila;
	private Vrsta vrstaAutomobila;
	private ArrayList<Vozac> listaVozaca;

	public Automobil() {
		super();
		this.listaVozaca = new ArrayList<Vozac>();
	}

	public Automobil(String model, String proizvodjac, int godinaProizvodnje, String brojRegistracije, int brojVozila,
			Vrsta vrstaAutomobila) {
		super();
		this.model = model;
		this.proizvodjac = proizvodjac;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brojRegistracije = brojRegistracije;
		this.brojVozila = brojVozila;
		this.vrstaAutomobila = vrstaAutomobila;
		this.listaVozaca = new ArrayList<Vozac>();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public String getBrojRegistracije() {
		return brojRegistracije;
	}

	public void setBrojRegistracije(String brojRegistracije) {
		this.brojRegistracije = brojRegistracije;
	}

	public int getBrojVozila() {
		return brojVozila;
	}

	public void setBrojVozila(int brojVozila) {
		this.brojVozila = brojVozila;
	}

	public Vrsta getVrstaAutomobila() {
		return vrstaAutomobila;
	}

	public void setVrstaAutomobila(Vrsta vrstaAutomobila) {
		this.vrstaAutomobila = vrstaAutomobila;
	}

	public ArrayList<Vozac> getListaVozaca() {
		return listaVozaca;
	}

}
