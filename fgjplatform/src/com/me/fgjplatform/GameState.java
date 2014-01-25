package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	private World world;
	private Global.GameEnding gameEnding;
	
	public GameState(World world) {
		this.world = world;
		gameEnding = Global.GameEnding.none;
	}
	
	public void SetGameEnding(Global.GameEnding ending) {
		gameEnding = ending;
	}
}
