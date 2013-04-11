package tda367.operation5a.geo;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A class implementing a line. 
 * The line can be moved and rotated.
 * NOTE: Some of the methods aren't implemented properly, however, these
 * methods are labelled.
 * 
 * @author Calleberg
 *
 */
public class SimpleLine2D extends Line2D implements Shape2D {

	private double		x1, y1,
	x2, y2;

	private double 		ox1, oy1,
	ox2, oy2;
	
	private float rotation = 0f;

	public SimpleLine2D(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Not used.
	 */
	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}

	@Override
	public double getX1() {
		return x1;
	}

	@Override
	public double getY1() {
		return y1;
	}

	@Override
	public Point2D getP1() {
		return new Point((int)x1, (int)y1);
	}

	@Override
	public double getX2() {
		return x2;
	}

	@Override
	public double getY2() {
		return y2;
	}

	@Override
	public Point2D getP2() {
		return new Point((int)x2, (int)y2);
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void rotate(double angle) {
		this.rotate(angle, 0, 0);
	}

	public void rotate(double angle, float origoX, float origoY) {
		double[] pt1 = {x1, y1};
		AffineTransform.getRotateInstance(Math.toRadians(angle), origoX, origoY)
		.transform(pt1, 0, pt1, 0, 1);
		x1 = pt1[0];
		y1 = pt1[1];

		double[] pt2 = {x2, y2};
		AffineTransform.getRotateInstance(Math.toRadians(angle), origoX, origoY)
		.transform(pt2, 0, pt2, 0, 1);
		x2 = pt2[0];
		y2 = pt2[1];
		
		this.rotation += angle;
	}

	/**
	 * Not used.
	 */
	@Override
	public float getWidth() {
		return 0;
	}

	/**
	 * Not used.
	 */
	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public float getX() {
		return (float)this.x1;
	}

	@Override
	public float getY() {
		return (float)this.y1;
	}
	
	@Override
	public Position getPosition() {
		return new Position(this.getX(), this.getY());
	}

	@Override
	public void move(float dx, float dy) {
		this.set(this.getX() + dx, this.getY() + dy);
	}
	
	@Override
	public void set(float x, float y) {
		ox1 = x1;
		oy1 = y1;
		ox2 = x2;
		oy2 = y2;
		this.x2 = x + x2-x1;
		this.y2 = y + y2-y1;
		this.x1 = x;
		this.y1 = y;
	}

	@Override
	public void rollBack(){
		this.x1 = ox1;
		this.y1 = oy1;
		this.x2 = ox2;
		this.y2 = oy2;
	}

	/**
	 * A SimpleLine2D is only made up of one line - itself.
	 */
	@Override
	public SimpleLine2D[] getPolygon() {
		return new SimpleLine2D[]{this};
	}
	
	@Override
	public String toString() {
		return "SimpleLine2D[x1=" + this.getX1() + ",y1=" + this.getY1() + ",x2=" + this.getX2() + ",y2=" + this.getY2() + "]";
	}

	@Override
	public float getRotation() {
		return this.rotation;
	}
}
