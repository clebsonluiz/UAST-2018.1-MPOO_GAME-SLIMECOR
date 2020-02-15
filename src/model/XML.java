package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

/**
 * @author Clébson Luiz de Moraes Silva
 * @version 1.1 - Bug Save corrigido
 * @see -Correção no bug de não substituir o xml ao salvar, 
 * Ele não estava salvando o arquivo apesar de ter entrado na função.
 * Bug provavelmente apareceu devido a mudança no modo de 
 * carregamento do arquivo com FileOutputStream. 
 * Corrigido com BufferedReader.
 * */
public final class XML {

	private final InputStream is = getClass().
								   getClassLoader().
								   getResourceAsStream("rank.xml");	
	
	private static final Class<?>[] classes = new Class[] {Ranking.class,
														   ArrayList.class};

	private static XML instance;
	

	private XML() {}
	
	public static XML getInstance() {
		
		if(instance == null)
			instance = new XML();
		return instance;
	}
	
	public void salvar(ArrayList<Ranking> rank) {

		try {
			
			XStream xstream = new XStream(new Dom4JDriver());
			/*FileOutputStream file = new FileOutputStream(
				getClass().
				getClassLoader().
				getResource("rank.xml").
				getFile(), false);*/
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("res/rank.xml"));
			
			
			xstream.alias("Array", ArrayList.class);
			xstream.processAnnotations(Ranking.class);
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(classes);
			String s = xstream.toXML(rank);
			bw.write(s);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Ranking> ler(){
		ArrayList<Ranking> rank;
		try {
			
			XStream xstream = new XStream(new Dom4JDriver());
			xstream.alias("Array", ArrayList.class);
			xstream.processAnnotations(Ranking.class);	
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(classes);
			BufferedReader ler = new BufferedReader(new InputStreamReader(is));
			rank = ((ArrayList<Ranking>)xstream.fromXML(ler));
			ler.close();
			return rank;
		} catch (IOException e) {}
		
		return new ArrayList<>();
	}
	
	
}
