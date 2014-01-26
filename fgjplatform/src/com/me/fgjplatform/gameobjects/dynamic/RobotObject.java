package com.me.fgjplatform.gameobjects.dynamic;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.Global;

public class RobotObject extends CreatureObject {
	public RobotObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height,world, "data/robot.png");
	}
	
	@Override
	public void loadTextures() {
		super.loadTextures("data/robot.png", "data/robot.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		
		Filter filter = fixture.getFilterData();
		filter.maskBits = ~(Global.CATEGORY_ALIEN); // I do not collide with
		filter.categoryBits = Global.CATEGORY_ROBOT;  // I am robot
		fixture.setFilterData(filter);
		
		getBody().setUserData("robot");
	}
}
