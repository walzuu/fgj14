package com.me.fgjplatform;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BaseObject {
	protected float position_x;
	protected float position_y;
	protected BodyDef bodyDef;
	protected Body body;
	protected World world;
	protected OrthographicCamera camera;

	// Constructor
	public BaseObject(float x, float y) {
		this.position_x = x;
		this.position_y = y;
		this.world = FgjPlatform.GetWorld();
		this.camera = FgjPlatform.GetCamera();
	}
}
