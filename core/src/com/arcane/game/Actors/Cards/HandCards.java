package com.arcane.game.Actors.Cards;

import com.arcane.game.Actors.Characters.Dracula;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class HandCards extends Group {
    private final static float distance = 50F;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private float nextPos = 0F;
    public HandCards(float WORLD_WIDTH, float WORLD_HEIGHT) {
        super();
        setSize(0F, 0F);
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
    }

    @Override
    public void addActor(Actor actor) {

        if (!(actor instanceof Card)) {
            return;
        }

        actor.setPosition(nextPos, 0F);
        super.addActor(actor);
        nextPos += actor.getWidth() + distance;
        setSize(nextPos, actor.getHeight());
        setPosition(WORLD_WIDTH / 2 - (this.getWidth() / 2), 60F);
    }
}
