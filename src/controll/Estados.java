package controll;


/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public enum Estados {
	
	FASE_1("fase1"),
	FASE_2("fase2"),
	FASE_3("fase3"),
	FASE_4("fase4"),
	MENU_AJUDA("ajuda"),
	MENU_SOBRE("sobre"),
	E_MORREU("eMorreu"),
	MULTI_PLAYER("multi"),
	MENU_INICIAL("menuInicial"),
	NOME_JOGADOR_SINGLEPLAYER("nomeSingle"),
	NOME_JOGADOR_MULTIPLAYER_1("nomeMulti1"),
	NOME_JOGADOR_MULTIPLAYER_2("nomeMulti2"),
	MENU_VITORIA_MULTIPLAYER("vitoriaMulti"),
	MENU_VITORIA_SINGLEPLAYER("vitoriaSingle");
	
	private String estadoAtual;
	
	Estados(String estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	public String getEstadoAtual() {
		return estadoAtual;
	}
	
	
	
}
