package controll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.CampoVazioException;
import model.CamposIguaisException;
import model.ColisaoSlime;
import model.ImagemPergunta;
import model.Imagem;
import model.Slime;
import view.Janela;
import view.Mensagem;


/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.1 - Correção de Bug
 * @see -Mudança sutil na função passarDeFase(), aparentemente
 *  poderia ocorrer um bug ao zerar o jogo e 
 *  ele não aparecer a tela de vitoria.
 * 
 * */
public class ControladorControles extends Thread implements KeyListener, Observer{

	Estados estado;
	
	private Janela janela;
	private static ImagemPergunta respostaCerta;
	private Slime slime;
	public boolean multi = false;
	private  ArrayList<Integer> teclas;
	Controlador controlador;
	
	public ControladorControles(Janela janela,Controlador controlador, Slime slime,ArrayList<Integer> k, boolean b){
		this.janela = janela;	
		this.slime = slime;
		this.controlador = controlador;
		this.teclas = k;
		multi = b;
	}
	public void controll() {
		janela.addKeyListener(this);
		start();
	}
	
	public void keyPressed(KeyEvent k) {
		
		if(estado.getEstadoAtual().equals("menuInicial")&&!multi) {
			controlador.getAudioSingle().getAudio().stop();
			controlador.getEasterEgg().getAudio().stop();
			
			controlador.getAudioDerrota().getAudio().stop();
			controlador.getAudioVitoria().getAudio().stop();
			controlador.getAudioMultiPlayer().getAudio().stop();
			menu(k);
		}
		else if(estado.getEstadoAtual().equals("nomeSingle")&&!multi)
			menuNome(k);
		
		else if(estado.getEstadoAtual().equals("nomeMulti1")&&!multi)
			menuNome1(k);
		else if(estado.getEstadoAtual().equals("nomeMulti2")&&!multi)
			menuNome2(k);
		else if(estado.getEstadoAtual().equals("multi")) {
			movimento(k, Controlador.multiColision, slime);
		}
		else if(estado.getEstadoAtual().equals("ajuda")&&!multi)
			menuAjuda(k);
		else if(estado.getEstadoAtual().equals("sobre")&&!multi)
			menuSobre(k);
		
		else if(estado.getEstadoAtual().equals("fase1")
				||estado.getEstadoAtual().equals("fase2")
				||estado.getEstadoAtual().equals("fase3")
				||estado.getEstadoAtual().equals("fase4")) {
			
			movimento(k, Controlador.colisaoSlime, slime);
		}
		else if(estado.getEstadoAtual().equals("vitoriaSingle")) {
			
			if(k.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
				controlador.menuInicial();
			}
		}
		
		else if(estado.getEstadoAtual().equals("vitoriaMulti")) {
			
			if(k.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
				controlador.menuInicial();
			}
		}else if(estado.getEstadoAtual().equals("eMorreu")) {
			
			if(k.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
				controlador.menuInicial();
			}
		}
	}

	
	public void menu(KeyEvent k) {
		
		if(k.getKeyCode()==teclas.get(1)) {
			Controlador.escolhaAtual--;
			if(Controlador.escolhaAtual==-1) Controlador.escolhaAtual = 4;
		}
		if(k.getKeyCode()==teclas.get(2)) {
			Controlador.escolhaAtual++;
			if(Controlador.escolhaAtual== 5) Controlador.escolhaAtual = 0;
		}
		if(k.getKeyCode()==teclas.get(5)) System.exit(0); 
		if(k.getKeyCode()==KeyEvent.VK_ENTER) {
			
			if(Controlador.escolhaAtual==0) {
				
				controlador.nomeJogadorSingle();
				ControladorGraphics.nomeJogador1 = "";
			}
			if(Controlador.escolhaAtual==1) {
				controlador.nomeJogadorMulti1();
				ControladorGraphics.nomeJogador1 = "";
			}
			if(Controlador.escolhaAtual==2) {
				controlador.menuAjuda(); 
				
			}
			if(Controlador.escolhaAtual==3) {
				controlador.menuSobre();
				
			}
			if(Controlador.escolhaAtual==4) System.exit(0);
			
		}
		
	}
	
	
	public String menuNomeJogador(KeyEvent k, String jogador) {
		if(k.getKeyCode()==KeyEvent.VK_ESCAPE) {
			controlador.menuInicial();
		}else
		if(k.getKeyCode()==KeyEvent.VK_BACK_SPACE&&!(jogador.equals(""))) {
			jogador = jogador.substring(0, jogador.length()-1);
		}else 
			if(k.getKeyCode()!=KeyEvent.VK_BACK_SPACE
			 &&k.getKeyCode()!=KeyEvent.VK_ENTER
			 &&k.getKeyCode()!=KeyEvent.VK_SPACE
			 &&k.getKeyCode()!=KeyEvent.VK_SHIFT
			 &&k.getKeyCode()!=KeyEvent.VK_CONTROL
			 &&k.getKeyCode()!=KeyEvent.VK_ALT
			 &&k.getKeyCode()!=KeyEvent.VK_UP
			 &&k.getKeyCode()!=KeyEvent.VK_DOWN
			 &&k.getKeyCode()!=KeyEvent.VK_LEFT
			 &&k.getKeyCode()!=KeyEvent.VK_RIGHT
			 &&k.getKeyCode()!=KeyEvent.VK_TAB
			 &&jogador.length()<8
			 &&KeyEvent.getKeyText(k.getKeyCode()).matches("^[a-zA-Z]*$")
			 ||KeyEvent.getKeyText(k.getKeyCode()).matches("^[0-9]*$")) {
				
			jogador += KeyEvent.getKeyText(k.getKeyCode());
		}
		if(k.getKeyCode()==KeyEvent.VK_SPACE) {
			jogador +=" ";
		}
		
		return jogador;
	}
	
	public void menuNome(KeyEvent k) {
		ControladorGraphics.nomeJogador1 = menuNomeJogador(k,
										ControladorGraphics.nomeJogador1);
		
		if(k.getKeyCode()==teclas.get(0)) {
			try {
				
			if(ControladorGraphics.nomeJogador1.equals("")) 
				throw new CampoVazioException();
				
			controlador.fase1();
			Controlador.setPosicoesFase1();
			this.slime = Controlador.getSlime();
		    controlador. new Tempo().start();
			
		    
		    
		    controlador.getAudioMenu().getAudio().stop();
		    
		   
		    if(ControladorGraphics.nomeJogador1.equalsIgnoreCase("Proerd"))
		    	controlador.getEasterEgg().getAudio().loop();
		    else 
		    	controlador.getAudioSingle().getAudio().loop();
		    
		    
		    
		    
			} catch (CampoVazioException e) {
				
				Mensagem.exibirMensagem(e.getMessage(), "Opa meu Consagrado calma aí!");
				
			}
			
			
		}
	}
	public void menuNome1(KeyEvent k) {
		ControladorGraphics.nomeJogador1 = menuNomeJogador(k, ControladorGraphics.nomeJogador1);
		if(k.getKeyCode()==teclas.get(0)) {
			try {
				
			
			if(ControladorGraphics.nomeJogador1.equals("")) throw new CampoVazioException();
			
			controlador.nomeJogadorMulti2();
			ControladorGraphics.nomeJogador2 = "";
			
			Controlador.getSlime().setPosition(janela.getWidth()/3, janela.getHeight()/2);
			while(Controlador.getSlime().getVidas().size()<3)
				Controlador.getSlime().addNovaVida();
			
			} catch(CampoVazioException e) {
				Mensagem.exibirMensagem(e.getMessage(), "CALMA AÍ MEU CONSAGRADO!");
			}
			
		}
	}
	
	public void menuNome2(KeyEvent k) {
		ControladorGraphics.nomeJogador2 = menuNomeJogador(k, ControladorGraphics.nomeJogador2);
		if(k.getKeyCode()==teclas.get(0)) {
			
			try {
			
				if(ControladorGraphics.nomeJogador2.equals("")) throw new CampoVazioException();
				if(ControladorGraphics.nomeJogador2.
						equals(ControladorGraphics.nomeJogador1)) throw new CamposIguaisException();
				
			controlador.multiPlayer();
			Controlador.setNovaPerguntasMulti();
			Controlador.getSlime2().setPosition(janela.getWidth()/3, janela.getHeight()/2);
			while(Controlador.getSlime2().getVidas().size()<3)
				Controlador.getSlime2().addNovaVida();
		   
		    controlador.new Tempo().start();
		    
		    controlador.getAudioMenu().getAudio().stop();
		    controlador.getAudioMultiPlayer().getAudio().loop();
		    
		} catch(CampoVazioException | CamposIguaisException e) {
			Mensagem.exibirMensagem(e.getMessage(), "CALMA AÍ MEU CONSAGRADO!");
		} 
		   
		}
	}
	
	public void menuAjuda(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_ESCAPE) 
			controlador.menuInicial();
	}
	public void menuSobre(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_ESCAPE) 
			controlador.menuInicial();
	}
	
	
	
	
	public void acertoSingle(ColisaoSlime colisaoSlime, Slime slime) {
		for(int i = 0; i<Controlador.imagensPerguntas.size(); i++) {
			if(ColisaoSlime.colisaoComObjeto(slime.getSprites()[slime.getAparencia()],
					slime.getPosX()+Controlador.mapaX,
					slime.getPosY()+Controlador.mapaY,
										Controlador.imagensPerguntas.get(i).getImagem(), Controlador.posicoeslist.get(i)[0],Controlador.posicoeslist.get(i)[1]))
			{
				
				int aparencia = slime.getAparencia();
				ArrayList<Imagem> vidas = slime.getVidas();
				Controlador.mudarStatusSlime(Controlador.imagensPerguntas.get(i).getAtributo());
				slime  = Controlador.getSlime();
				slime.setAparencia(aparencia);
				slime.setVidas(vidas);
				
				if(slime.getStatus()==respostaCerta.getAtributo()) {
					Controlador.tempo = Controlador.tempo - 10;
					Controlador.imagensPerguntas.remove(i);
					Controlador.posicoeslist.remove(i);	
					respostaCerta = null;
				}
				else {
					slime.getVidas().remove(0);
					Controlador.imagensPerguntas.remove(i);
					Controlador.posicoeslist.remove(i);
					if(slime.getVidas().size()<=0) {
						ControladorGraphics.setNome(ControladorGraphics.nomeJogador1);
						ControladorGraphics.setVida(Controlador.getSlime().getVidas().size());
						ControladorGraphics.setTempo(Controlador.tempo);
						controlador.eMorreu();
						controlador.getAudioSingle().getAudio().stop();
						controlador.getEasterEgg().getAudio().stop();
						controlador.getAudioDerrota().getAudio().loop();
					}
					respostaCerta = null;
					
				}
				this.slime = slime;
				break;
			}
		}
	}
	
	public void acertoMulti(ColisaoSlime colisaoSlime, Slime slime) {
		for(int i = 0; i<Controlador.imagensPerguntas.size(); i++) {
			if(ColisaoSlime.colisaoComObjeto(slime.getSprites()[slime.getAparencia()],
					slime.getPosX()+Controlador.mapaX,
					slime.getPosY()+Controlador.mapaY,
					Controlador.imagensPerguntas.get(i).getImagem(), Controlador.posicoesMultiValida.get(i)[0],Controlador.posicoesMultiValida.get(i)[1]))
			{
				if(Controlador.getSlime()==slime) {
				
					int aparencia = slime.getAparencia();
					ArrayList<Imagem> vidas = slime.getVidas();
					Controlador.mudarStatusSlime(Controlador.getPerguntasMulti().get(i).getImagemResposta()[i].getAtributo());
					slime  = Controlador.getSlime();
					slime.setAparencia(aparencia);
					slime.setVidas(vidas);
				
					if(slime.getStatus()==respostaCerta.getAtributo()) { 
						if(slime.getVidas().size()<3)
							slime.addNovaVida();
						
						Controlador.getSlime2().getVidas().remove(0);
						Controlador.getPerguntasMulti().remove(0);
						Controlador.setNovaPerguntasMulti();
						Controlador.setArrayPosicoesMulti();
						respostaCerta=null;
						if(Controlador.getSlime2().getVidas().size()==0) {
							ControladorGraphics.setNome(ControladorGraphics.nomeJogador1);
							ControladorGraphics.setVida(Controlador.getSlime().getVidas().size());
							ControladorGraphics.setTempo(Controlador.tempo);
							controlador.ganhouMulti();
							controlador.getAudioVitoria().getAudio().loop();
						}
					}
					else {
						Controlador.getSlime().getVidas().remove(0);
						Controlador.imagensPerguntas.remove(i);
						Controlador.posicoesMultiValida.remove(i);
						if(Controlador.getSlime().getVidas().size()==0) {
							ControladorGraphics.setNome(ControladorGraphics.nomeJogador2);
							ControladorGraphics.setVida(Controlador.getSlime2().getVidas().size());
							ControladorGraphics.setTempo(Controlador.tempo);
							controlador.ganhouMulti();
							controlador.getAudioVitoria().getAudio().loop();
						}
					}
					this.slime = slime;
				break;
				
				}else if(Controlador.getSlime2()==slime) {
				
					int aparencia = slime.getAparencia();
					ArrayList<Imagem> vidas = slime.getVidas();
					Controlador.mudarStatusSlime2(Controlador.imagensPerguntas.get(i).getAtributo());
					slime  = Controlador.getSlime2();
					slime.setAparencia(aparencia);
					slime.setVidas(vidas);
				
					if(slime.getStatus()==respostaCerta.getAtributo()) { 
						if(slime.getVidas().size()<3)
							slime.addNovaVida();
						Controlador.getSlime().getVidas().remove(0);
						Controlador.getPerguntasMulti().remove(0);					
						Controlador.setNovaPerguntasMulti();
						Controlador.setArrayPosicoesMulti();
						respostaCerta=null;
						if(Controlador.getSlime().getVidas().size()==0) {
							ControladorGraphics.setNome(ControladorGraphics.nomeJogador2);
							ControladorGraphics.setVida(Controlador.getSlime2().getVidas().size());
							ControladorGraphics.setTempo(Controlador.tempo);
							controlador.ganhouMulti();
							controlador.getAudioVitoria().getAudio().loop();
						}
						
					}
					else {
						Controlador.getSlime2().getVidas().remove(0);
						Controlador.imagensPerguntas.remove(i);
						Controlador.posicoesMultiValida.remove(i);
						if(Controlador.getSlime2().getVidas().size()==0) {
							ControladorGraphics.setNome(ControladorGraphics.nomeJogador1);
							ControladorGraphics.setVida(Controlador.getSlime().getVidas().size());
							ControladorGraphics.setTempo(Controlador.tempo);
							controlador.ganhouMulti();
							controlador.getAudioVitoria().getAudio().loop();
						}
						
					}
					this.slime = slime;
					break;
				}
				
				
			}
		}
	}
	
	
	public void movimento(KeyEvent k, ColisaoSlime colisaoSlime, Slime slime) {	
		
		if (k.getKeyCode() == teclas.get(0)) {

			if(respostaCerta == null)respostaCerta = Controlador.imagensPerguntas.get(0);
			
			if(estado.getEstadoAtual().
				equals("fase1")
				||estado.getEstadoAtual().
				equals("fase2")
				||estado.getEstadoAtual().
				equals("fase3")
				||estado.getEstadoAtual().
				equals("fase4")
				){
				acertoSingle(colisaoSlime, slime);
			}else {
				acertoMulti(colisaoSlime, slime);
			}

			
		}
		if (k.getKeyCode() == teclas.get(1)) {

				slime.setCima(true);

		}
		if (k.getKeyCode() ==  teclas.get(2)) {

				slime.setBaixo(true);

		}
		if (k.getKeyCode() == teclas.get(3)) {

				slime.setEsquerda(true);

		}
		if (k.getKeyCode() ==  teclas.get(4)) {

				slime.setDireita(true);

		}
		if (k.getKeyCode() ==  teclas.get(5)) {
			controlador.menuInicial(); 
			controlador.getAudioSingle().getAudio().stop();
			controlador.getEasterEgg().getAudio().stop();
			controlador.getAudioMultiPlayer().getAudio().stop();
			controlador.getAudioMenu().getAudio().loop();
		}
	}

	public void passarDeFase() {
		for(int i = 0; i<Controlador.posicoesEstrela.length; i++) {
			if(ColisaoSlime.colisaoComObjeto(slime.getSprites()[slime.getAparencia()],
					slime.getPosX()+Controlador.mapaX,
					slime.getPosY()+Controlador.mapaY,
					Controlador.estrelinha.getSprites()[0],
					Controlador.posicoesEstrela[i][0],
					Controlador.posicoesEstrela[i][1])) {
				
				if(estado.getEstadoAtual().equals("fase1")) {
					Controlador.setPosicoesFase2();
					this.slime = Controlador.getSlime();
					controlador.fase2();
					break;
				}else if(estado.getEstadoAtual().equals("fase2")) {
					Controlador.setPosicoesFase3();
					this.slime = Controlador.getSlime();
					controlador.fase3();
					break;
				}else if(estado.getEstadoAtual().equals("fase3")) {
					Controlador.setPosicoesFase4();
					this.slime = Controlador.getSlime();
					controlador.fase4();
					break;
				}else if(estado.getEstadoAtual().equals("fase4")) {
					this.slime = Controlador.getSlime();
					ControladorGraphics.setNome(ControladorGraphics.nomeJogador1);
					ControladorGraphics.setVida(Controlador.getSlime().getVidas().size());
					ControladorGraphics.setTempo(Controlador.tempo);
					controlador.getAudioSingle().getAudio().stop();
					controlador.getEasterEgg().getAudio().stop();
					controlador.getAudioVitoria().getAudio().loop();
					controlador.ganhouSingle();
					Controlador.atualizarRank(ControladorGraphics.nomeJogador1,
							Integer.toString(Controlador.getSlime().getVidas().size()), 
							Controlador.tempo);
				}
			}		
		}
	}
	
	public void naoAtravessaDeFormaMulti() {
		
		if (ColisaoSlime.checarColisaoBaixo(
				slime, 
				Controlador.multiColision,
				Controlador.mapaX,
				Controlador.mapaY))
			slime.setPosY(slime.getPosY() - Slime.velocidade);
		if (ColisaoSlime.checarColisaoCima(
				slime,
				Controlador.multiColision,
				Controlador.mapaX,
				Controlador.mapaY))
			slime.setPosY(slime.getPosY() + Slime.velocidade);
		if (ColisaoSlime.checarColisaoEsquerda(
				slime,
				Controlador.multiColision,
				Controlador.mapaX,
				Controlador.mapaY))
			slime.setPosX(slime.getPosX() + Slime.velocidade);
		if (ColisaoSlime.checarColisaoDireita(
				slime,
				Controlador.multiColision,
				Controlador.mapaX,
				Controlador.mapaY))
			slime.setPosX(slime.getPosX() - Slime.velocidade);
	}
	
	public void naoAtravessaDeFormaSingle() {
		if (ColisaoSlime.checarColisaoBaixo(
				slime, 
				Controlador.colisaoSlime,
				Controlador.mapaX,
				Controlador.mapaY-2))
		slime.setPosY(slime.getPosY() - Slime.velocidade);
		if (ColisaoSlime.checarColisaoCima(
				slime,
				Controlador.colisaoSlime,
				Controlador.mapaX,
				Controlador.mapaY+2))
		slime.setPosY(slime.getPosY() + Slime.velocidade);
		if (ColisaoSlime.checarColisaoEsquerda(
				slime,
				Controlador.colisaoSlime,
				Controlador.mapaX+2,
				Controlador.mapaY))
		slime.setPosX(slime.getPosX() + Slime.velocidade);
		if (ColisaoSlime.checarColisaoDireita(
				slime,
				Controlador.colisaoSlime,
				Controlador.mapaX-2,
				Controlador.mapaY))
		slime.setPosX(slime.getPosX() - Slime.velocidade);
	}
	
	
	
	@Override
	public void run() {
		
			while (true) {
				
				try 
				{	
					
						
					if(estado.getEstadoAtual().equals("multi"))
						naoAtravessaDeFormaMulti();
					else if(estado.getEstadoAtual().equals("fase1")
							||estado.getEstadoAtual().equals("fase2")
							||estado.getEstadoAtual().equals("fase3")
							||estado.getEstadoAtual().equals("fase4"))
						naoAtravessaDeFormaSingle();
					slime.animacao();

					passarDeFase();
					
					Controlador.estrelinha.animacao(
							!(estado.getEstadoAtual().equals("fase4"))
							);
					sleep(60);
					
				} catch (InterruptedException e) {}
			}
		
	}
	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == teclas.get(1)) {
			
				slime.setCima(false);
		}
		if (k.getKeyCode() ==  teclas.get(2)) {
			
				slime.setBaixo(false);
		}
		if (k.getKeyCode() == teclas.get(3)) {
			
				slime.setEsquerda(false);
		}
		if (k.getKeyCode() ==  teclas.get(4)) {
			
				slime.setDireita(false);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	
	public void update(Observable o, Object obj) {
		
		if(obj instanceof Estados)
			this.estado = (Estados) obj;
		
	}
	
}
