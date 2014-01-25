package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
		body.setUserData("tree");
	}

}
