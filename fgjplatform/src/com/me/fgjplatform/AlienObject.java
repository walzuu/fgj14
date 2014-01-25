package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class AlienObject extends CreatureObject {
	private Fixture forceFieldFixture;
	
	public AlienObject(float x, float y, float width, float height, World world) 
	{
		super(x, y, width, height,world, "data/Human.png", 4, 3);
	}
	
	@Override
	public void loadTextures() 
	{
		super.loadTextures("data/Human.png", "data/Alien.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		
		FixtureDef fixtureSensor = new FixtureDef();
        PolygonShape sensorShape = new PolygonShape();
        Vector2[] vertices = { new Vector2(0,0), new Vector2(1,0), new Vector2(1,1), new Vector2(0,1) };
        sensorShape.set(vertices);
        fixtureSensor.shape = sensorShape;
        fixtureSensor.isSensor = true;
        this.forceFieldFixture = body.createFixture(fixtureSensor);
        this.forceFieldFixture.setUserData("forcefield");
		body.setUserData("alien");
	}
	
	public void switchForceField(boolean isFieldOn) {
		if (this.forceFieldFixture != null) {
			Vector2[] verticesOn = { new Vector2(-width,-height/2f), new Vector2(width,-height/2f), 
	        		new Vector2(width,height), new Vector2(-width, height) };
			Vector2[] verticesOff = { new Vector2(0,0), new Vector2(1,0), new Vector2(1,1), new Vector2(0,1) };
			
			if (isFieldOn) {
				((PolygonShape)forceFieldFixture.getShape()).set(verticesOn);
			}
			
			else {
				((PolygonShape)forceFieldFixture.getShape()).set(verticesOff);
			}
		}
	}
}
