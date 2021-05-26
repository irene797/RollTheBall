package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.mygdx.game.Screens.BaseScreen;
import com.mygdx.game.Screens.Level1;
import com.mygdx.game.Screens.Level3;
import com.mygdx.game.Screens.Level2;
import com.mygdx.game.Screens.Level4;
import com.mygdx.game.Screens.Level5;
import com.mygdx.game.Screens.Level6;

public class MyContactListener implements ContactListener {
    BaseScreen level;

    public MyContactListener(BaseScreen level) {
        this.level = level;

    }

    @Override
    public void beginContact(Contact contact) {


    }

    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        WorldManifold manifold = contact.getWorldManifold();
        for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("p") &&
                    contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("b")) {
                update();
            }
        }


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {


    }

    void update() {
        switch (level.getClass().toString()) {
            case "class com.mygdx.game.Screens.Level1":
                level.game.setScreen(new Level1(level.game));
                break;
            case "class com.mygdx.game.Screens.Level2":
                level.game.setScreen(new Level2(level.game));
                break;
            case "class com.mygdx.game.Screens.Level3":
                level.game.setScreen(new Level3(level.game));
                break;
            case "class com.mygdx.game.Screens.Level4":
                level.game.setScreen(new Level4(level.game));
                break;
            case "class com.mygdx.game.Screens.Level5":
                level.game.setScreen(new Level5(level.game));
                break;
            case "class com.mygdx.game.Screens.Level6":
                level.game.setScreen(new Level6(level.game));
                break;

        }
    }
}
