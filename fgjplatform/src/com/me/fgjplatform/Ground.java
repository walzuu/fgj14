package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Ground extends StaticObject {

	public Ground(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		// TODO Auto-generated constructor stub
		
		this.sprite.setTexture(textures.get(0));
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		this.loadTextures("data/gtile0.png");
		this.textures.get(0).setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
	}
	
	@Override
	public void loadTextures(String... paths) {
		this.textures = new ArrayList<Texture>();
		
		for (int i=0; i<paths.length; i++) {
			Texture texture = new Texture(Gdx.files.internal(paths[i]));
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			
			textures.add(texture);
		}
		
		
	}
}
