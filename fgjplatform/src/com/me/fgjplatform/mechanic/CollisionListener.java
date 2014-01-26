package com.me.fgjplatform.mechanic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.GameState;
import com.me.fgjplatform.gameobjects.dynamic.CreatureObject;
import com.me.fgjplatform.screens.GameScreen;

public class CollisionListener implements ContactListener {

	@SuppressWarnings("unused")
	private World world;
	private GameState gameState;
	private MapCreator mapCreator;
	private boolean isTreeFaded;
	private Fixture lateSensorSetting;
	private GameScreen player;
	
	public CollisionListener(World world, GameState gameState, MapCreator mapCreator, GameScreen player) {
		this.world = world;
		this.gameState = gameState;
		this.mapCreator = mapCreator;
		this.isTreeFaded = false;
		this.lateSensorSetting = null;
		this.player = player;
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
		
		Vector2[] vertices = { new Vector2(-50,30), new Vector2(-50,31), 
				new Vector2(50,31), new Vector2(50,30)};
		((PolygonShape)fixtureTree.getShape()).set(vertices);
		isTreeFaded = true;

	}
	
	private void handleTreeFadingRobotOn(Fixture fixtureTree) {

		fixtureTree.getBody().setUserData("faded_tree_robot_on");

		Vector2[] vertices = { new Vector2(-50,30), new Vector2(-50,31), 
				new Vector2(50,31), new Vector2(50,30)};
		((PolygonShape)fixtureTree.getShape()).set(vertices);

	}
	
	private void handleTreeFadeBack(Fixture fixtureTree) {

		try {

		fixtureTree.getBody().setUserData("tree");

		((PolygonShape)fixtureTree.getShape()).setAsBox(50f, 100f);

		isTreeFaded = false;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void handleDoorCollision(Body bodyB) {
		String userDataB = (String) bodyB.getUserData();
		if (userDataB == "alien" || userDataB == "robot") {
			
			gameState.sendToGoal(userDataB.toString());
			
			if (userDataB == "alien") {
				player.updateTargetPos(mapCreator.getRobot());
				mapCreator.removeAlien();
				
			}
			
			if (userDataB == "robot") {
				player.updateTargetPos(mapCreator.getAlien());
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


			if (userDataA == "faded_tree" && (String)contact.getFixtureB().getUserData() == "forcefield") {
				handleTreeFadeBack(contact.getFixtureA());

			}
			
			if (userDataA == "faded_tree_robot_on" && (String)contact.getFixtureB().getUserData() == "forcefield") {

				isTreeFaded = false;
			}
			
			if (userDataA == "faded_tree_robot_on" && userDataB == "robot") {

				if (!isTreeFaded) {
					handleTreeFadeBack(contact.getFixtureA());

				}

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
	}

}
