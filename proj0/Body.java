/* 
 * 	class Body
 */

public class Body {
	/** 
	 * Constant value G 
	 */
	public static final double G = 6.67e-11;

	/** 
	 * Its current x position 
	 */
	public double xxPos;

	/** 
	 * Its current y position 
	 */
	public double yyPos;

	/** 
	 * Its current velocity in the x direction 
	 */
	public double xxVel;

	/** 
	 * Its current velocity in the y direction 
	 */
	public double yyVel;

	/** 
	 * Its mass 
	 */
	public double mass;

	/** 
	 * The name of the file that corresponds to the image that depicts the body 
	 */
	public String imgFileName;

	/**
	 * Constructor with given parameters
	 * @param xP	x position
	 * @param yP	y position
	 * @param xV	velocity in x direction
	 * @param yV	velocity in y direction
	 * @param m		the mass 
	 * @param img 	the name of the image file
	 */
	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**
	 * Constructor with a given Body instance
	 * @param b 	a Body instance
	 */
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/**
	 * Calculates the distance between the given Body instance and this Body instance
	 * @param b		a given Body instance
	 * @return		the distance between the two Body instance
	 */
	public double calcDistance(Body b) {
		double xxSquare = Math.pow(b.xxPos - xxPos, 2);
		double yySquare = Math.pow(b.yyPos - yyPos, 2);
		return Math.sqrt(xxSquare + yySquare);
	}

	/**
	 * Calculates the force exerted on this Body isntance by a given Body instance
	 * @param b		a given Body instance
	 * @return		the total force exerted on this Body instance by the given Body instance
	 */
	public double calcForceExertedBy(Body b) {
		double distance = calcDistance(b);
		return G * mass * b.mass / Math.pow(distance, 2);
	}

	/**
	 * Calculates the force exerted on this Body instance by a given Body instance in X direction
	 * @param b		a given Body instance
	 * @return		the force exerted in the X direction
	 */
	public double calcForceExertedByX(Body b) {
		double vecDistanceX = b.xxPos - xxPos;
		double distance = calcDistance(b);
		double force = calcForceExertedBy(b);
		return force * vecDistanceX / distance;
	}

	/**
	 * Calculates the force exerted on this Body instance by a given Body instance in Y direction
	 * @param b		a given Body instance
	 * @return		the force exerted in the Y direction
	 */
	public double calcForceExertedByY(Body b) {
		double vecDistanceY = b.yyPos - yyPos;
		double distance = calcDistance(b);
		double force = calcForceExertedBy(b);
		return force * vecDistanceY / distance;
	}

	/**
	 * Calculates the net force in X direction exerted on this Body instance by all bodies in the array
	 * @param bodies	a given array holds all the Body instances
	 * @return			the net force exerted in the X direction
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double netForceX = 0.0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				netForceX += this.calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	/**
	 * Calculates the net force in Y direction exerted on this Body instance by all bodies in the array
	 * @param bodies	a given array holds all the Body instances
	 * @return			the net force exerted in the Y direction
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double netForceY = 0.0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				netForceY += this.calcForceExertedByY(b);
			}
		}
		return netForceY;
	}
}
