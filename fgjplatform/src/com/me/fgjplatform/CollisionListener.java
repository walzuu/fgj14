package com.me.fgjplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
		if (userDataA == "door" && 
				(String)contact.getFixtureB().getUserData() != "forcefield") {
			handleDoorCollision(bodyB);
			return;
		}
		
		if (userDataA == "water" && 
				(String)contact.getFixtureB().getUserData() != "forcefield") {
			handleWaterCollision(bodyB);
			return;
		}

		if (userDataA == "tree" && 
				(String)contact.getFixtureB().getUserData() == "forcefield") {
			handleTreeRemoval(bodyA);
		}
		
		if (contact.getFixtureB().getUserData() == "feet") {
			if (bodyB.getUserData() == "alien") {
				mapCreator.getAlien().resetJump();
			}
			
			if (bodyB.getUserData() == "robot") {
				mapCreator.getRobot().resetJump();
			}
		}
	}
	
	private void handleTreeRemoval(Body bodyA) {
		mapCreator.removeStaticObject(bodyA);
		bodyA.setUserData("dead");
	}
	
	private void handleDoorCollision(Body bodyB) {
		String userDataB = (String) bodyB.getUserData();
		if (userDataB == "alien" || userDataB == "robot") {
			gameState.sendToGoal(userDataB.toString());
			
			if (userDataB == "alien") {
				mapCreator.removeAlien();
			}
			
			if (userDataB == "robot") {
				mapCreator.removeRobot();
			}
			
			bodyB.setUserData("out");
		}
	}
	
	private void handleWaterCollision(Body bodyB) {
		String userDataB = (String) bodyB.getUserData();
		if (userDataB == "alien" || userDataB == "robot") {
			gameState.kill(userDataB.toString());
			
			if (userDataB == "alien") {
				mapCreator.removeAlien();
			}
			
			if (userDataB == "robot") {
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
