package com.arcane.game.Actors.Characters;

import com.arcane.game.Actions.Performance;
import com.arcane.game.Actors.Cards.Card;
import com.arcane.game.Actors.Cards.Cards.TestCard;
import com.arcane.game.Actors.Cards.Deck;
import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Cards.InitialSetting.BasicSetting;
import com.arcane.game.Actors.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    Marks:

    Milestone1:
    Charlotte is the internal code name of our main character.

 */
public class Charlotte extends Character {
    private TextureRegion region;
    private float ratio;
    private static final float portion = 0.1F;
    private static final float margin = 0.1F;
    private static final float heightPortion = 0.3F;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private int MAXHP = 100;
    private int curHP = MAXHP;
    private int MAXMP = 4;
    private int curMP = MAXMP;
    private ProgressBar HPBar;
    private Label HPLabel;
    private Label MPLabel;
    private HandCards handCards;
    private Deck deck;
    public Charlotte(LinkedList<Texture> textures, String path, float WORLD_WIDTH, float WORLD_HEIGHT, HandCards handCards) {
        super();
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.handCards = handCards;
        deck = new Deck(this);
        Texture tempTexture = new Texture(Gdx.files.internal(path));
        textures.add(tempTexture);
        this.region = new TextureRegion(tempTexture);
        ratio = (float) region.getRegionHeight() / region.getRegionWidth();
        setSize(WORLD_WIDTH * portion, WORLD_WIDTH * portion * ratio);
        setPosition(WORLD_WIDTH * margin, WORLD_HEIGHT * heightPortion);
    }
    //Link the character to the HP bar and then set its pos and size;
    public void linkHPBar(ProgressBar HPBar) {
        this.HPBar = HPBar;
        this.HPBar.setPosition(WORLD_WIDTH * margin, WORLD_HEIGHT * heightPortion);
        this.HPBar.setWidth(WORLD_WIDTH * portion);
    }

    //Link the character to the HP label.
    public void linkHPLabel(Label label) {
        this.HPLabel = label;
        label.setPosition(WORLD_WIDTH * margin + (getWidth() - label.getWidth()) / 2
                , WORLD_HEIGHT * heightPortion - label.getHeight() / 2 );
        label.setColor(1, 0, 0, 1);
    }

    public Charlotte(TextureRegion region) {
        super();
        this.region = region;
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion o) {
        this.region = o;
        this.setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    public void initialize() {
        setOrigin(0, 0);
        setScale(0.5F, 0.5F);
    }

    @Override
    public void act(float delta) {

    }

    public void newBattle() {
        this.deck.newBattle();
        triggerNextRound();
    }
    private boolean playing = true;

    public void endPlaying() {
        this.playing = false;
        this.handCards.endPlaying(deck);
    }

    public boolean roundEndConfirmed() {
        return !playing;
    }

    public void triggerNextRound() {
        playing  = true;
        this.handCards.triggerNextRound(deck);
        this.refreshMP();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //parentAlpha is a param a little complex to handle, so it's temporarily not handled here.

        super.draw(batch, parentAlpha);
        if (region == null || !isVisible()) {
            return;
        }

        batch.draw(
                region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );

    }

    public int getMaxHP() {
        return MAXHP;
    }

    public int getCurHP() {
        return curHP;
    }

    public int getMAXMP() {return MAXMP;}
    public int getCurMP() {return curMP;}
    public void refreshMP() {
        this.curMP = MAXMP;
    }
    public boolean isMPEnoughFor(Card card) {
        return card.getCost() <= curMP;
    }

    public int affectHP(int delta) {
        this.curHP += delta;
        curHP = Math.max(0, Math.min(curHP, MAXHP));
        this.setHP(curHP); //Update the HP bar and label.
        return curHP;
    }

    //Method to Update the HP bar.
    public void setHP(int HP) {
        this.HPBar.setValue(HP);
        this.HPLabel.setText(getCurHP() + " / " + getMaxHP());
    }

    public int affectMP(int delta) {
        this.curMP += delta;
        curMP = Math.max(0, Math.min(curMP, MAXMP));
        return curMP;
    }

    public ArrayList<Card> getSetting() {
        return new BasicSetting(handCards);
    }
}
