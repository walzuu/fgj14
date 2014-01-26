package com.me.fgjplatform;

import com.badlogic.gdx.Game;
import com.me.fgjplatform.screens.GameScreen;

public class FgjPlatform extends Game {
    
	@Override
	public void create() {		
		this.setScreen(new GameScreen(this));
	}
}
