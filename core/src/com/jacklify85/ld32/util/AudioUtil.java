package com.jacklify85.ld32.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtil {

	private static HashMap<String, Music> musicPlaying = new HashMap<String, Music>();
	private static ArrayList<String> musicList = new ArrayList<String>();
	
	static {
		musicList.add("GameOver");
	}
	
	public static void playMusic(String name, Music music, boolean loop) {
		musicPlaying.put(name, music);
		music.setLooping(loop);
		music.play();
		Gdx.app.log("LDGAME", "AudioUtil: Playing " + name);
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
		for (int i = 0; i < musicList.size(); i++) {
			String song = musicList.get(i);
			if (musicPlaying.get(song) != null) {
				Music music = musicPlaying.get(song);
				if (music.isPlaying() == false) {
					musicPlaying.remove(song);
				}
			}
		}
	}
}
