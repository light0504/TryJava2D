package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int wooden_key = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
        screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

        solidArea = new Rectangle(16, 32, 32, 20);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefautValues();
        getPlayerImage();
    }

    public void setDefautValues() {
        //dat nguoi choi o giua man hinh
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = gp.tileSize / 8;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            //them anh cua player
            up1 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Resource/player/boy_right_2.png"));

        } catch (IOException e) {

        }
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.downleftPressed == true || keyH.downrightPressed == true || keyH.upleftPressed == true || keyH.uprightPressed == true) {
            if (keyH.uprightPressed) {
                direction = "upright";
            } else if (keyH.upleftPressed) {
                direction = "upleft";
            } else if (keyH.downrightPressed) {
                direction = "downright";
            } else if (keyH.downleftPressed) {
                direction = "downleft";
            } else if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }


            collisionOn = false;
            System.out.println(direction);
            //check va cham voi nen
            gp.colCheck.checkTile(this);
            // check va cham voi vat the
            int obj_index = gp.colCheck.checkObject(this, true);
            System.out.println(collisionOn);
            ObjInteractive(obj_index);
            if (!collisionOn) { // If there is no collision, move
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "upright":
                        worldY -= speed * 3 / 4;
                        worldX += speed * 3 / 4;
                        break;
                    case "upleft":
                        worldY -= speed * 3 / 4;
                        worldX -= speed * 3 / 4;
                        break;
                    case "downright":
                        worldY += speed * 3 / 4;
                        worldX += speed * 3 / 4;
                        break;
                    case "downleft":
                        worldY += speed * 3 / 4;
                        worldX -= speed * 3 / 4;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void ObjInteractive(int i) {
        if (i != 999) {
            String objName = gp.obj[i].name;
            switch (objName) {
                case "Copper Key":
                    wooden_key++;
                    gp.obj[i] = null;
                    break;
                case "Wooden Door":
                    if (wooden_key > 0) {
                        wooden_key--;
                        gp.obj[i] = null;
                    }
                    break;
                case "Normal Chest":
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "upleft":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "upright":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "downleft":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "downright":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
