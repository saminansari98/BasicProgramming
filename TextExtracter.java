import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class TextExtracter
{
    private String inputAddress;

    public TextExtracter(String inputimageAddress){

        inputAddress = inputimageAddress;
    }




    public void extract(String keyAddress , String textAddress)
    {
        try{
            PrintWriter out = new PrintWriter(textAddress);


            BufferedImage inputImage = ImageIO.read(new File(inputAddress));
            BufferedImage keyImage = ImageIO.read(new File(keyAddress));
            int x = inputImage.getWidth() ; int y = inputImage.getHeight();
            String stringResult = "";
            String binaryResult = "" ; 
            for(int j = 0 ; j < keyImage.getHeight(); j++){
                for(int i = 0 ; i < keyImage.getWidth(); i++){
                    if (binaryResult.length() == 8)
                    {
                        int result = 0;
                        for(int k = 0; k< binaryResult.length() ; k++)
                        {
                            result += Character.getNumericValue(binaryResult.charAt(binaryResult.length() - 1 - k)) * Math.pow(2 , k);
                        }
                        stringResult += (char)result;
                        binaryResult = "";
                    }

                    Color color = new Color(keyImage.getRGB(i , j));
                    Color colorinput = new Color(inputImage.getRGB(i , j));
                    if(color.getRed() == 255  ) {
                        binaryResult += colorinput.getRed() & 1;
                    }
                    if(color.getGreen() == 255  ) {
                        binaryResult += colorinput.getGreen() & 1;
                    }
                    if(color.getBlue() == 255  ) {
                        binaryResult += colorinput.getBlue() & 1;
                    }
                 }
             }
             
             out.print(stringResult);
             out.close();









        }
        catch (IOException e)
        {
            e.getMessage();
        }
    }
}