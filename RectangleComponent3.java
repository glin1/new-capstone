import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.Timer;

/**
This component displays a rectangle that can be moved. 
 */
public class RectangleComponent3 extends JComponent
{
    
    private static final int BOX_X = 0;
    private static final int BOX_Y = 150;
    private static final int BOX_WIDTH = 40;
    private static final int BOX_HEIGHT = 90;
    private static final int BOX_X2 = 500;
    private static final int BOX_Y2 = 100;
    private static final int BOX_WIDTH2 = 40;
    private static final int BOX_HEIGHT2 = 90;
    private static final int BOX_X3 = 0;
    private static final int BOX_Y3 = 0;
    private static final int BOX_WIDTH3 = 800;
    private static final int BOX_HEIGHT3 = 20;
    private static final int BOX_X4 = 0;
    private static final int BOX_Y4 = 750;
    private static final int BOX_WIDTH4 = 800;
    private static final int BOX_HEIGHT4 = 90;
    private static final int BOX_X5 = 250;
    private static final int BOX_Y5 = 300;
    private static final int BOX_WIDTH5 = 40;
    private static final int BOX_HEIGHT5 = 90;
    private static final int BOX_X6 = 370;
    private static final int BOX_Y6 = 600;
    private static final int BOX_WIDTH6 = 40;
    private static final int BOX_HEIGHT6 = 90;
    private static final int BOX_X7 = 600;
    private static final int BOX_Y7 = 350;
    private static final int BOX_WIDTH7 = 50;
    private static final int BOX_HEIGHT7 = 220;
    private  int circle_x = 185;
    private  int circle_y = 350;
    private int speed_hori = 15;
    private int speed_verti = 20;
    
    private static  int circle_radius = 25;
    private int circleX = 500;
    private int circleY = 500;
    private int circleXSpeed=5 ;
    private int circleYSpeed=5;
    
    private Rectangle box;
    private Rectangle box2;
    private Rectangle box3;
    private Rectangle box4;
    private Rectangle box5;
    private Rectangle box6;
    private Rectangle box7;
    private Ellipse2D circle1;
    
    private boolean stillInGame = true;
    private Image background; 

    public RectangleComponent3()
    {  
        // The rectangle that the paintComponent method draws 
        box = new Rectangle(BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
        box2= new Rectangle(BOX_X2, BOX_Y2, BOX_WIDTH2, BOX_HEIGHT2);
        box3= new Rectangle (BOX_X3, BOX_Y3, BOX_WIDTH3, BOX_HEIGHT3);
        box4= new Rectangle (BOX_X4, BOX_Y4, BOX_WIDTH4, BOX_HEIGHT4);
        box5= new Rectangle (BOX_X5, BOX_Y5, BOX_WIDTH5, BOX_HEIGHT5);
        box6= new Rectangle (BOX_X6, BOX_Y6, BOX_WIDTH6, BOX_HEIGHT6);
        box7= new Rectangle (BOX_X7, BOX_Y7, BOX_WIDTH7, BOX_HEIGHT7);
        
        circle1= new Ellipse2D.Double(circleX, circleY, circle_radius , circle_radius);
        ImageIcon ii = new ImageIcon("C:\\Users\\Guo\\Pictures\\Background.");
        background = ii.getImage();  

        this.setFocusable(true);
        this.addKeyListener(new KeyStrokeListener());
    }
    
    
    public void paintComponent(Graphics g)
    { 
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background,0,0,this);
        g2.draw(box);
        g2.draw(box2);
        g2.draw(box3);
        g2.draw(box4);
        g2.draw(box5);
        g2.draw(box6);
        g2.draw(box7);
        g2.draw(circle1);
        ImageIcon ii = new ImageIcon("Background.jpg");
        background = ii.getImage();  
        g2.drawImage(background,0,0,this);
        
        int delay =1000;
        ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed (ActionEvent evt)
         {
                while (stillInGame == true)
                {
                    
                    g2.draw(circle1);
                    circleX += circleXSpeed;
                    circleY += circleYSpeed;
            
                    if (circleX - circle_radius < 0 )
                    {
                        System.out.println("GAME OVER!");
                        stillInGame = false;
                        
                    }
            
                    else if (circleX + circle_radius > 1000)
                    {
                        circleXSpeed = - circleXSpeed;
                        circleX = 1000 - circle_radius;
                    }
                
                    if (circleY - circle_radius < 0 )
                    {
                        circleYSpeed = - circleYSpeed;
                        circleY = circle_radius;
                    }
                
                    else if (circleY + circle_radius > 1000)
                    {
                        circleYSpeed = -circleYSpeed;
                        circleY = 1000 - circle_radius;
                    }
                    
                    else if (circle1.intersects(box) || circle1.intersects(box2) || circle1.intersects(box3) || circle1.intersects(box4) || 
                             circle1.intersects(box5) || circle1.intersects(box6) || circle1.intersects(box7))
                    {
                        circleXSpeed = -circleXSpeed;
                        circleYSpeed = -circleYSpeed;         
                    }
                    
                    
              }
            }
        };  
     new Timer (delay, taskPerformer).start();

  
}

    /**
    Moves the rectangle to the given location.
    @param x the x-position of the new location
    @param y the y-position of the new location
     */
    public void moveRectangleTo(int x, int y)
    {
        box.setLocation(x, y);
        repaint();      
    }

    /**
    Moves the rectangle by a given amount. 
    @param dx the amount to move in the x-direction 
    @param dy the amount to move in the y-direction 
     */
    public void moveRectangleBy(int dx, int dy)
    {
        box.translate(dx, dy);
        repaint();      
    }


    class KeyStrokeListener implements KeyListener
    {
        public void keyPressed(KeyEvent event) 
        {
            String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 
            if (key.equals("DOWN"))
            {
                if ( box.intersects(box4)  )
                {
                    moveRectangleBy(0,0);
                }
                else
                {
                    moveRectangleBy(0,8);
                }            
            }
            else if (key.equals("UP"))
            {
                if (box.intersects(box3))
                {
                    moveRectangleBy(0, 0);        
                }
                else
                {
                    moveRectangleBy(0,-8);
                }
            }
            
            else if (key.equals("ENTER"))
            {
                if (box.intersects (box2)  || box.intersects(box3)   || 
                box.intersects(box4) || box.intersects (box5)   || 
                box.intersects(box6) || box.intersects(box7) )
                {
                    moveRectangleBy(0,0);
                }
                else
                {
                    moveRectangleBy(0,90);
                }            
            }
            
            else if (key.equals("SPACE"))
            {
                if (box.intersects (box2)  || box.intersects(box3)   || 
                box.intersects(box4) || box.intersects (box5)   || 
                box.intersects(box6) || box.intersects(box7) )
                {
                    moveRectangleBy(0,0);
                }
                else
                {
                    moveRectangleBy(90,0);
                }
                
            }
            
            else if (key.equals("LEFT"))
            {
               if (box.intersects (box2)  || box.intersects(box3)   || 
                box.intersects(box4) || box.intersects (box5)   || 
                box.intersects(box6) || box.intersects(box7) )
                {
                    moveRectangleBy(0,0);
                }
                else
                {
                    moveRectangleBy(-8,0);
                }
            }
            
        }

        public void keyTyped(KeyEvent event) {}

        public void keyReleased(KeyEvent event) {}
    }
}

