package com.jacklify85.ld32.world;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.jacklify85.ld32.pickups.AmmoPickup;
import com.jacklify85.ld32.pickups.HealthBoostPickup;
import com.jacklify85.ld32.pickups.HealthPickup;
import com.jacklify85.ld32.pickups.SpeedPickup;
import com.jacklify85.ld32.screens.GameScreen;

public class WaveManagementThread implements Runnable{

	private GameScreen gScreen;
	
	public WaveManagementThread(GameScreen g) {
		this.gScreen = g;
	}
	
	@Override
	public void run() {
		int cycles = 0;
		Random rand = new Random();
		int wave = 1;
		while (GameScreen.alive) {
			while (GameScreen.world.zombiesRemaining > 0 || !GameScreen.go) {
				cycles++;
				if (cycles >= 7) {
					cycles = 0;
					float posX = GameScreen.player.getX() + (rand.nextInt(25) * rand.nextInt(3) * rand.nextFloat());
					float posY = GameScreen.player.getY() + (rand.nextInt(30) * rand.nextFloat());
					int pickup = rand.nextInt(5);
					if (pickup == 0 || pickup == 1) {
						AmmoPickup aPickup = new AmmoPickup(posX, posY);
						Gdx.app.log("LDGAME", "Spawning ammo pickup at: " + posX + ", " + posY);
						this.gScreen.objsToBeAdded.add(aPickup);
					}
					if (pickup == 2) {
						HealthPickup hPickup = new HealthPickup(posX, posY);
						Gdx.app.log("LDGAME", "Spawning health boost pickup at: " + posX + ", " + posY);
						this.gScreen.objsToBeAdded.add(hPickup);
					}
					if (pickup == 3) {
						HealthBoostPickup hbPickup = new HealthBoostPickup(posX, posY);
						Gdx.app.log("LDGAME", "Spawning max health boost pickup at: " + posX + ", " + posY);
						this.gScreen.objsToBeAdded.add(hbPickup);
					}
					if (pickup == 4) {
						SpeedPickup sPickup = new SpeedPickup(posX, posY);
						Gdx.app.log("LDGAME", "Spawning speed pickup at: " + posX + ", " + posY);
						this.gScreen.objsToBeAdded.add(sPickup);
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Gdx.app.error("LDGAME", "InterruptedException on WaveManagementThread");
				}	
				continue;
			}
			wave++;				
			GameScreen.wave += 1;
			int randamt =  (5 * wave) * rand.nextInt(3);

			for (int i = 0; i < randamt; i++) {
				float posX = GameScreen.player.getX() + (rand.nextInt(1000) * rand.nextInt(3) * rand.nextFloat());
				float posY = GameScreen.player.getY() + (rand.nextInt(1000) * rand.nextFloat());
				Zombie zombie = new Zombie(posX, posY, (int)posX);
				this.gScreen.objsToBeAdded.add(zombie);
				try {
					Thread.sleep(1000);
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
