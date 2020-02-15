package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.01 - Mudança sutil.
 * @see Sprite não implementa a interface movimento, e sim personagem.
 * */
public abstract class Sprite{
	
	private int aparencia;
	private int posX, posY;
	private int width, height;
	private BufferedImage[] sprites;
	private BufferedImage spriteSheet;
	
	public Sprite(String caminho, 
				  int aparencia, int columns, 
				  int rows, int posX, int posY) throws IOException {
		
		spriteSheet = ImageIO.read(getClass().getResourceAsStream(caminho));
		this.aparencia = aparencia;

		this.height = spriteSheet.getHeight() / rows;
		this.width = spriteSheet.getWidth() / columns;
		this.posX = posX;
		this.posY = posY;

		sprites = new BufferedImage[columns * rows];
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
	}
	
	public int getPosX() {return posX;}
	public int getPosY() {return posY;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getAparencia() {return aparencia;}
	public void setPosX(int posX) {this.posX = posX;}
	public void setPosY(int posY) {this.posY = posY;}
	public void setAparencia(int aparencia) {this.aparencia = aparencia;}
	public BufferedImage[] getSprites() {return sprites;}
	

	
}