package poslasticarnica.model;

public abstract class Slatkis {

	protected String sifra;
	protected String naziv;
	protected String opis;
	protected double cena;
	protected double kolicina;
	protected boolean potroseno;

	public Slatkis() {
		potroseno = false;
	}

	public Slatkis(String sifra, String naziv, String opis, double cena, double kolicina, boolean potroseno) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.kolicina = kolicina;
		this.potroseno = potroseno;
	}

	public Slatkis(Slatkis slatkis) {
		this.sifra = slatkis.sifra;
		this.naziv = slatkis.naziv;
		this.opis = slatkis.opis;
		this.cena = slatkis.cena;
		this.kolicina = slatkis.kolicina;
		potroseno = false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slatkis other = (Slatkis) obj;
		if (sifra == null) {
			if (other.sifra != null)
				return false;
		} else if (!sifra.equals(other.sifra))
			return false;
		return true;
	}

	public double uporediCenu(Slatkis s) {
		return this.cena - s.cena;
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public boolean isPotroseno() {
		return potroseno;
	}

	public void setPotroseno(boolean potroseno) {
		this.potroseno = potroseno;
	}

	public String zaFajl() {
		return sifra + "|" + naziv + "|" + opis + "|" + cena + "|" + kolicina + "|" + potroseno;
	}

	@Override
	public String toString() {
		return "Sifra: " + sifra + "; Naziv: " + naziv + "; Opis: " + opis + "; Cena: " + cena + "; Kolicina: "
				+ kolicina + "; (" + ((potroseno == false) ? "ima u rezervi" : "potroseno") + ") ";
	}

}