package poslasticarnica;

public abstract class Meniji {

	public static void pocetniMeni() {
		System.out.println("Unesi broj opcije:");
		System.out.println("\tOpcija broj 1 - Unos");
		System.out.println("\tOpcija broj 2 - Izmena");
		System.out.println("\tOpcija broj 3 - Brisanje");
		System.out.println("\tOpcija broj 4 - Pretraga i pregled");
		System.out.println("\tOpcija broj 5 - Sortiranje");
		System.out.println("\tOpcija broj 6 - Snimanje podataka u fajl");
		System.out.println("\tOpcija broj 7 - Citanje podataka iz fajla");
		System.out.println("\tOpcija broj 0 - Izlaz");
	}

	public static void podMeniZaPretragu() {
		System.out.println("Unesi broj opcije:");
		System.out.println("\tOpcija broj 1 - Pretraga slatkisa");
		// System.out.println("\tOpcija broj 2 - Pretraga sastojaka");
		// System.out.println("\tOpcija broj 3 - Pretraga napravljenih slatkisa");
		// System.out.println("\tOpcija broj 4 - Pretraga vrsta slatkisa");
		// System.out.println("\tOpcija broj 5 - Pregled svih slatkisa");
		System.out.println("\tOpcija broj 6 - Pregled neobrisanih slatkisa");
		System.out.println("\tOpcija broj 7 - Pregled obrisanih slatkisa");
		System.out.println("\tOpcija broj 0 - Nazad");
	}

	public static void podMeniZaPretraguSlatkisa() {
		System.out.println("Unesi broj opcije:");
		System.out.println("\tOpcija broj 1 - Pretraga po sifri");
		System.out.println("\tOpcija broj 2 - Pretraga po nazivu");
		System.out.println("\tOpcija broj 3 - Pretraga po opsegu cene");
		System.out.println("\tOpcija broj 0 - Pretraga po nazad");
	}
}