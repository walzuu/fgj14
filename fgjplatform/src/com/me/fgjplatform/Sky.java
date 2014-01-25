package com.me.fgjplatform;

public class Sky extends StaticSprite {

	public Sky(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/test.png", "data/test2.png");
	}

}
