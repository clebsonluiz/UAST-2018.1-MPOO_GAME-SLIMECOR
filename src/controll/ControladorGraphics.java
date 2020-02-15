package controll;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import model.Camada;
import view.Janela;


/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public class ControladorGraphics extends Thread implements Observer{
	
	Estados estado;
	
	
	private static final String tituloMenu = "Colored Slimes";
	private static final String[] opcao = {"Inicio", "Multi-Player", "Ajuda", "Sobre", "Sair"};
	
	private static final Font fonteTitulo = Controlador.fonteGame().deriveFont(Font.PLAIN, 140);
	private static final Color corSelecionado = Color.RED;
	private static final Color naoSelecionado = Color.BLACK;
	private static final Font fonte = Controlador.fonteGame().deriveFont(Font.PLAIN, 100);
	
	private static ArrayList<Color> cores = new ArrayList<>();
	
	private static Random r = new Random();
	
	static String nomeJogador1 = "", nomeJogador2 = "";
	
	static String nome;
	static int vida, tempo;
	
	
	private Janela janela;
	private BufferedImage tela;
	
	

	public ControladorGraphics(Janela janela) {
		this.janela = janela;
	}
	
	public void controll() {
		Collections.addAll(cores,
				Color.BLACK,
				Color.BLUE,
				Color.RED,
				Color.YELLOW,
				Color.GREEN
				);
	}
	

	public static void setNome(String nome) {
		ControladorGraphics.nome = nome;
	}

	public static void setVida(int vida) {
		ControladorGraphics.vida = vida;
	}

	public static void setTempo(int tempo) {
		ControladorGraphics.tempo = tempo;
	}
	
	public void run() {
		tela = new BufferedImage(930, 640, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) tela.getGraphics();
		while(true) {
			
			if(estado.getEstadoAtual().equals("menuInicial")) {
				menuPrincipal(g);	
			}
			else
				if(estado.getEstadoAtual().equals("nomeSingle")) {
					menuNome1(g);
			}else
				if(estado.getEstadoAtual().equals("nomeMulti1")) {
					menuNome1(g);
			}
			else
				if(estado.getEstadoAtual().equals("nomeMulti2")) {
					menuNome2(g);
			}else
				if(estado.getEstadoAtual().equals("multi")) {
					jogoMulti(g, Controlador.multi.getCamada());

			}else
				if(estado.getEstadoAtual().equals("ajuda")) {
					menuAjuda(g);
			}
			else
				if(estado.getEstadoAtual().equals("sobre")) {
					menuSobre(g);
			}
			else 
				if(estado.getEstadoAtual().equals("fase1")) {
					jogoFase(tela, g,Controlador.fase);
			}
			else
				if(estado.getEstadoAtual().equals("fase2")) {
					jogoFase(tela, g,Controlador.fase);
			}
			else
				if(estado.getEstadoAtual().equals("fase3")) {
					jogoFase(tela, g,Controlador.fase);
			}
			else
				if(estado.getEstadoAtual().equals("fase4")) {
					jogoFase(tela, g,Controlador.fase);
			}
			else
				if(estado.getEstadoAtual().equals("vitoriaSingle")) {
					menuGameOverVitoria(g, nome, vida, tempo);
			}else
				 if(estado.getEstadoAtual().equals("vitoriaMulti")) {
					 menuGameOverVitoria(g, nome, vida, tempo);
			}else
				 if(estado.getEstadoAtual().equals("eMorreu")) {
					 menuGameOverDerrota(g, nome, tempo);
			}
			
			g.setColor(Color.BLACK);
			g.drawRect(0,0, janela.getWidth()-1, janela.getHeight()-1);
			janela.getJanelaPanel().getGraphics().drawImage(tela, 0, 0, null);
			janela.repaint();
			
		}
	}
	
	

	
	public void fundoEstatico(Graphics2D g) {
		Controlador.fundoMenu.staticUpdate(g);
	}
	
	public void fundoMenu(Graphics2D g) {	
		Controlador.fundoMenu.update(g);	
	}
	
	public void fundoGameOver(Graphics2D g) {
		
		Controlador.fundoGameOver.update(g);	
	}
	
	
	public void menuGameOverVitoria(Graphics2D g, String nome, int vida,int tempo) {
		try {
			fundoGameOver(g);
			g.setColor(cores.get(r.nextInt(3)));
			g.setFont(fonteTitulo);
			g.drawString("Vitoria de "+ nome, janela.getWidth()/6, 120);
			g.setFont(fonte);
			g.drawString("Terminou num tempo de "+ tempo + "  Segundos", 
						janela.getWidth()/6, 190);
			g.drawString("Com "+ vida +" vidas", janela.getWidth()/6, 230);
			g.drawString("Aperte a tecla backspace <- ", 200, 300);
			g.drawString("para voltar a tela de menu", 200, 340);
			janela.repaint();	
			sleep(40);
		} catch (InterruptedException e) {}
	}
	
	public void menuGameOverDerrota(Graphics2D g, String nome,int tempo) {
		try {
			fundoGameOver(g);
			g.setColor(cores.get(r.nextInt(3)));
			g.setFont(fonteTitulo);
			g.drawString("E morreu... "+ nome, janela.getWidth()/3, 120);
			g.setFont(fonte);
			g.drawString(nome+ " chegou num tempo maximo de ", janela.getWidth()/6, 180);
			g.drawString(tempo + "   Segundos", janela.getWidth()/4, 230);
			
			g.drawString("Aperte a tecla BACKSPACE <- ", 200, 300);
			g.drawString("para voltar a tela de menu", 200, 340);
			janela.repaint();	
			
			sleep(40);
		} catch (InterruptedException e) {}
	}
	
	
	public void menuPrincipal(Graphics2D g) {
		try {
			fundoMenu(g);
			
			
			
			g.setColor(cores.get(r.nextInt(5)));
			g.setFont(fonteTitulo);
			g.drawString(tituloMenu, janela.getWidth()/4, 120);
			g.setFont(fonte);
			
			for(int i = 0; i< opcao.length; i++) {
				if(i==Controlador.escolhaAtual)	
					g.setColor(corSelecionado);
				else 
					g.setColor(naoSelecionado);	
				g.drawString(opcao[i],145, 230 + i* 50);
			}
			
			g.setColor(cores.get(1));
			g.drawString("Rank", 450, 220);
			g.setFont(fonte.deriveFont(Font.PLAIN, 60));
			for(int i = 0; i<Controlador.getRank().size(); i++) {
				
				g.drawString(Controlador.getRank().get(i).getNome()+"; Vidas: "+
						Controlador.getRank().get(i).getVidas()+"; Tempo "+
						Controlador.getRank().get(i).getTempo()+" Segundos",
						450, 250 + i*50);
			}

			sleep(40);
			
		} catch (InterruptedException e) {}
	}
	
	public void menuNome1(Graphics2D g) {
		try {
			fundoMenu(g);
			g.setColor(cores.get(r.nextInt(5)));
			g.setFont(fonteTitulo);
			g.drawString("Nome do Jogador", janela.getWidth()/4, 120);
			g.setFont(fonte);
			g.drawString(nomeJogador1,145, 230 + 50);
			janela.repaint();	
		
			sleep(40);
		} catch (InterruptedException e) {}
	}
	public void menuNome2(Graphics2D g) {
		try {
			fundoMenu(g);
			g.setColor(cores.get(r.nextInt(5)));
			g.setFont(fonteTitulo);
			g.drawString("Nome do 2º Jogador", janela.getWidth()/4, 120);
			g.setFont(fonte);
			g.drawString(nomeJogador2,145, 230 + 50);
			janela.repaint();	
			
			sleep(40);
		} catch (InterruptedException e) {}
	}

	
	public void menuAjuda(Graphics2D g) {
		try {
			
			fundoEstatico(g);
			g.setColor(cores.get(r.nextInt(3)));
			g.setFont(fonteTitulo);
			g.drawString("Ajuda", (int)(janela.getWidth()/2.5), 120);
			
			g.setFont(fonte);
			g.drawString("Irá Aparecer uma pergunta, que o Jogador",50, 200 + 0* 50);
			g.drawString("terá de acerta-la para diminuir o tempo,",50, 200 + 1* 50);
			g.drawString("este que será sua pontuaçao no final do",50, 200 + 2* 50);
			g.drawString("game, erro perde uma vida. Os controles",50, 200 + 3* 50);
			g.drawString("sao os direcionais e a tecla ENTER.",50, 200 + 4* 50);
			janela.repaint();	
			
			sleep(100);
		} catch (InterruptedException e) {}
		
		
	}
	
	public void menuSobre(Graphics2D g) {
		try {
			
			fundoEstatico(g);
			g.setColor(cores.get(r.nextInt(3)));
			g.setFont(fonteTitulo);
			g.drawString("Sobre o Jogo", (int)(janela.getWidth()/3), 120);
			g.setFont(fonte);
				
			g.drawString("Criado por Clébson Luiz de Moraes Silva",80, 230 + 0* 50);
			g.drawString("Como Projeto para 2ª V.A. e 3ª V.A. de",95, 230 + 1* 50);
			g.drawString("MPOO utilizando TileMap e Sprite",110, 230 + 2* 50);
		
			sleep(100);
		} catch (InterruptedException e) {}
		
	}
	
	
	public void cameraJogo() {
		int px = (Controlador.getSlime().getPosX()+Controlador.getSlime().getWidth()*2);
		int py = (Controlador.getSlime().getPosY()+Controlador.getSlime().getHeight()*2);
		

		if(px > (janela.getWidth()-Controlador.inventario.getWidth())/2) {
			if(px < Controlador.fase.getCamada().getWidth() - (janela.getWidth()-Controlador.inventario.getWidth())/2) {
				Controlador.mapaX = px- (janela.getWidth()-Controlador.inventario.getWidth())/2;
			}
		}else {
			Controlador.mapaX = 0; 
		}
		if(py > (janela.getHeight())/2) {
			if(py < Controlador.fase.getCamada().getHeight() - (janela.getHeight())/2) {
				Controlador.mapaY = py-  (janela.getHeight()/2);						
			}
			
		}else Controlador.mapaY = 0;
	}
		
	public void jogoFase(BufferedImage tela, Graphics2D g, Camada camada) {
		cameraJogo();
		
		tela.getGraphics().drawImage(camada.getCamada(), -Controlador.mapaX, -Controlador.mapaY, null);
		
		for(int i = 0; i<Controlador.posicoeslist.size(); i++)
			tela.getGraphics().drawImage(Controlador.imagensPerguntas.get(i).getImagem()
																,Controlador.posicoeslist.get(i)[0]-Controlador.mapaX
																, Controlador.posicoeslist.get(i)[1]-Controlador.mapaY
																, null);
	
		
		
		
		for(int[] i: Controlador.posicoesEstrela)
			tela.getGraphics().drawImage(Controlador.estrelinha.getSprites()[Controlador.estrelinha.getAparencia()], i[0]-Controlador.mapaX, i[1]-Controlador.mapaY, null);
		
		
		

		tela.getGraphics().drawImage(Controlador.getSlime().getSprites()[Controlador.getSlime().getAparencia()], Controlador.getSlime().getPosX(), Controlador.getSlime().getPosY(), null);
	
		tela.getGraphics().drawImage(Controlador.inventario, 640, 0, null);
		
		for(int i = 0; i<Controlador.getSlime().getVidas().size(); i++) {
			tela.getGraphics().drawImage(Controlador.getSlime().getVidas().get(i).getImagem(), 655+ i*75, 60, null);
		}
		
		g.drawImage(tela, 0, 0, null);
		
        g.setFont(fonte.deriveFont(Font.PLAIN, 100));
        g.setColor(Color.RED);
        
        
        String[] string = Controlador.getPerguntasMulti().get(0).getPergunta().split(";");
        
        for(int i = 0; i < string.length; i++)
        	g.drawString(string[i], 650, 230 + i*40);
        
        g.drawString(""+Controlador.tempo, 800, 440);
        g.setColor(cores.get(r.nextInt(5)));
        g.setFont(fonte.deriveFont(Font.PLAIN, 40));
        g.drawString(nomeJogador1, Controlador.getSlime().getPosX()-nomeJogador1.length()/2, Controlador.getSlime().getPosY()-10);
        g.drawString("V", Controlador.getSlime().getPosX()+Controlador.getSlime().getWidth()/2, Controlador.getSlime().getPosY());
        
       // try {sleep(15);	} catch (InterruptedException e) {}
	
	}
	
	public void jogoMulti(Graphics2D g, BufferedImage camada) {
		tela.getGraphics().drawImage(camada, 0, 0, null);
		
		for(int i = 0; i<Controlador.posicoesMultiValida.size(); i++)
			tela.getGraphics().drawImage(Controlador.imagensPerguntas.get(i).getImagem()
																,Controlador.posicoesMultiValida.get(i)[0]-Controlador.mapaX
																, Controlador.posicoesMultiValida.get(i)[1]-Controlador.mapaY
																, null);
		
		tela.getGraphics().drawImage(Controlador.getSlime().getSprites()[Controlador.getSlime().getAparencia()], Controlador.getSlime().getPosX(), Controlador.getSlime().getPosY(), null);
		tela.getGraphics().drawImage(Controlador.getSlime2().getSprites()[Controlador.getSlime2().getAparencia()], Controlador.getSlime2().getPosX(), Controlador.getSlime2().getPosY(), null);
		
		tela.getGraphics().drawImage(Controlador.inventarioMulti, 640, 0, null);
		
		for(int i = 0; i<Controlador.getSlime().getVidas().size(); i++) {
			
			Image vida = Controlador.getSlime().getVidas().get(i).getImagem();
			vida = vida.getScaledInstance(vida.getWidth(null)/2, vida.getHeight(null)/2, BufferedImage.SCALE_SMOOTH);
			
			tela.getGraphics().drawImage(vida, 650+ i*40, 30, null);
			
		}
		
		for(int i = 0; i<Controlador.getSlime2().getVidas().size(); i++) {
			Image vida = Controlador.getSlime2().getVidas().get(i).getImagem();
			vida = vida.getScaledInstance(vida.getWidth(null)/2, vida.getHeight(null)/2, BufferedImage.SCALE_SMOOTH);
			tela.getGraphics().drawImage(vida, 650+ i*40, 80, null);
		}
		
		g.drawImage(tela, 0, 0, null);
		
		 g.setFont(fonte.deriveFont(Font.PLAIN, 100));
	        g.setColor(Color.RED);
        
        
        String[] string = Controlador.getPerguntasMulti().get(0).getPergunta().split(";");
        
        for(int i = 0; i < string.length; i++)
        	g.drawString(string[i], 650, 230 + i*40);
        
        g.drawString(""+Controlador.tempo, 800, 440);
        g.setColor(cores.get(r.nextInt(5)));
        g.setFont(fonte.deriveFont(Font.PLAIN, 40));
        g.drawString(nomeJogador1, Controlador.getSlime().getPosX()-nomeJogador1.length()/2, Controlador.getSlime().getPosY()-10);
        g.drawString("V", Controlador.getSlime().getPosX()+Controlador.getSlime().getWidth()/2, Controlador.getSlime().getPosY());
        g.drawString(nomeJogador2, Controlador.getSlime2().getPosX()-nomeJogador2.length()/2, Controlador.getSlime2().getPosY()-10);
        g.drawString("V", Controlador.getSlime2().getPosX()+Controlador.getSlime2().getWidth()/2, Controlador.getSlime2().getPosY());
        
        g.setColor(cores.get(0));
        g.drawString(nomeJogador1, 790, 50);
        g.drawString(nomeJogador2, 790, 100);
	}

	@Override
	public void update(Observable observable, Object obj) {
		
		if(obj instanceof Estados)
			this.estado = (Estados) obj;
		
		
	}
}
