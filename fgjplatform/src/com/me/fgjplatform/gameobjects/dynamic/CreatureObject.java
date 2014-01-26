package com.me.fgjplatform.gameobjects.dynamic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.gameobjects.BaseObject;
import com.me.fgjplatform.mechanic.Animator;

public class CreatureObject extends BaseObject {
	private boolean canJump;
	private boolean moving;
	private boolean facingLeft;
	private Animator anim;
	
	protected FixtureDef fixtureSensorFeet;
	
	public CreatureObject(float x, float y, float width, float height,World world,
			String walkfile, int columns, int rows) 
	{
		super(x, y, width, height,world);
		initPhysicalBody();
		canJump = true;
		moving = false;
		facingLeft = false;
		anim = new Animator();
		anim.create(walkfile, columns, rows);
	}
	

	@Override
	public void draw(SpriteBatch batch)
	{
		if(moving)
		{
			anim.render(batch, (int)(this.getBody().getWorldCenter().x), 
					(int)(this.getBody().getWorldCenter().y), width, height);
			
		}
		else 
		{
			super.draw(batch);
		}
	}
	
	public Body GetBody()
	{
		return getBody();
	}
	
	protected void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(position_x, position_y);  
        setBody(world.createBody(bodyDef));  
        getBody().setFixedRotation(true);
        PolygonShape dynamicBox = new PolygonShape();  
        dynamicBox.setAsBox(width/2, height/2);  
        fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicBox;  
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.3f;
        this.fixture = getBody().createFixture(fixtureDef);
        
        fixtureSensorFeet = new FixtureDef();
        PolygonShape sensorShape = new PolygonShape();
        Vector2[] vertices = { new Vector2(-width/2.2f,-height/1.9f), new Vector2(width/2.2f,-height/1.9f), 
        		new Vector2(width/2.2f,-height/2), new Vector2(-width/2.2f, -height/2) };
        sensorShape.set(vertices);
        fixtureSensorFeet.shape = sensorShape;
        fixtureSensorFeet.isSensor = true;
        Fixture fixture = getBody().createFixture(fixtureSensorFeet);
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
			this.getBody().applyLinearImpulse(0, 1000000000f, this.getBody().getWorldCenter().x, this.getBody().getWorldCenter().y, true);
		}
	}
	
	public void move(float transform)
	{
		this.getBody().setLinearVelocity(transform, this.getBody().getLinearVelocity().y);
		moving = true;
	}
}
