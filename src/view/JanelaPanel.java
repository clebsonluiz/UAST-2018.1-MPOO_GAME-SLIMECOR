package view;

import java.awt.Graphics;
import javax.swing.JPanel;


/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * 
 * */

@SuppressWarnings("serial")
public class JanelaPanel extends JPanel{

	public JanelaPanel(int x, int y) {
		setSize(x, y);
		//setIgnoreRepaint(true);
		setLayout(null);
		setVisible(true);		
		
	}
	
	
	public void paint(Graphics g) {}
	
	
	
}
