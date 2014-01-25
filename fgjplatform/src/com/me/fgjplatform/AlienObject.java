package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class AlienObject extends CreatureObject {
	public AlienObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height,world);
	}
	
	@Override
	public void loadTextures() {
		super.loadTextures("data/Human.png", "data/Alien.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		
		FixtureDef fixtureSensor = new FixtureDef();
        PolygonShape sensorShape = new PolygonShape();
        Vector2[] vertices = { new Vector2(-width,-height/2f), new Vector2(width,-height/2f), 
        		new Vector2(width,height), new Vector2(-width, height) };
        sensorShape.set(vertices);
        fixtureSensor.shape = sensorShape;
        fixtureSensor.isSensor = true;
        Fixture fixture = body.createFixture(fixtureSensor);
        fixture.setUserData("forcefield");
        
		body.setUserData("alien");
	}
}
