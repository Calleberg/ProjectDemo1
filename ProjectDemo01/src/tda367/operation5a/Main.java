package tda367.operation5a;

import java.awt.Dimension;

import tda367.operation5a.gamemode.Testmode;

/**
 * 
 * 
 * @author
 *
 */
public class Main {

	public static void main(String[] args) {
		Window wh = Window.getInstance();
		wh.setSize(new Dimension(1000, 800), true);
		wh.setTitle("Operation 5A");
		wh.nextGamemode(new Testmode());
	}
}
