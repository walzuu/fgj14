package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

public class MapCreator {
	private static ArrayList<CreatureObject> creatureObjects;
	private static ArrayList<BaseRectDynamicObject> rectDynamicObjects;
	private static ArrayList<StaticObject> groundObjects;
	private static World world;
	
	public static void CreateWorld() {
		creatureObjects = new ArrayList<CreatureObject>();
		rectDynamicObjects = new ArrayList<BaseRectDynamicObject>();
		groundObjects = new ArrayList<StaticObject>();
		
		world = FgjPlatform.GetWorld();
		initStaticObjects();
		initDynamicObjects();
		
	}
	
	private static void initDynamicObjects() {
		creatureObjects.add(new CreatureObject(0f, 300f, 10f, 10f));
		
		rectDynamicObjects.add(new BaseRectDynamicObject(300f, 110f, 100f, 200f));
	}
	
	private static void initStaticObjects() {
		groundObjects.add(new StaticObject(0f, 0f));
	}
	
	public static CreatureObject getPhysicalPlayer() {
		if (creatureObjects.size() > 0) {
			return creatureObjects.get(0);
		}
		return null;
	}
}
