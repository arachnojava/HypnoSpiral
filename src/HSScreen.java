import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import mhframework.MHDisplayModeChooser;
import mhframework.MHGame;
import mhframework.MHScreen;
import mhframework.io.MHFileFilter;
import mhframework.media.MHResourceManager;


public class HSScreen extends MHScreen
{
    private ArrayList<Image> spirals;
    private int spiralIndex = 0;
    private Image image;
    private double rotation = 0.0;
    private double rotationSpeed = -1.0;
    
    public HSScreen()
    {
    }
    
   
    @Override
    public void render(Graphics2D g)
    {
        int x = MHDisplayModeChooser.getCenterX() - image.getWidth(null)/2;
        int y = MHDisplayModeChooser.getCenterY() - image.getHeight(null)/2;
        rotation += rotationSpeed;
        
        final AffineTransform originalTransform = g.getTransform();
        g.rotate(rotation * (Math.PI / 180.0), MHDisplayModeChooser.getCenterX(), MHDisplayModeChooser.getCenterY());  //.rotate(rotation * Math.PI / 180.0, w / 2.0, h / 2.0);

        g.drawImage(image, x, y, null);
        
        g.setTransform(originalTransform);
        
        super.render(g);
    }


    @Override
    public void keyPressed(KeyEvent e)
    {
        super.keyPressed(e);
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            MHGame.setProgramOver(true);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rotationSpeed += 0.5;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            rotationSpeed -= 0.5;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            spiralIndex--;
            if (spiralIndex < 0) 
                spiralIndex = spirals.size() - 1;
            generateImage();
        }
        else
        {
            spiralIndex = (spiralIndex + 1) % spirals.size();
            generateImage();
        }
    }





    private void generateImage()
    {
        Image img = new BufferedImage(1200, 1200, BufferedImage.TYPE_INT_RGB); 
        Graphics gr = img.getGraphics();
        gr.drawImage(spirals.get(spiralIndex), 0, 0, 1200, 1200, null);
        image = img;
    }


    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void load()
    {
        if (spirals == null)
        {
            spirals = new ArrayList<Image>();
            
            for (File file : MHFileFilter.listFiles("images", "jpg"))
                spirals.add(MHResourceManager.loadImage(file.getAbsolutePath()));

            for (File file : MHFileFilter.listFiles("images", "jpeg"))
                spirals.add(MHResourceManager.loadImage(file.getAbsolutePath()));

            for (File file : MHFileFilter.listFiles("images", "png"))
                spirals.add(MHResourceManager.loadImage(file.getAbsolutePath()));

            for (File file : MHFileFilter.listFiles("images", "gif"))
                spirals.add(MHResourceManager.loadImage(file.getAbsolutePath()));
        }
        generateImage();
    }


    @Override
    public void unload()
    {
        // TODO Auto-generated method stub

    }

}
