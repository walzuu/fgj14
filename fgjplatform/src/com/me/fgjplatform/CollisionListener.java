package com.me.fgjplatform;

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
		
		Object userDataA = contact.getFixtureA().getBody().getUserData();
		Object userDataB = contact.getFixtureB().getBody().getUserData();
		if ( (userDataA == "door" && userDataB == "alien") ||
			(userDataA == "door" && userDataB == "robot")) {
			if (userDataB == "alien") {
				gameState.sendToGoal(mapCreator.getAlien());
			}
			
			if (userDataB == "robot") {
				gameState.sendToGoal(mapCreator.getRobot());
			}
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
