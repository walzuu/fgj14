package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionListener implements ContactListener {

	private World world;
	private GameState gameState;
	private MapCreator mapCreator;
	
	public CollisionListener(World world, GameState gameState, MapCreator mapCreator) {
		this.world = world;
		this.gameState = gameState;
		this.mapCreator = mapCreator;
	}
	
	@Override
	public void beginContact(Contact contact) {
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		Object userDataA = bodyA.getUserData();
		Object userDataB = bodyB.getUserData();
		if ( (userDataA == "door" && userDataB == "alien") ||
			(userDataA == "door" && userDataB == "robot") ) {
			
			if (userDataB == "alien") {
				gameState.sendToGoal("alien");
				mapCreator.removeAlien();
			}
			
			if (userDataB == "robot") {
				gameState.sendToGoal("robot");
				mapCreator.removeRobot();
			}
			
			bodyB.setUserData("dead");
		}
	}

	@Override
	public void endContact(Contact contact) {
		//System.out.println("over "+contact);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		//System.out.println("pre "+contact);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//System.out.println("post "+contact);
	}

}
