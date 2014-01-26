package com.me.fgjplatform.mechanic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator
{
	
    //private static final int        FRAME_COLS = 3;         // #1
    //private static final int        FRAME_ROWS = 1;         // #2
    
    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    //SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7
    boolean flipped;
    float stateTime;                                        // #8
    
    public void create(String aName, int columns, int rows, int skippedRows)
    {
    	//columns = 4;
    	//rows = 4;
            walkSheet = new Texture(Gdx.files.internal(aName));     // #9
            TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/ 
columns, walkSheet.getHeight() / rows);
            walkFrames = new TextureRegion[columns * (rows-skippedRows)];
            int index = 0;
            for (int i = 0; i < rows - skippedRows; i++) {
                    for (int j = 0; j < columns; j++) {
                            walkFrames[index++] = tmp[i][j];
                    }
            }
            walkAnimation = new Animation(0.1f, walkFrames);              // #11
      //      spriteBatch = new SpriteBatch();                                // #12
            stateTime = 0f;                                                 // #13
            flipped = false;
    }
    
    public void flip()
    {
    	flipped = !flipped;
    }

    public void render(SpriteBatch batch, int x, int y, float width, float height) 
    {
            //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);                                            // #14
            stateTime += Gdx.graphics.getDeltaTime();                       // #15
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);      // #16
           
            //if( flipped ) 
            //{
            //    if( currentFrame.getRegionWidth() > 0 ) 
            //    	currentFrame.flip( true, false );
            //}
            //else if( currentFrame.getRegionWidth() < 0 )
            //{
            //    	currentFrame.flip( true, false );
            //}
            
            
            Sprite sprite = new Sprite();
            sprite.setRegion(currentFrame);
            sprite.setBounds(0, 0, width, height);
            //spriteBatch.begin();
            sprite.setPosition(x-width/2, y-height/2);
            if(flipped)
            	sprite.flip(true, false);
            
            sprite.draw(batch);
            //batch.draw(sprite, x, y);                         // #17
            
            //spriteBatch.end();
    }
}