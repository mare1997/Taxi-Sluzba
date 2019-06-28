package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import entiteti.automobil.Automobil;
import entiteti.automobil.Vrsta;
import entiteti.osoba.Dispecer;
import entiteti.osoba.Musterija;
import entiteti.osoba.Odeljenje;
import entiteti.osoba.Pol;
import entiteti.osoba.Vozac;
import entiteti.voznja.MobilnaAplikacija;
import entiteti.voznja.TelefonskiPoziv;
import taksi_sluzba.TaksiSluzba;

public class Snimanje {

	public static void snimiMusterije(TaksiSluzba ts) {
		try {
			File musterijeFile = new File("src/fajlovi/musterije.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(musterijeFile));
			String x = "";
			for (Musterija musterija : ts.getMusterije()) {
				x += musterija.getIme() + "|" + musterija.getPrezime() + "|" + musterija.getJMBG() + "|"
						+ musterija.getAdresa() + "|" + Pol.toInt(musterija.getPol()) + "|"
						+ musterija.getBrojTelefona() + "|" + musterija.getKorisnickoIme() + "|"
						+ musterija.getLozinka() + "|" + musterija.getMobapp() + "\n";

			}
			writer.write(x);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void snimiVozace(TaksiSluzba ts) {
		try {
			File vozaciFile = new File("src/fajlovi/vozaci.txt");
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(vozaciFile));
			String x1 = "";
			for (Vozac vozac : ts.getVozaci()) {
				x1 += vozac.getIme() + "|" + vozac.getPrezime() + "|" + vozac.getJMBG() + "|" + vozac.getAdresa() + "|"
						+ Pol.toInt(vozac.getPol()) + "|" + vozac.getBrojTelefona() + "|" + vozac.getKorisnickoIme()
						+ "|" + vozac.getLozinka() + "|" + vozac.getPlata() + "|" + vozac.getBrojClanskeKarte() + "|";
				if (vozac.getAutomobil() == null) {
					x1 += "null \n";
				} else {
					x1 += vozac.getAutomobil().getBrojVozila() + "\n";
				}

			}
			writer1.write(x1);
			writer1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snimiDispecere(TaksiSluzba ts) {
		try {
			File dispeceriFile = new File("src/fajlovi/dispeceri.txt");
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(dispeceriFile));
			String x2 = "";
			for (Dispecer dispecer : ts.getDispeceri()) {
				x2 += dispecer.getIme() + "|" + dispecer.getPrezime() + "|" + dispecer.getJMBG() + "|"
						+ dispecer.getAdresa() + "|" + Pol.toInt(dispecer.getPol()) + "|" + dispecer.getBrojTelefona()
						+ "|" + dispecer.getKorisnickoIme() + "|" + dispecer.getLozinka() + "|" + dispecer.getPlata()
						+ "|" + dispecer.getBrojTelLinije() + "|" + Odeljenje.toInt(dispecer.getOdeljenjeNaPoslu())
						+ "\n";

			}
			writer2.write(x2);
			writer2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snimiAutomobile(TaksiSluzba ts) {
		try {
			File automobiliFile = new File("src/fajlovi/automobili.txt");
			BufferedWriter writer3 = new BufferedWriter(new FileWriter(automobiliFile));
			String x3 = "";
			for (Automobil automobil : ts.getAutomobili()) {
				x3 += automobil.getModel() + "|" + automobil.getProizvodjac() + "|" + automobil.getGodinaProizvodnje()
						+ "|" + automobil.getBrojRegistracije() + "|" + automobil.getBrojVozila() + "|"
						+ Vrsta.toInt(automobil.getVrstaAutomobila()) + "\n";
			}
			writer3.write(x3);
			writer3.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snimiTelefonskiPoziv(TaksiSluzba ts) {
		try {
			File poziviFile = new File("src/fajlovi/pozivi.txt");
			BufferedWriter writer4 = new BufferedWriter(new FileWriter(poziviFile));
			String x4 = "";
			for (TelefonskiPoziv telefonskiPoziv : ts.getPozivi()) {
				if (telefonskiPoziv != null) {
					x4 += telefonskiPoziv.getId() + "|" + Tools.formatDate(telefonskiPoziv.getDatumVreme()) + "|"
							+ telefonskiPoziv.getAdresaPolaska() + "|" + telefonskiPoziv.getMusterija().getJMBG() + "|";
					if (telefonskiPoziv.getVozac() == null) {
						x4 += "null|";
					} else {
						x4 += telefonskiPoziv.getVozac().getJMBG() + "|";
					}
					x4 += telefonskiPoziv.getDispecer().getJMBG() + "|" + telefonskiPoziv.getKilometraza() + "|"
							+ telefonskiPoziv.getCena() + "\n";
				}
			}
			writer4.write(x4);
			writer4.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snimiMobilnuAplikaciju(TaksiSluzba ts) {
		try {
			File mobilneAplikacijeFile = new File("src/fajlovi/mobilneAplikacije.txt");
			BufferedWriter writer5 = new BufferedWriter(new FileWriter(mobilneAplikacijeFile));
			String x5 = "";
			for (MobilnaAplikacija mobilnaAplikacija : ts.getMobilneAplikacije()) {
				x5 += mobilnaAplikacija.getId() + "|" + Tools.formatDate(mobilnaAplikacija.getDatumVreme()) + "|"
						+ mobilnaAplikacija.getAdresaPolaska() + "|" + mobilnaAplikacija.getMusterija().getJMBG() + "|";
				if (mobilnaAplikacija.getVozac() == null) {
					x5 += "null|";
				} else {
					x5 += mobilnaAplikacija.getVozac().getJMBG() + "|";
				}
				x5 += mobilnaAplikacija.getKoordinate() + "|" + mobilnaAplikacija.getNapomena() + "|"
						+ mobilnaAplikacija.getKilometraza() + "|" + mobilnaAplikacija.getCena() + "\n";
			}
			writer5.write(x5);
			writer5.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snimiTaksiSluzbu(TaksiSluzba ts) {
		try {
			File taksiSluzbaFile = new File("src/fajlovi/taksiSluzba.txt");
			BufferedWriter writer6 = new BufferedWriter(new FileWriter(taksiSluzbaFile));
			String x6 = ts.getPib() + "|" + ts.getNaziv() + "|" + ts.getAdresa();

			writer6.write(x6);
			writer6.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snimiSve(TaksiSluzba ts) {
		snimiAutomobile(ts);
		snimiDispecere(ts);
		snimiMobilnuAplikaciju(ts);
		snimiMusterije(ts);
		snimiTaksiSluzbu(ts);
		snimiTelefonskiPoziv(ts);
		snimiVozace(ts);
	}

}
