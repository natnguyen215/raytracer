import java.io.*;
import java.util.*;

public class Humanoid extends Surface 
{

    private Point origin; //origin represents x in the middle, y at the bottom, and z at the front 
    private ArrayList<Point> vertices; //arraylist of vertices of the object 
    private ArrayList<Triangle> faces; //arralist of the faces (triangles) of the objects 
    private Material material; //material of faces 
    private double scalar; //scalar for size 
    public Humanoid(Point p, double scaleFactor, Material m) throws Exception{
        //initialize
        origin = p; 
        scalar = scaleFactor;
        material = m;
        vertices = new ArrayList<Point>();
        faces = new ArrayList<Triangle>();
        
        
        //pass obj file in as a txt 
        File file = new File("Humanoid.obj");

        Scanner sc = new Scanner(file); //read file into scanner
        while (sc.hasNextLine()){ //loop until end of file 
            StringTokenizer st = new StringTokenizer(sc.nextLine()); //tokenize each line 
            while(st.hasMoreTokens()){ // go through all tokens per line 
                String s = st.nextToken(); //get first token of line 
                if(st.hasMoreTokens() && s.equals("v")){ //if a vertice is given 
                    double z = (Double.parseDouble(st.nextToken())*-1 + origin.getZ())*scalar; //get z of vertice, translated and scaled 
                    double x = (Double.parseDouble(st.nextToken()) + origin.getX())*scalar; //get x of vertice, translated and scaled 
                    double y = (Double.parseDouble(st.nextToken()) + origin.getY())*scalar; //get y  of vertice, translated and scaled 
                    vertices.add(new Point(x,y,z)); //create new vertice and add it to arraylist 
                }
                if(st.hasMoreTokens() && s.equals("f")){ //if a face is given 
                    //get the 3 vertices of the face 
                    int p1 = Integer.parseInt(st.nextToken()); 
                    int p2 = Integer.parseInt(st.nextToken()); 
                    int p3 = Integer.parseInt(st.nextToken());
                    //create a new triangle using the points and material and add the triangle to the faces arraylist 
                    faces.add(new Triangle(vertices.get(p1-1),vertices.get(p2-1),vertices.get(p3-1) ,this.material));  //-1 for 0-indexing
                }
            }
        }
    }


    public Intersection intersect(Ray ray){ //returns intersection from ray to humanoid  
        double distance = Double.MAX_VALUE;
        Intersection i = null; 
        for(Triangle tri: faces){ //loops through triangle array and look for closest surface 
            Intersection iTemp = tri.intersect(ray); //get intersection from ray to triangle
            if (iTemp != null && iTemp.getDistance() < distance){ // check if it's the shortest intersection 
                distance = iTemp.getDistance(); 
                System.out.println(distance); 
                i = iTemp; //change i to shortest intersection
            } 
        }
        return i; //returns null/the closest intersection 
    }
}

