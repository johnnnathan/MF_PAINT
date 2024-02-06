import java.util.Random;
public class Color_Node {
    public int red;
    public int green;
    public int blue;
    public Color_Node(){
        Random random = new Random();
        red = random.nextInt(256);
        blue = random.nextInt(256);
        green = random.nextInt(256);
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    public Color_Node(String colorType){

        switch (colorType){
            case "Red":
                red = 200;green = 10; blue = 10;
                break;
            case "Green":
                red = 10;green = 255; blue = 10;
                break;
            case "Blue":
                red = 10;green = 100; blue = 200;
                break;
            default:
                red = 255;green = 255; blue = 255;
        }



        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    public Color_Node(int rbg){
        this.red = rbg/1000000;
        this.blue = (rbg%1000000)/1000;
        this.green = rbg%1000;
    }
    public int getRed(){return red;}

    public int getBlue(){return blue;}

    public int getGreen(){return green;}



}