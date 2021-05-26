package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Platform extends Actor {

    float posX;
    float posY;
    Texture texture;
    float width;
    float height;
    Body platform;

    public Platform(float posX, float posY, Texture texture, float width, float height) {
        this.posX = posX;
        this.posY = posY;
        this.texture = texture;
        this.width = width;
        this.height = height;
        setBounds(posX-width/2, posY-height/2, width, height);
    }

    public void createPlatform(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posX, posY);

        Body platform = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.1f;
        platform.createFixture(fixtureDef);
        platform.getFixtureList().get(0).setUserData("p");
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), width, height);
    }
}
