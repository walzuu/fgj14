package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class CreatureObject extends BaseObject {
	private boolean canJump;
	private boolean moving;
	
	private Animator anim;
	
	public CreatureObject(float x, float y, float width, float height,World world) {
		super(x, y, width, height,world);
		initPhysicalBody();
		canJump = true;
		moving = false;
		anim = new Animator();
		anim.create("data/Alien.png", 2);
	}
	

	@Override
	public void draw(SpriteBatch batch)
	{
		if(moving)
		{
			anim.render(batch, (int)(this.body.getWorldCenter().x), 
					(int)(this.body.getWorldCenter().y), width, height);
		}
		else 
		{
			super.draw(batch);
		}
	}
	
	public Body GetBody() {
		return body;
	}
	
	protected void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(position_x, position_y);  
        body = world.createBody(bodyDef);  
        body.setFixedRotation(true);
        PolygonShape dynamicBox = new PolygonShape();  
        dynamicBox.setAsBox(width/2, height/2);  
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicBox;  
        fixtureDef.density = 0.9f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        
        FixtureDef fixtureSensor = new FixtureDef();
        PolygonShape sensorShape = new PolygonShape();
        Vector2[] vertices = { new Vector2(-width/2.2f,-height/1.9f), new Vector2(width/2.2f,-height/1.9f), 
        		new Vector2(width/2.2f,-height/2), new Vector2(-width/2.2f, -height/2) };
        sensorShape.set(vertices);
        fixtureSensor.shape = sensorShape;
        fixtureSensor.isSensor = true;
        Fixture fixture = body.createFixture(fixtureSensor);
        fixture.setUserData("feet");
	}
	
	public void resetMove()
	{
		moving = false;
	}
	
	public void resetJump() {
		canJump = true;
	}
	
	public void jump(){
		if (canJump) {
			canJump = true;
			this.body.applyLinearImpulse(0, 10000000, this.body.getWorldCenter().x, this.body.getWorldCenter().y, true);
		}
	}
	
	public void move(float transform)
	{
		this.body.setLinearVelocity(transform, this.body.getLinearVelocity().y);
		moving = true;
	}
}
