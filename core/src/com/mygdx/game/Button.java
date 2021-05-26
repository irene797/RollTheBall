package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Screens.BaseScreen;
import com.mygdx.game.Screens.Menu;


public class Button extends Actor {
    float posX;
    float posY;
    Texture textureNotPressed, texturePressed;
    float width;
    float height;
    public static final int NOTPRESSED = 0, PRESSED = 1, STOPPRESED = 2;
    public int state;
    float time = 0, delta;

    public Button(final float posX, final float posY, Texture textureNotPressed, Texture texturePressed, final float width, final float height) {
        //кнока рисуется из нижнего левого угла
        this.posX = posX;
        this.posY = posY;
        this.textureNotPressed = textureNotPressed;
        this.texturePressed = texturePressed;
        this.width = width;
        this.height = height;
        state = NOTPRESSED;
        setBounds(posX, posY, width, height);


        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = PRESSED;
                //Gdx.app.log("MyTag", "touchDown     x/y" + x + " / " + y);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (state == PRESSED ) {
                    try {Thread.sleep(200);} catch (InterruptedException e){}
                    state = STOPPRESED;}
                //Gdx.app.log("MyTag", "touchUp x/y" + x + " / " + y);

            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (x>=posX && x<=posX+width && y>=posY && y<=posY+height && state==PRESSED) state=NOTPRESSED;
               // Gdx.app.log("MyTag", "touchDragged x/y" + x + " / " + y);

            }
        });

    }

    public void update(BaseScreen screen) {
        if (state == STOPPRESED) {

            screen.game.setScreen(screen);
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (state == NOTPRESSED || state==STOPPRESED) {
            batch.draw(textureNotPressed, getX(), getY(), getWidth(), getHeight());
        }
        if (state == PRESSED) {
            batch.draw(texturePressed, getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void act(float delta) {
        this.delta = delta;
    }

    boolean anim(float delta) {
        time += delta;
        if (time > 10) {
            time = 0;
            return true;
        }
        else return false;
    }
}
