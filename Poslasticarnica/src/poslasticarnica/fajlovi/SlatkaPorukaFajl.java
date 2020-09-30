package poslasticarnica.fajlovi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import poslasticarnica.model.SlatkaPoruka;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class SlatkaPorukaFajl {

	public static ArrayList<SlatkaPoruka> poruke = new ArrayList<SlatkaPoruka>();
	public static boolean ocitan = false;

	public static void pisanjeUFajl() {
		try {
			String putanja = PomocnaKlasa.putanjaDoFajla("poruke.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists())
				brisanjeKreiranje.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (SlatkaPoruka sp : poruke) {
				pw.println(sp.zaFajl());
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
			poruke.clear();
			String putanja = PomocnaKlasa.putanjaDoFajla("poruke.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists()) {
				System.out.println("Nepostojeci fajl (slatke poruke)");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String string;
			while ((string = br.readLine()) != null) {
				SlatkaPoruka sp = new SlatkaPoruka(string);
				poruke.add(sp);
			}
			ocitan = true;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ispisPoruka(boolean trueFalse) {
		for (SlatkaPoruka sp : poruke) {
			if (sp.isPotroseno() == trueFalse) {
				System.out.println(sp);
			}
		}
	}

	public static SlatkaPoruka pretragaPoSifri(String sifra) {
		for (SlatkaPoruka sp : poruke) {
			if (sp.getSifra().equalsIgnoreCase(sifra)) {
				return sp;
			}

		}
		return null;
	}

	public static SlatkaPoruka pretragaPoSifri(String sifra, boolean trueFalse) {
		for (SlatkaPoruka sp : poruke) {
			if (sp.getSifra().equalsIgnoreCase(sifra) && sp.isPotroseno() == trueFalse) {
				return sp;
			}

		}
		return null;
	}
}
