package javalabra.liisan_muistipeli.logic;

public final class Card implements GlobalConstants
{
    private int x, y;
    private final int id, pair_id;
    private double velocity, fx, fy;
    private final Radian angle;
    private final Picture picture;
    private Global global;
    
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
        
        if (fx < 0 || fy < 0 || fx > horizontalsize-cardsize || fy > verticalsize-cardsize || id == pair_id)
          {
            throw new IndexOutOfBoundsException();
          }
    }
    
    // Getters >>> start >>>
    public int x() { return x; }
    public int y() { return y; }
    public double fx() { return fx; }
    public double fy() { return fy; }
    public int id() { return id; }
    public int pair_id() { return pair_id; }
    public double angle() { return angle.get(); }
    public double velocity() { return velocity; }
    //// Getters <<< end <<<
    
    // Setters >>> start >>>
    public void set_yx(double y, double x)
    {
        this.fx = x;
        this.fy = y;
        this.x = (int)x;
        this.y = (int)y;
        checkBoundaries();
    }
    public void set_velocity(double velocity) { this.velocity = velocity; }
    public void set_angle(double angle) { this.angle.set(angle);}
    
    //// Setters <<< end <<<
    
}
