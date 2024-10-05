package main;

import entity.Entity;

public class CollisionCheck {   
    
    GamePanel gp;
    
    public CollisionCheck(GamePanel gp){
        this.gp = gp;
    }
    
    public void checkTile(Entity entity){
        // kiem tra xem doi tuong va nen gan nhat co va cham hay khong
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entityLeftX + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entityTopY + entity.solidArea.height;
        
        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].conllision == true || gp.tileM.tile[tileNum2].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].conllision == true || gp.tileM.tile[tileNum2].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].conllision == true || gp.tileM.tile[tileNum2].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].conllision == true || gp.tileM.tile[tileNum2].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "upleft":
                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "upright":
                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum2].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "downleft":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].conllision == true){
                    entity.collisionOn = true;
                }
                break;
            case "downright":
                entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum2].conllision == true){
                    entity.collisionOn = true;
                }
                break;
                
        }
        
    }
    
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        // kiem tra cho tat ca doi tuong va vat the co va cham nhau de tuong tac hay khong
        for(int i=0; i<gp.obj.length; i++){
            if(gp.obj[i] != null){
                //get entity solid area
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get obj solid area
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                
                switch(entity.direction){
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "upleft":
                    entity.solidArea.y -= entity.speed*3/4;
                    entity.solidArea.x -= entity.speed*3/4;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "upright":
                    entity.solidArea.y -= entity.speed*3/4;
                    entity.solidArea.x += entity.speed*3/4;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "downleft":
                    entity.solidArea.y += entity.speed*3/4;
                    entity.solidArea.x -= entity.speed*3/4;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "downright":
                    entity.solidArea.y += entity.speed*3/4;
                    entity.solidArea.x += entity.speed*3/4;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                }
                //dat lai gia tri solid area ban dau cho nhan vat
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                
                //dat lai gia tri solid area ban dau cho vat the
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}
