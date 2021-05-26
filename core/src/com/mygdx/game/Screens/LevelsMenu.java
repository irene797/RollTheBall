package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Background;
import com.mygdx.game.Button;
import com.mygdx.game.MyGame;

public class LevelsMenu extends  BaseScreen {
    public LevelsMenu(MyGame game) {
        super(game);
    }
    Stage stage;
    Button button1, button2, button3, button4, button5, button6;
    Background background;
    OrthographicCamera camera;

    @Override
    public void show() {

        button1 = new Button(5.7f, 4.3f, new Texture("buttonsTexture/btnew1.png"), new Texture("buttonsTexture/btnew1.png"), 1.3f, 1.3f);
        button2 = new Button(7.3f, 4.3f, new Texture("buttonsTexture/btnew2.png"), new Texture("buttonsTexture/btnew2.png"), 1.3f, 1.3f);
        button3 = new Button(8.9f, 4.3f, new Texture("buttonsTexture/btnew3.png"), new Texture("buttonsTexture/btnew3.png"), 1.3f, 1.3f);
        button4 = new Button(5.7f, 2.8f, new Texture("buttonsTexture/btnew4.png"), new Texture("buttonsTexture/btnew4.png"), 1.3f, 1.3f);
        button5 = new Button(7.3f, 2.8f, new Texture("buttonsTexture/btnew5.png"), new Texture("buttonsTexture/btnew5.png"), 1.3f, 1.3f);
        button6 = new Button(8.9f, 2.8f, new Texture("buttonsTexture/btnew6.png"), new Texture("buttonsTexture/btnew6.png"), 1.3f, 1.3f);

        camera = new OrthographicCamera(16, 9);
        camera.position.set(new Vector2(8, 4.5f), 0);
        stage = new Stage(new FitViewport(16, 9, camera));
        background = new Background(new Texture("bglvl.png"), stage);
        stage.addActor(background);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
        stage.addActor(button5);
        stage.addActor(button6);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        button1.update(new Level1(game));
        button2.update(new Level2(game));
        button3.update(new Level3(game));
        button4.update(new Level4(game));
        button5.update(new Level5(game));
        button6.update(new Level6(game));

        stage.act();
        stage.draw();

    }


    @Override
    public void resume() {
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
