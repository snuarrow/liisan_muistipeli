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

public class MainFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private Timer t;
    private int runtime;
    private Engine engine;
    private Global global;
    private HashMap cards;
    private Picture picture;
    private Picture background;
    private int image_display_time;
    private int counter = 0;
    private int clicked_id = 0;
    private Card clicked = null;
    private Button playbutton;
    private int image_zoom_time;
    
    public MainFrame(Global global)
    {
        playbutton = new Button((int)(global.getHorizontalsize()*0.2),(int)(global.getVerticalsize()*0.7),global.getCardsize()*3,global.getCardsize(),global.getCardsize(),"play");
        
        image_zoom_time = 0;
        image_display_time = -1;
        runtime = 0;
        picture = new Picture(0, "acid3tb.png");
        background = new Picture(0, "background.png");
        this.global = global;
        engine = new Engine(global);
        cards = engine.getCards();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        t = new Timer(global.getTimer_interval(), this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (runtime < Integer.MAX_VALUE) runtime += 1;
        repaint();
            
        counter++;
        if (counter > 1000) counter = 0;
    }
    
    public void start_menu(Graphics2D g)
    {
        //playbutton.setBackGroundColor(Color.darkGray);
        //playbutton.setBorderColor(Color.GRAY);
        //playbutton.setFontColor(Color.GRAY);
        playbutton.draw(g);
//        Font font = new Font("Sherif", Font.PLAIN, 96);
//        g.setFont(font);
//        g.setColor(Color.yellow);
//        g.draw3DRect(70, 400, 600, 200, true);
//        
//        g.drawString("play", 100, 500);
    }
    
    public void start_animation(Graphics2D g)
    {
        
        Picture q = new Picture(0,"pluto.gif");
        
        g.drawImage(q.image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize() ,this);
    }
    public void in_game(Graphics2D g)
    {
        
            g.drawImage(background.image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize() ,this);
        
            for (int id : engine.getPC().getIds())
            {
                Card instance = engine.getCards().get(id);
                g.drawImage(picture.image(), instance.x() , instance.y(), global.getCardsize(), global.getCardsize(), this);
            }
        
    }
    
    private int show_picture_state = 0;
    private int upper_left_x;
    private int upper_left_y;
    private int width;
    private int height;
    
    
    public void show_picture(Graphics2D g)
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
        else if (show_picture_state == 1) 
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
        
//        int upper_left_x = clicked.x()-(image_display_time/2);
//        int upper_left_y = (int) (clicked.y()-((image_display_time/2)*clicked.picture().ratio()));
//        int width = global.getCardsize()+image_display_time;
//        int height = (int) ((global.getCardsize()+ image_display_time)*clicked.picture().ratio());
//        
//        global.getImage_zoomtime_ms();
//        global.getImage_displaytime_ms();
//        global.getImage_maxsize();
//        
//        
//        if (upper_left_x + width > global.getHorizontalsize())
//        {
//            int correction = upper_left_x + width - global.getHorizontalsize();
//            upper_left_x -= correction;
//        }
//        if (upper_left_y + height > global.getVerticalsize())
//        {
//            int correction = upper_left_y + height - global.getVerticalsize();
//            upper_left_y -= correction;
//        }
//        
//        
//        
//        
//        
//        g.drawImage(
//                clicked.picture().image(), //image
//                upper_left_x, // upper left x
//                upper_left_y, // upper left y
//                width, // width
//                height, //height
//                this);
//        image_display_time += 3;
//        if (image_display_time > global.getImage_displaytime_ms()) image_display_time = -1;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (runtime < 1000)
        {
            start_animation(g2);
            start_menu(g2);
        }
        else
        {
            
            in_game(g2);
            if (image_display_time >= 0)
            {
                show_picture(g2);
            } else {
                engine.iteration();
            }
        }
        
        //if (counter != 0)
        //{
        //    Card instance = engine.getCards().get(clicked_id);
        //    g2.drawImage(picture.image(), instance.x()-counter/2, instance.y()-counter/2, counter+global.getCardsize(), counter+global.getCardsize(), Color.WHITE, this);
        //}
        
    }
    
    
    

    @Override
    public void mouseClicked(MouseEvent me) {
        
        
//        if (counter == 0)
//        {
//            Card card = engine.click(me.getY(), me.getX());
//            if (card != null)
//            {
//                clicked_id = card.id();
//                counter++;
//            }
//        }
        show_picture_state = 0;
        image_zoom_time = 0;
        
        clicked = engine.click(me.getY(), me.getX());
        
        if (clicked != null) {
            image_display_time = 0;
            System.out.println("card_id: "+clicked.id()+"  pair_id: "+clicked.pair_id());
        }
        else System.out.println("null");
        
        //engine.getCards().get(1).set_velocity(1);
        
        //System.out.println("clicked!");
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        if (me.getX() > playbutton.x() && me.getY() > playbutton.y()-playbutton.height() && me.getY() < playbutton.y() && me.getX() < playbutton.x()+playbutton.width()) playbutton.setFontColor(Color.yellow);
        else playbutton.setFontColor(Color.DARK_GRAY);
    }
}
