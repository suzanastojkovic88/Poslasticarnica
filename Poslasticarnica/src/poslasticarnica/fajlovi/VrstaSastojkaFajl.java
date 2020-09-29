package poslasticarnica.fajlovi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import poslasticarnica.model.VrstaSastojka;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class VrstaSastojkaFajl {

	public static ArrayList<VrstaSastojka> vrste = new ArrayList<VrstaSastojka>();
	public static boolean ocitan = false;

	public static void pisanjeUFajl() {
		try {
			String putanja = PomocnaKlasa.putanjaDoFajla("vrste.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists())
				brisanjeKreiranje.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (VrstaSastojka vs : vrste) {
				pw.println(vs.zaFajl());
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
			vrste.clear();
			String putanja = PomocnaKlasa.putanjaDoFajla("vrste.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists()) {
				System.out.println("Nepostojeci fajl");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String string;
			while ((string = br.readLine()) != null) {
				VrstaSastojka vs = new VrstaSastojka(string);
				vrste.add(vs);
			}
			ocitan = true;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ispisVrsta(boolean trueFalse) {
		for (VrstaSastojka vs : vrste) {
			if (vs.isPotroseno() == trueFalse) {
				System.out.println(vs);
			}
		}
	}

	public static VrstaSastojka pretragaPoSifri(String sifra) {
		for (VrstaSastojka vs : vrste) {
			if (sifra.equalsIgnoreCase(vs.getSifra()))
				return vs;
		}
		return null;
	}

	public static VrstaSastojka pretragaPoSifri(String sifra, boolean trueFalse) {
		for (VrstaSastojka vs : vrste) {
			if (sifra.equalsIgnoreCase(vs.getSifra()) && vs.isPotroseno() == trueFalse)
				return vs;
		}
		return null;
	}

}
