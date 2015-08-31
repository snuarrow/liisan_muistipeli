/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import liisan_muistipeli.logic.Global;

/**
 *
 * @author hexvaara
 */
public class EndMenu
{
    private ArrayList<Button> buttons;
    private Global global;
    
    //alignment variables
    private int column0_from_left;
    private int column1_from_left;
    private int headline_fontsize;
    private int fontsize;
    private Font headline_font;
    private Font font;
    
    public EndMenu(Global global)
    {
        column0_from_left = (int) (global.getVerticalsize()*0.2);
        column1_from_left = (int) (global.getVerticalsize()*0.7);
        headline_fontsize = 40;
        fontsize = 24;
        font = new Font("Monospaced", Font.PLAIN, fontsize);
        headline_font = new Font("Monospaced", Font.PLAIN, headline_fontsize);
        
        this.global = global;
        buttons = new ArrayList<>();
        createToMainMenuButton();
    }
    public void drawScore(Graphics2D g, int score)
    {
        int fromleft = 100;
        int fromtop = 100;
        //int fontsize = 80;
        
        //Font font = new Font("Sherif", Font.PLAIN, fontsize);
        g.setFont(headline_font);
        g.setColor(Color.yellow);
        g.drawString("score: "+score+"%", fromleft, fromtop);

    }
    public void createToMainMenuButton()
    {
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.5);
        
        int width = (int) (global.getVerticalsize()*0.4);
        int height = (int) (global.getVerticalsize()*0.1);
        
        //int fontsize = (int) (global.getHorizontalsize()*0.05);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "main menu", 0, font));
    }
    public void createPlayAgainButton()
    {
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.7);
        
        int width = (int) (global.getVerticalsize()*0.4);
        int height = (int) (global.getVerticalsize()*0.1);
        
        //int fontsize = (int) (global.getHorizontalsize()*0.05);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "play again", 20, font));
    }
    
        public ArrayList<Button> buttons()
    {
        return buttons;
    }
    public void mousehover(MouseEvent me)
    {
        for (Button button : buttons)
        {
            button.mousehover(me);
        }
    }
    public Button mouseclicked(MouseEvent me)
    {
        for (Button button : buttons)
        {
           if (button.mousehover(me)) return button;
        }
        return null;
    }
}
