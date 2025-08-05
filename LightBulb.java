
/**
 * Write a description of class LightBulb here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LightBulb extends Light
{
    Point position, temporaryPoint;
    Color intensity;
    
    //creates a lightbulb that uses a different temporaryPoint around the "center" position for every calculation, instead of position, in order to create soft shadows
    public LightBulb(Color c, Point location){ 
        intensity = c;
        position = location; // "center" Point of the light 
        temporaryPoint = location; //Point used for calculations 
    }
    public Vector computeLightDirection(Point surfacePoint){ 
        Vector returnVect = (this.temporaryPoint.subtract(surfacePoint));
        return returnVect.normalize(); 
    }
    public Color computeLightColor(Point surfacePoint){
        return intensity;
    }
    public double computeLightDistance(Point surfacePoint){
        Vector returnVect = (this.temporaryPoint.subtract(surfacePoint));
        return returnVect.length();
    }
    public void changePosition(){
        //changes temporaryPosition
        temporaryPoint = new Point(position.getX()- 0.05 + Math.random()/10, position.getY()- 0.05 + Math.random()/10, position.getZ()- 0.05 + Math.random()/10); 
    }
}
