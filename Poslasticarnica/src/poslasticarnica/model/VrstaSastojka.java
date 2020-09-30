package poslasticarnica.model;

import poslasticarnica.Poslasticarnica;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class VrstaSastojka {

	protected String sifra;
	public String naziv;
	protected String opis;
	protected VrstaSastojka nadVrsta;
	protected boolean potroseno;

	public VrstaSastojka() {
		potroseno = false;
	}

	public VrstaSastojka(String sifra, String naziv, String opis, VrstaSastojka nadVrsta, boolean potroseno) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.opis = opis;
		this.nadVrsta = nadVrsta;
		this.potroseno = potroseno;
	}

	public VrstaSastojka(String tekst) {
		String tokeni[] = tekst.split("\\|");
		if (tokeni.length != 5) {
			System.out.println("Greska pri ocitavanju vrste sastojka");

		}

		sifra = tokeni[0];
		naziv = tokeni[1];
		opis = tokeni[2];
		nadVrsta = Poslasticarnica.pretragaVrstePoSifri(tokeni[3]);

		if (PomocnaKlasa.isBoolean(tokeni[4])) {
			potroseno = Boolean.parseBoolean(tokeni[4]);
		}

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VrstaSastojka other = (VrstaSastojka) obj;
		if (sifra == null) {
			if (other.sifra != null)
				return false;
		} else if (!sifra.equals(other.sifra))
			return false;
		return true;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public VrstaSastojka getNadVrsta() {
		return nadVrsta;
	}

	public void setNadVrsta(VrstaSastojka nadVrsta) {
		this.nadVrsta = nadVrsta;
	}

	public boolean isPotroseno() {
		return potroseno;
	}

	public void setPotroseno(boolean potroseno) {
		this.potroseno = potroseno;
	}

	public String zaFajl() {
		if (nadVrsta == null) {
			return sifra + "|" + naziv + "|" + opis + "|" + null + "|" + potroseno;
		}
		return sifra + "|" + naziv + "|" + opis + "|" + nadVrsta.getSifra() + "|" + potroseno;
	}

	@Override
	public String toString() {
		return naziv;
	}

}
