package com.arcane.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;

/*
    Marks:

    Milestone1:
    Dracula is the internal code name of all the enemies in this game.
    So it would be the father class of all enemy classes.
    By now, the only difference between Dracula and Charlotte is that the texture of Dracula would be flipped.

 */
public class Dracula extends Actor {
    private TextureRegion region;

    public Dracula(LinkedList<Texture> textures, String path) {
        super();
        Texture tempTexture = new Texture(Gdx.files.internal(path));
        textures.add(tempTexture);
        this.region = new TextureRegion(tempTexture);
        this.region.flip(false, false);
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    public Dracula(TextureRegion region) {
        super();
        this.region = region;
        this.region.flip(false, false);
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion o) {
        this.region = o;
        this.setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
}
