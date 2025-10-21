
/**
 * Write a description of class farrermat here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class farrermat extends Material
{
    // This represents a flat color 
    private Color c;

    /**
     * Constructor for objects of class farrermat
     */
    public farrermat(Color c)
    {
        this.c = c; 
    }
    
    public Color computeLighting(Intersection i, Ray viewingRay, Light li){
        return c; 
    }
}
