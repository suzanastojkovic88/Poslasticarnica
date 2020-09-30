package poslasticarnica.model;

public class Korisnik {

	protected String korisnickoIme;
	protected String lozinka;
	protected String ime;
	protected String prezime;

	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Korisnik(String tekst) {
		String tokeni[] = tekst.split("\\|");

		if (tokeni.length != 4) {
			System.out.println("Greska pri ocitavanju korisnika");
		}
		korisnickoIme = tokeni[0];
		lozinka = tokeni[1];
		ime = tokeni[2];
		prezime = tokeni[3];

	}

	public String toString() {
		return ime + " " + prezime;
	}

	public String zaFajl() {
		return korisnickoIme + "|" + lozinka + "|" + ime + "|" + prezime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		return true;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
