package tda367.operation5a.gamemode;
import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * 
 *
 * @author Calleberg
 *
 */
public abstract class Gamemode extends JPanel {

	private static final long serialVersionUID = 1L;	//default serialID.

	/**
	 * Creates a new game mode.
	 */
	public Gamemode() {
		super();
		this.setLayout(new BorderLayout());
		this.init();
	}
	
	/**
	 * Creates and loads all the instances needed for the game mode to run.
	 */
	protected abstract void init();
}
