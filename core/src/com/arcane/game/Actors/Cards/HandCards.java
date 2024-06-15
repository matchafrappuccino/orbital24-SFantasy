package com.arcane.game.Actors.Cards;

import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula;
import com.arcane.game.Actors.Characters.Draculas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class HandCards extends Group {
    private int cardNum = 0;
    private Card selectedCard = null;
    private final static float distance = 50F;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private Charlotte charlotte;
    private Draculas draculas;

    public HandCards(Charlotte charlotte, Draculas draculas) {
        super();
        setSize(0F, 0F);
        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();
        this.charlotte = charlotte;
        this.draculas = draculas;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        updateCards();
        super.draw(batch, parentAlpha);
    }

    @Override
    public void addActor(Actor actor) {

        if (!(actor instanceof Card)) {
            return;
        }
        super.addActor(actor);
        cardNum++;
        updateCards();
    }

    @Override
    public boolean removeActor(Actor actor) {
        super.removeActor(actor);
        cardNum--;
        return true;
    }

    public void updateCards() {
        float curWidth = 0F;
        for (int i = 0; i < cardNum; i++) {
            Actor cur = this.getChildren().get(i);
            cur.setPosition(curWidth, 0F);
            curWidth += cur.getWidth() - 20;
            if (((Card) cur).isSelected()) {
                selectedCard = (Card) cur;
            }
        }
        setSize(curWidth, 0);
        setPosition(WORLD_WIDTH / 2 - (this.getWidth() / 2), 0F);
    }

    public boolean hasCardSelected() {
        return selectedCard != null;
    }

    public boolean hasCardSelectedToDracula() {
        return selectedCard != null && selectedCard.isToDracula();
    }

    public void unSelectCard() {
        if (selectedCard != null) {
            selectedCard.unSelect();
            selectedCard = null;
            updateCards();
        }
    }

    public void perform(Actor target) {
        if (selectedCard.perform(target)) {
            charlotte.affectMP(selectedCard.getMPChange());
            removeActor(selectedCard);
            selectedCard = null;
        } else {
        }
    }

    public void newRound() {

    }

    public boolean isMPEnoughFor(Card card) {

        return charlotte.isMPEnoughFor(card);
    }

}
