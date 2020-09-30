package poslasticarnica.fajlovi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import poslasticarnica.model.NapravljenSlatkis;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class NapravljenSlatkisFajl {

	public static ArrayList<NapravljenSlatkis> napravljeniSlatkisi = new ArrayList<NapravljenSlatkis>();
	public static boolean ocitan = false;

	public static void pisanjeUFajl() {
		try {
			String putanja = PomocnaKlasa.putanjaDoFajla("napravljeniSlatkisi.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists())
				brisanjeKreiranje.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (NapravljenSlatkis ns : napravljeniSlatkisi) {
				pw.println(ns.zaFajl());
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void citanjeIzFajla() {
		if (ocitan) {
			return;
		}
		try {
			napravljeniSlatkisi.clear();
			String putanja = PomocnaKlasa.putanjaDoFajla("napravljeniSlatkisi.txt");
			File obrisiKreiraj = new File(putanja);
			if (!obrisiKreiraj.exists()) {
				System.out.println("Nepostojeci fajl (napravljeni slatkisi)");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String string;
			while ((string = br.readLine()) != null) {
				NapravljenSlatkis ns = new NapravljenSlatkis(string);
				napravljeniSlatkisi.add(ns);
			}
			ocitan = true;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ispisNapravljenihSlatkisa(boolean trueFalse) {
		for (NapravljenSlatkis ns : napravljeniSlatkisi) {
			if (ns.isPotroseno() == trueFalse) {
				System.out.println(ns);
			}

		}
	}

	public static NapravljenSlatkis pretragaPoSifri(String sifra) {
		for (NapravljenSlatkis ns : napravljeniSlatkisi) {
			if (sifra.equals(ns.getSifra()))
				return ns;

		}
		return null;

	}

	public static NapravljenSlatkis pretragaPoSifri(String sifra, boolean trueFalse) {
		for (NapravljenSlatkis ns : napravljeniSlatkisi) {
			if (sifra.equalsIgnoreCase(ns.getSifra()) && ns.isPotroseno() == trueFalse)
				return ns;
		}
		return null;
	}

	public static ArrayList<NapravljenSlatkis> pretragaPoNazivu(String naziv) {
		ArrayList<NapravljenSlatkis> lista = new ArrayList<NapravljenSlatkis>();
		for (NapravljenSlatkis ns : napravljeniSlatkisi) {
			if ((ns.getNaziv().toLowerCase()).contains(naziv.toLowerCase())) {
				lista.add(ns);
			}
		}
		return lista;
	}

	public static ArrayList<NapravljenSlatkis> pretragaPoOpseguCene(double pocetnaCena, double krajnjaCena) {
		ArrayList<NapravljenSlatkis> lista = new ArrayList<NapravljenSlatkis>();
		for (NapravljenSlatkis ns : napravljeniSlatkisi) {
			if (ns.getCena() >= pocetnaCena && ns.getCena() <= krajnjaCena) {
				lista.add(ns);
			}
		}
		return lista;
	}

	public static ArrayList<NapravljenSlatkis> pretragaPoOpseguKolicine(double pocetnaKolicina,
			double krajnjaKolicina) {
		ArrayList<NapravljenSlatkis> lista = new ArrayList<NapravljenSlatkis>();
		for (NapravljenSlatkis ns : napravljeniSlatkisi) {
			if (ns.getKolicina() >= pocetnaKolicina && ns.getKolicina() <= krajnjaKolicina) {
				lista.add(ns);
			}
		}
		return lista;
	}

}
