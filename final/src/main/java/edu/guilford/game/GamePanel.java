package edu.guilford.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import edu.guilford.entity.Player;
import edu.guilford.object.SuperObject;
import edu.guilford.tile.Tilemanager;

public class GamePanel extends JPanel implements Runnable {

    // Settings
    final int originalTileSize = 16; // creates a 16x16 tile which is standard 2d game size
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //WORLD Settings
    public final int maxWolrdColumn = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWolrdColumn;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;
    Tilemanager tileM = new Tilemanager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Collision collisionCheck = new Collision(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame () {

        aSetter.setObject();

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Using a Delta time loop
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer = timer + (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //TILE
        tileM.draw(g2);
        
        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //Player
        player.draw(g2);

        g2.dispose();
    }

}