package entiteti.osoba;

public enum Odeljenje {

	PRIJEM_VOZNJE, // 1
	REKLAMACIJE; // 2

	public static Odeljenje fromInt(int a) {
		switch (a) {
		case 1:
			return PRIJEM_VOZNJE;
		default:
			return REKLAMACIJE;
		}
	}

	public static int toInt(Odeljenje odeljenje) {
		switch (odeljenje) {
		case PRIJEM_VOZNJE:
			return 1;
		default:
			return 2;

		}
	}
}
