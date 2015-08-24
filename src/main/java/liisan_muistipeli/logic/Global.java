package liisan_muistipeli.logic;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Globaalien muuttujien luokka, josta luodaan yksi instanssi jaettavaksi muille sitä tarvitsaville luokille. mahdollistaa joidenkin muuttujien muuttamisen.
 */

public final class Global implements GlobalConstants
{
    private int pictures_in_folder, horizontalsize, verticalsize, cardsize, image_displaytime_ms, image_zoomtime_ms, image_maxsize, fps, timer_interval, clearance, slot, horizontalamount, verticalamount, cardamount, y_max_index, x_max_index;
    
    //not used yet but will be in near future
    private double acceleration, default_start_speed;
    
    
    public Global() //tested
    {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        renew();
        refresh();
        
    }
    public void renew()
    {
        this.default_start_speed = i_default_start_speed;
        this.horizontalsize = i_horizontalsize;
        this.verticalsize = i_verticalsize;
        this.cardsize = i_cardsize;
        this.acceleration = i_acceleration;
        this.image_displaytime_ms = i_image_displaytime_ms;
        this.image_zoomtime_ms = i_image_zoomtime_ms;
        this.image_maxsize = i_image_maxsize;
        this.fps = i_fps;
        this.pictures_in_folder = i_pictures_in_folder;
    }
    
    
    public void refresh() //tested
    {
        timer_interval = 1000/fps;
        clearance = cardsize / 3;
        slot = cardsize + clearance;
        horizontalamount = (horizontalsize-clearance)/(cardsize+clearance);
        verticalamount = (verticalsize-clearance)/(cardsize+clearance);
        cardamount = horizontalamount*verticalamount;
        y_max_index = verticalsize-cardsize;
        x_max_index = horizontalsize-cardsize;
        
        if (horizontalamount*verticalamount % 2 != 0) cardamount = (horizontalamount*verticalamount)-1;
        else cardamount = horizontalamount*verticalamount;
    }
    public double getDefaultStartSpeed()
    {
        return default_start_speed;
    }
    
    public int getPicturesInFolder() { return this.pictures_in_folder; }

    public int getHorizontalsize() //tested_default
    {
        return horizontalsize;
    }

    public void setHorizontalsize(int horizontalsize) //tested positive
    {
        // filter negative values plz.
        
        this.horizontalsize = horizontalsize;
        refresh();
    }

    public int getVerticalsize() //tested_default
    {
        return verticalsize;
    }

    public void setVerticalsize(int verticalsize) // tested positive
    {
        // filter negative values plz.
        
        this.verticalsize = verticalsize;
        refresh();
    }

    public int getCardsize() // tested
    {
        return cardsize;
    }

    public void setCardsize(int cardsize) // tested positive
    {
        // filter negative values plz.
        
        this.cardsize = cardsize;
        refresh();
    }

    public int getImage_displaytime_ms() // tested
    {
        return image_displaytime_ms;
    }

    public void setImage_displaytime_ms(int image_displaytime_ms) // tested positive
    {
        // filter negative values plz.
        
        this.image_displaytime_ms = image_displaytime_ms;
        refresh();
    }

    public int getImage_zoomtime_ms() //tested
    {
        return image_zoomtime_ms;
    }

    public void setImage_zoomtime_ms(int image_zoomtime_ms) //tested positive
    {
        // filter negative values plz.
        
        this.image_zoomtime_ms = image_zoomtime_ms;
        refresh();
    }

    public int getImage_maxsize() //tested
    {
        return image_maxsize;
    }

    public void setImage_maxsize(int image_maxsize) //tested positive
    {
        // filter negative values plz.
        
        this.image_maxsize = image_maxsize;
        refresh();
    }

    public int getFps()
    {
        return fps;
    }

    public void setFps(int fps) // tested positive
    {
        // filter negative values plz.
        
        this.fps = fps;
        refresh();
    }

    public int getTimer_interval()
    {
        return timer_interval;
    }

    public int getClearance()
    {
        return clearance;
    }

    public int getSlot()
    {
        return slot;
    }

    public int getHorizontalamount()
    {
        return horizontalamount;
    }

    public int getVerticalamount()
    {
        return verticalamount;
    }

    public int getCardamount()
    {
        return cardamount;
    }

    public int getY_max_index()
    {
        return y_max_index;
    }

    public int getX_max_index()
    {
        return x_max_index;
    }

    public double getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(double acceleration)
    {
        // filter negative values plz.
        
        this.acceleration = acceleration;
        refresh();
    }
    
}
