package entiteti.automobil;

public enum Vrsta {

	PUTNICKI, // 1
	KOMBI; // 2

	public static Vrsta fromInt(int a) {
		switch (a) {
		case 1:
			return PUTNICKI;
		default:
			return KOMBI;
		}
	}

	public static int toInt(Vrsta vrsta) {
		switch (vrsta) {
		case PUTNICKI:
			return 1;
		default:
			return 2;

		}
	}
}
