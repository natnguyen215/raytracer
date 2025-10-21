
/**
 * Write a description of class Color here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Color
{
    private double r,g,b;
    public Color(double newR, double newG, double newB){
        r = newR;
        g = newG;
        b = newB;
    }
   public double getR(){
        return r;
    }
    public double getG(){
        return g;
    }
    public double getB(){
        return b; 
    }
    public Color add(Color c){
        return new Color(this.r+c.getR(), this.g+c.getG(), this.b+c.getB());
    }
    public Color scale(double scalar){
        return new Color(r*scalar,g*scalar,b*scalar);
    }
    public int toARGB() {
        int ir = (int)(Math.min(Math.max(r,0),1) * 255 + 0.1);
        int ig = (int)(Math.min(Math.max(g,0),1) * 255 + 0.1);
        int ib = (int)(Math.min(Math.max(b,0),1) * 255 + 0.1);
        return (ir << 16) | (ig << 8) | (ib << 0);
}

public Color shade(Color c){ //shades two colors together
    double r2 = this.r*c.r;
    double g2 = this.g*c.g;
    double b2 = this.b*c.b;
    return new Color(r2,g2,b2);
}
public Color tint(Color c){ //tints this color with another color 
    double r2 = this.r + (1-this.r)*c.r;
    double g2 = this.g + (1-this.g)*c.g;
    double b2 = this.b + (1-this.b)*c.b;
    return new Color(r2,g2,b2);
}
}
