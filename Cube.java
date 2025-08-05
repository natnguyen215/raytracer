
/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cube extends Surface 
{
    private double radius;
    private Triangle t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
    private Triangle[] triangles;
    /**
     * Constructor for objects of class Square
     */
    public Cube(Point p0, double r, Material m){ //creates a cube given a point, radus, and material using 12 triangles 
        triangles = new Triangle[12]; 
        
        //creates points for triangles 
        Point p1 = new Point(p0.getX()-r, p0.getY()-r, p0.getZ()-r); //0,0,0 
        Point p2 = new Point(p0.getX()-r, p0.getY()-r, p0.getZ()+r); //0,0,1
        Point p3 = new Point(p0.getX()+r, p0.getY()-r, p0.getZ()-r); //1,0,0 
        Point p4 = new Point(p0.getX()+r, p0.getY()-r, p0.getZ()+r); //1,0,1 
        Point p5 = new Point(p0.getX()-r, p0.getY()+r, p0.getZ()-r); //0,1,0
        Point p6 = new Point(p0.getX()-r, p0.getY()+r, p0.getZ()+r); //0,1,1
        Point p7 = new Point(p0.getX()+r, p0.getY()+r, p0.getZ()-r); //1,1,0
        Point p8 = new Point(p0.getX()+r, p0.getY()+r, p0.getZ()+r); //1,1,1
        
        //create triangles 
        
        //bottom
        t1 = new Triangle(p1,p2,p3,m);
        t2 = new Triangle(p2,p3,p4,m);
        //back 
        t3 = new Triangle(p1,p3, p5, m);
        t4 = new Triangle(p7,p3, p5, m);
        //front
        t5 = new Triangle(p2,p4, p6, m);
        t6 = new Triangle(p8,p4, p6, m);        
        //top 
        t7 = new Triangle(p6,p7,p5,m);
        t8 = new Triangle(p6,p7,p8,m);
        //sides
        t9 = new Triangle(p1,p5,p6,m);
        t10 = new Triangle(p1,p2,p6,m); 
        t11 = new Triangle(p3, p4, p8, m); 
        t12 = new Triangle(p3, p7, p8, m); 
        //put triangles into array
        triangles[0] = t5; 
        triangles[1] = t6; 
        triangles[2] = t7; 
        triangles[3] = t8; 
        triangles[4] = t9; 
        triangles[5] = t10; 
        triangles[6] = t11; 
        triangles[7] = t12; 
        triangles[8] = t1; 
        triangles[9] = t2; 
        triangles[10] = t3; 
        triangles[11] = t4; 

    }

    public Intersection intersect(Ray ray){ //returns intersection from ray to cube 
        double distance = Double.MAX_VALUE;
        Intersection i = null; 
        for(Triangle tri: triangles){ //loops through triangle array and look for closest surface 
            Intersection iTemp = tri.intersect(ray); //get intersection from ray to triangle
            if (iTemp != null && iTemp.getDistance() < distance){ // check if it's the shortest intersection 
                distance = iTemp.getDistance(); 
                i = iTemp; //change i to shortest intersection
            } 
        }
        return i; //returns null/the closest intersection 
    }
}
