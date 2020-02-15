package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.0
 * */

@XStreamAlias("Ranking")
public final class Ranking implements Comparable<Object>{

	private int tempo;
	private String nome;
	private String vidas;
	
	public Ranking(String nome, String vidas, int tempo) {
		super();
		this.nome = nome;
		this.vidas = vidas;
		this.tempo = tempo;
	}
	
	public int getTempo() {return tempo;}
	public String getNome() {return nome;}
	public String getVidas() {return vidas;}

	@Override
	public int compareTo(Object o) {
		if (this.tempo < ((Ranking)o).tempo)
			if(Integer.parseInt(this.vidas)>Integer.parseInt(((Ranking)o).vidas)) {
            return -1;
			}
			else return -1;
        if (this.tempo > ((Ranking)o).tempo)
        	if( Integer.parseInt(this.vidas)<Integer.parseInt(((Ranking)o).vidas)) {
            return 1;
        	}
        	else 
        		return 1;
        return 0;
		
	}
	
	
	
}
