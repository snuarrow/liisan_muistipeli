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
public class MainFrame extends JPanel implements ActionListener, MouseListener
{
    private Timer t;
    private int runtime;
    private Engine engine;
    private Global global;
    private HashMap cards;
    private Picture picture;
    private Picture background;
    private int counter = 0;
    private int clicked_id = 0;
    
    public MainFrame(Global global)
    {
        runtime = 0;
        picture = new Picture(0, "acid3tb.png");
        background = new Picture(0, "background.png");
        this.global = global;
        engine = new Engine(global);
        cards = engine.getCards();
        
        addMouseListener(this);
        t = new Timer(global.getTimer_interval(), this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (runtime < Integer.MAX_VALUE) runtime += 1;
        repaint();
        if (counter == 0) engine.iteration();
        else
        {
            counter++;
            if (counter > 1000) counter = 0;
        }
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
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (runtime < 1000) start_animation(g2);
        else in_game(g2);
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
        
        Card card = engine.click(me.getY(), me.getX());
        
        if (card != null) System.out.println("card_id: "+card.id()+"  pair_id: "+card.pair_id());
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
}
