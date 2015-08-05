/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalabra.liisan_muistipeli.logic;

/**
 *
 * @author hexvaara
 */
public final class Card implements GlobalConstants
{
    private int x, y;
    private final int id, pair_id;
    private double velocity, fx, fy;
    private final Radian angle;
    
    public Card(int y, int x, int id, int pair_id)
    {
        this.x = x;
        this.y = y;
        this.fx = x;
        this.fy = y;
        this.id = id;
        this.pair_id = pair_id;
        velocity = 0;
        angle = new Radian();
        if(!checkBoundaries()) throw new RuntimeException();
    }
    public boolean checkBoundaries()
    {
        return !(fx < 0 || fy < 0 || fx > horizontalsize-cardsize || fy > verticalsize-cardsize);
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
    public boolean set_yx(double y, double x)
    {
        this.fx = x;
        this.fy = y;
        this.x = (int)x;
        this.y = (int)y;
        return checkBoundaries();
    }
    public void set_velocity(double velocity) { this.velocity = velocity; }
    public void set_angle(double angle) { this.angle.set(angle);}
    
    //// Setters <<< end <<<
    
}
