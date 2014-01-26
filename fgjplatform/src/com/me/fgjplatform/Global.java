package com.me.fgjplatform;

import com.badlogic.gdx.Gdx;

public class Global {
	public static final short CATEGORY_ALIEN = 0x0002; 
	public static final short CATEGORY_ROBOT = 0x0004; 
	public static final short CATEGORY_TREE = 0x0008;
	public static final short CATEGORY_BOX_DYNAMIC = 0x0010; 
	public static final short CATEGORY_BOX_STATIC = 0x0020;
	
	public static final float WIDTH = Gdx.graphics.getWidth();
	public static final float HEIGHT = Gdx.graphics.getHeight();
	
	public static final float WTB = 0.03125f;
	public static final float BTW = 32f;
	
	public enum GameEnding { none, win, lose };
}
