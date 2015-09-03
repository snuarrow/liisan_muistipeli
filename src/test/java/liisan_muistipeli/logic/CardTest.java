package liisan_muistipeli.logic;

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
        card = new Card(global, 1,0,1,2, new Picture(0, "acid3.png"));
    }
    
    @Before
    public void setUp()
    {
        global = new Global();
        //card = new Card(global, 1,0,1,2, new Picture(0, "acid3.png"));
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
    @Test
    public void when_double_values_are_altered_integer_values_change_also()
    {
        Card c = new Card(global, 1,0,1,2, new Picture(0, "acid3.png"));
        c.set_yx(50.1, 60.1);
        assertEquals(50, c.y());
        assertEquals(60, c.x());
    }
    @Test
    public void test_get_picture_works()
    {
        assertNotNull(card.picture());
    }
    
    @Test
    public void test_id_cannot_be_0()
    {
        constructorTest_exception_thrower(0, 0, 0, 1);
    }
    @Test
    public void test_pair_id_cannot_be_0()
    {
        constructorTest_exception_thrower(0, 0, 1, 0);
    }
    
    // CONSTRUCTOR TESTS >>> START >>>
    @Test
    public void testConstructor_x_incorrect()
    {
        constructorTest_exception_thrower(0,-1,1,2);
    }
    @Test
    public void testConstructor_y_incorrect()
    {
        constructorTest_exception_thrower(-1,0,1,2);
    }
    @Test
    public void testConstructor_incorrect_id_and_pair_id_are_same()
    {
        constructorTest_exception_thrower(0,0,1,1);
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
        assertEquals(1, card.id());
    }
    @Test
    public void testConstructor_pair_id_correct_values1()
    {
        assertEquals(2, card.pair_id());
    }
    //// <<< CONSTRUCTOR TESTS END <<<
    
    public void setterTest_exception_thrower_int(int y, int x)
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
    public void setterTest_exception_thrower_float(double y, double x)
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
        assertTrue(!(card.set_yx(-1, 0)));
        //setterTest_exception_thrower_int(-1, 0);
    }
    @Test
    public void test_int_setY_getY_incorrect_high()
    {
        assertTrue(!card.set_yx(global.getY_max_index()+1, 0));
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
        assertTrue(!(card.set_yx(0, -1)));
    }
    @Test
    public void test_int_setX_incorrect_high()
    {
        assertTrue(!(card.set_yx(0, global.getX_max_index()+1)));
    }
    
    //// SETTER_GETTER_X TESTS <<< END <<<
    
    // float_y_TESTS >>> START >>>
    @Test
    public void test_float_setY_getY_correct_low()
    {
        card.set_yx(0.0, 0.0);
        assertEquals(card.fy(), 0, 0.001);
    }
    @Test
    public void test_float_setY_getY_correct_high()
    {
        card.set_yx(global.getY_max_index(), 0);
        assertEquals(card.fy(), global.getY_max_index(), 0.001);
    }
    @Test
    public void test_float_setY_getY_incorrect_low()
    {
        assertTrue(!(card.set_yx(-0.01, 0)));
    }
    @Test
    public void test_float_setY_getY_incorrect_high()
    {
        assertTrue(!(card.set_yx((double)global.getY_max_index()+0.01, 0)));
        //setterTest_exception_thrower_float((double)global.getY_max_index()+0.01, 0);
    }
    //// float_y_TESTS >>> END >>>
    
    // float_y_TESTS >>> START >>>
    @Test
    public void test_float_setX_getX_correct_low()
    {
        card.set_yx(0.0, 0.0);
        assertEquals(card.fx(), 0, 0.001);
    }
    @Test
    public void test_float_setX_getX_correct_high()
    {
        card.set_yx(0,global.getX_max_index());
        assertEquals(card.fx(), global.getX_max_index(), 0.001);
    }
    @Test
    public void test_float_setX_getX_incorrect_low()
    {
        assertTrue(!(card.set_yx(0, -0.01)));
        //setterTest_exception_thrower_float(0, -0.01);
    }
    @Test
    public void test_float_setX_getX_incorrect_high()
    {
        assertTrue(!(card.set_yx((double)global.getX_max_index()+0.01, 0)));
        //setterTest_exception_thrower_float((double)global.getX_max_index()+0.01, 0);
    }
    //// float_y_TESTS >>> END >>>
    
    
    // id tests >>> START >>>
    @Test
    public void test_get_id()
    {
        assertEquals(1, card.id());
    }
    @Test
    public void test_get_pair_id()
    {
        assertEquals(2, card.pair_id());
    }
    @Test
    public void test_id_and_pair_id_cannot_be_same()
    {
        Throwable e = null;
        try {
            card = new Card(global, 1,0,0,0, new Picture(0, "acid3.png"));
        } catch (Throwable ex)
        {
            e = ex;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    // id tests <<< END <<<
    
    // angle tests >>> START >>>
    @Test
    public void test_angle_default_radian()
    {
        assertEquals(card.angle(), 0, 0.01);
    }
    @Test
    public void test_set_angle_between_0_and_2pi()
    {
        for (int i = 0; i < 628; i++) {
            card.set_angle(i/100.0);
            assertEquals(card.angle(),i/100.0,0.0001);
        }
    }
    @Test
    public void test_set_angle_negative()
    {
        card.set_angle(-0.01);
        assertEquals(card.angle(), (2*Math.PI)-0.01, 0.001);
    }
    @Test
    public void test_set_angle_greater_than_2pi()
    {
        card.set_angle((2*Math.PI)+0.01);
        assertEquals(card.angle(), 0.01, 0.001);
    }
    // angle tests <<< END <<<
    
    // velocity tests >>> START >>>
    @Test
    public void test_get_velocity_default()
    {
        assertEquals(card.velocity(), 0, 0.001);
    }
    @Test
    public void test_set_velocity_1_0()
    {
        card.set_velocity(1.0);
        assertEquals(card.velocity(), 1.0, 0.01);
    }
    @Test
    public void test_set_velocity_negative_fail()
    {
        Throwable e = null;
        try
        {
            card.set_velocity(-0.01);
        } catch (Exception ex)
        {
            e = ex;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    
    @Test
    public void test_slow_velocity_0()
    {
        assertEquals(card.velocity(), 0, 0.001);
        assertFalse(card.slow());
        assertEquals(card.velocity(), 0, 0.001);
    }
    @Test
    public void test_slow_velocity_same_than_acceleration()
    {
        
        card.set_velocity(global.getAcceleration());
        assertEquals(card.velocity(), global.getAcceleration(), 0.001);
        assertFalse(card.slow());
        assertEquals(card.velocity(), 0, 0.001);
    }
    @Test
    public void test_slow_velocity_greater_than_acceleration()
    {
        card.set_velocity(global.getAcceleration()*3);
        assertEquals(card.velocity(), global.getAcceleration()*3, 0.001);
        assertTrue(card.slow());
        assertEquals(card.velocity(), global.getAcceleration()*2, 0.001);
    }
    @Test
    public void test_slow_velocity_smaller_than_acceleration()
    {
        card.set_velocity(global.getAcceleration()/2);
        assertEquals(card.velocity(), global.getAcceleration()/2, 0.001);
        assertFalse(card.slow());
        assertEquals(card.velocity(), 0, 0.001);
    }
    // velocity tests <<< END <<<
}
