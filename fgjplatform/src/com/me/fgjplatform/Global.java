package com.me.fgjplatform;

import com.badlogic.gdx.Gdx;

public class Global {
	
	public static final float WIDTH = Gdx.graphics.getWidth();
	public static final float HEIGHT = Gdx.graphics.getHeight();
	
	public static final float PIXELS_PER_METER = 5.0f;
	
	public static float PtoM (float value) {
		return value / PIXELS_PER_METER;
	}
	
	public static float MtoP (float value) {
		return value * PIXELS_PER_METER;
	}
}
