package liisan_muistipeli.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import liisan_muistipeli.GameStarter;
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
    private final Timer t;
    private Engine engine;
    private final Global global;
    private final Picture smiley;
    private final Picture background;
    private int image_display_time;
    private Card clicked = null;
    private int image_zoom_time;
    private final StartMenu startmenu;
    private final SettingsMenu settingsmenu;
    private final EndMenu endmenu;
    
    private int card_clicks;
    private int gamestate; // 0 = startmenu, 1 = in game, 2 = in settings menu
    private final GameStarter gamestarter;
    
    
    public MainFrame(Global global, GameStarter gamestarter)
    {
        this.gamestarter = gamestarter;
        
        card_clicks = 0;
        gamestate = 0;
        
        startmenu = new StartMenu(global);
        settingsmenu = new SettingsMenu(global);
        endmenu = new EndMenu(global);
        
        image_zoom_time = 0;
        image_display_time = -1;
        smiley = new Picture(0, "acid3tb.png");
        background = new Picture(0, "background.png");
        this.global = global;
        engine = new Engine(global);
        
        
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
    
    /**
     * renderöi aloitusvalikon näkymän
     * @param g 
     */
    public void start_menu(Graphics2D g)
    {
        g.drawImage(new Picture(0, "pluto.gif").image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize(), this);
        
        startmenu.drawHeadline(g);
        
        for (Button button : startmenu.buttons())
        {
            button.draw(g);
        }
    }
    
    /**
     * renderöi asetusvalikon näkymän
     * @param g 
     */
    public void settings_menu(Graphics2D g)
    {
        g.drawImage(new Picture(0, "pluto.gif").image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize(), this);
        
        settingsmenu.drawHeadline(g);
        settingsmenu.showCurrentResolution(g);
        settingsmenu.showCurrentDifficulty(g);
        
        for (Button button : settingsmenu.buttons())
        {
            button.draw(g);
        }
    }
    
    /**
     * renderöi pelin päättymisvalikon näkymän
     * @param g 
     */
    public void end_menu(Graphics2D g)
    {
        g.drawImage(new Picture(0, "pluto.gif").image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize(), this);
        
        endmenu.drawScore(g, (int) ((global.getCardamount()/(double)card_clicks)*100));
        
        for (Button button : endmenu.buttons())
        {
            button.draw(g);
        }
    }
    
    /**
     * renderöi pelinäkymän
     * @param g 
     */
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
    
    /**
     * piirtää zoomautuvan kuvan
     * @param g 
     */
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
        
        g.drawImage
        (
            clicked.picture().image(), //image
            upper_left_x, // upper left x
            upper_left_y, // upper left y
            width, // width
            height, //height
            this
        );
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
            case 3 : end_menu(g2);
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
                        System.out.println(engine.getPC().getIds().size());

                        if (clicked != null)
                        {
                            if (engine.getPC().getIds().isEmpty()) gamestate = 3;
                            card_clicks += 1;
                            image_display_time = 0;
                            System.out.println("card_id: "+clicked.id()+"  pair_id: "+clicked.pair_id());
                        }
                        else System.out.println("null");
                    } break;
            case 2 : 
                    {
                        // <--------------------------------------------------------------------------------------------------------------------switch case tänne!
                      
                        Button button = settingsmenu.mouseclicked(me);
                        
                        int click = button.click();
                        
                        if (click == 10) 
                        {
                            settingsmenu.changeResolution();
                            System.out.println("change resolution called "+click);
                        } // change resolution button clicked
                        if (click == 11)
                        {
                                System.out.println("apply called");
                                global.setVerticalsize(settingsmenu.getResolution()[1]);
                                global.setHorizontalsize(settingsmenu.getResolution()[0]);
                                gamestarter.closeFrame();
                                gamestarter.startFrame();
                        }
                        if (click == 12)
                        {
                            settingsmenu.changeDifficulty();
                        }
                          
                        if (click == 0) // return button clicked 
                        {
                            System.out.println("return called");
                            gamestate = 0;
                        }
                    }
            case 3 :
                    {
                        Button button = endmenu.mouseclicked(me);
                        gamestate = button.click();
                        engine = new Engine(global);
                        image_zoom_time = 0;
                        image_display_time = -1;
                    }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me)
    {
        // <-----------------------------------------------------------------switch case tänne!
        if (gamestate == 0) startmenu.mousehover(me);
        if (gamestate == 2) settingsmenu.mousehover(me);
        if (gamestate == 3) endmenu.mousehover(me);
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        // <-------------------------------------------------------------------ei toimi, miksei?
        
        System.out.println("key pressed");
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.out.println("esc pressed");
            gamestate = 0;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
}
