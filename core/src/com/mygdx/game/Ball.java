package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Screens.BaseScreen;

public class Ball extends Actor {
    float posX;
    float posY;
    Texture texture;
    float radius;
    public Body ball;
    float time = 0;
    public boolean click;

    public Ball(float posX, float posY, float radius, int skin) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        click = false;
        setBounds(posX, posY, radius * 2, radius * 2);
        if (skin == 1) texture = new Texture("football.png");
    }

    public void createBall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, posY);

        ball = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        fixtureDef.shape = shape;
        fixtureDef.density = 10;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.1f;
        //******
        /*
        fixtureDef.density = 2;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 0.5f;*/
        //******
        ball.createFixture(fixtureDef);
        ball.getFixtureList().get(0).setUserData("b");
    }




    @Override
    public void act(float delta) {
        time+=delta;
        if (Gdx.input.isTouched()) click = true;
        ball.setActive(click);
       


        float fps = 60f;
        if (Gdx.graphics.getFramesPerSecond() > 0)
            fps = (float) Gdx.graphics.getFramesPerSecond();
        int znakX = (int) (Gdx.input.getAccelerometerY() / Math.abs(Gdx.input.getAccelerometerY()));
        int znakY = (int) (Gdx.input.getAccelerometerX() / Math.abs(Gdx.input.getAccelerometerX()));
        ball.applyForceToCenter(new Vector2(1 / fps * 100 * znakX * Gdx.input.getAccelerometerY() * Gdx.input.getAccelerometerY(), 1 / fps * 100 * -znakY * Gdx.input.getAccelerometerX() * Gdx.input.getAccelerometerX()), true);

        setPosition(ball.getPosition().x - radius, ball.getPosition().y - radius);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!click)  batch.draw(new Texture("text.png"), 4, 8, 8, 1);
        batch.draw(texture, getX(), getY(), 2 * radius, 2 * radius);
        //super.draw(batch, parentAlpha);
    }
}
