/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import liisan_muistipeli.logic.Global;

/**
 *
 * @author hexvaara
 */
public class StartMenu
{
    private ArrayList<Button> buttons;
    private Global global;
    
    public StartMenu(Global global)
    {
        this.global = global;
        buttons = new ArrayList<>();
        createPlayButton();
        createSettingsButton();
    }
    
    public void createPlayButton()
    {
        // skalaarit pitäis jotenki järkeistää, eli ei kovakoodata.
        
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.6);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.07);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "play", 1));
    }
    public void createSettingsButton()
    {
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.7);
        
        int width = (int) (global.getVerticalsize()*0.4);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.05);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "settings", 0));
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
