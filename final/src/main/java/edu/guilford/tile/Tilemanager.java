package edu.guilford.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.InputStream;

import javax.imageio.ImageIO;

import edu.guilford.game.GamePanel;

public class Tilemanager {

    public GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public Tilemanager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWolrdColumn][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/WorldMap.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Tilemanager.class.getResourceAsStream("/tile/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/dirt.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/bush.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath); // filePath makes it easier to change maps
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // reads the text file

            int column = 0;
            int row = 0;

            while (column < gp.maxWolrdColumn && row < gp.maxWorldRow) {

                String line = br.readLine(); // reads single line from MapData

                while (column < gp.maxWolrdColumn) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[column]); // Changing from string to int

                    mapTileNum[column][row] = num;
                    column++;
                }
                if (column == gp.maxWorldRow) {
                    column = 0;
                    row++;
                }
            }
            br.close(); // line will be scanned and then the next one will be scanned

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < gp.maxWolrdColumn && worldRow < gp.maxWorldRow) {

            int tileNumber = mapTileNum[worldColumn][worldRow]; // this will ensure that we are getting the proper
                                                                // tile types

            int worldX = worldColumn * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // player.screen provides offset so player can
                                                                         // go in corners
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // returns player screen position

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldColumn++;

            if (worldColumn == gp.maxWolrdColumn) {
                worldColumn = 0;
                worldRow++;
            }
        }

    }
}
