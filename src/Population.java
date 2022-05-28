import java.util.ArrayList;
import java.util.Comparator;

public class Population {
	
	private ArrayList<Stadt> tour = new ArrayList<>();
	private double tourLaenge;
	
	Population(ArrayList<Stadt> tour) {
		this.tour = tour;
		this.tourLaenge = 0;
	}
	
	public static Comparator<Population> sortiereDurchLaenge() {
		return new Comparator<Population>() {
			@Override
			public int compare(Population p1, Population p2) {
				return (int) (p1.getTourLaenge() - p2.getTourLaenge());
			}
		};
	}
	
	
	
	public ArrayList<Stadt> getTour() {
		return tour;
	}

	public void setTour(ArrayList<Stadt> tour) {
		this.tour = tour;
	}

	public double getTourLaenge() {
		return tourLaenge;
	}

	public void setTourLaenge(double tourLaenge) {
		this.tourLaenge = tourLaenge;
	}
	
	
	
}
