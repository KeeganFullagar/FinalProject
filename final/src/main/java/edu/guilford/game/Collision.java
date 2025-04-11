package edu.guilford.game;

import edu.guilford.entity.Entity;

public class Collision {

    GamePanel gp;

    public Collision(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {

        int enetityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = enetityLeftWorldX / gp.tileSize;
        int entityRightColumn = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNum[entityRightColumn][entityTopRow];
                if (gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityLeftColumn][entityBottomRow];
                tileNumber2 = gp.tileM.mapTileNum[entityRightColumn][entityBottomRow];
                if (gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftColumn = (enetityLeftWorldX - entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNum[entityLeftColumn][entityBottomRow];
                if (gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityRightColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNum[entityRightColumn][entityBottomRow];
                if (gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                    break;
                }
        }
    }

    public int checkObject(Entity entity, boolean player) { // checks if player hits an object

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
