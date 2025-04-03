package src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/src/res/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
                y = y - speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y = y + speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x = x - speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x = x + speed;
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

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction) { // this is was painful to figure out but now I know to always add the break
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

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
