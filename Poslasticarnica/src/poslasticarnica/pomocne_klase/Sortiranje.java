package poslasticarnica.pomocne_klase;

import java.util.Comparator;

import poslasticarnica.model.Slatkis;

@SuppressWarnings("rawtypes")
public class Sortiranje implements Comparator {

	int pravac = 1;

	public Sortiranje(int pravac) {
		if (pravac != 1 && pravac != -1) {
			pravac = 1;
		}
		this.pravac = pravac;
	}

	public int compare(Object o1, Object o2) {
		int retVal = 0;
		if (o1 != null && o2 != null && o1 instanceof Slatkis && o2 instanceof Slatkis) {
			Slatkis slatkis1 = (Slatkis) o1;
			Slatkis slatkis2 = (Slatkis) o2;
			retVal = slatkis1.getNaziv().compareTo(slatkis2.getNaziv());
		}
		return retVal * pravac;
	}

}
