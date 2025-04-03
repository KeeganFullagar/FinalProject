package src.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    
    //Settings
    final int originalTileSize = 16; //creates a 16x16 tile which is standard 2d game size 
    final int scale = 3; 
    final int tileSize = originalTileSize * scale; 
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    Thread gameThread; 

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        
    }
}