/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author hexvaara
 */
public final class Engine {
    
    private final PositionController pc;
    private Global global;
    private int last_clicked_picture = 0;
    private int last_clicked_card_id = 0;
    
    public Engine(Global global)
    {
        this.global = global;
        pc = new PositionController(global);
        create_paired_Cards();
    }
    public HashMap<Integer, Card> getCards()
    {
        return pc.get_cards();
    }
    
    
    public Card check_if_click_inside_hitbox(int y, int x)
    {
        // täällä bugi, ei tarkista meneekö yli pelikentän ja rumaa koodia.
        
        boolean up = true;
        boolean down = true;
        Card found = null;
        for (int i = 0; i < global.getCardsize(); i++) {
            if (up && pc.get_by_coordinates(y+i, x) != null)
            {
                if (found == null)
                {
                    up = false;
                    found = pc.get_by_coordinates(y+i, x);
                } else if (found == pc.get_by_coordinates(y+i, x)) break;
            }
            if (down && pc.get_by_coordinates(y-1, x) != null)
            {
                if (found == null)
                {
                    down = false;
                    found = pc.get_by_coordinates(y-i, x);
                } else if (found == pc.get_by_coordinates(y-i, x)) break;
            }
        }
        return found;
    }

    public Card click(int y, int x)
    {
        Card found = check_if_click_inside_hitbox(y, x);
        
        if (found != null) 
        {
            found.set_velocity(global.getDefaultStartSpeed());
            found.set_angle((new Random().nextDouble())*Math.PI*2);
            if (last_clicked_picture == found.pair_id()) {
                pc.delete_card_permanent(found);
                pc.delete_card_permanent((Card) pc.get_cards().get(last_clicked_picture));
            }
            last_clicked_picture = found.id();
        }
        return found;
    }
    
    public PositionController getPC()
    {
        return pc;
    }
    public boolean iteration() // moves all cards which have velocity, if none has, returns false.
    {
        boolean returnvalue = false;
        for (int i : pc.getIds()) {
            if (pc.move_card(i)) returnvalue = true;
        }
        return returnvalue;
    }
    public void create_paired_Cards()
    {
        ArrayList<int[]> sc = startcoordinates();
        ArrayList<Picture> pictures = pick_pictures();
        
        for (int i = 0, j = 0; j < pictures.size(); i += 2, j += 1) {
            int y0 = sc.get(i)[0];
            int x0 = sc.get(i)[1];
            int y1 = sc.get(i+1)[0];
            int x1 = sc.get(i+1)[1];
            
            int x_id = i+1;
            int y_id = i+2;
            
            Picture picture = pictures.get(j);
            
            Card instance0 = new Card(global, y0, x0, x_id, y_id, picture);
            Card instance1 = new Card(global, y1, x1, y_id, x_id, picture);
            
            pc.addCard(instance0);
            pc.addCard(instance1);
        }
        
        // make cards, add same picture to two coordinates.
        
        
    }
    private ArrayList startcoordinates() // returns shuffled start coordinate matrix
    {
        int a = 0;
        int b = 1;
        
        
        int[][] startcoordinates = new int[global.getCardamount()][2];
        //int[][] startcoordinates = new int[62][2];
        
        int master_index = 0;
        
        for (int y = 0; y < global.getVerticalamount(); y++) {
            for (int x = 0; x < global.getHorizontalamount(); x++) {
                
                if (master_index < global.getCardamount())
                {
                    int cardindex = y*global.getHorizontalamount()+x;
                    startcoordinates[cardindex][0] = global.getClearance()+y*global.getSlot();
                    startcoordinates[cardindex][1] = global.getClearance()+x*global.getSlot();
                }
                master_index += 1;                
            }
        }
        
        ArrayList<int[]> returnarray = new ArrayList<>();
        
        for (int i = 0; i < global.getCardamount(); i++) {
            int[] instance = new int[2];
            instance[0] = startcoordinates[i][0];
            instance[1] = startcoordinates[i][1];
            returnarray.add(instance);
        }
        
        Collections.shuffle(returnarray);
        
        return returnarray;
    }
    private ArrayList<Picture> pick_pictures() // picks cardamount/2 random pics from images folder
    {
        ArrayList<Integer> all_picture_ids = new ArrayList<>();
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < global.getPicturesInFolder(); i++) {
            all_picture_ids.add(i);
        }
        Collections.shuffle(all_picture_ids);
        
        for (int i = 0; i < global.getCardamount()/2; i++) {
            Picture picture = new Picture(i, all_picture_ids.get(i)+".jpg");
            pictures.add(picture);
        }
        return pictures;
    }
}

