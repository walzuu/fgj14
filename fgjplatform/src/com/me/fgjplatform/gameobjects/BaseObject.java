package com.me.fgjplatform.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class BaseObject {
	protected float position_x;
	protected float position_y;
	
	protected float width;	
	protected float height;
	
	protected BodyDef bodyDef;
	private Body body;
	protected FixtureDef fixtureDef;
	protected World world;
	
	protected Fixture fixture;
	
	protected Sprite sprite;
	protected ArrayList<Texture> textures;

	// Constructor
	public BaseObject(float x, float y, float width, float height,World world) {
		this.position_x = x;
		this.position_y = y;
		
		this.width = width;
		this.height = height;
		
		this.world = world;		
		
		this.loadTextures();
		this.sprite = loadSprite();
		
	}
	
	public void draw(SpriteBatch batch)
	{
		if (this.getBody() != null)
		{
			this.sprite.setPosition(this.getBody().getWorldCenter().x - this.sprite.getWidth()/2, 
					this.getBody().getWorldCenter().y - this.sprite.getHeight()/2);
			this.sprite.setRotation(this.getBody().getTransform().getRotation() / (float) Math.PI * 180);
			this.sprite.draw(batch);
		}
	}
	
	// load the sprite:
	public Sprite loadSprite() {
		if (textures.size() > 0) {
			Sprite s = new Sprite(textures.get(0));
			s.setSize(this.width, this.height);
			s.setOrigin(s.getWidth()/2, s.getHeight()/2);
			
			return s;
		} else
			return loadSprite("data/crate.png");
	}
	
	// load the sprite from specific sprite path:
	public Sprite loadSprite(String path) {
		//System.out.println("load sprite from asset manager exception");
		Texture texture = new Texture(Gdx.files.internal(path));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion textureRegion = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		
		Sprite s = new Sprite(textureRegion);
		s.setSize(this.width, this.height);
		s.setOrigin(s.getWidth()/2, s.getHeight()/2);
		
		return s;
	}
	
	public void loadTextures() {
		this.loadTextures("data/crate.png");
	}
	
	public void loadTextures(String... paths) {
		this.textures = new ArrayList<Texture>();
		
		for (int i=0; i<paths.length; i++) {
			Texture texture = new Texture(Gdx.files.internal(paths[i]));
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			textures.add(texture);
		}
	}
	
	public void changeTexture(int textureId) {
		if (this.textures.size() > 0 && textureId >= 0 && textureId < this.textures.size()) {
			this.sprite.setTexture(textures.get(textureId));
		}
		
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
}
