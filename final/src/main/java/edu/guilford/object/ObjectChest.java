package edu.guilford.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject {

    public ObjectChest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
