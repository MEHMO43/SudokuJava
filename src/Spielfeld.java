import java.util.Random;
import java.util.Scanner;

public class Spielfeld {

	private final int zeilen = 9;
	private final int spalten = 9;
	private char[][] spielfeld;
	private static int volleFelder = 0;
	private int[][] spielfeldVoll = { { 6, 1, 4, 7, 2, 3, 9, 5, 8 }, { 3, 7, 5, 8, 9, 4, 1, 2, 6 },
			{ 8, 9, 2, 1, 6, 5, 4, 3, 7 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 9, 4, 8, 2, 3, 7, 6, 1, 5 },
			{ 5, 6, 7, 9, 1, 8, 2, 4, 3 }, { 2, 3, 1, 6, 8, 9, 5, 7, 4 }, { 4, 5, 6, 3, 7, 1, 8, 9, 2 },
			{ 7, 8, 9, 5, 4, 2, 3, 6, 1 } };

	Random random = new Random();
	Scanner sc = new Scanner(System.in);

	public Spielfeld() {
		this.spielfeld = new char[zeilen][spalten];
	}

	public void erstelleSpielfeld() {
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				spielfeld[i][j] = '_';
			}
		}
	}

	public void druckeSpielfeld() {
		System.out.println();

		System.out.print("   | ");
		for (int i = 0; i < spalten; i++) {

			if (i % 3 == 0 && i != 0) {
				System.out.print("  ");
			}
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < spalten; i++) {
			System.out.print("---");
		}

		System.out.println();
		for (int i = 0; i < zeilen; i++) {
			System.out.print(i + "  | ");
			for (int j = 0; j < spalten; j++) {
				if ((j % 3 == 0) && j != 0) {
					System.out.print("| ");
				}
				System.out.print(spielfeld[i][j] + " ");
			}

			System.out.println();
			if ((i + 1) % 3 == 0 && i != 0) {
				for (int k = 0; k < spalten; k++) {
					System.out.print("---");
				}
			}
			System.out.println();
		}
	}

	public void fuelleZufaellig(int anz) {

		for (int i = 0; i < anz; i++) {

			boolean gesetzt = false;

			while (!gesetzt) {
				int x = random.nextInt(9);
				int y = random.nextInt(9);

				if (spielfeld[x][y] == '_') {
					spielfeld[x][y] = (char) ((char) spielfeldVoll[x][y] + 48);
					volleFelder++;
					gesetzt = true;
				}
			}

		}
	}

	public void feldAnlegen() {

		boolean richtig = false;
		int anz = 0;
		do {
			System.out.println("Wie viele Felder sollen anfangs gefuellt sein?");
			anz = sc.nextInt();
			if (anz < 81) {
				richtig = true;
			}

		} while (!richtig);
		fuelleZufaellig(anz);

	}

	public int getZeilen() {
		return zeilen;
	}

	public int getSpalten() {
		return spalten;
	}

	public char[][] getSpielfeld() {
		return spielfeld;
	}

	public int[][] getSpielfeldVoll() {
		return spielfeldVoll;
	}

	public int getVolleFelder() {
		return volleFelder;
	}

	public void erhoeheVolleFelder() {
		volleFelder++;
	}

	public void verringereVolleFelder() {
		volleFelder--;
	}

}
