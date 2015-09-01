package liisan_muistipeli;

import liisan_muistipeli.logic.Global;

public class Main
{
    public static void main(String[] args)
    {
        //System.out.println("*** start ***");
        
        // start main frame here
//        JFrame f = new JFrame();
//        Global g = new Global();
//        
//        
//        
//        MainFrame m = new MainFrame(g);
//        m.setBackground(Color.darkGray);
//        f.add(m);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setSize(g.getHorizontalsize(), g.getVerticalsize());
        
        Global global = new Global();
        GameStarter gamestarter = new GameStarter(global);
        gamestarter.startFrame();
        
        //System.out.println("*** end ***");
    }
}
