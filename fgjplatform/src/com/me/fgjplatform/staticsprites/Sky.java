package com.me.fgjplatform.staticsprites;

public class Sky extends StaticSprite {

	public Sky(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/background.png", "data/background_robot.png");
	}

}
