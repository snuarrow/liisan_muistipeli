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
    public void test_collide_simple_left()
    {
        g.setCardsize(5);
        Card moving = new Card(g, 0,0,1,2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 0,4,1,2, new Picture(0, "acid3.png"));
        moving.set_angle(Math.PI/2);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin left");
        pc.collide(moving, still);
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(Math.PI/2, still.angle(), 0.01);  
        System.out.println("end");
    }
    @Test
    public void test_collide_simple_right()
    {
        g.setCardsize(5);
        Card moving = new Card(g, 0, 5, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 0, 1, 1, 2, new Picture(0, "acid3.png")); 
        
        // m.x > s.x, m.y = s.y
        
        moving.set_angle((3/2)*Math.PI);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin right");
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(1.5*Math.PI, still.angle(), 0.01); 
    }
    
    @Test public void test_collide_simple_down()
    {
        g.setCardsize(5);
        Card moving = new Card(g, 0, 0, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 4, 0, 1, 2, new Picture(0, "acid3.png")); 
        
        // m.x = s.x, m.y < s.y
        
        moving.set_angle(0);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin down");
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(0, still.angle(), 0.01);
    }
    @Test
    public void test_collide_simple_up()
    {
        g.setCardsize(5);
        Card moving = new Card(g, 5, 0, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 1, 0, 1, 2, new Picture(0, "acid3.png")); 
        
        // m.x = s.x, m.y > s.y
        
        moving.set_angle(Math.PI);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin up");
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(Math.PI, still.angle(), 0.01);
    }
    @Test
    public void test_collide_south_east()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card moving = new Card(g, 0, 0, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 4, 4, 2, 1, new Picture(0, "acid3.png")); 
        
        // m.x < s.x, m.y < s.y
        
        moving.set_angle(0.25*Math.PI);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin south east");
        pc.getHitBoxBoard().print();
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(0.25*Math.PI, still.angle(), 0.01);
    }
    @Test
    public void test_collide_north_west()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card moving = new Card(g, 5, 5, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 1, 1, 2, 1, new Picture(0, "acid3.png")); 
        
        
        
        moving.set_angle(1.25*Math.PI);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin north west");
        pc.getHitBoxBoard().print();
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(1.25*Math.PI, still.angle(), 0.01);
    }
    @Test
    public void test_collide_north_east()
    {
        g.setHorizontalsize(11);
        g.setVerticalsize(11);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card moving = new Card(g, 5, 0, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 1, 4, 2, 1, new Picture(0, "acid3.png")); 
        
        
        
        moving.set_angle(0.75*Math.PI);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin north east");
        pc.getHitBoxBoard().print();
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(0.75*Math.PI, still.angle(), 0.01);
    }
    @Test
    public void test_collide_south_west()
    {
        g.setHorizontalsize(11);
        g.setVerticalsize(11);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card moving = new Card(g, 1, 5, 1, 2, new Picture(0, "acid3.png"));
        Card still = new Card(g, 5, 1, 2, 1, new Picture(0, "acid3.png")); 
        
        
        
        moving.set_angle(1.75*Math.PI);
        moving.set_velocity(1);
        still.set_angle(0);
        still.set_velocity(0);
        pc.addCard(still);
        pc.addCard(moving);
        System.out.println("begin south west");
        pc.getHitBoxBoard().print();
        pc.collide(moving, still);
        System.out.println("end");
        assertEquals(0, moving.angle(), 0.01);
        assertEquals(0, moving.velocity(), 0.01);
        assertEquals(1, still.velocity(), 0.01);
        assertEquals(1.75*Math.PI, still.angle(), 0.01);
    }
    @Test
    public void test_ping_top_up()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card card = new Card(g, 0, 1, 1, 2, new Picture(0, "acid3.png"));
        card.set_velocity(1);
        card.set_angle(Math.PI);
        pc.addCard(card);
        
        pc.move_card(1);
        
        assertEquals(0, card.angle(), 0.01);
    }
    @Test
    public void test_ping_left_left()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card card = new Card(g, 1, 0, 1, 2, new Picture(0, "acid3.png"));
        card.set_velocity(1);
        card.set_angle(1.5*Math.PI);
        pc.addCard(card);
        
        pc.move_card(1);
        
        assertEquals(0.5*Math.PI, card.angle(), 0.01);
    }
    @Test
    public void test_ping_down_down()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card card = new Card(g, 5, 1, 1, 2, new Picture(0, "acid3.png"));
        card.set_velocity(1);
        card.set_angle(0);
        pc.addCard(card);
        
        //System.out.println("test_ping_down_down");
        //pc.getHitBoxBoard().print();
        
        pc.move_card(1);
        
        assertEquals(Math.PI, card.angle(), 0.01);
    }
    @Test
    public void test_ping_right_right()
    {
        g.setHorizontalsize(10);
        g.setVerticalsize(10);
        g.setCardsize(5);
        pc = new PositionController(g);
        Card card = new Card(g, 1, 5, 1, 2, new Picture(0, "acid3.png"));
        card.set_velocity(1);
        card.set_angle(0.5*Math.PI);
        pc.addCard(card);
        
        System.out.println("test_ping_right_right");
        pc.getHitBoxBoard().print();
        
        pc.move_card(1);
        
        assertEquals(1.5*Math.PI, card.angle(), 0.01);
    }
    
    
    

    /**
     * Test of getIds method, of class PositionController.
     */
    
    
}
