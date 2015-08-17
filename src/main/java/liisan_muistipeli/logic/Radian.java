package liisan_muistipeli.logic;

//class of angle in radian, has only values from 0 to 2*pi.
public class Radian {
    private double value;
    
    public Radian() { set(0); }
    public Radian(double val) { set(val); }
    
    public void set(double val)
    {
        while(val >= 2*Math.PI) val -= 2*Math.PI;
        while(val < 0) val += 2*Math.PI;
        value = val;
    }
    public double get() { return value; }
}
