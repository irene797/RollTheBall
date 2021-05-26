package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Background;
import com.mygdx.game.Button;
import com.mygdx.game.MyGame;


public class Menu extends BaseScreen {
    public Menu(MyGame game) {
        super(game);
    }
    Stage stage;
    Button button;
    Background background;
    OrthographicCamera camera;
    Preferences prefs;




    @Override
    public void show() {
        /*prefs = Gdx.app.getPreferences("MySuperWWWPreferences");
        String score = prefs.getString("score", "");
        if (score.equals("")){
            prefs.putString("score", "1");
        }
        if (score.equals("1")) {
            prefs.putString("score", "2");
        }
        else {
            int i = Integer.parseInt(score);
            i++;
            prefs.putString("score", Integer.toString(i));
        }

        prefs.flush();
        Gdx.app.log("MyTag", "hi - "+prefs.getString("score")+"   "+score);*/

        button = new Button(7, 4.5f, new Texture("buttonsTexture/btnplay.png"), new Texture("buttonsTexture/btnplay.png"), 2, 1);
        camera = new OrthographicCamera(16, 9);
        camera.position.set(new Vector2(8, 4.5f), 0);
        stage = new Stage(new FitViewport(16, 9, camera));
        background = new Background(new Texture("bgMenu.png"), stage);
        stage.addActor(background);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        button.update(new LevelsMenu(game));
        stage.act();
        stage.draw();

    }


    @Override
    public void hide() {

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
