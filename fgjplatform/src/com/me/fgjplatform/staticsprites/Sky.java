package com.me.fgjplatform.staticsprites;

public class Sky extends StaticSprite {

	public Sky(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/bg_human.png", "data/bg_robot.png");
	}

}
