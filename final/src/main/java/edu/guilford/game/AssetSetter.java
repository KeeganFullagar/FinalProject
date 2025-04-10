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
        gp.obj[1].worldX = 43 * gp.tileSize;
        gp.obj[1].worldY = 45 * gp.tileSize;

        gp.obj[1] = new ObjectKey();
        gp.obj[1].worldX = 36 * gp.tileSize;
        gp.obj[1].worldY = 5 * gp.tileSize;

        gp.obj[2] = new ObjectDoor();
        gp.obj[2].worldX = 5 * gp.tileSize;    
        gp.obj[2].worldY = 5 * gp.tileSize;

        gp.obj[3] = new ObjectDoor();
        gp.obj[3].worldX = 16 * gp.tileSize;    
        gp.obj[3].worldY = 5 * gp.tileSize;

        gp.obj[4] = new ObjectChest();
        gp.obj[4].worldX = 14 * gp.tileSize;    
        gp.obj[4].worldY = 3 * gp.tileSize;

        gp.obj[5] = new ObjectChest();
        gp.obj[5].worldX = 18 * gp.tileSize;    
        gp.obj[5].worldY = 3 * gp.tileSize;
    }
}
