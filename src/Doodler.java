import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Doodler extends Object {
	private static final int RESET_DY = -45;
	private static final int DDY = 3;

	private Game m_game;
	
	private Dimension screenDimension;
	
	private BufferedImage img;
	private String imgName = "images/Doodler.png";
	
	private int m_xPos = 150;
	private double dx;
	private int m_yPos = 250;
	private int dy;
	
	public Doodler(Game game) {
		try {
			img = ImageIO.read(new File(imgName));
		} catch(IOException ex) {
			System.out.println("Cant't find image " + imgName);
		}
		m_game = game;
		
		dy = RESET_DY;
		
		screenDimension = m_game.getScreenDimension();
	}
	
	@Override
	public void update() {
//		m_input = m_game.getInput();
		try {
			switch(m_game.getInput().toLowerCase()) {
				case "a": {
					if(dx >= -20) {
						dx--;
					}
					break;
				}
				case "d": {
					if(dx <= 20) {
						dx++;
					}
					break;
				}
				case "w": {
					shoot();
					break;
				}
				default:
					if(dx > 1) dx-=2;
					else if(dx < -1) dx+=2;
					else if(dx > 0) dx--;
					else if(dx < 0) dx++;
					break;
			}
		} catch(NullPointerException ex) {
//			System.out.println("Error in update()");
		}
		
		dy += DDY;
		m_yPos += dy;
		
		if(m_yPos >= m_game.getScreenDimension().getHeight()) {
			bounce();
		}
		
		m_xPos += dx;
		if(m_xPos >= screenDimension.getWidth()) {
			m_xPos = 0;
		} else if(m_xPos <= 0) {
			m_xPos = (int)screenDimension.getWidth();
		}
	}
	
	public void shoot() {
		
	}
	
	public void bounce() {
		dy = RESET_DY;
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