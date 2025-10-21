
/**
 * Write a description of class Vector here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vector
{
    // instance variables - replace the example below with your own
    private double dx, dy, dz;
    public Vector(double newDX, double newDY, double newDZ){
        dx = newDX;
        dy = newDY;
        dz = newDZ;
    }

    public double getDX(){
        return dx;
    }

    public double getDY(){
        return dy;
    }

    public double getDZ(){
        return dz; 
    }

    public Vector scale(double scalar){ //scales a vector by a scalar number
        return new Vector(dx*scalar, dy*scalar, dz*scalar);
    }

    public Vector add(Vector v){ //adds two vectors
        return new Vector(dx+v.getDX(), dy+v.getDY(), dz+v.getDZ());    
    }

    public Vector subtract(Vector v){ //subtracts this vector by another given vector
        return new Vector(dx-v.getDX(), dy-v.getDY(), dz-v.getDZ());
    }

    public double dot(Vector v){ //returns the dot product of two vectors 
        return (dx*v.getDX() + dy*v.getDY() + dz*v.getDZ());
    }

    public Vector cross(Vector v){ //returns the cross product of this vector with another vector 
        double tempdx = dy*v.getDZ() - dz*v.getDY();
        double tempdy = dz*v.getDX() - dx*v.getDZ();
        double tempdz = dx*v.getDY() - dy*v.getDX();
        return new Vector(tempdx, tempdy, tempdz);
    }

    public double length(){ //returns legth of this vector 
        return Math.sqrt(this.dot(this));
    }

    public Vector normalize() { //normalizes vector by diving all 
        double L = this.length(); 
        return new Vector(dx/L, dy/L, dz/L);
    }
}
