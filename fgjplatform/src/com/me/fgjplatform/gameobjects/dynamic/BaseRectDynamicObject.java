package com.me.fgjplatform.gameobjects.dynamic;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.gameobjects.BaseObject;

public class BaseRectDynamicObject extends BaseObject {

	public BaseRectDynamicObject(float x, float y, float width, float height,World world) {
		super(x, y, width, height, world);
		
		initPhysicalBody();
	}

	@Override
	public void loadTextures() {
		this.loadTextures("data/crate.png", "data/crate2.png");
	}
	
	protected void initPhysicalBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position_x, position_y);
		
		setBody(world.createBody(bodyDef));
		getBody().setFixedRotation(true);
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(width/2, height/2);
		
		this.fixture = getBody().createFixture(bodyShape, 1.0f);
	}
}
