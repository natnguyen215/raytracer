
/**
 * Write a description of class Scene here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.*;
public class Scene
{
    // instance variables - replace the example below with your own
    private Camera camera;
    private ArrayList<Surface> surfaces; 
    private ArrayList<Light> lights;
    public Scene(Camera newCam){
        camera = newCam;   
        surfaces = new ArrayList<Surface>();
        lights = new ArrayList<Light>();
    }

    public void setCamera(Camera newCam){
        camera = newCam;
    }

    public void addSurface(Surface s){
        surfaces.add(s); 
    }

    public void addLight(Light li){
        lights.add(li);
    }

    public Color computeVisibleColor(Ray r, int bouncesLeft, int recursor){ //for a given viewing ray, find its intersection (if it has one) and return the intersection's Color 

        double distance = Double.MAX_VALUE;
        Intersection i = null; 
        for(Surface s: surfaces){ //loop through all the surfaces in the scene 
            Intersection iTemp = s.intersect(r); //check for intersect with ray to the surface 
            if (iTemp != null && iTemp.getDistance() < distance){ //this looks for the closest intersection and stores it into i
                distance = iTemp.getDistance(); 
                i = iTemp;                
            }
        }
        if(recursor==0){ //if no intersection was found, return black 
            return (new Color(0,0,0));
        }
        if(i == null || distance > 1){
            //null is checked as light bends and we need to make sure the light has hit something in that bend even if it is null without the bend 
            //distance > 1 is checked for the recursive step 
            for(Surface s: surfaces){
                if (s instanceof BlackHole){
                    r.applyGravity((BlackHole) s); //applies gravity if blackhole is in the scene 
                }
            }
            return computeVisibleColor(new Ray(r.evaluate(1), r.getDirection()), bouncesLeft, recursor-1); //computervisiblecolor is recursed by creating a new ray that is 1 lightsecond ahead of the original ray after an application of grvaity 
        }
        
        
        Color c = new Color(0,0,0);
        
        c = c.tint(i.getMaterial().computeLighting(i, r, null)); //returns color if something is hit 
       
        
        for (Light li: lights){ //loops through all the lights in the scene 
            li.changePosition(); //see LightBulb
            if(!isShadowed(i.getPosition(), li, r)){ //if an intersection point isn't shadowed from a light 
                c = c.tint(i.getMaterial().computeLighting(i, r, li)); //tint black Color with the color of the light at the intersection 
            }
        }

        if(i.getMaterial().getReflectiveness() == 0 || bouncesLeft == 0){ //if the material is not reflective/finishes all reflections, return the color 
            return c; 
        }

        Vector N = i.getNormal(); 
        Vector V = r.getDirection().scale(-1); 
        Vector mirrorDirection = N.scale(2*(N.dot(V))).subtract(V); //calculates the direction of the reflected vieweing ray reflecting off material 
        Ray reflectedView = new Ray(i.getPosition(), mirrorDirection, r.getTime());  //creates reflected viewing ray using reflected directioon
        Color reflectionColor = computeVisibleColor(reflectedView, bouncesLeft-1, recursor); //recursively trace ray until final bounce to get reflectionColor
        reflectionColor = reflectionColor.scale(i.getMaterial().getReflectiveness()); //scale reflection color by reflectiveness value

        return c.tint(reflectionColor); //returns final color by tinting material color and reflection color together
    }

    public ColorImage renderWithParallelProcessing(int xRes, int yRes,int numSamples){
        ColorImage colorimage = new ColorImage(xRes,yRes);
        int aaResolution = (int) Math.sqrt(numSamples); 
        for(int x = 0; x < xRes; x++){ //loop through each pixel 
            for(int y = 0; y < yRes; y++){
                Color c = new Color(0,0,0);
                for(int i = 0; i < aaResolution; i++){ //loop from 0 to aaResolution to create subpixel samples  
                    for(int j = 0; j < aaResolution; j++){
                        double u = (x + (i+0.5)/aaResolution)/xRes; //gets the corresponding ray location for x and y pixel samples
                        double v = (y + (j+0.5)/aaResolution)/yRes; 
                        Ray r = camera.generateRay(u,v); //creates ray using pixel samples 
                        c = c.add(computeVisibleColor(r, 2, 1)); //calculates color each in subpixel and adds it up to get a color for each pixel
                    }
                }
                c = c.scale(1.0/(Math.pow(aaResolution,2))); //scales down color by number of samples 
                colorimage.setColor(x,y,c); //sets the color for each pixel 
            }
        }
        return colorimage; 
    }

    public ColorImage render(int xRes, int yRes,int numSamples){
        ColorImage colorimage = new ColorImage(xRes,yRes);
        int aaResolution = (int) Math.sqrt(numSamples); 
        for(int x = 0; x < xRes; x++){ //loop through each pixel 
            for(int y = 0; y < yRes; y++){
                Color c = new Color(0,0,0);
                for(int i = 0; i < aaResolution; i++){ //loop from 0 to aaResolution to create subpixel samples  
                    for(int j = 0; j < aaResolution; j++){
                        double u = (x + (i+0.5)/aaResolution)/xRes; //gets the corresponding ray location for x and y pixel samples
                        double v = (y + (j+0.5)/aaResolution)/yRes; 
                        Ray r = camera.generateRay(u,v); //creates ray using pixel samples 
                        c = c.add(computeVisibleColor(r, 2, 200)); //calculates color each in subpixel and adds it up to get a color for each pixel
                    }
                }
                c = c.scale(1.0/(Math.pow(aaResolution,2))); //scales down color by number of samples 
                colorimage.setColor(x,y,c); //sets the color for each pixel 
            }
        }
        return colorimage; 
    }

    public boolean isShadowed(Point p, Light li, Ray r){ //this checks if there is a surface between a point and a light 
        Ray shadow = new Ray(p, li.computeLightDirection(p),r.getTime()); //creates shadow ray 
        Intersection i = null; 
        for(Surface s: surfaces){ //loop through surfaces 
            i = s.intersect(shadow); //check intersection of shadow array 
            if (i != null && i.getDistance() < li.computeLightDistance(p)){ // if there is a shadow intersection between surface and light, then return true 
                return true;
            }
        }
        return false; //if no surface is detected between the point and light, return false  
    }

    public Camera getCamera(){
        return camera; 
    }
}
class MultiRender extends RecursiveAction {
    int seqThreshold = 100;
    int start, end, col; 
    ColorImage image;
    Scene s;
    MultiRender(ColorImage image, Scene s, int start, int end, int col)
    {
        this.image = image;
        this.start = start;
        this.end = end;
        this.col = col;
        this.s = s;
    }

    @Override
    protected void compute()
    {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                image.setColor(col,i,s.render(seqThreshold,seqThreshold,2).getColor(col,i));
            }
        }
        else {
            // Otherwise, continue to break the data into smaller pieces
            // Find the midpoint
            int middle = (start + end) / 2;

            // Invoke new tasks, using the subdivided tasks.
            invokeAll(new MultiRender(image,s, start, middle, col),
                new MultiRender(image, s, middle, end, col));
        }
    }
}

