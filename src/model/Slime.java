package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public final class Slime extends Personagem{

	public static final int velocidade = 4;

	private int status;
	private Imagem vida;
	private ArrayList<Imagem> vidas;
	
	public Slime(String caminho, int aparencia, int columns, int rows, int posX, int posY, int status, Imagem vida) throws IOException{
		super(caminho, aparencia, columns, rows, posX, posY);
		this.vida = vida;
		this.status = status;
		this.vidas = new ArrayList<>();
		Collections.addAll(this.vidas, vida, vida, vida);
	}

	public ArrayList<Imagem> getVidas() {return vidas;}
	public int getStatus() {return status;}
	public void addNovaVida() {vidas.add(vida);}
	public void setStatus(int status) {this.status = status;}
	public void setVidas(ArrayList<Imagem> vidas) {this.vidas = vidas;}

	public void setPosition(int x, int y) {
		setPosX(x);
		setPosY(y);
	}
	
	public void cima() {
		setPosY(getPosY() - velocidade);
		switch (getUp()) {
			case 0:
				setAparencia(0);
				break;
			case 1:
				setAparencia(4);
				break;
			case 2:
				setAparencia(8);
				break;
			case 3:
				setAparencia(4);
				break;
		}
		if (getUp() == 3)
			setUp(0);
		else
			setUp(getUp()+1);
	}
	
	public void baixo() {
		setPosY(getPosY() + velocidade);
		switch (getDown()) {
			case 0:
				setAparencia(2);
				break;
			case 1:
				setAparencia(6);
				break;
			case 2:
				setAparencia(10);
				break;
			case 3:
				setAparencia(6);
				break;
		}
		if (getDown() == 3)
			setDown(0);
		else
			setDown(getDown()+1);
	}
	
	public void esquerda() {
		setPosX(getPosX() - velocidade);
		switch (getLeft()) {
			case 0:
				setAparencia(3);
				break;
			case 1:
				setAparencia(7);
				break;
			case 2:
				setAparencia(11);
				break;
			case 3:
				setAparencia(7);
				break;
		}
		if (getLeft() == 3)
			setLeft(0);
		else
			setLeft(getLeft()+1);
	}
	
	public void direita() {
		setPosX(getPosX() + velocidade);
		switch (getRight()) {
			case 0:
				setAparencia(1);
				break;
			case 1:
				setAparencia(5);
				break;
			case 2:
				setAparencia(9);
				break;
			case 3:
				setAparencia(5);
				break;
		}
		if (getRight() == 3)
			setRight(0);
		else
			setRight(getRight()+1);
	}
	
	public void animacao() {
		if (isCima()) 
			cima();
		else
		if (isBaixo()) 
			baixo();
		else
		if (isDireita()) 
			direita();
		else
		if (isEsquerda())
			esquerda();	
	}	
}
