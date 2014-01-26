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
	private boolean facingLeft;
	private Animator anim;
	
	protected FixtureDef fixtureSensorFeet;
	
	public CreatureObject(float x, float y, float width, float height,World world,
			String walkfile) {
		super(x, y, width, height,world);
		initPhysicalBody();
		canJump = true;
		moving = false;
		facingLeft = false;
		anim = new Animator();
		anim.create(walkfile, 1);
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
	
	public Body GetBody()
	{
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
        fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicBox;  
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.3f;
        this.fixture = body.createFixture(fixtureDef);
        
        fixtureSensorFeet = new FixtureDef();
        PolygonShape sensorShape = new PolygonShape();
        Vector2[] vertices = { new Vector2(-width/2.2f,-height/1.9f), new Vector2(width/2.2f,-height/1.9f), 
        		new Vector2(width/2.2f,-height/2), new Vector2(-width/2.2f, -height/2) };
        sensorShape.set(vertices);
        fixtureSensorFeet.shape = sensorShape;
        fixtureSensorFeet.isSensor = true;
        Fixture fixture = body.createFixture(fixtureSensorFeet);
        fixture.setUserData("feet");
	}
	
	public void resetMove()
	{
		moving = false;
	}
	
	public void resetJump() {
		canJump = true;
	}
	
	public void faceLeft(boolean left)
	{
		if(left != facingLeft)
		{
			sprite.flip(true, false);
			anim.flip();
		}
		facingLeft = left;
	}
	
	public void jump()
	{
		if (canJump) {
			canJump = false;
			this.body.applyLinearImpulse(0, 1000000000f, this.body.getWorldCenter().x, this.body.getWorldCenter().y, true);
		}
	}
	
	public void move(float transform)
	{
		this.body.setLinearVelocity(transform, this.body.getLinearVelocity().y);
		moving = true;
	}
}
