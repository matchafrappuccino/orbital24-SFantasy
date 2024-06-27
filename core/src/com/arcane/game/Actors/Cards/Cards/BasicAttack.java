package com.arcane.game.Actors.Cards.Cards;

import com.arcane.game.Actors.Cards.Card;
import com.arcane.game.Actors.Cards.HandCards;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BasicAttack extends Card {
    public BasicAttack(String path, HandCards handCards) {
        super(path, handCards);
    }

    @Override
    public boolean perform(Actor target) {
        return false;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public boolean isToDracula() {
        return false;
    }

    @Override
    public int getMPChange() {
        return 0;
    }

    @Override
    public int getCost() {
        return 0;
    }
}
