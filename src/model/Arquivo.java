package model;

import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public class Arquivo {

	private  Fundo fundoMenu, fundoGameOver;	
	private  Imagem inventario, inventarioMulti;
	private  Imagem vida;
	private  Slime slimeRoxo;
	private  Slime slimeAmarelo;
	private  Slime slimeAzul;
	private  Slime slimeVerde;
	private  Slime slimeVermelho;
	
	
	private  Slime slimeRoxo2;
	private  Slime slimeVerde2;
	private  Slime slimeAmarelo2;
	private  Slime slimeAzul2;
	private  Slime slimeVermelho2;
	
	
	private  Camada fase1, fase2, fase3, fase4,multi;
	private  ColisaoSlime fase1Colisao, fase2Colisao, fase3Colisao,fase4Colisao, multiColisao;
	
	private ArrayList<PerguntaImagem> perguntas;
	
	private ArrayList<Slime> slimes, slimes2;
	private ArrayList<Camada> camadas;
	private ArrayList<ColisaoSlime> colisaoSlime;
	private Estrelinha estrelinha;
	
	private int[][] posicoesEstrela = {{820,110},{110,110},{400,140},{630,510},{80,510}};	
	private int[][] posicoesEstrela2 = {{490,380},{90,520},{800,470},{460,60},{570,180}};	
	private int[][] posicoesEstrela3 = {{520,520},{660,510},{830,460},{370,480},{110,120},{190,310}};	
	private int[][] posicoesEstrela4 = {{150,460},{290,560},{580,230},{140,330}};	
	
	private static int[][] posicoesPergunta1 = {{820,300},{800,540},{280,520},{330,340}};
	private static int[][] posicoesPergunta2 = {{340,270},{360,530},{830,250},{840,60}};
	private static int[][] posicoesPergunta3 = {{330,110},{770,110},{860,260},{500,400}};
	private static int[][] posicoesPergunta4 = {{80,240},{720,520},{300,300},{660,320}};
	
	private ArrayList<int[][]> posicoesPerguntaSingle;
	
	static final int[][] posicoesMultiplayer = {{70,60},{70,380},{540,380},{540,60},
			{300,60},{300,400},{70,240},{540,240},
			{540,130},{440,370},{100,370},{100,100},
			{300,70},{200,220},{200,390},{110,300},
			{270,300},{440,300},{370,150},{170,100},
			{170,200},{300,200},{300,100},{400,150},
			{350,200},{230,120},{200,370},{440,300}};
	
	private ArrayList<int[]> posicoesMulti;
	
	private FonteGame fonteGame;
	
	public FonteGame getFont() {
		return fonteGame;
	}
	
	public Audio getAudioMultiplay() {
		return audioMulti;
	}
	
	public Audio getAudioVitoria() {
		return audioVit;
	}
	
	public Audio getAudioDerrota() {
		return audioDer;
	}
	
	public Audio getAudioMenu() {
		return audioMenu;
	}
	
	
	public Audio getEasterEgg() {
		return audiosEasterEggs;
	}
	
	public Audio getAudioSingle() {
		return audioSingle;
	}
	
	private Audio audioMulti, audioVit, audioDer, audioMenu, audioSingle ;
	private Audio audiosEasterEggs;
	
	public Arquivo() throws IOException, FileNotFoundException, FontFormatException{
		
		audioMulti = new Audio("Power Bots Loop.wav");
		audioVit = new Audio("Pixel-Drama.wav");
		audioDer = new Audio("musica triste violino meme.wav");
		audioMenu = new Audio("Fantasy Game Loop.wav");
		audioSingle = new Audio("Off Limits.wav");
		
		
		audiosEasterEggs = new Audio("Proerd_em_8_Bits.wav");
		
		
		
		
		fonteGame= new FonteGame("/_arquivos/game_over.ttf");
		
		
		fundoMenu = new Fundo("/_arquivos/Menu.png");
		fundoGameOver = new Fundo("/_arquivos/files/FundoGameOver.png");
		
		inventario = new Imagem("/_arquivos/files/Inventario.png");
		inventarioMulti = new Imagem("/_arquivos/files/InventarioMulti.png");
		vida = new Imagem("/_arquivos/files/Vida.png");	
		slimeRoxo = new Slime("/_arquivos/files/slimeRoxo.png", 0, 3, 4, 0, 0, -1, vida);
		slimeVerde = new Slime("/_arquivos/files/slimeVerde.png", 0, 3, 4, 0, 0, 0, vida);
		slimeAmarelo = new Slime("/_arquivos/files/slimeAmarelo.png", 0, 3, 4, 0, 0, 1, vida);
		slimeAzul = new Slime("/_arquivos/files/slimeAzul.png", 0, 3, 4, 0, 0, 2, vida);
		slimeVermelho = new Slime("/_arquivos/files/slimeVermelho.png", 0, 3, 4, 0, 0, 3, vida);	
		
		
		slimeRoxo2 = new Slime("/_arquivos/files/slimeRoxo.png", 0, 3, 4, 0, 0, -1, vida);
		slimeVerde2 = new Slime("/_arquivos/files/slimeVerde.png", 0, 3, 4, 0, 0, 0, vida);
		slimeAmarelo2 = new Slime("/_arquivos/files/slimeAmarelo.png", 0, 3, 4, 0, 0, 1, vida);
		slimeAzul2 = new Slime("/_arquivos/files/slimeAzul.png", 0, 3, 4, 0, 0, 2, vida);
		slimeVermelho2 = new Slime("/_arquivos/files/slimeVermelho.png", 0, 3, 4, 0, 0, 3, vida);	
		
		
		
		fase1 = new Camada( 60,20, 32, 32, "/_arquivos/files/parede5cores.png", "/_arquivos/files/Fases/TileFase1.txt");
		fase2 = new Camada(60,20,  32, 32, "/_arquivos/files/parede5cores.png", "/_arquivos/files/Fases/TileFase2.txt");
		fase3 = new Camada( 60,20, 32, 32, "/_arquivos/files/parede5cores.png", "/_arquivos/files/Fases/TileFase3.txt");
		fase4 = new Camada( 60,20, 32, 32, "/_arquivos/files/parede5cores.png", "/_arquivos/files/Fases/TileFase4.txt");
		multi = new Camada( 20,15, 32, 32, "/_arquivos/files/parede5cores.png", "/_arquivos/files/Fases/TileMulti.txt");

		fase1Colisao = new ColisaoSlime(fase1);
		fase2Colisao = new ColisaoSlime(fase2);
		fase3Colisao = new ColisaoSlime(fase3);
		fase4Colisao = new ColisaoSlime(fase4);
		multiColisao = new ColisaoSlime(multi);
		
		
		perguntas = new ArrayList<>();
		Collections.addAll(perguntas,new PerguntaImagem("Choose the ;yellow colored ;ball",
				new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarela.png", 1),
				new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzul.png", 2),
				new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVerde.png", 0),
				new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVermelha.png", 3)),
				
				new PerguntaImagem("Choose the ;red color ;triangle",
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVermelhoCentro.png", 3),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVerdeCentro.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAmareloCentro.png", 1)),
				
				new PerguntaImagem("Choose the ;retangle; with center ;color blue",
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAmareloCentro.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro2.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;yellow triangle",
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAmareloCentro.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVerdeCentro.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;figure with;four ;vertices",
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarelaFaixaVermelha.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;blue ball ;with the ;green line",
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzulFaixaVerde.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzul.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVermelha.png", 3),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarelaFaixaAzul.png", 1)),
				
				new PerguntaImagem("Choose the ;black line; retangle",
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAmareloCentro.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro2.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro2.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;ball with the ;blue line",
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarelaFaixaAzul.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarela.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzulFaixaVerde.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVermelha.png", 3)),
				
				new PerguntaImagem("Choose the ;triangle with ;the yellow ;center",
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAmareloCentro.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro2.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;figure with ;tree ;vertices",
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloVerdeCentro.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzulFaixaVerde.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro2.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;ball with ;two yellow ;lines",
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVermelha.png", 3),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzulFaixaVerde.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro2.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarelaFaixaVermelha.png", 1)),
				
				new PerguntaImagem("Choose the ;triangle ;with the ;blue center",
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/trianguloAmareloCentro.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro2.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;retangle ;with the ;yellow line",
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro2.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVerdeCentro.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVermelhoCentro.png", 3)),
				
				new PerguntaImagem("Choose the ;red color ball",
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVermelha.png", 3),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarela.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzul.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVerde.png", 0)),
				
				new PerguntaImagem("Choose the ;green color ;ball",
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVerde.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAmarela.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaAzul.png", 2),						
						new ImagemPergunta("/_arquivos/ImagensGenericas/bolaVermelha.png", 3)),
				
				new PerguntaImagem("Choose the ;retangle ;with yellow ;center",
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAmareloCentro.png", 1),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro.png", 2),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoAzulCentro2.png", 0),
						new ImagemPergunta("/_arquivos/ImagensGenericas/quadradoVermelhoCentro2.png", 3)));
		
		
		
		estrelinha = new Estrelinha("/_arquivos/estrelinha210x60.png", 0, 7, 2, 0, 0);
		
		
		
		slimes = new ArrayList<>();
		slimes2 = new ArrayList<>();
		Collections.addAll(slimes, slimeRoxo,slimeAmarelo, slimeAzul, slimeVerde, slimeVermelho);
		Collections.addAll(slimes2, slimeRoxo2,slimeAmarelo2, slimeAzul2, slimeVerde2, slimeVermelho2);
		
		camadas = new ArrayList<>();
		Collections.addAll(camadas, fase1, fase2, fase3, fase4, multi);
		
		colisaoSlime = new ArrayList<>();
		Collections.addAll(colisaoSlime, fase1Colisao, fase2Colisao, fase3Colisao, fase4Colisao, multiColisao);	
	
		posicoesMulti = new ArrayList<>();
		
		for(int[] i: posicoesMultiplayer)
			posicoesMulti.add(i);	
		
		posicoesPerguntaSingle = new ArrayList<>();
		Collections.addAll(posicoesPerguntaSingle,
								posicoesPergunta1,
								posicoesPergunta2,
								posicoesPergunta3,
								posicoesPergunta4);
		
		
		
	}


	

	public ArrayList<int[][]> getPosicoesPerguntaSingle(){
		return posicoesPerguntaSingle;
	}

	public int[][] getPosicoesEstrelaFase1() {
		return posicoesEstrela;
	}
	
	public int[][] getPosicoesEstrelaFase2() {
		return posicoesEstrela2;
	}
	
	public int[][] getPosicoesEstrelaFase3() {
		return posicoesEstrela3;
	}
	
	public int[][] getPosicoesEstrelaFase4() {
		return posicoesEstrela4;
	}
	
	public Fundo getFundoGameOver() {
		return fundoGameOver;
	}
	
	public ArrayList<PerguntaImagem> getPerguntas() {
		return perguntas;
	}

	public ArrayList<int[]> getPosicoesMulti() { //TODO
		return posicoesMulti;
	}

	public Estrelinha getEstrelinha() {
		return estrelinha;
	}
	
	

	public Fundo getFundoMenu() {return fundoMenu;}


	public Imagem getInventario() {return inventario;}

	public Imagem getInventarioMulti() {return inventarioMulti;}


	public Imagem getVida() {return vida;}


	public ArrayList<Slime> getSlimes() {return slimes;}

	public ArrayList<Slime> getSlimes2() {return slimes2;}


	public ArrayList<Camada> getCamadas() {return camadas;}


	public ArrayList<ColisaoSlime> getColisaoSlime() {return colisaoSlime;}
	
	
}
