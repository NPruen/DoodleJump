import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Object {
	private static final int SPEED_FACTOR = 4;

	private double m_x, m_y, m_dy, m_dx;
	
	private BufferedImage img;
	private String imgName = "images/Bullet.png";
	
	public Bullet(double x, double y, double dx, double dy) {
		m_dx = -dx / 100;
		m_dy = -dy / 100;
		
		m_x = x;
		m_y = y;
		
		try {
			img = ImageIO.read(new File(imgName));
		} catch (IOException ex) {
			System.out.println("Cant't find image " + imgName);
		}
	}
	
	@Override
	public void update() {
		m_x += m_dx * SPEED_FACTOR;
		m_y += m_dy * SPEED_FACTOR;
	}
	
	@Override
	public int getX() {
		return (int)m_x;
	}
	
	@Override
	public int getY() {
		return (int)m_y;
	}
	
	@Override
	public BufferedImage getImage() {
		return img;
	}
}