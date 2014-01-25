package com.me.fgjplatform;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
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
		
		TiledMap tileMap = new TiledMap();
		
	}
	
	private void initDynamicObjects() {
		// player characters
		creatureObjects.add(new AlienObject(100f, 10f + 50f, 64f, 100f,this.world));
		creatureObjects.add(new RobotObject(400f, 10f + 25f, 20f, 50f,this.world));
		
		rectDynamicObjects.add(new BaseRectDynamicObject(900f, 160f, 100f, 100f,this.world));
		rectDynamicObjects.add(new BaseRectDynamicObject(900f, 60f, 100f, 100f,this.world));
	}
	
	private  void initStaticObjects() {
		staticObjects.add(new Ground(-1000f + 2000f, -190f + 50f, 4000f, 100f, this.world));
		staticObjects.add(new Ground(-1000f + 250f, -90f + 500f, 500f, 1000f, this.world));
		
		staticObjects.add(new Ground(-500f + 800f, -90f + 50f, 1600f, 100f, this.world));
		staticObjects.add(new Water(1100f + 50f, -90f + 50f, 100f, 100f, this.world));
		staticObjects.add(new Ground(1200f + 50f, -90f + 50f, 100f, 100f, this.world));
		staticObjects.add(new Water(1300f + 50f, -90f + 50f, 100f, 100f, this.world));
		staticObjects.add(new Ground(1400f + 250f, -90f + 50f, 500f, 100f, this.world));
		staticObjects.add(new Ground(1900f + 250f, -90f + 500f, 500f, 1000f, this.world));
		
		//staticObjects.add(new Ground(-500f + 350f, -90f + 50f, 700f, 100f, this.world));
		//staticObjects.add(new Ground(300f + 200f, -90 + 50f, 400f, 100f, this.world));
		
		staticObjects.add(new Tree(200f + 100f, 10f + 100f, 100f, 200f, this.world));
		staticObjects.add(new Tree(700f + 50f, 10f + 100f, 100f, 200f, this.world));
		staticObjects.add(new Door(1500f + 25f, 10f + 50f, 50f, 100f, this.world));
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
	
	public AlienObject getAlien() {
		for (BaseObject o: creatureObjects) {
			if (o.getClass() == AlienObject.class) {
				return (AlienObject)o;
			}
		}
		return null;
	}
	
	public RobotObject getRobot() {
		for (BaseObject o: creatureObjects) {
			if (o.getClass() == RobotObject.class) {
				return (RobotObject)o;
			}
		}
		return null;
	}
	
	public void removeAlien() {
		creatureObjects.remove(getAlien());
	}
	
	public void removeRobot() {
		creatureObjects.remove(getRobot());
	}
}
