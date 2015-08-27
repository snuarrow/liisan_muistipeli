/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

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
    public void test_click()
    {
        assertNotNull(e.click(g.getClearance(), g.getClearance()));
    }
             

    /**
     * Test of getCards method, of class Engine.
     */
    
    
}
