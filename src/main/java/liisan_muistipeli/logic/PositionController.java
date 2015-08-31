package liisan_muistipeli.logic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Luokka joka kontrolloi korttien sijaintia pelissä, päivittää hitboxboard luokkaa sekä korttien koordinaatteja
 */
public class PositionController
{
    private final Global global;
    private final HitBoxBoard hitboxboard;
    private final HashMap<Integer, Card> cards;
    private final ArrayList<Integer> ids;
    
    public PositionController(Global global)
    {
        this.global = global;
        this.hitboxboard = new HitBoxBoard(global);
        this.cards = new HashMap<>();
        this.ids = new ArrayList<>();
    }
    public ArrayList<Integer> getIds()
    {
        return ids;
    }
    public HitBoxBoard getHitBoxBoard()
    {
        return hitboxboard;
    }
    
    /**
     * korjaa korttien hitboksit, mikäli niihin on törmäyksien jälkeen jäänyt reikiä.
     */
    public void refresh_hitboxes_of_all_cards()
    {
        hitboxboard.memset(0);
        for (Integer id : ids)
        {
            hitboxboard.setCard(cards.get(id));
        }
    }
    /**
     * palauttaa kortin joka on kyseisessä ruudussa.
     * @param y
     * @param x
     * @return card / null
     */
    public Card get_by_coordinates(int y, int x)
    {
        if (y < 0 || x < 0 || y > global.getVerticalsize()-1 || x > global.getHorizontalsize()-1) return null;
        
        if (hitboxboard.checkCell(y, x) != 0)
        {
            return cards.get(hitboxboard.checkCell(y, x));
        }
        return null;
    }
    
    /**
     * lisää kortin peliin
     * @param card 
     */
    public void addCard(Card card)
    {
        hitboxboard.setCard(card);
        cards.put(card.id(), card);
        ids.add(card.id());
    }
    public HashMap get_cards()
    {
        return cards;
    }
    /**
     * poistaa kortin pelistä, hajautustauluun se kuitenkin jää, mutta avainta ei enää ole jolla sen sieltä löytäisi.
     * @param card 
     */
    public void delete_card_permanent(Card card) // removes card from hitboxboard and from list of ids, it still stays in hashmap
    {
        hitboxboard.delCard(card);
        for (int i = 0; i < global.getCardamount(); i++) {
            if (ids.get(i) == card.id())
            {
                ids.remove(i);
                break;
            }
        }
    }
    
    /**
     * liikuttaa korttia, tarkistaa onko se törmännyt toiseen korttiin tai seinään
     * ja mikäli, niin kutsuu tarvittavaa funktiota.
     * @param id
     * @return 
     */
    public boolean move_card(int id)
    {
        boolean returnvalue = false;
        Card card = cards.get(id);
        
        if (card.velocity() > 0)
        {
            returnvalue = true;
            hitboxboard.delCard(card);
            
        
            double x = card.fx();
            double y = card.fy();
            x += card.velocity()*Math.sin(card.angle());
            y += card.velocity()*Math.cos(card.angle());
        
            if (!card.set_yx(y, x))
            {
                ping_against_the_wall(x, y, card);
            } // if fails, card is next to wall, else its free to move.
            
            hitboxboard.setCard(card);
            int collided_id = hitboxboard.checkCollision(card);
            card.slow();
            if (collided_id != 0) collide(card, cards.get(collided_id));
            
        }
        return returnvalue;
    }
    
    /**
     * muuttaa kortin liikkumiskulmaa, niin että se kimpoaa luonnollisesti seinästä
     * @param card 
     */
    public void ping_against_the_wall(double x, double y, Card card)
    {
        if (x >= global.getX_max_index() || x <= 0)
        {
            card.set_angle((2*Math.PI-card.angle()));
        } // 2*Pi-angle, vasen tai oikea laita.
        if (y >= global.getY_max_index() || y <= 0)
        {
            card.set_angle((Math.PI-card.angle()));
        } // Pi-angle, ylä tai alalaita.
    }
    
    /**
     * törmäyttää kaksi korttia, laskee näiden koordinaateista uuden liikkumiskulman
     * paikallaan olevalle kortille sekä siirtää liikkuvan kortin liike-energian paikallaan olevalle.
     * liikkuva pysähtyy, fysiikka on vielä alkeellista
     * @param moving
     * @param still 
     */
    public void collide(Card moving, Card still)
    {
        Radian angle = new Radian();
        
        // katsotaan kahden kortin koordinaattien kulma ja siirretään liikkuvan velocity paikallaan olevalle. jokaisella ympyrän neljäksellä tapaus pitää käsitellä eri tavalla.
        
        if (moving.fx() > still.fx() && moving.fy() <= still.fy())  // toisella neljänneksellä.
        {
            //System.out.println("toka");
            angle.set(2*Math.PI+Math.atan((still.fx()-moving.fx())/(still.fy()-moving.fy())));
        }
        if (moving.fx() >= still.fx() && moving.fy() > still.fy()) // ensimmäisellä neljänneksellä
        {
            //System.out.println("eka");
            angle.set(Math.PI+Math.atan((moving.fx()-still.fx())/(moving.fy()-still.fy())));
        }
        if (moving.fx() < still.fx() && moving.fy() <= still.fy()) // kolmas neljännes
        {
            //System.out.println("kolmas atan: "+(still.fx()-moving.fx())/(still.fy()-moving.fy()));
            angle.set(Math.atan((still.fx()-moving.fx())/(still.fy()-moving.fy())));
        }
        if (moving.fx() < still.fx() && moving.fy() > still.fy()) // neljäs neljännes
        {
            //System.out.println("neljäs");
            angle.set(Math.PI+Math.atan((still.fx()-moving.fx())/(still.fy()-moving.fy())));
        }
        
        still.set_velocity(moving.velocity());
        still.set_angle(angle.get());
        moving.set_angle(0);
        moving.set_velocity(0);
    }
}