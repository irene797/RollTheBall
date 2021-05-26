package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Background;
import com.mygdx.game.Ball;
import com.mygdx.game.Button;
import com.mygdx.game.MyContactListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.Platform;

public class Level5 extends BaseScreen {
    Stage stage;
    OrthographicCamera camera;
    World world;
    Ball ball;
    Platform platform1, platform2, platform3, platform4, platform5, platform6;
    Background background;
    Button btnFinal;


    public Level5(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        btnFinal = new Button(15, 0, new Texture("finish2.png"), new Texture("finish2.png"), 1, 9);


        world = new World(new Vector2(0, 0), true);

        platform1 = new Platform(8, 0.5f, new Texture("bushes/bushFor5And6CB.png"), 12, 1);
        platform2 = new Platform(8, 8.5f, new Texture("bushes/bushFor5And6CT.png"), 12, 1);
        platform3 = new Platform(2.5f, 2.25f, new Texture("bushes/bushFor5And6LB.png"), 1, 2.5f);
        platform4 = new Platform(2.5f, 6.75f, new Texture("bushes/bushFor5And6LT.png"), 1, 2.5f);
        platform5 = new Platform(13.5f, 2.25f, new Texture("bushes/bushFor5And6RB.png"), 1, 2.5f);
        platform6 = new Platform(13.5f, 6.75f, new Texture("bushes/bushFor5And6RT.png"), 1, 2.5f);


        ball = new Ball(1, 8, 0.5f, 1);

        camera = new OrthographicCamera(16, 9);
        camera.position.set(new Vector2(8, 4.5f), 0);
        stage = new Stage(new FitViewport(16, 9, camera));
        background = new Background(new Texture("grass.png"), stage);
        stage.addActor(background);
        stage.addActor(platform1);
        stage.addActor(platform2);
        stage.addActor(platform3);
        stage.addActor(platform4);
        stage.addActor(platform5);
        stage.addActor(platform6);
        stage.addActor(btnFinal);
        ball.createBall(world);
        stage.addActor(ball);
        createWall();
        platform1.createPlatform(world);
        platform2.createPlatform(world);
        platform3.createPlatform(world);
        platform4.createPlatform(world);
        platform5.createPlatform(world);
        platform6.createPlatform(world);
        world.setContactListener(new MyContactListener(this));

    }


    @Override
    public void render(float delta) {

        float fps = 60f;
        if (Gdx.graphics.getFramesPerSecond() > 0) fps = (float) Gdx.graphics.getFramesPerSecond();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (ball.getX() >= 14.5f) {
            ball.ball.setActive(false);
            game.setScreen(new Menu(game));
        }


        world.step(1 / fps, 4, 4);


    }

    @Override
    public void dispose() {

    }

    private void createWall() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        Body b = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        ChainShape shape = new ChainShape();

        shape.createChain(new Vector2[]{new Vector2(0, 0), new Vector2(0, 9), new Vector2(16, 9), new Vector2(16, 0), new Vector2(0, 0)});
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.1f;
        b.createFixture(fixtureDef);
        b.getFixtureList().get(0).setUserData("w");
    }


    @Override
    public void resume() {
        Gdx.app.exit();
    }
}


