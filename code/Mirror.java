
/**
 * Write a description of class ReflectiveLambert here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mirror extends Material
{
    //this mimics Lambert, but gives it a preset diffuse and reflectiveness

    private Color diffuse; 
    private double reflectiveness;

    public Mirror(){ 
        diffuse = new Color(0.0000001,0.0000001,0.0000001); //get close to "colorless" by creating a near black color 
        reflectiveness = 1; //mirrors have a reflectiveness of 1 
    }

    public double getReflectiveness(){
        return reflectiveness; 
    }

    public Color computeLighting(Intersection i, Ray viewingRay, Light li){ //see lambert for explanation of code  
        Vector lightDirection = li.computeLightDirection(i.getPosition());
        double dot = i.getNormal().dot(lightDirection); 
        if(dot < 0){
            return new Color(0,0,0);
        }
        Color c2 = diffuse.scale(dot);
        return c2.shade(li.computeLightColor(i.getPosition()));
    }   
}
