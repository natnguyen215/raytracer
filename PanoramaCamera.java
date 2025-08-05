
/**
 * Write a description of class Panorama here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PanoramaCamera extends Camera
{
    Point location; 
    Vector forward, up, right; 
    double xFoV, yFoV;
    
    public PanoramaCamera(Point position, Vector forwardVector, Vector upVector, double fieldOfView, double aspectRatio){
        location = position;
        forward = forwardVector.normalize();
        up = upVector.normalize(); 
        right = forwardVector.cross(upVector);
        xFoV = Math.toRadians(fieldOfView);
        yFoV = Math.atan(xFoV/aspectRatio); //yFoV equation altered from PerspectiveCamera equation

    }
    public Point imagePlanePoint(double u, double v){
        //changes imagePlanePoint equation in order to simulate a panorma cam 
        return (location.add(forward.scale(Math.cos((u-0.5)*2*xFoV)).add(right.scale(Math.sin((u-0.5)*2*xFoV)).add(up.scale(2 * (v-0.5) * Math.tan(yFoV))))));
    }
    public Ray generateRay(double u, double v){
        Point p = imagePlanePoint(u,v);
        Vector direction = p.subtract(location); 
        return new Ray(location, direction, Math.random()); 
    }
}
