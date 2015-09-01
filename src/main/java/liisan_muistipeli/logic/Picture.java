package liisan_muistipeli.logic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Kortti luokalle annettava Kuva, helpottaa javan Image olion käyttöä.
 */
public class Picture
{
    private final int id;
    private final Image image;
    
    public Picture(int id, String filename)
    {
        this.id = id;
        //image = new ImageIcon("src/main/resources/images/"+filename).getImage();
        
        
        //BufferedImage img = ImageIO.read(getClass().getResource("src/main/resources/images/"+filename));
        //InputStream stream = getClass().getResourceAsStream("src/main/resources/images/"+filename);
        image = new ImageIcon(getClass().getClassLoader().getResource("images/"+filename)).getImage();
        //System.out.println("image loaded with height: "+image.getHeight(null));
        
        
    }
    public Image image() { return image; }
    public int id() { return id; }
    public int y() { return image.getHeight(null); }
    public int x() { return image.getWidth(null); }
    public double ratio() { return (double)y()/(double)x(); }
}
