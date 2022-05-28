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

		ArrayList<Stadt> population = new ArrayList<>();
		population.addAll(originalTour);
		ArrayList<Population> allePopulationen = new ArrayList<>();
		Comparator<Population> laengenComparator = Population.sortiereDurchLaenge();
		
		Population p1 = new Population(population);
		Population p2 = new Population(population);
		Population p3 = new Population(population);
		Population p4 = new Population(population);
		Population p5 = new Population(population);
		Population p6 = new Population(population);
		Population p7 = new Population(population);
		Population p8 = new Population(population);
		Population p9 = new Population(population);
		Population p10 = new Population(population);
		Population p11 = new Population(population);
		Population p12 = new Population(population);
		Population p13 = new Population(population);
		Population p14 = new Population(population);
		Population p15 = new Population(population);
		Population p16 = new Population(population);
		Population p17 = new Population(population);
		Population p18 = new Population(population);
		Population p19 = new Population(population);
		Population p20 = new Population(population);
		Population p21 = new Population(population);
		Population p22 = new Population(population);
		Population p23 = new Population(population);
		Population p24 = new Population(population);
		Population p25 = new Population(population);
		Population p26 = new Population(population);
		Population p27 = new Population(population);
		Population p28 = new Population(population);
		Population p29 = new Population(population);
		Population p30 = new Population(population);
		Population p31 = new Population(population);
		Population p32 = new Population(population);
		
		allePopulationen.add(p1);
		allePopulationen.add(p2);
		allePopulationen.add(p3);
		allePopulationen.add(p4);
		allePopulationen.add(p5);
		allePopulationen.add(p6);
		allePopulationen.add(p7);
		allePopulationen.add(p8);
		allePopulationen.add(p9);
		allePopulationen.add(p10);
		allePopulationen.add(p11);
		allePopulationen.add(p12);
		allePopulationen.add(p13);
		allePopulationen.add(p14);
		allePopulationen.add(p15);
		allePopulationen.add(p16);
		allePopulationen.add(p17);
		allePopulationen.add(p18);
		allePopulationen.add(p19);
		allePopulationen.add(p20);
		allePopulationen.add(p21);
		allePopulationen.add(p22);
		allePopulationen.add(p23);
		allePopulationen.add(p24);
		allePopulationen.add(p25);
		allePopulationen.add(p26);
		allePopulationen.add(p27);
		allePopulationen.add(p28);
		allePopulationen.add(p29);
		allePopulationen.add(p30);
		allePopulationen.add(p31);
		allePopulationen.add(p32);
		
		
		for (Population p : allePopulationen) {
			Collections.shuffle(p.getTour());
			p.setTourLaenge(berechneTourLaengeDurchAuslesen(alleAbstaende, p.getTour()));
		}
		
		Collections.sort(allePopulationen, laengenComparator);
		
		//Genetischer Algirithmus kommt dann hier!
		
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
