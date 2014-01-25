package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class CreatureObject extends BaseObject {
	public CreatureObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		initPhysicalBody();
	}
	
	public Body GetBody() {
		return body;
	}
	
	private void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;  
        bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);  
        body = world.createBody(bodyDef);  
        CircleShape dynamicCircle = new CircleShape();  
        dynamicCircle.setRadius(5f);  
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicCircle;  
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1;  
        body.createFixture(fixtureDef);
	}
}
