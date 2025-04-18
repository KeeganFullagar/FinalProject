package edu.guilford.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.guilford.game.GamePanel;
import edu.guilford.game.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); //centers character
        screenY = gp.screenHeight/2 - (gp.tileSize/2); 

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //position and speed
        worldX = gp.tileSize * 3; 
        worldY = gp.tileSize * 46;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            
            //check tile collision
            collisionOn = false;
            gp.collisionCheck.checkTile(this);

            //check object collision
            int objIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objIndex);

            //if collision is false then player can move
            if(collisionOn == false){
                
            switch(direction) {
            case "up": worldY = worldY - speed; break;
            case "down": worldY = worldY + speed; break;
            case "left": worldX = worldX - speed; break;
            case "right": worldX = worldX + speed; break;
            }
        }

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
            else if(spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {

        if(i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null; //removes the object
                    System.out.println("Keys: " + hasKey);
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--; 
                    }                     
                    System.out.println("Keys: " + hasKey);
                    break;
            }
           
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction) { // look at Update 2 to find my initial struggles
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
