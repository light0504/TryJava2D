package object;

import javax.imageio.ImageIO;

public class Boots_Leather extends Boots{
    
    public Boots_Leather(){
        name = "Leather Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Resource/objects/leather_boots.png"));
        }catch(Exception e){  
        }
    }

}
