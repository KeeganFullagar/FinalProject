package edu.guilford.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject{

    public ObjectDoor() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }
}
