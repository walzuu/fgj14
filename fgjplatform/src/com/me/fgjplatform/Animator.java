package com.me.fgjplatform;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator
{
	
    private static final int        FRAME_COLS = 3;         // #1
    private static final int        FRAME_ROWS = 1;         // #2
    
    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    //SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7
    
    float stateTime;                                        // #8
    
    public void create(String aName, int frames) {
            walkSheet = new Texture(Gdx.files.internal(aName));     // #9
            TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
frames, walkSheet.getHeight() / FRAME_ROWS);
            walkFrames = new TextureRegion[frames * FRAME_ROWS];
            int index = 0;
            for (int i = 0; i < FRAME_ROWS; i++) {
                    for (int j = 0; j < frames; j++) {
                            walkFrames[index++] = tmp[i][j];
                    }
            }
            walkAnimation = new Animation(0.1f, walkFrames);              // #11
      //      spriteBatch = new SpriteBatch();                                // #12
            stateTime = 0f;                                                 // #13
    }

    public void render(SpriteBatch batch, int x, int y) {
            //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);                                            // #14
            stateTime += Gdx.graphics.getDeltaTime();                       // #15
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);      // #16
            //spriteBatch.begin();
            batch.draw(currentFrame, x, y);                         // #17
            //spriteBatch.end();
    }


}