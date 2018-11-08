import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
	// create a seam carver object based on the given picture
	Picture picture;
	int width;
	int height;
	double energy[][];
	public SeamCarver(Picture picture) {
		this.picture = picture;
		this.width = picture.width();
		this.height = picture.height();
		this.energy = new double[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height ; j++) {
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					energy[i][j] = 1000.0;
				} else {
					energy[i][j] = squareroot(i, j);
				}
			}
		}

	}
	public double squareroot(int i, int j) {
		int xaxis = xaxis(i, j);
		int yaxis = yaxis(i, j);
		double energy1 = Math.sqrt(xaxis + yaxis);
		return energy1;
	}
	public int xaxis(int i, int j) {
		Color element1 = picture.get(i - 1, j);
		Color element2 = picture.get(i + 1, j);
		int r = element1.getRed() - element2.getRed();
		int g = element1.getGreen() - element2.getGreen();
		int b = element1.getBlue() - element2.getBlue();
		int xaxis = (r * r) + (g * g) + (b * b);
		return xaxis;
	}
	public int yaxis(int i, int j) {
		Color element1 = picture.get(i, j-1);
		Color element2 = picture.get(i, j+1);
		int r = element1.getRed() - element2.getRed();
		int g = element1.getGreen() - element2.getGreen();
		int b = element1.getBlue() - element2.getBlue();
		int yaxis = (r * r) + (g * g) + (b * b);
		return yaxis;
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return energy[x][y];
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}