package com.me.fgjplatform.gameobjects.staticobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Door extends StaticObject {

	public Door(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
	}
	
	@Override
	public void loadTextures() {
		super.loadTextures("data/doorway.png", "data/doorway.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.position.set(new Vector2(position_x,position_y));  
        setBody(world.createBody(bodyDef));
        
        PolygonShape groundBox = new PolygonShape();  
        groundBox.set(new Vector2[] { new Vector2(-width/4, height/3), new Vector2(width/4, height/3), 
        							new Vector2(-width/4, -height/3), new Vector2(width/4, -height/3) });
        Fixture fix = getBody().createFixture(groundBox, 0.0f);
        fix.setSensor(true);
        
        getBody().setUserData("door");
	}
	
}
