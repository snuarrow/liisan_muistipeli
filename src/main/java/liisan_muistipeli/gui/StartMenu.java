package liisan_muistipeli.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import liisan_muistipeli.logic.Global;

/**
 * Luokka päävalikon näkymälle ja asetuksille.
 */
public class StartMenu
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
    
    public StartMenu(Global global)
    {
        column0_from_left = (int) (global.getVerticalsize()*0.2);
        column1_from_left = (int) (global.getVerticalsize()*0.7);
        headline_fontsize = 40;
        fontsize = 24;
        font = new Font("Monospaced", Font.PLAIN, fontsize);
        headline_font = new Font("Monospaced", Font.PLAIN, headline_fontsize);
        
        this.global = global;
        buttons = new ArrayList<>();
        createPlayButton();
        createSettingsButton();
    }
    public void drawHeadline(Graphics2D g)
    {
        g.setFont(headline_font);
        g.setColor(Color.DARK_GRAY);
        g.drawString("muistipeli", 100, 100);
    }
    
    public void createPlayButton()
    {
        // skalaarit pitäis jotenki järkeistää, eli ei kovakoodata.
        
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.7);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.03);
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "play", 1, font));
    }
    public void createSettingsButton()
    {
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.65);
        
        int width = (int) (global.getVerticalsize()*0.4);
        int height = (int) (global.getVerticalsize()*0.03);
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "settings", 2, font));
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
