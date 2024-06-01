package com.arcane.game.UI;

import com.arcane.game.Screens.ArcaneScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ScreenChangeButton extends Button {
    ArcaneScreen curScreen;
    ArcaneScreen newScreen;
    public ScreenChangeButton(String up, String down, ArcaneScreen curScreen, ArcaneScreen newScreen) {
        super();
        Texture upTexture = new Texture(Gdx.files.internal(up));
        Texture downTexture = new Texture(Gdx.files.internal(down));
        Button.ButtonStyle style = new ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
        this.setStyle(style);
        this.setSize(upTexture.getWidth(), upTexture.getHeight());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                curScreen.getMainGame().setScreen(newScreen);
                curScreen.hide();
            }
        });
    }
}
