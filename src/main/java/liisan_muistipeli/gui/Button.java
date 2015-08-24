/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author hexvaara
 */
public class Button
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int fontsize;
    private String text;
    private Color backgroundcolor, fontcolor, bordercolor;
    private Font font;
    
    
    public Button(int x, int y, int width, int height, int fontsize, String text)
    {
        this.text = text;
        font = new Font("Sherif", Font.PLAIN, fontsize);
        this.fontsize = fontsize;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }
    public int x()
    {
        return x;
    }
    public int y()
    {
        return y;
    }
    public int height()
    {
        return height;
    }
    public int width()
    {
        return width;
    }
    public void setBackGroundColor(Color color)
    {
        backgroundcolor = color;
    }
    public void setFontColor(Color color)
    {
        fontcolor = color;
    }
    public void setBorderColor(Color color)
    {
        bordercolor = color;
    }
    public void draw(Graphics2D g)
    {
        //drawBackGround(g);
        //drawBorder(g);
        drawFont(g);
    }
    public void drawFont(Graphics2D g)
    {
        g.setFont(font);
        g.setColor(fontcolor);
        g.drawString(text, x, y);
    }
    public void drawBorder(Graphics2D g)
    {
        g.setColor(bordercolor);
        g.drawRect(x, y, x+width, y+height);
    }
    public void drawBackGround(Graphics2D g)
    {
        g.setColor(backgroundcolor);
        g.fillRect(x, y, x+width, y+height);
    }
    
    
    
    
    
    
}
