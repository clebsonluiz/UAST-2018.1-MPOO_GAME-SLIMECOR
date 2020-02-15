package model;

import java.io.IOException;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public class Estrelinha extends Personagem{

	private int left;
	private int right;

	public Estrelinha(String caminho, int aparencia, int columns, int rows, int posX, int posY) throws IOException {
		super(caminho, aparencia, columns, rows, posX, posY);
	}
	
	public void cima() {}
	public void baixo() {}
	
	public void esquerda() {
		
		switch (left) {
		case 0:
			setAparencia(0);
			break;
		case 1:
			setAparencia(2);
			break;
		case 2:
			setAparencia(4);
			break;
		case 3:
			setAparencia(6);
			break;
		case 4:
			setAparencia(8);
			break;
		case 5:
			setAparencia(10);
			break;
		case 6:
			setAparencia(12);
			break;
		}
		if (left == 6)
			left = 0;
		else
			left++;
	}
	public void direita() {
		switch (right) {
		case 0:
			setAparencia(1);
			break;
		case 1:
			setAparencia(3);
			break;
		case 2:
			setAparencia(5);
			break;
		case 3:
			setAparencia(7);
			break;
		case 4:
			setAparencia(9);
			break;
		case 5:
			setAparencia(11);
			break;
		case 6:
			setAparencia(13);
			break;
		}
		if (right == 6)
			right = 0;
		else
			right++;
	}
	public void animacao(boolean b) {
		
		if(b)
			esquerda();
		else
			direita();
		
	}

}
