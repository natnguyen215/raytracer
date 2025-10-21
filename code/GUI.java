//MAIN INSPRIATION: https://www.youtube.com/watch?v=2xSA9cBWGVI&list=PLWfJeLqH0KUUicazPYhTdnZydkC10h6wB&index=1 
import javax.swing.JFrame; 
import javax.swing.*; 
import javax.swing.JLabel;
import javax.swing.JTextField; 
import java.awt.GridLayout; 
import java.awt.Container; 
import java.awt.event.ActionListener; 
import java.awt.event.*; 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList; 

public class GUI
{
    //parts of the GUI 
    private JFrame frame; 
    private JLabel DisplayedImage;
    private JButton leftButton, rightButton, forwardButton, backButton, upButton, downButton, rotateLButton, rotateRButton, saveButton;
    private JPanel buttons;
    private JPanel image;
    //width and height of the GUI
    private int width; 
    private int height;  
    //store scene for image creation and scene editing
    private Scene scene; 
    //variables for creating new scenes
    private int xRes, yRes, aaNumSamples; 

    public GUI(int width, int height, BufferedImage bi, Scene s,int xResolution, int yResolution, int aaNum){ //intialize GUI 
        //doubles
        xRes = xResolution;
        yRes = yResolution;
        aaNumSamples = aaNum; 
        //intialize parts of the GUI 
        frame = new JFrame();
        buttons = new JPanel(new GridLayout(3,3)); 
        image = new JPanel(); 
        DisplayedImage = new JLabel(new ImageIcon(bi)); 
        scene = s;
        //initalize buttons 
        leftButton = new JButton("Left");
        rightButton = new JButton("Right"); 
        forwardButton = new JButton("Forward");
        backButton = new JButton("Back");
        upButton = new JButton("Up"); 
        downButton = new JButton("Down"); 
        rotateLButton = new JButton("Rotate Left");
        rotateRButton = new JButton("Rotate Right");
        saveButton = new JButton("Save");
        //initalize width and height 
        this.width = width;
        this.height = height;
        
        
    }

    public void setUpGUI(){
        Container cp = frame.getContentPane(); //get container to setLayout  
        BorderLayout borderLayout = new BorderLayout(); //create 
        cp.setLayout(borderLayout); //get layout as BorderLayout 
        frame.setSize(width,height); //set frame using given width and height as dimensions 
        frame.setTitle("Photoshop");
        //add Buttons to buttons panel 
        buttons.add(leftButton);
        buttons.add(rightButton);
        buttons.add(forwardButton);
        buttons.add(backButton);
        buttons.add(upButton);
        buttons.add(downButton);
        buttons.add(saveButton); 
        buttons.add(rotateRButton);
        buttons.add(rotateLButton); 
        //add DisplyedImage label/icon to image panel 
        image.add(DisplayedImage);
        //add image and buttons to frame 
        cp.add(image,borderLayout.CENTER);
        cp.add(buttons,borderLayout.SOUTH);
        //close on exit 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
    }
    
  
    public void updateImage(BufferedImage bi){
        DisplayedImage.setIcon(new ImageIcon(bi)); //change displayedImage 
         
    }
    

    public void setUpButtonListeners(){
        ActionListener buttonListener = new ActionListener(){ //create ActionListener object 
            @Override //override the actionPerformed function from ActionListener 
            public void actionPerformed(ActionEvent ae){
                //the algorithim is to first update the scene's camera 
                //then, render the scene with this new camera, get the resulting bufferedImage, and update the displayed image accordingly
                if(ae.getSource() == leftButton){
                    scene.getCamera().move(scene.getCamera().getRightVector().scale(-1)); //move left by one unit, using the left direction vector of the camera 
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == rightButton){
                    scene.getCamera().move(scene.getCamera().getRightVector()); //move right by one unit, using the right direction vector of the camera 
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == forwardButton){
                    scene.getCamera().move(scene.getCamera().getForwardVector()); //move forward by one unit, using the forawrd direction vector of the camera 
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == backButton){
                    scene.getCamera().move(scene.getCamera().getForwardVector().scale(-1)); //move back by one unit, using the right direction vector of the camera 
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == upButton){
                    scene.getCamera().move(scene.getCamera().getUpVector());
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == downButton){
                    scene.getCamera().move(scene.getCamera().getUpVector().scale(-1));
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == rotateRButton){
                    scene.getCamera().setAngle(scene.getCamera().getAngle()+5);
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == rotateLButton){
                    scene.getCamera().setAngle(scene.getCamera().getAngle()-5);
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }
                else if(ae.getSource() == saveButton){
                    scene.getCamera().setAngle(scene.getCamera().getAngle()-5);
                    updateImage(returnBufferedImage(scene.render(xRes, yRes, aaNumSamples)));
                }

            }

        };
        
        //add ActionListeners to buttons 
        leftButton.addActionListener(buttonListener); 
        rightButton.addActionListener(buttonListener); 
        forwardButton.addActionListener(buttonListener); 
        backButton.addActionListener(buttonListener); 
        upButton.addActionListener(buttonListener); 
        downButton.addActionListener(buttonListener); 
        rotateLButton.addActionListener(buttonListener); 
        rotateRButton.addActionListener(buttonListener); 
        saveButton.addActionListener(buttonListener); 
        
    }
    
    public static BufferedImage returnBufferedImage(ColorImage image){ //gets BufferedImage from ColorImage
        BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                bi.setRGB(x,image.getHeight()-1-y,toARGB(image.getColor(x,y)));
            }
        }
        return bi; 
    }
    
    public static int toARGB(Color c) {
        int ir = Math.min(Math.max((int) (c.getR()*255),0),255);
        int ig = Math.min(Math.max( (int) (c.getG()*255),0),255);
        int ib = Math.min(Math.max((int) (c.getB() *255),0),255);
        return  (ir << 16) | (ig << 8) | (ib << 0); //returns rgb bits so that they each have unique locations that correspond to RGB byte
    }
}
