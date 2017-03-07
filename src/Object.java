import java.awt.image.BufferedImage;

public class Object {
	private BufferedImage img;
	private int m_x;
	private int m_y;
	
	public BufferedImage getImage() {
		return img;
	}
	
	public void update(){}
	
	public int getX() {
		return m_x;
	}
	public int getY() {
		return m_y;
	}
}