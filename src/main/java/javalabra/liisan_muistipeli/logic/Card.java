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
    
    public Card(int x, int y, int id, int pair_id)
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
    public int x() { return x; }
    public int y() { return y; }
    
}
