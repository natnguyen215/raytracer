
/**
 * Write a description of class PointLight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PointLight extends Light
{
    Point position;
    Color intensity;
    
    public PointLight(Color c, Point location){
        intensity = c;
        position = location;
    }
    public Vector computeLightDirection(Point surfacePoint){ //computes direction from light to a point
        Vector returnVect = (this.position.subtract(surfacePoint));
        return returnVect.normalize(); 
    }
    public Color computeLightColor(Point surfacePoint){ //gets color of the light 
        return intensity;
    }
    public double computeLightDistance(Point surfacePoint){ //gets distance from light to a point 
        Vector returnVect = (this.position.subtract(surfacePoint));
        return returnVect.length();
    }
    
}
