package liisan_muistipeli;

import liisan_muistipeli.logic.Global;

public class Main
{
    public static void main(String[] args)
    {
        Global global = new Global();
        GameStarter gamestarter = new GameStarter(global);
        gamestarter.startFrame();        
    }
}
