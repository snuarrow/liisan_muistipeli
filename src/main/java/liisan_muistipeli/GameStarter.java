package liisan_muistipeli;

import java.awt.Color;
import javax.swing.JFrame;
import liisan_muistipeli.gui.MainFrame;
import liisan_muistipeli.logic.Global;

/**
 * Starts and closes JFrame, used to restart game after altering settings.
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
        MainFrame m = new MainFrame(global, this);
        m.setBackground(Color.darkGray);
        f.add(m);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(global.getHorizontalsize(), global.getVerticalsize());
        f.setLocationRelativeTo(null);
    }
    public void closeFrame()
    {
       f.setVisible(false);
       f.dispose();
    }
}
