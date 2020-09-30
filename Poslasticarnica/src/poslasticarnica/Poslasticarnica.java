package poslasticarnica;

import java.util.ArrayList;
import java.util.Collections;

import poslasticarnica.fajlovi.KorisnikFajl;
import poslasticarnica.fajlovi.NapravljenSlatkisFajl;
import poslasticarnica.fajlovi.SastojakFajl;
import poslasticarnica.fajlovi.SlatkaPorukaFajl;
import poslasticarnica.fajlovi.UkrasFajl;
import poslasticarnica.fajlovi.VrstaSastojkaFajl;
import poslasticarnica.model.Korisnik;
import poslasticarnica.model.NapravljenSlatkis;
import poslasticarnica.model.Sastojak;
import poslasticarnica.model.SlatkaPoruka;
import poslasticarnica.model.Slatkis;
import poslasticarnica.model.Ukras;
import poslasticarnica.model.VrstaSastojka;
import poslasticarnica.pomocne_klase.PomocnaKlasa;
import poslasticarnica.pomocne_klase.Sortiranje;

public class Poslasticarnica {

	static ArrayList<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
	static ArrayList<VrstaSastojka> sveVrste = new ArrayList<VrstaSastojka>();
	static ArrayList<Slatkis> sviSlatkisi = new ArrayList<Slatkis>();

	public static void ocitavanje() {

		sviKorisnici.clear();
		sveVrste.clear();
		sviSlatkisi.clear();

		KorisnikFajl.citanjeIzFajla();
		sviKorisnici.addAll(KorisnikFajl.korisnici);
		VrstaSastojkaFajl.citanjeIzFajla();
		sveVrste.addAll(VrstaSastojkaFajl.vrste);
		SastojakFajl.citanjeIzFajla();
		sviSlatkisi.addAll(SastojakFajl.sastojci);
		SlatkaPorukaFajl.citanjeIzFajla();
		sviSlatkisi.addAll(SlatkaPorukaFajl.poruke);
		UkrasFajl.citanjeIzFajla();
		sviSlatkisi.addAll(UkrasFajl.ukrasi);
		NapravljenSlatkisFajl.citanjeIzFajla();
		sviSlatkisi.addAll(NapravljenSlatkisFajl.napravljeniSlatkisi);

	}

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

	public static Korisnik pretragaKorisnika(String korisnickoIme, String lozinka) {
		for (Korisnik korisnik : sviKorisnici) {
			if (korisnik.getKorisnickoIme().equals(korisnickoIme) && korisnik.getLozinka().equals(lozinka)) {
				return korisnik;
			}
		}
		return null;
	}

	public static Korisnik logovanje() {
		System.out.print("Unesite korisnicko ime: ");
		String korisnickoIme = PomocnaKlasa.ocitavanjeTeksta();
		System.out.print("Unesite lozinku:");
		String lozinka = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaKorisnika(korisnickoIme, lozinka) == null) {
			System.out.println("Korisnicko ime ili lozinka nije ispravna");
			System.out.println("Ukucajte ponovo");
			System.out.print("Korisnicko ime: ");
			korisnickoIme = PomocnaKlasa.ocitavanjeTeksta();
			System.out.print("Lozinka:");
			lozinka = PomocnaKlasa.ocitavanjeTeksta();
		}
		Korisnik korisnik = pretragaKorisnika(korisnickoIme, lozinka);
		return korisnik;
	}

	public static void cuvanjeKorisnika() {
		KorisnikFajl.korisnici.clear();
		for (Korisnik korisnik : sviKorisnici) {
			KorisnikFajl.korisnici.add((Korisnik) korisnik);
		}
		KorisnikFajl.pisanjeUFajl();
	}

	public static void cuvanjeSlatkisa() {
		SastojakFajl.sastojci.clear();
		NapravljenSlatkisFajl.napravljeniSlatkisi.clear();
		SlatkaPorukaFajl.poruke.clear();
		UkrasFajl.ukrasi.clear();
		for (Slatkis slatkis : sviSlatkisi) {
			if (slatkis instanceof Sastojak && !(slatkis instanceof SlatkaPoruka) && !(slatkis instanceof Ukras)) {
				SastojakFajl.sastojci.add((Sastojak) slatkis);
			}
			if (slatkis instanceof NapravljenSlatkis) {
				NapravljenSlatkisFajl.napravljeniSlatkisi.add((NapravljenSlatkis) slatkis);
			}
			if (slatkis instanceof SlatkaPoruka) {
				SlatkaPorukaFajl.poruke.add((SlatkaPoruka) slatkis);
			}
			if (slatkis instanceof Ukras) {
				UkrasFajl.ukrasi.add((Ukras) slatkis);
			}
		}
		SastojakFajl.pisanjeUFajl();
		SlatkaPorukaFajl.pisanjeUFajl();
		UkrasFajl.pisanjeUFajl();
		NapravljenSlatkisFajl.pisanjeUFajl();
	}

	public static Slatkis pretragaSlatkisaPoSifri(String sifra) {
		Slatkis retVal = null;
		for (int i = 0; i < sviSlatkisi.size(); i++) {
			Slatkis slatkis = sviSlatkisi.get(i);
			if (slatkis.getSifra().equalsIgnoreCase(sifra)) {
				retVal = slatkis;
				break;
			}
		}
		return retVal;
	}

	public static void pretragaSlatkisaPoSifri() {
		Slatkis retVal = null;
		System.out.println("Unesite sifru: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		retVal = pretragaSlatkisaPoSifri(sifra);
		if (retVal == null) {
			System.out.println("Slatkis sa datom sifrom " + sifra + " ne postoji");
		} else {
			System.out.println(retVal);
		}
	}

	public static ArrayList<Slatkis> pretragaSlatkisaPoNazivu(String naziv) {
		ArrayList<Slatkis> lista = new ArrayList<Slatkis>();
		for (Slatkis s : sviSlatkisi) {
			if ((s.getNaziv().toLowerCase()).contains(naziv.toLowerCase())) {
				lista.add(s);
			}
		}
		return lista;
	}

	public static void pretragaSlatkisaPoNazivu() {
		ArrayList<Slatkis> retVal = new ArrayList<Slatkis>();
		System.out.println("Unesite naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		retVal = pretragaSlatkisaPoNazivu(naziv);
		if (retVal.isEmpty()) {
			System.out.println("Ne postoji slatkis sa takvom kljucnom reci");
		} else {
			for (Slatkis slatkis : retVal) {
				System.out.println(slatkis);
			}
		}
	}

	public static ArrayList<Slatkis> pretragaSlatkisaPoOpseguCene(double pocetnaCena, double krajnaCena) {
		ArrayList<Slatkis> lista = new ArrayList<Slatkis>();
		for (Slatkis slatkis : sviSlatkisi) {
			if (slatkis.getCena() >= pocetnaCena && slatkis.getCena() <= krajnaCena) {
				lista.add(slatkis);
			}
		}
		return lista;
	}

	public static void pretragaSlatkisaPoOpseguCene() {
		ArrayList<Slatkis> lista = null;
		System.out.println("Unesite opseg cene");
		System.out.println("Od: ");
		Double najniza = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Do: ");
		Double najvisa = PomocnaKlasa.ocitavanjeRealnogBroja();
		lista = pretragaSlatkisaPoOpseguCene(najniza, najvisa);
		if (lista.size() == 0) {
			System.out.println("Slatkisi sa datim opsegom cena ne postoje");
		} else {
			for (Slatkis slatkis : lista) {
				System.out.println(slatkis);
			}
		}
	}

	public static void ispisSlatkisa() {
		for (Slatkis slatkis : sviSlatkisi) {
			System.out.println(slatkis);
		}
	}

	public static void ispisSlatkisa(boolean trueFalse) {
		for (int i = 0; i < sviSlatkisi.size(); i++) {
			Slatkis slatkis = sviSlatkisi.get(i);
			if (slatkis.isPotroseno() == trueFalse) {
				System.out.println(slatkis);
			}

		}
	}

	@SuppressWarnings("unchecked")
	static void sortiranjeSlatkisaPoNazivu(int pravac) {
		Collections.sort(sviSlatkisi, new Sortiranje(pravac));
		for (Slatkis slatkis : sviSlatkisi) {
			System.out.println(slatkis);
		}
	}

	static void sortiranjeSlatkisaPoCeni(int pravac) {
		Slatkis temp;
		int min;
		for (int i = 0; i < sviSlatkisi.size() - 1; ++i) {
			min = i;
			for (int j = i + 1; j < sviSlatkisi.size(); ++j) {
				if (pravac * sviSlatkisi.get(min).uporediCenu(sviSlatkisi.get(j)) > 0) {
					min = j;
				}
			}
			temp = sviSlatkisi.get(min);
			sviSlatkisi.set(min, sviSlatkisi.get(i));
			sviSlatkisi.set(i, temp);
		}
		ispisSlatkisa();
	}

	static void pretragaSlatkisa() {
		Meniji.podMeniZaPretraguSlatkisa();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			pretragaSlatkisaPoSifri();
			break;
		case "2":
			pretragaSlatkisaPoNazivu();

			break;
		case "3":
			pretragaSlatkisaPoOpseguCene();
			break;
		case "0":
			break;
		}
	}

	static void pretraga() {
		Meniji.podMeniZaPretragu();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			pretragaSlatkisa();
			break;
		case "2":
			// pretragaSastojaka();
			break;
		case "3":
			// pretragaNapravljenihSlatkisa();
			break;
		case "4":
			// pretragaVrstaSastojaka();
			break;
		case "5":
			ispisSlatkisa();
			break;
		case "6":
			ispisSlatkisa(false);
			break;
		case "7":
			ispisSlatkisa(true);
			break;
		case "0":
			break;
		}
	}

	public static ArrayList<Korisnik> getSviKorisnici() {
		return sviKorisnici;
	}

	public static void setSviKorisnici(ArrayList<Korisnik> korisnici) {
		sviKorisnici = korisnici;
	}

	public static ArrayList<VrstaSastojka> getSveVrste() {
		return sveVrste;
	}

	public static void setSveVrste(ArrayList<VrstaSastojka> vrste) {
		sveVrste = vrste;
	}

	public static ArrayList<Slatkis> getSviSlatkisi() {
		return sviSlatkisi;
	}

	public static void setSviSlatkisi(ArrayList<Slatkis> slatkisi) {
		sviSlatkisi = slatkisi;
	}

	public static void main(String[] args) {
		System.out.println("-----------------------------------");
		System.out.println("         POSLASTICARNICA           ");
		System.out.println("-----------------------------------");
		
		ocitavanje();
		String odluka = " ";

		Korisnik ulogovanKorisnik = logovanje();
		System.out.println("Dobro dosli " + ulogovanKorisnik.getIme() + " !!!");

		while (!odluka.equals("0")) {
			Meniji.pocetniMeni();
			odluka = PomocnaKlasa.ocitavanjeTeksta();

			switch (odluka) {
			case "1":
				// unos();
				break;
			case "2":
				// izmena();
				break;
			case "3":
				// brisanje();
				break;
			case "4":
				pretraga();
				break;
			case "5":
				// sortiranje();
				break;
			case "6":
				break;
			case "7":
				cuvanjeSlatkisa();
				// cuvanjeVrste();
				break;
			case "8":
				ocitavanje();
				break;
			case "0":
				break;

			}
		}
		cuvanjeSlatkisa();
		// cuvanjeVrste();
	}
}
