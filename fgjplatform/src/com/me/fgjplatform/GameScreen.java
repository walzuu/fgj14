package com.me.fgjplatform;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends DefaultScreen implements InputProcessor  {

	private static OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private static World world;
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private Body playerBody;
	private CreatureObject player;
	private GameState gameState;
	static final float BOX_STEP=1/60f;  
	static final int BOX_VELOCITY_ITERATIONS=6;  
	static final int BOX_POSITION_ITERATIONS=2; 

	private MapCreator mapCreator;

	public GameScreen(Game game) {
		super(game);

		Gdx.input.setInputProcessor(this);

		world = new World(new Vector2(0, -119.81f), true);
		mapCreator = new MapCreator(world);
		camera = new OrthographicCamera();  
		camera.viewportHeight = Global.HEIGHT;  
		camera.viewportWidth = Global.WIDTH;  
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0);  
		camera.zoom = 1f;
		camera.update();  

		player = mapCreator.getPhysicalPlayer(0);
		playerBody = player.GetBody();

		batch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		// CollisionListener calls proper functions when user collides with
		// a special object
		gameState = new GameState(world);
		world.setContactListener(new CollisionListener(world, gameState, mapCreator));
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render(float delta) {	
		checkGameEnding();
		updateMovement();
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		for (BaseObject o: mapCreator.getRectDynamicObjects()) {
			o.draw(batch);
		}
		
		for (BaseObject o: mapCreator.getStaticObjects()) {
			o.draw(batch);
		}
		
		for (BaseObject o: mapCreator.getCreatureObjects()) {
			o.draw(batch);
		}
		updateCamera();
		batch.end();
		debugRenderer.render(world, camera.combined);
		world.step(1/60f, 6, 2);
		sweepDeadBodies();
	}

	public void checkGameEnding() {
		if (gameState != null) {
			switch (gameState.getGameEnding()) {
			case none:
				break;
			case win:
				System.out.println("YOU WIN!");
				break;
			case lose:
				System.out.println("YOU LOSE!");
				break;
			}
		}
	}
	
	public void updateCamera(){
		playerBody = player.GetBody();
        camera.position.set(playerBody.getPosition().x, playerBody.getPosition().y, 0);
        camera.update();
	}
	
	public void updateMovement() {
		if(Gdx.input.isKeyPressed(Keys.A)) 
			player.move(Gdx.graphics.getDeltaTime()*-5000f);
		if(Gdx.input.isKeyPressed(Keys.D)) 
			player.move(Gdx.graphics.getDeltaTime()*5000f);
	}
	
	public void sweepDeadBodies() {
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		for (Body body: bodies) {
			if (body!=null) {
				String data = (String) body.getUserData();
				if (data == "dead" || data == "out") {
					world.destroyBody(body);
					body.setUserData(null);
					body = null;
				}
			}
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (gameState.getGameEnding() == Global.GameEnding.none) {
			if(keycode == Keys.SPACE){
				player.jump();
			}
	
			if (keycode == Keys.NUM_1 && player.getClass() != AlienObject.class) {
				if (mapCreator.getAlien() != null) {
					player.move(0f);
					player = mapCreator.getAlien();
					((AlienObject)player).switchForceField(false);
					for (BaseObject o: mapCreator.getCreatureObjects()) {
						o.changeTexture(0);
					}
					
					for (BaseObject o: mapCreator.getRectDynamicObjects()) {
						o.changeTexture(0);
					}
					
					for (BaseObject o: mapCreator.getStaticObjects()) {
						o.changeTexture(0);
					}
				}
			}
			
			if (keycode == Keys.NUM_2) {
				if (mapCreator.getRobot() != null && player.getClass() != RobotObject.class) {
					((AlienObject)player).switchForceField(true);
					player.move(0f);
					player = mapCreator.getRobot();
					for (BaseObject o: mapCreator.getCreatureObjects()) {
						o.changeTexture(1);
					}
					
					for (BaseObject o: mapCreator.getRectDynamicObjects()) {
						o.changeTexture(1);
					}
					
					for (BaseObject o: mapCreator.getStaticObjects()) {
						o.changeTexture(1);
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.A){
			player.move(0f);
		}
		
		if(keycode == Keys.D){
			player.move(0f);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
