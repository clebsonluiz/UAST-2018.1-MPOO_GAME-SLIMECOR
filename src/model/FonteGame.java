package model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FonteGame {

	private Font font;
	
	public FonteGame(String caminho) throws FontFormatException, IOException {	
		this.font = Font.createFont(Font.TRUETYPE_FONT, 
				getClass().getResourceAsStream(caminho)).deriveFont(Font.PLAIN, 100);		
	}
	public Font getFont() {
		return font;
	}
	
}
