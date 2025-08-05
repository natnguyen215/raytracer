
/**
 * Write a description of class Phong here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Phong extends Material
{
    private Color diffuse, specular;
    private double exponent; 
    public Phong(Color diff, Color spec, double exp){
        diffuse = diff;
        specular = spec;
        exponent = exp;
    }

    public Color computeLighting(Intersection i, Ray viewingRay, Light li){
        Vector lightDirection = li.computeLightDirection(i.getPosition()); //calculates direction from light to intersection point
        double dot = i.getNormal().dot(lightDirection); //gets dot product of normal and light direction to scale diffuse color 
        if(dot < 0){ //if shading is less than 0, the color is black 
            return new Color(0,0,0);
        }
        Color c2 = diffuse.scale(dot); //gets diffuse color scaled by dot 
        
        //use Phong specular model to find highlight color 
        
        Vector N = i.getNormal(); 
        Vector mirrorDirection = N.scale(2*(N.dot(lightDirection))).subtract(lightDirection); //get mirror direction 
        double cos = viewingRay.getDirection().scale(-1).dot(mirrorDirection); // get cosine of the specular angle 
        if(cos < 0){
            //if cosine is less than 0, no specular is being reflected, so you return scaled defuse color 
            return c2.shade(li.computeLightColor(i.getPosition())); 
        }
        double specCoefficient = Math.pow(cos,exponent); //get specular coefficient 
        Color highlight = li.computeLightColor(i.getPosition()).scale(specCoefficient).shade(c2); //get color of diffuse light at the intersect position, scale it by specular coeffiecient, and shade it with diffuse color 
        return highlight.tint(c2); //return color given by specular light and diffuse light 
    }

}
