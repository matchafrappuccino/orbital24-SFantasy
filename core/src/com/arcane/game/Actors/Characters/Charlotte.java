package com.arcane.game.Actors.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;

/*
    Marks:

    Milestone1:
    Charlotte is the internal code name of our main character.

 */
public class Charlotte extends Actor {
    private TextureRegion region;
    private float ratio;
    private static final float portion = 0.1F;
    private static final float margin = 0.1F;
    private static final float heightPortion = 0.3F;
    public Charlotte(LinkedList<Texture> textures, String path, float WORLD_WIDTH, float WORLD_HEIGHT) {
        super();
        Texture tempTexture = new Texture(Gdx.files.internal(path));
        textures.add(tempTexture);
        this.region = new TextureRegion(tempTexture);
        ratio = (float) region.getRegionHeight() / region.getRegionWidth();
        setSize(WORLD_WIDTH * portion, WORLD_WIDTH * portion * ratio);
        setPosition(WORLD_WIDTH * margin, WORLD_HEIGHT * heightPortion);
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
