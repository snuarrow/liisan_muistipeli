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

    /**
     * Test of getIds method, of class PositionController.
     */
    
    
}
