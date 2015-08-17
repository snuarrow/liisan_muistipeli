/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author hexvaara
 */
public class MainFrame extends JPanel implements ActionListener, MouseListener
{
    private Timer t;
    private Engine engine;
    private Global global;
    private HashMap cards;
    private Picture picture;
    private int counter = 0;
    private int clicked_id = 0;
    
    public MainFrame(Global global)
    {
        picture = new Picture(0, "acid3.png");
        this.global = global;
        engine = new Engine(global);
        cards = engine.getCards();
        
        addMouseListener(this);
        t = new Timer(global.getTimer_interval(), this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        if (counter == 0) engine.iteration();
        else
        {
            counter++;
            if (counter > 1000) counter = 0;
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int id : engine.getPC().getIds())
        {
            Card instance = engine.getCards().get(id);
            g2.drawImage(picture.image(), instance.x() , instance.y(), global.getCardsize(), global.getCardsize(), this);
        }
        if (counter != 0)
        {
            Card instance = engine.getCards().get(clicked_id);
            g2.drawImage(picture.image(), instance.x()-counter/2, instance.y()-counter/2, counter+global.getCardsize(), counter+global.getCardsize(), Color.WHITE, this);
        }
        
    }
    

    @Override
    public void mouseClicked(MouseEvent me) {
        if (counter == 0)
        {
            Card card = engine.click(me.getY(), me.getX());
            if (card != null)
            {
                clicked_id = card.id();
                counter++;
            }
        }
        
        Card card = engine.click(me.getY(), me.getX());
        
        if (card != null) System.out.println("asd");
        
        //engine.getCards().get(1).set_velocity(1);
        
        System.out.println("clicked!");
        
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
