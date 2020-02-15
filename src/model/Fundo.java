package model;

import java.io.IOException;
import java.awt.Graphics2D;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public final class Fundo extends Imagem{
	
	private double x;
	private double y; 
	private double dx; 
	private double dy; 

	public Fundo(String s) throws IOException{ 	
		super(s);
	}
	
	public void update(Graphics2D g) {
		if(x+getImagem().getWidth()<=0) {
			x = getImagem().getWidth();
			y += dy;
		}else if(x+getImagem().getWidth()>getImagem().getWidth()) {
			x = 0;
			y += dy;
		}if(y+getImagem().getHeight()<=0) {
			x = dx;
			y += getImagem().getHeight();
		}else if(y+getImagem().getHeight()>getImagem().getHeight()) {
			x = dx;
			y += 0;
		}else {
			x += dx;
			y += dy;
		}

	
		
		staticUpdate(g);		
	}
	
	public void staticUpdate(Graphics2D g) {
		g.drawImage(getImagem(), (int)x, (int)y, null);
		if(x < 0) 
			g.drawImage(getImagem(), (int) x + getImagem().getWidth(), (int) y, null);
		
		if(x > 0) 
			g.drawImage(getImagem(), (int) x - getImagem().getWidth(), (int) y, null);		
	}
	
	public void setDirecao(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	
}
