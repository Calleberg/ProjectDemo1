package tda367.operation5a.game.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tda367.operation5a.Camera;
import tda367.operation5a.geo.Shape2D;

/**
 * 
 * 
 * @author Martin
 *
 */
public class Prop {
	
	private Shape2D collisionBox;
	private BufferedImage image;
	
	/**
	 * Creates a new prop with the specified image and collision box.
	 * @param image the image to use.
	 * @param collisionBox the collision box of this prop.
	 */
	public Prop(BufferedImage image, Shape2D collisionBox) {
		this.collisionBox = collisionBox;
		this.image = image;
	}
	
	/**
	 * Gives whether or not the prop is solid.
	 * @return <code>true</code> if the prop is solid.
	 */
	public boolean isSolid() {
		return (this.collisionBox != null);
	}
	
	/**
	 * Gives the collision box of this prop.
	 * @return the collision box of this prop.
	 */
	public Shape2D getCollisionBox() {
		return this.collisionBox;
	}
	
	/**
	 * Gives the x-coordinate of this prop on the map.
	 * @return the x-coordinate of this prop on the map.
	 */
	public float getX() {
		return this.getCollisionBox().getX();
	}
	
	/**
	 * Gives the y-coordinate of this prop on the map.
	 * @return the y-coordinate of this prop on the map.
	 */
	public float getY() {
		return this.getCollisionBox().getY();
	}
	
	/**
	 * Draws the prop with the specified graphics instance and camera.
	 * @param g the graphics instance to use.
	 * @param cam the camera to draw relative to.
	 */
	public void render(Graphics g, Camera cam) {
		g.drawImage(image, 
				(int)this.getX() * cam.getScale() - cam.getX(), (int)this.getY() * cam.getScale() - cam.getY(),
				cam.getScale(), cam.getScale(), null);
	}
}
