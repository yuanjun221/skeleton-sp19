/**
 * 	class NBody
 */

public class NBody {
	/**
	 * Read radius from a given file
	 * @param fileName		a string represent the directory and file name
	 * @return				the raduis read from the file
	 */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}
}
