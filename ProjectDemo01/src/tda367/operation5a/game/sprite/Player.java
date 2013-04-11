package tda367.operation5a.game.sprite;

import java.awt.Color;
import java.awt.Graphics;

import tda367.operation5a.Camera;
import tda367.operation5a.geo.Shape2D;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class Player extends Sprite {

	/**
	 * Creates a new player with the specified collision box.
	 * @param collisionBox
	 */
	public Player(Shape2D collisionBox) {
		super(collisionBox);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(Graphics g, Camera cam) {
		g.setColor(Color.BLUE);
		g.fillRect((int)(this.getX() * cam.getScale()) - cam.getX(), (int)(this.getY() * cam.getScale()) - cam.getY(), 
				(int)(cam.getScale() * this.getCollisionBox().getWidth()), 
				(int)(cam.getScale() * this.getCollisionBox().getHeight()));
	}
}
