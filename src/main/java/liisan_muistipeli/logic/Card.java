package liisan_muistipeli.logic;

/**
 * Luokka yhden muistipelikortin sijainnin ja kuvan hallitsemiseen.
 */
public final class Card
{
    private int x, y;
    private final int id, pair_id;
    private double velocity, fx, fy;
    private final Radian angle;
    private final Picture picture;
    private final Global global;
    
    /**
     * Konstruktori, Luo uuden kortin ja validoi invariantit
     * @param global globaalien muuttujien luokka.
     * @param y y-koordinaatti.
     * @param x x-koordinaatti.
     * @param id tämän kortin id.
     * @param pair_id parin id.
     * @param picture korttiin liittyvä kuva.
     */
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
        if (!checkBoundaries()) throw new IndexOutOfBoundsException();
    }
    
    /**
     * Invarianttien tarkistus
     * @return true/false riippuen oliko kaikki ok.
     */
    
    public boolean checkBoundaries()
    {
        // tänne tarvii jonkun validaattorin kuvalle.
        
        if (fx < 0 || fy < 0 || fx > global.getHorizontalsize()-global.getCardsize() || fy > global.getVerticalsize()-global.getCardsize() || id == pair_id || id < 1 || pair_id < 1)
          {
            return false;
          }
        return true;
    }
    
    /**
     * Hidastaa kortin nopeutta
     * @return true mikäli nopeutta voitiin hidastaa (oli suurempi kuin nolla), muulloin false.
     */
    
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
    public Picture picture() { return picture; }
    //// Getters <<< end <<<
    
    // Setters >>> start >>>
    
    /**
     * Muuttaa kortin koordinaatteja
     * @param y koordinaatti
     * @param x koordinaatti
     * @return true/false riippuen onnistuiko operaatio.
     */
    public boolean set_yx(double y, double x) //tested
    {
        if (y < 0 || x < 0 || y > global.getVerticalsize()-global.getCardsize() || x > global.getHorizontalsize()-global.getCardsize())
        {
            checkBoundaries();
            return false;
        } else
        {
            this.fx = x;
            this.fy = y;
            this.x = (int)x;
            this.y = (int)y;
        }
        
        
        checkBoundaries();
        return true;
    }
    public void set_velocity(double velocity) //tested
    {
        if (velocity >= 0) this.velocity = velocity;
        else throw new IndexOutOfBoundsException();
    }
    public void set_angle(double angle) { this.angle.set(angle);} //tested
    
    //// Setters <<< end <<<
    
}
