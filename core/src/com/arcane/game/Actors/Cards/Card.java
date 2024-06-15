package com.arcane.game.Actors.Cards;

import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/*
    Card class would be a model for every specific card.
    Card would the father class of all cards.
    It will implement every functionality a card can have, while itself would be 'empty'.
*/
public abstract class Card extends Image {
    private HandCards handCards;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private boolean selected;
    private static final float ratio = 1.5F;
    private static final float portion = 0.1F;
    public Card (String path, HandCards handCards) {
        super(new Texture(Gdx.files.internal(path)));
        this.handCards = handCards;
        this.WORLD_WIDTH = Gdx.graphics.getWidth();
        this.WORLD_HEIGHT = Gdx.graphics.getHeight();
        selected = false;
        this.setSize(WORLD_WIDTH * portion, WORLD_WIDTH * portion * ratio);
        this.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (handCards.hasCardSelected() || !handCards.isMPEnoughFor(itSelf())) {
                    return;
                }
                setY(10F);
                setSize(WORLD_WIDTH * portion * 1.5F, WORLD_WIDTH * portion * ratio * 1.5F);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (selected && handCards.hasCardSelected()) {
                    return;
                }
                setSize(WORLD_WIDTH * portion, WORLD_WIDTH * portion * ratio);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (handCards.hasCardSelected() || !handCards.isMPEnoughFor(itSelf())) {
                    return;
                }
                selected = true;
                setSize(WORLD_WIDTH * portion * 1.5F, WORLD_WIDTH * portion * ratio * 1.5F);
                handCards.updateCards();
            }


        });
    }

    public abstract boolean perform(Actor target);

    public Card itSelf(){
        return this;
    }

    @Override
    public abstract void act(float delta);

    public void unSelect() {
        selected = false;
        setSize(WORLD_WIDTH * portion, WORLD_WIDTH * portion * ratio);
    }

    public boolean isSelected() {
        return selected;
    }

    public abstract boolean isToDracula();
    public abstract int getMPChange();
    public abstract int getCost();

}
