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
}
