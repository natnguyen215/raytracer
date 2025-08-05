
/**
 * Write a description of class Ray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ray
{
    // instance variables
    private Point position;
    private Vector direction;
    private double time; //takes a time value for motion blur 
    private double speed; 
   public Ray(Point p, Vector v, double t){ //ray is made of a starting position, direction vector, time that represents ray
       //time represents the location of a surface on a movement vector 
       //i.e if a surface's center is at (0,1,0) and it's movement vector is (0,2,0), the center will be between (0,1,0) and (0,3,0), using the time as the scale for the movement   
       //time will be a random number from 0-1 when created in the camera and the time will be passed around for shadow and reflection rays
       //each ray will have a random time, meaning that the ray will intersect the surface using several different surface center values 
       //this emulates the surface blurring as it traverses its movement vector 
       time = t;  
       position = p;
       direction = v.normalize(); //direction vector must be normal 
    }
    public Ray(Point p, Vector v){ //ray is made of a starting position and direction vector 
       time = 0; 
       position = p;
       direction = v.normalize(); //direction vector must be normal 
    }
    public Point getPosition(){ 
        return position;
    }
    public Vector getDirection(){ 
        return direction;
    }
    public Point evaluate(double dist){ //finds the point at a given distance on the ray 
        return(position.add(direction.scale(dist)));
    }
    public double getTime(){
        return time;
    }
    public double getSpeed(){
        return speed;
    }
    public Vector getGravity(BlackHole blackHole){
        Vector distanceVector = new Vector(-this.position.getX() + blackHole.getPosition().getX(), -this.position.getY() + blackHole.getPosition().getY(), -this.position.getZ() + blackHole.getPosition().getZ());
        double distance = distanceVector.length();
        double G = 6.6743 * Math.pow(10, -11)/Math.pow(300000000,3);
        double force = G*blackHole.getMass()/Math.pow(distance, 2);
        return new Vector(force*distanceVector.normalize().getDX(), force*distanceVector.normalize().getDY(), force*distanceVector.normalize().getDZ()) ;
    }
    public void applyGravity(BlackHole blackHole){
        
        direction = direction.add(getGravity(blackHole));
    }
}
