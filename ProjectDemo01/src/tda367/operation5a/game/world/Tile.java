package tda367.operation5a.game.world;

import java.awt.Graphics;

import tda367.operation5a.Camera;
import tda367.operation5a.geo.Shape2D;

/**
 * 
 *
 * @author Calleberg
 *
 */
public abstract class Tile {

	private int floor;
	private int x, y;	//coordinate
	private boolean northWall, eastWall;
	private boolean solid;
	
	/**
	 * Creates a new tile with the specified floor.
	 * @param floor the floor to use.
	 */
	public Tile(int floor, int x, int y) {
		this.floor = floor;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the solid state of the tile.
	 * @param solid the new state of the tile.
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
	/**
	 * Gives the solid state of the tile.
	 * @return <code>true</code> if the tile is completely solid.
	 */
	public boolean isSolid() {
		return this.solid;
	}
	
	/**
	 * Sets if the tile has a north wall.
	 * @param hasWall the state of the north wall.
	 */
	public void setNorthWall(boolean hasWall) {
		this.northWall = hasWall;
	}
	
	/**
	 * Sets if the tile has an east wall.
	 * @param hasWall the state of the east wall.
	 */
	public void setEastWall(boolean hasWall) {
		this.eastWall = hasWall;
	}
	
	/**
	 * Gives whether or not the tile has a north wall.
	 * @return <code>true</code> if the tile has a north wall.
	 */
	public boolean hasNorthWall() {
		return this.northWall;
	}
	
	/**
	 * Gives whether or not the tile has an east wall.
	 * @return <code>true</code> if the tile has an east wall.
	 */
	public boolean hasEastWall() {
		return this.eastWall;
	}
	
	/**
	 * Sets the floor on this tile to the specified one.
	 * @param floor the new floor to use.
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/**
	 * Gives the current floor on this tile.
	 * @return the current floor on this tile.
	 */
	public int getFloor() {
		return this.floor;
	}
	
	/**
	 * Gives the x-coordinate of this tile.
	 * @return the x-coordinate of this tile.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gives the y-coordinate of this tile.
	 * @return the y-coordinate of this tile.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Gives an array of collision boxes associated with this tile.
	 * @return an array of collision boxes associated with this tile.
	 */
	public abstract Shape2D[] getCollisionBoxes();
	
	/**
	 * Draws the tile with the specified graphics instance and relative to the 
	 * specified camera.
	 * @param g the graphics instance to use.
	 * @param cam the camera.
	 */
	public abstract void renderBase(Graphics g, Camera cam);
	
	/**
	 * Draws the tile's components with the specified graphics instance and relative to
	 * the specified camera.
	 * @param g the graphics instance to use.
	 * @param cam the camera to draw relative to.
	 */
	public abstract void renderComponents(Graphics g, Camera cam);
}
