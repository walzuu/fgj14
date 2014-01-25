package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class MapCreator {
	private ArrayList<CreatureObject> creatureObjects;
	private ArrayList<BaseRectDynamicObject> rectDynamicObjects;

	private  ArrayList<StaticObject> staticObjects;
	private World world;
	
	public MapCreator(World world){
		this.world = world;
		createWorld();
	}
	
	private void createWorld() {
		creatureObjects = new ArrayList<CreatureObject>();
		rectDynamicObjects = new ArrayList<BaseRectDynamicObject>();
		staticObjects = new ArrayList<StaticObject>();
		
		initStaticObjects();
		initDynamicObjects();
		
	}
	
	private void initDynamicObjects() {
		// player characters
		creatureObjects.add(new AlienObject(100f, 300f, 50f, 100f,this.world));
		creatureObjects.add(new RobotObject(200f, 300f, 20f, 50f,this.world));
		
		rectDynamicObjects.add(new BaseRectDynamicObject(600f, 160f, 100f, 100f,this.world));
		rectDynamicObjects.add(new BaseRectDynamicObject(600f, 60f, 100f, 100f,this.world));
	}
	
	private  void initStaticObjects() {
		staticObjects.add(new Ground(0f + 1000f, -40f - 50f, 2000f, 100f, this.world));
		
		staticObjects.add(new Ground(0f + 100f, 10f - 50f, 200f, 100f, this.world));
		staticObjects.add(new Ground(300f + 200f, 10f - 50f, 400f, 100f, this.world));
		staticObjects.add(new Tree(400f + 20f, 10f + 50f, 20f, 100f, this.world));
		staticObjects.add(new Water(200f + 50f, 10 - 50f, 100f, 100f, this.world));
		staticObjects.add(new Door(800f + 25f, 10f + 50f, 50f, 100f, this.world));
	}
	
	public  CreatureObject getPhysicalPlayer(int index) {
		if (creatureObjects.size() > index) {
			return creatureObjects.get(index);
		}
		return null;
	}
	
	public ArrayList<BaseRectDynamicObject> getRectDynamicObjects() {
		return rectDynamicObjects;
	}

	public  ArrayList<StaticObject> getStaticObjects() {
		return staticObjects;
	}
	
	public ArrayList<CreatureObject> getCreatureObjects() {
		return creatureObjects;
	}
}
