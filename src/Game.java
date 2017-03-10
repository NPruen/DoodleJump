import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class Game {
	private JFrame m_frame;
	private JLabel m_scoreboard;
	private Canvas m_canvas;
	
	private static final Dimension SCREEN_DIMENSION = new Dimension(300, 500);
	
	private List<Object> m_objects;
	private List<Object> m_toRemove;
	private Integer m_numOfObjectsToAdd;
	
	private double m_yTranslation;
	
	
	private int m_mouseX, m_mouseY;
	private MouseListener mouse = new MouseListener() {
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			m_doodler.shoot();
		}
	};
	
	private MouseMotionListener mouseMotion = new MouseMotionListener() {
		@Override
		public void mouseMoved(MouseEvent e) {
			m_mouseX = e.getX();
			m_mouseY = e.getY();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
	};
	
	private boolean m_keyPressed = false;
	private String m_keyInput;
	private KeyListener m_keyboard = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			m_keyPressed = false;
			m_keyInput = "";
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (!m_keyPressed) {
				m_keyInput = "" + e.getKeyChar();
				System.out.println(m_keyInput);
				m_keyPressed = true;
			}
		}
	};
	
	private ActionListener clock = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Object object : m_objects) {
				object.update();
			}
			for(Object object : m_toRemove) {
				m_objects.remove(object);
			}
			m_toRemove.removeAll(m_toRemove);
			for(; m_numOfObjectsToAdd > 0; m_numOfObjectsToAdd--) {
				createNewPad();
			}
			
			m_scoreboard.setText("Score: " + m_score);
			
			m_canvas.repaint();
		}
	};
	private Timer timer;
	
	private Doodler m_doodler;
	
	private int m_score;
	
	public Game() {
		m_doodler = new Doodler(this);
		
		m_score = 0;
		
		m_objects = new ArrayList<Object>();
		m_toRemove = new ArrayList<Object>();
		m_numOfObjectsToAdd = new Integer(0);
		m_objects.add(m_doodler);
		createNewPad();
		createNewPad(100);
		createNewPad(200);
		createNewPad(300);
		createNewPad(400);
		createNewPad(500);
		
		m_canvas = new Canvas(SCREEN_DIMENSION, m_objects);
		
		m_scoreboard = new JLabel();
		
		m_yTranslation = 0;
		
		m_frame = new JFrame("Doodle Jump");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setSize(SCREEN_DIMENSION);
		m_frame.setResizable(false);
		m_frame.setLayout(new BorderLayout());
		m_frame.add(m_canvas, BorderLayout.CENTER);
		m_frame.add(m_scoreboard, BorderLayout.NORTH);
		m_frame.addKeyListener(m_keyboard);
		m_frame.setVisible(true);
		
		m_frame.addMouseListener(mouse);
		m_frame.addMouseMotionListener(mouseMotion);
		
		timer = new Timer(10, clock);
		timer.start();
	}
	
	public void createBullet(double x, double y, double dx, double dy) {
		m_objects.add(new Bullet(x, y, dx, dy));
	}
	
	public void translate(double y) {
		m_yTranslation = y;
		m_score += y;
	}
	
	public double getTranslation() {
		return m_yTranslation;
	}
	
	public void createNewPad() {
		Pad pad = new Pad(this, 0);
		m_objects.add(0, pad);
	}
	
	public void createNewPad(int y) {
		Pad pad = new Pad(this, y);
		m_objects.add(0, pad);
	}
	
	public void remove(Object object) {
		m_objects.remove(object);
	}
	
	public void addToToRemove(Object object) {
		m_toRemove.add(object);
	}
	
	public void addNewCreate() {
		m_numOfObjectsToAdd++;
	}
	
	public String getInput() {
		return m_keyInput;
	}
	
	public Canvas getCanvas() {
		return m_canvas;
	}
	
	public Dimension getScreenDimension() {
		return SCREEN_DIMENSION;
	}
	
	public List<Object> getObjects() {
		return m_objects;
	}
	
	public int getMouseX() {
		return m_mouseX;
	}
	
	public int getMouseY() {
		return m_mouseY;
	}
	
	public void endGame() {
		timer.stop();
		JOptionPane.showMessageDialog(m_frame, "You lost. Final score: " + m_score);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}
}