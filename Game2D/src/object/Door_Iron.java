package object;

import javax.imageio.ImageIO;

public class Door_Iron extends Door{
    public Door_Iron(){
        super();
        name = "Iron Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Resource/objects/iron_door.png"));
        }catch(Exception e){  
        }
    }

}
