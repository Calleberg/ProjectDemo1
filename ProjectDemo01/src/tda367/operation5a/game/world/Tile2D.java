package tda367.operation5a.game.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tda367.operation5a.Camera;
import tda367.operation5a.Resources;
import tda367.operation5a.geo.Rectangle2D;
import tda367.operation5a.geo.Shape2D;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class Tile2D extends Tile {

	private static BufferedImage[] textureFloor = Resources.splitImages("resources/floor.png", 10, 10); 
	private Prop prop1;
	
	/**
	 * Creates a new Tile2D instance with the specified floor texture and position.
	 * @param floor the floor to use.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public Tile2D(int floor, int x, int y) {
		super(floor, x, y);
	}
	
	public static BufferedImage[] getFloorTextures() {
		return textureFloor;
	}
	
	/**
	 * Sets the prop on this tile.
	 * @param prop1 the prop to use.
	 */
	public void setProp(Prop prop1) {
		this.prop1 = prop1;
		if(prop1 != null) {
			prop1.getCollisionBox().move(this.getX(), this.getY());
		}
	}
	
	/**
	 * Gives the prop on this tile.
	 * @return the prop on this tile.
	 */
	public Prop getProp() {
		return this.prop1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Shape2D[] getCollisionBoxes() {	//TODO fixa till
		Shape2D[] temp = new Shape2D[3];
		if(this.hasNorthWall()) {
			temp[0] = new Rectangle2D(this.getX() - 0.1f, this.getY() - 0.1f, 1.2f, 0.2f);
		}
		if(this.hasEastWall()) {
			temp[1] = new Rectangle2D(this.getX() - 0.1f, this.getY() - 0.1f, 0.2f, 1.2f);
		}
		if(this.prop1 != null) {
			temp[2] = prop1.getCollisionBox();
		}
		return temp;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderBase(Graphics g, Camera cam) {
		g.drawImage(textureFloor[this.getFloor()], 
				this.getX() * cam.getScale() - cam.getX(), this.getY() * cam.getScale() - cam.getY(),
				cam.getScale(), cam.getScale(), null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderComponents(Graphics g, Camera cam) {	//TODO fixa till
		if(this.prop1 != null) {
			this.prop1.render(g, cam);
		}
		
		if(this.hasNorthWall()) {
			g.fillRect(Math.round((this.getX() - 0.1f) * cam.getScale()) - cam.getX(), Math.round((this.getY() - 0.1f) * cam.getScale()) - cam.getY(), 
					Math.round(cam.getScale() * 1.2f), Math.round(cam.getScale() * 0.2f));
		}
		if(this.hasEastWall()) {
			g.fillRect(Math.round((this.getX() - 0.1f) * cam.getScale()) - cam.getX(), Math.round((this.getY() - 0.1f) * cam.getScale()) - cam.getY(), 
					Math.round(cam.getScale() * 0.2f), Math.round(cam.getScale() * 1.2f));
		}
	}
}
