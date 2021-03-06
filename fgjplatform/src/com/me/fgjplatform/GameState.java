package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	@SuppressWarnings("unused")
	private World world;
	private Global.GameEnding gameEnding;
	private boolean isAlienInGoal;
	private boolean isRobotInGoal;
	
	public GameState(World world) {
		this.world = world;
		this.gameEnding = Global.GameEnding.none;
		this.isAlienInGoal = false;
		this.isRobotInGoal = false;
	}
	
	public void sendToGoal(String name) {
		if (name == "alien") {
			isAlienInGoal = true;
		}
		
		if (name == "robot") {
			isRobotInGoal = true;
		}
		
		if (isAlienInGoal && isRobotInGoal) {
			setGameEnding(Global.GameEnding.win);
		}
	}
	
	public void kill(String name) {
		if (name == "alien" || name == "robot") {
			setGameEnding(Global.GameEnding.lose);
		}
	}
	
	public void setGameEnding(Global.GameEnding ending) {
		gameEnding = ending;
	}
	
	public Global.GameEnding getGameEnding() {
		return gameEnding;
	}
}
