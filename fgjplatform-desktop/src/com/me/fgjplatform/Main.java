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
		
		new LwjglApplication(new FgjPlatform(), cfg);
	}
}
