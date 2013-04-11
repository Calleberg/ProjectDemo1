package tda367.operation5a.game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import tda367.operation5a.game.sprite.Player;
import tda367.operation5a.game.sprite.Sprite;
import tda367.operation5a.game.world.Tile;
import tda367.operation5a.game.world.World;
import tda367.operation5a.game.world.WorldBuilder;
import tda367.operation5a.geo.Rectangle2D;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class GameModel {
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private World world;				//
	private Player player;				//
	
	/**
	 * Creates a new model.
	 */
	public GameModel() {
		WorldBuilder wb = new WorldBuilder();
		Tile[][] map = wb.createWorld();
		this.world = new World(map);
		this.player = new Player(new Rectangle2D(2, 2, 0.6f, 0.6f));
		world.addSprite(player);
	}
	
	/**
	 * Gives the player.
	 * @return the player.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Gives the world.
	 * @return the world.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Adds the specified sprite to model.
	 * @param sprite the sprite to add.
	 */
	public void addSprite(Sprite sprite) {
		this.world.addSprite(sprite);
	}
	
	/**
	 * Removes the specified sprite from the model.
	 * @param sprite the model to remove.
	 */
	public void removeSprite(Sprite sprite) {
		this.world.removeSprite(sprite);
	}
	
	/**
	 * Updates the model and tells all the views to repaint.
	 */
	public void update() {
		this.pcs.firePropertyChange("", 0, 1);
	}
	
	/**
	 * Adds the specified listener to the model. The listener will be called
	 * each time the model updates.
	 * @param pcl the listener to add.
	 */
	public void addListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Removes the specified listener.
 	 * @param pcl the listener to remove.
	 */
	public void removeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
	}
}
