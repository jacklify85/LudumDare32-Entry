package com.jacklify85.ld32.world;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jacklify85.ld32.screens.GameScreen;

public class WaveManagementThread implements Runnable{

	private GameScreen gScreen;
	
	public WaveManagementThread(GameScreen g) {
		this.gScreen = g;
	}
	
	@Override
	public void run() {
		Random rand = new Random();
		int wave = 1;
		while (GameScreen.alive) {
			while (GameScreen.world.zombiesRemaining > 0 || !GameScreen.go) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Gdx.app.error("LDGAME", "InterruptedException on WaveManagementThread");
				}	
				continue;
			}
			wave++;				
			GameScreen.wave += 1;
			int randamt =  5;
			for (int i = 0; i < randamt; i++) {
				float posX = GameScreen.player.getX() + (rand.nextInt(1000) * rand.nextInt(3) * rand.nextFloat());
				float posY = GameScreen.player.getY() + (rand.nextInt(1000) * rand.nextFloat());
				Zombie zombie = new Zombie(posX, posY, (int)posX);
				this.gScreen.objsToBeAdded.add(zombie);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
				}
			}
			GameScreen.go = false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
	}

}
