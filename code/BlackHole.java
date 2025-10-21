
/**
 * Write a description of class BlackHole here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackHole extends Sphere
{
    // instance variables - replace the example below with your own
    double m;

    /**
     * Constructor for objects of class BlackHole
     */
    public BlackHole(Point position, double radius, Material m)
    {
        // initialise instance variables
     
        super(position, radius, m, new Vector(0,0,0) );
        this.m = Math.pow(10,36.5);
        
    }

    public double getMass(){
        return m;
    }
}
