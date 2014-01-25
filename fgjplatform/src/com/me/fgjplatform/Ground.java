package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Ground extends StaticObject {

	public Ground(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/gtile0.png");
	}
}
