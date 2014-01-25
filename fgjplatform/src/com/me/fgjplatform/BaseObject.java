package com.me.fgjplatform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class BaseObject {
	protected float position_x;
	protected float position_y;
	
	protected float width;
	protected float height;
	
	protected BodyDef bodyDef;
	protected Body body;
	protected World world;
	protected OrthographicCamera camera;
	
	protected Sprite sprite;

	// Constructor
	public BaseObject(float x, float y, float width, float height) {
		this.position_x = x;
		this.position_y = y;
		
		this.width = width;
		this.height = height;
		
		this.world = FgjPlatform.GetWorld();
		this.camera = FgjPlatform.GetCamera();
		
		
		
	}
	
	public void draw(SpriteBatch batch) {
		this.sprite.draw(batch);
	}
	
	// load the sprite:
	public Sprite loadSprite() {
		return loadSprite("data/test.png");
	}
	
	// load the sprite from specific sprite path:
	public Sprite loadSprite(String path) {
		//System.out.println("load sprite from asset manager exception");
		Texture texture = new Texture(Gdx.files.internal(path));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion textureRegion = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		
		Sprite s = new Sprite(textureRegion);
		s.setSize(this.width, this.height);
		
		return s;
	}
	
}
