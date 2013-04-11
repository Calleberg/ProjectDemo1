package tda367.operation5a.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import tda367.operation5a.Camera;
import tda367.operation5a.Input;
import tda367.operation5a.Window;
import tda367.operation5a.gamemode.Gamemode;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class GameView extends JPanel implements PropertyChangeListener, MouseWheelListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;	//default serialID.
	private GameModel model;							//the model to draw.
	private Camera camera;								//the camera on this view.
	private GameController ctrl;						//the controller handling the model.
	
	/**
	 * Creates a new game view with the specified model and the model's controller.
	 * @param model the model to draw.
	 * @param ctrl the controller to the model.
	 */
	public GameView(GameModel model, GameController ctrl, Gamemode gamemode) {
		super();
		this.model = model;
		this.ctrl = ctrl;
		this.camera = new Camera(0, 0, 40);
		this.setIgnoreRepaint(true);
		gamemode.addMouseMotionListener(this);
		gamemode.addMouseWheelListener(this);
		
		this.setLayout(new FlowLayout());
		Minimap minimap = new Minimap(model);
		minimap.setPreferredSize(new Dimension(100, 100));
		this.add(minimap);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		if(camera.getScale() < 40) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
		model.getWorld().render(g2d, camera, (int)this.translateX(0), (int)this.translateY(0), 
				(int)this.translateX(this.getWidth()) + 1, (int)this.translateY(this.getHeight()) + 1);
		g.setColor(Color.BLACK);
		
		g.drawString("camera scale=" + camera.getScale(), 0, 10);
		g.drawString("camera x=" + camera.getX(), 0, 20);
		g.drawString("camera y=" + camera.getY(), 0, 30);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Input input = Window.getInstance().getInput();
		if(input.isPressed(KeyEvent.VK_UP)) {
			camera.move(0, -5);
		}
		if(input.isPressed(KeyEvent.VK_DOWN)) {
			camera.move(0, 5);
		}
		if(input.isPressed(KeyEvent.VK_LEFT)) {
			camera.move(-5, 0);
		}
		if(input.isPressed(KeyEvent.VK_RIGHT)) {
			camera.move(5, 0);
		}
		camera.setToCenter(model.getPlayer().getCenterPosition(), this.getSize());
		
		this.repaint();
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

	/////////////////// Input
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		camera.scale(e.getWheelRotation());
		camera.setToCenter(model.getPlayer().getCenterPosition(), this.getSize());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ctrl.handleMouseAt(translateX(e.getX()), translateY(e.getY()));
	}
}
