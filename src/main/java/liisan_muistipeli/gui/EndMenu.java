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
    
    public EndMenu(Global global)
    {
        this.global = global;
        buttons = new ArrayList<>();
        createToMainMenuButton();
    }
    public void drawScore(Graphics2D g, int score)
    {
        int fromleft = 100;
        int fromtop = 100;
        int fontsize = 80;
        
        Font font = new Font("Sherif", Font.PLAIN, fontsize);
        g.setFont(font);
        g.setColor(Color.yellow);
        if (score < 100) g.drawString("score: "+score+"%", fromleft, fromtop);

    }
    public void createToMainMenuButton()
    {
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.5);
        
        int width = (int) (global.getVerticalsize()*0.4);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.05);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "main menu", 0));
    }
    public void createPlayAgainButton()
    {
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.7);
        
        int width = (int) (global.getVerticalsize()*0.4);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.05);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "play again", 1));
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
