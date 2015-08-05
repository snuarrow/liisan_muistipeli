package javalabra.liisan_muistipeli.logic;


public interface GlobalConstants
{
    int horizontalsize = 1024, verticalsize = 768, cardsize = 80;

    
    // not yet used but will be in near future
    double acceleration = 0.005;
    int image_displaytime_ms = 3000;
    int image_zoomtime_ms = 500;
    int image_maxsize = 0;
    int fps = 60;
    
    
    
    // pre calculated constants for code readability
    int timer_interval = 1000/fps;
    int clearance = cardsize / 3;
    int slot = cardsize + clearance;
    int horizontalamount = (horizontalsize-clearance)/(cardsize+clearance);
    int verticalamount = (verticalsize-clearance)/(cardsize+clearance);
    int lappuamount = horizontalamount*verticalamount;
    int y_max_index = verticalsize-cardsize;
    int x_max_index = horizontalsize-cardsize;
}
