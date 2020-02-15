package model;

import java.io.IOException;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public final class ImagemPergunta extends Imagem{

	private int atributo;
	
	public ImagemPergunta(String caminho, int atributo) throws IOException{
		super(caminho);
		this.atributo = atributo;
	}

	public int getAtributo() {return atributo;}	
}
