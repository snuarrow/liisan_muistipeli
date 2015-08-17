/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author hexvaara
 */
public class Picture
{
    private final int id;
    private final Image image;
    
    public Picture(int id, String filename)
    {
        this.id = id;
        image = new ImageIcon("src/main/resources/images/"+filename).getImage();
        
    }
    public Image image() { return image; }
    public int id() { return id; }
    public int y() { return image.getHeight(null); }
    public int x() { return image.getWidth(null); }
    public double ratio() { return (double)y()/(double)x(); }
}
