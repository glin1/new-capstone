import javax.swing.JFrame;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;

/**                         
This frame contains a moving rectangle.
 */
public class RectangleFrame3 extends JFrame
{
    private Image background;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 815;

    private RectangleComponent3 scene;
    
    
    private void initBoard() 
    {
        
        loadImage();
        
        int w = 1000;
        int h =  1000;
        setPreferredSize(new Dimension(w, h));        
    }
    
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("Background.jpg");
        background = ii.getImage();  
        
    }

    
    public void paintComponent(Graphics g) {

        g.drawImage(background, 0, 0, null);
    }

    class FrameWindowListener extends WindowAdapter
    {
        public void windowOpened(WindowEvent event)
        {
            scene.requestFocusInWindow();
        }
    }

    public RectangleFrame3()
    {
        scene = new RectangleComponent3();
        add(scene);
        initBoard();

        this.addWindowListener(new FrameWindowListener());

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
} 
