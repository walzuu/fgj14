package com.me.fgjplatform.gameobjects.dynamic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.me.fgjplatform.Global;

public class Box extends BaseRectDynamicObject{
	protected Body staticBody;
	protected Fixture staticBodyFixture;
	

	public Box(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
	}
	
	@Override
	public void loadTextures() {
		this.loadTextures("data/crate.png", "data/crate2.png");
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		
		staticBody.setTransform(this.body.getPosition(), this.body.getAngle());
	}

	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		
		Filter filter = fixture.getFilterData();
		filter.maskBits = ~(Global.CATEGORY_ALIEN | Global.CATEGORY_BOX_STATIC);
		filter.categoryBits = Global.CATEGORY_BOX_DYNAMIC;
		fixture.setFilterData(filter);
		
		
		// Inner static body object to disable alien moving box
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(position_x, position_y);
		
		this.staticBody = world.createBody(bodyDef);
		this.staticBody.setFixedRotation(true);
		PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(width/2, height/2);
		
		staticBodyFixture = staticBody.createFixture(bodyShape, 1.0f);
		Filter filterStatic = staticBodyFixture.getFilterData();
		filterStatic.maskBits = ~(Global.CATEGORY_ROBOT | Global.CATEGORY_BOX_DYNAMIC);
		filterStatic.categoryBits = Global.CATEGORY_BOX_STATIC;
		staticBodyFixture.setFilterData(filterStatic);
	}
	
}
