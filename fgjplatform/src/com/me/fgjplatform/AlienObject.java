package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class AlienObject extends CreatureObject {
	public AlienObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height,world);
	}
	
	@Override
	public void loadTextures() {
		super.loadTextures("data/Human.png", "data/Alien.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		body.setUserData("alien");
	}
}
