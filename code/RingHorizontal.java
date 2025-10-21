/**
 * Represents a flat 2D torus (like Saturn's rings) in 3D space.
 * This can also represent a disc by setting the inner radius to 0 when constructing.
 * 
 * @author Ben Farrar
 * @version 2022.05.11
 */
public class RingHorizontal extends Surface {
    private double r1; //outer radius
    private double r2; //inner radius (of the hole in the middle)
    private Vector normal;
    private Material mat;
    private Point center; 
    //Minimum distance for a valid collision. This prevents the disc's rays from colliding with itself.
    public static double EPSILON = 1e-6;
    
    public RingHorizontal(Point position, double outerRadius, double innerRadius, Vector norm, Material m){
        r1 = outerRadius;
        r2 = innerRadius;
        normal = norm;
        mat = m;
        center = position;
    }
    
    public Intersection intersect(Ray ray){
        double d = new Point(0,0,0).subtract(center).dot(normal);
        Point rayOrigin = ray.getPosition();
        Vector rayOriginVec = new Vector(rayOrigin.getX(), rayOrigin.getY(), rayOrigin.getZ());
        double distance = -(rayOriginVec.dot(normal) + d)/(ray.getDirection().dot(normal));
        if (distance>EPSILON){
            Point inter = ray.evaluate(distance);
            double length = center.subtract(inter).length();
            if (length < r1 && length > r2){
                if(normal.dot(ray.getDirection()) > 0){
                    //System.out.println(inter.getX());
                    //System.out.println("hi");
                    //System.out.println(center.getX());
                    return new Intersection(inter, normal.scale(-1), distance, mat, -(inter.getX()-center.getX() - (r1))/r1/2, -(inter.getZ()-center.getZ() - (r1))/r1/2);
                }
                else {
                    //System.out.println(inter.getX());
                    //System.out.println("hi");
                    //System.out.println(center.getX());
                    return new Intersection(inter, normal, distance, mat, -(inter.getX()-center.getX() - (r1))/r1/2, -(inter.getZ()-center.getZ() - (r1))/r1/2);
                }
            }
        }
        return null;
    }
    
}
