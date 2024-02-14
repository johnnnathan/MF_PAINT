import java.util.Random;
public class Color_Node {
    private int red;
    private int green;
    private int blue;
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
                red = 0;green = 0; blue = 0;
        }



        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public Color_Node(int rbg){
        this.red = rbg/1000000;
        this.green = rbg%1000;
        this.blue = (rbg%1000000)/1000;

    }
    public Color_Node(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;

    }
    public int getRed(){return red;}

    public int getBlue(){return blue;}

    public int getGreen(){return green;}

    public boolean compareColors(Color_Node compared){
        if (this.getBlue() == compared.getBlue() && this.getRed() == compared.getRed() && this.getGreen() == compared.getGreen()){return true;}
        return false;
    }

    public int getRGB() {
        return (red << 16) | (green << 8) | blue;
    }



}
