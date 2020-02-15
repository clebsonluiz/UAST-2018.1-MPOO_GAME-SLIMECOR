package model;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CamposIguaisException extends Exception {

	public CamposIguaisException() {
		super("Opa! O que é isso? Os nomes dos dois jogadores são IGUAIS!");
	}

}
