public class Stadt {

	private int x;
	private int y;
	private int nr;
	private double tourLaenge;

	public Stadt(int nr, int x, int y) {
		this.x = x;
		this.y = y;
		this.nr = nr;
		this.tourLaenge = 0;
	}

	public double berechneAbstand(Stadt nachbar) {
		double abstandx, abstandy;
		abstandx = this.getX() - nachbar.getX();
		abstandy = this.getY() - nachbar.getY();
		double abstandInsgesamt;
		abstandInsgesamt = Math.sqrt(Math.pow(abstandy, 2) + Math.pow(abstandx, 2));
		return abstandInsgesamt;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	@Override
	public String toString() {
		return "Die Stadt besitzt folgende Koordinaten: X " + getX() + " und Y: " + getY();
	}
}