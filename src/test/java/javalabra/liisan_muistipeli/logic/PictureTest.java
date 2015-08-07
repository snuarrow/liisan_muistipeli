/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalabra.liisan_muistipeli.logic;

import java.awt.Image;
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
public class PictureTest
{
    private Picture picture;
    
    public PictureTest()
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
        picture = new Picture(0, "acid3.png");
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void test_with_acid3_png_192x192_getx()
    {
        assertEquals(192, picture.x());
    }
    @Test
    public void test_with_acid3_png_192x192_gety()
    {
        assertEquals(192, picture.y());
    }
}
