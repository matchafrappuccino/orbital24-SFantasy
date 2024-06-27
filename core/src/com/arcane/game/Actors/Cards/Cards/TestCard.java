package com.arcane.game.Actors.Cards.Cards;

import com.arcane.game.Actors.Cards.Card;
import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula.Dracula;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TestCard extends Card {
    private static final int cost = 2;
    private static final String assetPath = "Cards/EmptyCard.png";
    public TestCard(HandCards handCards) {
        super(assetPath, handCards);
    }

    @Override
    public boolean perform(Actor target) {
        if ((isToDracula() && target instanceof Dracula) || (!isToDracula() && target instanceof Charlotte)) {
            ((Dracula) target).affectHP(-30);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public boolean isToDracula() {
        return true;
    }

    @Override
    public int getMPChange() {
        return -cost;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
