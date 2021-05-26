package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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

public class Level3 extends BaseScreen {
    Stage stage;
    OrthographicCamera camera;
    World world;
    Ball ball;
    Platform platform1, platform2;
    Background background;
    Button btnFinal;
    //Box2DDebugRenderer renderer;



    public Level3(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        //renderer = new Box2DDebugRenderer();
        btnFinal = new Button(12.6f, 0, new Texture("finish.png"), new Texture("finish.png"), 3.4f, 1);


        world = new World(new Vector2(0, 0), true);

        platform1 = new Platform(5, 6, new Texture("bushes/bushFor3.png"), 3.2f, 6);
        platform2 = new Platform(11, 3, new Texture("bushes/bushFor3.png"), 3.2f, 6);


        ball = new Ball(1, 8, 0.5f, 1);

        camera = new OrthographicCamera(16, 9);
        camera.position.set(new Vector2(8, 4.5f), 0);
        stage = new Stage(new FitViewport(16, 9, camera));
        background = new Background(new Texture("grass.png"), stage);
        stage.addActor(background);
        stage.addActor(platform1);
        stage.addActor(platform2);
        stage.addActor(btnFinal);
        //stage.addActor(btnBack);
        ball.createBall(world);
        stage.addActor(ball);
        createWall();
        platform1.createPlatform(world);
        platform2.createPlatform(world);
        world.setContactListener(new MyContactListener(this));

    }


    @Override
    public void render(float delta) {

        float fps = 60f;
        if (Gdx.graphics.getFramesPerSecond()> 0) fps = (float) Gdx.graphics.getFramesPerSecond();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //btnBack.update(new LevelsMenu(game));
        stage.act();
        stage.draw();
        if (ball.getX()>=12.6f && ball.getY()<=1) {
            ball.ball.setActive(false);
            game.setScreen(new Menu(game));
        }


        world.step(1/fps, 4,4);
        //renderer.render(world, camera.combined);

    }

    @Override
    public void dispose() {

    }

    private void createWall(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        Body b = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        //shape.createChain(new Vector2[]{new Vector2(-8,-4.5f), new Vector2(-8,4.5f), new Vector2(8,4.5f), new Vector2(8,-4.5f), new Vector2(-8,-4.5f)});
        shape.createChain(new Vector2[]{new Vector2(0,0), new Vector2(0,9), new Vector2(16,9), new Vector2(16,0), new Vector2(0,0)});
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