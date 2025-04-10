package edu.guilford.game;

import edu.guilford.object.ObjectChest;
import edu.guilford.object.ObjectDoor;
import edu.guilford.object.ObjectKey;

public class AssetSetter {
    GamePanel gp; 
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
    
        gp.obj[0] = new ObjectKey();
        gp.obj[0].worldX = 5 * gp.tileSize;    
        gp.obj[0].worldY = 3 * gp.tileSize;

        gp.obj[1] = new ObjectKey();
        gp.obj[1].worldX = 38 * gp.tileSize;
        gp.obj[1].worldY = 47 * gp.tileSize;

        gp.obj[2] = new ObjectKey();
        gp.obj[2].worldX = 36 * gp.tileSize;
        gp.obj[2].worldY = 5 * gp.tileSize;

        gp.obj[3] = new ObjectDoor();
        gp.obj[3].worldX = 5 * gp.tileSize;    
        gp.obj[3].worldY = 5 * gp.tileSize;

        gp.obj[4] = new ObjectDoor();
        gp.obj[4].worldX = 16 * gp.tileSize;    
        gp.obj[4].worldY = 5 * gp.tileSize;

        gp.obj[5] = new ObjectDoor();
        gp.obj[5].worldX = 42 * gp.tileSize;    
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new ObjectDoor();
        gp.obj[6].worldX = 27 * gp.tileSize;    
        gp.obj[6].worldY = 46 * gp.tileSize;

        gp.obj[7] = new ObjectChest();
        gp.obj[7].worldX = 14 * gp.tileSize;    
        gp.obj[7].worldY = 3 * gp.tileSize;

        gp.obj[8] = new ObjectChest();
        gp.obj[8].worldX = 18 * gp.tileSize;    
        gp.obj[8].worldY = 3 * gp.tileSize;
    }
}
