package model;

import java.io.IOException;

/**@author Clébson Luiz de Moraes Silva
 * @version 1.01 - Mudança sutil
 * @see -Sprite não implementa a interface movimento, e sim personagem.
 * */
public abstract class Personagem extends Sprite implements Movimento{

	private boolean cima = false, baixo = false, esquerda = false, direita = false;
	private int up, down, left, right;

	public Personagem(String caminho, int aparencia, int columns, int rows, int posX, int posY) throws IOException{
		super(caminho, aparencia, columns, rows, posX, posY);
	}

	public boolean isCima() {return cima;}
	public boolean isBaixo() {return baixo;}
	public boolean isDireita() {return direita;}
	public boolean isEsquerda() {return esquerda;}

	public void setCima(boolean cima) {	this.cima = cima;}
	public void setBaixo(boolean baixo) {this.baixo = baixo;}
	public void setDireita(boolean direita) {this.direita = direita;}
	public void setEsquerda(boolean esquerda) {	this.esquerda = esquerda;}
	
	public int getUp() {return up;}
	public int getDown() {return down;}
	public int getLeft() {return left;}
	public int getRight() {return right;}
	public void setUp(int up) {this.up = up;}
	public void setDown(int down) {this.down = down;}
	public void setLeft(int left) {this.left = left;}
	public void setRight(int right) {this.right = right;}
}