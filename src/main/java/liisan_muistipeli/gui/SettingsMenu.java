/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import liisan_muistipeli.logic.Global;

/**
 *
 * @author hexvaara
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
    
    public SettingsMenu(Global global)
    {
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
        
        resolution_index = 0;
        
        difficulties = new String[3];
        
        difficulties[0] = "kid's stuff";
        difficulties[1] = "normal";
        difficulties[2] = "autistic";
        
        difficulties_index = 1;
        
        
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
    
    public ArrayList<Button> buttons()
    {
        return buttons;
    }
    public void drawHeadline(Graphics2D g)
    {
        Font font = new Font("Monospaced", Font.PLAIN, 80);
        g.setFont(font);
        g.setColor(Color.DARK_GRAY);
        g.drawString("settings", 100, 100);
    }
    public void createDifficultyButton()
    {
        int fromleft = (int) (global.getHorizontalsize()*0.6);
        int fromtop = (int) (global.getVerticalsize()*0.2);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.07);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "difficulty", 12));
    }
    public void createReturnButton()
    {
        // skalaarit pitäis jotenki järkeistää, eli ei kovakoodata.
        
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.7);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.07);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "return", 0));
    }
    
    public void createSetResolutionButton()
    {
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.4);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.03);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "change resolution", 10));
    }
    public void createApplyButton()
    {
        int fromleft = (int) (global.getHorizontalsize()*0.2);
        int fromtop = (int) (global.getVerticalsize()*0.9);
        
        int width = (int) (global.getVerticalsize()*0.25);
        int height = (int) (global.getVerticalsize()*0.1);
        
        int fontsize = (int) (global.getHorizontalsize()*0.03);
        
        
        buttons.add(new Button(fromleft, fromtop, width, height, fontsize, "apply", 11));
    }
    public void showCurrentResolution(Graphics2D g)
    {
        String reso = resolutions[resolution_index][0]+" x "+resolutions[resolution_index][1];
        Font font = new Font("Monospaced", Font.PLAIN, (int) (global.getHorizontalsize()*0.03));
        g.setFont(font);
        
        int fromleft = (int) (global.getHorizontalsize()*0.6);
        int fromtop = (int) (global.getVerticalsize()*0.5);
        
        
        g.drawString(reso, fromleft , fromtop);
    }
    public void showCurrentDifficulty(Graphics2D g)
    {
        String difficulty = difficulties[difficulties_index];
        Font font = new Font("Monospaced", Font.PLAIN, (int) (global.getHorizontalsize()*0.03));
        g.setFont(font);
        
        int fromleft = (int) (global.getHorizontalsize()*0.6);
        int fromtop = (int) (global.getVerticalsize()*0.4);
        g.drawString(difficulty, fromleft , fromtop);
    }
    public void changeDifficulty()
    {
        difficulties_index += 1;
        if (difficulties_index > 2) difficulties_index = 0;
    }
    
    public void changeResolution()
    {
        resolution_index += 1;
        if (resolution_index > 4) resolution_index = 0;
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
