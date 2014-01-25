package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class BaseRectDynamicObject extends BaseObject {
	protected float width;
	protected float height;

	public BaseRectDynamicObject(float x, float y, float width, float height,World world) {
		super(x, y, width, height,world);
		this.width = width;
		this.height = height;
		initPhysicalBody();
	}

	private void initPhysicalBody() {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position_x, position_y);
		
		body = world.createBody(bodyDef);
		
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(width/2, height/2);
		
		body.createFixture(bodyShape, 1.0f);
	}

}
