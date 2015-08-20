/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

import java.util.ArrayList;
import java.util.HashMap;
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
public class PositionControllerTest
{
    private PositionController pc;
    private Global g;
    private Card c;
    
    public PositionControllerTest()
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
        pc = new PositionController(new Global());
        c = new Card(g, 0,0,1,2, new Picture(0, "acid3.png"));
        pc.addCard(c);
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void test_constructor()
    {
        pc = new PositionController(new Global());
        assertEquals(0, pc.getIds().size());
        assertEquals(0, pc.get_cards().size());
    }
    @Test
    public void test_ids_size_is_1_with_one_card()
    {
        assertEquals(1, pc.getIds().size());
    }
    @Test
    public void test_hashmap_size_is_1_with_one_card()
    {
        assertEquals(1, pc.get_cards().size());
    }
    @Test
    public void test_delete_existing_card_permanently()
    {
        pc.delete_card_permanent(c);
        assertEquals(1, pc.get_cards().size());
        assertEquals(0, pc.getIds().size());
    }
    @Test
    public void test_get_by_coordinates_1()
    {
        assertEquals(1, pc.get_by_coordinates(0, 0).id());
    }
    @Test
    public void test_get_by_coordinates_null()
    {
        assertNull(pc.get_by_coordinates(1, 1));
    }
    @Test
    public void test_delete_card_permanent()
    {
        pc.delete_card_permanent(c);
        assertNull(pc.get_by_coordinates(0, 0));
        assertEquals(0,pc.getIds().size());
    }
    @Test
    public void test_move_simple_right()
    {
        c.set_angle(Math.PI/2);
        c.set_velocity(1);
        pc.move_card(c.id());
        assertEquals(1, c.x());
        assertEquals(0, c.y());
        assertNull(pc.get_by_coordinates(0, 0));
    }
    @Test
    public void test_move_simple_down()
    {
        c.set_angle(0);
        c.set_velocity(1);
        pc.move_card(c.id());
        assertEquals(0, c.x());
        assertEquals(1, c.y());
        assertNull(pc.get_by_coordinates(0, 0));
    }
    @Test
    public void test_simple_ping_against_left_wall()
    {
        c.set_angle(1.5*Math.PI);
        c.set_velocity(1);
        pc.ping_against_the_wall(c);
        assertEquals(Math.PI/2, c.angle(), 0.001);
    }
    @Test
    public void test_collide_simple_left()
    {
        g.setCardsize(5);
        Card temp1 = new Card(g, 0,0,1,2, new Picture(0, "acid3.png"));
        Card temp2 = new Card(g, 0,4,1,2, new Picture(0, "acid3.png"));
        temp1.set_angle(Math.PI/2);
        temp1.set_velocity(1);
        temp2.set_angle(0);
        temp2.set_velocity(0);
        pc.addCard(temp2);
        pc.addCard(temp1);
        pc.collide(temp1, temp2);
        assertEquals(0, temp1.angle(), 0.01);
        assertEquals(0, temp1.velocity(), 0.01);
        assertEquals(1, temp2.velocity(), 0.01);
        //assertEquals(Math.PI/2, temp2.angle(), 0.01);  // <--------------------------------------------------------------------------------------this bugs
    }
    

    /**
     * Test of getIds method, of class PositionController.
     */
    
    
}
