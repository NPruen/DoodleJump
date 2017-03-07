import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Game {
	private JFrame m_frame;
	private Canvas m_canvas;
	
	private static final Dimension SCREEN_DIMENSION = new Dimension(300, 500);
	
	private ArrayList<Object> m_objects;
	
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
			if(!m_keyPressed) {
				m_keyInput = "" + e.getKeyChar();
				System.out.println(m_keyInput);
				m_keyPressed = true;
			}
		}
	};
	
	private ActionListener clock = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			m_canvas.repaint();
		}
	};
	private Timer timer;
	
	private Doodler m_doodler;
	
	public Game() {
		m_doodler = new Doodler(this);
		
		m_objects = new ArrayList<Object>();
		m_objects.add(m_doodler);
		
		m_canvas = new Canvas(SCREEN_DIMENSION, m_objects);
		
		timer = new Timer(20, clock);
		timer.start();
		
		m_frame = new JFrame("Doodle Jump");
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setSize(SCREEN_DIMENSION);
		m_frame.setResizable(false);
		m_frame.add(m_canvas);
		m_frame.addKeyListener(m_keyboard);
		m_frame.setVisible(true);
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
	
	
	public static void main(String[] args) {
		Game game = new Game();
	}
}