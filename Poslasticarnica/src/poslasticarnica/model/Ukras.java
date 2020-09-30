package poslasticarnica.model;

import poslasticarnica.Poslasticarnica;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class Ukras extends Sastojak {

	protected int brojFigurica;

	public Ukras() {

	}

	public Ukras(String sifra, String naziv, String opis, double cena, double kolicina, boolean potroseno,
			VrstaSastojka vrsta, int brojFigurica) {
		super(sifra, naziv, opis, cena, kolicina, potroseno, vrsta);
		this.brojFigurica = brojFigurica;
	}

	public Ukras(String tekst) {
		super();
		String tokeni[] = tekst.split("\\|");

		if (tokeni.length != 8) {
			System.out.println("Greska pri ocitavanju ukrasa");
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
		if (PomocnaKlasa.isInteger(tokeni[7])) {
			brojFigurica = Integer.parseInt(tokeni[7]);
		}
		vrsta = Poslasticarnica.pretragaVrstePoSifri(tokeni[6]);
	}

	public int getBrojFigurica() {
		return brojFigurica;
	}

	public void setBrojFigurica(int brojFigurica) {
		this.brojFigurica = brojFigurica;
	}

	@Override
	public String toString() {
		String retVal = super.toString();
		return "Ukras: " + retVal + " ; Broj figurica: " + brojFigurica;
	}

	@Override
	public String zaFajl() {
		String retVal = super.zaFajl();
		return retVal + "|" + brojFigurica;
	}
}
