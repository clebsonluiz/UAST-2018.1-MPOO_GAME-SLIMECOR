package model;

/**
 * @author Cl�bson Luiz de Moraes Silva
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CampoVazioException extends Exception {

	public CampoVazioException() {
		super("Os campos de nomes n�o podem ser vazios");
	}

}
