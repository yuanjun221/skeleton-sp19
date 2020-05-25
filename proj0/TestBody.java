/**
 *  Tests the Body class.
 */
public class TestBody {

    /**
     *  Tests the Body class by creating two bodies and printing out the pariwise force between them.
     */
    public static void main(String[] args) {
        Body b1 = new Body(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(3.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");

        double force = b1.calcForceExertedBy(b2);
        double forceX = b1.calcForceExertedByX(b2);
        double forceY = b1.calcForceExertedByY(b2);

        System.out.println("The pairwise force between Body b1 and b2 is: " + force);
        System.out.println("The pairwise force in x direction is: " + forceX);
        System.out.println("The pairwise force in y direciton is: " + forceY);
    }
}
