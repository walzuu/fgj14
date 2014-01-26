package com.me.fgjplatform.gameobjects.staticobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.mechanic.Animator;

public class Water extends StaticObject{

	
	private Animator a;
	
	
	public Water(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		a.render(batch,(int)(this.getBody().getWorldCenter().x), 
				(int)(this.getBody().getWorldCenter().y), width, height);
	}
	
	@Override
	protected void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.position.set(new Vector2(position_x,position_y));  
        setBody(world.createBody(bodyDef));  
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox(width/2, height/2);  
        Fixture fixture = getBody().createFixture(groundBox, 0.0f);
        fixture.setSensor(true);
        getBody().setUserData("water");
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
        a = new Animator();
		a.create("data/water1.png", 4, 1, 0);
		super.loadTextures("data/water1.png", "data/corrosive_anim.png");
	}
	
	@Override
	public void changeTexture(int textureId) {
		if (this.textures.size() > 0 && textureId >= 0 && textureId < this.textures.size()) {
			a = new Animator();
			switch (textureId) {
			case 0:
				a.create("data/water1.png", 4, 1,0);
				break;
			case 1:
				a.create("data/corrosive_anim.png", 4, 1,0);
				break;
			}
		}
	}
	
}
