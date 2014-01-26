package com.me.fgjplatform;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "fgjplatform";
		cfg.useGL20 = false;
		cfg.width = 800;
	cfg.height = 500;
	//	cfg.width = 1366;
		//cfg.height = 768;
	//	cfg.fullscreen = true;
		cfg.vSyncEnabled = true;
		cfg.samples = 4;
		
		new LwjglApplication(new FgjPlatform(), cfg);
	}
}
