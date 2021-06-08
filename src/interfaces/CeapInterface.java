package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.CeapWindow;

public abstract class CeapInterface extends JPanel{
	
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public static final int w = CeapWindow.width;
	public static final int h = CeapWindow.height;
	public static final Color cinzaClaro = new Color(0, 0, 0, 125);
	public static final Color orange = new Color(241,149,0);
	public static final Color green = new Color(126, 185, 40);
	public static final Color blue = new Color(3, 175, 230);
	public static final Color purple = new Color(90, 80, 157);
	
	public static final Font HeaderFont = new Font("Century Gothic", Font.BOLD, 60);
	
	public static final Font SemiHeaderFont = new Font("Century Gothic", 0, 40);
	
	public static final Font LetterFont = new Font("Century Gothic", 0, 18);
	
	public static final Font TableFont = new Font("Century Gothic", 0, 16);
	
	public static final String fonte = "Century Gothic";
	
	public abstract void init();
	
	public abstract void definir_eventos();
}
