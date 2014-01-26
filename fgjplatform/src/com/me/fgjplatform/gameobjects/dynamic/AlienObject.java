package com.me.fgjplatform.gameobjects.dynamic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class AlienObject extends CreatureObject {
	private Fixture forceFieldFixture;
	
	public AlienObject(float x, float y, float width, float height, World world) 
	{
		super(x, y, width, height,world, "data/Human_Walking_POT.png", 4, 4);
	}
	
	@Override
	public void loadTextures() 
	{
		super.loadTextures("data/Human.png", "data/Alien.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		Filter filter = fixture.getFilterData();
		filter.maskBits = ~(0x0008 | 0x0004); // I do not collide with:
		filter.categoryBits = 0x0002;  // I am
		fixture.setFilterData(filter);
		
		FixtureDef fixtureSensor = new FixtureDef();
        PolygonShape sensorShape = new PolygonShape();
        Vector2[] vertices = { new Vector2(0,-height * 2), new Vector2(100,-height * 2), new Vector2(100,1-height * 2), new Vector2(0,1-height * 2) };
        sensorShape.set(vertices);
        fixtureSensor.shape = sensorShape;
        fixtureSensor.isSensor = true;
        this.forceFieldFixture = getBody().createFixture(fixtureSensor);
        this.forceFieldFixture.setUserData("forcefield");
		getBody().setUserData("alien");
	}
	
	public void switchForceField(boolean isFieldOn) {
		if (this.forceFieldFixture != null) {
			Vector2[] verticesOn = { new Vector2(-width,-height/2f), new Vector2(width,-height/2f), 
	        		new Vector2(width,height), new Vector2(-width, height) };
			Vector2[] verticesOff = { new Vector2(-width,-height * 2), new Vector2(width,-height * 2), new Vector2(width,1-height * 2), new Vector2(-width,1-height * 2) };
			
			if (isFieldOn) {
				((PolygonShape)forceFieldFixture.getShape()).set(verticesOn);
			}
			
			else {
				((PolygonShape)forceFieldFixture.getShape()).set(verticesOff);
			}
		}
	}
}
