package com.me.fgjplatform.mechanic;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.me.fgjplatform.gameobjects.BaseObject;
import com.me.fgjplatform.gameobjects.dynamic.AlienObject;
import com.me.fgjplatform.gameobjects.dynamic.BaseRectDynamicObject;
import com.me.fgjplatform.gameobjects.dynamic.Box;
import com.me.fgjplatform.gameobjects.dynamic.CreatureObject;
import com.me.fgjplatform.gameobjects.dynamic.RobotObject;
import com.me.fgjplatform.gameobjects.staticobjects.Door;
import com.me.fgjplatform.gameobjects.staticobjects.Ground;
import com.me.fgjplatform.gameobjects.staticobjects.StaticObject;
import com.me.fgjplatform.gameobjects.staticobjects.Tree;
import com.me.fgjplatform.gameobjects.staticobjects.Water;
import com.me.fgjplatform.staticsprites.Grass;
import com.me.fgjplatform.staticsprites.Sky;
import com.me.fgjplatform.staticsprites.StaticSprite;

public class MapCreator {
	private static final float spawnGrassFrequency = 2.0f;
	
	private ArrayList<CreatureObject> creatureObjects;
	private ArrayList<BaseRectDynamicObject> rectDynamicObjects;

	private ArrayList<StaticObject> staticObjects;
	private ArrayList<StaticSprite> staticSprites; // non-collision sprite and background 
	private World world;
	
	public MapCreator(World world){
		this.world = world;
		createWorld();
	}
	
	private void createWorld() {
		creatureObjects = new ArrayList<CreatureObject>();
		rectDynamicObjects = new ArrayList<BaseRectDynamicObject>();
		staticObjects = new ArrayList<StaticObject>();
		staticSprites = new ArrayList<StaticSprite>();
		
		initStaticObjects();
		initDynamicObjects();
		initStaticSprites();
	}
	
	private void initDynamicObjects() {
		// player characters
		creatureObjects.add(new AlienObject(100f, 10f + 50f, 64f, 100f,this.world));
		creatureObjects.add(new RobotObject(400f, 10f + 25f, 50f, 50f,this.world));
		
		rectDynamicObjects.add(new Box(900f, 160f, 98f, 100f,this.world));
		rectDynamicObjects.add(new Box(900f, 60f, 98f, 100f,this.world));
	}
	
	private  void initStaticObjects() {
		for (int i = 0; i < 45; ++i) {
			staticObjects.add(new Ground(-850f+i*100f, -140f, 100f, 100f, this.world, false));
		}
		
		for (int i = 0; i < 20; ++i) {
			staticObjects.add(new Ground(-850f+i*100f, -40f, 100f, 100f, this.world, true));
		}
		
		staticObjects.add(new Water(1100f + 50f, -94f + 50f, 100f, 92f, this.world));
		staticObjects.add(new Ground(1200f + 50f, -90f + 50f, 100f, 100f, this.world, true));
		staticObjects.add(new Water(1300f + 50f, -94f + 50f, 100f, 92f, this.world));
		
		for (int i = 0; i < 20; ++i) {
			staticObjects.add(new Ground(1450f + i*100f, -90f + 50f, 100f, 100f, this.world, true));
		}

		staticObjects.add(new Tree(200f + 100f, 110f, 100f, 200f, this.world));
		staticObjects.add(new Tree(700f, 110f, 100f, 200f, this.world));
		staticObjects.add(new Door(1500f + 100f, 10f + 150f, 200f, 300f, this.world));
	}
	
	private void initStaticSprites() {
		staticSprites.add(new Sky(-900f, 0f, 4500f, 440f));
		
		for (int i = 0; i < 45; ++i) {
			int type = MathUtils.random(0,4);
			
			if (MathUtils.random(0.0f,spawnGrassFrequency) < 1.0f && i!= 20 && i!= 22 && i!=24) {
				staticSprites.add(new Grass(-900f+i*100f, 0f, 100f, 100f, type));
			}
		}
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
	
	public ArrayList<StaticSprite> getStaticSprites() {
		return staticSprites;
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
	
	public void removeStaticObject(Body body) {
		StaticObject objectToRemove = null;
		for (StaticObject o : staticObjects) {
			if (o.getBody() == body) {
				objectToRemove = o;
				break;
			}
		}
		
		if (objectToRemove != null) {
			staticObjects.remove(objectToRemove);
		}
	}
}
