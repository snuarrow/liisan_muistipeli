/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli;

import java.awt.Color;
import javax.swing.JFrame;
import liisan_muistipeli.gui.MainFrame;
import liisan_muistipeli.logic.Global;

/**
 * Ei käytössä vielä!!!
 * 
 * @author hexvaara
 */
public class GameStarter
{
    JFrame f;
    Global global;
    
    public GameStarter(Global global)
    {
        this.global = global;
    }
    
    public void startFrame()
    {
        f = new JFrame();
        MainFrame m = new MainFrame(global);
        m.setBackground(Color.darkGray);
        f.add(m);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(global.getHorizontalsize(), global.getVerticalsize());
    }
    public void closeFrame()
    {
       
    }
}
