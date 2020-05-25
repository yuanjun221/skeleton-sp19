/**
 * 	class NBody
 */

public class NBody {
	/**
	 * Read radius from a given file
	 * @param fileName		a string represent the directory with the file name
	 * @return				the raduis read from the file
	 */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}

	/**
	 * Create a array which holds Body instances read from a given file
	 * @param fileName		a string represent the directory with the file name
	 * @return				a array holds Body instances
	 */
	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int count = in.readInt();
		in.readDouble();
		Body[] bList = new Body[count];

		for (int i = 0; i < count; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imageFilePath = in.readString();
			bList[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imageFilePath);
		}
		return bList;
	}
}
