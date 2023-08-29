import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.FileNotFoundException;
public class Main
{
    public static void main (String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        if(args[0].equals("-h") )
        {
            String textAddress = args[1] , inputAddress = args[2] , outputAddress;
            if(args.length==3)
                outputAddress = "";
            else
                outputAddress = args[3];

            TextHider main = new TextHider(textAddress);
            main.hide(inputAddress , outputAddress); 






        }else{
            
            String inputAddress = args[1] , keyAddress = args[2] , textAddress;
            if(args.length==3){
                textAddress = "";
            }
            else{
                textAddress = args[3];
            }
            TextExtracter main = new TextExtracter(inputAddress);
            main.extract(keyAddress , textAddress);
        }
    }
}