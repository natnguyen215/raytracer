
/**
 * Write a description of class ColorImage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColorImage
{
    // instance variables - replace the example below with your own
    private int height, width; 
    private Color[][] array; 

    public ColorImage(int newWidth, int newHeight){ //initalizes the ColorImage and fills in the Color array with black colors 
        width = newWidth;
        height = newHeight;
        array = new Color[width][height];
        for(int m = 0; m < width; m++){
            for(int n = 0; n < height; n++){
                array[m][n] = new Color(0,0,0); 
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height; 
    }

    public Color getColor(int col, int row){
        return array[col][row];
    }

    public void setColor(int col, int row, Color c){ 
        array[col][row] = c; 
    }
    //changes a ColorImage to a monochrome ColorImage
    public ColorImage Monochrome(){
        ColorImage returnImage = new ColorImage(width,height);
        //loop through ColorImage 
        for(int x = 0; x < width; x++){ 
            for(int y = 0; y < height; y++){
                //find each pixel's average monochrome RGB and change Color to this RGB 
                double averageGray = (this.getColor(x,y).getR() + this.getColor(x,y).getB() + this.getColor(x,y).getG())/3.0;  
                Color monochromePixel = new Color(averageGray,averageGray, averageGray);  
                returnImage.setColor(x,y,monochromePixel); 
            }
        }
        return returnImage;
    }
    //changes a ColorImage to a sepia ColorImage 
    public ColorImage Sepia(){ 
        ColorImage returnImage = new ColorImage(width,height);
        //loop through ColorImage 
        for(int x = 0; x < width; x++){ 
            for(int y = 0; y < height; y++){
                //find each pixel's Sepia RGB and change Color to this RGB 
                double newRed =  (this.getColor(x,y).getR() * .393) + (this.getColor(x,y).getG() *.769) + (this.getColor(x,y).getB() * .189); 
                double newGreen =   (this.getColor(x,y).getR() * .349) + (this.getColor(x,y).getG() *.686) + (this.getColor(x,y).getB() * .189); 
                double newBlue =  (this.getColor(x,y).getR() * .272) + (this.getColor(x,y).getG() *.534) + (this.getColor(x,y).getB() * .131); 
                Color sepiaPixel = new Color(newRed,newGreen, newBlue);  
                returnImage.setColor(x,y,sepiaPixel); 
            }
        }
        return returnImage;
    }
}
