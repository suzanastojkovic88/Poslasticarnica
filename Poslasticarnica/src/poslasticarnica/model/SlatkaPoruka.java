package poslasticarnica.model;

import poslasticarnica.Poslasticarnica;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class SlatkaPoruka extends Sastojak {

	protected String tekst;

	public SlatkaPoruka() {
		super();
	}

	public SlatkaPoruka(String sifra, String naziv, String opis, double cena, double kolicina, boolean potroseno,
			VrstaSastojka vrsta, String tekst) {
		super(sifra, naziv, opis, cena, kolicina, potroseno, vrsta);
		this.tekst = tekst;
	}

	public SlatkaPoruka(String tekst) {
		super();
		String tokeni[] = tekst.split("\\|");
		if (tokeni.length != 8) {
			System.out.println("Greska pri ocitavanju slatke poruke");

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

		this.tekst = tokeni[7];

	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	@Override
	public String zaFajl() {
		String retVal = super.zaFajl();
		return retVal + "|" + tekst;
	}

	@Override
	public String toString() {
		String retVal = super.toString();
		return "Slatka poruka: " + retVal + " ; Tekst: " + tekst;
	}
}