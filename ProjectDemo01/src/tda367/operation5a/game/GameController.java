package tda367.operation5a.game;

import java.awt.event.KeyEvent;
import tda367.operation5a.Input;
import tda367.operation5a.Window;

/**
 * 
 *
 * @author Calleberg
 *
 */
public class GameController extends Thread {

	private Input input = Window.getInstance().getInput();
	private GameModel model;		//the model to work on.
	private int sleep;				//the amount of time in ms to sleep between updates.
	
	/**
	 * Creates a new game controller controlling the specified model and updating with 
	 * the specified FPS.
	 * @param model the model to handle.
	 * @param FPS the FPS to draw at.
	 */
	public GameController(GameModel model, int FPS) {
		this.model = model;
		this.sleep = 1000 / FPS;
		this.start();
	}
	
	@Override
	public void run() {
		while(true) {
			this.update();
			try{
				Thread.sleep(sleep);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Updates the model.
	 */
	private void update() {
		if(input.isPressed(KeyEvent.VK_E)) {
			System.out.println("E");
		}
		if(input.isPressed(KeyEvent.VK_W)) {
			model.getPlayer().moveForward();
		}
		
		this.model.update();	//Should be last as this will call a repaint.
	}
	
	/**
	 * Handles a mouse event at the specified position on the map.
	 * @param x the x-coordinate relative to the map.
	 * @param y the y-coordinate relative to the map.
	 */
	public void handleMouseAt(float x, float y) {
		float dx = model.getPlayer().getX() - x + model.getPlayer().getCollisionBox().getWidth()/2;
		float dy = model.getPlayer().getY() - y + model.getPlayer().getCollisionBox().getHeight()/2;
		float dir = (float)Math.atan(dy/dx);
		if(dx < 0) {
			dir -= (float)(Math.PI);
		}
		model.getPlayer().setDirection(-dir);
	}
}
