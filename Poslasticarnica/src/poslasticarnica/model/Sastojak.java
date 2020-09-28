package poslasticarnica.model;

import poslasticarnica.Poslasticarnica;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class Sastojak extends Slatkis {

	protected VrstaSastojka vrsta;

	public Sastojak() {
		super();
	}

	public Sastojak(String sifra, String naziv, String opis, double cena, double kolicina, boolean potroseno,
			VrstaSastojka vrsta) {
		super(sifra, naziv, opis, cena, kolicina, potroseno);
		this.vrsta = vrsta;
	}

	public Sastojak(Sastojak sastojak) {
		super(sastojak);
		this.vrsta = sastojak.vrsta;
	}

	public Sastojak(String tekst) {
		super();
		String[] tokeni = tekst.split("\\|");
		if (tokeni.length != 7) {
			System.out.println("Greska pri ocitavanju!");
			return;
		}

		sifra = tokeni[0];
		naziv = tokeni[1];
		opis = tokeni[2];

		if (PomocnaKlasa.isDouble(tokeni[3])) {
			cena = Double.parseDouble(tokeni[3]);
		}
		if (PomocnaKlasa.isDouble(tokeni[4])) {
			kolicina = Double.parseDouble(tokeni[4]);
		}
		if (PomocnaKlasa.isBoolean(tokeni[5])) {
			potroseno = Boolean.parseBoolean(tokeni[5]);
		}

		vrsta = Poslasticarnica.pretragaVrstePoSifri(tokeni[6]);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public VrstaSastojka getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaSastojka vrsta) {
		this.vrsta = vrsta;
	}

	public String zaFajl() {
		String retVal = sifra + "|" + naziv + "|" + opis + "|" + cena + "|" + kolicina + "|" + potroseno + "|"
				+ vrsta.getSifra();
		return retVal;
	}

	@Override
	public String toString() {
		String retVal = super.toString();
		return retVal + "Vrsta sastojka: " + vrsta.getSifra();

	}
}
