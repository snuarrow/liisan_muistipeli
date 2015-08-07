/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalabra.liisan_muistipeli.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest
{
    private Card card;
    private Global global;
    
    public CardTest()
    {
        global = new Global();
        card = new Card(global, 1,0,0,1, new Picture(0, "acid3.png"));
    }
    
    @Before
    public void setUp()
    {
        global = new Global();
    }
    
    @After
    public void tearDown()
    {
    }
    public void constructorTest_exception_thrower(int y, int x, int id, int pair_id)
    {
        Throwable e = null;
        try 
        {
            card = new Card(global, y,x,id,pair_id, new Picture(0, "acid3.png"));
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    // CONSTRUCTOR TESTS >>> START >>>
    @Test
    public void testConstructor_x_incorrect()
    {
        constructorTest_exception_thrower(0,-1,0,1);
    }
    @Test
    public void testConstructor_y_incorrect()
    {
        constructorTest_exception_thrower(-1,0,0,1);
    }
    @Test
    public void testConstructor_incorrect_id_and_pair_id_are_same()
    {
        constructorTest_exception_thrower(0,0,0,0);
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
    
    public void setterTest_exception_thrower(int y, int x)
    {
        Throwable e = null;
        try 
        {
            card.set_yx(y, x);
        } catch (Throwable ex)
          {
            e = ex;
          }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
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
        card.set_yx(global.getY_max_index(), 0);
        assertEquals(card.y(), global.getY_max_index());
    }
    @Test
    public void test_int_setY_getY_incorrect_low()
    {
        setterTest_exception_thrower(-1, 0);
    }
    @Test
    public void test_int_setY_getY_incorrect_high()
    {
        setterTest_exception_thrower(global.getY_max_index()+1, 0);
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
        card.set_yx(0, global.getX_max_index());
        assertEquals(card.x(), global.getX_max_index());
    }
    @Test
    public void test_int_setX_incorrect_low()
    {
        setterTest_exception_thrower(0, -1);
    }
    @Test
    public void test_int_setX_incorrect_high()
    {
        setterTest_exception_thrower(0, global.getX_max_index()+1);
    }
    
    //// SETTER_GETTER_X TESTS <<< END <<<
}
