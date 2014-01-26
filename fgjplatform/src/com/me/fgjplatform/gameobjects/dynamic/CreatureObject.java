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
	private boolean human;
	private boolean robot_;
	
	private Animator walk_anim;
	private Animator idle_anim;
	
	protected FixtureDef fixtureSensorFeet;
	
	public CreatureObject(float x, float y, float width, float height,World world,
			String walkfile, int columns, int rows, int skippedrows, 
			String idlefile, int icolumns, int irows, int iskippedrows, boolean robot) 
	{
		super(x, y, width, height,world);
		initPhysicalBody();
		canJump = true;
		moving = false;
		facingLeft = false;
		walk_anim = new Animator();
		walk_anim.create(walkfile, columns, rows, skippedrows);
		idle_anim = new Animator();
		idle_anim.create(idlefile, icolumns, irows, iskippedrows);
		human = true;
		robot_ = robot;
	}
	

	@Override
	public void draw(SpriteBatch batch)
	{
		if(moving)
		{
			walk_anim.render(batch, (int)(this.getBody().getWorldCenter().x), 
					(int)(this.getBody().getWorldCenter().y), width, height);
			
		}
		else 
		{
			idle_anim.render(batch, (int)(this.getBody().getWorldCenter().x), 
					(int)(this.getBody().getWorldCenter().y), width, height);
			//super.draw(batch);
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
			//sprite.flip(true, false);
			walk_anim.flip();
			idle_anim.flip();
		}
		facingLeft = left;
	}
	public void transformation()
	{
		if(!robot_)
		{
		if(human)
		{
			walk_anim.create("data/Alien_Walking_POT.png", 4, 4, 1);
			idle_anim.create("data/Alien_Idle.png", 4, 1, 0);
		}
		else
		{
			walk_anim.create("data/Human_Walking_POT.png", 4, 4, 1);
			idle_anim.create("data/Human_Idle.png",  4, 1, 0);
		}
		}
		else
		{
			if(human)
			{
				walk_anim.create("data/Robot_Moving.png", 4, 1, 0);
				idle_anim.create("data/Robot_Idle.png", 4, 1, 0);
			}
			else
			{
				walk_anim.create("data/Jelly_Walking.png", 4, 1, 0);
				idle_anim.create("data/Jelly_Idle.png",  4, 1, 0);
			}
			
		}
		
		
		human = !human;
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
