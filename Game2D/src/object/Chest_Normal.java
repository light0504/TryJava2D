package object;

import javax.imageio.ImageIO;

public class Chest_Normal extends Chest{
    
    public Chest_Normal(){
        name = "Normal Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Resource/objects/normal_chest.png"));
        }catch(Exception e){  
        }
    }

}
