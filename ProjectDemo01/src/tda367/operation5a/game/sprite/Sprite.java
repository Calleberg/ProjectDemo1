package tda367.operation5a.game.sprite;

import java.awt.Graphics;

import tda367.operation5a.Camera;
import tda367.operation5a.game.world.World;
import tda367.operation5a.geo.Collision;
import tda367.operation5a.geo.Position;
import tda367.operation5a.geo.Shape2D;

/**
 * 
 *
 * @author Calleberg
 *
 */
public abstract class Sprite {

	private float dir;		//the angle in radians.
	private World world;	//the world to move about on.
	private Shape2D collisionBox;	//the sprites collision box.
	
	/**
	 * Creates a new sprite with the specified collision box.
	 * @param collisionBox
	 */
	public Sprite(Shape2D collisionBox) {
		this.collisionBox = collisionBox;
	}
	
	/**
	 * Sets the world this sprite should move about on.
	 * @param world the world to move about on.
	 */
	public void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * Sets the collision box of this sprite.
	 * @param collisionBox the new collision box.
	 */
	public void setCollisionBox(Shape2D collisionBox) {
		this.collisionBox = collisionBox;
	}
	
	/**
	 * Gives the collision box of this sprite.
	 * @return the collision box of this sprite.
	 */
	public Shape2D getCollisionBox() {
		return this.collisionBox;
	}
	
	/**
	 * Gives the position of the sprite.
	 * @return the position of the sprite.
	 */
	public Position getPosition() {
		return this.getCollisionBox().getPosition();
	}
	
	/**
	 * Gives the position of the center of the sprite.
	 * @return the position of the center of the sprite.
	 */
	public Position getCenterPosition() {
		return new Position(this.getCollisionBox().getX() + this.getCollisionBox().getWidth()/2, 
				this.getCollisionBox().getY() + this.getCollisionBox().getHeight()/2);
	}
	
	/**
	 * Sets the direction this sprite is facing.
	 * @param dir the direction to face.
	 */
	public void setDirection(float dir) {
		this.dir = dir;
	}
	
	/**
	 * Moves the sprite in the direction the sprite is facing.
	 */
	public void moveForward() {
		this.moveTo(this.getX() + (float)(Math.cos(dir) * 0.07f), this.getY());
		if(invalidPosition()) {
			this.rollBack();
		}
		this.moveTo(this.getX(), this.getY() + (float)(-Math.sin(dir) * 0.07f));
		if(invalidPosition()) {
			this.rollBack();
		}
	}
	
	/**
	 * Checks if the sprite is currently in an invalid position.
	 * @return <code>true</code> if the sprite is currently in an invalid position.
	 */
	public boolean invalidPosition() {
		for(int x = (int)this.getX() - 1; x < (int)this.getX() + 2; x++) {
			for(int y = (int)this.getY() - 1; y < (int)this.getY() + 2; y++) {
				if(world.validPosition(x, y)) {
					Shape2D shapes[] = world.getTileAt(x, y).getCollisionBoxes();
					for(int i = 0; i < shapes.length; i++) {
						if(shapes[i] != null && Collision.collide(this.getCollisionBox(), shapes[i])) {
							return true;
						}//if
					}//for
				}//if
			}//y
		}//x
		return false;
	}
	
	/**
	 * Sets the position of the sprite to the specified one.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public void moveTo(float x, float y) {
		this.collisionBox.set(x, y);
	}
	
	/**
	 * Moves the sprite back to a previous position.
	 * Note: can only be called once after each move.
	 */
	public void rollBack() {
		this.collisionBox.rollBack();
	}
	
	/**
	 * Gives the x-coordinate on the map.
	 * @return the x-coordinate on the map.
	 */
	public float getX() {
		return this.collisionBox.getX();
	}
	
	/**
	 * Gives the y-coordinate on the map.
	 * @return the y-coordinate on the map.
	 */
	public float getY() {
		return this.collisionBox.getY();
	}
	
	/**
	 * Draws the sprite with the specified graphics instance and relative to the camera
	 * specified.
	 * @param g the graphics instance to use.
	 * @param cam the camera to draw the sprite relative to.
	 */
	public abstract void render(Graphics g, Camera cam);
}
