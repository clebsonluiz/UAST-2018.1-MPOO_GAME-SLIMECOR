package model;

import java.awt.image.BufferedImage;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public abstract class ColisaoMapa{
	private int mapa[][];
	private int tileWidth;
	private int tileHeight;

	public ColisaoMapa(Camada camada) {
		mapa = camada.getMapa();
		tileWidth = camada.getTileWidth();
		tileHeight = camada.getTileHeight();
	}	
	
	public int[][] getMapa() {return mapa;}
	public int getTileWidth() {return tileWidth;}
	public int getTileHeight() {return tileHeight;}

	public static boolean colisaoComObjeto(BufferedImage a, int ax, int ay,
										   BufferedImage b, int bx, int by) { 
		
		int aX = ax;
		int aY = ay;
		int ladoDireitoA = aX+a.getWidth();
		int ladoEsquerdoA = aX;
		int ladoBaixoA = aY+a.getHeight();
		int ladoCimaA = aY;
				
		int bX = bx;
		int bY = by;
		int ladoDireitoB = bX+b.getWidth();
		int ladoEsquerdoB = bX;
		int ladoBaixoB = bY+b.getHeight();
		int ladoCimaB = bY;
		
		boolean colisao = false;
		
		boolean cDireita = false;
		boolean cBaixo = false;
		boolean cCima = false;
		boolean cEsquerda = false;
		
		if(ladoCimaA<=ladoBaixoB) {cCima = true;}
		if(ladoBaixoA>=ladoCimaB) {cBaixo = true;}
		if(ladoDireitoA>=ladoEsquerdoB) {cDireita = true;}
		if(ladoEsquerdoA<=ladoDireitoB) {cEsquerda = true;}
		
		if(cDireita && cEsquerda && cBaixo && cCima) {colisao = true;}

		return colisao;
	}	
}
