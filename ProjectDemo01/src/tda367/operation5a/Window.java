package tda367.operation5a;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tda367.operation5a.gamemode.Gamemode;

/**
 * A singleton object which holds the window.
 * The window will not be displayed if there is no game mode specified.
 *
 * @author Calleberg
 *
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;				//the panel everything is drawn on.
	private static Window instance;			//the singleton instance.
	private Input input;					//holder of all the inputs.

	/*
	 * Singleton constructor.
	 */
	private Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.contentPane = new JPanel();
		this.setContentPane(contentPane);
		input = new Input();
	}
	
	/**
	 * Gives the singleton instance of the game window.
	 * @return the singleton instance of the game window.
	 */
	public static Window getInstance() {
		if(instance == null) {
			instance = new Window();
		}
		return instance;
	}
	
	/**
	 * Sets the size of the window.
	 * @param size the size of the window.
	 * @param centerWindow specify if the window should be centered after the resize.
	 */
	public void setSize(Dimension size, boolean centerWindow) {
		this.setSize(size);
		if(centerWindow) {
			this.setLocationRelativeTo(null);
		}
	}
	
	/**
	 * Gives the input handler associated with this window.
	 * @return the input handler associated with this window.
	 */
	public Input getInput() {
		return this.input;
	}
	
	/**
	 * Places the specified game mode on the window and starts it.
	 * If the specified game mode is null, then the window will hide.
	 * @param gamemode the new game mode.
	 */
	public void nextGamemode(Gamemode gamemode) {
		this.contentPane.removeAll();
		this.contentPane.setLayout(new BorderLayout());
		this.contentPane.add(gamemode, BorderLayout.CENTER);
		this.setVisible(gamemode != null);
		input.setContainer(gamemode);
		gamemode.requestFocus();
	}
}
