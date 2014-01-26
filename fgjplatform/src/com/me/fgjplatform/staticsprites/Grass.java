package com.me.fgjplatform.staticsprites;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Grass extends StaticSprite {
	
	protected int type = 0;

	public Grass(float posX, float posY, float width, float height, int type) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
		if (type < this.textures.size()/2) {
			this.type = type;
			changeTexture(0);
		}
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/grass/grass1.png","data/grass/grass11.png",
				"data/grass/grass2.png","data/grass/grass22.png",
				"data/grass/grass3.png","data/grass/grass33.png",
				"data/grass/grass4.png","data/grass/grass44.png",
				"data/grass/grass5.png","data/grass/grass55.png");
	}
	
	@Override
	public void changeTexture(int textureId) {
		if (this.textures.size() > 0 && textureId >= 0 && textureId < this.textures.size()) {
			this.sprite.setTexture(textures.get(textureId + type*2));
		}
	}

}
