package liisan_muistipeli.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import liisan_muistipeli.logic.Card;
import liisan_muistipeli.logic.Engine;
import liisan_muistipeli.logic.Global;
import liisan_muistipeli.logic.Picture;

/**
 * Graafinen käyttöliittymä engine luokan käyttämiseen. sisältää toiminnallisuutta mm. hiiren seuraamista ja ajastimien ylläpitoa.
 */

// rumaa koodia!! siisti demon jälkeen koko luokka.

public class MainFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    private Timer t;
    private Engine engine;
    private Global global;
    private HashMap cards;
    private Picture smiley;
    private Picture background;
    private int image_display_time;
    private Card clicked = null;
    private int image_zoom_time;
    private StartMenu startmenu;
    private SettingsMenu settingsmenu;
    
    private int gamestate; // 0 = startmenu, 1 = in game, 2 = in settings menu
    
    public MainFrame(Global global)
    {
        gamestate = 0;
        
        startmenu = new StartMenu(global);
        settingsmenu = new SettingsMenu(global);
        
        
        image_zoom_time = 0;
        image_display_time = -1;
        smiley = new Picture(0, "acid3tb.png");
        background = new Picture(0, "background.png");
        this.global = global;
        engine = new Engine(global);
        cards = engine.getCards();
        
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        t = new Timer(global.getTimer_interval(), this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        repaint();
    }
    
    public void start_menu(Graphics2D g)
    {
        g.drawImage(new Picture(0, "pluto.gif").image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize(), this);
        
        startmenu.drawHeadline(g);
        
        for (Button button : startmenu.buttons())
        {
            button.draw(g);
        }
    }
    public void settings_menu(Graphics2D g)
    {
        g.drawImage(new Picture(0, "pluto.gif").image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize(), this);
        
        settingsmenu.drawHeadline(g);
        settingsmenu.showCurrentResolution(g);
        
        for (Button button : settingsmenu.buttons())
        {
            button.draw(g);
        }
        
        
    }
    
    public void in_game(Graphics2D g)
    {
        g.drawImage(background.image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize() ,this);
        
        for (int id : engine.getPC().getIds())
        {
            Card instance = engine.getCards().get(id);
            g.drawImage(smiley.image(), instance.x() , instance.y(), global.getCardsize(), global.getCardsize(), this);
        }
    }
    
    private int show_picture_state = 0; // 0 = in zoom period, 1 = fully zoomed
    private int upper_left_x;
    private int upper_left_y;
    private int width;
    private int height;
    
    
    public void show_picture(Graphics2D g)  // tämä funktio kaipaa siistimistä, rumat luokkamuuttujat, keksi jotain.
    {
        if (show_picture_state == 0) // in zoom period
        {
            image_zoom_time += 20;
            
            upper_left_x = clicked.x()-(image_zoom_time/2);
            upper_left_y = (int) (clicked.y()-((image_zoom_time/2)*clicked.picture().ratio()));
            width = global.getCardsize()+image_zoom_time;
            height = (int) ((global.getCardsize()+ image_zoom_time)*clicked.picture().ratio());
            
            if (upper_left_x + width > global.getHorizontalsize())
            {
                int correction = upper_left_x + width - global.getHorizontalsize();
                upper_left_x -= correction;
            }
            if (upper_left_y + height > global.getVerticalsize())
            {
                int correction = upper_left_y + height - global.getVerticalsize();
                upper_left_y -= correction;
            }
            if (width > global.getImage_maxsize() || height > global.getImage_maxsize())
            {
                show_picture_state = 1;
            }
            if (upper_left_x < 0) upper_left_x = 0;
            if (upper_left_y < 0) upper_left_y = 0;
        }
        else if (show_picture_state == 1) // fully opened
        {
            image_display_time++;
            if (image_display_time > global.getImage_displaytime_ms()) image_display_time = -1;
        }
        
        
        
        g.drawImage(
        clicked.picture().image(), //image
        upper_left_x, // upper left x
        upper_left_y, // upper left y
        width, // width
        height, //height
        this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        switch (gamestate)
        {
            case 0 : start_menu(g2);
                break;
            case 1 : 
                {
                    in_game(g2);
                    if (image_display_time >= 0) show_picture(g2);
                    else engine.iteration();
                } break;
            case 2 : settings_menu(g2);
                break;
        }        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
        switch (gamestate)
        {
            case 0 : 
                    {
                        Button button = startmenu.mouseclicked(me);
                        gamestate = button.click();
                    } break;
                
            case 1 :
                    {
                        show_picture_state = 0;
                        image_zoom_time = 0;

                        clicked = engine.click(me.getY(), me.getX());

                        if (clicked != null)
                        {
                            image_display_time = 0;
                            System.out.println("card_id: "+clicked.id()+"  pair_id: "+clicked.pair_id());
                        }
                        else System.out.println("null");
                    } break;
            case 2 : 
                    {
                        Button button = settingsmenu.mouseclicked(me);
                        
                        int click = button.click();
                        if (click > 0)
                        {
                            if (click == 10) settingsmenu.changeResolution();
                          
                          
                        } else if (click == 0) this.setSize(800, 600);
                    }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me)
    {
        if (gamestate == 0) startmenu.mousehover(me);
        if (gamestate == 2) settingsmenu.mousehover(me);
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        System.out.println("key pressed");
        if (ke.getKeyCode() == KeyEvent.VK_ENTER)
        {
            System.out.println("enter pressed");
            gamestate = 0;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
