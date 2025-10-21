/**
 * Represents a sphere in 3D space.
 * 
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class Sphere extends Surface {
    private Point center;
    private double r;
    private Material mat;
    private Vector movement, up, forward, right; 
    
    
    //Minimum distance for a valid collision. This prevents the sphere's rays from colliding with itself.
    public static double EPSILON = 1e-6;

    public Sphere(Point position, double radius, Material m, Vector upVect, Vector forwardVect){ //constructor for textures
        center = position;
        r = radius;
        mat = m;
        movement = new Vector(0,0,0); 
        
        //gives vectors representing the directions that the sphere face 
        up = upVect.normalize();
        forward = forwardVect.normalize();
        right = forwardVect.cross(upVect);
    } 
    
    public Sphere(Point position, double radius, Material m, Vector move){ //constructor for motion blur 
        center = position;
        r = radius;
        mat = m;
        movement = move; //gives vector to represent the movement of the sphere  
        up = new Vector(0,0,0);
        right = new Vector(0,0,0);
        forward = new Vector(0,0,0);
    }

    public Sphere(Point position, double radius, Material m){ //constructor for regular sphere
        center = position;
        r = radius;
        mat = m;
        movement = new Vector(0,0,0); 
        up = new Vector(0,0,0);
        right = new Vector(0,0,0);
        forward = new Vector(0,0,0);
    }
    public Intersection intersect(Ray ray){
        //System.out.println(ray);
        Point tempCenter = center.add(movement.scale(ray.getTime())); //moves the sphere to a random point on the movement vector, using this as the center for calculations
        
        Vector diff = ray.getPosition().subtract(tempCenter);
        double a = ray.getDirection().dot(ray.getDirection());
        double b = (ray.getDirection().scale(2)).dot(diff);
        double c = diff.dot(diff)-(r*r);
        // determinant
        double d = (b*b)-4*a*c;
        if (d>=0){
            //Confirmed collision
            double distance = ((-b)-Math.sqrt(d))/(2*a);
            if (distance < -EPSILON){
                //Specific for being inside of a sphere (first solution would be behind you)
                distance = ((-b)+Math.sqrt(d))/(2*a);
            }
            if (distance > EPSILON){
                Point collision = ray.evaluate(distance);
                Vector normal = collision.subtract(tempCenter).normalize();
                
                //If this is a collision with the inside of the sphere, make sure the normal points inward as well
                if(normal.dot(ray.getDirection()) > 0){
                    normal = normal.scale(-1);
                }
                
                //use up, right, and forward facing vectors to calculate the intersection's imageX and imageY at different intersections 
                //imageX and imageY represent's the sphere's "longitude" and "latitutde" on a scale 0-1 
                double X; 
                double Y = 1 - (Math.acos( normal.dot(up) )/ Math.PI);
                Vector E = normal.subtract(up.scale( normal.dot(up))).normalize();
                if(E.dot(right) >= 0){
                    X = 0.5 - Math.acos(E.dot(forward))/2/Math.PI;
                }
                else{
                    X = 0.5 + Math.acos(E.dot(forward))/2/Math.PI;
                }
                
                
                return new Intersection(collision, normal, distance, mat, X, Y);
            }
        }
        return null;
    }
    public Point getPosition(){
        return center;
    }
}