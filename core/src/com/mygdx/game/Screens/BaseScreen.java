package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGame;

public abstract class BaseScreen implements Screen {
    public static MyGame game;
    public int click;
    public BaseScreen(MyGame game) {
        this.game = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
