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

	// KORISNICI

	public static Korisnik pretragaKorisnika(String korisnickoIme, String lozinka) {
		for (Korisnik korisnik : sviKorisnici) {
			if (korisnik.getKorisnickoIme().equals(korisnickoIme) && korisnik.getLozinka().equals(lozinka)) {
				return korisnik;
			}
		}
		return null;
	}

	public static void cuvanjeKorisnika() {
		KorisnikFajl.korisnici.clear();
		for (Korisnik korisnik : sviKorisnici) {
			KorisnikFajl.korisnici.add((Korisnik) korisnik);
		}
		KorisnikFajl.pisanjeUFajl();
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

//SLATKISI

	public static Slatkis proveraSlatkisa(String sifra) {
		for (Slatkis slatkis : sviSlatkisi) {
			if (slatkis instanceof Sastojak || slatkis instanceof Ukras || slatkis instanceof SlatkaPoruka) {
				if (slatkis.getSifra().equalsIgnoreCase(sifra)) {
					return slatkis;
				}
			}
		}
		return null;
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

	// SASTOJCI

	public static Sastojak pretragaSastojakaPoSifri(String sifra) {
		Sastojak retVal = null;
		for (Slatkis s : sviSlatkisi) {
			if (s instanceof Sastojak || (s instanceof SlatkaPoruka) || (s instanceof Ukras)) {
				if (sifra.equalsIgnoreCase(s.getSifra()))
					retVal = (Sastojak) s;
			}
		}
		return retVal;
	}

	public static Sastojak pretragaSastojakaPoSifri(String sifra, boolean trueFalse) {
		Sastojak retVal = null;
		for (Slatkis s : sviSlatkisi) {
			if (s instanceof Sastojak || (s instanceof SlatkaPoruka) || (s instanceof Ukras)) {
				if (sifra.equalsIgnoreCase(s.getSifra()) && s.isPotroseno() == trueFalse)
					retVal = (Sastojak) s;
			}
		}
		return retVal;
	}

	public static void pretragaSastojakaPoSifri() {
		System.out.println("Unesite sifru: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		Slatkis retVal = proveraSlatkisa(sifra);
		if (retVal == null) {
			System.out.println("Sastojak sa datom sifrom ne postoji");
		} else {
			System.out.println(retVal);
		}

	}

	public static ArrayList<Sastojak> pretragaSastojakaPoNazivu(String naziv) {
		ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
		for (Slatkis sastojak : sviSlatkisi) {

			if ((sastojak.getNaziv().toLowerCase()).contains(naziv.toLowerCase()) && sastojak instanceof Sastojak) {
				sastojci.add((Sastojak) sastojak);
			}
		}
		return sastojci;
	}

	static void pretragaSastojakaPoNazivu() {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		System.out.println("Unesite naziv sastojka: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		lista = pretragaSastojakaPoNazivu(naziv);
		if (lista.isEmpty()) {
			System.out.println("Sastojak sa datim nazivom ne postoji");
		} else {
			for (Sastojak sastojak : lista) {
				System.out.println(sastojak);
			}
		}
	}

	public static ArrayList<Sastojak> pretragaSastojakaPoOpseguCene(double pocetnaCena, double krajnjaCena) {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		for (Slatkis sastojak : sviSlatkisi) {
			if (sastojak.getCena() >= pocetnaCena && sastojak.getCena() <= krajnjaCena
					&& sastojak instanceof Sastojak) {
				lista.add((Sastojak) sastojak);

			}
		}
		return lista;
	}

	public static void pretragaSastojakaPoOpseguCene() {
		ArrayList<Sastojak> retVal = null;
		System.out.println("Unesi opseg cene");
		System.out.println("Od: ");
		Double najniza = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Do: ");
		Double najvisa = PomocnaKlasa.ocitavanjeRealnogBroja();
		retVal = pretragaSastojakaPoOpseguCene(najniza, najvisa);
		if (retVal.size() == 0) {
			System.out.println("Sastojci sa datim opsegom ne postoje");
		} else {
			for (Sastojak sastojak : retVal) {
				System.out.println(sastojak);
			}
		}
	}

	public static ArrayList<Sastojak> pretragaSastojakaPoOpseguKolicine(double pocetnaKolicina,
			double krajnjaKolicina) {
		ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
		for (Slatkis sastojak : sviSlatkisi) {
			if (sastojak.getKolicina() >= pocetnaKolicina && sastojak.getKolicina() <= krajnjaKolicina
					&& sastojak instanceof Sastojak) {
				sastojci.add((Sastojak) sastojak);
			}
		}
		return sastojci;
	}

	public static void pretragaSastojakaPoOpseguKolicine() {
		ArrayList<Sastojak> retVal = null;
		System.out.println("Unesi opseg kolicine");
		System.out.println("Od: ");
		double najmanja = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Do: ");
		double najveca = PomocnaKlasa.ocitavanjeRealnogBroja();
		retVal = pretragaSastojakaPoOpseguKolicine(najmanja, najveca);
		if (retVal.isEmpty()) {
			System.out.println("Sastojci sa datim opsegom ne postoje");
		} else {
			for (Sastojak sastojak : retVal) {
				System.out.println(sastojak);
			}
		}
	}

	public static void pretragaSastojakaPoVrsti() {
		ArrayList<Sastojak> retVal = null;
		System.out.println("Unesi sifru vrste: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		retVal = pretragaSastojakaPoVrsti(sifra);
		if (retVal.isEmpty()) {
			System.out.println("Sastojak pod datom vrstom ne postoje");
		} else {
			for (Sastojak sastojak : retVal) {
				System.out.println(sastojak);
			}
		}
	}

	public static ArrayList<Sastojak> pretragaSastojakaPoVrsti(String sifra) {
		ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
		ArrayList<Sastojak> listaodakleSeBira = new ArrayList<Sastojak>();
		listaodakleSeBira.addAll(SastojakFajl.sastojci);
		listaodakleSeBira.addAll(UkrasFajl.ukrasi);
		listaodakleSeBira.addAll(SlatkaPorukaFajl.poruke);
		for (Sastojak sastojak : listaodakleSeBira) {
			if ((sastojak.getVrsta().getSifra()).equalsIgnoreCase(sifra)) {
				sastojci.add(sastojak);
			}
		}
		return sastojci;
	}

	public static void ispisSastojaka(ArrayList<Sastojak> sastojci) {
		for (int i = 0; i < sastojci.size(); i++) {
			System.out.println(sastojci.get(i));
		}
	}

	public static ArrayList<Sastojak> nepotroseniSastojci() {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		for (int i = 0; i < sviSlatkisi.size(); i++) {
			Slatkis slatkis = sviSlatkisi.get(i);
			if (slatkis instanceof Sastojak) {
				Sastojak sastojak = ((Sastojak) slatkis);
				if (sastojak.isPotroseno() == false) {
					lista.add(sastojak);
				}
			}
		}
		return lista;
	}

	public static void unosNovogSastojka() {
		System.out.println("Unesite sifru:");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaSlatkisaPoSifri(sifra) != null) {
			System.out.println("Slatkis sa indeksom " + sifra + " vec postoji");
			System.out.println("Unesite sifru ponovo");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		System.out.println("Unesite naziv sastojka:");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite opis:");
		String opis = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite cenu:");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Unesite kolicinu:");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Unesite sifru vrste:");
		String vrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(vrsta, false) == null) {
			System.out.println("Ova vrsta ne postoji. Unesite ponovo");
			vrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = VrstaSastojkaFajl.pretragaPoSifri(vrsta, false);
		boolean potroseno = false;
		Sastojak sastojak = new Sastojak(sifra, naziv, opis, cena, kolicina, potroseno, vs);
		sviSlatkisi.add(sastojak);
	}

	public static void izmenaPodatakaOSastojku(Sastojak sastojak) {
		System.out.println("Unesi naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesi opis: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesi novu cenu: ");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Unesi novu kolicinu: ");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();

		ispisVrstaSastojaka(false);
		System.out.println("Izaberite novu vrstu: ");
		String vrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(vrsta, false) == null) {
			ispisVrstaSastojaka(false);
			System.out.println("Vrsta sa sifrom " + vrsta + " ne postoji");
			System.out.println("Unesite ponovo: ");
			vrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = VrstaSastojkaFajl.pretragaPoSifri(vrsta, false);
		sastojak.setNaziv(naziv);
		sastojak.setOpis(opis);
		sastojak.setCena(cena);
		sastojak.setKolicina(kolicina);
		sastojak.setVrsta(vs);
		System.out.println("Izmena uspesno obavljena!");
	}

	public static void izmenaPodatakaOSastojku() {
		SastojakFajl.ispisSastojaka(false);
		System.out.println("Unesite sifru sastojka za izmenu: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaSastojakaPoSifri(sifra, false) == null) {
			System.out.println("Sastojak sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		izmenaPodatakaOSastojku(pretragaSastojakaPoSifri(sifra, false));
	}

	static void brisanjeSastojka() {
		SastojakFajl.ispisSastojaka(false);
		System.out.println("Unesite sifru sastojka: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(sifra, false) == null) {
			System.out.println("Sastojak sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte sifru ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		Sastojak sastojak = SastojakFajl.pretragaPoSifri(sifra, false);
		sastojak.setPotroseno(true);
		System.out.println("Brisanje uspesno obavljeno");
	}

	// VRSTE SASTOJAKA

	public static void cuvanjeVrste() {
		VrstaSastojkaFajl.vrste.clear();
		for (VrstaSastojka vs : sveVrste) {
			VrstaSastojkaFajl.vrste.add((VrstaSastojka) vs);
		}
		VrstaSastojkaFajl.pisanjeUFajl();
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

	public static void pretragaVrstePoSifri() {
		VrstaSastojka retVal = null;
		System.out.println("Unesite sifru: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		retVal = pretragaVrstePoSifri(sifra);
		if (retVal == null) {
			System.out.println("Vrsta slatkisa sa datom sifrom ne postoji");
		} else {
			System.out.println(retVal);
		}
	}

	public static ArrayList<VrstaSastojka> pretragaVrstePoNazivu(String naziv) {
		ArrayList<VrstaSastojka> lista = new ArrayList<VrstaSastojka>();
		for (VrstaSastojka vrsta : sveVrste) {
			if ((vrsta.getNaziv().toLowerCase()).contains(naziv.toLowerCase())) {
				lista.add(vrsta);
			}
		}
		return lista;
	}

	public static void pretragaVrstePoNazivu() {
		ArrayList<VrstaSastojka> lista = new ArrayList<VrstaSastojka>();
		System.out.println("Unesite naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		lista = pretragaVrstePoNazivu(naziv);
		if (lista.isEmpty()) {
			System.out.println("Nijedna vrsta nije pronadjena");
		} else {
			for (VrstaSastojka vs : lista) {
				System.out.println(vs);
			}
		}
	}

	public static void ispisVrstaSastojaka() {
		for (int i = 0; i < sveVrste.size(); i++) {
			System.out.println(sveVrste.get(i));
		}
	}

	public static void ispisVrstaSastojaka(boolean trueFalse) {
		for (int i = 0; i < sveVrste.size(); i++) {
			VrstaSastojka vs = sveVrste.get(i);
			if (vs.isPotroseno() == trueFalse) {
				System.out.println(vs);
			}
		}
	}

	public static void unosNoveVrste() {
		boolean potroseno = false;
		System.out.println("Unesi sifru:");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaVrstePoSifri(sifra) != null) {
			System.out.println("Vrsta sa indeksom " + sifra + " vec postoji");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		System.out.println("Unesi naziv vrste:");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesi opis:");
		String opis = PomocnaKlasa.ocitavanjeTeksta();
		char odluka = PomocnaKlasa.ocitavanjePotvrde("Vrsta ima nadvrstu? ");
		if (odluka == 'Y') {
			System.out.println("Unesite sifru nadvrste: ");
			String sifraNadvrste = PomocnaKlasa.ocitavanjeTeksta();
			while (pretragaVrstePoSifri(sifraNadvrste) == null) {
				System.out.println("Vrste je nepostojeca");
				sifraNadvrste = PomocnaKlasa.ocitavanjeTeksta();
			}
			VrstaSastojka izabrano = VrstaSastojkaFajl.pretragaPoSifri(sifraNadvrste, false);

			VrstaSastojka vrsta1 = new VrstaSastojka(sifra, naziv, opis, izabrano, potroseno);
			sveVrste.add(vrsta1);
		}
		if (odluka == 'N') {
			VrstaSastojka izabrano = null;
			VrstaSastojka vrsta = new VrstaSastojka(sifra, naziv, opis, izabrano, potroseno);
			sveVrste.add(vrsta);
		}
	}

	static void izmenaPodatakaOVrsti(VrstaSastojka vrsta) {
		System.out.println("Unesite naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite opis: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();

		ispisVrstaSastojaka(false);
		System.out.println("Izaberite sifru nadkategorije (ukucajte 'nema' ako ne postoji) : ");
		String nadvrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaVrstePoSifri(nadvrsta) == null && !(nadvrsta.equalsIgnoreCase("nema"))) {
			System.out.println("Vrsta pod sifrom " + nadvrsta + " ne postoji");
			System.out.println("Ukucajte ponovo");
			nadvrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka v = pretragaVrstePoSifri(nadvrsta);
		vrsta.setNaziv(naziv);
		vrsta.setOpis(opis);
		vrsta.setNadVrsta(v);
		System.out.println("Izmena uspesno obavljena!");
	}

	static void izmenaPodatakaOVrsti() {
		ispisVrstaSastojaka(false);
		System.out.println("Izaberite vrstu za brisanje: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaVrstePoSifri(sifra) == null) {
			System.out.println("Vrsta pod sifrom " + sifra + "ne postoji");
			System.out.println("Unesite ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		izmenaPodatakaOVrsti(pretragaVrstePoSifri(sifra));
	}

	static void brisanjeVrsteSastojka() {
		VrstaSastojkaFajl.ispisVrsta(false);
		System.out.println("Unesite sifru vrste sastojka: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaVrstePoSifri(sifra) == null) {
			System.out.println("Vrsta sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = pretragaVrstePoSifri(sifra);
		vs.setPotroseno(true);
		System.out.println("Brisanje vrste pod sifrom: " + sifra + " uspesno obavljeno!");
	}

//NAPRAVLJENI SLATKISI

	public static void pretragaNapravljenihSlatkisaPoSifri() {
		System.out.println("Unesite sifru trazenog slatkisa: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		NapravljenSlatkis ns = NapravljenSlatkisFajl.pretragaPoSifri(sifra, false);
		NapravljenSlatkis nsPotrosen = NapravljenSlatkisFajl.pretragaPoSifri(sifra, true);
		if (ns == null && nsPotrosen == null) {
			System.out.println("Napravljen slatkis sa datom sifrom ne postoji ");
		}
		if (nsPotrosen == null) {
			System.out.println(ns);
		}
		if (ns == null) {
			System.out.println(nsPotrosen);
		}
	}

	public static void pretragaNapravljenihSlatkisaPoNazivu() {
		ArrayList<NapravljenSlatkis> ns = new ArrayList<NapravljenSlatkis>();
		System.out.println("Unesi kljucnu rec: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		ns = NapravljenSlatkisFajl.pretragaPoNazivu(naziv);
		if (ns.size() == 0) {
			System.out.println("Napravljen slatkis sa datim nazivom ne postoje ");
		} else {
			for (NapravljenSlatkis napravljenSlatkis : ns) {
				System.out.println(napravljenSlatkis);
			}
		}
	}

	public static void pretragaNapravljenihSlatkisaPoOpseguCene() {
		ArrayList<NapravljenSlatkis> retVal = null;
		System.out.println("Unesite opseg cene");
		System.out.println("Od: ");
		Double najniza = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Do: ");
		Double najvisa = PomocnaKlasa.ocitavanjeRealnogBroja();
		retVal = NapravljenSlatkisFajl.pretragaPoOpseguCene(najniza, najvisa);
		if (retVal.size() == 0) {
			System.out.println("Slatkisi pod datim opsegom cene ne postoje");
		} else {
			for (NapravljenSlatkis ns : retVal) {
				System.out.println(ns);
			}
		}
	}

	public static void pretragaNapravljenihSlatkisaPoOpseguKolicine() {
		ArrayList<NapravljenSlatkis> retVal = null;
		System.out.println("Unesite opseg kolicine");
		System.out.println("Od: ");
		Double najmanja = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Do: ");
		Double najveca = PomocnaKlasa.ocitavanjeRealnogBroja();
		retVal = NapravljenSlatkisFajl.pretragaPoOpseguKolicine(najmanja, najveca);
		if (retVal.size() == 0) {
			System.out.println("Slatkis sa datim opsegom kolicine ne postoji");
		} else {
			for (NapravljenSlatkis ns : retVal) {
				System.out.println(ns);
			}
		}
	}

	static void ispisNapravljenihSlatkisa(boolean trueFalse) {
		for (Slatkis s : sviSlatkisi) {
			if (s instanceof NapravljenSlatkis && s.isPotroseno() == trueFalse) {
				System.out.println(s);
			}
		}
	}

	public static void unosNapravljenogSlatkisa() {
		System.out.println("Unesite sifru:");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaSlatkisaPoSifri(sifra) != null) {
			System.out.println("Slatkis sa indeksom " + sifra + " vec postoji");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		System.out.println("Unesite naziv novog slatkisa:");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite opis:");
		String opis = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite cenu:");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Unesite kolicinu:");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();
		ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
		ArrayList<Sastojak> neobrisani = nepotroseniSastojci();
		boolean prekid = false;
		while (prekid == false) {
			ispisSastojaka(neobrisani);
			System.out.println("Unesite sifru sastojka (ukucajte prekid za prestanjanje unosa):");
			String odluka = PomocnaKlasa.ocitavanjeTeksta();
			if (odluka.equalsIgnoreCase("prekid")) {
				if (sastojci.size() == 0) {
					prekid = false;
				} else {
					prekid = true;
				}
			}

			while (proveraSlatkisa(odluka) == null && prekid == false) {
				ispisSastojaka(neobrisani);
				if (odluka.equalsIgnoreCase("prekid")) {
					System.out.println("Niste uneli nijedan sastojak");
				} else {
					System.out.println("Sastojak sa sifrom " + odluka + " ne postoji");
				}
				System.out.println("Ukucajte ponovo");
				odluka = PomocnaKlasa.ocitavanjeTeksta();
			}

			Slatkis slatkis = proveraSlatkisa(odluka);
			if (slatkis instanceof SlatkaPoruka) {
				SlatkaPoruka poruka = (SlatkaPoruka) slatkis;
				sastojci.add(poruka);
			} else if (slatkis instanceof Ukras) {
				Ukras ukras = (Ukras) slatkis;
				sastojci.add(ukras);
			} else if (slatkis instanceof Sastojak) {
				Sastojak s = (Sastojak) slatkis;
				sastojci.add(s);
			}
		}
		boolean obrisano = false;
		NapravljenSlatkis ns = new NapravljenSlatkis(sifra, naziv, opis, cena, kolicina, obrisano, sastojci);
		sviSlatkisi.add(ns);
	}

	public static void izmenaPodatakaONapravljenomSlatkisu(NapravljenSlatkis ns) {
		System.out.println("Unesite naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite opis: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite cenu: ");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();

		System.out.println("Unesite kolicinu: ");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();

		ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
		ArrayList<Sastojak> nepotroseni = nepotroseniSastojci();
		boolean prekid = false;
		while (prekid == false) {
			ispisSastojaka(nepotroseni);
			System.out.println("Unesi sifru sastojka (ukucajte 'prekid' za zaustavljanje unosa):");
			String odluka = PomocnaKlasa.ocitavanjeTeksta();
			if (odluka.equalsIgnoreCase("prekid")) {
				prekid = true;
			}
			while (proveraSlatkisa(odluka) == null && prekid == false) {
				ispisSastojaka(nepotroseni);
				System.out.println("Sastojak sa sifrom " + odluka + " ne postoji");
				System.out.println("Ukucajte ponovo");
				odluka = PomocnaKlasa.ocitavanjeTeksta();
			}

			Slatkis slatkis = proveraSlatkisa(odluka);
			if (slatkis instanceof SlatkaPoruka) {
				SlatkaPoruka sp = (SlatkaPoruka) slatkis;
				sastojci.add(sp);
			} else if (slatkis instanceof Ukras) {
				Ukras ukras = (Ukras) slatkis;
				sastojci.add(ukras);
			} else if (slatkis instanceof Sastojak) {
				Sastojak sastojak = (Sastojak) slatkis;
				sastojci.add(sastojak);
			}
		}
		ns.setNaziv(naziv);
		ns.setOpis(opis);
		ns.setCena(cena);
		ns.setKolicina(kolicina);
		if (sastojci.size() != 0) {
			ns.setSastojci(sastojci);
		}

		System.out.println("Izmena uspesno obavljena!");
	}

	public static void izmenaPodatakaONapravljenomSlatkisu() {
		ispisNapravljenihSlatkisa(false);
		System.out.println("Unesite sifru slatkisa za izmenu: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (NapravljenSlatkisFajl.pretragaPoSifri(sifra, false) == null) {
			System.out.println("Slatkis sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		NapravljenSlatkis ns = NapravljenSlatkisFajl.pretragaPoSifri(sifra, false);
		izmenaPodatakaONapravljenomSlatkisu(ns);
	}

	static void brisanjeNapravljenogSlatkisa() {
		NapravljenSlatkisFajl.ispisNapravljenihSlatkisa(false);
		System.out.println("Unesi sifru slatkisa za brisanje: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (NapravljenSlatkisFajl.pretragaPoSifri(sifra, false) == null) {
			System.out.println("Slatkis sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte sifru ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		NapravljenSlatkis ns = NapravljenSlatkisFajl.pretragaPoSifri(sifra, false);
		ns.setPotroseno(true);
		System.out.println("Brisanje slatkisa pod sifrom " + sifra + " uspesno obavljeno!");
	}

	// UKRAS

	static void ispisUkrasa(boolean trueFalse) {
		for (Slatkis slatkis : sviSlatkisi) {
			if (slatkis instanceof Ukras && slatkis.isPotroseno() == trueFalse) {
				System.out.println(slatkis);

			}
		}
	}

	public static void unosNovogUkrasa() {
		System.out.println("Unesite sifru:");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaSlatkisaPoSifri(sifra) != null) {
			System.out.println("Slatkis sa indeksom " + sifra + " vec postoji");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		System.out.println("Unesite naziv ukrasa: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite opis: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite cenu: ");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Unesite kolicinu:");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();

		ispisVrstaSastojaka(false);
		System.out.println("Unesite sifru vrste: ");
		String vrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(vrsta, false) == null) {
			ispisVrstaSastojaka(false);
			System.out.println("Vrsta pod sifrom" + vrsta + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			vrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = VrstaSastojkaFajl.pretragaPoSifri(vrsta, false);

		System.out.println("Unesite broj ukrasa: ");
		int brojUkrasa = PomocnaKlasa.ocitavanjeCelogBroja();

		boolean potroseno = false;
		Ukras ukras = new Ukras(sifra, naziv, opis, cena, kolicina, potroseno, vs, brojUkrasa);
		sviSlatkisi.add(ukras);
	}

	static void izmenaPodatakaOUkrasu(Ukras ukras) {
		System.out.println("Unesite naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite opis: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite cenu: ");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();

		System.out.println("Unesite kolicinu: ");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();

		ispisVrstaSastojaka(false);
		System.out.println("Izaberite novu vrstu: ");
		String vrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(vrsta, false) == null) {
			System.out.println("Vrsta sa sifrom " + vrsta + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			vrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = VrstaSastojkaFajl.pretragaPoSifri(vrsta, false);

		System.out.println("Unesite broj figurica:");
		int brFigurica = PomocnaKlasa.ocitavanjeCelogBroja();

		ukras.setNaziv(naziv);
		ukras.setOpis(opis);
		ukras.setCena(cena);
		ukras.setKolicina(kolicina);
		ukras.setVrsta(vs);
		ukras.setBrojFigurica(brFigurica);
		System.out.println("Izmena uspesno obavljena!");
	}

	static void izmenaPodatakaOUkrasu() {
		ispisUkrasa(false);
		System.out.println("Unesite sifru ukrasa za izmenu: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (UkrasFajl.pretragaPoSifri(sifra, false) == null) {
			System.out.println("Ukras sa sifrom " + sifra + " ne postoji");
			System.out.println("Unesite ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		Ukras ukras = UkrasFajl.pretragaPoSifri(sifra, false);
		izmenaPodatakaOUkrasu(ukras);
	}

	static void brisanjeUkrasa() {
		UkrasFajl.ispisUkrasa(false);
		System.out.println("Unesi sifru ukrasa za brisanje: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (UkrasFajl.pretragaPoSifri(sifra, false) == null) {
			System.out.println("Ukras sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		Ukras ukras = UkrasFajl.pretragaPoSifri(sifra, false);
		ukras.setPotroseno(true);
		System.out.println("Brisanje ukrasa sa sifrom: " + sifra + " uspesno obavljeno!");
	}

	// SLATKA PORUKA

	static SlatkaPoruka pretragaPorukePoSifri(String sifra) {
		for (Slatkis s : sviSlatkisi) {
			if (s instanceof SlatkaPoruka) {
				if (s.getSifra().equalsIgnoreCase(sifra)) {
					return (SlatkaPoruka) s;
				}
			}
		}
		return null;
	}

	public static void unosNovePoruke() {
		System.out.println("Unesite sifru:");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaSlatkisaPoSifri(sifra) != null) {
			System.out.println("Slatkis sa indeksom " + sifra + " vec postoji");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		System.out.println("Unesite naziv poruke: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite opis poruke: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();
		System.out.println("Unesite cenu: ");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();
		System.out.println("Unesite kolicinu:");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();
		ispisVrstaSastojaka(false);
		System.out.println("Unesite sifru vrste: ");
		String vrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(vrsta, false) == null) {
			ispisVrstaSastojaka(false);
			System.out.println("Vrsta pod sifrom" + vrsta + " ne postoji");
			System.out.println("Unesite ponovo: ");
			vrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = VrstaSastojkaFajl.pretragaPoSifri(vrsta, false);
		System.out.println("Unesite tekst poruke: ");
		String tekst = PomocnaKlasa.ocitavanjeTeksta();
		boolean potroseno = false;
		SlatkaPoruka sp = new SlatkaPoruka(sifra, naziv, opis, cena, kolicina, potroseno, vs, tekst);
		sviSlatkisi.add(sp);
	}

	static void izmenaPodatakaOPoruci(SlatkaPoruka sp) {
		System.out.println("Unesite naziv: ");
		String naziv = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite opis: ");
		String opis = PomocnaKlasa.ocitavanjeTeksta();

		System.out.println("Unesite cenu: ");
		double cena = PomocnaKlasa.ocitavanjeRealnogBroja();

		System.out.println("Unesite kolicinu: ");
		double kolicina = PomocnaKlasa.ocitavanjeRealnogBroja();

		ispisVrstaSastojaka(false);
		System.out.println("Izaberite novu vrstu: ");
		String porukaVrsta = PomocnaKlasa.ocitavanjeTeksta();
		while (VrstaSastojkaFajl.pretragaPoSifri(porukaVrsta, false) == null) {
			System.out.println("Vrsta sa sifrom " + porukaVrsta + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			porukaVrsta = PomocnaKlasa.ocitavanjeTeksta();
		}
		VrstaSastojka vs = VrstaSastojkaFajl.pretragaPoSifri(porukaVrsta, false);

		System.out.println("Unesite tekst poruke: ");
		String tekst = PomocnaKlasa.ocitavanjeTeksta();

		sp.setNaziv(naziv);
		sp.setOpis(opis);
		sp.setCena(cena);
		sp.setKolicina(kolicina);
		sp.setVrsta(vs);
		sp.setTekst(tekst);
		System.out.println("Izmena uspesno obavljena!");
	}

	static void izmenaPodatakaOPoruci() {
		SlatkaPorukaFajl.ispisPoruka(false);
		System.out.println("Unesite sifru poruke za izmenu: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (pretragaPorukePoSifri(sifra) == null) {
			System.out.println("Poruka sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		SlatkaPoruka sp = pretragaPorukePoSifri(sifra);
		izmenaPodatakaOPoruci(sp);

	}

	static void brisanjePoruke() {
		SlatkaPorukaFajl.ispisPoruka(false);
		System.out.println("Unesite sifru poruke za brisanje: ");
		String sifra = PomocnaKlasa.ocitavanjeTeksta();
		while (SlatkaPorukaFajl.pretragaPoSifri(sifra, false) == null) {
			System.out.println("Poruka sa sifrom " + sifra + " ne postoji");
			System.out.println("Ukucajte ponovo: ");
			sifra = PomocnaKlasa.ocitavanjeTeksta();
		}
		SlatkaPoruka sp = SlatkaPorukaFajl.pretragaPoSifri(sifra, false);
		sp.setPotroseno(true);
		System.out.println("Brisanje poruke pod sifrom: " + sifra + " uspesno obavljeno!");
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

	static void unos() {
		Meniji.podMeniZaUnosIzmenuIBrisanje();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			unosNovogSastojka();
			break;
		case "2":
			unosNapravljenogSlatkisa();
			break;
		case "3":
			unosNoveVrste();
			break;
		case "4":
			unosNovePoruke();
			break;
		case "5":
			unosNovogUkrasa();
			break;
		case "0":
			break;

		}
	}

	static void izmena() {
		Meniji.podMeniZaUnosIzmenuIBrisanje();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			izmenaPodatakaOSastojku();
			break;
		case "2":
			izmenaPodatakaONapravljenomSlatkisu();
			break;
		case "3":
			izmenaPodatakaOVrsti();
			break;
		case "4":
			izmenaPodatakaOPoruci();
			break;
		case "5":
			izmenaPodatakaOUkrasu();
			break;
		case "0":
			break;
		}
	}

	static void brisanje() {
		Meniji.podMeniZaUnosIzmenuIBrisanje();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			brisanjeSastojka();
			break;
		case "2":
			brisanjeNapravljenogSlatkisa();
			break;
		case "3":
			brisanjeVrsteSastojka();
			break;
		case "4":
			brisanjePoruke();
			break;
		case "5":
			brisanjeUkrasa();
			break;
		case "0":
			break;
		}
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

	static void pretragaSastojaka() {
		Meniji.podMeniZaPretraguSastojaka();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			pretragaSastojakaPoSifri();
			break;
		case "2":
			pretragaSastojakaPoNazivu();
			break;
		case "3":
			pretragaSastojakaPoOpseguCene();
			break;
		case "4":
			pretragaSastojakaPoOpseguKolicine();
			break;
		case "5":
			pretragaSastojakaPoVrsti();
			break;
		case "0":
			break;

		}
	}

	static void pretragaNapravljenihSlatkisa() {
		Meniji.podMeniZaPretraguNapravljenihSlatkisa();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			pretragaNapravljenihSlatkisaPoSifri();
			break;
		case "2":
			pretragaNapravljenihSlatkisaPoNazivu();
			break;
		case "3":
			pretragaNapravljenihSlatkisaPoOpseguCene();
			break;
		case "4":
			pretragaNapravljenihSlatkisaPoOpseguKolicine();
			break;
		case "0":
			break;
		}
	}

	static void pretragaVrstaSastojaka() {
		Meniji.podMeniZaPretraguVrsta();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			pretragaVrstePoSifri();
			break;
		case "2":
			pretragaVrstePoNazivu();
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
			pretragaSastojaka();
			break;
		case "3":
			pretragaNapravljenihSlatkisa();
			break;
		case "4":
			pretragaVrstaSastojaka();
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

	static void sortiranje() {
		Meniji.podMeniZaSortiranje();
		String odluka = PomocnaKlasa.ocitavanjeTeksta();
		switch (odluka) {
		case "1":
			sortiranjeSlatkisaPoNazivu(-1);
			break;
		case "2":
			sortiranjeSlatkisaPoNazivu(1);
			break;
		case "3":
			sortiranjeSlatkisaPoCeni(-1);
			break;
		case "4":
			sortiranjeSlatkisaPoCeni(1);
			break;
		case "0":
			break;

		}
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
				unos();
				break;
			case "2":
				izmena();
				break;
			case "3":
				brisanje();
				break;
			case "4":
				pretraga();
				break;
			case "5":
				sortiranje();
				break;
			case "6":
				break;
			case "7":
				cuvanjeSlatkisa();
				cuvanjeVrste();
				break;
			case "8":
				ocitavanje();
				break;
			case "0":
				break;

			}
		}
		cuvanjeSlatkisa();
		cuvanjeVrste();
	}
}
