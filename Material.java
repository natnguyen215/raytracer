
/**
 * Write a description of class Material here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
abstract class Material
{
public abstract Color computeLighting(Intersection i, Ray viewingRay, Light li);
public double getReflectiveness(){return 0;}
}
