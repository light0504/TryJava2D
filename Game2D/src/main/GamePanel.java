package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTING
    final int originalTileSize = 32; //size 32*32
    final int scale = 2; 
    
    public final int tileSize = originalTileSize * scale;
    
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol*tileSize; //1344px
    public final int screenHeight = maxScreenRow*tileSize; //768px
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    //khai bao cac doi tuong trong game
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    
    // check va cham
    public CollisionCheck colCheck = new CollisionCheck(this);
    
    // set vi tri vat the
    public ObjectSetter aSetter = new ObjectSetter(this);
    
    TileManager tileM = new TileManager(this);
    
    //set vi tri ban dau cua player

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void setup(){
        aSetter.setObject();
    }

//    @Override
//    public void run() {
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null){
//            long currTime = System.nanoTime();
//            // Update Information in game
//            update();
//            // Draw the screen
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                if (remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
    //chay game voi FPS
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >=1){
                update();
                repaint();
                delta --;
                drawCount ++;
            }
            
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update(){
        player.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        // ve nen 
        tileM.draw(g2);
        // ve object
        for(int i=0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //ve player
        player.draw(g2);
        g2.dispose();
    }
}
