package tda367.operation5a.game;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import tda367.operation5a.Camera;
import tda367.operation5a.game.world.Tile;
import tda367.operation5a.game.world.Tile2D;

public class Minimap extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private Camera camera;
	private GameModel model;
	
	public Minimap(GameModel model) {
		this.model = model;
		this.camera = new Camera(0, 0, 3);
		this.camera.setMinimum(1);
		this.camera.setMaximum(10);
		model.addListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Draws the tiles shown by the view.
		for(int x = (int)translateX(0); x < translateX(getWidth()); x++) {
			for(int y = (int)translateY(0); y < translateY(getHeight()); y++) {
				if(model.getWorld().validPosition(x, y)) {
					Tile t = model.getWorld().getTileAt(x, y);
					g.drawImage(Tile2D.getFloorTextures()[t.getFloor()], (int)(x * camera.getScale()) - camera.getX(),
							(int)(y * camera.getScale()) - camera.getY(), 
							camera.getScale(), camera.getScale(), null);
					if(t.hasNorthWall()) {
						g.drawLine((int)(x * camera.getScale()) - camera.getX(),
								(int)(y * camera.getScale()) - camera.getY(),
								(int)((x+1) * camera.getScale()) - camera.getX(),
								(int)(y * camera.getScale()) - camera.getY());
					}
					if(t.hasEastWall()) {
						g.drawLine((int)(x * camera.getScale()) - camera.getX(),
								(int)(y * camera.getScale()) - camera.getY(),
								(int)(x * camera.getScale()) - camera.getX(),
								(int)((y+1) * camera.getScale()) - camera.getY());
					}
				}
			}
		}
	}
	
	/**
	 * Translates the specified screen position to a map position.
	 * @param screenX the position on the window.
	 * @return the position on the map.
	 */
	public float translateX(int screenX) {
		return (screenX + camera.getX())/(float)camera.getScale();
	}
	
	/**
	 * Translates the specified screen position to a map position.
	 * @param screenY the position on the window.
	 * @return the position on the map.
	 */
	public float translateY(int screenY) {
		return (screenY + camera.getY())/(float)camera.getScale();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		camera.setToCenter(model.getPlayer().getPosition(), this.size());
		
		this.repaint();
	}
}
