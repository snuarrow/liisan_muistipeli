package liisan_muistipeli.logic;

/**
 * Kortin kulkusuunnan määrittävä kulman sisältävä luokka, voi antaa mitä 
 * tahansa arvoja, mutta tämä muuttaa ne aina välille 0-2*pi radian.
 */
public class Radian {
    private double value;
    
    public Radian() { set(0); }
    public Radian(double val) { set(val); }
    
    /**
     * vähentää tai lisää kulmaan kokonaisen kierroksen niin kauan kunnes kulma on välillä 0-2pi astetta.
     * @param val 
     */
    public void set(double val)
    {
        while(val >= 2*Math.PI) val -= 2*Math.PI;
        while(val < 0) val += 2*Math.PI;
        value = val;
    }
    public double get() { return value; }
}
