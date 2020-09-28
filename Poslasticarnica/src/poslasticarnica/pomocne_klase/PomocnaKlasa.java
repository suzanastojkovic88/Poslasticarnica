package poslasticarnica.pomocne_klase;

import java.util.Scanner;

public class PomocnaKlasa {

	static Scanner sc = new Scanner(System.in);

	public static String putanjaDoFajla(String nazivFajla) {
		String separator = System.getProperty("file.separator");
		String putanja = "." + separator + "podaci" + separator + nazivFajla;
		return putanja;
	}

	public static String ocitavanjeTeksta() {
		String tekst = "";
		while (tekst == null || tekst.equals(""))
			tekst = sc.nextLine();

		return tekst;
	}

	public static int ocitavanjeCelogBroja() {
		int ceoBroj = 0;
		boolean nijeUcitan = true;
		do {
			if (sc.hasNextInt()) {
				ceoBroj = sc.nextInt();
				nijeUcitan = false;
			} else {
				System.out.println("Pogresno uneta vrednost, pokusajte ponovo. ");
			}
			sc.nextLine();
		} while (nijeUcitan);
		return ceoBroj;
	}

	public static double ocitavanjeRealnogBroja() {
		double realanBroj = 0;
		boolean nijeUcitan = true;
		do {
			if (sc.hasNextDouble()) {
				realanBroj = sc.nextDouble();
				nijeUcitan = false;
			} else {
				System.out.println("Pogresno uneta vrednost, pokusajte ponov. ");
			}
			sc.nextLine();
		} while (nijeUcitan);
		return realanBroj;
	}

	public static char ocitavanjeKaraktera() {
		char karakter = ' ';
		boolean nijeUcitan = true;
		do {
			String text = sc.next();
			if (text.length() == 1) {
				karakter = text.charAt(0);
				nijeUcitan = false;
			} else {
				System.out.println("Pogresno uneta vrednost, pokusajte ponovo. ");
			}
			sc.nextLine();
		} while (nijeUcitan);
		return karakter;
	}

	public static char ocitavanjePotvrde(String tekst) {
		System.out.println("Da li zelite " + tekst + " [Y/N]:");
		char odluka = ' ';
		while (!(odluka == 'Y' || odluka == 'N')) {
			odluka = ocitavanjeKaraktera();
			if (!(odluka == 'Y' || odluka == 'N')) {
				System.out.println("Opcije su Y(Da) ili N(ne)");
			}
		}
		return odluka;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isBoolean(String s) {
		try {
			Boolean.parseBoolean(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
