package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class CreatureObject extends BaseObject {
	public CreatureObject(float x, float y, float width, float height,World world) {
		super(x, y, width, height,world);
		initPhysicalBody();
	}
	
	public Body GetBody() {
		return body;
	}
	
	private void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(Global.WIDTH / 2, Global.HEIGHT / 2);  
        body = world.createBody(bodyDef);  
        CircleShape dynamicCircle = new CircleShape();  
        dynamicCircle.setRadius(30f);  
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicCircle;  
        fixtureDef.density = 0.5f;  
        fixtureDef.friction = 0.1f;  
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
	}
	
	public void jump(){
		this.body.applyLinearImpulse(0, 10000000, this.body.getWorldCenter().x, this.body.getWorldCenter().y, true);
	}
	
	public void move(float transform){
		this.body.setLinearVelocity(transform, this.body.getLinearVelocity().y);
	}
}
