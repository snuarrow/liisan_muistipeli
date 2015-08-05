/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalabra.liisan_muistipeli.logic;

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
public class CardTest
{
    private Card card;
    
    public CardTest()
    {
        card = new Card(1,0,0,1);
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    @Test
    public void testConstructor_incorrect_values_1()
    {
        Throwable e = null;
        try 
        {
            card = new Card(-1,-1,0,0);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof RuntimeException);
    }
    @Test
    public void testConstructor_x_correct_values_1()
    {
        assertEquals(1,card.x());
        
    }
    @Test
    public void testConstructor_y_correct_values_1()
    {
        assertEquals(0,card.y());
    }

    /**
     * Test of checkBoundaries method, of class Card.
     */
    
    
}
