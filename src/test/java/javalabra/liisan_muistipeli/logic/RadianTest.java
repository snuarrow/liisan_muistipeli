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

public class RadianTest
{
    Radian angle;
    
    public RadianTest()
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
        angle = new Radian();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of set method, of class Radian.
     */
    @Test
    public void test1() {
        Radian angle = new Radian();
        assertEquals(angle.get(), 0, 0.00001);
    }
    @Test
    public void test2()
    {
        angle.set(2*Math.PI);
        assertEquals(0, angle.get(), 0.000001);
    }
    @Test
    public void test3()
    {
        angle.set(-Math.PI/2);
        assertEquals(1.5*Math.PI, angle.get(), 0.000001);
    }
    @Test
    public void test4()
    {
        angle.set(3*Math.PI);
        assertEquals(Math.PI, angle.get(), 0.000001);
    }
    @Test
    public void test5()
    {
        angle.set(-3*Math.PI);
        assertEquals(Math.PI, angle.get(), 0.000001);
    }
    @Test
    public void testRadian_over0_and_under2pi()
    {
        for (int i = 0; i < 314; i++)
        {
            Radian radian = new Radian(i/100.0);
            assertEquals(radian.get(),i/100.0, 0.001);
        }
    }
    @Test
    public void testRadian_under0()
    {
        for (int i = 0; i < 314; i++)
        {
            Radian radian = new Radian(i/100.0-2*Math.PI);
            assertEquals(radian.get(),i/100.0, 0.001);
        }
    }
    @Test
    public void testRadian_over_2pi()
    {
        for (int i = 0; i < 314; i++)
        {
            Radian radian = new Radian(i/100.0+2*Math.PI);
            assertEquals(radian.get(),i/100.0, 0.001);
        }
    }

    /**
     * Test of get method, of class Radian.
     */
    
    
}
