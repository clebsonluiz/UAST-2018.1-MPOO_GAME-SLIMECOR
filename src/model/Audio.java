package model;

import java.applet.Applet;
import java.applet.AudioClip;

public class Audio {

	private AudioClip audio;
	
	public Audio(String caminhoMusica) {
		
		audio = Applet.newAudioClip(getClass().getClassLoader().getResource(caminhoMusica));
		
	}

	public AudioClip getAudio() {
		return audio;
	}
}
