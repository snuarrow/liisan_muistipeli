package javalabra.liisan_muistipeli.logic;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class Global
{
    private int horizontalsize, verticalsize, cardsize, image_displaytime_ms, image_zoomtime_ms, image_maxsize, fps, timer_interval, clearance, slot, horizontalamount, verticalamount, cardamount, y_max_index, x_max_index;
    
    //not used yet but will be in near future
    double acceleration;
    
    
    public Global()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.horizontalsize = screenSize.width-100;
        this.verticalsize = screenSize.height-100;
        this.cardsize = 80;
        this.acceleration = 0.005;
        this.image_displaytime_ms = 3000;
        this.image_zoomtime_ms = 500;
        this.image_maxsize = 0;
        this.fps = 60;
        
        refresh();
        
    }
    public void refresh()
    {
        timer_interval = 1000/fps;
        clearance = cardsize / 3;
        slot = cardsize + clearance;
        horizontalamount = (horizontalsize-clearance)/(cardsize+clearance);
        verticalamount = (verticalsize-clearance)/(cardsize+clearance);
        cardamount = horizontalamount*verticalamount;
        y_max_index = verticalsize-cardsize;
        x_max_index = horizontalsize-cardsize;
    }

    public int getHorizontalsize()
    {
        return horizontalsize;
    }

    public void setHorizontalsize(int horizontalsize)
    {
        this.horizontalsize = horizontalsize;
    }

    public int getVerticalsize()
    {
        return verticalsize;
    }

    public void setVerticalsize(int verticalsize)
    {
        this.verticalsize = verticalsize;
    }

    public int getCardsize()
    {
        return cardsize;
    }

    public void setCardsize(int cardsize)
    {
        this.cardsize = cardsize;
    }

    public int getImage_displaytime_ms()
    {
        return image_displaytime_ms;
    }

    public void setImage_displaytime_ms(int image_displaytime_ms)
    {
        this.image_displaytime_ms = image_displaytime_ms;
    }

    public int getImage_zoomtime_ms()
    {
        return image_zoomtime_ms;
    }

    public void setImage_zoomtime_ms(int image_zoomtime_ms)
    {
        this.image_zoomtime_ms = image_zoomtime_ms;
    }

    public int getImage_maxsize()
    {
        return image_maxsize;
    }

    public void setImage_maxsize(int image_maxsize)
    {
        this.image_maxsize = image_maxsize;
    }

    public int getFps()
    {
        return fps;
    }

    public void setFps(int fps)
    {
        this.fps = fps;
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
        this.acceleration = acceleration;
    }
    
}
