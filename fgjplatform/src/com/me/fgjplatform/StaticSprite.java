package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StaticSprite {
	protected Sprite sprite;
	protected ArrayList<Texture> textures;
	
	protected float posX;
	protected float posY;
	
	protected float width;
	protected float height;
	
	public StaticSprite(float posX, float posY, float width, float height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		
		this.loadTextures();
		this.sprite = this.loadSprite();
	}
	
	public void draw(SpriteBatch batch) {
		this.sprite.draw(batch);
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

}
