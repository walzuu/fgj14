package com.me.fgjplatform.mechanic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.GameState;

public class CollisionListener implements ContactListener {

	@SuppressWarnings("unused")
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
			handleTreeFading(contact.getFixtureA());
		}
		
		if (userDataA == "faded_tree" && userDataB == "robot") {
			handleTreeFadingRobotOn(contact.getFixtureA());
		}
		
		if (userDataA == "tree") {
			System.out.println("found tree contact");
		}
		
		if (userDataA == "faded_tree_robot_on contact") {
			System.out.println("found faded_tree_robot_on");
		}
		
		if (userDataA == "faded_tree") {
			System.out.println("found faded_tree contact");
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
	
	private void handleTreeFading(Fixture fixtureTree) {
		fixtureTree.getBody().setUserData("faded_tree");
		fixtureTree.setSensor(true);
	}
	
	private void handleTreeFadingRobotOn(Fixture fixtureTree) {
		fixtureTree.getBody().setUserData("faded_tree_robot_on");
		fixtureTree.setSensor(true);
//		Filter filter = fixtureTree.getFilterData();
//		filter.maskBits = ~(0x0002 | 0x0008); // I do not collide with alien and robot
//		fixtureTree.setFilterData(filter);
	}
	
	private void handleTreeFadeBack(Fixture fixtureTree) {
		try {
			fixtureTree.getBody().setUserData("tree");
			fixtureTree.setSensor(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Filter filter = fixtureTree.getFilterData();
//		filter.maskBits = ~(0x0002); // I do not collide with alien
//		fixtureTree.setFilterData(filter);
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
		try {
			Body bodyA = contact.getFixtureA().getBody();
			Body bodyB = contact.getFixtureB().getBody();
			Object userDataA = bodyA.getUserData();
			Object userDataB = bodyB.getUserData();
			
			if (userDataA == "faded_tree_robot_on") {
				System.out.println("found faded_tree_robot_on");
			}
			
			/*
			if (userDataA != null && userDataB != null)
				System.out.println(userDataA + " " + userDataB);
			*/
			if (userDataA == "faded_tree" && contact.getFixtureB().getUserData() == "forcefield") {
				handleTreeFadeBack(contact.getFixtureA());
				System.out.println("handle faded tree");
			}
			
			if (userDataA == "faded_tree_robot_on" && userDataB == "robot") {
				//handleTreeFadeBack(contact.getFixtureA()); // ! - temporary bugged
				System.out.println("handle tree fadeback");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//System.out.println("post "+contact);
	}

}
