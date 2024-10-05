package main;

import object.Key_Copper;
import object.Chest;
import object.Chest_Normal;
import object.SuperObject;
import object.Door_Wooden;

public class ObjectSetter {
    GamePanel gp;
    
    public ObjectSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        addObject(0, new Key_Copper(), 23, 35);
        
        addObject(1, new Key_Copper(), 11, 32);
        
        addObject(2, new Door_Wooden(), 10, 11);
        
        addObject(3, new Chest_Normal(), 10, 7);
    }
    
    public void addObject(int num, SuperObject obj,int worldX, int worldY){
        gp.obj[num] = obj;
        gp.obj[num].worldX = worldX*gp.tileSize;
        gp.obj[num].worldY = worldY*gp.tileSize;
    }
}
