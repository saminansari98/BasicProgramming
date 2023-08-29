import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
public class TextHider{
    private String textAddress ;

    public TextHider(String textFileAddress){
        textAddress = textFileAddress;
    }




	public  void hide( String inputAddress , String outputAddress)
    {
		try
        {
            ArrayList<Integer> rand = new ArrayList<Integer> ();
			BufferedImage inputImage = ImageIO.read(new File(inputAddress));
			BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),BufferedImage.TYPE_INT_RGB);
            BufferedImage keyImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),BufferedImage.TYPE_INT_RGB);
            int x = inputImage.getWidth() ; int y = inputImage.getHeight();
            



            Scanner in = new Scanner(new File(textAddress));// gereftan text va tabdil be code aski darmabnaye 2
            String text="";
            text=in.next(); 
            while (in.hasNextLine())
            {
                    text+="\r\n"+in.nextLine();     
            }
            in.close();

            String b ="";
            for(int i =0 ; i<text.length() ; i++ )
            {
                int chara = (int) text.charAt(i);
                for(int j=128 ; j>=1 ; j/=2){
                    if( (chara &j) >0){
                        b +="1";
                    }else{
                        b+="0";
                    }
                }
            }


            if(b.length() > (x*y)*(0.75) )
            throw new IllegalArgumentException("your text is big !!");
            

            while(rand.size() < b.length()) // entekhabe mokhtasate random
            {
                Boolean tekrar = true;
                int k = (int)(Math.random()*x*y);
                for(int j = 0 ; j <rand.size() ; j++)
                {
                    if(k == rand.get(j))
                        tekrar = false;
                }
                if(tekrar)
                    rand.add(k);
            }
            Collections.sort(rand);


            // taghire mokhtasate random entekhab shode
        for(int i = 0 ; i<rand.size() ; i++)
        {
            int y1 = rand.get(i)/x ; int x1 = rand.get(i)%x;
            int k = (int)(Math.random()*4);
            Color rgboutput = new Color(inputImage.getRGB(x1 , y1));
            Color rgbkey = new Color(0,0,0);
            if (k == 0)
            {
                
                int red = (rgboutput.getRed() & ~1) | Character.getNumericValue(b.charAt(i));
                rgboutput = new Color(red, rgboutput.getGreen(), rgboutput.getBlue());

                rgbkey = new Color(255, 0, 0);
            }
            else if (k == 1)
            {
                int green = (rgboutput.getGreen() & ~1) | Character.getNumericValue(b.charAt(i));
                rgbkey = new Color(0, 255, 0);
                rgboutput = new Color(rgboutput.getRed(), green, rgboutput.getBlue());
            }
            else 
            {
                int blue = (rgboutput.getBlue() & ~1) | Character.getNumericValue(b.charAt(i));
                rgbkey = new Color(0, 0, 255);
                rgboutput = new Color(rgboutput.getRed(), rgboutput.getGreen(), blue);

            }
            keyImage.setRGB(x1,y1,rgbkey.getRGB());
            outputImage.setRGB(x1,y1,rgboutput.getRGB()); 
        }




        // taghire mokhtasate random entekhab nashode!!
        for(int i =0 ; i<x ; i++)
        {
            for (int j=0 ; j<y ; j++)
            {
                Boolean yorn = true ; //yes or no baraye check kardane pixel haye taghir yafte ya nayafte 
                for(int k =0 ;k<rand.size() ; k++ )
                {
                    int y1 = rand.get(k)/x ;
                    int x1 = rand.get(k)%x;
                    if(x1 ==i  && y1 == j)
                        yorn = false;
                }
                if(yorn)
                {
                    int rgboutput = inputImage.getRGB(i , j);
                    outputImage.setRGB(i,j,rgboutput);
                    
                }
            }
        }

        ImageIO.write(outputImage,"BMP",new File(outputAddress));
        ImageIO.write(keyImage,"BMP",new File("key.bmp"));

        } catch (IOException e)
		{
            System.out.println(e.getMessage());
        }
    }
}