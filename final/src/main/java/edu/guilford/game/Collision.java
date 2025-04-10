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
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityLeftColumn][entityBottomRow];
                tileNumber2 = gp.tileM.mapTileNum[entityRightColumn][entityBottomRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
            entityLeftColumn = (enetityLeftWorldX - entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNum[entityLeftColumn][entityBottomRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
            entityRightColumn = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileM.mapTileNum[entityRightColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNum[entityRightColumn][entityBottomRow];
                if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                break;
            }
        }
    }
}
