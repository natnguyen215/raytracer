
/**
 * Write a description of class Texture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Texture extends Material 
{
    ColorImage texture;
    public Texture(String filename){
        texture = ImageLoader.loadImage(filename);
    }
    public Color computeLighting(Intersection i, Ray viewingRay, Light li){
        //gets  texture's pixel coordinates using 
        int x = (int) (i.getImageX()*texture.getWidth());
        int y = (int) (i.getImageY()*texture.getHeight());
        //Vector lightDirection = li.computeLightDirection(i.getPosition());
        //double dot = i.getNormal().dot(lightDirection); 
        //if(dot < 0){
         //   return new Color(0,0,0);
        //}
        //System.out.println(x);
        //System.out.println(y);
        Color c2 = texture.getColor(x,y); 
        //c2 = c2.scale(dot);
        return c2;
        //.shade(li.computeLightColor(i.getPosition()));

    }
}
