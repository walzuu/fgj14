package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;

public class RobotObject extends CreatureObject {
	public RobotObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height,world, "data/robot.png");
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
		
		Filter filter = fixture.getFilterData();
		filter.maskBits = ~(0x0002); // I do not collide with:
		filter.categoryBits = 0x0008;  // I am
		fixture.setFilterData(filter);
		
		body.setUserData("robot");
	}
}
