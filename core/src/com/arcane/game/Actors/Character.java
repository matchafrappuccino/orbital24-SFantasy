package com.arcane.game.Actors;

import com.arcane.game.Actions.Performance;
import com.arcane.game.States.State;
import com.arcane.game.States.ToActions.StateToAction;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Character extends Actor {
    ArrayList<State> states;
    public Character() {
        super();
        states = new ArrayList<>();
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public void updateStateRounds() {
        states.forEach(State::updateRound);
        states = states.stream()
                .filter(state -> !state.isExhausted())
                .collect(Collectors.toCollection(ArrayList<State>::new));;
    }

    public void affectAction(Performance performance) {
        states.forEach((state) -> {
            if (state instanceof StateToAction) {
                ((StateToAction) state).tryToAffectAction(performance);
            }
        });
    }
}
