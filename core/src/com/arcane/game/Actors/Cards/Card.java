package com.arcane.game.Actors.Cards;

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
public class Card extends Image {
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private boolean selected;
    private static final float ratio = 1.5F;
    private static final float portion = 0.1F;
    private Texture arrow = new Texture(Gdx.files.internal("UI/arrow.png"));
    public Card (String path, float WORLD_WIDTH, float WORLD_HEIGHT) {
        super(new Texture(Gdx.files.internal(path)));
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        selected = false;
        this.setSize(WORLD_WIDTH * portion, WORLD_WIDTH * portion * ratio);
        this.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                selected = true;
                setDrawable(new TextureRegionDrawable(
                        mergeTexturesVertically(((TextureRegionDrawable) getDrawable()).getRegion().getTexture()
                                , arrow)
                ));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal(path))));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
                return;
            }
        });
    }

    @Override
    public void act(float delta) {
        return; //Do nothing
    }

    public Texture mergeTexturesVertically(Texture texture1, Texture texture2) {

        if (!texture1.getTextureData().isPrepared()) {
            texture1.getTextureData().prepare();
        }
        if (!texture2.getTextureData().isPrepared()) {
            texture2.getTextureData().prepare();
        }

        Pixmap pixmap1 = texture1.getTextureData().consumePixmap();
        Pixmap pixmap2 = texture2.getTextureData().consumePixmap();

        int width = Math.max(texture1.getWidth(), texture2.getWidth());
        int height = texture1.getHeight() + texture2.getHeight();
        Pixmap pixmap = new Pixmap(width, height, pixmap1.getFormat());

        pixmap.drawPixmap(pixmap1, 0, 0, 0, 0, texture1.getWidth(), texture1.getHeight());

        pixmap.drawPixmap(pixmap2, 0, texture1.getHeight(), 0, 0, texture2.getWidth(), texture2.getHeight());

        Texture combinedTexture = new Texture(pixmap);

        pixmap1.dispose();
        pixmap2.dispose();
        pixmap.dispose();

        return combinedTexture;
    }
}
