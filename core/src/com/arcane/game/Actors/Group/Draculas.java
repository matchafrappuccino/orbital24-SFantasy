package com.arcane.game.Actors.Group;

import com.arcane.game.Actors.Dracula;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Draculas extends Group {
    private final static float distance = 10F;
    private float nextPos = 0F;
    private static final float rightMargin = 0.05F;
    private static final float heightPortion = 0.3F;
    private static final float sizePortion = 0.1F;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;

    public Draculas(float WORLD_WIDTH, float WORLD_HEIGHT) {
        super();
        setSize(0F, 0F);
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
    }

    @Override
    public void addActor(Actor actor) {
        if (!(actor instanceof Dracula)) {
            return;
        }
        actor.setPosition(nextPos, 0F);
        float ratio = actor.getHeight() / actor.getWidth();
        actor.setSize(WORLD_WIDTH * sizePortion, WORLD_WIDTH * sizePortion * ratio);
        super.addActor(actor);
        nextPos += actor.getWidth() + distance;
        setSize(nextPos, 0F);
        setPosition(WORLD_WIDTH *(1.0F - rightMargin)- this.getWidth(),
                WORLD_HEIGHT * heightPortion);
    }
}
