package liisan_muistipeli.logic;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EngineTest
{
    private Global g;
    private Engine e;
    
    
    public EngineTest()
    {
        g = new Global();
        e = new Engine(g);
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
        e = new Engine(g);
    }
    
    @After
    public void tearDown()
    {
    }
    @Test
    public void test_constructor_builds_cards()
    {
        assertEquals(g.getCardamount(), e.getPC().getIds().size());
    }
    @Test
    public void test_click_inside_hitbox_upper_left_corner()
    {
        assertNotNull(e.click(g.getClearance(), g.getClearance()));
    }
    @Test
    public void test_click_inside_hitbox_center()
    {
        //e.create_paired_Cards();
        Card card = e.click(g.getClearance()+g.getCardsize()/2, g.getClearance()+g.getCardsize()/2);
        assertNotNull(card);
    }
    @Test
    public void test_click_outside_hitbox()
    {
        g = new Global();
        g.setVerticalsize(46);
        g.setHorizontalsize(51);
        g.setCardsize(4);
        e = new Engine(g);
        //e.getPC().getHitBoxBoard().print_blank();
        
        for (int y = 0; y < g.getVerticalsize(); y += 5)
        {
              for (int x = 0; x < g.getHorizontalsize(); x++)
              {
                  assertNull(e.click(y, x));
                  
                  // 0
              }
        }
        for (int x = 0; x < g.getHorizontalsize(); x += 5)
        {
            for (int y = 0; y < g.getVerticalsize(); y++)
            {
                assertNull(e.click(y, x));
            }
        }
    }
    @Test
    public void test_click_inside_hitbox()
    {
        g = new Global();
        g.setVerticalsize(46);
        g.setHorizontalsize(51);
        g.setCardsize(4);
        e = new Engine(g);
        e.getPC().getHitBoxBoard().print_blank();
        
        for (int i = 0; i < 1; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                for (int y = 0; y < g.getCardsize(); y++)
                {
                    for (int x = 0; x < g.getCardsize(); x++)
                    {
                        assertNotNull(e.click(y+1+(i*5), x+1+(j*5)));
                    }
                }
            }
        }
    }
    @Test
    public void test_iteration_at_least_something_happens()
    {
        g = new Global();
        g.setVerticalsize(46);
        g.setHorizontalsize(51);
        g.setCardsize(4);
        e = new Engine(g);
        boolean iterated = e.iteration();
        Card card = null;
        //e.getPC().getHitBoxBoard().print_blank();
        
        for (int y = 0; y < g.getVerticalsize(); y += 5)
        {
              for (int x = 0; x < g.getHorizontalsize(); x++)
              {
                  if (e.click(y, x) != null) card = e.click(y, x);
                  
                  // 0
              }
        }
        for (int x = 0; x < g.getHorizontalsize(); x += 5)
        {
            for (int y = 0; y < g.getVerticalsize(); y++)
            {
                if (e.click(y, x) != null) card = e.click(y, x);
            }
        }
        assertTrue(iterated);
        assertNotNull(card);
    }
    @Test
    public void test_iteration_returns_false_if_no_velocity_in_any_card()
    {
        g = new Global();
        g.setVerticalsize(46);
        g.setHorizontalsize(51);
        g.setCardsize(4);
        e = new Engine(g);
        for (int id : e.getPC().getIds())
        {
            e.getCards().get(id).set_velocity(0);
        }
        assertFalse(e.iteration());
    }
}
