package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BaseRectDynamicObject extends BaseObject {
	protected float width;
	protected float height;

	public BaseRectDynamicObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.width = width;
		this.height = height;
		initPhysicalBody();
	}

	private void initPhysicalBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position_x, position_y);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(width/2, height/2);
		
		body.createFixture(bodyShape, 1.0f);
	}

}
