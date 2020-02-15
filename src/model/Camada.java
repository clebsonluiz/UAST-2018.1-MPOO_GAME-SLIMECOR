package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

/**
 * Modelo com base em uma das três classes do Prof. de MPOO Richarlyson
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * @see https://sites.google.com/site/profricodemery/mpoo
 * */
public class Camada{
	private int mapa[][];
	private int tileWidth, tileHeight;
	private int mapaWidth, mapaHeight;
	private BufferedImage camada;
	private BufferedImage tileSet;

	public Camada(int mapaWidth, int mapaHeight,
				  int tileWidth, int tileHeight,
				  String img, String arquivo) throws IOException, FileNotFoundException{
		this.mapaWidth=mapaWidth;
		this.mapaHeight=mapaHeight;
		this.tileWidth=tileWidth;
		this.tileHeight=tileHeight;
		mapa = new int[mapaHeight][mapaWidth];
		mapa = carregaMatriz(mapa, arquivo);
		
		tileSet = ImageIO.read(getClass().getResourceAsStream(img));
		montarMapa(mapaWidth*tileWidth, mapaHeight*tileHeight);
	}

	private int[][] carregaMatriz(int[][] matriz, String arquivo) throws IOException,
																  FileNotFoundException{
		ArrayList<String> linhasMatrizCamada = new ArrayList<String>();
		InputStream is = getClass().getResourceAsStream (arquivo);
		BufferedReader br = new BufferedReader (new InputStreamReader (is));   
		String linha="";	

			while ((linha = br.readLine()) != null){
				linhasMatrizCamada.add(linha);
			}
			int j = 0;
			for (int i = 0; i < linhasMatrizCamada.size(); i++) {
				StringTokenizer token = new StringTokenizer(linhasMatrizCamada.get(i), ",");

				while (token.hasMoreElements()) {
					matriz[i][j] = Integer.parseInt(token.nextToken());
					j++;
				}
				j = 0;
			}	
		return matriz;
	}

	public void montarMapa(int lar, int alt) {

		camada = new BufferedImage(lar, alt, BufferedImage.TYPE_4BYTE_ABGR);
		camada.createGraphics();

		int tile;
		int tileRow;
		int tileCol;
		int colunasTileSet=tileSet.getWidth()/tileWidth;
		
		for (int i = 0; i < mapaHeight; i++) {
			for (int j = 0; j < mapaWidth; j++) {
				tile = (mapa[i][j]);
				tileRow = (tile / (colunasTileSet)) | 0;
				tileCol = (tile % (colunasTileSet)) | 0;
				camada.getGraphics().drawImage(tileSet, (j * tileHeight), (i * tileWidth), (j * tileHeight) + tileHeight,
						(i * tileWidth) + tileWidth, (tileCol * tileHeight), (tileRow * tileWidth),
						(tileCol * tileHeight) + tileHeight, (tileRow * tileWidth) + tileWidth, null);
			}
		}
	}
	
	public int[][] getMapa() {return mapa;}
	public int getMapaWidth() {return mapaWidth;}
	public int getTileWidth() {return tileWidth;}
	public int getMapaHeight() {return mapaHeight;}
	public int getTileHeight() {return tileHeight;}
	public BufferedImage getCamada() {return camada;}
	public BufferedImage getTileSet() {return tileSet;}

}