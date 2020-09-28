package poslasticarnica;

import java.util.ArrayList;

import poslasticarnica.model.Korisnik;
import poslasticarnica.model.Sastojak;
import poslasticarnica.model.SlatkaPoruka;
import poslasticarnica.model.Slatkis;
import poslasticarnica.model.Ukras;
import poslasticarnica.model.VrstaSastojka;

public class Poslasticarnica {

	static ArrayList<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
	static ArrayList<VrstaSastojka> sveVrste = new ArrayList<VrstaSastojka>();
	static ArrayList<Slatkis> sviSlatkisi = new ArrayList<Slatkis>();

	public static Slatkis proveraSastojaka(String sifra) {
		for (Slatkis slatkis : sviSlatkisi) {
			if (slatkis instanceof Sastojak || slatkis instanceof Ukras || slatkis instanceof SlatkaPoruka) {
				if (slatkis.getSifra().equalsIgnoreCase(sifra)) {
					return slatkis;
				}
			}
		}
		return null;
	}

	public static Sastojak proveraPostojanjaSastojka(String sifra, boolean trueFalse) {
		Sastojak retVal = null;
		for (Slatkis sastojak : sviSlatkisi) {
			if (sastojak instanceof Sastojak || (sastojak instanceof SlatkaPoruka) || (sastojak instanceof Ukras)) {
				if (sifra.equalsIgnoreCase(sastojak.getSifra()) && sastojak.isPotroseno() == trueFalse)
					retVal = (Sastojak) sastojak;
			}
		}
		return retVal;
	}

	public static Sastojak proveraPostojanjaSastojka(String sifra) {
		Sastojak retVal = null;
		for (Slatkis sastojak : sviSlatkisi) {
			if (sastojak instanceof Sastojak || (sastojak instanceof SlatkaPoruka) || (sastojak instanceof Ukras)) {
				if (sifra.equalsIgnoreCase(sastojak.getSifra()))
					retVal = (Sastojak) sastojak;
			}
		}
		return retVal;
	}

	public static VrstaSastojka pretragaVrstePoSifri(String sifra) {
		VrstaSastojka retVal = null;
		for (int i = 0; i < sveVrste.size(); i++) {
			VrstaSastojka vs = sveVrste.get(i);
			if (sifra.equals(vs.getSifra())) {
				retVal = vs;
				break;
			}
		}
		return retVal;
	}
}
