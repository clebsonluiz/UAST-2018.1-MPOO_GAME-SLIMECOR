package model;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public class ColisaoSlime extends ColisaoMapa{

	public ColisaoSlime(Camada camada) {
		super(camada);
	}
	
	private boolean checarColisao(int statusSlime, int x, int y) {
		if(x<0||y<0) return true;
		return !(statusSlime == getMapa()[y][x])&&(getMapa()[y][x]!=4);
	}
	
	public static boolean checarColisaoCima(Slime personagem, 
											ColisaoSlime colisao,
											int mapaX, int mapaY) {
		
		int x = personagem.getPosX()+personagem.getWidth()/2 + mapaX;
		int y = personagem.getPosY()+personagem.getHeight()*2/4 + mapaY;	
		
		return colisao.checarColisao(personagem.getStatus(),
									 Math.round(x/colisao.getTileWidth()),
									 Math.round(y/colisao.getTileHeight()));
	}
	
	public static boolean checarColisaoBaixo(Slime personagem, 
											 ColisaoSlime colisao,
											 int mapaX, int mapaY) {
		
		int x = personagem.getPosX()+personagem.getWidth()/2 + mapaX;
		int y = personagem.getPosY()+personagem.getHeight() + mapaY;
		
		return colisao.checarColisao(personagem.getStatus(),
									 Math.round(x/colisao.getTileWidth()), 
									 Math.round(y/colisao.getTileHeight()));
	}
	public static boolean checarColisaoEsquerda(Slime personagem,
												ColisaoSlime colisao,
												int mapaX, int mapaY) {

		int x = personagem.getPosX() + mapaX;
		int y = personagem.getPosY()+personagem.getHeight()*4/5 + mapaY;
		
		return colisao.checarColisao(personagem.getStatus(), 
									 Math.round(x/colisao.getTileWidth()),
									 Math.round(y/colisao.getTileHeight()));
	}
	public static boolean checarColisaoDireita(Slime personagem, 
											   ColisaoSlime colisao, 
											   int mapaX, int mapaY) {
		
		int x = personagem.getPosX()+personagem.getWidth() + mapaX;
		int y = personagem.getPosY()+personagem.getHeight()*4/5 + mapaY;
		
		return colisao.checarColisao(personagem.getStatus(),
									 Math.round(x/colisao.getTileWidth()),
									 Math.round(y/colisao.getTileHeight()));
	}
}
