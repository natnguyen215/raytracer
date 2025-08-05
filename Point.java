import java.util.*;

/**
 * Write a description of class Point here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Point
{
    private double x, y, z;
    public Point(double newX, double newY, double newZ){
        x = newX;
        y = newY;
        z = newZ; 
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z; 
    }

    public Point add(Vector v){ //adds vector to a point
        double tempx, tempy, tempz;
        tempx = x + v.getDX();
        tempy = y + v.getDY(); 
        tempz = z + v.getDZ();
        return new Point(tempx, tempy, tempz); 
    }

    public Vector subtract(Point p){ //subtracts vector from a point
        double tempx, tempy, tempz; 
        tempx = this.x - p.getX();
        tempy = this.y - p.getY();
        tempz = this.z - p.getZ();

        return new Vector(tempx, tempy, tempz);
    }

}
