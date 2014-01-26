package com.me.fgjplatform.gameobjects.staticobjects;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;

public class Tree extends StaticObject {

	public Tree(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/tree.png", "data/tree2.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		Filter f = this.fixture.getFilterData();
		f.maskBits = ~(0x0002); // I do not collide with (alien)
		f.categoryBits = 0x0004;  // I am 
		fixture.setFilterData(f);
		
		this.getBody().setUserData("tree");
	}

}
