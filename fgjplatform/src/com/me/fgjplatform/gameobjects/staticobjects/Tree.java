package com.me.fgjplatform.gameobjects.staticobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.Global;

public class Tree extends StaticObject {

	public Tree(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		float alphaMod = 1.0f;
		if (this.body != null && this.body.getUserData() != "tree" ){
			alphaMod = 0.5f;
		}
		if (this.getBody() != null)
		{
			this.sprite.setPosition(this.getBody().getWorldCenter().x - this.sprite.getWidth()/2, 
					this.getBody().getWorldCenter().y - this.sprite.getHeight()/2);
			this.sprite.setRotation(this.getBody().getTransform().getRotation() / (float) Math.PI * 180);
			this.sprite.draw(batch, alphaMod);
		}
	}
	
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		super.loadTextures("data/tree.png", "data/tree2.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		bodyDef = new BodyDef();  
        bodyDef.position.set(new Vector2(position_x,position_y));  
        setBody(world.createBody(bodyDef));
        
        PolygonShape groundBox = new PolygonShape();  
        groundBox.set(new Vector2[] { new Vector2(-width/2, height/2), new Vector2(width/2, height/2)
        								, new Vector2(-width/2, -height/2), new Vector2(width/2, -height/2) });
        
        fixtureDef = new FixtureDef();  
        fixtureDef.shape = groundBox;  
        fixtureDef.friction = 0.0f;
        fixtureDef.density = 0.0f;

        this.fixture = getBody().createFixture(fixtureDef);
		
		Filter f = this.fixture.getFilterData();
		f.maskBits = ~(Global.CATEGORY_ALIEN); // I do not collide with (alien)
		f.categoryBits = Global.CATEGORY_TREE;  // I am 
		fixture.setFilterData(f);
		
		this.getBody().setUserData("tree");
	}

}
