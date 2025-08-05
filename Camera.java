
/**
 * Write a description of class Camera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Camera
{
    public abstract Ray generateRay(double u, double v);
    public void move(Vector v){};
    public Vector getForwardVector(){return null;}
    public Vector getRightVector(){return null;}
    public Vector getUpVector() { return null;} 
    public double getAngle(){return 0;}
    public void setAngle(double a){;}
}
