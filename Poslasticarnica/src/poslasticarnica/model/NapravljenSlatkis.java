package poslasticarnica.model;

import java.util.ArrayList;

import poslasticarnica.Poslasticarnica;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class NapravljenSlatkis extends Slatkis {

	public ArrayList<Sastojak> sastojci;

	public NapravljenSlatkis() {
	}

	public NapravljenSlatkis(String sifra, String naziv, String opis, double cena, double kolicina, boolean potroseno,
			ArrayList<Sastojak> sastojci) {
		super(sifra, naziv, opis, cena, kolicina, potroseno);
		this.sastojci = sastojci;
	}

	public NapravljenSlatkis(String tekst) {
		String[] tokeni = tekst.split("\\|");

		if (tokeni.length != 7) {
			System.out.println("Greska pri ocitavanju!");
		}

		this.sifra = tokeni[0];
		this.naziv = tokeni[1];
		this.opis = tokeni[2];

		if (PomocnaKlasa.isDouble(tokeni[3])) {
			this.cena = Double.parseDouble(tokeni[3]);
		}
		if (PomocnaKlasa.isDouble(tokeni[4])) {
			this.kolicina = Double.parseDouble(tokeni[4]);
		}
		if (PomocnaKlasa.isBoolean(tokeni[5])) {
			this.potroseno = Boolean.parseBoolean(tokeni[5]);
		}
		this.sastojci = new ArrayList<Sastojak>();
		Sastojak s = null;
		String[] sifreSastojaka = tokeni[6].split("\\;");
		for (int i = 0; i < sifreSastojaka.length; i++) {
			s = Poslasticarnica.proveraPostojanjaSastojka(sifreSastojaka[i]);
			if (s == null) {
				System.out.println("Sastojak ne postoji");
				System.exit(0);
			}
			sastojci.add(s);
		}
	}

	public ArrayList<Sastojak> getSastojci() {
		return sastojci;
	}

	public void setSastojci(ArrayList<Sastojak> sastojci) {
		this.sastojci = sastojci;
	}

	@Override
	public String zaFajl() {
		String retVal = super.zaFajl();
		String sastojak = "";
		for (int i = 0; i < sastojci.size(); i++) {
			Sastojak s = sastojci.get(i);
			if (s == null) {
				System.out.println("Greska!");
			}
			sastojak += s.getSifra() + ";";

		}
		return retVal + "|" + sastojak;
	}

	@Override
	public String toString() {
		String nazivi = "";
		for (Sastojak s : sastojci) {
			nazivi += s.getNaziv() + ", ";
		}
		String retVal = super.toString();
		return "Napravljen slatkis: " + retVal + " Sastojci: " + nazivi;
	}

}