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
		int len = bodies.length;

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);

		/**
		 * For each time through the loop
		 */
		for (double time = 0; time <= T; time += dt) {
			/**
			 * Calculate the net x and y forces for each Body, storing these
			 * in the xForces and yForces arrays respectively.
			 */
			double xForces[] = new double[len];
			double yForces[] = new double[len];
			for (int i = 0; i < len; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			/**
			 * Update each body's position, velocity and acceleration
			 * after calculating the net forces for every Body.
			 */
			for (int i = 0; i < len; i++) {
				bodies[i].update(dt, xForces[i], yForces[i]);
			}

			/**
			 * Draw the background image.
			 */
			StdDraw.clear();
			StdDraw.picture(0, 0, backgroundImage);

			/**
			 * Draw all of the Bodys.
			 */
			for (Body b : bodies) {
				b.draw();
			}

			/**
			 * Show the offscreen buffer.
			 */
			StdDraw.show();
			/**
			 * Pause the animation for 10 milliseconds.
			 */
			StdDraw.pause(10);
		}

		/**
		 * Print the Universe
		 */
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}

	/**
	 * Read radius from a given file.
	 * @param fileName		a string represent the directory with the file name
	 * @return				the raduis read from the file
	 */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}

	/**
	 * Create a array which holds Body instances read from a given file.
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
