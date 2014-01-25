package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	private World world;
	private Global.GameEnding gameEnding;
	private ArrayList<CreatureObject> objectsInGoal;
	
	public GameState(World world) {
		this.world = world;
		this.gameEnding = Global.GameEnding.none;
		this.objectsInGoal = new ArrayList<CreatureObject>();
	}
	
	public void sendToGoal(CreatureObject playerCharacter) {
		objectsInGoal.add(playerCharacter);
		if (objectsInGoal.size() == 2) {
			gameEnding = Global.GameEnding.win;
		}
	}
	
	public void setGameEnding(Global.GameEnding ending) {
		gameEnding = ending;
	}
	
	public Global.GameEnding getGameEnding() {
		return gameEnding;
	}
}
