public class Body {
	/** Its current x position */
	public double xxPos;
	/** Its current y position */
	public double yyPos;
	/** Its current velocity in the x direction */
	public double xxVel;
	/** Its current velocity in the y direction */
	public double yyVel;
	/** Its mass */
	public double mass;
	/** The name of the file that corresponds to the image that depicts the body */
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double xxSquare = Math.pow(b.xxPos - xxPos, 2);
		double yySquare = Math.pow(b.yyPos - yyPos, 2);

		return Math.sqrt(xxSquare + yySquare);
	}

	public double calcForceExertedBy(Body b) {
		double G = 6.67e-11;
		double distance = calcDistance(b);
		return G * mass * b.mass / Math.pow(distance, 2);
	}
}
