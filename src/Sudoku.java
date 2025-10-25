import java.util.Scanner;

public class Sudoku {

	private Spielfeld spielfeld;
	private int[] letzteZuegeZeile = new int[5];
	private int[] letzteZuegeSpalte = new int[5];
	private int indexLetzteZuege = 0;
	Scanner sc = new Scanner(System.in);

	public Sudoku(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}

	public boolean zahlEintragen(int zeile, int spalte, int zahl) {

		if (zeile < 0 || zeile > spielfeld.getZeilen() || spalte < 0 || spalte > spielfeld.getSpalten() || zahl < 1
				|| zahl > 9) {
			return false;
		} else if (spielfeld.getSpielfeld()[zeile][spalte] == '_') {
			spielfeld.getSpielfeld()[zeile][spalte] = (char) ((char) zahl + 48);
			spielfeld.erhoeheVolleFelder();
			return true;
		} else {
			return false;
		}
	}

	public boolean zug() {

		do {
			System.out.println("Bitte geben Sie das Feld ein:");
			System.out.println("Zeile (0 - 8):");
			int zeile = sc.nextInt();

			System.out.println("Spalte (0 - 8):");
			int spalte = sc.nextInt();
			System.out.println("Bitte geben Sie jetzt die einzutragende Zahl ein (1 - 9):");
			int zahl = sc.nextInt();
			if (zahlEintragen(zeile, spalte, zahl)) {
				letzteZuegeZeile[indexLetzteZuege] = zeile;
				letzteZuegeSpalte[indexLetzteZuege] = spalte;
				indexLetzteZuege = (indexLetzteZuege + 1) % 5;
				return true;
			} else {
				System.out.println("Zug nicht moeglich. Bitte nochmal versuchen.");
			}

		} while (true);
	}

	public boolean testeTeilfeld(int zeile, int spalte) {
		boolean zahlen[] = new boolean[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int zahl = (int) spielfeld.getSpielfeld()[i + zeile][j + spalte] - 48;
				if (zahlen[zahl - 1] == false) {
					zahlen[zahl - 1] = true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public boolean testeFeld() {
		for (int i = 0; i < spielfeld.getZeilen(); i += 3) {
			for (int j = 0; j < spielfeld.getSpalten(); j += 3) {
				if (!testeTeilfeld(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean testeSpalten() {

		for (int j = 0; j < spielfeld.getSpalten(); j++) {
			boolean zahlen[] = new boolean[9];
			for (int i = 0; i < spielfeld.getZeilen(); i++) {
				int zahl = (int) spielfeld.getSpielfeld()[i][j] - 48;
				if (zahlen[zahl - 1] == false) {
					zahlen[zahl - 1] = true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public boolean testeZeilen() {
		for (int i = 0; i < spielfeld.getZeilen(); i++) {
			boolean zahlen[] = new boolean[9];
			for (int j = 0; j < spielfeld.getSpalten(); j++) {
				int zahl = (int) spielfeld.getSpielfeld()[i][j] - 48;
				if (zahlen[zahl - 1] == false) {
					zahlen[zahl - 1] = true;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public boolean testeSieg() {
		return testeFeld() && testeSpalten() && testeZeilen();
	}

	public boolean zuruecknehmen() {
		int indexTmp = (indexLetzteZuege + 4) % 5;
		int zeile = letzteZuegeZeile[indexTmp];
		int spalte = letzteZuegeSpalte[indexTmp];
		if (spielfeld.getSpielfeld()[zeile][spalte] != '_') {
			spielfeld.getSpielfeld()[zeile][spalte] = '_';
			indexLetzteZuege = (indexLetzteZuege + 4) % 5;
			spielfeld.verringereVolleFelder();
			return true;
		}
		return false;
	}

}
