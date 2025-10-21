
/**
 * Write a description of class IsometricCamera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IsometricCamera extends Camera
{
    //isometric camera views the scene by creating new rays for each x,y that look at the plane directly forward instead of from a single location at an different angles
    Point location; 
    Vector forward, up, right; 
    double xFoV, yFoV;

    public IsometricCamera(Point position, Vector forwardVector, Vector upVector, double fieldOfView, double aspectRatio){
        location = position;
        forward = forwardVector.normalize();
        up = upVector.normalize(); 
        right = upVector.cross(forwardVector);
        xFoV = fieldOfView; //uses FoV input directly in order to represnt the movement of the camera
        yFoV = (xFoV/aspectRatio);
    }

    public Point imagePlanePoint(double u, double v){ 
        //because isometric camera travels distance instead of degrees, remove tangent from calculations
        return (location.add(forward.add(right.scale((2 * (u-0.5) * (xFoV))).add(up.scale(2 * (v-0.5) * (yFoV))))));
    }

    public Ray generateRay(double u, double v){ 
        //generate ray by using forward vector and moving the Point of the ray around as the imagePlanePoint moves around  
        Vector negForward = forward.scale(-1);
        Point ipp = imagePlanePoint(u,v);
        Point p = ipp.add(negForward);
        return new Ray(p, forward, Math.random()); 
    }
}
