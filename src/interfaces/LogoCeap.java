package interfaces;

import java.awt.Color;
import java.awt.Graphics;
import main.InterfaceManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoCeap extends JPanel {

	private final int width;
	private final int height;
	private JLabel img = new JLabel(new ImageIcon(getClass().getResource("LogoCEAP.png")));
	
	public LogoCeap(int w, int h) {
		setSize(w / 6, h / 5);
		this.width = w / 6;
		this.height = h / 5;
		
		setBackground(Color.white);
		
		img.setSize(width, height);
		add(img);
		
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
