/**
 * 	class NBody
 */

public class NBody {
	public static String backgroundImage = "/images/starfield.jpg";
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);

		//StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundImage);

		for (Body b : bodies) {
			b.draw();
		}

		//StdDraw.show();
		//StdDraw.pause(2000);
	}

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
		Body[] bodies = new Body[count];

		for (int i = 0; i < count; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imageFilePath = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imageFilePath);
		}
		return bodies;
	}
}
