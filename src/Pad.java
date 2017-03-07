import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pad extends Object {
	private BufferedImage img;
	private String imgName = "images/Pad.png";
	
	private int m_xPos;
	private int m_yPos;
	
	private Game m_game;
	
	public Pad(Game game, int y) {
		try {
			img = ImageIO.read(new File(imgName));
		} catch(IOException ex) {
			System.out.println("Cant't find image " + imgName);
		}
		m_game = game;
		m_xPos = (int)(Math.random()*m_game.getScreenDimension().getWidth());
		m_yPos = y;
	}
	
	@Override
	public void update() {
		m_yPos += m_game.getTranslation();
		
		if(m_yPos >= m_game.getScreenDimension().getHeight()) {
			m_game.createNewPad();
			m_game.remove(this); //suicide
		}
	}
	@Override
	public int getX() {
		return m_xPos;
	}
	@Override
	public int getY() {
		return m_yPos;
	}
	@Override
	public BufferedImage getImage() {
		return img;
	}
}