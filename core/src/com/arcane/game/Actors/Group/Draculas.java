package com.arcane.game.Actors.Group;

import com.arcane.game.Actors.Dracula;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Draculas extends Group {
    private final static float distance = -400F;
    private float nextPos = 0F;

    public Draculas() {
        super();
        setSize(0F, 0F);
    }

    @Override
    public void addActor(Actor actor) {
        if (!(actor instanceof Dracula)) {
            return;
        }
        actor.setPosition(nextPos, 0F);
        super.addActor(actor);
        nextPos += actor.getWidth() + distance;
        setSize(nextPos, 0F);

    }
}
