package tda367.operation5a.geo;

/**
 * A static class with tools to test if different shapes is colliding.
 * 
 * @author Calleberg
 *
 */
public class Collision {

	/**
	 * Checks if the two specified shapes collided.
	 * @param shape1 the first shape.
	 * @param shape2 the second shape.
	 * @return <code>true</code> if the two shapes collided.
	 */
	public static boolean collide(Shape2D shape1, Shape2D shape2) {
		SimpleLine2D[] 	lines1 = shape1.getPolygon(),
						lines2 = shape2.getPolygon();
		
		for(int i = 0; i < lines1.length; i++) {
			for(int j = 0; j < lines2.length; j++) {
				if(lines1[i].intersectsLine(lines2[j])) {
					System.out.println("Collision between:");
					System.out.println("\t" + shape1.toString());
					System.out.println("  and\t" + shape2.toString());
					return true;
				}//if
			}//for 2
		}//for 1
		return false;
	}
}
