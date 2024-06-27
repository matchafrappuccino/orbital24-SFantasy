package com.arcane.game.UI;

import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Screens.ArcaneScreen;
import com.arcane.game.Screens.BattleScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ConfirmButton extends Button {

    private final static float margin = 0.1F;
    private final static float height = 0.15F;
    BattleScreen battleScreen;
    public ConfirmButton(String up, String down, Charlotte charlotte) {
        super();
        Texture upTexture = new Texture(Gdx.files.internal(up));
        Texture downTexture = new Texture(Gdx.files.internal(down));
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
        this.setStyle(style);
        this.setSize(upTexture.getWidth(), upTexture.getHeight());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                charlotte.endPlaying();
            }
        });
        this.setPosition(Gdx.graphics.getWidth() * (1 - margin)
                , Gdx.graphics.getHeight() * height);
        this.setScale(1, 1);
    }
}
