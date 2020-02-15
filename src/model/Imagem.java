package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Clébson Luiz
 * @version 1.0
 * */
public class Imagem {

	private BufferedImage imagem;

	public Imagem(String caminho) throws IOException {
		imagem = ImageIO.read(getClass().getResourceAsStream(caminho));
	}

	public BufferedImage getImagem() {return imagem;}
}
