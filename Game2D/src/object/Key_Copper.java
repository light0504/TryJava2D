package object;

import javax.imageio.ImageIO;

public class Key_Copper extends Key{
    
    public Key_Copper(){
        name = "Copper Key";
        try{
            image = ImageIO.read(getClass().getResource("/Resource/objects/bronze_key.png"));
        }catch(Exception e){       
        }
    }

}
