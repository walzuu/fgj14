package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class MapCreator {
	private ArrayList<CreatureObject> creatureObjects;
	private ArrayList<BaseRectDynamicObject> rectDynamicObjects;

	private  ArrayList<StaticObject> groundObjects;
	private World world;
	
	public MapCreator(World world){
		this.world = world;
		createWorld();
	}
	
	private void createWorld() {
		creatureObjects = new ArrayList<CreatureObject>();
		rectDynamicObjects = new ArrayList<BaseRectDynamicObject>();
		groundObjects = new ArrayList<StaticObject>();
		
		initStaticObjects();
		initDynamicObjects();
		
	}
	
	private void initDynamicObjects() {
		creatureObjects.add(new CreatureObject(0f, 300f, 10f, 10f,this.world));
		
		rectDynamicObjects.add(new BaseRectDynamicObject(300f, 110f, 100f, 200f,this.world));
	}
	
	private  void initStaticObjects() {
		groundObjects.add(new StaticObject(0f, 0f,this.world));
	}
	
	public  CreatureObject getPhysicalPlayer() {
		if (creatureObjects.size() > 0) {
			return creatureObjects.get(0);
		}
		return null;
	}
	
	public ArrayList<BaseRectDynamicObject> getRectDynamicObjects() {
		return rectDynamicObjects;
	}

	public  ArrayList<StaticObject> getGroundObjects() {
		return groundObjects;
	}
}
