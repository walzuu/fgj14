package com.me.fgjplatform;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class StaticObject extends BaseObject {
	public StaticObject(float x, float y,World world) {
		super(x, y, (Global.WIDTH) * 2f, 20.0f,world);
		initPhysicalBody();
	}
	
	private void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.position.set(new Vector2(Global.PtoM(position_x), Global.PtoM(position_y)));  
        body = world.createBody(bodyDef);  
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox((Global.WIDTH) * 1f, 10.0f);  
        body.createFixture(groundBox, 0.0f);
	}
}
