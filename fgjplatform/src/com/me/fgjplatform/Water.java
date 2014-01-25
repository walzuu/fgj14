package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Water extends StaticObject{

	
	private Animator a;
	
	
	public Water(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
	}
	
	
	@Override
	public void draw(SpriteBatch batch)
	{
		a.render(batch,(int)(this.body.getWorldCenter().x), 
				(int)(this.body.getWorldCenter().y));
	}
	
	@Override
	protected void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.position.set(new Vector2(position_x,position_y));  
        body = world.createBody(bodyDef);  
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox(width/2, height/2);  
        Fixture fixture = body.createFixture(groundBox, 0.0f);
        fixture.setSensor(true);
        body.setUserData("water");
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
        a = new Animator();
		a.create("data/water1.png", 3);
		super.loadTextures("data/water1.png");
	}
	
}
