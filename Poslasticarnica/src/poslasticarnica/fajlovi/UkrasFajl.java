package poslasticarnica.fajlovi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import poslasticarnica.model.Ukras;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class UkrasFajl {

	public static ArrayList<Ukras> ukrasi = new ArrayList<Ukras>();
	public static boolean ocitan = false;

	public static void pisanjeUFajl() {
		try {
			String putanja = PomocnaKlasa.putanjaDoFajla("ukrasi.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists())
				brisanjeKreiranje.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (Ukras ukras : ukrasi) {
				pw.println(ukras.zaFajl());
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
			ukrasi.clear();
			String putanja = PomocnaKlasa.putanjaDoFajla("ukrasi.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists()) {
				System.out.println("Nepostojeci fajl");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String string;
			while ((string = br.readLine()) != null) {
				Ukras ukras = new Ukras(string);
				ukrasi.add(ukras);
			}
			ocitan = true;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ispisUkrasa(boolean trueFalse) {
		for (Ukras u : ukrasi) {
			if (u.isPotroseno() == trueFalse) {
				System.out.println(u);
			}
		}
	}

	public static Ukras pretragaPoSifri(String sifra) {
		for (Ukras u : ukrasi) {
			if (u.getSifra().equalsIgnoreCase(sifra)) {
				return u;
			}

		}
		return null;
	}

	public static Ukras pretragaPoSifri(String sifra, boolean trueFalse) {
		for (Ukras u : ukrasi) {
			if (sifra.equalsIgnoreCase(u.getSifra()) && u.isPotroseno() == trueFalse)
				return u;
		}
		return null;
	}
}
