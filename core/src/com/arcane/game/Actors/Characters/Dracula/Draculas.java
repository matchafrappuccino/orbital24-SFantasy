package com.arcane.game.Actors.Characters.Dracula;

import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Character;
import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula.Dracula;
import com.arcane.game.Actors.Initializer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Draculas extends Group {
    private final static float distance = 10F;
    private float nextPos = 0F;
    private static final float rightMargin = 0.05F;
    private static final float heightPortion = 0.3F;
    public static final float sizePortion = 0.1F;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private HandCards handCards;
    private Charlotte charlotte;
    private Image arrow;
    //private Dracula dracula;

    public Draculas(float WORLD_WIDTH, float WORLD_HEIGHT, HandCards handCards, Charlotte charlotte) {
        super();
        setSize(0F, 0F);
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.handCards = handCards;
        arrow = new Image(new TextureRegion(new Texture("UI/arrow.png")));
        this.charlotte = charlotte;
    }

    public void selectDracula(Dracula target) {
        float xOfArrow = target.getX() + target.getWidth() / 2;
        float yOfArrow = target.getY() + target.getHeight() + 10;
        arrow.setPosition(xOfArrow, yOfArrow);
        super.addActor(arrow);
    }

    public void unSelectDracula(Dracula target) {
        super.removeActor(arrow);
    }

    public void confirmSelection(Dracula target) {
        handCards.perform(target);
    }

    public boolean selectionActivated() {
        return handCards.hasCardSelectedToDracula();
    }
    public Charlotte getCharlotte() {
        return charlotte;
    }

    @Override
    public void act(float delta) {
        this.getChildren().forEach(actor -> actor.act(delta));
        if (charlotte.roundEndConfirmed() && this.getChildren() != null) {
            this.getChildren().forEach(actor -> {
                if (actor instanceof Initializer.DracuSys) {
                    ((Initializer.DracuSys) actor).getDracula().perform();
                }
            });
            triggerNextRound();
        }
    }

    public void triggerNextRound() {
        this.charlotte.triggerNextRound();
    }

    @Override
    public void addActor(Actor group) {
        if (!(group instanceof Initializer.DracuSys)) {
            return;
        }
        group.setPosition(nextPos, 0F);
        nextPos += group.getWidth() + distance;
        setSize(nextPos, 0F);
        setPosition(WORLD_WIDTH *(1.0F - rightMargin)- this.getWidth(),
                WORLD_HEIGHT * heightPortion);
        super.addActor(group);
    }
}
