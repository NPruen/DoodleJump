import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pad extends Object {
	private BufferedImage img;
	private String imgName = "images/Pad.png";
	
	private int m_xPos;
	private int m_yPos;
	
	public Pad() {
		try {
			img = ImageIO.read(new File(imgName));
		} catch(IOException ex) {
			System.out.println("Cant't find image " + imgName);
		}
	}
	
	public void update() {
		
	}
}