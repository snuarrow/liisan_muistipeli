/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hexvaara
 */
public class HitBoxBoardTest
{
    private Global g;
    private HitBoxBoard h;
    private Card c;
    
    public HitBoxBoardTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        
        g = new Global();
        c = new Card(g, 0,0,1,2,new Picture(9, "acid3.png"));
        h = new HitBoxBoard(g);
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void test_memset()
    {
        h.memset(421);
        
        for (int y = 0; y < g.getY_max_index(); y++)
        {
              for (int x = 0; x < g.getX_max_index(); x++)
              {
                  assertEquals(421, h.checkCell(y, x));
              }
        }
    }
    
    @Test
    public void test_set_card_writes_correct_amount_of_ids()
    {
        // this section for hollow square hitboxes:
        h.setCard(c);
        
        int counter = count_amount_of_ids(c.id());
        
        assertEquals(g.getCardsize()*4-4, counter);
        
        // this section for hollow circle hitboxes:
        // NOT IMPLEMENTED YET
    }
    
    public int count_amount_of_ids(int id)
    {
        int counter = 0;
        for (int y = 0; y < g.getY_max_index(); y++)
        {
            for (int x = 0; x < g.getX_max_index(); x++)
            {
                if (h.checkCell(y, x) == id) counter += 1;
            }
        }
        return counter;
    }
    
    @Test
    public void test_after_set_del_0_ids()
    {
        h.setCard(c);
        h.delCard(c);
        assertEquals(0, count_amount_of_ids(c.id()));
    }
    @
    Test
    public void test_collide_should_collide()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        h = new HitBoxBoard(g);
        Card temp = new Card(g,0,0,1,2,new Picture(0, "acid3.png"));
        Card temp2 = new Card(g,4,4,2,1,new Picture(0, "acid3.png"));
        h.setCard(temp);
        h.setCard(temp2);
        assertEquals(1, h.checkCollision(temp2));
        
    }
    @Test
    public void test_collide_should_not_collide()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        h = new HitBoxBoard(g);
        Card temp = new Card(g,0,0,1,2,new Picture(0, "acid3.png"));
        Card temp2 = new Card(g,5,5,2,1,new Picture(0, "acid3.png"));
        h.setCard(temp);
        h.setCard(temp2);
        assertEquals(0, h.checkCollision(temp2));
    }
    @Test
    public void temp_test()
    {
        g.setVerticalsize(10);
        g.setHorizontalsize(10);
        g.setCardsize(5);
        System.out.println("cardsize: "+g.getCardsize());
        h = new HitBoxBoard(g);
        Card temp = new Card(g,0,0,1,2,new Picture(0, "acid3.png"));
        Card temp2 = new Card(g,4,4,2,1,new Picture(0, "acid3.png"));
        h.setCard(temp);
        h.setCard(temp2);
        System.out.println("checkCollision temp2: "+h.checkCollision(temp2));
        h.print();
    }
    
}