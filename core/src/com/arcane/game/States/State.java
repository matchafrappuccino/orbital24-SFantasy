package com.arcane.game.States;

import com.badlogic.gdx.scenes.scene2d.Actor;

//Common properties of all states
public abstract class State {
    private final Actor owner;
    private int roundsLeft;
    public State(Actor owner, int rounds) {
        this.owner = owner;
        this.roundsLeft = rounds;
    }

    public Actor getOwner() {
        return owner;
    }

    public boolean isExhausted() {
        return roundsLeft <= 0;
    }

    public void updateRound() {
        roundsLeft--;
    }
}