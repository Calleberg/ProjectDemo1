package tda367.operation5a;

import java.awt.Dimension;

import tda367.operation5a.geo.Position;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class Camera {

	private int scale;		//the scale at which the camera is at.
	private int x, y;		//the position of the camera.
	private int min = 10, 
				max = 100;	//the minimum and maximum scale values.
	
	/**
	 * Creates a new camera with the specified position and scale.
	 * @param x the x-coordinate of the camera.
	 * @param y the y-coordinate of the camera.
	 * @param scale the scale of the camera.
	 */
	public Camera(int x, int y, int scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	/**
	 * Sets the maximum scale value this camera can be at.
	 * If the specified value is less or equals to 0; the minimum value
	 * won't be set.
	 * @param max
	 */
	public void setMaximum(int max) {
		if(min <= 0) {
			this.max = max;
		}
	}
	
	/**
	 * Sets the minimum scale value this camera can be at.
	 * If the specified value is less or equals to 0; the minimum value
	 * won't be set.
	 * @param min the minimum scale.
	 */
	public void setMinimum(int min) {
		if(min <= 0) {
			this.min = min;
		}
	}

	/**
	 * Centers the camera to the specified position.
	 * @param pos
	 * @param size the size of the view.
	 */
	public void setToCenter(Position pos, Dimension size) {
		this.x = (int)(pos.getX() * this.getScale() - size.getWidth()/2);
		this.y = (int)(pos.getY() * this.getScale() - size.getHeight()/2);
	}
	
	/**
	 * Sets the scale of the camera.
	 * However, 10 is the minimum.
	 * @param scale the new scale.
	 */
	public void setScale(int scale) {
		if(scale < min) {
			this.scale = min;
		}else if(scale > max){
			this.scale = max;
		}else{
			this.scale = scale;
		}
	}
	
	/**
	 * Gives the current scale.
	 * @return the current scale.
	 */
	public int getScale() {
		return this.scale;
	}
	
	/**
	 * Zooms in one step.
	 */
	public void zoomIn() {
		this.setScale(scale + scale/10);
	}
	
	/**
	 * Zooms out one step.
	 */
	public void zoomOut() {
		this.setScale(scale - scale/10);
	}
	
	/**
	 * Gives the x-coordinate of the camera.
	 * @return the x-coordinate of the camera.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gives the y-coordinate of the camera.
	 * @return the y-coordinate of the camera.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Moves the camera the specified distance.
	 * @param dx the distance to move along the x-axis.
	 * @param dy the distance to move along the y-axis.
	 */
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	/**
	 * Scales the camera according to the distance the mouse wheel have moved.
	 * @param mouseWheelDistance the distance the mouse wheel have moved.
	 */
	public void scale(int mouseWheelDistance) {
		if(mouseWheelDistance != 0) {
			if(mouseWheelDistance < 0) {
				zoomIn();
			}else{
				zoomOut();
			}
		}
	}
}
