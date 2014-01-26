package com.me.fgjplatform.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUD {
	
	private Sprite human;
	private Sprite robot;
	private OrthographicCamera camera;
	
	public HUD(OrthographicCamera camera) {
		TextureRegion humanTextureRegion = new TextureRegion();
		Texture humanTexture = new Texture("data/Alien.png");
		humanTextureRegion.setTexture(humanTexture);
		this.human = new Sprite(humanTextureRegion);
		
		TextureRegion robotTextureRegion = new TextureRegion();
		Texture robotTexture = new Texture("data/robot.png");
		robotTextureRegion.setTexture(robotTexture);
		this.robot = new Sprite(robotTextureRegion);
		this.camera = camera;
	}
	
	public void draw(SpriteBatch batch) {
		human.setPosition(camera.position.x, camera.position.y);
		human.draw(batch);
		robot.setPosition(camera.position.x, camera.position.y);
		robot.draw(batch);
	}
}
