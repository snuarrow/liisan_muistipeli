/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

import liisan_muistipeli.logic.GlobalConstants;
import liisan_muistipeli.logic.Global;
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
public class GlobalTest implements GlobalConstants
{
    private Global global;
    
    public GlobalTest()
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
        global = new Global();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of refresh method, of class Global.
     */
    @Test
    public void test_set_get_difficulty()
    {
        global.setDifficulty(0);
        assertEquals(0, global.getDifficulty());
    }
    @Test
    public void test_set_get_resolution_index()
    {
        global.setResolution(0);
        assertEquals(0, global.getResolution());
    }
    
    
    @Test
    public void test_Constructor()
    {
        assertEquals(global.getAcceleration(), i_acceleration, 0.001);
        assertEquals(global.getCardsize(), i_cardsize);
        assertEquals(global.getFps(), i_fps);
        assertEquals(global.getHorizontalsize(), i_horizontalsize);
        assertEquals(global.getImage_displaytime_ms(), i_image_displaytime_ms);
        assertEquals(global.getImage_maxsize(), i_image_maxsize);
        assertEquals(global.getImage_zoomtime_ms(), i_image_zoomtime_ms);
        assertEquals(global.getVerticalsize(), i_verticalsize);
        
    }
    @Test
    public void test_refresh_after_constructor()
    {
        assertEquals(global.getClearance(), i_cardsize / 3);
        assertEquals(global.getHorizontalamount(), (i_horizontalsize-global.getClearance())/(i_cardsize+global.getClearance()));
        assertEquals(global.getSlot(), i_cardsize + global.getClearance());
        assertEquals(global.getTimer_interval(), 1000/i_fps);
        assertEquals(global.getVerticalamount(), (i_verticalsize-global.getClearance())/(i_cardsize+global.getClearance()));
        assertEquals(global.getX_max_index(), i_horizontalsize-i_cardsize);
        assertEquals(global.getY_max_index(), i_verticalsize-i_cardsize);
    }
    @Test
    public void test_get_horizontalsize_default()
    {
        assertEquals(global.getHorizontalsize(), i_horizontalsize);
    }
    @Test
    public void test_set_horizontalsize_1000()
    {
        global.setHorizontalsize(1000);
        assertEquals(global.getHorizontalsize(), 1000);
    }
    @Test
    public void test_set_horizontalsize_negative_fail()
    {
        // NOT IMPLEMENTED YET
        assertTrue(true);
    }
    @Test
    public void test_get_verticalsize_default()
    {
        assertEquals(global.getVerticalsize(), i_verticalsize);
    }
    @Test
    public void test_set_verticalsize_1000()
    {
        global.setVerticalsize(1000);
        assertEquals(global.getVerticalsize(), 1000);
    }
    @Test
    public void test_set_verticalsize_negative_fail()
    {
        // NOT IMPLEMENTED YET
        assertTrue(true);
    }
    @Test
    public void test_get_cardsize()
    {
        assertEquals(global.getCardsize(), i_cardsize);
    }
    @Test
    public void test_set_cardsize_112()
    {
        global.setCardsize(112);
        assertEquals(global.getCardsize(), 112);
    }
    @Test
    public void test_set_cardsize_negative_fail()
    {
        // NOT IMPLEMENTED YET
        assertTrue(true);
    }
    @Test
    public void test_get_image_displaytime_ms()
    {
        assertEquals(global.getImage_displaytime_ms(), i_image_displaytime_ms);
    }
    @Test
    public void test_set_image_displaytime_3000()
    {
        global.setImage_displaytime_ms(3000);
        assertEquals(global.getImage_displaytime_ms(), 3000);
    }
    @Test
    public void test_set_image_displaytime_negative_fail()
    {
        // NOT IMPLEMENTED YET
        assertTrue(true);
    }
    @Test
    public void test_get_image_zoomtime_ms()
    {
        assertEquals(global.getImage_zoomtime_ms(),  i_image_zoomtime_ms);
    }
    @Test
    public void test_set_image_zoomtime_ms_500()
    {
        global.setImage_zoomtime_ms(500);
        assertEquals(global.getImage_zoomtime_ms(), 500);
    }
    @Test
    public void test_set_image_zoomtime_ms_negative_fail()
    {
        // NOT IMPLEMENTED YET
        assertTrue(true);
    }
    @Test
    public void test_get_image_max_size()
    {
        assertEquals(global.getImage_maxsize(), i_image_maxsize);
    }
    @Test
    public void test_set_image_max_size_112()
    {
        global.setImage_maxsize(112);
        assertEquals(global.getImage_maxsize(), 112);
    }
    @Test
    public void test_set_image_max_size_negative_fail()
    {
        // NOT IMPLEMENTED YET
        assertTrue(true);
    }
    @Test
    public void test_set_fps()
    {
        global.setFps(30);
        assertEquals(global.getFps(),30);
        assertEquals(global.getFps(),1000/global.getTimer_interval());
    }
    @Test
    public void test_get_card_amount()
    {
        global.setHorizontalsize(130);
        global.setVerticalsize(130);
        global.setCardsize(30);
        //assertEquals(345, global.getVerticalamount());
        //assertEquals(456, global.getVerticalamount());
        assertEquals(8, global.getCardamount());
        
    }
    @Test
    public void test_get_pictures_in_folder()
    {
        assertEquals(global.getPicturesInFolder(), i_pictures_in_folder);
    }
    @Test
    public void test_set_acceleration()
    {
        global.setAcceleration(1.23456789);
        assertEquals(1.23456789, global.getAcceleration(), 0.000000001);
    }
}
