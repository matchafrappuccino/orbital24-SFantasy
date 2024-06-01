package com.arcane.game.UI;

import com.arcane.game.Screens.ArcaneScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class startButton extends ScreenChangeButton {
    public startButton(ArcaneScreen curScreen, ArcaneScreen newScreen) {
        super("UI/button.png", "UI/buttonPressed.png", curScreen, newScreen);
    }
}
