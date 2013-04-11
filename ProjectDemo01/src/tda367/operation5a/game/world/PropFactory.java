package tda367.operation5a.game.world;

import java.awt.image.BufferedImage;

import tda367.operation5a.Resources;
import tda367.operation5a.geo.Rectangle2D;

/**
 * A factory for creating props to populate a world.
 * 
 * @author Calleberg
 *
 */
public class PropFactory {

	private static BufferedImage[] textureFurniture = Resources.splitImages("resources/furniture.png", 10, 10);
	
	/**
	 * Gives an instance of the prop specified by the data provided.
	 * @param prop the data to read from.
	 * @return an instance of the prop specified by the data provided.
	 */
	public static Prop parseProp(int prop) {
		return parseProp(prop/10, prop%10);
	}
	
	/**
	 * Gives an instance of the prop specified and with the rotation specified.
	 * @param prop the prop to create an instance of.
	 * @param rotation the rotation of the prop.
	 * @return an instance of the prop specified and with the rotation specified.
	 */
	public static Prop parseProp(int prop, int rotation) {
		if(prop == 0) {
			return null;
		}
		Prop temp;
		BufferedImage image = Resources.getRotatedImage(textureFurniture[prop], Math.PI/2 * rotation);
		
		switch(prop) {
		case 1: 	temp = new Prop(image, getRotatableCollisionBox(0, 0, 1f, 0.825f)); break;
		case 2: 	temp = new Prop(image, getRotatableCollisionBox(0, 0, 1f, 1f)); break;
		case 10:	temp = new Prop(image, getRotatableCollisionBox(0, 0, 1f, 1f)); break;
		case 11:	temp = new Prop(image, getRotatableCollisionBox(0.05f, 0.25f, 0.7f, 0.55f)); break;
		case 20:	temp = new Prop(image, getRotatableCollisionBox(0.1f, 0.15f, 0.8f, 0.8f)); break;
		case 21:	temp = new Prop(image, getRotatableCollisionBox(0.1f, 0.15f, 0.9f, 0.8f)); break;
		case 22:	temp = new Prop(image, getRotatableCollisionBox(0, 0.15f, 0.9f, 0.8f)); break;
		case 23:	temp = new Prop(image, getRotatableCollisionBox(0.1f, 0.3f, 0.6f, 0.6f)); break;
		default:	temp = new Prop(image, null); break;
		}
		
		//Rotates the collision box until both the image and collision box are rotated the same amount.
		for(int i = 0; i < rotation; i++) {
			temp.getCollisionBox().rotate(90);
		}
		
		return temp;
	}
	
	/**
	 * Gives a rotatable rectangle which will rotate around the center of the tile it's on.
	 * @param x the x-coordinate of the rectangle relative to the tile.
	 * @param y the y-coordinate of the rectangle relative to the tile.
	 * @param w the width of the rectangle.
	 * @param h the height of the rectangle.
	 * @return a rotatable rectangle which will rotate around the center of the tile it's on.
	 */
	public static Rectangle2D getRotatableCollisionBox(float x, float y, float w, float h) {
		return new Rectangle2D(x, y, w, h, 0.5f - x, 0.5f - y);
	}
}
