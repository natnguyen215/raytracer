
/**
 * Write a description of class Camera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PerspectiveCamera extends Camera
{
    Point location; 
    Vector forward, up, right; 
    double xFoV, yFoV;
    double angle; 
    public PerspectiveCamera(Point position, Vector forwardVector, Vector upVector, double fieldOfView, double aspectRatio){ 
        location = position; 
        forward = forwardVector.normalize(); //get direction by normalizing 
        up = upVector.normalize(); 
        right = forwardVector.cross(upVector); //right vector cross of forward and up 
        xFoV = Math.toRadians(fieldOfView); //convert FOV to radians 
        yFoV = Math.atan( Math.tan(xFoV) / aspectRatio ); //get yFOV using aspect ratio 
        angle = 0;
    }

    public Point imagePlanePoint(double u, double v){ //generates point on the plane given (u,v) coordinates 
        return (location.add(forward.add(right.scale((2 * (u-0.5) * Math.tan(xFoV))).add(up.scale(2 * (v-0.5) * Math.tan(yFoV))))));
    }

    public Ray generateRay(double u, double v){ //generates viewing ray to plane from camera 
        Point p = imagePlanePoint(u,v);
        Vector direction = p.subtract(location); 
        //return new Ray(location, direction, Math.random()); 
        return new Ray(location, direction, 0); 
    }
    

    public void move(Vector v){ //moves camera 
        location = location.add(v);
    }

    public double getAngle(){
        return angle; 
    }

    public void setAngle(double a){
        //changes the angle that the camera is facing 
        angle = a; 
        double aInRadians = a*Math.PI/180;
        forward = new Vector(Math.sin(aInRadians), 0, Math.cos(aInRadians)*-1); 
        right = forward.cross(up);
    }

    public Vector getForwardVector(){return forward;}

    public Vector getRightVector(){return right;}
    public Vector getUpVector(){return up;}
}
