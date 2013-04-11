package tda367.operation5a.geo;

/**
 * A rectangle which can be moved and rotated around its origin.
 * 
 * @author Calleberg
 *
 */
public class Rectangle2D implements Shape2D {
	
	private SimpleLine2D[] lines;		//all the lines of this shape.
	private Position rotationOrigin;	//the position to rotate around.
	private Position position;
	
	private float width, height;
	
	public Rectangle2D(float x, float y, float w, float h) {
		this(x, y, w, h, x + w/2, y + h/2);
	}
	
	public Rectangle2D(float x, float y, float w, float h, float rotationX, float rotationY) {
		this.width = w;
		this.height = h;
		position = new Position(x, y);
		lines = new SimpleLine2D[4];
		lines[0] = new SimpleLine2D(x, y, x + w, y);
		lines[1] = new SimpleLine2D(x + w, y, x + w, y + h);
		lines[2] = new SimpleLine2D(x + w, y + h, x, y + h);
		lines[3] = new SimpleLine2D(x, y + h, x, y);
		rotationOrigin = new Position(x + rotationX, y + rotationY);
	}
	
	@Override
	public void rotate(double angle) {
		for(int i = 0; i < lines.length; i++) {
			lines[i].rotate(angle, rotationOrigin.getX(), rotationOrigin.getY());
		}
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public float getX() {
		return position.getX();
	}

	@Override
	public float getY() {
		return position.getY();
	}
	
	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void move(float dx, float dy) {
		for(int i = 0; i < lines.length; i++) {
			lines[i].move(dx, dy);
		}
		rotationOrigin.move(dx, dy);
		position.move(dx, dy);
	}
	
	@Override
	public void set(float x, float y) {
		this.move(this.getX() - x, this.getY() - y);
	}
	
	@Override
	public void rollBack(){
		for(int i = 0; i < lines.length; i++) {
			lines[i].rollBack();
		}
		rotationOrigin.rollBack();
		position.rollBack();
	}

	@Override
	public SimpleLine2D[] getPolygon() {
		return this.lines;
	}
	
	@Override
	public String toString() {
		return this.getClass().toString() + "[x=" + this.getX() + ",y=" + this.getY() + ",width=" + this.getWidth() + ",height=" + this.getHeight() + "]";
	}

	@Override
	public float getRotation() {
		return this.lines[0].getRotation();
	}
}