import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean ende = false;

		Spielfeld spielfeld = null;
		Sudoku sudoku = null;

		do {

			System.out.println("Bitte entscheiden Sie sich fuer eine der folgenden Aktionen: \n "
					+ "1) Spielfeld anlegen\n " + "2) Eine Zahl in ein Feld eintragen\n "
					+ "3) Den letzten Schritt zuruecknehmen\n " + "4) Spiel verlassen");

			int auswahl = sc.nextInt();

			switch (auswahl) {
			case 1:
				spielfeld = new Spielfeld();
				spielfeld.erstelleSpielfeld();
				spielfeld.feldAnlegen();
				sudoku = new Sudoku(spielfeld);
				break;
			case 2:
				if (sudoku == null) {
					System.out.println("Zuerst Sudokufeld anlegen.");
					break;
				}
				if (spielfeld.getVolleFelder() != 81) {
					if (sudoku.zug()) {
						if (spielfeld.getVolleFelder() == 81 && sudoku.testeSieg()) {
							System.out.println("Sie haben gewonnen");
						}
					}
				}
				break;

			case 3:
				if (!sudoku.zuruecknehmen()) {
					System.out.println("Zug kann nicht zurueckgenommen werden.");
				}
				break;

			case 4:
				ende = true;
				break;

			}

			if (spielfeld != null) {
				spielfeld.druckeSpielfeld();
			}

		} while (!ende);
		
		sc.close();

	}

}
