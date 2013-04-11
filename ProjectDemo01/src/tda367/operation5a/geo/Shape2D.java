package tda367.operation5a.geo;

/**
 * An interface for creating new geometrical shapes.
 * 
 * @author Calleberg
 *
 */
public interface Shape2D {

	/**
	 * Rotates the shape the specified angle with the specified position as
	 * the turning point.
	 * @param angle the angle in degrees to turn.
	 */
	public void rotate(double angle);
	
	/**
	 * The width of the shape.
	 * However, this will not change when moving or rotating the 
	 * shape as the width should be the same as when created and is 
	 * not to be confused with the bounding box.
	 * @return the width of the shape.
	 */
	public float getWidth();
	
	/**
	 * The height of the shape.
	 * However, this will not change when moving or rotating the 
	 * shape as the height should be the same as when created and is 
	 * not to be confused with the bounding box.
	 * @return the height of the shape.
	 */
	public float getHeight();
	
	/**
	 * Gives the x position of this shape.
	 * This can be implemented differently on different
	 * shapes.
	 * @return the x position of this shape.
	 */
	public float getX();
	
	/**
	 * Gives the y position of this shape.
	 * @return the y position of this shape.
	 */
	public float getY();
	
	/**
	 * Gives the x position of the shape.
	 * @return the position of the shape.
	 */
	public Position getPosition();
	
	/**
	 * Moves the shape the specified distance.
	 * @param dx the distance to move the shape on the x-axis.
	 * @param dy the distance to move the shape on the y-axis.
	 */
	public void move(float dx, float dy);
	
	/**
	 * Changes the position to the specified one.
	 * @param x the new x-coordinate.
	 * @param y the new y-coordinate.
	 */
	public void set(float x, float y);
	
	/**
	 * Gives all the lines that make up the shape.
	 * @return an array of all the line creating this shape.
	 */
	public SimpleLine2D[] getPolygon();
	
	/**
	 * Moves the shape back one step.
	 * NOTE: This can only be called once after each move.
	 */
	public void rollBack();
	
	public float getRotation();
}
