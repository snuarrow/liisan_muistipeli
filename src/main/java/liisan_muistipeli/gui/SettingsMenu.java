package liisan_muistipeli.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import liisan_muistipeli.logic.Global;

/**
 * Luokka asetusvalikon näkymälle ja napeille.
 */
public class SettingsMenu
{
    private ArrayList<Button> buttons;
    private Global global;
    private int xreso;
    private int yreso;
    private int resolution_index;
    private int[][] resolutions;
    private String[] difficulties;
    private int difficulties_index;
    
    //alignment variables
    private int column0_from_left;
    private int column1_from_left;
    private int headline_fontsize;
    private int fontsize;
    private Font headline_font;
    private Font font;
    
    public SettingsMenu(Global global)
    {
        column0_from_left = (int) (global.getVerticalsize()*0.2);
        column1_from_left = (int) (global.getVerticalsize()*0.8);
        headline_fontsize = 40;
        fontsize = 24;
        font = new Font("Monospaced", Font.PLAIN, fontsize);
        headline_font = new Font("Monospaced", Font.PLAIN, headline_fontsize);
        
        resolutions = new int[5][2];
        
        resolutions[0][0] = 1024;
        resolutions[0][1] = 768;
        resolutions[1][0] = 800;
        resolutions[1][1] = 600;
        resolutions[2][0] = 1280;
        resolutions[2][1] = 720;
        resolutions[3][0] = 1920;
        resolutions[3][1] = 1080;
        resolutions[4][0] = 1600;
        resolutions[4][1] = 900;
        
        resolution_index = global.getResolution();
        
        difficulties = new String[3];
        
        difficulties[0] = "kid's stuff";
        difficulties[1] = "normal";
        difficulties[2] = "autistic";
        
        difficulties_index = global.getDifficulty();
        
        
        xreso = global.getHorizontalsize();
        yreso = global.getVerticalsize();
        buttons = new ArrayList<>();
        this.global = global;
        createSetResolutionButton();
        createReturnButton();
        createApplyButton();
        createDifficultyButton();
    }
    public int[] getResolution()
    {
        int[] value = new int[2];
        value[0] = resolutions[resolution_index][0];
        value[1] = resolutions[resolution_index][1];
        return value;
    }
    public int getDifficulty()
    {
        return difficulties_index;
    }
    
    public ArrayList<Button> buttons()
    {
        return buttons;
    }
    public void drawHeadline(Graphics2D g)
    {
        //Font font = new Font("Monospaced", Font.PLAIN, 80);
        g.setFont(headline_font);
        g.setColor(Color.DARK_GRAY);
        g.drawString("muistipeli/settings", 100, 100);
    }
    public void createDifficultyButton()
    {
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.6);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.03);
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "difficulty", 12, font));
    }
    public void createReturnButton()
    {
        // skalaarit pitäis jotenki järkeistää, eli ei kovakoodata.
        
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.7);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.03);
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "return", 0, font));
    }
    
    public void createSetResolutionButton()
    {
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.65);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.03);
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "resolution", 10, font));
    }
    public void createApplyButton()
    {
        int fromleft = column0_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.75);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.03);
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "apply", 11, font));
    }
    public void showCurrentResolution(Graphics2D g)
    {
        String reso = resolutions[resolution_index][0]+" x "+resolutions[resolution_index][1];
        g.setFont(font);
        
        int fromleft = (int) column1_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.65);

        g.drawString(reso, fromleft , fromtop);
    }
    public void showCurrentDifficulty(Graphics2D g)
    {
        String difficulty = difficulties[difficulties_index];
        g.setFont(font);
        
        int fromleft = column1_from_left;
        int fromtop = (int) (global.getVerticalsize()*0.6);
    
        g.drawString(difficulty, fromleft , fromtop);
    }
    public void changeDifficulty()
    {
        difficulties_index++;
        if (difficulties_index > 2) difficulties_index = 0;
        global.setDifficulty(difficulties_index);
    }
    
    public void changeResolution()
    {
        resolution_index += 1;
        if (resolution_index > 4) resolution_index = 0;
        global.setResolution(resolution_index);
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
