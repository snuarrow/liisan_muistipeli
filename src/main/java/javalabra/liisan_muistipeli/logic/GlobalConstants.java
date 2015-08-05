package javalabra.liisan_muistipeli.logic;


public interface GlobalConstants
{
    int horizontalsize = 1024, verticalsize = 768, cardsize = 80;
    double acceleration = 0.005;
    
    // not yet used but will be in near future
    int image_displaytime = 0;
    int image_zoomtime = 0;
    int image_maxsize = 0;
    
    int clearance = cardsize / 3;
    int slot = cardsize + clearance;
    int horizontalamount = (horizontalsize-clearance)/(cardsize+clearance);
    int verticalamount = (verticalsize-clearance)/(cardsize+clearance);
    int lappuamount = horizontalamount*verticalamount;
    int y_max_index = verticalsize-cardsize;
    int x_max_index = horizontalsize-cardsize;
}
