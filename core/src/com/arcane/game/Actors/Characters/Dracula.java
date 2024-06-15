package com.arcane.game.Actors.Characters;

import com.arcane.game.Actors.Initializer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.tools.corba.se.idl.PragmaEntry;

import java.util.LinkedList;

import static com.arcane.game.Actors.Characters.Draculas.sizePortion;

/*
    Marks:

    Milestone1:
    Dracula is the internal code name of all the enemies in this game.
    So it would be the father class of all enemy classes.
    By now, the only difference between Dracula and Charlotte is that the texture of Dracula would be flipped.

 */
public abstract class Dracula extends Actor {
    private TextureRegion region;
    private Draculas draculas;
    private int MAXHP;
    private int curHP;

    private ProgressBar HPBar;
    private Label HPLabel;

    public Dracula(LinkedList<Texture> textures, String path, Draculas draculas) {
        super();
        Texture tempTexture = new Texture(Gdx.files.internal(path));
        textures.add(tempTexture);
        this.region = new TextureRegion(tempTexture);
        this.region.flip(false, false);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        this.draculas = draculas;
        this.MAXHP = getMaxHP();
        this.curHP = getMaxHP();
        float ratio = this.getHeight() / this.getWidth();
        this.setSize(Gdx.graphics.getWidth() * sizePortion, Gdx.graphics.getWidth() * sizePortion * ratio);
        this.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!draculas.selectionActivated()) {
                    return;
                }
                draculas.selectDracula(itSelf());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!draculas.selectionActivated()) {
                    return;
                }
                draculas.unSelectDracula(itSelf());
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!draculas.selectionActivated()) {
                    return;
                }
                System.out.println("Confirmed");
                draculas.confirmSelection(itSelf());
                draculas.unSelectDracula(itSelf());
            }
        });
    }

    //Link the character to the HP bar and then set its pos and size;
    public void linkHPBar(ProgressBar HPBar) {
        this.HPBar = HPBar;
        this.HPBar.setPosition(0, 0);
        this.HPBar.setWidth(Gdx.graphics.getWidth() * sizePortion);
    }

    //Link the character to the HP label.
    public void linkHPLabel(Label label) {
        this.HPLabel = label;
        label.setPosition(0 + (getWidth() - label.getWidth()) / 2
                , 0 - label.getHeight() / 2 );
        label.setColor(1, 0, 0, 1);
    }

    private Dracula itSelf() {
        return this;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion o) {
        this.region = o;
        this.setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    public ProgressBar initializeHPBar() {
        this.HPBar = Initializer.initializeHPBar(getMaxHP());
        return HPBar;
    }
    @Override
    public abstract void act(float delta);

    public void setHP(int newValue) {
        this.HPBar.setValue(newValue);
        this.HPLabel.setText(this.getCurHP() + " / " + this.getMaxHP());
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

    public abstract int getMaxHP();
    public abstract int getInitialHP();
    public int getCurHP() {
        return curHP;
    }

    public int affectHP(int delta) {
        this.curHP += delta;
        System.out.println(curHP);
        curHP = Math.max(0, Math.min(curHP, MAXHP));
        setHP(curHP); //Update the HP bar.
        return curHP;
    }

    public abstract void noHP();

    public void deleteDracula() {
        draculas.removeActor(HPBar);
        draculas.removeActor(this);
    }

}
