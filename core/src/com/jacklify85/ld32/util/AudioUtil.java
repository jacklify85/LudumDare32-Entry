package com.jacklify85.ld32.util;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtil {

	private static HashMap<String, Music> musicPlaying = new HashMap<String, Music>();
	
	public static void playMusic(String name, Music music, boolean loop) {
		musicPlaying.put(name, music);
		music.setLooping(loop);
	}
	
	public static boolean isPlaying(String name) {
		return musicPlaying.containsKey(name);
	}
	
	public static void stopPlaying(String name) {
		Music m = musicPlaying.get(name);
		m.stop();
		musicPlaying.remove(name);
	}
	
	public static void playEffect(Sound sound) {
		sound.play();
	}
	
	public static void pulse() {
		Iterator mIterator = musicPlaying.entrySet().iterator();
		
		while (mIterator.hasNext()) {
			Music m = (Music) mIterator.next();
			
			if (m.isLooping() == false) {
				musicPlaying.remove(m);
			}
		}
	}
}
