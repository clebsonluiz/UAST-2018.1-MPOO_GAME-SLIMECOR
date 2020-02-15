package view;

import javax.swing.JOptionPane;

public class Mensagem {

	public static void exibirMensagem(String mensagem,String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
}
