import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		/*
		 * Diese 5 Zeilen NICHT verändern Bei bedarf die markierte Zeile auskommentieren
		 * wenn abstand.txt vorhanden ist (siehe Methodendefinition)
		 * 
		 */

		ArrayList<Stadt> originalTour = new ArrayList<>();
		tspRouteInArraySpeichern(originalTour);
		double[][] alleAbstaende = new double[originalTour.size()][originalTour.size()];
		alleAbstaendeInTxtSpeichern(originalTour, alleAbstaende); // diese!!!!!
		einlesenAbstandtabelleInMatrixSpeichern(alleAbstaende);

		/*
		 * Ab hier bedarf wie man will
		 */

		ArrayList<Stadt> population1 = new ArrayList<>();
		ArrayList<Stadt> population2 = new ArrayList<>();
		ArrayList<Stadt> population3 = new ArrayList<>();
		ArrayList<Stadt> population4 = new ArrayList<>();
		ArrayList<Stadt> population5 = new ArrayList<>();
		ArrayList<Stadt> population6 = new ArrayList<>();
		ArrayList<Stadt> population7 = new ArrayList<>();
		ArrayList<Stadt> population8 = new ArrayList<>();
		ArrayList<Stadt> population9 = new ArrayList<>();
		ArrayList<Stadt> population10 = new ArrayList<>();

		ArrayList<ArrayList> allePopulationen = new ArrayList<>();
		allePopulationen.add(population1);
		allePopulationen.add(population2);
		allePopulationen.add(population3);
		allePopulationen.add(population4);
		allePopulationen.add(population5);
		allePopulationen.add(population6);
		allePopulationen.add(population7);
		allePopulationen.add(population8);
		allePopulationen.add(population9);
		allePopulationen.add(population10);
	}

	private static double[][] einlesenAbstandtabelleInMatrixSpeichern(double[][] abstandArray)
			throws FileNotFoundException {
		File fileAbstand = new File("abstand.txt");
		Scanner scanner = new Scanner(fileAbstand);
		String nextValue = null;
		int k = 0;
		while (scanner.hasNextLine()) {
			String[] zeile = new String[532]; // Willkürrliche Zahl, ich weiß nicht wie man das sonst machen kann
			String line = scanner.nextLine();
			zeile = line.split(" ");
			for (int i = 0; i < zeile.length; i++) {
				abstandArray[i][k] = Double.valueOf(zeile[i]);
			}
			k++;
		}
		scanner.close();
		return abstandArray;
	}

	private static void alleAbstaendeInTxtSpeichern(ArrayList<Stadt> tour, double[][] cool) throws IOException {
		for (int i = 0; i < tour.size(); i++) {
			for (int j = 0; j < tour.size(); j++) {
				cool[i][j] = tour.get(i).berechneAbstand(tour.get(j));
			}
		}

		File fileAbstand = new File("abstand.txt");
		fileAbstand.setWritable(true);
		fileAbstand.setReadable(true);
		FileWriter datei = new FileWriter(fileAbstand);
		BufferedWriter output = new BufferedWriter(datei);

		for (int i = 0; i < cool.length; i++) {
			for (int j = 0; j < cool.length; j++) {
				output.write(cool[i][j] + " ");
			}
			output.newLine();
		}
		output.flush();
		datei.close();
	}

	private static void tspRouteInArraySpeichern(ArrayList<Stadt> tour) throws FileNotFoundException {
		String[] teile = new String[3]; // 3, einmal nummer der koordinate und zweimal die koordinaten
		File file = new File("tsp.txt");
		Scanner scanner = new Scanner(file);
		String nextValue = null;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("NODE_COORD_SECTION".equals(line)) {
				while (scanner.hasNextLine()) {
					nextValue = scanner.nextLine();
					if ("EOF".equals(nextValue))
						break;
					teile = nextValue.split(" ");
					String stringNr = teile[0];
					String stringX = teile[1];
					String stringY = teile[2];
					int x, y, nr;
					x = Integer.valueOf(stringX);
					y = Integer.valueOf(stringY);
					nr = Integer.valueOf(stringNr) - 1;
					tour.add(new Stadt(nr, x, y));
				}
			}
		}
		scanner.close();
	}

	private static double berechneTourLaengeDurchBruteforce(ArrayList<Stadt> liste) {
		double gesamtStrecke = 0;
		for (int i = 0; i < liste.size() - 1; i++) {
			gesamtStrecke = gesamtStrecke + liste.get(i).berechneAbstand(liste.get(i + 1));
		}
		gesamtStrecke = gesamtStrecke + liste.get(liste.size() - 1).berechneAbstand(liste.get(0));
		return gesamtStrecke;
	}

	private static double berechneTourLaengeDurchAuslesen(double[][] alleAbstaende, ArrayList<Stadt> liste) {
		int ort1, ort2;
		double gesamtStrecke = 0;
		for (int i = 0; i < liste.size() - 1; i++) {
			ort1 = liste.get(i).getNr();
			ort2 = liste.get(i + 1).getNr();
			gesamtStrecke = gesamtStrecke + alleAbstaende[ort1][ort2];
		}
		ort1 = liste.get(liste.size() - 1).getNr();
		ort2 = liste.get(0).getNr();
		gesamtStrecke = gesamtStrecke + alleAbstaende[ort1][ort2];
		return gesamtStrecke;
	}
}
