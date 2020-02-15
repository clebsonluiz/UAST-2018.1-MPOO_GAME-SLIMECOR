package app;

import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import controll.Controlador;
import controll.ControladorControles;
import controll.ControladorGraphics;
import model.Arquivo;
import model.Slime;
import view.Janela;
import view.Mensagem;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 */
public class App {

	public static void main(String[] args) {

		Janela janela = new Janela();

		Slime slime, slime2;
		try {

			Arquivo arquivos = new Arquivo();

			slime = arquivos.getSlimes().get(0);
			slime2 = arquivos.getSlimes2().get(0);
			
			ArrayList<Integer> k = new ArrayList<>();
			Collections.addAll(k, KeyEvent.VK_ENTER,
								  KeyEvent.VK_UP,
								  KeyEvent.VK_DOWN,
								  KeyEvent.VK_LEFT,
								  KeyEvent.VK_RIGHT,
								  KeyEvent.VK_ESCAPE);

			ArrayList<Integer> k2 = new ArrayList<>();
			Collections.addAll(k2, KeyEvent.VK_E,
								   KeyEvent.VK_W,
								   KeyEvent.VK_S,
								   KeyEvent.VK_A,
								   KeyEvent.VK_D,
								   KeyEvent.VK_ESCAPE);

			Controlador controlador = new Controlador(janela, slime, slime2, arquivos);
			controlador.controll();
			ControladorControles controles = new ControladorControles(janela,
					controlador, 
					Controlador.getSlime(),
					k,
					false);
			ControladorControles controles2 = new ControladorControles(janela,
					controlador, 
					Controlador.getSlime2(), 
					k2,
					true);
			
			ControladorGraphics graficos = new ControladorGraphics(janela);

			controlador.addObserver(controles);
			controlador.addObserver(controles2);
			controlador.addObserver(graficos);
			controlador.menuInicial();
			controles.controll();
			controles2.controll();
			graficos.controll();
			graficos.start();

		} catch (FileNotFoundException e) {

			Mensagem.exibirMensagem(
					"Algum arquivo não foi encontrado, encerrando aplicação",
					"Houston, Temos um problema!");
			System.exit(0);
		} catch (IOException e) {
			Mensagem.exibirMensagem(
					"Algum arquivo não foi lido corretamente, encerrando aplicação",
					"Carambolas!");
			System.exit(0);
		} catch (FontFormatException e) {
			Mensagem.exibirMensagem(
					"Fonte Não suportada, encerrando aplicação",
					"Balde de água fria :/");
			System.exit(0);
		}

	}

}
