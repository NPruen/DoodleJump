import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class Canvas extends JPanel {
	private BufferedImage m_image;
	private Graphics g;
	
	private List<Object> m_objects;
	
	public Canvas(Dimension d, List<Object> objects) {
		setPreferredSize(d);
		m_image = new BufferedImage((int) d.getWidth(), (int) d.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g = m_image.getGraphics();
		
		m_objects = objects;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Object object : m_objects) {
			BufferedImage image = object.getImage();
			try {
				g.drawImage(image, object.getX() - image.getWidth(null) / 2, object.getY() - image.getHeight(null) / 2,
								image.getWidth(null), image.getHeight(null), null);
			} catch (NullPointerException ex) {
				System.err.println("Couldn't draw image");
			}
		}
	}
	
	// public void drawObject(Object object) {
	// BufferedImage image = object.getImage();
	// try {
	// g.drawImage(image, object.getX(), object.getY(), image.getWidth(null),
	// image.getHeight(null), null);
	// } catch(NullPointerException ex) {
	// System.err.println("Couldn't draw image");
	// }
	// }
}