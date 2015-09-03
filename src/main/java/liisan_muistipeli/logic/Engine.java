package liisan_muistipeli.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Logiikan käyttöluokka, joka toimii kommunikaatiorajapintana graafiselle
 * käyttöliittymälle, antaa korteille liikettä ja peliä aloittaessa luo uudet
 * kortit.
 */
public final class Engine
{
    private final PositionController pc;
    private Global global;
    private int last_clicked_picture = 0;

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

    /**
     * kun korttia klikataan, selvittää tämä funktio, onko klikkaus kortin
     * hitboksin sisäpuolella, lähtee algoritmisesti klikkauspisteestä kahteen
     * suuntaan ja mikäli molemmissa suunnissa vastaan tulee sama id, ollaan
     * kortin sisäpuolella, muuten ei. palautetaan kortti tai null.
     *
     * @param y koordinaatti
     * @param x koordinaatti
     * @return kortti tai null
     */
    public Card check_if_click_inside_hitbox(int y, int x)
    {
        boolean up = true;
        boolean down = true;
        Card upper = null;
        Card lower = null;
        
        for (int i = 0; i < global.getCardsize()+1; i++)
        {
            if (y-i >= 0) // upper inside board
            {
                if (up && pc.get_by_coordinates(y-i, x) != null)
                {
                    up = false;
                    upper = pc.get_by_coordinates(y-i, x);
                }
            }
            if (y+i < global.getVerticalsize()) // lower inside board
            {
                if (down && pc.get_by_coordinates(y+i, x) != null)
                {
                    down = false;
                    lower = pc.get_by_coordinates(y+i, x);
                }
            }
            
            
        }
        
        if (upper == null) return null;
        if (lower == null) return null;
        if (upper.id() == lower.id()) return upper;
        else return null;
    }

    /**
     * Kortin klikkaus, tarkistaa onko klikattu taustaa vai korttia, asettaa
     * kortille nopeuden ja satunnaisen lähtökulman välillä 0-360 astetta.
     *
     * @param y
     * @param x
     * @return kortti jota klikattiin, null jos ei osunut.
     */
    public Card click(int y, int x)
    {
        pc.refresh_hitboxes_of_all_cards();
        Card found = check_if_click_inside_hitbox(y, x);

        if (found != null)
          {
            found.set_velocity(global.getDefaultStartSpeed());
            found.set_angle((new Random().nextDouble()) * Math.PI * 2);
            if (last_clicked_picture == found.pair_id())
              {
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

    /**
     * pelimoottorin yksi iteraatio, käy kortit läpi ja liikuttaa niitä joilla
     * on velocityä, mikäli millään ei ole, palauttaa false, jolloin
     * pelimoottori voidaan pysäyttää hetkellisesti idle rasituksen
     * välttämiseksi.
     *
     * @return liikkuuko joku
     */
    public boolean iteration()
    {
        boolean returnvalue = false;
        for (int i : pc.getIds())
          {
            if (pc.move_card(i))
              {
                returnvalue = true;
              }
          }
        return returnvalue;
    }

    /**
     * Pelin alkaessa luo joukon kuvallisia kortteja ja antaa ne position
     * controllerille. kortteja luodaan parillinen määrä ja yksi kuva kahta
     * korttia kohden.
     */
    public void create_paired_Cards()
    {
        ArrayList<int[]> sc = startcoordinates();
        ArrayList<Picture> pictures = pick_pictures();

        for (int i = 0, j = 0; j < pictures.size(); i += 2, j += 1)
          {
            int y0 = sc.get(i)[0];
            int x0 = sc.get(i)[1];
            int y1 = sc.get(i + 1)[0];
            int x1 = sc.get(i + 1)[1];

            int x_id = i + 1;
            int y_id = i + 2;

            Picture picture = pictures.get(j);

            Card instance0 = new Card(global, y0, x0, x_id, y_id, picture);
            Card instance1 = new Card(global, y1, x1, y_id, x_id, picture);

            Random r = new Random();

            instance0.set_velocity(global.getDefaultStartSpeed());
            instance1.set_velocity(global.getDefaultStartSpeed());

            instance0.set_angle(r.nextDouble() * 2 * Math.PI);
            instance1.set_angle(r.nextDouble() * 2 * Math.PI);

            pc.addCard(instance0);
            pc.addCard(instance1);
          }
    }

    /**
     * palauttaa sekoitetun aloitusjärjestyksen korteille.
     *
     * @return
     */

    private ArrayList startcoordinates()
    {
        int a = 0;
        int b = 1;

        int[][] startcoordinates = new int[global.getCardamount()][2];

        int master_index = 0;

        for (int y = 0; y < global.getVerticalamount(); y++)
          {
            for (int x = 0; x < global.getHorizontalamount(); x++)
              {

                if (master_index < global.getCardamount())
                  {
                    int cardindex = y * global.getHorizontalamount() + x;
                    startcoordinates[cardindex][0] = global.getClearance() + y * global.getSlot();
                    startcoordinates[cardindex][1] = global.getClearance() + x * global.getSlot();
                  }
                master_index += 1;
              }
          }

        ArrayList<int[]> returnarray = new ArrayList<>();

        for (int i = 0; i < global.getCardamount(); i++)
          {
            int[] instance = new int[2];
            instance[0] = startcoordinates[i][0];
            instance[1] = startcoordinates[i][1];
            returnarray.add(instance);
          }

        Collections.shuffle(returnarray);

        return returnarray;
    }

    /**
     * noukkii kuvakansiosta satunnaisesti valitun kuvajoukon jonka koko on
     * puolet korttien määrästä.
     *
     * @return
     */
    private ArrayList<Picture> pick_pictures()
    {
        ArrayList<Integer> all_picture_ids = new ArrayList<>();
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < global.getPicturesInFolder(); i++)
          {
            all_picture_ids.add(i);
          }
        Collections.shuffle(all_picture_ids);

        for (int i = 0; i < global.getCardamount() / 2; i++)
          {
            Picture picture = new Picture(i, all_picture_ids.get(i) + "");
            pictures.add(picture);
          }
        return pictures;
    }
}