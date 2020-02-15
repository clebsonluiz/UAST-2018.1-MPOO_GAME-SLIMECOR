package model;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CampoVazioException extends Exception {

	public CampoVazioException() {
		super("Os campos de nomes não podem ser vazios");
	}

}
