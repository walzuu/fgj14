package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.World;

public class FlyingMonster extends CreatureObject {
	public FlyingMonster(float x, float y, float width, float height,
			World world) {
		super(x, y, width, height, world);
	}
	
}

/* AlienObject.java code below:
package com.me.fgjplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class AlienObject extends CreatureObject {
	public AlienObject(float x, float y, float width, float height, World world) {
		super(x, y, width, height,world);
	}
	
	@Override
	public Sprite loadSprite() {
		return super.loadSprite("data/alien.png");
	}
	
	@Override
	protected void initPhysicalBody() {
		super.initPhysicalBody();
		body.setUserData("alien");
	}
}
*/