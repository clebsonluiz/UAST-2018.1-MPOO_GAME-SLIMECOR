package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */

public class Janela extends JFrame{
	
	private static final long serialVersionUID = 001;
	
	private static final Dimension tamanho = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int largura = 890, altura = 480;
	
	private JanelaPanel janelaPanel;
	
	public Janela() {
		super("Jogo");
		
		//setLayout(null);
		setResizable(false);
		
		int x = (tamanho.width - largura)/2;
		int y = (tamanho.height - altura)/2;

		setLocation(x,y);

		
		janelaPanel = new JanelaPanel(largura, altura);
		
		add(BorderLayout.CENTER, janelaPanel);
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(largura,altura);
		setVisible(true);
		
	}
	
	
	public JanelaPanel getJanelaPanel() {
		return janelaPanel;
	}
	
	public void paint(Graphics g) {}
	
	
}
