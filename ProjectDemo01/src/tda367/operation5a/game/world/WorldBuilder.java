package tda367.operation5a.game.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 
 * @author Calleberg
 *
 */
public class WorldBuilder {

	private int tilesAdded;		//counter.
	
	public WorldBuilder() {
		this.tilesAdded = 0;
	}
	
	public Tile[][] createWorld() {
		System.out.println("Building world ...");
		int width = 30;
		int height = 30;
		Tile2D[][] tiles = new Tile2D[width][height];
		
		for(int y = 0; y < tiles[0].length; y++) {
			for(int x = 0; x < tiles.length; x++) {
				tiles[x][y] = new Tile2D(0, x, y);
			}
		}
		tiles[4][2].setFloor(2);
		tiles[4][3].setFloor(2);
		tiles[1][1].setFloor(0);
		tiles[1][1].setNorthWall(true);
		tiles[1][1].setEastWall(true);
		tiles[1][2].setEastWall(true);
		tiles[4][2].setEastWall(true);
		
		this.addTileSet(0, 0, 2, 2, tiles, "resources/lots/2x2_test001.txt");
		this.addTileSet(10, 0, 10, 10, tiles, "resources/lots/10x10_roadNorth.txt");
		this.addTileSet(10, 10, 10, 10, tiles, "resources/lots/10x10_4crossing.txt");
		this.addTileSet(10, 20, 10, 10, tiles, "resources/lots/10x10_roadNorth.txt");
		
		tiles[5][5].setProp(PropFactory.parseProp(1, 1));
		tiles[6][5].setEastWall(true);
		tiles[5][6].setProp(PropFactory.parseProp(1, 1));
		tiles[6][6].setEastWall(true);
		tiles[5][7].setProp(PropFactory.parseProp(2, 1));
		tiles[6][7].setEastWall(true);
		tiles[4][7].setProp(PropFactory.parseProp(1, 2));
		tiles[3][7].setProp(PropFactory.parseProp(1, 2));
		
		tiles[3][5].setProp(PropFactory.parseProp(10, 0));
		tiles[4][5].setProp(PropFactory.parseProp(11, 0));
		tiles[2][5].setProp(PropFactory.parseProp(11, 2));
		tiles[3][4].setProp(PropFactory.parseProp(11, 3));
		
		tiles[7][2].setProp(PropFactory.parseProp(20, 0));
		tiles[8][2].setProp(PropFactory.parseProp(23, 0));
		tiles[8][3].setProp(PropFactory.parseProp(21, 1));
		tiles[8][4].setProp(PropFactory.parseProp(22, 1));
		
		tiles[7][2].setNorthWall(true);
		tiles[8][2].setNorthWall(true);
		tiles[9][2].setEastWall(true);
		tiles[9][3].setEastWall(true);
		
		for(int y = 5; y < 8; y++) {
			for(int x = 3; x < 6; x++) {
				tiles[x][y].setFloor(4);
			}
		}
		
		System.out.println("world built!");
		return tiles;
	}
	
	/**
	 * Adds a specified set of tiles to the specified world at the specified position.
	 * @param startX the tile to start at.
	 * @param startY the tile to start at.
	 * @param endX the tile to stop at.
	 * @param endY the tile to stop at.
	 * @param world the world to add the tiles to.
	 * @param url the path to the file containing the data to load from.
	 * @return <code>true</code> if the tiles could be added.
	 */
	public boolean addTileSet(int startX, int startY, int width, int height, Tile[][] tiles, String url) {
		String data[][] = new String[width][height];
		
		try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url)), "ISO-8859-1"));

            System.out.print("building tileset #" + (tilesAdded+1) + " (" + width + "x" + height + ") ... ");
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
            	if(!line.substring(0, 2).equals("//")) {
            		data[i] = line.split(";");
            		i++;
            	}
            }
            
            for(int x = 0; x < data.length; x++) {
            	for(int y = 0; y < data[0].length; y++) {
            		this.addTile(y + startX, x + startY, tiles, data[x][y]); //The position is mirrored in data[][]
            	}
            }

            tilesAdded++;
            reader.close();
            System.out.println("Done!");
            return true;
        } catch (IOException exc) {
            return false;
        }
    }
	
	/*
	 * Adds one tile to the world by reading the data provided.
	 */
	private boolean addTile(int x, int y, Tile[][] tiles, String tileData) {
		String[] data = tileData.split(",");
		Tile2D tile = new Tile2D(Integer.parseInt(data[0]), x, y);

		if(data.length > 1) {
			int walls = Integer.parseInt(data[1]);
			if(walls > 0 && walls % 2 == 1) {
				tile.setEastWall(true);
				walls--;
			}
			if(walls > 0 && walls % 2 == 0) {
				tile.setNorthWall(true);
			}
		}
		if(data.length > 2) {
			tile.setProp(PropFactory.parseProp(Integer.parseInt(data[2])));
		}
		
		tiles[x][y] = tile;
		return true;
	}
}
