package object;

import javax.imageio.ImageIO;

public class Door_Wooden extends Door{
    public Door_Wooden() {
        super();
        name = "Wooden Door";
        try{
            image = ImageIO.read(getClass().getResource("/Resource/objects/wooden_door.png"));
        }catch(Exception e){       
        }
    }
}
