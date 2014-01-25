package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Water extends StaticObject{

	public Water(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Sprite loadSprite() {
		// TODO Auto-generated method stub
		return super.loadSprite("data/test2.png");
	}
	
}
