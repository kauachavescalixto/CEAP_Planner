package interfaces;

import java.awt.Color;
import java.awt.Graphics;
import main.InterfaceManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogoCeap extends JPanel {

	private final int width;
	private final int height;

	public LogoCeap(int w, int h) {
		setSize(w / 6, h / 5);
		this.width = w / 6;
		this.height = h / 5;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int w = this.width;
		int h = this.height;

		g.setColor(InterfaceManager.blue);
		int rounder = w - h;
		g.fillOval(0, 0,
				(int) ((w - rounder) / 5f),
				(int) (h / 5f)
				);

	}

	/*public static void main(String[] args) {

		JFrame frame = new JFrame("");
		frame.getContentPane().add(new LogoCeap(1152, 648));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		frame.setSize(246, 200);
	}*/

}
