package com.me.fgjplatform;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class StaticObject extends BaseObject {
	public StaticObject(float x, float y) {
		super(x, y);
		initPhysicalBody();
	}
	
	private void initPhysicalBody() {
		BodyDef groundBodyDef = new BodyDef();  
        groundBodyDef.position.set(new Vector2(Global.PtoM(position_x), Global.PtoM(position_y)));  
        Body groundBody = world.createBody(groundBodyDef);  
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox((camera.viewportWidth) * 1f, 10.0f);  
        groundBody.createFixture(groundBox, 0.0f);
	}
}
