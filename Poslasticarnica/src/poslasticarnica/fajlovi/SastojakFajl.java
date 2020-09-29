package poslasticarnica.fajlovi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import poslasticarnica.model.Sastojak;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class SastojakFajl {
	
	public static ArrayList<Sastojak> sastojci = new ArrayList<Sastojak>();
	public static boolean ocitan = false;

	public static void pisanjeUFajl() {
		try {
			String putanja = PomocnaKlasa.putanjaDoFajla("sastojci.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists())
				brisanjeKreiranje.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (Sastojak s : sastojci) {
				pw.println(s.zaFajl());
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
			sastojci.clear();
			String putanja = PomocnaKlasa.putanjaDoFajla("sastojci.txt");
			File obrisiKreiraj = new File(putanja);
			if (!obrisiKreiraj.exists()) {
				System.out.println("Nepostojeci fajl");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String string;
			while ((string = br.readLine()) != null) {
				Sastojak s = new Sastojak(string);
				sastojci.add(s);
			}
			ocitan = true;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ispisSastojaka(boolean trueFalse) {
		for (Sastojak s : sastojci) {
			if (s.isPotroseno() == trueFalse) {
				System.out.println(s);
			}

		}
	}

	public static Sastojak pretragaPoSifri(String sifra) {
		for (Sastojak s : sastojci) {
			if (sifra.equals(s.getSifra()))
				return s;
		}

		return null;
	}

	public static Sastojak pretragaPoSifri(String sifra, boolean trueFalse) {
		for (Sastojak s : sastojci) {
			if ((s.getSifra()).equalsIgnoreCase(sifra) && s.isPotroseno() == trueFalse)
				return s;
		}
		return null;
	}

	public static ArrayList<Sastojak> pretragaPoNazivu(String naziv) {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		for (Sastojak s : sastojci) {
			if ((s.getNaziv().toLowerCase()).contains(naziv.toLowerCase())) {
				lista.add(s);
			}
		}
		return lista;
	}

	public static ArrayList<Sastojak> pretragaPoVrsti(String sifra) {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		for (Sastojak s : sastojci) {
			if ((s.getVrsta().getSifra()).equalsIgnoreCase(sifra)) {
				lista.add(s);
			}
		}
		return lista;
	}

	public static ArrayList<Sastojak> pretragaPoOpseguCene(double pocetnaCena, double krajnjaCena) {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		for (Sastojak s : sastojci) {
			if (s.getCena() >= pocetnaCena && s.getCena() <= krajnjaCena) {
				lista.add(s);

			}
		}
		return lista;
	}

	public static ArrayList<Sastojak> pretragaPoOpseguKolicine(double pocetnaKolicina, double krajnjaKolicina) {
		ArrayList<Sastojak> lista = new ArrayList<Sastojak>();
		for (Sastojak s : sastojci) {
			if (s.getKolicina() >= pocetnaKolicina && s.getKolicina() <= krajnjaKolicina) {
				lista.add(s);
			}
		}
		return lista;
	}

}
