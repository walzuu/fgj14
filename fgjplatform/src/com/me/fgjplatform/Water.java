package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Water extends StaticObject{

	public Water(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
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
	}
	
	@Override
	public Sprite loadSprite() {
		// TODO Auto-generated method stub
		return super.loadSprite("data/test2.png");
	}
	
}
