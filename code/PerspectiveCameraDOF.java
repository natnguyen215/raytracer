
/**
 * Write a description of class Camera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PerspectiveCameraDOF extends PerspectiveCamera
{
    double lensSize, focusLength; 
    
    public PerspectiveCameraDOF(Point position, Vector forwardVector, Vector upVector, double fieldOfView, double aspectRatio, double lensRadius, double focusLength){
        super(position, forwardVector, upVector, fieldOfView, aspectRatio); 
        lensSize = lensRadius;
        this.focusLength = focusLength; 
    }
    public Point imagePlanePoint(double u, double v, Point p){
        //modfies equation in order to simulate a lens with a depth of field  
        return (p.add(
        forward.scale(focusLength).add(
        right.scale((2 * (u-0.5) * Math.tan(xFoV))).scale(focusLength).add(
        up.scale(2 * (v-0.5) * Math.tan(yFoV)).scale(focusLength)))));
    }
    public Ray generateRay(double u, double v){
        //creates random point on the lens as source of the Ray
        Point randomLensPoint = location.add(right.scale(Math.random()*lensSize - lensSize/2.0)); 
        randomLensPoint = randomLensPoint.add(up.scale(Math.random()*lensSize - lensSize/2.0));
        
        //follows algorithim from PersepctiveCamera, using the randomLensPoint instead 
        Point p = imagePlanePoint(u,v, randomLensPoint); 
        Vector direction = p.subtract(randomLensPoint); 
        return new Ray(randomLensPoint, direction, Math.random()); 
    }
    public Vector getForwardVector(){return forward;}
}
