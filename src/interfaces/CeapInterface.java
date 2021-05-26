package interfaces;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class CeapInterface extends JPanel{
	
	public static final Font HeaderFont = new Font("Century Gothic", Font.BOLD, 60);
	
	public static final Font LetterFont = new Font("Century Gothic", 0, 18);
	
	public static final String fonte = "Century Gothic";
	
	public abstract void init();
	
	public abstract void definir_eventos();
}
