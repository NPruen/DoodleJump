import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Doodler extends Object {
	private static final int RESET_DY = -16;
	private static final double DDY = .5;
	
	private Game m_game;
	
	private int m_score;
	
	private List<Object> m_objects;
	
	private Dimension screenDimension;
	
	private BufferedImage img;
	private String imgName = "images/Doodler.png";
	
	private int m_xPos = 150;
	private double dx;
	private int m_yPos = 250;
	private double dy;
	
	private int m_width;
	private int m_height;
	
	public Doodler(Game game) {
		try {
			img = ImageIO.read(new File(imgName));
		} catch (IOException ex) {
			System.out.println("Cant't find image " + imgName);
		}
		m_game = game;
		
		dy = RESET_DY;
		
		screenDimension = m_game.getScreenDimension();
		
		m_objects = m_game.getObjects();
		
		m_width = img.getWidth();
		m_height = img.getHeight();
	}
	
	@Override
	public void update() {
		try {
			switch (m_game.getInput().toLowerCase()) {
				case "a": {
					if (dx >= -10) {
						dx-=.5;
					}
					break;
				}
				case "d": {
					if (dx <= 10) {
						dx+=.5;
					}
					break;
				}
				case "w": {
					shoot();
					break;
				}
				default:
					if (dx > .5)
						dx-=.5;
					else if (dx < -.5)
						dx+=.5;
					else if (dx > 0)
						dx-=.25;
					else if (dx < 0)
						dx+=.25;
					break;
			}
		} catch (NullPointerException ex) {
			// System.err.println("Error in update()");
		}
		
		if(dy <= 30) {
			dy += DDY;
		}
		if(m_yPos <= screenDimension.getHeight()/3 && dy <= 0) {
			m_game.translate(-dy);
		} else {
			m_yPos += dy;
			m_game.translate(0);
		}
		
		m_objects = m_game.getObjects();
		
		System.out.println(isOnObject());
		if (isOnObject()) {
			bounce();
		}
		
		m_xPos += dx;
		if (m_xPos >= screenDimension.getWidth()) {
			m_xPos = 0;
		} else if (m_xPos <= 0) {
			m_xPos = (int) screenDimension.getWidth();
		}
		if(m_yPos >= m_game.getScreenDimension().getHeight()) {
			m_game.endGame();
		}
	}
	
	public void shoot() {
		
	}
	
	public boolean isOnObject() {
		for(Object obj : m_objects) {
			if(obj.getClass() != Doodler.class) {
				if((obj.getX()-obj.getImage().getWidth()/2 <= m_xPos + m_width/2)
								&& (obj.getX() + obj.getImage().getWidth()/2 >= m_xPos - m_width/2)
								&& (obj.getY() - dy/2 <= m_yPos + m_height)
								&& (obj.getY() + obj.getImage().getHeight() + dy/2 >= m_yPos + m_height)
								&& dy >= 0) {
					m_yPos = obj.getY() - m_height;;
					return true;
				}
			}
		}
		
		
		return false;
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