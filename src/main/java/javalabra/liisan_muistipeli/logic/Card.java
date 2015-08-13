package javalabra.liisan_muistipeli.logic;

public final class Card
{
    private int x, y;
    private final int id, pair_id;
    private double velocity, fx, fy;
    private final Radian angle;
    private final Picture picture;
    private final Global global;
    
    public Card(Global global, int y, int x, int id, int pair_id, Picture picture)
    {
        this.global = global;
        this.x = x;
        this.y = y;
        this.fx = x;
        this.fy = y;
        this.id = id;
        this.pair_id = pair_id;
        velocity = 0;
        angle = new Radian();
        this.picture = picture;
        checkBoundaries();
    }
    public void checkBoundaries()
    {
        // tänne tarvii jonkun validaattorin kuvalle.
        
        if (fx < 0 || fy < 0 || fx > global.getHorizontalsize()-global.getCardsize() || fy > global.getVerticalsize()-global.getCardsize() || id == pair_id)
          {
            throw new IndexOutOfBoundsException();
          }
    }
    public boolean slow() //tested
    {
        if (velocity > global.getAcceleration()) { velocity -= global.getAcceleration(); return true; }
        else { velocity = 0; return false; }
    }
    
    // Getters >>> start >>>
    public int x() { return x; }  //tested
    public int y() { return y; }  //tested
    public double fx() { return fx; } //tested
    public double fy() { return fy; } //tested
    public int id() { return id; } //tested
    public int pair_id() { return pair_id; } //tested
    public double angle() { return angle.get(); } //tested
    public double velocity() { return velocity; }
    //// Getters <<< end <<<
    
    // Setters >>> start >>>
    public void set_yx(double y, double x) //tested
    {
        this.fx = x;
        this.fy = y;
        this.x = (int)x;
        this.y = (int)y;
        checkBoundaries();
    }
    public void set_velocity(double velocity) //tested
    {
        if (velocity >= 0) this.velocity = velocity;
        else throw new IndexOutOfBoundsException();
    }
    public void set_angle(double angle) { this.angle.set(angle);} //tested
    
    //// Setters <<< end <<<
    
}
