package com.jacklify85.ld32.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jacklify85.ld32.LDGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.allowSoftwareMode = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new LDGame(), config);
	}
}
