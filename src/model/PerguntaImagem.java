package model;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */
public class PerguntaImagem {

	private ImagemPergunta[] imagens;
	private String pergunta;
	
	public PerguntaImagem(String pergunta,
			ImagemPergunta imagemResposta,
			ImagemPergunta outraImagem_1,
			ImagemPergunta outraImagem_2,
			ImagemPergunta outraImagem_3) {
		
		this.pergunta = pergunta;
		imagens = new ImagemPergunta[4];
		this.imagens[0] = imagemResposta;
		this.imagens[1]  = outraImagem_1;
		this.imagens[2]  = outraImagem_2;
		this.imagens[3]  = outraImagem_3;
	}
	
	public String getPergunta() {
		return pergunta;
	}

	public ImagemPergunta[] getImagemResposta() {
		return imagens;
	}
}
