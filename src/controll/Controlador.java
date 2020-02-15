package controll;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import model.Arquivo;
import model.Audio;
import model.Camada;
import model.ColisaoSlime;
import model.Estrelinha;
import model.Fundo;
import model.ImagemPergunta;
import model.PerguntaImagem;
import model.Ranking;
import model.Slime;
import model.XML;
import view.Janela;


/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.01 - Mudança Trivial
 * @see -Foi Somente removida uma chamada do metodo "lerRank();" 
 * e "XML.getInstance().salvar(rank);" sem uso necessário.
 * */
public class Controlador extends Observable{

	private Estados estado;
	
	public void menuInicial() {
		estado = Estados.MENU_INICIAL;
		avisarObservadores();
	}
	
	public void menuAjuda() {
		estado = Estados.MENU_AJUDA;
		avisarObservadores();
	}
	public void menuSobre() {
		estado = Estados.MENU_SOBRE;
		avisarObservadores();
	}
	public void multiPlayer() {
		estado = Estados.MULTI_PLAYER;
		avisarObservadores();
	}
	public void nomeJogadorSingle() {
		estado = Estados.NOME_JOGADOR_SINGLEPLAYER;
		avisarObservadores();
	}
	public void nomeJogadorMulti1() {
		estado = Estados.NOME_JOGADOR_MULTIPLAYER_1;
		avisarObservadores();
	}
	
	public void nomeJogadorMulti2() {
		estado = Estados.NOME_JOGADOR_MULTIPLAYER_2;
		avisarObservadores();
	}
	
	public void fase1() {
		estado = Estados.FASE_1;
		avisarObservadores();
	}
	public void fase2() {
		estado = Estados.FASE_2;
		avisarObservadores();
	}
	public void fase3() {
		estado = Estados.FASE_3;
		avisarObservadores();
	}
	public void fase4() {
		estado = Estados.FASE_4;
		avisarObservadores();
	}
	
	public void eMorreu() {
		estado = Estados.E_MORREU;
		avisarObservadores();
	}
	
	public void ganhouSingle() {
		estado = Estados.MENU_VITORIA_SINGLEPLAYER;
		avisarObservadores();
	}
	
	public void ganhouMulti() {
		estado = Estados.MENU_VITORIA_MULTIPLAYER;
		avisarObservadores();
	}
	

	
	private void avisarObservadores() {
		setChanged();
		notifyObservers(estado);
	}
	
	static int tempo = 0;
	static int mapaX = 0;
	static int mapaY = 0;
	static int escolhaAtual = 0;
			
	static Janela janela;
	static BufferedImage tela;

	static Arquivo arquivos;
	
	static Slime slime;
	static Slime slime2;
	static Camada fase;
	static Camada multi;
	static ColisaoSlime colisaoSlime;
	static ColisaoSlime multiColision;
	static BufferedImage inventario, inventarioMulti;
	static BufferedImage fundo, fundoGameOver2;
	static Fundo fundoMenu, fundoGameOver;

	static int[][] posicoesObjetosSinglePlay;
	static int[][] posicoesEstrela;

	static PerguntaImagem pergunta;
	static ArrayList<int[]> posicoeslist;
	static ArrayList<int[]> posicoesMultiValida;
	static ArrayList<ImagemPergunta> imagensPerguntas ;

	
	static Estrelinha estrelinha;

	static ArrayList<PerguntaImagem> perguntasMulti;

	public Audio getAudioMultiPlayer() {
		return arquivos.getAudioMultiplay();
	}
	
	
	
	public Audio getAudioVitoria() {
		return arquivos.getAudioVitoria();
	}
	
	public Audio getAudioDerrota() {
		return arquivos.getAudioDerrota();
	}
	
	public Audio getAudioMenu() {
		return arquivos.getAudioMenu();
	}
	
	
	public Audio getEasterEgg() {
		return arquivos.getEasterEgg();
	}
	
	public Audio getAudioSingle() {
		return arquivos.getAudioSingle();
	}
	
	
	public Controlador(Janela janela, Slime slime, Slime slime2, Arquivo arquivo) {
		Controlador.janela = janela;
		Controlador.slime = slime;
		Controlador.slime2 = slime2;
		arquivos = arquivo;
	}

	public void controll() {
		fundoMenu = arquivos.getFundoMenu();
		fundoMenu.setDirecao(-1, 0);
		fundo = fundoMenu.getImagem();

		fundoGameOver = arquivos.getFundoGameOver();
		fundoGameOver.setDirecao(-1, 0);
		fundoGameOver2 = fundoGameOver.getImagem();
		posicoesMultiValida = new ArrayList<>();
		inventario = arquivos.getInventario().getImagem();
		inventarioMulti = arquivos.getInventarioMulti().getImagem();
		multi = arquivos.getCamadas().get(arquivos.getCamadas().size() - 1);
		multiColision = arquivos.getColisaoSlime().get(arquivos.getColisaoSlime().size() - 1);
		estrelinha = arquivos.getEstrelinha();
	
		posicoeslist = new ArrayList<>();
		perguntasMulti = new ArrayList<>();
		imagensPerguntas = new ArrayList<>();
		
		lerRank();

		setPosicoesFase1();
		setArrayPosicoesMulti();
		setNovaPerguntasMulti();
		
		
		getAudioMenu().getAudio().loop();
	}

	
	public static Font fonteGame() {
		return arquivos.getFont().getFont();
	}
	
	public static void mudarStatusSlime(int i) { 

		for (Slime slim : arquivos.getSlimes()) {
			if (slim.getStatus() == i) {
				int x = Controlador.slime.getPosX();
				int y = Controlador.slime.getPosY();
				slim.setPosition(x, y);
				Controlador.slime = slim;
				break;
			}
		}
	}

	public static void mudarStatusSlime2(int i) {

		for (Slime slim : arquivos.getSlimes2()) {
			if (slim.getStatus() == i) {
				int x = slime2.getPosX();
				int y = slime2.getPosY();
				slim.setPosition(x, y);
				Controlador.slime2 = slim;
				break;
			}
		}
	}

	public static ArrayList<PerguntaImagem> getPerguntasMulti() {
		return perguntasMulti;
	}

	public static void setNovaPerguntasMulti() {

		perguntasMulti.clear();
		imagensPerguntas.clear();
		Collections.shuffle(arquivos.getPerguntas());
		for (PerguntaImagem per : arquivos.getPerguntas())
			perguntasMulti.add(per);

		Collections.addAll(imagensPerguntas,
				perguntasMulti.get(0).getImagemResposta()[0],
				perguntasMulti.get(0).getImagemResposta()[1],
				perguntasMulti.get(0).getImagemResposta()[2],
				perguntasMulti.get(0).getImagemResposta()[3]);
	}

	public static void setArrayPosicoesMulti() {

		posicoesMultiValida.clear();
		Collections.shuffle(arquivos.getPosicoesMulti());
		for (int i = 0; i < 4; i++)
			posicoesMultiValida.add(arquivos.getPosicoesMulti().get(i));
	}

	private static void setPosicoesEstrela(int[][] posicoesEstrela) {
		Controlador.posicoesEstrela = posicoesEstrela;
	}

	public static void setPosicoesFase1() {
		setNovaPerguntasMulti();
		
		posicoeslist.clear();
		setPosicoesEstrela(arquivos.getPosicoesEstrelaFase1());
		posicoesObjetosSinglePlay = arquivos.getPosicoesPerguntaSingle().get(0);
		
		Collections.addAll(posicoeslist,
				posicoesObjetosSinglePlay[0],
				posicoesObjetosSinglePlay[1],
				posicoesObjetosSinglePlay[2],
				posicoesObjetosSinglePlay[3]);
		Collections.shuffle(posicoeslist);
		colisaoSlime = arquivos.getColisaoSlime().get(0);
		fase = arquivos.getCamadas().get(0);
		Controlador.slime = arquivos.getSlimes().get(0);
		Controlador.slime.setPosition(30, 30);
		while (Controlador.slime.getVidas().size() < 3)
			Controlador.slime.addNovaVida();
	}

	public static void setPosicoesFase2() {
		setNovaPerguntasMulti();
		posicoeslist.clear();
		setPosicoesEstrela(arquivos.getPosicoesEstrelaFase2());
		posicoesObjetosSinglePlay = arquivos.getPosicoesPerguntaSingle().get(1);
		
		Collections.addAll(posicoeslist,
				posicoesObjetosSinglePlay[0],
				posicoesObjetosSinglePlay[1],
				posicoesObjetosSinglePlay[2],
				posicoesObjetosSinglePlay[3]);
		Collections.shuffle(posicoeslist);
		colisaoSlime = arquivos.getColisaoSlime().get(1);
		fase = arquivos.getCamadas().get(1);
		Controlador.slime = arquivos.getSlimes().get(0);
		Controlador.slime.setPosition(30, 30);
	}

	public static void setPosicoesFase3() {
		setNovaPerguntasMulti();
		posicoeslist.clear();
		setPosicoesEstrela(arquivos.getPosicoesEstrelaFase3());
		posicoesObjetosSinglePlay = arquivos.getPosicoesPerguntaSingle().get(2);
	
		Collections.addAll(posicoeslist,
				posicoesObjetosSinglePlay[0],
				posicoesObjetosSinglePlay[1],
				posicoesObjetosSinglePlay[2],
				posicoesObjetosSinglePlay[3]);
		Collections.shuffle(posicoeslist);
		colisaoSlime = arquivos.getColisaoSlime().get(2);
		fase = arquivos.getCamadas().get(2);
		Controlador.slime = arquivos.getSlimes().get(0);
		Controlador.slime.setPosition(30, 30);
	}

	public static void setPosicoesFase4() {
		setNovaPerguntasMulti();
		posicoeslist.clear();
		setPosicoesEstrela(arquivos.getPosicoesEstrelaFase4());
		posicoesObjetosSinglePlay = arquivos.getPosicoesPerguntaSingle().get(3);
		
		Collections.addAll(posicoeslist,
				posicoesObjetosSinglePlay[0],
				posicoesObjetosSinglePlay[1],
				posicoesObjetosSinglePlay[2],
				posicoesObjetosSinglePlay[3]);
		Collections.shuffle(posicoeslist);
		colisaoSlime = arquivos.getColisaoSlime().get(3);
		fase = arquivos.getCamadas().get(3);
		Controlador.slime = arquivos.getSlimes().get(0);
		slime.setPosition(60, 70);
	}

	public static Slime getSlime2() {
		return slime2;
	}

	public static Slime getSlime() {
		return slime;
	}

	@XStreamAlias("rank")
	private static ArrayList<Ranking> rank;

	public static ArrayList<Ranking> getRank() {
		return rank;
	}

	public static void atualizarRank(String nome, String vidas, int tempo) {
		rank.add(new Ranking(nome, vidas, tempo));
		Collections.sort(rank);
		if (rank.size() > 5)
			rank.remove(rank.size() - 1);
		XML.getInstance().salvar(rank);
	}

	public static void lerRank() {
		rank = XML.getInstance().ler();
		Collections.sort(rank);
		
	}

	public class Tempo extends Thread {
		boolean stop = false;
		
		public void run() {
			while (!stop) {
				try {
					sleep(1000);
					tempo += 1;
					if (estado.getEstadoAtual().equals("menuInicial")) {
						stop = true;
						tempo = 0;
					}
				} catch (InterruptedException e) {
				}

			}
		}
	}	
}
