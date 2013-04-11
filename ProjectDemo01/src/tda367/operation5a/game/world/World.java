package tda367.operation5a.game.world;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import tda367.operation5a.Camera;
import tda367.operation5a.game.sprite.Sprite;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class World {

	private Tile[][] tiles;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	
	/**
	 * Creates a new world.
	 */
	public World(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	/**
	 * Adds the specified tile to the specified position.
	 * @param x the x-coordinate on the map.
	 * @param y the y-coordinate on the map.
	 * @param tile the tile to add to the map.
	 * @return <code>false</code> if the specified position was invalid,
	 * otherwise <code>true</code>.
	 */
	public boolean setTileAt(int x, int y, Tile tile) {
		if(this.validPosition(x, y)) {
			this.tiles[x][y] = tile;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Gives the tile at the specified position.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 * @return gives the tile at the specified position.
	 */
	public Tile getTileAt(int x, int y) {
		return tiles[x][y];
	}
	
	/**
	 * Gives a list of all the sprites on this world.
	 * @return a list of all the sprites on this world.
	 */
	public List<Sprite> getSprites() {
		return this.sprites;
	}
	
	/**
	 * Adds the specified sprite to the world.
	 * @param sprite the sprite to add.
	 */
	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
		sprite.setWorld(this);
	}
	
	/**
	 * Removes the specified sprite from the world.
	 * @param sprite the sprite to remove.
	 */
	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
		sprite.setWorld(null);
	}
	
	/**
	 * Checks if the specified position is valid,
	 * @param x the x-coordinate to check.
	 * @param y the y-coordinate to check.
	 * @return <code>true</code> if the position is valid.
	 */
	public boolean validPosition(int x, int y) {
		return !(x < 0 || y < 0 || x >= tiles.length || y >= tiles[0].length);
	}
	
	public void render(Graphics g, Camera cam, int startX, int startY, int endX, int endY) {
		//Draws the tiles shown by the view.
		for(int x = startX; x < endX; x++) {
			for(int y = startY; y < endY; y++) {
				if(validPosition(x, y)) {
					getTileAt(x, y).renderBase(g, cam);
				}
			}
		}
		//Draws the tiles shown by the view.
		for(int x = startX; x < endX; x++) {
			for(int y = startY; y < endY; y++) {
				if(validPosition(x, y)) {
					getTileAt(x, y).renderComponents(g, cam);
				}
			}
		}
		//Draws all the sprites shown by the view.
		for(int i = 0; i < getSprites().size(); i++) {
			getSprites().get(0).render(g, cam);
		}
	}
}
