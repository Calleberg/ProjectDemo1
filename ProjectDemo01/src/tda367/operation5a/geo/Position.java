package tda367.operation5a.geo;

/**
 * Models a position in a 2D space.
 * The position can be moved to any position valid in
 * a 2D space.
 * 
 * @author Calleberg
 *
 */
public class Position {

	private float x, y;
	private float oldX, oldY;
	
	/**
	 * Creates a new position with the specified x- and y-coordinate.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public Position(float x, float y) {
		this.set(x, y);
	}
	
	/**
	 * Creates a new position with the same coordinate as the specified 
	 * position.
	 * @param position the position to copy from.
	 */
	public Position(Position position) {
		this.set(position.getX(), position.getY());
	}
	
	/**
	 * Gives the x-coordinate of this position.
	 * @return the x-coordinate.
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * Gives the y-coordinate of this position.
	 * @return the y-coordinate.
	 */
	public float getY() {
		return this.y;
	}
	
	public void rollBack() {
		this.x = oldX;
		this.y = oldY;
	}
	
	/**
	 * Moves the position the specified amount.
	 * @param dx the amount to move along the x-axis.
	 * @param dy the amount to move along the y-axis.
	 */
	public void move(float dx, float dy) {
		this.set(x + dx, y + dy);
	}
	
	/**
	 * Sets the position to the specified position.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public void set(float x, float y) {
		this.oldX = this.x;
		this.oldY = this.y;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return this.getClass().toString() + "[x=" + this.getX() + ",y=" + this.getY() + "]";
	}
}
