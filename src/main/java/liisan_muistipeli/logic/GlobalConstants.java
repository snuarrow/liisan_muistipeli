package liisan_muistipeli.logic;

/**
 * Oletusarvoiset globaalit muuttujat tulevat tästä interfacesta
 */

public interface GlobalConstants
{
    int i_horizontalsize = 1024, i_verticalsize = 768, i_cardsize = 120;

    
    // not yet used but will be in near future
    double i_acceleration = 0.005;
    int i_image_displaytime_ms = 200;
    int i_image_zoomtime_ms = 700;
    int i_image_maxsize = 500;
    int i_fps = 60;
    int i_pictures_in_folder = 507;
    double i_default_start_speed = 1.5;
    
    
    
    // pre calculated constants for code readability
//    int timer_interval = 1000/fps;
//    int clearance = cardsize / 3;
//    int slot = cardsize + clearance;
//    int horizontalamount = (horizontalsize-clearance)/(cardsize+clearance);
//    int verticalamount = (verticalsize-clearance)/(cardsize+clearance);
//    int lappuamount = horizontalamount*verticalamount;
//    int y_max_index = verticalsize-cardsize;
//    int x_max_index = horizontalsize-cardsize;
}
