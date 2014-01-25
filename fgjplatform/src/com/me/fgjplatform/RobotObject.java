package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class RobotObject extends CreatureObject {
	public RobotObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height,world, "data/robot.png", 4, 4);
	}
	
	//@Override
//	public Sprite loadSprite() {
//		return super.loadSprite("data/robot.png", "data/robot.png");
//	}
	
	@Override
	public void loadTextures() {
		super.loadTextures("data/robot.png", "data/robot.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		body.setUserData("robot");
	}
}
