package com.arcane.game.Actors.Characters.Dracula;

import com.arcane.game.Actions.Performance;
import com.arcane.game.Actors.Character;
import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Initializer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.LinkedList;

import static com.arcane.game.Actors.Characters.Dracula.Draculas.sizePortion;

/*
    Marks:

    Milestone1:
    Dracula is the internal code name of all the enemies in this game.
    So it would be the father class of all enemy classes.
    By now, the only difference between Dracula and Charlotte is that the texture of Dracula would be flipped.

 */
public abstract class Dracula extends Character {
    private TextureRegion region;
    private Draculas draculas;
    private int MAXHP;
    private int curHP;
    private ProgressBar HPBar;
    private Label HPLabel;
    private DracuActions actions;
    private Initializer.DracuSys dracuSys;
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
        setActions();
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
    public Initializer.DracuSys linkSys(Initializer.DracuSys sys) {
        this.dracuSys = sys;
        return sys;
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
    public Charlotte getCharlotte() {
        return draculas.getCharlotte();
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
        curHP = Math.max(0, Math.min(curHP, MAXHP));
        setHP(curHP); //Update the HP bar.
        return curHP;
    }

    public abstract void noHP();

    public abstract void setActions();
    public final void setActions(DracuActions actions) {
        this.actions = actions;
    }
    public Performance getNextAction() {
        return this.actions.getNextAction();
    }
    public abstract void perform();

    public void deleteDracula() {
        draculas.removeActor(dracuSys);
    }

}
