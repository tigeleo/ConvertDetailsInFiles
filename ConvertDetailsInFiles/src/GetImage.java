import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GetImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String syntetixUrl="http://www.syndetics.com/index.php?client=primo&issn=0003-1224/sc.jpg";
		try {
			BufferedImage image= ImageIO.read(new URL(syntetixUrl));
			if (image != null) {
				System.out.println(image.getHeight() + " - " + image.getWidth() + " " + image.getType());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.err.println("=======================================================================================================");
		String test2= "http://mignews.com/i/logo6.png";
		try {
			BufferedImage image= ImageIO.read(new URL(test2));
			if (image != null) {
				System.out.println(image.getHeight() + " - " + image.getWidth() + " " + image.getType());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.err.println("=======================================================================================================");
		test2= "https://www.google.co.il/logos/doodles/2016/first-day-of-summer-2016-northern-hemisphere-5669295896920064.2-hp.gif";
		try {
			BufferedImage image= ImageIO.read(new URL(test2));
			if (image != null) {
				System.out.println(image.getHeight() + " - " + image.getWidth() + " " + image.getType());
			}else{
				System.err.println("Image is null");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.err.println("=======================================================================================================");
		test2= "http://indy100.independent.co.uk/image/31869-c6d5w5.jpg";
		try {
			BufferedImage image= ImageIO.read(new URL(test2));
			if (image != null) {
				System.out.println(image.getHeight() + " - " + image.getWidth() + " " + image.getType());
			}else{
				System.err.println("Image is null");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
