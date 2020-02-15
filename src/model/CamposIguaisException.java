package model;

/**
 * @author Cl�bson Luiz de Moraes Silva
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CamposIguaisException extends Exception {

	public CamposIguaisException() {
		super("Opa! O que � isso? Os nomes dos dois jogadores s�o IGUAIS!");
	}

}
