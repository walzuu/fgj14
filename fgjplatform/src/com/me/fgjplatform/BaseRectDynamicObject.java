package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class BaseRectDynamicObject extends BaseObject {


	public BaseRectDynamicObject(float x, float y, float width, float height,World world) {
		super(x, y, width, height, world);
		
		initPhysicalBody();
	}

	private void initPhysicalBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position_x, position_y);
		
		body = world.createBody(bodyDef);
		
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(width/2, height/2);
		
		body.createFixture(bodyShape, 1.0f);
	}
}
