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

public class CardTest implements GlobalConstants
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
    
    
    // CONSTRUCTOR TESTS >>> START >>>
    @Test
    public void testConstructor_x_incorrect()
    {
        Throwable e = null;
        try 
        {
            card = new Card(0,-1,0,1);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    @Test
    public void testConstructor_y_incorrect()
    {
        Throwable e = null;
        try 
        {
            card = new Card(-1,0,0,1);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    @Test
    public void testConstructor_incorrect_id_and_pair_id_are_same()
    {
        Throwable e = null;
        try 
        {
            card = new Card(0,0,0,0);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    @Test
    public void testConstructor_x_correct_values_1()
    {
        assertEquals(0,card.x());
        
    }
    @Test
    public void testConstructor_y_correct_values_1()
    {
        assertEquals(1,card.y());
    }
    @Test
    public void testConstructor_id_correct_values1()
    {
        assertEquals(0, card.id());
    }
    @Test
    public void testConstructor_pair_id_correct_values1()
    {
        assertEquals(1, card.pair_id());
    }
    //// <<< CONSTRUCTOR TESTS END <<<
    
    // SETTER_GETTER_Y TESTS >>> START >>>
    @Test
    public void test_int_setY_getY_correct_low()
    {
        card.set_yx(0, 0);
        assertEquals(card.y(),0);
    }
    @Test
    public void test_int_setY_getY_correct_high()
    {
        card.set_yx(y_max_index, 0);
        assertEquals(card.y(), y_max_index);
    }
    @Test
    public void test_int_setY_getY_incorrect_low()
    {
        Throwable e = null;
        try 
        {
            card.set_yx(-1, 0);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    @Test
    public void test_int_setY_getY_incorrect_high()
    {
        Throwable e = null;
        try 
        {
            card.set_yx(y_max_index +1 , 0);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    //// SETTER_GETTER_Y TESTS <<< END <<<
    
    
    // SETTER_GETTER_X TESTS >>> START >>>
    @Test
    public void test_int_setX_getX_correct_low()
    {
        card.set_yx(0, 0);
        assertEquals(card.x(),0);
    }
    @Test
    public void test_int_setX_getX_correct_high()
    {
        card.set_yx(0, x_max_index);
        assertEquals(card.x(), x_max_index);
    }
    @Test
    public void test_int_setX_incorrect_low()
    {
        Throwable e = null;
        try 
        {
            card.set_yx(0, -1);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    @Test
    public void test_int_setX_incorrect_high()
    {
        Throwable e = null;
        try 
        {
            card.set_yx(x_max_index+1, 0);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    //// SETTER_GETTER_X TESTS <<< END <<<
}
