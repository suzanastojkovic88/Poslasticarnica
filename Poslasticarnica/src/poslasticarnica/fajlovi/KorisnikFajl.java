package poslasticarnica.fajlovi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import poslasticarnica.model.Korisnik;
import poslasticarnica.pomocne_klase.PomocnaKlasa;

public class KorisnikFajl {

	public static ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
	public static boolean ocitan = false;

	public static void pisanjeUFajl() {
		try {
			String putanja = PomocnaKlasa.putanjaDoFajla("korisnici.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists())
				brisanjeKreiranje.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (Korisnik korisnik : korisnici) {
				pw.println(korisnik.zaFajl());
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
			korisnici.clear();
			String putanja = PomocnaKlasa.putanjaDoFajla("korisnici.txt");
			File brisanjeKreiranje = new File(putanja);
			if (!brisanjeKreiranje.exists()) {
				System.out.println("Nepostojeci fajl (korisnici)");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String string;
			while ((string = br.readLine()) != null) {
				Korisnik korisnik = new Korisnik(string);
				korisnici.add(korisnik);
			}
			ocitan = true;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Korisnik pretragaPoKorisnickomImenu(String korisnickoIme) {
		for (Korisnik korisnik : korisnici) {
			if (korisnickoIme.equals(korisnik.getKorisnickoIme())) {
				return korisnik;
			}

		}
		return null;
	}

}
