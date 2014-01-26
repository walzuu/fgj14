package com.me.fgjplatform.gameobjects.staticobjects;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.Global;

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
		f.maskBits = ~(Global.CATEGORY_ALIEN); // I do not collide with (alien)
		f.categoryBits = Global.CATEGORY_TREE;  // I am 
		fixture.setFilterData(f);
		
		this.getBody().setUserData("tree");
	}

}
