
/**
 * Write a description of class Lambert here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lambert extends Material
{
    private Color diffuse;
    public Lambert(Color c){
        diffuse = c;
    }
    public Color computeLighting(Intersection i, Ray viewingRay, Light li){ //computers color from a light at an intesection point
        //use lambertian shading model to find color 
        Vector lightDirection = li.computeLightDirection(i.getPosition()); //calculates direction from light to intersection point
        double dot = i.getNormal().dot(lightDirection); //gets dot product of normal and light direction to scale diffuse color 
        if(dot < 0){ //if shading is less than 0, the color is black 
            return new Color(0,0,0);
        }
        Color c2 = diffuse.scale(dot); //gets diffuse color scaled by dot 
        return c2.shade(li.computeLightColor(i.getPosition())); // returns diffuse color shaded by light color 
    }
}
