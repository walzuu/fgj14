package com.me.fgjplatform;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class StaticObject extends BaseObject {
	public StaticObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		initPhysicalBody();
	}
	
	private void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.position.set(new Vector2(position_x,position_y));  
        body = world.createBody(bodyDef);  
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox(width/2, height/2);  
        body.createFixture(groundBox, 0.0f);
	}
}
