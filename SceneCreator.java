/**
 * Details the static methods to creating various scenes for use in the raytracer.
 * scene1() is included as an example. You can add more static methods (for example scene2(),
 * scene3(), etc.) to create different scenes without affecting scene1.
 *
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class SceneCreator {
    public static Scene scene1(double xResolution, double yResolution){
        PerspectiveCamera cam = new PerspectiveCamera(new Point(0,5,0),       // camera location
                                new Vector(0,0,-1),     // forward vector/view direction
                                new Vector(0,1,0),      // up vector
                                30,                     // field of view
                                xResolution/yResolution); // aspect ratio
        Scene s = new Scene(cam);
        
        //Each sphere takes a Point (its center), the radius, and a material.
        //For now, since we have not implemented the Material classes, we simply say they are null.
        
        //Light light = new LightBulb(new Color(1,1,1), new Point(0,5,0));
        PointLight light2 = new PointLight(new Color(1,1,1), new Point(0,100,50));
        Lambert a = new Lambert(new Color(1,1,1));
        Mirror k = new Mirror();
        Texture b = new Texture("testGradient.png");
        Lambert darkFloor = new Lambert(new Color(0.5,0.5,0.5));
        Surface floor = new Triangle(new Point(0,-5,0), new Point(3000,-5,-100), new Point(-3000,-5,-100), darkFloor);
        s.addSurface(floor);
        MirrorPhong c = new MirrorPhong(new Color(0.5,0.5,0.5), new Color(0.5,1,0.2), 3, 0.5);
        Surface s1 = new Sphere(new Point(0,-1,-20),3, b, new Vector(0,1,0), new Vector(1,0,0));
        //s.addSurface(s1);
        Surface s2 = new Cube(new Point(0,0,-15),3, a);
        //s.addSurface(s2);
        Surface s3 = new Sphere(new Point(5,-1,-20),1.5, b);
        s.addSurface(s3);
        
        //Each triangle takes 3 Points (its vertexes), and a material.
        Surface t1 = new Triangle(new Point(-3.5,-1,-15), new Point(-3.5,1,-15), new Point(-5,0,-16), a);
        //s.addSurface(t1);
        //s.addLight(light);
        s.addLight(light2);
        return s;
    }
    public static Scene scene2(double xResolution, double yResolution){
        PerspectiveCamera cam = new PerspectiveCamera(new Point(0,0,0),       // camera location
                                new Vector(0,0,-1),     // forward vector/view direction
                                new Vector(0,1,0),      // up vector
                                20,                     // field of view
                                xResolution/yResolution ); // aspect ratio
        Scene s = new Scene(cam);
        
        //Each sphere takes a Point (its center), the radius, and a material.
        //For now, since we have not implemented the Material classes, we simply say they are null.
        PointLight light = new PointLight(new Color(0.7,0.7,0.7), new Point(0,40,00));
        s.addLight(light);
        Material a = new Lambert(new Color(0.5,0.3,0.5));
        //tree 
        for(int x = 0; x< 15; x++){
        double random1 = Math.random()*5 - 2.5;
        double random2 = Math.random()*5 - 2.5;
        double random3 = Math.random()*2 - 1;
        Surface treeLeaves = new Sphere(new Point(0+random1,5+random2,-100+random3),3, new Lambert (new Color(0,1,0)));
        s.addSurface(treeLeaves);
        }
        Surface treeTube = new Tube(new Point(0,-5,-100), new Point (0,5,-100), 2, new Lambert(new Color(0.9,0.4,0.2)));
        s.addSurface(treeTube);
        
        //clouds 
        for(int x = 0; x< 30; x++){
        double randomx = Math.random()*20 - 10;
        double randomy = Math.random()*2 - 1;
        double randomz = Math.random()*5 - 2.5;
        Surface Cloud1 = new Sphere(new Point(0+randomx,15+randomy,-100+randomz),2, new Lambert (new Color(1,1,1)));
        s.addSurface(Cloud1);
        randomx = Math.random()*20 - 10;
        randomy = Math.random()*2 - 1;
        randomz = Math.random()*5 - 2.5;
        Surface Cloud2 = new Sphere(new Point(-30+randomx,15+randomy,-100+randomz),2, new Lambert (new Color(1,1,1)));
        s.addSurface(Cloud2);
        randomx = Math.random()*20 - 10;
        randomy = Math.random()*2 - 1;
        randomz = Math.random()*5 - 2.5;
        Surface Cloud3 = new Sphere(new Point(22+randomx,30+randomy,-120+randomz),2, new Lambert (new Color(1,1,1)));
        s.addSurface(Cloud3);
        }
        
        //grass
         for(int x = 0; x< 500; x++){
            double randomx = Math.random()*20 - 10;
        }
        Surface sky = new Triangle(new Point(-9999,-10,-9999), new Point(9999, -10, -9999), new Point (0,9999, -9999), new Lambert(new Color(0.2,0.2,1))); 
        s.addSurface(sky);
        Surface grass = new Triangle(new Point(-9999,-10,-9999), new Point(9999, -10, -9999), new Point (0,-10000, 9999), new Lambert(new Color(0.1,1,0.1)));
        s.addSurface(grass);
        return s;
    }
    public static Scene scene3(double xResolution, double yResolution){
        PerspectiveCamera cam = new PerspectiveCamera(new Point(0,1,0),       // camera location
                                new Vector(0,0,-1),     // forward vector/view direction
                                new Vector(0,1,0),      // up vector
                                20,                     // field of view
                                xResolution/yResolution // aspect ratio
                                );
        Scene s = new Scene(cam);
        
        //Each sphere takes a Point (its center), the radius, and a material.
        //For now, since we have not implemented the Material classes, we simply say they are null.
        PointLight light = new PointLight(new Color(1,1,1), new Point(1,0,1));
        Lambert a = new Lambert(new Color(0.5,0.5,1));
        Phong b = new Phong(new Color(0.5,0.5,0.5), new Color(0.5,1,0.2), 3);
        Lambert d = new Lambert(new Color(0,0,0));
        MirrorPhong c = new MirrorPhong(new Color(0.5,0.5,0.5), new Color(0.5,1,0.2), 3, 0.5);
        Surface s1 = new Sphere(new Point(0,0,-20),3, a, new Vector(1,0,0));
        s.addSurface(s1);
        Surface s2 = new BlackHole(new Point(0,5,-15),1, d);
        s.addSurface(s2);
        Surface s3 = new Sphere(new Point(5,0,-20),1.5, c);
        s.addSurface(s3);
        //Each triangle takes 3 Points (its vertexes), and a material.
        Surface floor = new Triangle(new Point(0,-5,0), new Point(3000,-5,-1000), new Point(-3000,-5,-1000), a);
        s.addSurface(floor);
        s.addLight(light);
        return s;
    }
    public static Scene highResScene(double xResolution, double yResolution){
        PerspectiveCamera cam = new PerspectiveCamera(new Point(0,1,0),       // camera location
                                new Vector(0,0,-1),     // forward vector/view direction
                                new Vector(0,1,0),      // up vector
                                50,                     // field of view
                                xResolution/yResolution // aspect ratio
                                );
        Scene s = new Scene(cam);
        
        
        PointLight light = new PointLight(new Color(1,1,1), new Point(0,0,0));
        //Texture earth = new Texture 
        Lambert a = new Lambert(new Color(0.5,0.5,0.5));
        
        Surface Earth = new Sphere(new Point(0,0,-100), 1, a); 
        
        Lambert redLambert = new Lambert(new Color(1,0.2,0.2)); 
        Lambert blueLambert = new Lambert(new Color(0,0.2,0.8)); 
        Phong bluePhong = new Phong(new Color(0.5,0.5,1), new Color(0.9,0.9,1), 12);
        Mirror mirror = new Mirror();
        return s; 
    }
    public static Scene test(double xResolution, double yResolution){
        PerspectiveCamera cam = new PerspectiveCamera(new Point(0,5,20),       // camera location
                                new Vector(0,0,-1),     // forward vector/view direction
                                new Vector(0,1,0),      // up vector
                                50,                     // field of view
                                xResolution/yResolution // aspect ratio
                                );
        Scene s = new Scene(cam);
        farrermat Black = new farrermat(new Color(0,0,0)); 
        farrermat White = new farrermat(new Color(1,0,0)); 
        PointLight light = new PointLight(new Color(1,1,1), new Point(0,5,0));
        //s.addLight(light);
        Lambert d = new Lambert(new Color(1,1,1));
        farrermat e = new farrermat(new Color(1,0,0));
        Texture b = new Texture("stars.png");
        MirrorPhong c = new MirrorPhong(new Color(0.5,0.5,0.5), new Color(0.5,1,0.2), 3, 0.5);
        Surface s2 = new BlackHole(new Point(0,0,-30),8.487859588608, Black);
        s.addSurface(s2);
        Surface s1 = new RingHorizontal(new Point(0,0,-30), 25, 15, new Vector(0,1,0), e); 
        Surface sky = new RingVertical(new Point(0,0,-50), 150, 1, new Vector(0,0,1), b); 
        s.addSurface(sky);
        s.addSurface(s1); 
        return s; 
    }
    
}