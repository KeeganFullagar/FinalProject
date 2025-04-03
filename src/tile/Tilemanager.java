package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class Tilemanager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNumber[][];

    public Tilemanager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNumber = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/maps/MapData.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath); //filePath makes it easier to change maps
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // reads the text file

            int column = 0;
            int row = 0;

            while (column < gp.maxScreenColumn && row < gp.maxScreenRow) {

                String line = br.readLine(); // reads single line from MapData

                while (column < gp.maxScreenColumn) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[column]); // Changing from string to int

                    mapTileNumber[column][row] = num;
                    column++;
                }
                if (column == gp.maxScreenColumn) {
                    column = 0;
                    row++;
                }
            }
            br.close(); // line will be scanned and then the next one will be scanned

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (column < gp.maxScreenColumn && row < gp.maxScreenRow) {

            int tileNumber = mapTileNumber[column][row]; //this will ensure that we are getting the proper tile types

            g2.drawImage(tile[tileNumber].image, x, y, gp.tileSize, gp.tileSize, null);
            column++;
            x = x + gp.tileSize;

            if (column == gp.maxScreenColumn) {
                column = 0;
                x = 0;
                row++;
                y = y + gp.tileSize;
            }
        }

    }
}
