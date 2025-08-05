
/**
 * Write a description of class Intersection here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Intersection
{
    // instance variables - replace the example below with your own
    Point position;
    Vector normal; 
    double distance, imageX, imageY;
    Material surfaceMaterial;

    public Intersection(Point pos, Vector norm, double dist, Material material){ //initalizer for regular intersection
        position = pos;
        normal = norm;
        distance = dist;
        surfaceMaterial = material;
        imageX = 0;
        imageY = 0;
    }

    public Intersection(Point pos, Vector norm, double dist, Material material, double x, double y){ //initializer for intersection with texture
        position = pos;
        normal = norm;
        distance = dist;
        surfaceMaterial = material;
        imageX = x;
        imageY = y;
    }
    public Point getPosition(){ return position;}

    public Vector getNormal() {return normal;}

    public double getDistance() {return distance;}

    public Material getMaterial() {return surfaceMaterial;} 
    public double getImageX() {return imageX;}
    public double getImageY() {return imageY;}
}
